package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
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
                AbstractCardEnum.CLINTOCK_COLOR, CardRarity.UNCOMMON, CardTarget.NONE);

        this.baseBlock = this.block = BLOCK_AMOUNT;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        if (null == p.getPower(PhosphatePower.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(
                    new TalkAction(true, "I need more phosphate!", 2.0F, 2.0F));
        } else {
            AbstractDungeon.actionManager.addToBottom(new BindAction(new AdenineOrb()));
            AbstractDungeon.actionManager.addToBottom(
                    new ReducePowerAction(p, p, PhosphatePower.POWER_ID, 1));
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBlock(UPGRADE_PLUS_BLOCK_AMOUNT);
        }
    }
}
