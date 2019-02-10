package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import hydrahatrack.clintock.powers.FluorophorePower;

public class Tyrosine extends AbstractAminoAcid {
    private static final int FLUOROPHORE_AMOUNT = 1;

    public Tyrosine() {
        this.baseNumber = FLUOROPHORE_AMOUNT;
    }

    @Override
    public String getLabel() {
        return " NL Tyrosine (Gain " + this.baseNumber + " Fluorophore)";
    }

    @Override
    public AbstractGameAction getAction() {
        return new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new FluorophorePower(AbstractDungeon.player, this.baseNumber),
                this.baseNumber, true, AbstractGameAction.AttackEffect.NONE);
    }
}
