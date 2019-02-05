package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPoisonOnRandomMonsterAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class Serine extends AbstractAminoAcid {
    private static final int POISON_AMOUNT = 2;

    public Serine() {
        this.baseNumber = POISON_AMOUNT;
    }

    @Override
    public String getLabel() {
        return "Ser";
    }

    @Override
    public AbstractGameAction getAction() {
        return new ApplyPoisonOnRandomMonsterAction(
                AbstractDungeon.player, this.baseNumber, true, AbstractGameAction.AttackEffect.POISON);
    }
}
