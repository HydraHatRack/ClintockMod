package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.enums.AbstractCardEnum;

public class Irradiate extends CustomCard {
    public static final String ID = "clintock:Irradiate";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    private static final int ATTACK_DMG = 18;
    private static final int WEAK_AMOUNT = 2;
    private static final int UPGRADE_PLUS_ATTACK_DMG = 4;
    private static final int UPGRADE_PLUS_WEAK_AMOUNT = 1;

    public Irradiate() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, CardType.ATTACK,
                AbstractCardEnum.CLINTOCK, CardRarity.UNCOMMON, CardTarget.ENEMY);

        this.baseDamage = this.damage = ATTACK_DMG;
        this.baseMagicNumber = this.magicNumber = WEAK_AMOUNT;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(
                m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_HEAVY));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false),
                        this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Deletion(), 1));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_ATTACK_DMG);
            this.upgradeMagicNumber(UPGRADE_PLUS_WEAK_AMOUNT);
        }
    }
}
