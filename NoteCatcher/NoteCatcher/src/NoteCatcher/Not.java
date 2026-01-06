package NoteCatcher;

public class Not {
    public int satir;
    public int y;
    public String notaAdi;
    public boolean calindiMi = false;
    public boolean hataliMi = false;
    public int uzunluk = 40; 
    public boolean uzunNotaMi = false;
    public long baslamaZamani = 0; 
    public boolean tuşaBasildi = false;
    public Integer midiNotaNo = null;
    public float notaSuresi = 1.0f;
    public boolean siyahTusMu = false;
    
    public Not(int satir, int baslangicY, String notaAdi) {
        this.satir = satir;
        this.y = baslangicY;
        this.notaAdi = notaAdi;
        this.siyahTusMu = false;
    }
    
    public Not(int satir, int baslangicY, String notaAdi, boolean uzunNotaMi) {
        this(satir, baslangicY, notaAdi, uzunNotaMi, false);
    }
    
    public Not(int satir, int baslangicY, String notaAdi, boolean uzunNotaMi, boolean siyahTusMu) {
        this.satir = satir;
        this.y = baslangicY;
        this.notaAdi = notaAdi;
        this.uzunNotaMi = uzunNotaMi;
        this.siyahTusMu = siyahTusMu;
        if (uzunNotaMi) {
            this.uzunluk = 120;
            this.notaSuresi = 2.0f;
        } else {
            this.notaSuresi = 1.0f;
        }
    }
    
    public Not(int satir, int baslangicY, String notaAdi, float notaSuresi) {
        this.satir = satir;
        this.y = baslangicY;
        this.notaAdi = notaAdi;
        this.notaSuresi = notaSuresi;
        this.uzunNotaMi = notaSuresi > 1.5f;
        this.siyahTusMu = false;
        if (uzunNotaMi) {
            this.uzunluk = (int)(40 + (notaSuresi - 1) * 80);
        }
    }
}