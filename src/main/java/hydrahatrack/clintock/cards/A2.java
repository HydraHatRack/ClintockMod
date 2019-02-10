package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.IntimidateEffect;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.enums.AbstractCardEnum;
import hydrahatrack.clintock.powers.PhosphatePower;

public class A2 extends CustomCard {
    public static final String ID = "clintock:A2";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int WEAK_AMOUNT = 1;
    private static final int UPGRADE_PLUS_WEAK_AMOUNT = 1;

    public A2() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, CardType.SKILL,
                AbstractCardEnum.CLINTOCK_COLOR, CardRarity.COMMON, CardTarget.ALL);

        this.baseMagicNumber = this.magicNumber = WEAK_AMOUNT;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SFXAction("INTIMIDATE"));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(
                p, new IntimidateEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 1.0F));

        int numberOfWeakenedMonsters = 0;
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo.hasPower(WeakPower.POWER_ID) || !mo.hasPower(ArtifactPower.POWER_ID)) {
                numberOfWeakenedMonsters++;
            }
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(mo, p, new WeakPower(mo, this.magicNumber, false),
                            this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new PhosphatePower(p, numberOfWeakenedMonsters), numberOfWeakenedMonsters));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_WEAK_AMOUNT);
        }
    }
}
