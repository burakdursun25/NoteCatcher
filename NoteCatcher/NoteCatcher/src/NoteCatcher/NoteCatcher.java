package NoteCatcher;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class NoteCatcher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Piano Öğrenme Oyunu");
            frame.setSize(900, 800);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.setLocationRelativeTo(null);
            
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    SesCal.kapat();
                }
            });
            
            JPanel yuklemePaneli = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.setColor(new Color(25, 15, 45));
                    g.fillRect(0, 0, getWidth(), getHeight());
                    
                    g.setColor(new Color(255, 215, 0));
                    g.setFont(new Font("Arial", Font.BOLD, 36));
                    String baslik = "🎹 Piano Öğrenme Oyunu";
                    FontMetrics fm = g.getFontMetrics();
                    g.drawString(baslik, (getWidth() - fm.stringWidth(baslik)) / 2, getHeight() / 2 - 50);
                    
                    g.setColor(new Color(200, 200, 255));
                    g.setFont(new Font("Arial", Font.PLAIN, 18));
                    String yukleme = "Ses sistemi hazırlanıyor...";
                    fm = g.getFontMetrics();
                    g.drawString(yukleme, (getWidth() - fm.stringWidth(yukleme)) / 2, getHeight() / 2 + 20);
                }
            };
            frame.add(yuklemePaneli);
            frame.setVisible(true);
            
            new Thread(() -> {
                int beklemeSuresi = 0;
                while (!SesCal.isHazir() && beklemeSuresi < 3000) {
                    try {
                        Thread.sleep(50);
                        beklemeSuresi += 50;
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                
                SwingUtilities.invokeLater(() -> {
                    frame.remove(yuklemePaneli);
                    AnaMenu menu = new AnaMenu(frame);
                    frame.add(menu);
                    frame.revalidate();
                    frame.repaint();
                });
            }, "MIDI-Bekleme").start();
        });
    }
}