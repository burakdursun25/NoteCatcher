package NoteCatcher;
import javax.swing.*;

public class SarkiMary extends OyunPaneli {
    private final String[] NOTALAR = {
        "mi-s.wav", "re-s.wav", "do-s.wav", "re-s.wav", 
        "mi-s.wav", "mi-s.wav", "mi-s.wav",
        "re-s.wav", "re-s.wav", "re-s.wav",
        "mi-s.wav", "sol-s.wav", "sol-s.wav",
        "mi-s.wav", "re-s.wav", "do-s.wav", "re-s.wav", 
        "mi-s.wav", "mi-s.wav", "mi-s.wav",
        "mi-s.wav", "re-s.wav", "re-s.wav", 
        "mi-s.wav", "re-s.wav", "do-s.wav"
    };
    
    private final boolean[] UZUN_NOTALAR = {
        false, false, false, false, false, false, true, 
        false, false, true,                             
        false, false, true,                             
        false, false, false, false, false, false, true,  
        false, false, true,                              
        false, false, true                               
    };
    
    public SarkiMary(JFrame frame) {
        super(frame);
        setSarkiAdi("Mary Had a Little Lamb");
        setHiz(5);
        notaEklemeBaslat(NOTALAR, UZUN_NOTALAR);
        
        zamanlayici.start();
    }
}
