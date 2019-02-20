package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect.OrbFlareColor;
import hydrahatrack.clintock.powers.ContaminationPower;

public class ThymineOrbPassiveAction extends AbstractGameAction {
    private DamageInfo info;
    private AbstractOrb orb;

    public ThymineOrbPassiveAction(DamageInfo info, AbstractOrb orb) {
        this.info = info;
        this.orb = orb;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.attackEffect = AttackEffect.BLUNT_LIGHT;
    }

    public void update() {
        float speedTime = 0.2F / AbstractDungeon.player.orbs.size();
        if (Settings.FAST_MODE) {
            speedTime = 0.0F;
        }

        if (AbstractDungeon.player.hasPower(ContaminationPower.POWER_ID)) {
            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                    if ((!m.isDead) && (!m.isDying)) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player,
                                new PoisonPower(m, AbstractDungeon.player, this.info.output), this.info.output));
                    }
                }
            }
        } else {
            AbstractDungeon.actionManager.addToTop(
                    new DamageAllEnemiesAction(AbstractDungeon.player,
                            DamageInfo.createDamageMatrix(this.info.base, true),
                            DamageInfo.DamageType.THORNS, this.attackEffect, true));
        }

        if (this.orb != null) {
            AbstractDungeon.actionManager.addToTop(new VFXAction(
                    new OrbFlareEffect(this.orb, OrbFlareColor.LIGHTNING), speedTime));
        }

        this.isDone = true;
    }
}
