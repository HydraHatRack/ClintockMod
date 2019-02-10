package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class Methionine extends AbstractAminoAcid {
    private static final int HEALTH_AMOUNT = 1;

    public Methionine() {
        this.baseNumber = HEALTH_AMOUNT;
    }

    @Override
    public String getLabel() {
        return " NL Methionine (Heal " + this.baseNumber + " HP)";
    }

    @Override
    public AbstractGameAction getAction() {
        return new HealAction(AbstractDungeon.player, AbstractDungeon.player, this.baseNumber);
    }
}
