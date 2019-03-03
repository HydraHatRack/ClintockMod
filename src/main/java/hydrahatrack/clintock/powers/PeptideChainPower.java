package hydrahatrack.clintock.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
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

    public void bindAminoAcid(final AbstractAminoAcid aminoAcid) {
        AbstractDungeon.actionManager.addToBottom(
                new TextAboveCreatureAction(AbstractDungeon.player, aminoAcid.getLabel()));
        aminoAcids.add(aminoAcid);

        Bateson9000Power bateson9000Power =
                (Bateson9000Power) AbstractDungeon.player.getPower(Bateson9000Power.POWER_ID);
        if (null != bateson9000Power) {
            bateson9000Power.flash();
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(
                    AbstractDungeon.player, AbstractDungeon.player, bateson9000Power.amount));
        }

        BiolelePrimePower biolelePrimePower =
                (BiolelePrimePower) AbstractDungeon.player.getPower(BiolelePrimePower.POWER_ID);
        if (null != biolelePrimePower) {
            for (int i = 0; i < biolelePrimePower.amount; i++) {
                biolelePrimePower.flash();
                AbstractDungeon.actionManager.addToBottom(
                        new TextAboveCreatureAction(AbstractDungeon.player, aminoAcid.getLabel()));
                aminoAcids.add(aminoAcid);
            }
        }

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

    public int getNumberOfAminoAcids() {
        return aminoAcids.size();
    }

    public boolean containsStopCodon() {
        for (AbstractAminoAcid aminoAcid : aminoAcids) {
            if (aminoAcid instanceof StopCodon) {
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
        for (int i = 0; i < Math.min(aminoAcids.size(), 10); i++) {
            stringBuilder.append(aminoAcids.get(i).getDescription());
        }
        if (aminoAcids.size() > 10) {
            stringBuilder.append(" NL ...and ").append(aminoAcids.size() - 10).append(" more.");
        }
        this.description = stringBuilder.toString();
    }
}
