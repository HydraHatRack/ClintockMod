package hydrahatrack.clintock.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.powers.PeptideChainPower;

public class MG53 extends CustomRelic {
    public static final String ID = "clintock:MG53";
    private static final Texture IMG = ImageMaster.loadImage(ClintockMod.getRelicImagePath(ID));
    private static final Texture OUTLINE = ImageMaster.loadImage(ClintockMod.getRelicOutlineImagePath(ID));

    public MG53() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public void onVictory() {
        PeptideChainPower p = (PeptideChainPower) AbstractDungeon.player.getPower(PeptideChainPower.POWER_ID);
        if (null != p && p.)
        flash();
        AbstractDungeon.actionManager.addToBottom(new MG53Action());
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
