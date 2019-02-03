package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.actions.BindAction;
import hydrahatrack.clintock.enums.AbstractCardEnum;
import hydrahatrack.clintock.orbs.ThymineOrb;
import hydrahatrack.clintock.powers.PhosphatePower;

public class Thymidine extends CustomCard {
    public static final String ID = "clintock:Thymidine";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 4;
    private static final int UPGRADE_PLUS_ATTACK_DMG = 3;

    public Thymidine() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, CardType.ATTACK,
                AbstractCardEnum.CLINTOCK_COLOR, AbstractCard.CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);

        this.baseDamage = this.damage = ATTACK_DMG;
        this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new CleaveEffect(), 0.0F));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(
                p, this.multiDamage, this.damageType, AbstractGameAction.AttackEffect.NONE, false));
        if (null == p.getPower(PhosphatePower.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(
                    new TalkAction(true, "I need more phosphate!", 2.0F, 2.0F));
        } else {
            AbstractDungeon.actionManager.addToBottom(new BindAction(new ThymineOrb()));
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
