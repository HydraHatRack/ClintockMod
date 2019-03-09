package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.powers.PoisonPower;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.enums.AbstractCardEnum;

public class DegradedPrimer extends CustomCard {
    public static final String ID = "clintock:DegradedPrimer";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    private static final int ATTACK_DMG = 8;
    private static final int POISON_AMOUNT = 2;
    private static final int UPGRADE_PLUS_ATTACK_DMG = 3;
    private static final int UPGRADE_PLUS_POISON_AMOUNT = 1;

    public DegradedPrimer() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, CardType.ATTACK,
                AbstractCardEnum.CLINTOCK, CardRarity.COMMON, CardTarget.ENEMY);

        this.baseDamage = this.damage = ATTACK_DMG;
        this.baseMagicNumber = this.magicNumber = POISON_AMOUNT;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                    new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
                            AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

        int numberOfOrbs = 0;
        for (int i = 0; i < p.orbs.size(); i++) {
            if (!(p.orbs.get(i) instanceof EmptyOrbSlot)) {
                numberOfOrbs++;
            }
        }

        if (numberOfOrbs > 0) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p,
                    new PoisonPower(m, p, numberOfOrbs * this.magicNumber),
                    numberOfOrbs * this.magicNumber, AbstractGameAction.AttackEffect.POISON));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_ATTACK_DMG);
            this.upgradeMagicNumber(UPGRADE_PLUS_POISON_AMOUNT);
        }
    }
}
