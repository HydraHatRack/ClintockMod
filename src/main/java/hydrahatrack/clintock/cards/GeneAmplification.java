package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.actions.DoublePeptideChainAction;
import hydrahatrack.clintock.actions.TriplePeptideChainAction;
import hydrahatrack.clintock.enums.AbstractCardEnum;
import hydrahatrack.clintock.powers.PeptideChainPower;

public class GeneAmplification extends CustomCard {
    public static final String ID = "clintock:GeneAmplification";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 2;

    public GeneAmplification() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL,
                AbstractCardEnum.CLINTOCK, CardRarity.RARE, CardTarget.SELF);

        this.exhaust = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractPower power = AbstractDungeon.player.getPower(PeptideChainPower.POWER_ID);
        if (null == power) {
            AbstractDungeon.actionManager.addToBottom(
                    new TalkAction(true, ClintockMod.NEEDS_PEPTIDE_CHAIN, 2.0F, 2.0F));
        } else if (!this.upgraded){
            AbstractDungeon.actionManager.addToBottom(new DoublePeptideChainAction());
        } else {
            AbstractDungeon.actionManager.addToBottom(new TriplePeptideChainAction());
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
