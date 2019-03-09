package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.enums.AbstractCardEnum;
import hydrahatrack.clintock.powers.SugarPower;
import hydrahatrack.clintock.powers.PhosphatePower;

public class Replenish extends CustomCard {
    public static final String ID = "clintock:Replenish";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 0;
    private static final int BLOCK_AMOUNT = 3;
    private static final int UPGRADE_PLUS_BLOCK_AMOUNT = 2;
    private static final int BASE_MAGIC_NUMBER = 1;
    private static final int UPGRADE_PLUS_MAGIC_NUMBER = 1;

    public Replenish() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL,
                AbstractCardEnum.CLINTOCK, CardRarity.BASIC, CardTarget.SELF);

        this.baseBlock = this.block = BLOCK_AMOUNT;
        this.baseMagicNumber = this.magicNumber = BASE_MAGIC_NUMBER;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        if (!Settings.FAST_MODE) {
            AbstractDungeon.actionManager.addToBottom(new WaitAction(Settings.ACTION_DUR_FAST));
        }

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new SugarPower(p, this.magicNumber), this.magicNumber));
        if (!Settings.FAST_MODE) {
            AbstractDungeon.actionManager.addToBottom(new WaitAction(Settings.ACTION_DUR_FAST));
        }

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new PhosphatePower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_PLUS_BLOCK_AMOUNT);
            this.upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER);
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
