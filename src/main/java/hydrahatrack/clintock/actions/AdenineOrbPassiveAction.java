package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect.OrbFlareColor;

public class AdenineOrbPassiveAction extends AbstractGameAction {
    private AbstractOrb orb;

    public AdenineOrbPassiveAction(AbstractOrb orb) {
        this.orb = orb;
        this.actionType = ActionType.BLOCK;
    }

    public void update() {
        float speedTime = Settings.ACTION_DUR_FAST;
        if (Settings.FAST_MODE) {
            speedTime = 0.0F;
        }

        AbstractDungeon.actionManager.addToTop(
                new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.orb.passiveAmount));

        if (this.orb != null) {
            AbstractDungeon.actionManager.addToTop(
                    new VFXAction(new OrbFlareEffect(this.orb, OrbFlareColor.FROST), speedTime));
        }
        this.isDone = true;
    }
}
