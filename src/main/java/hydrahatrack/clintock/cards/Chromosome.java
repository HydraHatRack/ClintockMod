package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.powers.GenomeMapperPower;

public class Chromosome extends CustomCard {
    public static final String ID = "clintock:Chromosome";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;

    public Chromosome(final int number) {
        super(ID, getName(number), ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, CardType.STATUS,
                CardColor.COLORLESS, CardRarity.COMMON, CardTarget.SELF);

        this.exhaust = true;
    }

    public Chromosome() {
        this(-1);
    }

    private static String getName(final int number) {
        String name = NAME;
        if (number == 23) {
            name += " X/Y";
        } else if (number > 0) {
            name += " " + number;
        }
        return name;
    }

    @Override
    public boolean canUse(final AbstractPlayer p, final AbstractMonster m) {
        return (cardPlayable(m)) && (hasEnoughEnergy());
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {}

    @Override
    public void triggerOnExhaust() {
        if (AbstractDungeon.player.hasPower(GenomeMapperPower.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(
                    AbstractDungeon.player, AbstractDungeon.player, GenomeMapperPower.POWER_ID, 1));
        }
    }

    @Override
    public void upgrade() {}
}
