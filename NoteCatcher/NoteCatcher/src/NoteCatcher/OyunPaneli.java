package NoteCatcher;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.*;

public abstract class OyunPaneli extends JPanel implements ActionListener {
    
    protected static final long UZUN_NOTA_SURESI = 800;
    protected static final int TEMEL_NOTA_ARALIGI = 900;
    protected static final int DOGRU_NOTA_PUANI = 100;
    protected static final int UZUN_NOTA_PUANI = 200;
    protected static final int YANLIS_NOTA_CEZASI = 50;
    protected static final String[] TUS_ISIMLERI = {"A", "S", "D", "F", "J", "K", "L"};
    protected static final String[] SES_DOSYALARI = {"do-s.wav", "re-s.wav", "mi-s.wav", "fa-s.wav", "sol-s.wav", "la-s.wav", "si-s.wav"};
    protected static final char[] TUS_DIZISI = {'a', 's', 'd', 'f', 'j', 'k', 'l'};
    protected static final Map<Character, String> TUS_ESLEMESI = new HashMap<>();
    protected static final Map<Character, Integer> TUS_INDEX = new HashMap<>();
    
    static {
        for (int i = 0; i < TUS_DIZISI.length; i++) {
            TUS_ESLEMESI.put(TUS_DIZISI[i], SES_DOSYALARI[i]);
            TUS_INDEX.put(TUS_DIZISI[i], i);
        }
    }
   
    protected JFrame anaFrame;                              
    protected ArrayList<Not> aktifNotalar = new ArrayList<>(); 
    protected String[] notalar;                             
    protected boolean[] uzunNotalar;                       
    protected Timer zamanlayici, notaEklemeZamanlayici;     
    protected int notaindex = 0;                            
    protected int notDusmeHizi = 4;                         
    protected int mevcutPuan = 0;                           
    protected int  enYuksekPuan = 0;                         
    protected boolean oyunBitti = false;                    
    protected String sarkiAdi = "";                         
    protected boolean[] tusBasili = new boolean[7];         
    protected long[] tusBasilmaZamanlari = new long[7];     

    public OyunPaneli(JFrame frame) {
        this.anaFrame = frame;
        setLayout(null);
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new KlavyeDinleyici());
        zamanlayici = new Timer(20, this);  
    }
    
    protected void setSarkiAdi(String ad) {
        this.sarkiAdi = ad;
        this.enYuksekPuan = puanYukle();
    }
    
    private int puanYukle() {
        try {
            Properties p = new Properties();
            File f = new File("highscores.dat");
            if (f.exists()) { 
                p.load(new FileInputStream(f));
                return Integer.parseInt(p.getProperty(sarkiAdi, "0"));
            }
        } catch (Exception e) {}
        return 0;
    }
    
    private void puanKaydet() {
        try {
            Properties p = new Properties();
            File f = new File("highscores.dat");
            if (f.exists()) p.load(new FileInputStream(f));
            p.setProperty(sarkiAdi, String.valueOf(enYuksekPuan));
            p.store(new FileOutputStream(f), null);
        } catch (Exception e) {}
    }
    
    public boolean isBeklemede() {
        for (Not n : aktifNotalar)
            if (n.hataliMi && !n.calindiMi && (!n.uzunNotaMi || !n.tuşaBasildi)) return true;
        return false;
    }

    protected void setHiz(int hiz) { this.notDusmeHizi = hiz; }
    
    protected void sonrakiNotayiEkle() {
        if (notaindex >= notalar.length) return;
        
        if (isBeklemede()) { 
            new Timer(100, e -> { 
                ((Timer)e.getSource()).stop(); 
                sonrakiNotayiEkle(); 
            }).start(); 
            return; 
        }
        
        boolean uzunMu = uzunNotalar != null && notaindex < uzunNotalar.length && uzunNotalar[notaindex];
        
        notaEkle(notalar[notaindex], uzunMu);
        notaindex++;
        
        if (notaindex < notalar.length) {
            if (notaEklemeZamanlayici != null) notaEklemeZamanlayici.stop();
            notaEklemeZamanlayici = new Timer((int)(TEMEL_NOTA_ARALIGI * (uzunMu ? 2f : 1f)), e -> sonrakiNotayiEkle());
            notaEklemeZamanlayici.setRepeats(false);
            notaEklemeZamanlayici.start();
        }
    }
    
    protected void notaEklemeBaslat(String[] notalar, boolean[] uzunNotalar) {
        this.notalar = notalar;
        this.uzunNotalar = uzunNotalar;
        this.notaindex = 0;
        new Timer(500, e -> { sonrakiNotayiEkle(); ((Timer)e.getSource()).stop(); }).start();
    }

    protected void notaEkle(String notaAdi) { notaEkle(notaAdi, false); }
    
    protected void notaEkle(String notaAdi, boolean uzunNotaMi) {
        for (int j = 0; j < SES_DOSYALARI.length; j++)
            if (SES_DOSYALARI[j].equals(notaAdi)) { 
                aktifNotalar.add(new Not(j, -50, notaAdi, uzunNotaMi, false)); 
                return; 
            }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (notalar != null && notaindex >= notalar.length && aktifNotalar.isEmpty() && !oyunBitti) {
            oyunBitti = true;
            zamanlayici.stop();
            
            boolean yeniRekor = mevcutPuan > enYuksekPuan;
            if (yeniRekor) { enYuksekPuan = mevcutPuan; puanKaydet(); }
            
            JOptionPane.showMessageDialog(this, 
                "Şarkıyı Bitirdiniz!\n\nPuanınız: " + mevcutPuan + 
                "\nRekor: " + enYuksekPuan + 
                (yeniRekor ? "\n\n🏆 YENİ REKOR! 🏆" : ""));
            anaMenuyeDon();
            return;
        }

        int esikY = getHeight() - 200;
        int hedefY = esikY - 40;
        boolean duraklat = false;

        for (Not n : aktifNotalar) {
            int tolerans = n.uzunNotaMi ? 60 : 0;
            if (!n.calindiMi && n.y + n.uzunluk >= hedefY + tolerans) {
                if (!n.hataliMi) mevcutPuan = Math.max(0, mevcutPuan - YANLIS_NOTA_CEZASI);
                n.hataliMi = true;
                n.y = hedefY + tolerans - n.uzunluk;
                duraklat = true;
            }
        }

        for (int i = 0; i < aktifNotalar.size(); i++) {
            Not n = aktifNotalar.get(i);
            if (n.calindiMi) {
                n.y += 15;
                if (n.y > getHeight()) {
                    if (n.midiNotaNo != null) SesCal.notaDurdur(n.midiNotaNo);
                    aktifNotalar.remove(i--);
                }
            } else if (!duraklat) {
                n.y += notDusmeHizi;
            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int tusGenislik = getWidth() / 7;
        int esikY = getHeight() - 200;
        
        g.setColor(new Color(20, 20, 40));
        g.fillRect(0, 0, getWidth(), getHeight());
        
        for (int i = 0; i < 7; i++) {
            int x = i * tusGenislik;
            g.setColor(tusBasili[i] ? new Color(200, 200, 200) : Color.WHITE);
            g.fillRect(x + 1, esikY, tusGenislik - 2, 200);
            g.setColor(Color.BLACK);
            g.drawRect(x, esikY, tusGenislik, 200);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString(TUS_ISIMLERI[i], x + tusGenislik / 2 - 7, getHeight() - 20);
        }
        
        g.setColor(new Color(255, 215, 0));
        g.fillRect(0, esikY - 40, getWidth(), 5);
        
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Puan: " + mevcutPuan, getWidth() - 164, 40);
        g.setColor(new Color(100, 255, 100));
        g.drawString("Rekor: " + enYuksekPuan, getWidth() - 179, 70);

        for (Not n : new ArrayList<>(aktifNotalar)) {
            int notaX = n.satir * tusGenislik + 5;
            int notaGenislik = tusGenislik - 10;
            
            float ilerleme = (n.uzunNotaMi && n.tuşaBasildi && !n.calindiMi) ? 
                Math.min(1f, (System.currentTimeMillis() - n.baslamaZamani) / (float)UZUN_NOTA_SURESI) : 0;
            
            g.setColor(n.calindiMi ? new Color(100, 255, 100) :
                       n.hataliMi ? new Color(255, 100, 100) :
                       n.uzunNotaMi ? new Color(255, 150, 50) :
                       new Color(100, 150, 255));
            g.fillRect(notaX, n.y, notaGenislik, n.uzunluk);
            
            g.setColor(Color.WHITE);
            g.drawRect(notaX, n.y, notaGenislik, n.uzunluk);
            
            if (n.uzunNotaMi && n.tuşaBasildi && !n.calindiMi) {
                int doluY = (int)(n.uzunluk * ilerleme);
                g.setColor(new Color((int)(255 * (1 - ilerleme)), 255, 0));
                g.fillRect(notaX + 2, n.y + n.uzunluk - doluY, notaGenislik - 4, doluY);
                g.setFont(new Font("Arial", Font.BOLD, 20));
                g.setColor(Color.BLACK);
                g.drawString((int)(ilerleme * 100) + "%", notaX + notaGenislik / 2 - 15, n.y + n.uzunluk / 2 + 7);
            }
            
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.setColor(Color.WHITE);
            g.drawString(n.notaAdi.split("-")[0].toUpperCase(), notaX + notaGenislik / 2 - 10, n.y + 26);
            
            if (n.uzunNotaMi && !n.calindiMi && !n.hataliMi) {
                g.setFont(new Font("Arial", Font.BOLD, 11));
                g.setColor(n.tuşaBasildi ? new Color(255, 255, 100) : new Color(255, 200, 100));
                g.drawString(n.tuşaBasildi ? "TUTUN!" : "UZUN", notaX + notaGenislik / 2 - 19, n.y + 42);
            }
        }
    }

    protected void anaMenuyeDon() {
        zamanlayici.stop();
        if (anaFrame != null) {
            anaFrame.getContentPane().removeAll();
            anaFrame.add(new AnaMenu(anaFrame));
            anaFrame.revalidate();
            anaFrame.repaint();
        }
    }

    private class KlavyeDinleyici extends KeyAdapter {
        
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { anaMenuyeDon(); return; }
            
            char c = Character.toLowerCase(e.getKeyChar());
            
            if (TUS_INDEX.containsKey(c)) {
                int i = TUS_INDEX.get(c);
                if (!tusBasili[i]) {
                    tusBasili[i] = true;
                    tusBasilmaZamanlari[i] = System.currentTimeMillis();
                    notayiKontrolEt(c, i);
                }
            }
            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            char c = Character.toLowerCase(e.getKeyChar());
            if (TUS_INDEX.containsKey(c)) {
                int i = TUS_INDEX.get(c);
                uzunNotayiDogrula(i, System.currentTimeMillis() - tusBasilmaZamanlari[i]);
                tusBasili[i] = false;
            }
            repaint();
        }
    }

    private void notayiKontrolEt(char tusChar, int tusIndex) {
        int esikY = getHeight() - 200;
        int hedefY = esikY - 40;
        
        for (Not n : aktifNotalar) {
            if (n.satir == tusIndex && !n.calindiMi && n.y + n.uzunluk >= hedefY - 50 && n.y < esikY) {
                if (n.uzunNotaMi) {
                    n.tuşaBasildi = true;
                    n.baslamaZamani = System.currentTimeMillis();
                    n.hataliMi = false;
                    n.midiNotaNo = SesCal.notaBaslat(TUS_ESLEMESI.get(tusChar));
                } else {
                    SesCal.kisaNotaCal(TUS_ESLEMESI.get(tusChar));
                    n.calindiMi = true;
                    n.hataliMi = false;
                    mevcutPuan += DOGRU_NOTA_PUANI;
                }
                return;
            }
        }
    }
    
    private void uzunNotayiDogrula(int satirIndex, long basmaSuresi) {
        for (Not n : aktifNotalar) {
            if (n.satir == satirIndex && n.uzunNotaMi && n.tuşaBasildi && !n.calindiMi) {
                if (n.midiNotaNo != null) { SesCal.notaDurdur(n.midiNotaNo); n.midiNotaNo = null; }
                
                if (basmaSuresi >= UZUN_NOTA_SURESI) {
                    n.calindiMi = true;
                    n.hataliMi = false;
                    mevcutPuan += UZUN_NOTA_PUANI;
                } else {
                    n.hataliMi = true;
                    n.tuşaBasildi = false;
                    mevcutPuan = Math.max(0, mevcutPuan - YANLIS_NOTA_CEZASI);
                }
                return;
            }
        }
    }
}
