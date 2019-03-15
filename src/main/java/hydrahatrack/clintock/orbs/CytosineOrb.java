package hydrahatrack.clintock.orbs;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.actions.CytosineOrbPassiveAction;
import hydrahatrack.clintock.powers.KnockedOutPower;

public class CytosineOrb extends NucleobaseOrb {
    private static final String ORB_ID = "clintock:CytosineOrb";
    private static final OrbStrings orbStrings = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    private static final String NAME = orbStrings.NAME;
    private static final String[] DESCRIPTIONS = orbStrings.DESCRIPTION;
    public static final String LABEL = "C";
    private static final int DAMAGE_AMOUNT = 3;

    public CytosineOrb() {
        this.ID = ORB_ID;
        this.label = LABEL;
        this.name = NAME;
        this.img = ImageMaster.loadImage(ClintockMod.getOrbImagePath(ORB_ID));
        this.basePassiveAmount = DAMAGE_AMOUNT;
        this.passiveAmount = this.basePassiveAmount;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.applyFocus();
        this.description = DESCRIPTIONS[0] + this.passiveAmount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractOrb makeCopy() {
        return new CytosineOrb();
    }

    @Override
    public void onEndOfTurn() {
        if (AbstractDungeon.player.hasPower(KnockedOutPower.POWER_ID)) {
            AbstractDungeon.player.getPower(KnockedOutPower.POWER_ID).flash();
        } else {
            AbstractDungeon.actionManager.addToBottom(new CytosineOrbPassiveAction(new DamageInfo(
                    AbstractDungeon.player, this.passiveAmount, DamageInfo.DamageType.THORNS), this));
        }
    }
}
