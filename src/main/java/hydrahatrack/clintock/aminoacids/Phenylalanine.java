package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class Phenylalanine extends AbstractAminoAcid {
    public Phenylalanine() {}

    @Override
    public String getLabel() {
        return " NL Phenylalanine (Add a random uncommon card to your draw pile)";
    }

    @Override
    public AbstractGameAction getAction() {
        return new MakeTempCardInDrawPileAction(
                AbstractDungeon.getCard(AbstractCard.CardRarity.UNCOMMON, AbstractDungeon.cardRandomRng),
                1, true, true);
    }
}
