package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.actions.BindAction;
import hydrahatrack.clintock.enums.AbstractCardEnum;
import hydrahatrack.clintock.orbs.GuanineOrb;
import hydrahatrack.clintock.powers.PhosphatePower;

public class Deoxyguanosine extends CustomCard {
    public static final String ID = "clintock:Deoxyguanosine";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int ENERGY_AMOUNT = 1;
    private static final int UPGRADE_PLUS_ENERGY_AMOUNT = 1;

    public Deoxyguanosine() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL,
                AbstractCardEnum.CLINTOCK_COLOR, CardRarity.UNCOMMON, CardTarget.NONE);

        this.baseMagicNumber = this.magicNumber = ENERGY_AMOUNT;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new EnergizedPower(p, 1), 1));
        if (null == p.getPower(PhosphatePower.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(
                    new TalkAction(true, "I need more phosphate!", 2.0F, 2.0F));
        } else {
            AbstractDungeon.actionManager.addToBottom(new BindAction(new GuanineOrb()));
            AbstractDungeon.actionManager.addToBottom(
                    new ReducePowerAction(p, p, PhosphatePower.POWER_ID, 1));
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_ENERGY_AMOUNT);
        }
    }
}
