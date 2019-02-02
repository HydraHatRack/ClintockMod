package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class Arginine extends AbstractAminoAcid {
    @Override
    public String getLabel() {
        return "Arg";
    }

    @Override
    public AbstractGameAction getAction() {
        AbstractCreature m = AbstractDungeon.getRandomMonster();
        if (m != null) {
            return new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 2);
        }
        return null;
    }
}
