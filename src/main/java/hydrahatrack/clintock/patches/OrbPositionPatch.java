package hydrahatrack.clintock.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import hydrahatrack.clintock.characters.TheClintock;

@SpirePatch(
        clz = AbstractOrb.class,
        method = "setSlot",
        paramtypez = {
                int.class,
                int.class
        }
)
public class OrbPositionPatch {
    public static SpireReturn<Void> Prefix(AbstractOrb o, int slotNumber) {
        if (AbstractDungeon.player instanceof TheClintock) {
            o.tX = ((TheClintock) AbstractDungeon.player).orbPositionsX[slotNumber];
            o.tY = ((TheClintock) AbstractDungeon.player).orbPositionsY[slotNumber];
            o.hb.move(o.tX, o.tY);
            return SpireReturn.Return(null);
        }
        return SpireReturn.Continue();
    }
}
