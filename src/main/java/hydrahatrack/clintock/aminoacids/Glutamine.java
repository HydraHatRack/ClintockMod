package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class Glutamine extends AbstractAminoAcid {
    public Glutamine() {}

    @Override
    public String getLabel() {
        return "Gln";
    }

    @Override
    public AbstractGameAction getAction() {
        return new AddCardToDeckAction(AbstractDungeon.getCard(
                AbstractCard.CardRarity.COMMON, AbstractDungeon.cardRandomRng));
    }
}
