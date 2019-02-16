package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import hydrahatrack.clintock.powers.PhosphatePower;

public class CompostBinAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("RecycleAction");
    public static final String[] TEXT = uiStrings.TEXT;
    private int multiplier;

    public CompostBinAction(final int multiplier) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.multiplier = multiplier;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (AbstractDungeon.player.hand.isEmpty()) {
                this.isDone = true;
                return;
            }
            if (AbstractDungeon.player.hand.size() == 1) {
                if (AbstractDungeon.player.hand.getBottomCard().costForTurn == -1) {
                    int phosphateAmount = EnergyPanel.getCurrentEnergy() * multiplier;
                    AbstractDungeon.actionManager.addToTop(
                            new ApplyPowerAction(
                                    AbstractDungeon.player,
                                    AbstractDungeon.player,
                                    new PhosphatePower(AbstractDungeon.player, phosphateAmount),
                                    phosphateAmount));
                } else if (AbstractDungeon.player.hand.getBottomCard().costForTurn > 0) {
                    int phosphateAmount = AbstractDungeon.player.hand.getBottomCard().costForTurn * multiplier;
                    AbstractDungeon.actionManager.addToTop(
                            new ApplyPowerAction(
                                    AbstractDungeon.player,
                                    AbstractDungeon.player,
                                    new PhosphatePower(AbstractDungeon.player, phosphateAmount),
                                    phosphateAmount));
                }
                AbstractDungeon.player.hand.moveToExhaustPile(AbstractDungeon.player.hand.getBottomCard());
                tickDuration();
                return;
            }
            AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false);
            tickDuration();
            return;
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                if (c.costForTurn == -1) {
                    int phosphateAmount = EnergyPanel.getCurrentEnergy() * multiplier;
                    AbstractDungeon.actionManager.addToTop(
                            new ApplyPowerAction(
                                    AbstractDungeon.player,
                                    AbstractDungeon.player,
                                    new PhosphatePower(AbstractDungeon.player, phosphateAmount),
                                    phosphateAmount));
                } else if (c.costForTurn > 0) {
                    int phosphateAmount = c.costForTurn * multiplier;
                    AbstractDungeon.actionManager.addToTop(
                            new ApplyPowerAction(
                                    AbstractDungeon.player,
                                    AbstractDungeon.player,
                                    new PhosphatePower(AbstractDungeon.player, phosphateAmount),
                                    phosphateAmount));
                }
                AbstractDungeon.player.hand.moveToExhaustPile(c);
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
        }
        tickDuration();
    }
}
