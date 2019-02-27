package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.enums.AbstractCardEnum;

public class VectorFlow extends CustomCard {
    public static final String ID = "clintock:VectorFlow";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    private static final int ATTACK_DMG = 12;
    private static final int UPGRADE_PLUS_ATTACK_DMG = 4;

    public VectorFlow() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, CardType.ATTACK,
                AbstractCardEnum.CLINTOCK_COLOR, CardRarity.COMMON, CardTarget.ENEMY);

        this.baseDamage = this.damage = ATTACK_DMG;
    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        setCostForTurn(this.cost - AbstractDungeon.actionManager.orbsChanneledThisTurn.size());
    }

    @Override
    public void triggerOnOtherCardPlayed(final AbstractCard c) {
        if (c.cardID.equals(Adenine.ID) ||
                c.cardID.equals(Cytosine.ID) ||
                c.cardID.equals(Guanine.ID) ||
                c.cardID.equals(Thymine.ID) ||
                c.cardID.equals(Deoxyadenosine.ID) ||
                c.cardID.equals(Deoxycytidine.ID) ||
                c.cardID.equals(Deoxyguanosine.ID) ||
                c.cardID.equals(Thymidine.ID) ||
                c.cardID.equals(DAMP.ID) ||
                c.cardID.equals(DCMP.ID) ||
                c.cardID.equals(DGMP.ID) ||
                c.cardID.equals(DTMP.ID)) {
            modifyCostForTurn(-1);
        }
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_ATTACK_DMG);
        }
    }
}
