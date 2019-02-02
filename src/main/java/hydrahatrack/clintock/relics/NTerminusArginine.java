package hydrahatrack.clintock.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.aminoacids.Arginine;
import hydrahatrack.clintock.powers.PeptideChainPower;

public class NTerminusArginine extends CustomRelic {
    public static final String ID = "clintock:NTerminusArginine";
    private static final Texture IMG = ImageMaster.loadImage(ClintockMod.getRelicImagePath(ID));
    private static final Texture OUTLINE = ImageMaster.loadImage(ClintockMod.getRelicOutlineImagePath(ID));

    public NTerminusArginine() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {
        PeptideChainPower power = new PeptideChainPower(AbstractDungeon.player);
        power.addAminoAcid(new Arginine());
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, power));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
