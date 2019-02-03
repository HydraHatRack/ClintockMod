package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.actions.BindAction;
import hydrahatrack.clintock.enums.AbstractCardEnum;
import hydrahatrack.clintock.orbs.CytosineOrb;
import hydrahatrack.clintock.powers.PhosphatePower;

public class Deoxycytidine extends CustomCard {
    public static final String ID = "clintock:Deoxycytidine";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 6;
    private static final int UPGRADE_PLUS_ATTACK_DMG = 3;

    public Deoxycytidine() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
                AbstractCardEnum.CLINTOCK_COLOR, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);

        this.baseDamage = this.damage = ATTACK_DMG;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(
                m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        if (null == p.getPower(PhosphatePower.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(
                    new TalkAction(true, "I need more phosphate!", 2.0F, 2.0F));
        } else {
            AbstractDungeon.actionManager.addToBottom(new BindAction(new CytosineOrb()));
            AbstractDungeon.actionManager.addToBottom(
                    new ReducePowerAction(p, p, PhosphatePower.POWER_ID, 1));
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_ATTACK_DMG);
        }
    }
}
