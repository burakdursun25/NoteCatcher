package NoteCatcher;
import javax.swing.*;

public class SarkiFurElise extends OyunPaneli {
    private final String[] NOTALAR = {
        "mi-s.wav", "re-s.wav", "mi-s.wav", "re-s.wav", "mi-s.wav",
        "si-s.wav", "re-s.wav", "do-s.wav", "la-s.wav",
        "do-s.wav", "mi-s.wav", "la-s.wav", "si-s.wav",
        "mi-s.wav", "sol-s.wav", "si-s.wav", "do-s.wav",
        "mi-s.wav", "re-s.wav", "mi-s.wav", "re-s.wav", "mi-s.wav",
        "si-s.wav", "re-s.wav", "do-s.wav", "la-s.wav",
        "do-s.wav", "mi-s.wav", "la-s.wav", "si-s.wav",
        "mi-s.wav", "do-s.wav", "si-s.wav", "la-s.wav",
        "si-s.wav", "do-s.wav", "re-s.wav", "mi-s.wav",
        "sol-s.wav", "fa-s.wav", "mi-s.wav", "re-s.wav",
        "fa-s.wav", "mi-s.wav", "re-s.wav", "do-s.wav",
        "mi-s.wav", "re-s.wav", "do-s.wav", "si-s.wav",
        "mi-s.wav", "re-s.wav", "mi-s.wav", "re-s.wav", "mi-s.wav",
        "si-s.wav", "re-s.wav", "do-s.wav", "la-s.wav",
        "do-s.wav", "mi-s.wav", "la-s.wav", "si-s.wav",
        "mi-s.wav", "do-s.wav", "si-s.wav", "la-s.wav"
    };
    
    private final boolean[] UZUN_NOTALAR = {
        false, false, false, false, false,
        false, false, false, true,
        false, false, false, true,
        false, false, false, true,
        false, false, false, false, false,
        false, false, false, true,
        false, false, false, true,
        false, false, false, true,
        false, false, false, true,
        false, false, false, true,
        false, false, false, true,
        false, false, false, true,
        false, false, false, false, false,
        false, false, false, true,
        false, false, false, true,
        false, false, false, true
    };
    
    public SarkiFurElise(JFrame frame) {
        super(frame);
        setSarkiAdi("Fur Elise - Beethoven");
        setHiz(7);
        notaEklemeBaslat(NOTALAR, UZUN_NOTALAR);
        zamanlayici.start();
    }
}
