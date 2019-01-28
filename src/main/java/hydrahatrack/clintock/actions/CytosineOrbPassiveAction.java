package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect.OrbFlareColor;

public class CytosineOrbPassiveAction extends AbstractGameAction {
    private DamageInfo info;
    private AbstractOrb orb;

    public CytosineOrbPassiveAction(DamageInfo info, AbstractOrb orb) {
        this.info = info;
        this.orb = orb;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.attackEffect = AttackEffect.SLASH_DIAGONAL;
    }

    public void update() {
        AbstractCreature m = AbstractDungeon.getRandomMonster();
        if (m != null) {
            float speedTime = 0.2F / AbstractDungeon.player.orbs.size();
            if (Settings.FAST_MODE) {
                speedTime = 0.0F;
            }
            this.info.output = AbstractOrb.applyLockOn(m, this.info.base);
            AbstractDungeon.actionManager.addToTop(new DamageAction(m, this.info, this.attackEffect, true));
            AbstractDungeon.actionManager.addToTop(new VFXAction(new LightningEffect(m.drawX, m.drawY), speedTime));
            AbstractDungeon.actionManager.addToTop(new SFXAction("ORB_LIGHTNING_EVOKE"));
            if (this.orb != null) {
                AbstractDungeon.actionManager.addToTop(new VFXAction(new OrbFlareEffect(this.orb, OrbFlareColor.LIGHTNING), speedTime));
            }
        }
        this.isDone = true;
    }
}
