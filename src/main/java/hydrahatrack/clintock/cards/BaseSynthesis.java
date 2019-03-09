package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.actions.MakeRandomNucleobaseInHandAction;
import hydrahatrack.clintock.actions.SelectNucleobaseInHandAction;
import hydrahatrack.clintock.enums.AbstractCardEnum;

public class BaseSynthesis extends CustomCard {
    public static final String ID = "clintock:BaseSynthesis";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 1;
    private static final int BASE_MAGIC_NUMBER = 1;

    public BaseSynthesis() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL,
                AbstractCardEnum.CLINTOCK, CardRarity.BASIC, CardTarget.NONE);

        this.baseMagicNumber = this.magicNumber = BASE_MAGIC_NUMBER;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (this.upgraded) {
            AbstractDungeon.actionManager.addToBottom(new WaitAction(Settings.ACTION_DUR_MED));
            AbstractDungeon.actionManager.addToBottom(new SelectNucleobaseInHandAction(this.magicNumber));
        } else {
            AbstractDungeon.actionManager.addToBottom(new MakeRandomNucleobaseInHandAction(this.magicNumber));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
