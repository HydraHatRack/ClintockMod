package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class EpidemicAction extends AbstractGameAction {
    private int[] damage;
    private boolean firstFrame = true;
    private int baseNumberOfMonstersAlive;
    private int numberOfMonstersAlive;

    public EpidemicAction(final int[] amount, final int numberOfMonstersAlive) {
        setValues(null, source, amount[0]);
        this.baseNumberOfMonstersAlive = numberOfMonstersAlive;
        this.numberOfMonstersAlive = 0;
        this.damage = amount;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.damageType = DamageInfo.DamageType.NORMAL;
        this.attackEffect = AttackEffect.SLASH_HORIZONTAL;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        boolean playedMusic;
        if (this.firstFrame) {
            playedMusic = false;
            int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size();
            for (int i = 0; i < temp; i++) {
                if (null != AbstractDungeon.getCurrRoom().monsters.monsters.get(i) &&
                        (!(AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).isDying) &&
                        ((AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).currentHealth > 0) &&
                        (!(AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).isEscaping)) {
                    if (playedMusic) {
                        AbstractDungeon.effectList.add(new FlashAtkImgEffect(
                                (AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).hb.cX,
                                (AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).hb.cY,
                                this.attackEffect, true));
                    } else {
                        playedMusic = true;
                        AbstractDungeon.effectList.add(new FlashAtkImgEffect(
                                (AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).hb.cX,
                                (AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).hb.cY, this.attackEffect));
                    }
                }
            }
            this.firstFrame = false;
        }
        tickDuration();
        if (this.isDone) {
            for (AbstractPower p : AbstractDungeon.player.powers) {
                p.onDamageAllEnemies(this.damage);
            }
            int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size();
            for (int i = 0; i < temp; i++) {
                if (null != AbstractDungeon.getCurrRoom().monsters.monsters.get(i)) {
                    if (!(AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).isDeadOrEscaped()) {
                        (AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).damage(
                                new DamageInfo(AbstractDungeon.player, this.damage[i], this.damageType));
                    }
                    if (!AbstractDungeon.getCurrRoom().monsters.monsters.get(i).isDying) {
                        numberOfMonstersAlive++;
                    }
                }
            }
            if (numberOfMonstersAlive < baseNumberOfMonstersAlive) {
                AbstractDungeon.actionManager.addToBottom(new EpidemicAction(this.damage, numberOfMonstersAlive));
            }
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
            if (!Settings.FAST_MODE) {
                AbstractDungeon.actionManager.addToTop(new WaitAction(0.1F));
            }
        }
    }
}
