package hydrahatrack.clintock.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.actions.EpidemicAction;
import hydrahatrack.clintock.enums.AbstractCardEnum;

public class Epidemic extends CustomCard {
    public static final String ID = "clintock:Epidemic";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int ATTACK_DMG = 7;
    private static final int UPGRADE_PLUS_ATTACK_DMG = 3;

    public Epidemic() {
        super(ID, NAME, ClintockMod.getCardImagePath(ID), COST, DESCRIPTION, CardType.ATTACK,
                AbstractCardEnum.CLINTOCK, CardRarity.COMMON, CardTarget.ALL_ENEMY);

        this.baseDamage = this.damage = ATTACK_DMG;
        this.isMultiDamage = true;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        int monsterCount = 0;
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (null != monster && !monster.isDying && monster.currentHealth > 0 && !monster.isEscaping) {
                monsterCount++;
            }
        }
        AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_PIERCING_WAIL"));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new CleaveEffect(), 0.1F));
        AbstractDungeon.actionManager.addToBottom(new EpidemicAction(this.multiDamage, monsterCount));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_ATTACK_DMG);
        }
    }
}
