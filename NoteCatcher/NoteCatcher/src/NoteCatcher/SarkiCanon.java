package NoteCatcher;
import javax.swing.*;

public class SarkiCanon extends OyunPaneli {
    private final String[] NOTALAR = {
        "fa-s.wav", "mi-s.wav", "re-s.wav", "do-s.wav",
        "si-s.wav", "la-s.wav", "si-s.wav", "do-s.wav",
        "fa-s.wav", "mi-s.wav", "re-s.wav", "do-s.wav",
        "si-s.wav", "la-s.wav", "si-s.wav", "do-s.wav",
        "fa-s.wav", "sol-s.wav", "la-s.wav", "si-s.wav",
        "sol-s.wav", "la-s.wav", "sol-s.wav", "fa-s.wav",
        "mi-s.wav", "fa-s.wav", "mi-s.wav", "re-s.wav",
        "mi-s.wav", "fa-s.wav", "sol-s.wav", "la-s.wav",
        "fa-s.wav", "sol-s.wav", "fa-s.wav", "mi-s.wav",
        "re-s.wav", "mi-s.wav", "re-s.wav", "do-s.wav",
        "si-s.wav", "do-s.wav", "re-s.wav", "mi-s.wav",
        "do-s.wav", "re-s.wav", "do-s.wav", "si-s.wav",
        "la-s.wav", "si-s.wav", "do-s.wav", "re-s.wav",
        "si-s.wav", "do-s.wav", "si-s.wav", "la-s.wav",
        "sol-s.wav", "la-s.wav", "si-s.wav", "do-s.wav",
        "la-s.wav", "si-s.wav", "la-s.wav", "sol-s.wav",
        "fa-s.wav", "sol-s.wav", "la-s.wav", "si-s.wav",
        "sol-s.wav", "la-s.wav", "sol-s.wav", "fa-s.wav",
        "mi-s.wav", "fa-s.wav", "sol-s.wav", "la-s.wav",
        "fa-s.wav", "sol-s.wav", "fa-s.wav", "mi-s.wav",
        "re-s.wav", "mi-s.wav", "fa-s.wav", "sol-s.wav",
        "mi-s.wav", "fa-s.wav", "mi-s.wav", "re-s.wav",
        "do-s.wav", "re-s.wav", "mi-s.wav", "fa-s.wav",
        "re-s.wav", "mi-s.wav", "re-s.wav", "do-s.wav"
    };
    
    private final boolean[] UZUN_NOTALAR = {
        true, false, true, false,
        true, false, false, true,
        true, false, true, false,
        true, false, false, true,
        false, false, false, true,
        false, false, false, true,
        false, false, false, true,
        false, false, false, true,
        false, false, false, true,
        false, false, false, true,
        false, false, false, true,
        false, false, false, true,
        false, false, false, true,
        false, false, false, true,
        false, false, false, true,
        false, false, false, true,
        false, false, false, true,
        false, false, false, true,
        false, false, false, true,
        false, false, false, true,
        false, false, false, true,
        false, false, false, true,
        false, false, false, true,
        false, false, false, true
    };
    
    public SarkiCanon(JFrame frame) {
        super(frame);
        setSarkiAdi("Canon in D - Pachelbel");
        setHiz(9);
        notaEklemeBaslat(NOTALAR, UZUN_NOTALAR);
        zamanlayici.start();
    }
}
