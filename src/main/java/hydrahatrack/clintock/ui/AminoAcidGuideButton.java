package hydrahatrack.clintock.ui;

import basemod.TopPanelItem;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class AminoAcidGuideButton extends TopPanelItem {
    public static boolean enabled;
    private boolean needsNoise = true;

    public static Texture IMG = ImageMaster.loadImage("img/ui/AminoAcidHelperIcon.png");
    public static final String ID = "cvc_AminoAcidHelperButton";

    private final AminoAcidGuide aminoAcidGuide;

    public AminoAcidGuideButton(final AminoAcidGuide aminoAcidGuide) {
        super(IMG, ID);
        this.aminoAcidGuide = aminoAcidGuide;
    }

    @Override
    protected void onHover() {
        super.onHover();
        if (needsNoise) {
            CardCrawlGame.sound.play("UI_HOVER", 0.05F);
            needsNoise = false;
        }
    }

    @Override
    protected void onUnhover() {
        super.onUnhover();
        needsNoise = true;
    }

    @Override
    protected void onClick() {
        if (aminoAcidGuide.isShowing()) {
            aminoAcidGuide.hide();
        } else {
            aminoAcidGuide.show();
        }
    }
}
