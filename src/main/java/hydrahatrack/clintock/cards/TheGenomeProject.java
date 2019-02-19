package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.enums.AbstractCardEnum;
import hydrahatrack.clintock.powers.GenomeMapperPower;
import hydrahatrack.clintock.powers.GenomeProjectPower;

public class TheGenomeProject extends CustomCard {
    public static final String ID = "clintock:TheGenomeProject";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int NUMBER_OF_CHROMOSOME_PAIRS = 23;
    private static final int FINAL_ATTACK_DMG = 200;
    private static final int UPGRADE_PLUS_FINAL_ATTACK_DMG = 50;

    public TheGenomeProject() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, CardType.POWER,
                AbstractCardEnum.CLINTOCK_COLOR, CardRarity.RARE, CardTarget.SELF);

        this.baseMagicNumber = this.magicNumber = FINAL_ATTACK_DMG;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new GenomeMapperPower(p)));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new GenomeProjectPower(p, this.magicNumber), this.magicNumber));
        for (int i = 1; i <= NUMBER_OF_CHROMOSOME_PAIRS; i++) {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Chromosome(i), 1));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_FINAL_ATTACK_DMG);
        }
    }
}
