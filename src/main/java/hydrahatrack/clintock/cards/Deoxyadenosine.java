package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.actions.BindAction;
import hydrahatrack.clintock.enums.AbstractCardEnum;
import hydrahatrack.clintock.orbs.AdenineOrb;
import hydrahatrack.clintock.powers.InterruptedPower;
import hydrahatrack.clintock.powers.PhosphatePower;

public class Deoxyadenosine extends CustomCard {
    public static final String ID = "clintock:Deoxyadenosine";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int BLOCK_AMOUNT = 6;
    private static final int UPGRADE_PLUS_BLOCK_AMOUNT = 3;

    public Deoxyadenosine() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL,
                AbstractCardEnum.CLINTOCK_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);

        this.baseBlock = this.block = BLOCK_AMOUNT;
    }

    @Override
    public boolean canUse(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower(InterruptedPower.POWER_ID)) {
            this.cantUseMessage = ClintockMod.CANNOT_BIND;
            return false;
        } else if (p.hasPower(PhosphatePower.POWER_ID)) {
            this.cantUseMessage = ClintockMod.NEEDS_MORE_PHOSPHATE;
            return false;
        }
        return true;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        AbstractDungeon.actionManager.addToBottom(new BindAction(new AdenineOrb()));
        AbstractDungeon.actionManager.addToBottom(
                new ReducePowerAction(p, p, PhosphatePower.POWER_ID, 1));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBlock(UPGRADE_PLUS_BLOCK_AMOUNT);
        }
    }
}
