package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
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
            for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (null != monster && !monster.isDying && monster.currentHealth > 0 && !monster.isEscaping) {
                    if (playedMusic) {
                        AbstractDungeon.effectList.add(new FlashAtkImgEffect(
                                monster.hb.cX,
                                monster.hb.cY,
                                this.attackEffect, true));
                    } else {
                        playedMusic = true;
                        AbstractDungeon.effectList.add(new FlashAtkImgEffect(
                                monster.hb.cX,
                                monster.hb.cY, this.attackEffect));
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
                AbstractMonster monster = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                if (null != monster) {
                    if (!monster.isDeadOrEscaped()) {
                        monster.damage(new DamageInfo(AbstractDungeon.player, this.damage[i], this.damageType));
                    }
                    if (!monster.isDying) {
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
