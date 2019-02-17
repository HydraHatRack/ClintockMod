package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class TelomereErosionAction extends AbstractGameAction {
    private DamageInfo info;
    private int drawCardAmount;

    public TelomereErosionAction(
            final AbstractCreature target, final DamageInfo info, final int drawCardAmount) {
        this.info = info;
        this.target = target;
        this.drawCardAmount = drawCardAmount;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_FASTER;
    }

    @Override
    public void update() {
        if ((this.duration == Settings.ACTION_DUR_FASTER) && (this.target != null)) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(
                    this.target.hb.cX, this.target.hb.cY, AttackEffect.SLASH_HORIZONTAL));

            this.target.damage(this.info);
            if ((((AbstractMonster)this.target).isDying) || (this.target.currentHealth <= 0)) {
                AbstractDungeon.actionManager.addToBottom(
                        new DrawCardAction(AbstractDungeon.player, this.drawCardAmount));
            }
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }
        tickDuration();
    }
}
