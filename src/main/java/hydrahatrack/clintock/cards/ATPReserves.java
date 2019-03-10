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

public class ATPReserves extends CustomCard {
    public static final String ID = "clintock:ATPReserves";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    private static final int BLOCK_PER_CARD_EXHAUSTED = 1;
    private static final int BLOCK_AMOUNT = 10;
    private static final int UPGRADE_PLUS_BLOCK_AMOUNT = 3;

    public ATPReserves() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL,
                AbstractCardEnum.CLINTOCK, CardRarity.COMMON, CardTarget.SELF);

        this.baseBlock = this.block = BLOCK_AMOUNT;
        this.baseMagicNumber = this.magicNumber = BLOCK_PER_CARD_EXHAUSTED;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (AbstractDungeon.player instanceof TheClintock) {
            int cardExhaustCount = (((TheClintock) AbstractDungeon.player).getCardExhaustCount());
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(
                    p, p, this.block + (cardExhaustCount * this.magicNumber)));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_PLUS_BLOCK_AMOUNT);
        }
    }
}
