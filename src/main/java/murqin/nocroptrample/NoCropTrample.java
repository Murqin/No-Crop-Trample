// src/main/java/com/example/nocroptrample/NoCropTrample.java
package murqin.nocroptrample;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoCropTrample implements ModInitializer {

    // Mod ID'miz
    public static final String MOD_ID = "nocroptrample";
    // Konsola log yazmak için logger
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    // Config nesnesine her yerden erişebilmek için statik bir referans
    public static ModConfig CONFIG;

    @Override
    public void onInitialize() {
        LOGGER.info("NoCropTrample modu yükleniyor!");
        // Mod ilk yüklendiğinde config'i oluştur ve yükle
        CONFIG = new ModConfig();
    }
}
