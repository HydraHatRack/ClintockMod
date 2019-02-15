package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.enums.AbstractCardEnum;
import hydrahatrack.clintock.powers.PhosphatePower;
import hydrahatrack.clintock.powers.SugarPower;

public class A8 extends CustomCard {
    public static final String ID = "clintock:A8";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    public A8() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL,
                AbstractCardEnum.CLINTOCK_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);

        this.exhaust = true;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        int previousSugarAmount = 0;
        int previousPhosphateAmount = 0;
        if (null != p.getPower(SugarPower.POWER_ID)) {
            previousSugarAmount = p.getPower(SugarPower.POWER_ID).amount;
        }
        if (null != p.getPower(PhosphatePower.POWER_ID)) {
            previousPhosphateAmount = p.getPower(PhosphatePower.POWER_ID).amount;
        }

        int sugarAmount = previousPhosphateAmount - previousSugarAmount;
        int phosphateAmount = previousSugarAmount - previousPhosphateAmount;
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new SugarPower(p, sugarAmount), sugarAmount));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new PhosphatePower(p, phosphateAmount), phosphateAmount));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(UPGRADED_COST);
        }
    }
}
