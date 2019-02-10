package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.actions.BindAction;
import hydrahatrack.clintock.orbs.CytosineOrb;
import hydrahatrack.clintock.powers.SugarPower;
import hydrahatrack.clintock.powers.PhosphatePower;

public class Cytosine extends CustomCard {
    public static final String ID = "clintock:Cytosine";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 0;

    public Cytosine() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL,
                CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.SELF);

        this.showEvokeValue = true;
        this.showEvokeOrbCount = 1;
        this.isEthereal = true;
        this.exhaust = true;
    }

    @Override
    public boolean canUse(final AbstractPlayer p, final AbstractMonster m) {
        if (null == AbstractDungeon.player.getPower(SugarPower.POWER_ID)) {
            this.cantUseMessage = ClintockMod.NEEDS_MORE_SUGAR;
            return false;
        } else if (null == AbstractDungeon.player.getPower(PhosphatePower.POWER_ID)) {
            this.cantUseMessage = ClintockMod.NEEDS_MORE_PHOSPHATE;
            return false;
        }
        return true;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new BindAction(new CytosineOrb()));
        AbstractDungeon.actionManager.addToBottom(
                new ReducePowerAction(p, p, SugarPower.POWER_ID, 1));
        AbstractDungeon.actionManager.addToBottom(
                new ReducePowerAction(p, p, PhosphatePower.POWER_ID, 1));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isEthereal = false;
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
