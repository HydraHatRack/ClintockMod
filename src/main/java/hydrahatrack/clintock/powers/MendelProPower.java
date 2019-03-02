package hydrahatrack.clintock.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import hydrahatrack.clintock.ClintockMod;

public class MendelProPower extends AbstractPower {
    public static final String POWER_ID = "clintock:MendelProPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MendelProPower(final AbstractCreature owner, final int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = ImageMaster.loadImage(ClintockMod.getPowerImagePath(POWER_ID));
        this.updateDescription();
    }

    @Override
    public void onChannel(final AbstractOrb orb) {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, amount));
    }

    @Override
    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_FOCUS", 0.05F);
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
        } else {
            this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2]);
        }
    }
}
