package hydrahatrack.clintock.orbs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.actions.AdenineOrbPassiveAction;
import hydrahatrack.clintock.powers.KnockedOutPower;

public class AdenineOrb extends NucleobaseOrb {
    private static final String ORB_ID = "clintock:AdenineOrb";
    private static final OrbStrings orbStrings = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    private static final String NAME = orbStrings.NAME;
    private static final String[] DESCRIPTIONS = orbStrings.DESCRIPTION;
    public static final String LABEL = "A";
    private static final int BLOCK_AMOUNT = 2;

    public AdenineOrb() {
        this.ID = ORB_ID;
        this.label = LABEL;
        this.name = NAME;
        this.img = ImageMaster.loadImage(ClintockMod.getOrbImagePath(ORB_ID));
        this.basePassiveAmount = BLOCK_AMOUNT;
        this.passiveAmount = this.basePassiveAmount;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.applyFocus();
        this.description = (DESCRIPTIONS[0] + this.passiveAmount + DESCRIPTIONS[1]);
    }

    @Override
    public AbstractOrb makeCopy() {
        return new AdenineOrb();
    }

    @Override
    public void onEndOfTurn() {
        if (AbstractDungeon.player.hasPower(KnockedOutPower.POWER_ID)) {
            AbstractDungeon.player.getPower(KnockedOutPower.POWER_ID).flash();
        } else {
            AbstractDungeon.actionManager.addToBottom(new AdenineOrbPassiveAction(this));
        }
    }
}
