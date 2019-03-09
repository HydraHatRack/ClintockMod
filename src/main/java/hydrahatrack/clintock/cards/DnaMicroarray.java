package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.enums.AbstractCardEnum;

public class DnaMicroarray extends CustomCard {
    public static final String ID = "clintock:DnaMicroarray";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 3;
    private static final int UPGRADED_COST = 2;

    public DnaMicroarray() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL,
                AbstractCardEnum.CLINTOCK, CardRarity.RARE, CardTarget.NONE);

        this.exhaust = true;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Adenine(), 1));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Cytosine(), 1));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Guanine(), 1));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Thymine(), 1));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(UPGRADED_COST);
        }
    }
}