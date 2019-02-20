package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPoisonOnRandomMonsterAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect.OrbFlareColor;
import hydrahatrack.clintock.cards.Contamination;
import hydrahatrack.clintock.powers.ContaminationPower;

public class CytosineOrbPassiveAction extends AbstractGameAction {
    private DamageInfo info;
    private AbstractOrb orb;

    public CytosineOrbPassiveAction(DamageInfo info, AbstractOrb orb) {
        this.info = info;
        this.orb = orb;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.attackEffect = AttackEffect.BLUNT_HEAVY;
    }

    public void update() {
        AbstractCreature m = AbstractDungeon.getRandomMonster();
        if (m != null) {
            float speedTime = 0.2F;
            if (Settings.FAST_MODE) {
                speedTime = 0.0F;
            }

            if (AbstractDungeon.player.hasPower(ContaminationPower.POWER_ID)) {
                AbstractDungeon.actionManager.addToTop(new ApplyPoisonOnRandomMonsterAction(
                        AbstractDungeon.player, this.info.output, true, AttackEffect.POISON));
            } else {
                this.info.output = AbstractOrb.applyLockOn(m, this.info.base);
                AbstractDungeon.actionManager.addToTop(
                        new DamageAction(m, this.info, this.attackEffect, true));
            }

            if (this.orb != null) {
                AbstractDungeon.actionManager.addToTop(new VFXAction(
                        new OrbFlareEffect(this.orb, OrbFlareColor.DARK), speedTime));
            }
        }
        this.isDone = true;
    }
}
