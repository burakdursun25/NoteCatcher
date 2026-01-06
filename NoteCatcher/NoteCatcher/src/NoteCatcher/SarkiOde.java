package NoteCatcher;
import javax.swing.*;

public class SarkiOde extends OyunPaneli {
    private final String[] NOTALAR = {
        "mi-s.wav", "mi-s.wav", "fa-s.wav", "sol-s.wav", 
        "sol-s.wav", "fa-s.wav", "mi-s.wav", "re-s.wav",
        "do-s.wav", "do-s.wav", "re-s.wav", "mi-s.wav", 
        "mi-s.wav", "re-s.wav", "re-s.wav",
        "mi-s.wav", "mi-s.wav", "fa-s.wav", "sol-s.wav", 
        "sol-s.wav", "fa-s.wav", "mi-s.wav", "re-s.wav",
        "do-s.wav", "do-s.wav", "re-s.wav", "mi-s.wav", 
        "re-s.wav", "do-s.wav", "do-s.wav",
        "re-s.wav", "re-s.wav", "mi-s.wav", "do-s.wav", 
        "re-s.wav", "mi-s.wav", "fa-s.wav", "mi-s.wav", "do-s.wav",
        "re-s.wav", "mi-s.wav", "fa-s.wav", "mi-s.wav", "re-s.wav",
        "do-s.wav", "re-s.wav", "sol-s.wav",
        "mi-s.wav", "mi-s.wav", "fa-s.wav", "sol-s.wav", 
        "sol-s.wav", "fa-s.wav", "mi-s.wav", "re-s.wav",
        "do-s.wav", "do-s.wav", "re-s.wav", "mi-s.wav", 
        "re-s.wav", "do-s.wav", "do-s.wav"
    };
    
    private final boolean[] UZUN_NOTALAR = {
        false, false, false, false, false, false, false, false,
        false, false, false, false, true, false, true,   
        false, false, false, false, false, false, false, false,
        false, false, false, false, false, false, true, 
        false, false, false, false, false, false, false, false, false,
        false, false, false, false, false,
        false, false, true,                            
        false, false, false, false, false, false, false, false,
        false, false, false, false, false, false, true   
    };
    
    public SarkiOde(JFrame frame) {
        super(frame);
        setSarkiAdi("Ode to Joy");
        setHiz(6);
        
        notaEklemeBaslat(NOTALAR, UZUN_NOTALAR);
        
        zamanlayici.start();
    }
}
