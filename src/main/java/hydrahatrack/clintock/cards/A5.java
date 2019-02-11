package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.characters.TheClintock;
import hydrahatrack.clintock.enums.AbstractCardEnum;

public class A5 extends CustomCard {
    public static final String ID = "clintock:A5";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 0;
    private static final int BASE_MAGIC_NUMBER = 0;
    private static final int BLOCK_AMOUNT = 1;
    private static final int UPGRADE_PLUS_BLOCK_AMOUNT = 1;

    public A5() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL,
                AbstractCardEnum.CLINTOCK_COLOR, CardRarity.COMMON, CardTarget.SELF);

        this.baseBlock = this.block = BLOCK_AMOUNT;
        this.baseMagicNumber = this.magicNumber = BASE_MAGIC_NUMBER;

        if (AbstractDungeon.player instanceof TheClintock) {
            this.baseMagicNumber = this.magicNumber = (((TheClintock) AbstractDungeon.player).getCardExhaustCount());
            this.upgradedMagicNumber = true;
            this.updateDescription();
        }
    }

    public void incrementCardExhaustCount() {
        this.upgradeMagicNumber(1);
        this.updateDescription();
    }

    private void updateDescription() {
        if (1 == this.magicNumber) {
            this.rawDescription = UPGRADE_DESCRIPTION;
        } else {
            this.rawDescription = DESCRIPTION;
        }
        initializeDescription();
    }

    @Override
    public void triggerWhenDrawn() {
        if (AbstractDungeon.player instanceof TheClintock) {
            this.baseMagicNumber = this.magicNumber = (((TheClintock) AbstractDungeon.player).getCardExhaustCount());
            this.upgradedMagicNumber = true;
            this.updateDescription();
        }
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new GainBlockAction(p, p, this.block * this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_PLUS_BLOCK_AMOUNT);
            this.updateDescription();
        }
    }
}
