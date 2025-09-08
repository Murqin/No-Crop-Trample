// src/main/java/com/murqin/nocroptrample/ModMenuIntegration.java
package com.murqin.nocroptrample;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        // Mod Menu'ye, bu modun ayar ekranının bizim oluşturduğumuz
        // ModConfigScreen sınıfından yeni bir örnek olduğunu söylüyoruz.
        return parent -> new ModConfigScreen(parent);
    }
}
