package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.actions.LinkAction;
import hydrahatrack.clintock.enums.AbstractCardEnum;
import hydrahatrack.clintock.orbs.ThymineOrb;
import hydrahatrack.clintock.powers.InterruptedPower;

public class DTMP extends CustomCard {
    public static final String ID = "clintock:DTMP";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    private static final int ATTACK_DMG = 7;
    private static final int UPGRADE_PLUS_ATTACK_DMG = 3;

    public DTMP() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, CardType.ATTACK,
                AbstractCardEnum.CLINTOCK, CardRarity.UNCOMMON, CardTarget.ALL);

        this.baseDamage = this.damage = ATTACK_DMG;
        this.isMultiDamage = true;
    }

    @Override
    public boolean canUse(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower(InterruptedPower.POWER_ID)) {
            this.cantUseMessage = ClintockMod.CANNOT_LINK;
            return false;
        }
        return true;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new CleaveEffect(), 0.0F));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(
                p, this.multiDamage, this.damageType, AbstractGameAction.AttackEffect.NONE, false));
        AbstractDungeon.actionManager.addToBottom(new LinkAction(new ThymineOrb()));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_ATTACK_DMG);
        }
    }
}
