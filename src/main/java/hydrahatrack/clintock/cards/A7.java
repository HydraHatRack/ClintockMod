package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.enums.AbstractCardEnum;
import hydrahatrack.clintock.powers.SugarPower;

public class A7 extends CustomCard {
    public static final String ID = "clintock:A7";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;

    public A7() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, CardType.ATTACK,
                AbstractCardEnum.CLINTOCK_COLOR, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    public boolean canUse(final AbstractPlayer p, final AbstractMonster m) {
        if (null == p.getPower(SugarPower.POWER_ID)) {
            this.cantUseMessage = ClintockMod.NEEDS_MORE_SUGAR;
            return false;
        }
        return true;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        int amount = (p.getPower(SugarPower.POWER_ID).amount + 1) / 2;
        AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, SugarPower.POWER_ID, amount));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
                m, p, new PoisonPower(m, p, amount), amount, AbstractGameAction.AttackEffect.POISON));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(UPGRADED_COST);
        }
    }
}

