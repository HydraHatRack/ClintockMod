package hydrahatrack.clintock.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.aminoacids.Methionine;
import hydrahatrack.clintock.powers.PeptideChainPower;

public class StartCodon extends CustomRelic {
    public static final String ID = "clintock:StartCodon";
    private static final Texture IMG = ImageMaster.loadImage(ClintockMod.getRelicImagePath(ID));
    private static final Texture OUTLINE = ImageMaster.loadImage(ClintockMod.getRelicOutlineImagePath(ID));

    public StartCodon() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStartPreDraw() {
        flash();
        PeptideChainPower power = new PeptideChainPower(AbstractDungeon.player);
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, power));
        power.bindAminoAcid(new Methionine());
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
