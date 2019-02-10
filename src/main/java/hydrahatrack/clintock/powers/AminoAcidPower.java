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
        PeptideChainPower power = (PeptideChainPower)AbstractDungeon.player.getPower(PeptideChainPower.POWER_ID);
        if (null == power) {
            power = new PeptideChainPower(AbstractDungeon.player);
            AbstractDungeon.actionManager.addToTop(
                    new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, power));
        }
        Random random = new Random();
        AbstractAminoAcid aminoAcid;
        switch (random.nextInt(20)) {
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
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
