package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

public class Lysine extends AbstractAminoAcid {
    private static final int PLATED_ARMOR_AMOUNT = 2;

    public Lysine() {
        this.baseNumber = PLATED_ARMOR_AMOUNT;
    }

    @Override
    public String getLabel() {
        return " NL Lysine (Gain " + this.baseNumber + " Plated Armor)";
    }

    @Override
    public AbstractGameAction getAction() {
        return new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new PlatedArmorPower(AbstractDungeon.player, this.baseNumber),
                this.baseNumber, true, AbstractGameAction.AttackEffect.NONE);
    }
}
