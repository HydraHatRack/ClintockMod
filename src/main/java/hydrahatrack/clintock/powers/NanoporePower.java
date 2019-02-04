package hydrahatrack.clintock.powers;

import com.badlogic.gdx.graphics.Texture;
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
    private static final Texture IMG = ImageMaster.loadImage(ClintockMod.getPowerImagePath(POWER_ID));

    public NanoporePower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = IMG;
        this.updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        Random random = new Random();
        AbstractCard card;
        switch (random.nextInt(4)) {
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
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
