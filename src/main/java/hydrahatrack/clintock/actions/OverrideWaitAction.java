package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class OverrideWaitAction extends AbstractGameAction
{
    public OverrideWaitAction(final float duration) {
        setValues(null, null, 0);
        this.duration = duration;
        this.actionType = AbstractGameAction.ActionType.WAIT;
    }

    public void update() {
        tickDuration();
    }
}
