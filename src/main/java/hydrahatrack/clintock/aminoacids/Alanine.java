package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;

public class Alanine extends AbstractAminoAcid {
    private static final int ENERGY_AMOUNT = 1;

    public Alanine() {
        this.baseNumber = ENERGY_AMOUNT;
    }

    @Override
    public String getLabel() {
        return "Ala";
    }

    @Override
    public AbstractGameAction getAction() {
        return new ApplyPowerAction(
                AbstractDungeon.player, AbstractDungeon.player,
                new EnergizedBluePower(AbstractDungeon.player, this.baseNumber), this.baseNumber);
    }
}
