package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.RemoveAllOrbsAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import hydrahatrack.clintock.aminoacids.*;
import hydrahatrack.clintock.orbs.NucleobaseOrb;
import hydrahatrack.clintock.powers.PeptideChainPower;

public class BindAction extends AbstractGameAction {
    private AbstractOrb orbType;

    public BindAction(AbstractOrb newOrbType) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.orbType = newOrbType;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            boolean foundEmptyOrbSlot = false;
            StringBuilder dnaCodonBuilder = new StringBuilder();

            for (AbstractOrb o : AbstractDungeon.player.orbs) {
                if (o instanceof EmptyOrbSlot) {
                    foundEmptyOrbSlot = true;
                    break;
                } else {
                    dnaCodonBuilder.append(((NucleobaseOrb) o).getLabel());
                }
            }

            AbstractDungeon.player.channelOrb(this.orbType);

            if (!foundEmptyOrbSlot) {
                AbstractDungeon.actionManager.addToTop(new RemoveAllOrbsAction());

                PeptideChainPower power = (PeptideChainPower) AbstractDungeon.player.getPower(PeptideChainPower.POWER_ID);
                if (null == power) {
                    power = new PeptideChainPower(AbstractDungeon.player);
                    AbstractDungeon.actionManager.addToTop(
                            new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, power));
                }

                switch (dnaCodonBuilder.toString()) {
                    case "GCT":
                    case "GCC":
                    case "GCA":
                    case "GCG":
                        power.addAminoAcid(new Alanine()); // DONE
                        break;
                    case "CGT":
                    case "CGC":
                    case "CGA":
                    case "CGG":
                    case "AGA":
                    case "AGG":
                        power.addAminoAcid(new Arginine()); // DONE
                        break;
                    case "AAT":
                    case "AAC":
                        power.addAminoAcid(new Asparagine()); // DONE
                        break;
                    case "GAT":
                    case "GAC":
                        power.addAminoAcid(new AsparticAcid());
                        break;
                    case "TGT":
                    case "TGC":
                        power.addAminoAcid(new Cysteine()); // DONE
                        break;
                    case "GAA":
                    case "GAG":
                        power.addAminoAcid(new GlutamicAcid()); // DONE
                        break;
                    case "CAA":
                    case "CAG":
                        power.addAminoAcid(new Glutamine()); // DONE
                        break;
                    case "GGT":
                    case "GGC":
                    case "GGA":
                    case "GGG":
                        power.addAminoAcid(new Glycine()); // DONE
                        break;
                    case "CAT":
                    case "CAC":
                        power.addAminoAcid(new Histidine()); // DONE
                        break;
                    case "ATT":
                    case "ATC":
                    case "ATA":
                        power.addAminoAcid(new Isoleucine()); // DONE
                        break;
                    case "CTT":
                    case "CTC":
                    case "CTA":
                    case "CTG":
                    case "TTA":
                    case "TTG":
                        power.addAminoAcid(new Leucine()); // DONE
                        break;
                    case "AAA":
                    case "AAG":
                        power.addAminoAcid(new Lysine()); // DONE
                        break;
                    case "ATG":
                        power.addAminoAcid(new Methionine()); // DONE
                        break;
                    case "TTT":
                    case "TTC":
                        power.addAminoAcid(new Phenylalanine()); // DONE
                        break;
                    case "CCT":
                    case "CCC":
                    case "CCA":
                    case "CCG":
                        power.addAminoAcid(new Proline()); // DONE
                        break;
                    case "TCT":
                    case "TCC":
                    case "TCA":
                    case "TCG":
                    case "AGT":
                    case "AGC":
                        power.addAminoAcid(new Serine()); // DONE
                        break;
                    case "TAA":
                    case "TAG":
                    case "TGA":
                        power.addAminoAcid(new StopCodon());
                        break;
                    case "ACT":
                    case "ACC":
                    case "ACA":
                    case "ACG":
                        power.addAminoAcid(new Threonine()); // DONE
                        break;
                    case "TGG":
                        power.addAminoAcid(new Tryptophan()); // DONE
                        break;
                    case "TAT":
                    case "TAC":
                        power.addAminoAcid(new Tyrosine()); // DONE
                        break;
                    case "GTT":
                    case "GTC":
                    case "GTA":
                    case "GTG":
                        power.addAminoAcid(new Valine()); // DONE
                        break;
                    default:
                        System.out.println("Unexpected nucleotide chain!");
                }
            }

            if (Settings.FAST_MODE) {
                this.isDone = true;
                return;
            }
        }

        this.tickDuration();
    }
}
