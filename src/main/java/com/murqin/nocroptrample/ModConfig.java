// src/main/java/com/murqin/nocroptrample/ModConfig.java

package com.murqin.nocroptrample;

import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class ModConfig {

    private final Path configFile;
    private final Properties properties = new Properties();

    private boolean disablePlayerTrampling = true;
    private boolean disableMobTrampling = true;

    public ModConfig() {
        this.configFile = FabricLoader.getInstance().getConfigDir().resolve("nocroptrample.properties");
        load();
    }

    public boolean isPlayerTramplingDisabled() {
        return disablePlayerTrampling;
    }

    public boolean isMobTramplingDisabled() {
        return disableMobTrampling;
    }

    // YENİ EKLENEN METOTLAR BURADA BAŞLIYOR
    // ----------------------------------------

    /**
     * Oyuncu ezmesi ayarını değiştirir.
     * @param value Yeni ayar değeri (true veya false)
     */
    public void setPlayerTramplingDisabled(boolean value) {
        this.disablePlayerTrampling = value;
        // Değeri properties nesnesine de yazıyoruz ki save() çağrıldığında kaydedilsin.
        properties.setProperty("disable-player-trampling", String.valueOf(value));
    }

    /**
     * Mob ezmesi ayarını değiştirir.
     * @param value Yeni ayar değeri (true veya false)
     */
    public void setMobTramplingDisabled(boolean value) {
        this.disableMobTrampling = value;
        properties.setProperty("disable-mob-trampling", String.valueOf(value));
    }

    // --------------------------------------
    // YENİ EKLENEN METOTLAR BURADA BİTİYOR


    public void load() {
        if (Files.exists(configFile)) {
            try {
                properties.load(Files.newInputStream(configFile));
            } catch (IOException e) {
                System.err.println("NoCropTrample config dosyası okunamadı!");
                e.printStackTrace();
            }
        }

        this.disablePlayerTrampling = Boolean.parseBoolean(properties.getProperty("disable-player-trampling", "true"));
        this.disableMobTrampling = Boolean.parseBoolean(properties.getProperty("disable-mob-trampling", "true"));

        // Ayarları setProperty ile güncellediğimiz için save() çağrısı burada da kalmalı
        // ki dosya ilk oluşturulduğunda varsayılan değerler yazılsın.
        save();
    }

    public void save() {
        try {
            properties.store(Files.newOutputStream(configFile), "NoCropTrample Configuration");
        } catch (IOException e) {
            System.err.println("NoCropTrample config dosyası kaydedilemedi!");
            e.printStackTrace();
        }
    }
}
