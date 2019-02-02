package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisEffect;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.enums.AbstractCardEnum;
import hydrahatrack.clintock.orbs.GuanineOrb;
import hydrahatrack.clintock.orbs.ThymineOrb;

public class Ketosis extends CustomCard {
    public static final String ID = "clintock:Ketosis";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 10;
    private static final int SELF_DMG = 4;
    private static final int UPGRADE_MINUS_SELF_DMG = -2;

    public Ketosis() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
                AbstractCardEnum.CLINTOCK_COLOR, CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);

        this.baseDamage = this.damage = ATTACK_DMG;
        this.baseMagicNumber = this.magicNumber = SELF_DMG;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (abstractMonster != null) {
            boolean dealsDamage = false;
            for (AbstractOrb o : AbstractDungeon.player.orbs) {
                if (o instanceof GuanineOrb || o instanceof ThymineOrb) {
                    dealsDamage = true;
                    AbstractDungeon.actionManager.addToTop(new DamageAction(abstractMonster,
                            new DamageInfo(abstractPlayer, this.damage,
                                    this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                }
            }

            if (dealsDamage) {
                AbstractDungeon.actionManager.addToTop(new VFXAction(new HemokinesisEffect(
                        abstractPlayer.hb.cX,
                        abstractPlayer.hb.cY,
                        abstractMonster.hb.cX,
                        abstractMonster.hb.cY), 0.5F));
            }
        }

        AbstractDungeon.actionManager.addToTop(
                new LoseHPAction(abstractPlayer, abstractPlayer, this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MINUS_SELF_DMG);
        }
    }
}
