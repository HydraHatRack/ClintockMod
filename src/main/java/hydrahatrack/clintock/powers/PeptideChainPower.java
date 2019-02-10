package hydrahatrack.clintock.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.aminoacids.AbstractAminoAcid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PeptideChainPower extends AbstractPower {
    public static final String POWER_ID = "clintock:PeptideChainPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture IMG = ImageMaster.loadImage(ClintockMod.getPowerImagePath(POWER_ID));

    private List<AbstractAminoAcid> aminoAcids = new ArrayList<>();

    public PeptideChainPower(AbstractCreature owner) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = -1;
        this.img = IMG;
        this.updateDescription();
    }

    public void addAminoAcid(final AbstractAminoAcid aminoAcid) {
        aminoAcids.add(aminoAcid);
        this.amount = aminoAcids.size();
        this.updateDescription();
    }

    public void doubleAminoAcidList() {
        aminoAcids = Stream.concat(aminoAcids.stream(), aminoAcids.stream())
                .collect(Collectors.toList());
        this.amount = aminoAcids.size();
        this.updateDescription();
    }

    public void tripleAminoAcidList() {
        aminoAcids = Stream.concat(Stream.concat(aminoAcids.stream(), aminoAcids.stream()), aminoAcids.stream())
                .collect(Collectors.toList());
        this.amount = aminoAcids.size();
        this.updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (this.amount > 0) {
            this.flash();
            for (AbstractAminoAcid aminoAcid : aminoAcids) {
                AbstractDungeon.actionManager.addToBottom(aminoAcid.getAction());
            }
        }
    }

    @Override
    public void updateDescription() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(DESCRIPTIONS[0]);
        for (AbstractAminoAcid aminoAcid : aminoAcids) {
            stringBuilder.append(aminoAcid.getLabel());
        }
        this.description = stringBuilder.toString();
    }
}
