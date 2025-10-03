// src/main/java/com/murqin/nocroptrample/ModConfigScreen.java
package murqin.nocroptrample;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;


public class ModConfigScreen extends Screen {

    // Bir önceki ekranı saklamak için bir değişken. "Bitti" butonuna basınca geri döneceğiz.
    private final Screen parent;

    public ModConfigScreen(Screen parent) {
        // "NoCropTrample Ayarları" başlığını oluşturuyoruz.
        super(Text.literal("NoCropTrample Settings"));
        this.parent = parent;
    }

    // Bu metot, ekran ilk açıldığında çalışır ve butonlarımızı oluşturur.
    @Override
    protected void init() {
        super.init();

        // --- Oyuncu Ezmesi Butonu ---
        this.addDrawableChild(ButtonWidget.builder(
                // Butonun üzerinde yazacak metni, mevcut ayara göre dinamik olarak oluşturuyoruz.
                getPlayerTrampleText(),
                // Butona tıklandığında ne olacağını belirliyoruz.
                button -> {
                    // Ayarı tersine çevir (true ise false, false ise true yap).
                    NoCropTrample.CONFIG.setPlayerTramplingDisabled(!NoCropTrample.CONFIG.isPlayerTramplingDisabled());
                    // Değişikliği anında dosyaya kaydet.
                    NoCropTrample.CONFIG.save();
                    // Butonun metnini yeni duruma göre güncelle.
                    button.setMessage(getPlayerTrampleText());
                })
                // Butonun konumunu ve boyutunu ayarlıyoruz.
                .dimensions(this.width / 2 - 100, 50, 200, 20)
                .build());

        // --- Mob Ezmesi Butonu ---
        this.addDrawableChild(ButtonWidget.builder(
                getMobTrampleText(),
                button -> {
                    NoCropTrample.CONFIG.setMobTramplingDisabled(!NoCropTrample.CONFIG.isMobTramplingDisabled());
                    NoCropTrample.CONFIG.save();
                    button.setMessage(getMobTrampleText());
                })
                .dimensions(this.width / 2 - 100, 80, 200, 20)
                .build());

        // --- Bitti Butonu ---
        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Bitti"),
                // Tıklandığında...
                button -> {
                    // Bu ekranı kapat ve bir önceki ekrana (parent) geri dön.
                    this.client.setScreen(this.parent);
                })
                .dimensions(this.width / 2 - 100, this.height - 40, 200, 20)
                .build());
    }

    // Ekranın başlığını ve arka planını çizmek için bu metodu kullanıyoruz.
@Override
public void render(DrawContext context, int mouseX, int mouseY, float delta) {
    super.render(context, mouseX, mouseY, delta); // Bu satır zaten arka planı çiziyor, bu yüzden yeterli.
    context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
}

    // Buton metinlerini dinamik olarak oluşturan yardımcı metotlar
    private Text getPlayerTrampleText() {
        return Text.literal("Player Trample: " + (NoCropTrample.CONFIG.isPlayerTramplingDisabled() ? "§aOff" : "§cOn"));
    }

    private Text getMobTrampleText() {
        return Text.literal("Mob Trample: " + (NoCropTrample.CONFIG.isMobTramplingDisabled() ? "§aOff" : "§cOn"));
    }
}
