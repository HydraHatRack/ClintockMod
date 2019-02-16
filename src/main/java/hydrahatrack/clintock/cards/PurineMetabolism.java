package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.enums.AbstractCardEnum;
import hydrahatrack.clintock.orbs.AdenineOrb;
import hydrahatrack.clintock.orbs.GuanineOrb;

public class PurineMetabolism extends CustomCard {
    public static final String ID = "clintock:PurineMetabolism";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int BLOCK_AMOUNT = 7;
    private static final int UPGRADE_PLUS_BLOCK_AMOUNT = 3;

    public PurineMetabolism() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL,
                AbstractCardEnum.CLINTOCK_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);

        this.baseBlock = this.block = BLOCK_AMOUNT;
    }

    @Override
    public boolean canUse(final AbstractPlayer p, final AbstractMonster m) {
        boolean hasPurineNucleobase = false;
        for (AbstractOrb orb : AbstractDungeon.player.orbs) {
            if (orb instanceof AdenineOrb || orb instanceof GuanineOrb) {
                hasPurineNucleobase = true;
                break;
            }
        }
        if (!hasPurineNucleobase) {
            this.cantUseMessage = ClintockMod.NEEDS_PURINE_NUCLEOBASE;
            return false;
        }
        return true;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        for (AbstractOrb orb : AbstractDungeon.player.orbs) {
            if (orb instanceof AdenineOrb || orb instanceof GuanineOrb) {
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
            }
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_PLUS_BLOCK_AMOUNT);
        }
    }
}
