package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.enums.AbstractCardEnum;

import java.util.Random;

public class MonomerSynthesis extends CustomCard {
    public static final String ID = "clintock:MonomerSynthesis";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;
    private static final int BASE_MAGIC_NUMBER = 1;

    public MonomerSynthesis() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL,
                AbstractCardEnum.CLINTOCK_COLOR, CardRarity.BASIC, CardTarget.NONE);

        this.baseMagicNumber = this.magicNumber = BASE_MAGIC_NUMBER;
    }

    public void use(AbstractPlayer player, AbstractMonster monster) {
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
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(card, this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
        }
    }
}
