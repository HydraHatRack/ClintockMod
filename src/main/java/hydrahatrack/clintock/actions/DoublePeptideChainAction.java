package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import hydrahatrack.clintock.powers.PeptideChainPower;

public class DoublePeptideChainAction extends AbstractGameAction {
    public DoublePeptideChainAction() {
        this.actionType = ActionType.POWER;
    }

    public void update() {
        AbstractPower p = AbstractDungeon.player.getPower(PeptideChainPower.POWER_ID);
        if (null == p) {
            AbstractDungeon.actionManager.addToBottom(
                    new TalkAction(true, "I don't have a peptide chain yet!", 2.0F, 2.0F));
        } else {
            ((PeptideChainPower) p).doubleAminoAcidList();
        }
        this.isDone = true;
    }
}
