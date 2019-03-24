package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.powers.InterruptedPower;

public class Intron extends CustomCard {
    public static final String ID = "clintock:Intron";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;

    public Intron() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, CardType.STATUS,
                CardColor.COLORLESS, CardRarity.COMMON, CardTarget.SELF);

        this.exhaust = true;
    }

    private void checkForOtherIntrons() {
        System.out.println(AbstractDungeon.player.hand.getCardNames().toString());
        if (!AbstractDungeon.player.hand.getCardNames().contains(ID)) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(
                    AbstractDungeon.player, AbstractDungeon.player, InterruptedPower.POWER_ID));
        }
    }

    @Override
    public boolean canUse(final AbstractPlayer p, final AbstractMonster m) {
        return (cardPlayable(m)) && (hasEnoughEnergy());
    }

    @Override
    public void triggerWhenDrawn() {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(
                AbstractDungeon.player, AbstractDungeon.player,
                new InterruptedPower(AbstractDungeon.player)));
    }

    @Override
    public void triggerOnExhaust() {
        super.triggerOnExhaust();
        checkForOtherIntrons();
    }

    @Override
    public void triggerOnManualDiscard() {
        super.triggerOnManualDiscard();
        checkForOtherIntrons();
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {}

    @Override
    public void upgrade() {}
}
