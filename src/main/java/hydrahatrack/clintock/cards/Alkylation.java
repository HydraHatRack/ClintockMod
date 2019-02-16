package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.actions.CytosineOrbPassiveAction;
import hydrahatrack.clintock.actions.ThymineOrbPassiveAction;
import hydrahatrack.clintock.enums.AbstractCardEnum;
import hydrahatrack.clintock.orbs.CytosineOrb;
import hydrahatrack.clintock.orbs.ThymineOrb;

public class Alkylation extends CustomCard {
    public static final String ID = "clintock:Alkylation";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = -1;

    public Alkylation() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL,
                AbstractCardEnum.CLINTOCK_COLOR, CardRarity.COMMON, CardTarget.NONE);
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (this.energyOnUse < EnergyPanel.totalCount) {
            this.energyOnUse = EnergyPanel.totalCount;
        }

        int offset = 0;
        if (this.upgraded) {
            offset = 1;
        }

        for (AbstractOrb orb : p.orbs) {
            if (orb instanceof CytosineOrb) {
                for (int i = 0; i < this.energyOnUse + offset; i++) {
                    AbstractDungeon.actionManager.addToBottom(
                            new CytosineOrbPassiveAction(new DamageInfo(
                                    AbstractDungeon.player, orb.passiveAmount, DamageInfo.DamageType.THORNS), orb));
                }
            } else if (orb instanceof ThymineOrb) {
                for (int i = 0; i < this.energyOnUse + offset; i++) {
                    AbstractDungeon.actionManager.addToBottom(
                            new ThymineOrbPassiveAction(new DamageInfo(
                                    AbstractDungeon.player, orb.passiveAmount, DamageInfo.DamageType.THORNS), orb));
                }
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
