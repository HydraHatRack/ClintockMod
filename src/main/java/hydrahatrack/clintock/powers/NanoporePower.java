package hydrahatrack.clintock.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.cards.Adenine;
import hydrahatrack.clintock.cards.Cytosine;
import hydrahatrack.clintock.cards.Guanine;
import hydrahatrack.clintock.cards.Thymine;

import java.util.Random;

public class NanoporePower extends AbstractPower {
    private static final String POWER_ID = "clintock:NanoporePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public NanoporePower(final AbstractCreature owner, final int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = ImageMaster.loadImage(ClintockMod.getPowerImagePath(POWER_ID));
        this.updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        AbstractCard card;
        switch (new Random().nextInt(4)) {
            case 0:
                card = new Adenine();
                break;
            case 1:
                card = new Cytosine();
                break;
            case 2:
                card = new Guanine();
                break;
            case 3:
            default:
                card = new Thymine();
                break;
        }
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(card, this.amount));
    }

    @Override
    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("DARKLING_REGROW_2", 0.05F);
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
