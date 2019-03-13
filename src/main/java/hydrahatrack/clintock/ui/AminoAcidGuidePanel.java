package hydrahatrack.clintock.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;

class AminoAcidGuidePanel {
    private Texture panel;
    private boolean loaded = false;

    AminoAcidGuidePanel() {}

    void render(final SpriteBatch sb) {
        if (!loaded) {
            panel = ImageMaster.loadImage("img/ui/panel.png");
            loaded = true;
        }
        sb.draw(
                panel,
                750 * Settings.scale,
                550 * Settings.scale,
                1166 * Settings.scale,
                400 * Settings.scale
        );
    }
}
