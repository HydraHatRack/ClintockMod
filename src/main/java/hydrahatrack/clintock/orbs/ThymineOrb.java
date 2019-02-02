package hydrahatrack.clintock.orbs;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.actions.ThymineOrbPassiveAction;

public class ThymineOrb extends NucleobaseOrb {
    private static final String ORB_ID = "clintock:ThymineOrb";
    private static final OrbStrings orbStrings = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    private static final String NAME = orbStrings.NAME;
    private static final String[] DESCRIPTIONS = orbStrings.DESCRIPTION;
    private static final String LABEL = "T";
    private static final int DAMAGE_AMOUNT = 2;

    public ThymineOrb() {
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
        return new ThymineOrb();
    }

    @Override
    public void onEndOfTurn() {
        AbstractDungeon.actionManager.addToBottom(new ThymineOrbPassiveAction(
                new DamageInfo(AbstractDungeon.player, this.passiveAmount, DamageInfo.DamageType.THORNS), this));
    }
}
