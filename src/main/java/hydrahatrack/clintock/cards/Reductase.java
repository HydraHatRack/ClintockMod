package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.enums.AbstractCardEnum;
import hydrahatrack.clintock.powers.SugarPower;

public class Reductase extends CustomCard {
    public static final String ID = "clintock:Reductase";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 1;

    public Reductase() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL,
                AbstractCardEnum.CLINTOCK_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);

        this.exhaust = true;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractPower power = AbstractDungeon.player.getPower(SugarPower.POWER_ID);
        if (null != power) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, power, power.amount));
        } else {
            AbstractDungeon.actionManager.addToBottom(
                    new TalkAction(true, "I don't have any deoxyribose!", 2.0F, 2.0F));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.exhaust = false;
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
