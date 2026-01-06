package NoteCatcher;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.sound.midi.*;
import javax.sound.sampled.*;

public class SesCal {
    private static final String SES_DIZINI = ""; 
    private static Synthesizer synthesizer;
    private static MidiChannel[] channels;
    private static final Map<String, Integer> NOTA_MIDI_MAP = new HashMap<>();
    private static boolean hazirMi = false;
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
    
    static {
        NOTA_MIDI_MAP.put("do-s.wav", 60);
        NOTA_MIDI_MAP.put("re-s.wav", 62);
        NOTA_MIDI_MAP.put("mi-s.wav", 64);
        NOTA_MIDI_MAP.put("fa-s.wav", 65);
        NOTA_MIDI_MAP.put("sol-s.wav", 67);
        NOTA_MIDI_MAP.put("la-s.wav", 69);
        NOTA_MIDI_MAP.put("si-s.wav", 71);
        NOTA_MIDI_MAP.put("ust_do-s.wav", 72);
        NOTA_MIDI_MAP.put("do_diez-s.wav", 61);
        NOTA_MIDI_MAP.put("re_diez-s.wav", 63);
        NOTA_MIDI_MAP.put("fa_diez-s.wav", 66);
        NOTA_MIDI_MAP.put("sol_diez-s.wav", 68);
        NOTA_MIDI_MAP.put("la_diez-s.wav", 70);
        
        new Thread(() -> {
            try {
                synthesizer = MidiSystem.getSynthesizer();
                synthesizer.open();
                
                if (synthesizer.getLatency() > 0) {
                    System.out.println("MIDI Latency: " + synthesizer.getLatency() + " microseconds");
                }
                
                channels = synthesizer.getChannels();
                channels[0].programChange(0);
                
                channels[0].noteOn(60, 0);
                Thread.sleep(10);
                channels[0].noteOff(60);
                
                for (int i = 0; i < 12; i++) {
                    channels[0].noteOn(60 + i, 1);
                }
                Thread.sleep(5);
                for (int i = 0; i < 12; i++) {
                    channels[0].noteOff(60 + i);
                }
                
                hazirMi = true;
                System.out.println("MIDI Synthesizer hazır!");
                
            } catch (MidiUnavailableException | InterruptedException e) {
                e.printStackTrace();
                System.err.println("MIDI synthesizer başlatılamadı!");
            }
        }, "MIDI-Warmup").start();
    }
    
    public static boolean isHazir() {
        return hazirMi;
    }

    public static void sesCal(String dosyaAdi) {
        sesCal(dosyaAdi, false);
    }

    public static void sesCal(String dosyaAdi, boolean loop) {
        sesCal(dosyaAdi, loop, null);
    }

    public static Integer notaBaslat(String dosyaAdi) {
        Integer midiNota = NOTA_MIDI_MAP.get(dosyaAdi);
        if (midiNota != null && channels != null && channels.length > 0) {
            channels[0].noteOn(midiNota, 100);
            return midiNota;
        }
        return null;
    }
    
    public static void notaDurdur(Integer midiNota) {
        if (midiNota != null && channels != null && channels.length > 0) {
            channels[0].noteOff(midiNota);
        }
    }
    
    public static void kisaNotaCal(String dosyaAdi) {
        Integer midiNota = NOTA_MIDI_MAP.get(dosyaAdi);
        if (midiNota != null && channels != null && channels.length > 0) {
            channels[0].noteOn(midiNota, 100);
            scheduler.schedule(() -> {
                if (channels != null && channels.length > 0) {
                    channels[0].noteOff(midiNota);
                }
            }, 300, TimeUnit.MILLISECONDS);
        }
    }

    public static Clip sesBaslat(String dosyaAdi, boolean loop) {
        try {
            File sesDosyasi = new File(SES_DIZINI + dosyaAdi);
            if (!sesDosyasi.exists()) {
                System.err.println("HATA: Ses dosyası bulunamadı: " + sesDosyasi.getAbsolutePath());
                return null;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(sesDosyasi);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.start();
                clip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                });
            }

            clip.start();
            return clip;

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            System.err.println("Ses çalınırken bir hata oluştu: " + dosyaAdi);
            return null;
        }
    }

    public static void sesCal(String dosyaAdi, boolean loop, Clip clipToStop) {
        new Thread(() -> {
            try {
                File sesDosyasi = new File(SES_DIZINI + dosyaAdi);
                if (!sesDosyasi.exists()) {
                    System.err.println("HATA: Ses dosyası bulunamadı: " + sesDosyasi.getAbsolutePath());
                    return;
                }

                AudioInputStream audioStream = AudioSystem.getAudioInputStream(sesDosyasi);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);

                if (loop) {
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                }

                clip.start();

                if (!loop) {
                    clip.addLineListener(event -> {
                        if (event.getType() == LineEvent.Type.STOP) {
                            clip.close();
                        }
                    });
                }

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
                System.err.println("Ses çalınırken bir hata oluştu: " + dosyaAdi);
            }
        }).start();
    }
    
    public static void kapat() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(100, TimeUnit.MILLISECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
        }
        
        if (synthesizer != null && synthesizer.isOpen()) {
            synthesizer.close();
        }
    }
}