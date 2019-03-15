package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import hydrahatrack.clintock.aminoacids.GlutamicAcid;
import hydrahatrack.clintock.orbs.AdenineOrb;
import hydrahatrack.clintock.orbs.CytosineOrb;
import hydrahatrack.clintock.orbs.ThymineOrb;

public class GlutamicAcidAction extends AbstractGameAction {
    public GlutamicAcidAction(final int amount) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST) {
            AbstractDungeon.actionManager.addToBottom(
                    new TextAboveCreatureAction(AbstractDungeon.player, GlutamicAcid.NAME));

            for (AbstractOrb orb : AbstractDungeon.player.orbs) {
                if (orb instanceof AdenineOrb) {
                    AbstractDungeon.actionManager.addToBottom(new AdenineOrbPassiveAction(orb));
                } else if (orb instanceof CytosineOrb) {
                    AbstractDungeon.actionManager.addToBottom(
                            new CytosineOrbPassiveAction(new DamageInfo(
                                    AbstractDungeon.player, orb.passiveAmount, DamageInfo.DamageType.THORNS), orb));
                } else if (orb instanceof ThymineOrb) {
                    AbstractDungeon.actionManager.addToBottom(
                            new ThymineOrbPassiveAction(new DamageInfo(
                                    AbstractDungeon.player, orb.passiveAmount, DamageInfo.DamageType.THORNS), orb));
                }
            }

            this.isDone = true;
            return;
        }
        tickDuration();
    }
}
