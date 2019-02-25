package hydrahatrack.clintock.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import hydrahatrack.clintock.ClintockMod;
import hydrahatrack.clintock.aminoacids.*;
import hydrahatrack.clintock.powers.PeptideChainPower;

public class NTerminusArginine extends CustomRelic {
    public static final String ID = "clintock:NTerminusArginine";
    private static final Texture IMG = ImageMaster.loadImage(ClintockMod.getRelicImagePath(ID));
    private static final Texture OUTLINE = ImageMaster.loadImage(ClintockMod.getRelicOutlineImagePath(ID));

    public NTerminusArginine() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {
        PeptideChainPower power = new PeptideChainPower(AbstractDungeon.player);
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, power));

//        power.bindAminoAcid(new Alanine());
        power.bindAminoAcid(new Arginine());
//        power.bindAminoAcid(new Asparagine());
//        power.bindAminoAcid(new AsparticAcid());
//        power.bindAminoAcid(new Cysteine());
//        power.bindAminoAcid(new GlutamicAcid());
//        power.bindAminoAcid(new Glutamine());
//        power.bindAminoAcid(new Glycine());
//        power.bindAminoAcid(new Histidine());
//        power.bindAminoAcid(new Isoleucine());
//        power.bindAminoAcid(new Leucine());
//        power.bindAminoAcid(new Lysine());
//        power.bindAminoAcid(new Methionine());
//        power.bindAminoAcid(new Phenylalanine());
//        power.bindAminoAcid(new Proline());
//        power.bindAminoAcid(new Serine());
//        power.bindAminoAcid(new StopCodon());
//        power.bindAminoAcid(new Threonine());
//        power.bindAminoAcid(new Tryptophan());
//        power.bindAminoAcid(new Tyrosine());
//        power.bindAminoAcid(new Valine());
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
