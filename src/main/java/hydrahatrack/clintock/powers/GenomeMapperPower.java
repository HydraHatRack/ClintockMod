package hydrahatrack.clintock.powers;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import hydrahatrack.clintock.ClintockMod;

public class GenomeMapperPower extends AbstractPower {
    public static final String POWER_ID = "clintock:GenomeMapperPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final int NUMBER_OF_CHROMOSOME_PAIRS = 23;

    public GenomeMapperPower(final AbstractCreature owner) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = NUMBER_OF_CHROMOSOME_PAIRS;
        this.img = ImageMaster.loadImage(ClintockMod.getPowerImagePath(POWER_ID));
        this.updateDescription();
    }

    @Override
    public void onRemove() {
        if (AbstractDungeon.player.hasPower(GenomeProjectPower.POWER_ID)) {
            GenomeProjectPower power =
                    (GenomeProjectPower) AbstractDungeon.player.getPower(GenomeProjectPower.POWER_ID);

            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                if (m != null) {
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(
                            new WeightyImpactEffect(m.hb.cX, m.hb.cY, Color.PURPLE)));
                }
            }

            AbstractDungeon.actionManager.addToBottom(new WaitAction(0.8F));
            AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(
                    AbstractDungeon.player, new CleaveEffect(), 0.1F));
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(
                    AbstractDungeon.player, DamageInfo.createDamageMatrix(power.amount, true),
                    DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));
        }
    }

    @Override
    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("SCENE_TORCH_EXTINGUISH", 0.05F);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
