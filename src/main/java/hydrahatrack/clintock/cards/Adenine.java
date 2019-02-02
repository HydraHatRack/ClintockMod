package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.actions.BindAction;
import hydrahatrack.clintock.orbs.AdenineOrb;
import hydrahatrack.clintock.powers.DeoxyribosePower;
import hydrahatrack.clintock.powers.PhosphatePower;

public class Adenine extends CustomCard {
    public static final String ID = "clintock:Adenine";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 1;

    public Adenine() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL,
                CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.SELF);

        this.showEvokeValue = true;
        this.showEvokeOrbCount = 1;
        this.isEthereal = true;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (null == p.getPower(DeoxyribosePower.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(
                    new TalkAction(true, "I need more deoxyribose!", 2.0F, 2.0F));
        } else if (null == p.getPower(PhosphatePower.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(
                    new TalkAction(true, "I need more phosphate!", 2.0F, 2.0F));
        } else {
            AbstractDungeon.actionManager.addToBottom(new BindAction(new AdenineOrb()));
            AbstractDungeon.actionManager.addToBottom(
                    new ReducePowerAction(p, p, DeoxyribosePower.POWER_ID, 1));
            AbstractDungeon.actionManager.addToBottom(
                    new ReducePowerAction(p, p, PhosphatePower.POWER_ID, 1));
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isEthereal = false;
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
