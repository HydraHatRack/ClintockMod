package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PenNibPower;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.enums.AbstractCardEnum;

public class Mitosis extends CustomCard {
    public static final String ID = "clintock:Mitosis";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;

    public Mitosis() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, CardType.ATTACK,
                AbstractCardEnum.CLINTOCK_COLOR, CardRarity.RARE, CardTarget.ENEMY);
    }

    @Override
    public boolean canUse(final AbstractPlayer p, final AbstractMonster m) {
        if (null != m && AbstractMonster.EnemyType.NORMAL != m.type) {
            this.cantUseMessage = ClintockMod.CANNOT_TARGET_ENEMY;
            return false;
        }
        return true;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (null != m) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(
                    new WeightyImpactEffect(m.hb.cX, m.hb.cY, Color.PURPLE)));
            if (p.hasPower(PenNibPower.POWER_ID)) {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(
                        m, new DamageInfo(p, m.currentHealth, this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.NONE));
            } else {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(
                        m, new DamageInfo(p, (m.currentHealth + 1) / 2, this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.NONE));
            }
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(UPGRADED_COST);
        }
    }
}
