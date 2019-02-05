package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Histidine extends AbstractAminoAcid {
    public Histidine() {}

    @Override
    public String getLabel() {
        return "His";
    }

    @Override
    public AbstractGameAction getAction() {
        AbstractMonster m = AbstractDungeon.getRandomMonster();
        if (null != m) {
            return new RemoveAllBlockAction(m, AbstractDungeon.player);
        }
        return null;
    }
}
