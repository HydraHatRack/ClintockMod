package hydrahatrack.clintock.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import hydrahatrack.clintock.ClintockMod;

public class ContaminationPower extends AbstractPower {
    public static final String POWER_ID = "clintock:ContaminationPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ContaminationPower(final AbstractCreature owner) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = -1;
        this.img = ImageMaster.loadImage(ClintockMod.getPowerImagePath(POWER_ID));
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
        this.type = AbstractPower.PowerType.BUFF;
    }
}
