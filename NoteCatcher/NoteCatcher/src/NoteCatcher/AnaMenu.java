package NoteCatcher;

import java.awt.*;
import javax.swing.*;

public class AnaMenu extends JPanel {
    JButton twinkle, mary, ode, furElise, canon;
    JLabel baslik, aciklama;
    JFrame anaFrame;

    public AnaMenu(JFrame frame) {
        this.anaFrame = frame;
        setLayout(null);
        setBackground(new Color(25, 15, 45));

        baslik = new JLabel("NoteCatcher");
        baslik.setFont(new Font("Arial", Font.BOLD, 36));
        baslik.setForeground(new Color(255, 215, 0));
        baslik.setBounds(350, 20, 500, 50);
        this.add(baslik);

        aciklama = new JLabel("<html><center>🎹 Press the correct key as notes fall!<br>" +
                "<b>White Keys:</b> A, S, D, F, J, K, L (Do, Re, Mi, Fa, Sol, La, Si) <br>" +
                "⌨️ Press ESC to return to main menu</center></html>");
        aciklama.setFont(new Font("Arial", Font.PLAIN, 15));
        aciklama.setForeground(new Color(200, 200, 255));
        aciklama.setHorizontalAlignment(SwingConstants.CENTER);
        aciklama.setBounds(150, 70, 600, 70);
        this.add(aciklama);

        JLabel kolay = new JLabel(" Easy");
        kolay.setFont(new Font("Arial", Font.BOLD, 14));
        kolay.setForeground(new Color(100, 255, 100));
        kolay.setBounds(260, 145, 100, 20);
        this.add(kolay);

        twinkle = creatSimpleButton(" Twinkle Twinkle Little Star", new Color(100, 149, 237));
        twinkle.setBounds(250, 165, 400, 50);
        twinkle.addActionListener(e -> seviyeDegistir(new SarkiTwinkle(anaFrame)));
        this.add(twinkle);

        mary = creatSimpleButton(" Mary Had a Little Lamb", new Color(100, 149, 237));
        mary.setBounds(250, 220, 400, 50);
        mary.addActionListener(e -> seviyeDegistir(new SarkiMary(anaFrame)));
        this.add(mary);

        JLabel orta = new JLabel(" Medium");
        orta.setFont(new Font("Arial", Font.BOLD, 14));
        orta.setForeground(new Color(255, 200, 100));
        orta.setBounds(260, 280, 100, 20);
        this.add(orta);

        ode = creatSimpleButton(" Ode to Joy - Beethoven", new Color(255, 140, 0));
        ode.setBounds(250, 300, 400, 50);
        ode.addActionListener(e -> seviyeDegistir(new SarkiOde(anaFrame)));
        this.add(ode);

        JLabel zor = new JLabel(" Hard");
        zor.setFont(new Font("Arial", Font.BOLD, 14));
        zor.setForeground(new Color(255, 100, 100));
        zor.setBounds(260, 360, 100, 20);
        this.add(zor);

        furElise = creatSimpleButton(" Für Elise - Beethoven", new Color(220, 20, 60));
        furElise.setBounds(250, 380, 400, 50);
        furElise.addActionListener(e -> seviyeDegistir(new SarkiFurElise(anaFrame)));
        this.add(furElise);

        canon = creatSimpleButton(" Canon in D - Pachelbel", new Color(148, 0, 211));
        canon.setBounds(250, 435, 400, 50);
        canon.addActionListener(e -> seviyeDegistir(new SarkiCanon(anaFrame)));
        this.add(canon);

        JButton serbest = creatSimpleButton(" Free Play Mode", new Color(34, 139, 34));
        serbest.setBounds(250, 510, 400, 50);
        serbest.addActionListener(e -> {
            anaFrame.getContentPane().removeAll();
            SerbestCalma panel = new SerbestCalma(anaFrame);
            anaFrame.add(panel);
            anaFrame.revalidate();
            anaFrame.repaint();
            panel.requestFocusInWindow();
        });
        this.add(serbest);

        JButton cikis = creatSimpleButton(" Exit", new Color(105, 105, 105));
        cikis.setBounds(250, 570, 400, 40);
        cikis.addActionListener(e -> System.exit(0));
        this.add(cikis);
    }
    
    private JButton creatSimpleButton(String text, Color baseColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(baseColor);
        button.setFocusPainted(false);
        button.setBorderPainted(true);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(baseColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(baseColor);
            }
        });
        
        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(new Color(25, 15, 45));
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(new Color(255, 255, 255, 50));
        for (int i = 0; i < 20; i++) {
            int x = (i * 47 + 123) % getWidth();
            int y = (i * 83 + 67) % getHeight();
            g.fillOval(x, y, 3, 3);
        }
    }

    private void seviyeDegistir(OyunPaneli seviye) {
        anaFrame.getContentPane().removeAll();
        anaFrame.add(seviye);
        anaFrame.revalidate();
        anaFrame.repaint();
        seviye.requestFocusInWindow();
    }
}