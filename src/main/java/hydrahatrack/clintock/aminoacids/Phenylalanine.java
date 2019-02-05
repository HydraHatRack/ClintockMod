package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class Phenylalanine extends AbstractAminoAcid {
    public Phenylalanine() {}

    @Override
    public String getLabel() {
        return "Phe";
    }

    @Override
    public AbstractGameAction getAction() {
        return new AddCardToDeckAction(AbstractDungeon.getCard(
                AbstractCard.CardRarity.UNCOMMON, AbstractDungeon.cardRandomRng));
    }
}
