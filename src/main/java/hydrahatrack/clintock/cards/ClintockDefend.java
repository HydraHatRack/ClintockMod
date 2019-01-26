package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.enums.AbstractCardEnum;

public class ClintockDefend extends CustomCard {
    public static final String ID = "clintock:ClintockDefend";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final int COST = 1;
    private static final int BLOCK_AMOUNT = 5;
    private static final int UPGRADE_PLUS_BLOCK_AMOUNT = 3;

    public ClintockDefend() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
                AbstractCardEnum.CLINTOCK_COLOR, CardRarity.BASIC, AbstractCard.CardTarget.SELF);

        this.baseBlock = this.block = BLOCK_AMOUNT;

        this.tags.add(BaseModCardTags.BASIC_DEFEND);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_PLUS_BLOCK_AMOUNT);
        }
    }
}
