package hydrahatrack.clintock.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.powers.SugarPower;
import hydrahatrack.clintock.powers.PhosphatePower;

public class BiomoleculePool extends CustomRelic {
    public static final String ID = "clintock:BiomoleculePool";
    private static final Texture IMG = ImageMaster.loadImage(ClintockMod.getRelicImagePath(ID));
    private static final Texture OUTLINE = ImageMaster.loadImage(ClintockMod.getRelicOutlineImagePath(ID));

    public BiomoleculePool() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.CLINK);
    }

    @Override
    public void atBattleStartPreDraw() {
        int RESOURCE_AMOUNT = 3;
        flash();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
                AbstractDungeon.player,
                AbstractDungeon.player,
                new SugarPower(AbstractDungeon.player, RESOURCE_AMOUNT)));
        AbstractDungeon.actionManager.addToBottom(new WaitAction(Settings.ACTION_DUR_MED));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
                AbstractDungeon.player,
                AbstractDungeon.player,
                new PhosphatePower(AbstractDungeon.player, RESOURCE_AMOUNT)));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
