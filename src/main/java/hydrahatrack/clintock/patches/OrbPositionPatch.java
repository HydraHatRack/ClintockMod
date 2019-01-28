package hydrahatrack.clintock.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import hydrahatrack.clintock.characters.TheClintock;

@SpirePatch(clz = AbstractOrb.class,
        method = "setSlot",
        paramtypez = {
                int.class,
                int.class
        }
)
public class OrbPositionPatch {
    public static SpireReturn<Void> Prefix(AbstractOrb abstractOrb_instance, int slotNumber) {
        if (AbstractDungeon.player instanceof TheClintock) {
            abstractOrb_instance.tX = ((TheClintock) AbstractDungeon.player).orbPositionsX[slotNumber];
            abstractOrb_instance.tY = ((TheClintock) AbstractDungeon.player).orbPositionsY[slotNumber];
            abstractOrb_instance.hb.move(abstractOrb_instance.tX, abstractOrb_instance.tY);
            return SpireReturn.Return(null);
        } else {
            return SpireReturn.Continue();
        }
    }
}
