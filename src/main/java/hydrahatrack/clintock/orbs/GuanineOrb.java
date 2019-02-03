package hydrahatrack.clintock.orbs;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
import hydrahatrack.clintock.ClintockMod;

public class GuanineOrb extends NucleobaseOrb {
    private static final String ORB_ID = "clintock:GuanineOrb";
    private static final OrbStrings orbStrings = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    private static final String NAME = orbStrings.NAME;
    private static final String[] DESCRIPTIONS = orbStrings.DESCRIPTION;
    private static final String LABEL = "G";
    private static final int ENERGY_AMOUNT = 1;

    public GuanineOrb() {
        this.ID = ORB_ID;
        this.label = LABEL;
        this.name = NAME;
        this.img = ImageMaster.loadImage(ClintockMod.getOrbImagePath(ORB_ID));
        this.basePassiveAmount = ENERGY_AMOUNT;
        this.passiveAmount = this.basePassiveAmount;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + this.passiveAmount + DESCRIPTIONS[1]);
    }

    @Override
    public AbstractOrb makeCopy() {
        return new GuanineOrb();
    }

    @Override
    public void onStartOfTurn() {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(
                new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.PLASMA), 0.1F));
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.passiveAmount));
    }
}
