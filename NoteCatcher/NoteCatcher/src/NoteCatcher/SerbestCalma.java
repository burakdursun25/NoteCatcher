package NoteCatcher;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class SerbestCalma extends JPanel {
    private JFrame anaFrame;
    
    private static final String[] BEYAZ_TUS_ISIMLERI = {"A", "S", "D", "F", "J", "K", "L"};
    private static final String[] BEYAZ_NOTA_ISIMLERI = {"Do", "Re", "Mi", "Fa", "Sol", "La", "Si"};
    private static final String[] BEYAZ_SES_DOSYALARI = {"do-s.wav", "re-s.wav", "mi-s.wav", "fa-s.wav", "sol-s.wav", "la-s.wav", "si-s.wav"};
    private static final char[] BEYAZ_TUS_DIZISI = {'a', 's', 'd', 'f', 'j', 'k', 'l'};
    
    private static final String[] SIYAH_TUS_ISIMLERI = {"W", "E", "T", "Y", "U"};
    private static final String[] SIYAH_NOTA_ISIMLERI = {"Do#", "Re#", "Fa#", "Sol#", "La#"};
    private static final String[] SIYAH_SES_DOSYALARI = {"do_diez-s.wav", "re_diez-s.wav", "fa_diez-s.wav", "sol_diez-s.wav", "la_diez-s.wav"};
    private static final char[] SIYAH_TUS_DIZISI = {'w', 'e', 't', 'y', 'u'};
    private static final int[] SIYAH_TUS_POZISYONLARI = {0, 1, 3, 4, 5};
    
    private boolean[] beyazTusBasili = new boolean[7];
    private boolean[] siyahTusBasili = new boolean[5];
    
    private static final Map<Character, String> TUS_ESLEMESI = new HashMap<>();
    private static final Map<Character, Integer> BEYAZ_TUS_INDEX = new HashMap<>();
    private static final Map<Character, Integer> SIYAH_TUS_INDEX = new HashMap<>();
    
    static {
        for(int i=0; i<BEYAZ_TUS_DIZISI.length; i++) {
            TUS_ESLEMESI.put(BEYAZ_TUS_DIZISI[i], BEYAZ_SES_DOSYALARI[i]);
            BEYAZ_TUS_INDEX.put(BEYAZ_TUS_DIZISI[i], i);
        }
        for(int i=0; i<SIYAH_TUS_DIZISI.length; i++) {
            TUS_ESLEMESI.put(SIYAH_TUS_DIZISI[i], SIYAH_SES_DOSYALARI[i]);
            SIYAH_TUS_INDEX.put(SIYAH_TUS_DIZISI[i], i);
        }
    }

    public SerbestCalma(JFrame frame) {
        this.anaFrame = frame;
        setLayout(null);
        setBackground(new Color(20, 20, 40));
        setFocusable(true);
        addKeyListener(new KlavyeDinleyici());
        
        JLabel baslik = new JLabel("🎹 Serbest Çalma Modu");
        baslik.setFont(new Font("Arial", Font.BOLD, 32));
        baslik.setForeground(new Color(255, 215, 0));
        baslik.setBounds(250, 30, 400, 50);
        add(baslik);
        
        JLabel aciklama = new JLabel("<html><center>İstediğiniz notayı çalabilirsiniz!<br>" +
                "Beyaz Tuşlar: A, S, D, F, J, K, L<br>" +
                "Siyah Tuşlar: W, E, T, Y, U<br>" +
                "ESC ile ana menüye dönün</center></html>");
        aciklama.setFont(new Font("Arial", Font.PLAIN, 14));
        aciklama.setForeground(new Color(200, 200, 255));
        aciklama.setHorizontalAlignment(SwingConstants.CENTER);
        aciklama.setBounds(200, 90, 500, 80);
        add(aciklama);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int beyazTusGenislik = getWidth() / 7;
        int esikY = getHeight() / 2 - 100;
        int tusYukseklik = 200;
        int siyahTusYukseklik = 130;
        int siyahTusGenislik = (int)(beyazTusGenislik * 0.6);

        for (int i = 0; i < 7; i++) {
            int x = i * beyazTusGenislik;
            
            if (beyazTusBasili[i]) {
                g.setColor(new Color(200, 200, 200));
            } else {
                g.setColor(Color.WHITE);
            }
            g.fillRect(x + 1, esikY, beyazTusGenislik - 2, tusYukseklik);
            
            g.setColor(Color.BLACK);
            g.drawRect(x, esikY, beyazTusGenislik, tusYukseklik);
            
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.setColor(Color.BLACK);
            g.drawString(BEYAZ_TUS_ISIMLERI[i], x + beyazTusGenislik/2 - 8, esikY + tusYukseklik - 20);
            
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString(BEYAZ_NOTA_ISIMLERI[i], x + beyazTusGenislik/2 - 12, esikY + tusYukseklik - 45);
        }
        
        for (int i = 0; i < 5; i++) {
            int beyazTusIndex = SIYAH_TUS_POZISYONLARI[i];
            int x = (beyazTusIndex * beyazTusGenislik) + beyazTusGenislik - (siyahTusGenislik / 2);
            
            if (siyahTusBasili[i]) {
                g.setColor(new Color(80, 80, 80));
            } else {
                g.setColor(Color.BLACK);
            }
            g.fillRect(x, esikY, siyahTusGenislik, siyahTusYukseklik);
            
            g.setColor(new Color(50, 50, 50));
            g.drawRect(x, esikY, siyahTusGenislik, siyahTusYukseklik);
            
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.setColor(Color.WHITE);
            g.drawString(SIYAH_TUS_ISIMLERI[i], x + siyahTusGenislik/2 - 7, esikY + siyahTusYukseklik - 20);
            
            g.setFont(new Font("Arial", Font.PLAIN, 13));
            g.drawString(SIYAH_NOTA_ISIMLERI[i], x + siyahTusGenislik/2 - 12, esikY + siyahTusYukseklik - 40);
        }
        
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.setColor(new Color(100, 255, 100));
        String bilgi = "Tuşlara basarak nota çalın";
        g.drawString(bilgi, getWidth()/2 - 130, esikY - 30);
    }

    private void anaMenuyeDon() {
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
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                anaMenuyeDon();
                return;
            }
            
            char tusChar = Character.toLowerCase(e.getKeyChar());
            
            if (BEYAZ_TUS_INDEX.containsKey(tusChar)) {
                int i = BEYAZ_TUS_INDEX.get(tusChar);
                if (!beyazTusBasili[i]) {
                    beyazTusBasili[i] = true;
                    SesCal.kisaNotaCal(TUS_ESLEMESI.get(tusChar));
                    repaint();
                }
            }
            
            if (SIYAH_TUS_INDEX.containsKey(tusChar)) {
                int i = SIYAH_TUS_INDEX.get(tusChar);
                if (!siyahTusBasili[i]) {
                    siyahTusBasili[i] = true;
                    SesCal.kisaNotaCal(TUS_ESLEMESI.get(tusChar));
                    repaint();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            char tusChar = Character.toLowerCase(e.getKeyChar());
            
            if (BEYAZ_TUS_INDEX.containsKey(tusChar)) {
                int i = BEYAZ_TUS_INDEX.get(tusChar);
                beyazTusBasili[i] = false;
                repaint();
            }
            
            if (SIYAH_TUS_INDEX.containsKey(tusChar)) {
                int i = SIYAH_TUS_INDEX.get(tusChar);
                siyahTusBasili[i] = false;
                repaint();
            }
        }
    }
}
