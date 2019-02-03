package hydrahatrack.clintock.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import hydrahatrack.clintock.characters.TheClintock;
import hydrahatrack.clintock.powers.FluorophorePower;

@SpirePatch(
        clz = AbstractDungeon.class,
        method = "onModifyPower"
)
public class ModifyPowerPatch {
    public static SpireReturn<Void> Prefix() {
        if (AbstractDungeon.player instanceof TheClintock) {
            AbstractDungeon.player.hand.applyPowers();
            if (AbstractDungeon.player.hasPower(FluorophorePower.POWER_ID)) {
                for (AbstractOrb o : AbstractDungeon.player.orbs) {
                    o.updateDescription();
                }
            }
        }
        return SpireReturn.Return(null);
    }
}
