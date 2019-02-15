package hydrahatrack.clintock.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.actions.ApplyRandomAminoAcidAction;
import hydrahatrack.clintock.aminoacids.*;

import java.util.Random;

public class AminoAcidPower extends AbstractPower {
    private static final String POWER_ID = "clintock:AminoAcidPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture IMG = ImageMaster.loadImage(ClintockMod.getPowerImagePath(POWER_ID));

    public AminoAcidPower(final AbstractCreature owner, final int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = IMG;
        this.updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        AbstractDungeon.actionManager.addToBottom(new ApplyRandomAminoAcidAction());
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
