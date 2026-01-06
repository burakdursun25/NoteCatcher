package NoteCatcher;
import javax.swing.*;

public class SarkiTwinkle extends OyunPaneli {

    private final String[] NOTALAR = {
        "do-s.wav", "do-s.wav", "sol-s.wav", "sol-s.wav", 
        "la-s.wav", "la-s.wav", "sol-s.wav",
        "fa-s.wav", "fa-s.wav", "mi-s.wav", "mi-s.wav", 
        "re-s.wav", "re-s.wav", "do-s.wav",
        "sol-s.wav", "sol-s.wav", "fa-s.wav", "fa-s.wav", 
        "mi-s.wav", "mi-s.wav", "re-s.wav",
        "sol-s.wav", "sol-s.wav", "fa-s.wav", "fa-s.wav", 
        "mi-s.wav", "mi-s.wav", "re-s.wav",
        "do-s.wav", "do-s.wav", "sol-s.wav", "sol-s.wav", 
        "la-s.wav", "la-s.wav", "sol-s.wav",
        "fa-s.wav", "fa-s.wav", "mi-s.wav", "mi-s.wav", 
        "re-s.wav", "re-s.wav", "do-s.wav"
    };
    
    private final boolean[] UZUN_NOTALAR = {
        false, false, false, false, false, false, true, 
        false, false, false, false, false, false, true, 
        false, false, false, false, false, false, true, 
        false, false, false, false, false, false, true, 
        false, false, false, false, false, false, true,
        false, false, false, false, false, false, true
    };
    
    public SarkiTwinkle(JFrame frame) {
        super(frame);
        setSarkiAdi("Twinkle Twinkle Little Star");
        setHiz(3);
        
       
        notaEklemeBaslat(NOTALAR, UZUN_NOTALAR);
        
        zamanlayici.start();
    }
}
