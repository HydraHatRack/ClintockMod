package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import hydrahatrack.clintock.aminoacids.*;
import hydrahatrack.clintock.powers.PeptideChainPower;

import java.util.Random;

public class ApplyRandomAminoAcidAction extends AbstractGameAction {
    public ApplyRandomAminoAcidAction() {
        this.duration = 0.0F;
        this.actionType = ActionType.SPECIAL;
    }

    @Override
    public void update() {
        PeptideChainPower power = (PeptideChainPower) AbstractDungeon.player.getPower(PeptideChainPower.POWER_ID);
        if (null == power) {
            power = new PeptideChainPower(AbstractDungeon.player);
            AbstractDungeon.actionManager.addToTop(
                    new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, power));
        }
        AbstractAminoAcid aminoAcid;
        switch (new Random().nextInt(20)) {
            case 0:
                aminoAcid = new Alanine();
                break;
            case 1:
                aminoAcid = new Arginine();
                break;
            case 2:
                aminoAcid = new Asparagine();
                break;
            case 3:
                aminoAcid = new AsparticAcid();
                break;
            case 4:
                aminoAcid = new Cysteine();
                break;
            case 5:
                aminoAcid = new GlutamicAcid();
                break;
            case 6:
                aminoAcid = new Glutamine();
                break;
            case 7:
                aminoAcid = new Glycine();
                break;
            case 8:
                aminoAcid = new Histidine();
                break;
            case 9:
                aminoAcid = new Isoleucine();
                break;
            case 10:
                aminoAcid = new Leucine();
                break;
            case 11:
                aminoAcid = new Lysine();
                break;
            case 12:
                aminoAcid = new Methionine();
                break;
            case 13:
                aminoAcid = new Phenylalanine();
                break;
            case 14:
                aminoAcid = new Proline();
                break;
            case 15:
                aminoAcid = new Serine();
                break;
            case 16:
                aminoAcid = new Threonine();
                break;
            case 17:
                aminoAcid = new Tryptophan();
                break;
            case 18:
                aminoAcid = new Tyrosine();
                break;
            case 19:
            default:
                aminoAcid = new Valine();
                break;
        }
        power.addAminoAcid(aminoAcid);
        this.isDone = true;
    }
}
