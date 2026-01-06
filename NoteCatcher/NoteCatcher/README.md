# 🎹 Piano Öğrenme Oyunu

Java Swing ile geliştirilmiş interaktif bir piano öğrenme uygulaması. Düşen notalara basarak şarkı çalmayı öğrenin!

## 📋 Özellikler

- **5 Farklı Şarkı** - Kolaydan zora farklı zorluk seviyelerinde şarkılar
- **Serbest Çalma Modu** - İstediğiniz notaları özgürce çalın
- **Puan Sistemi** - Doğru notalara basarak puan kazanın
- **Rekor Takibi** - Her şarkı için en yüksek puanınız kaydedilir
- **Uzun Nota Desteği** - Bazı notaları basılı tutmanız gerekir
- **MIDI Ses Sistemi** - Gerçek zamanlı piano sesleri

## 🎵 Şarkılar

| Şarkı | Zorluk | Nota Sayısı | Hız |
|-------|--------|-------------|-----|
| Twinkle Twinkle Little Star | ⭐ Kolay | 42 | Yavaş |
| Mary Had a Little Lamb | ⭐ Kolay | 26 | Orta |
| Ode to Joy - Beethoven | ⭐⭐ Orta | 63 | Hızlı |
| Für Elise - Beethoven | ⭐⭐⭐ Zor | 68 | Çok Hızlı |
| Canon in D - Pachelbel | ⭐⭐⭐ Zor | 96 | En Hızlı |

## 🎮 Nasıl Oynanır

### Klavye Tuşları

**Beyaz Tuşlar (Ana Notalar):**
| Tuş | Nota |
|-----|------|
| A | Do |
| S | Re |
| D | Mi |
| F | Fa |
| J | Sol |
| K | La |
| L | Si |

**Siyah Tuşlar (Diyez Notalar - Serbest Çalma Modunda):**
| Tuş | Nota |
|-----|------|
| W | Do# |
| E | Re# |
| T | Fa# |
| Y | Sol# |
| U | La# |

### Oyun Kuralları

1. **Düşen Notaları Takip Edin** - Ekranın üstünden notalar düşer
2. **Doğru Tuşa Basın** - Nota altın çizgiye ulaştığında doğru tuşa basın
3. **Uzun Notalar** - Turuncu renkli "UZUN" yazan notalarda tuşu basılı tutun
4. **Puan Kazanın** - Doğru nota = +100 puan, Uzun nota = +200 puan
5. **Hata Yapmayın** - Yanlış veya kaçırılan nota = -50 puan

### Özel Tuşlar

- **ESC** - Ana menüye dön

## 🛠️ Kurulum

### Gereksinimler
- Java JDK 8 veya üzeri
- NetBeans IDE (önerilen) veya herhangi bir Java IDE

### Çalıştırma

1. Projeyi IDE'nizde açın
2. `pianooyun.java` dosyasını çalıştırın
3. Ana menüden bir şarkı seçin ve oynamaya başlayın!

### Manuel Derleme

```bash
cd src
javac pianooyun/*.java
java pianooyun.pianooyun
```

## 📁 Proje Yapısı

```
PianoOyun/
├── src/
│   └── pianooyun/
│       ├── pianooyun.java      # Ana uygulama giriş noktası
│       ├── AnaMenu.java        # Ana menü ekranı
│       ├── OyunPaneli.java     # Oyun mantığı ve çizim
│       ├── Not.java            # Nota sınıfı
│       ├── SesCal.java         # MIDI ses sistemi
│       ├── SerbestCalma.java   # Serbest çalma modu
│       ├── SarkiTwinkle.java   # Twinkle Twinkle şarkısı
│       ├── SarkiMary.java      # Mary Had a Little Lamb
│       ├── SarkiOde.java       # Ode to Joy
│       ├── SarkiFurElise.java  # Für Elise
│       └── SarkiCanon.java     # Canon in D
├── build.xml                   # Ant build dosyası
└── README.md                   # Bu dosya
```

## 🎨 Ekran Görüntüleri

### Ana Menü
- Şarkı seçimi
- Zorluk seviyeleri gösterimi
- Serbest çalma modu erişimi

### Oyun Ekranı
- 7 beyaz piano tuşu
- Düşen renkli notalar
- Puan ve rekor göstergesi
- Altın hedef çizgisi

## 🔧 Teknik Detaylar

- **Dil:** Java
- **GUI:** Swing
- **Ses:** Java MIDI Synthesizer
- **FPS:** 50 (20ms güncelleme)
- **Minimum Çözünürlük:** 900x800

## 👥 Geliştiriciler

- Hüseyin
- Burak

## 📄 Lisans

Bu proje eğitim amaçlı geliştirilmiştir.

---

🎹 *İyi eğlenceler ve bol şans!*
