package hydrahatrack.clintock.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import hydrahatrack.clintock.ClintockMod;

public class KnockedOutPower extends AbstractPower {
    public static final String POWER_ID = "clintock:KnockedOutPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public KnockedOutPower(final AbstractCreature owner) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.img = ImageMaster.loadImage(ClintockMod.getPowerImagePath(POWER_ID));
        this.updateDescription();
    }

    @Override
    public void atEndOfTurn(final boolean isPlayer) {
        if (isPlayer) {
            AbstractDungeon.actionManager.addToBottom(
                    new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }
    }

    @Override
    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("ENTANGLE", 0.05F);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
        this.type = PowerType.DEBUFF;
    }
}