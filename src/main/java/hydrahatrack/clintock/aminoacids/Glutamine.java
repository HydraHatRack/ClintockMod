package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class Glutamine extends AbstractAminoAcid {
    public Glutamine() {}

    @Override
    public String getLabel() {
        return " NL Glutamine (Add a random common card to your draw pile)";
    }

    @Override
    public AbstractGameAction getAction() {
        return new MakeTempCardInDrawPileAction(
                AbstractDungeon.getCard(AbstractCard.CardRarity.COMMON, AbstractDungeon.cardRandomRng),
                1, true, true);
    }
}
