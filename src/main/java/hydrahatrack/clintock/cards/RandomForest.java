package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.enums.AbstractCardEnum;
import hydrahatrack.clintock.powers.FluorophorePower;

import java.util.Random;

public class RandomForest extends CustomCard {
    public static final String ID = "clintock:RandomForest";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = -1;

    public RandomForest() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL,
                AbstractCardEnum.CLINTOCK, CardRarity.UNCOMMON, CardTarget.SELF);

        this.exhaust = true;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (this.energyOnUse < EnergyPanel.totalCount) {
            this.energyOnUse = EnergyPanel.totalCount;
        }

        if (this.energyOnUse > 0) {
            int randomValue = new Random().nextInt(3);
            if (this.upgraded || 0 == randomValue) {
                AbstractDungeon.actionManager.addToBottom(
                        new ApplyPowerAction(p, p, new StrengthPower(p, this.energyOnUse), this.energyOnUse));
            }
            if (this.upgraded || 1 == randomValue) {
                AbstractDungeon.actionManager.addToBottom(
                        new ApplyPowerAction(p, p, new DexterityPower(p, this.energyOnUse), this.energyOnUse));
            }
            if (this.upgraded || 2 == randomValue) {
                AbstractDungeon.actionManager.addToBottom(
                        new ApplyPowerAction(p, p, new FluorophorePower(p, this.energyOnUse), this.energyOnUse));
            }
        }

        if (!this.freeToPlayOnce) {
            p.energy.use(EnergyPanel.totalCount);
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
