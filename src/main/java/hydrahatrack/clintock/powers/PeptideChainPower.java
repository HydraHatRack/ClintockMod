package hydrahatrack.clintock.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.aminoacids.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PeptideChainPower extends AbstractPower {
    public static final String POWER_ID = "clintock:PeptideChainPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private List<AbstractAminoAcid> aminoAcids = new ArrayList<>();

    public PeptideChainPower(final AbstractCreature owner) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = -1;
        this.img = ImageMaster.loadImage(ClintockMod.getPowerImagePath(POWER_ID));
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

    public boolean containsAtLeastOneEssentialAminoAcid() {
        for (AbstractAminoAcid aminoAcid : aminoAcids) {
            if (aminoAcid instanceof Histidine ||
                aminoAcid instanceof Isoleucine ||
                aminoAcid instanceof Leucine ||
                aminoAcid instanceof Lysine ||
                aminoAcid instanceof Methionine ||
                aminoAcid instanceof Phenylalanine ||
                aminoAcid instanceof Threonine ||
                aminoAcid instanceof Tryptophan ||
                aminoAcid instanceof Valine) {
                return true;
            }
        }
        return false;
    }

    public boolean containsAtLeastOneNonessentialAminoAcid() {
        for (AbstractAminoAcid aminoAcid : aminoAcids) {
            if (aminoAcid instanceof Alanine ||
                aminoAcid instanceof Arginine ||
                aminoAcid instanceof Asparagine ||
                aminoAcid instanceof AsparticAcid ||
                aminoAcid instanceof Cysteine ||
                aminoAcid instanceof GlutamicAcid ||
                aminoAcid instanceof Glutamine ||
                aminoAcid instanceof Glycine ||
                aminoAcid instanceof Proline ||
                aminoAcid instanceof Serine ||
                aminoAcid instanceof Tyrosine) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void atEndOfTurn(final boolean isPlayer) {
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
