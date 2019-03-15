package hydrahatrack.clintock.ui;

import basemod.BaseMod;
import basemod.interfaces.OnStartBattleSubscriber;
import basemod.interfaces.PostBattleSubscriber;
import basemod.interfaces.PostUpdateSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import hydrahatrack.clintock.aminoacids.*;
import hydrahatrack.clintock.characters.TheClintock;
import hydrahatrack.clintock.orbs.AdenineOrb;
import hydrahatrack.clintock.orbs.CytosineOrb;
import hydrahatrack.clintock.orbs.GuanineOrb;
import hydrahatrack.clintock.orbs.ThymineOrb;

import java.util.regex.Pattern;

public class AminoAcidGuide implements
        OnStartBattleSubscriber,
        PostBattleSubscriber,
        PostUpdateSubscriber {
    public static int toggleKey = Input.Keys.W;
    private final AminoAcidGuidePanel panel;
    private BitmapFont font;
    private boolean canShow = false;
    private boolean showing = false;
    private boolean loaded = false;

    public AminoAcidGuide() {
        BaseMod.subscribe(this);
        panel = new AminoAcidGuidePanel();
    }

    private static BitmapFont prepareFont() {
        final float SIZE = 13.0F;

        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        FileHandle fontFile = Gdx.files.internal("font/Kreon-Bold.ttf");
        FreeTypeFontGenerator g = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();

        param.gamma = 1.2F;
        param.borderWidth = 1.0F;
        param.shadowOffsetX = 0;
        param.shadowOffsetY = 0;
        if (Settings.WIDTH >= 1600) {
            param.spaceX = -1;
        }

        p.characters = "";
        p.incremental = true;
        p.size = Math.round(SIZE * Settings.scale);
        p.gamma = param.gamma;
        p.spaceX = param.spaceX;
        p.spaceY = param.spaceY;
        p.borderColor = param.borderColor;
        p.borderStraight = param.borderStraight;
        p.borderWidth = param.borderWidth;
        p.borderGamma = param.borderGamma;
        p.shadowColor = param.shadowColor;
        p.shadowOffsetX = param.shadowOffsetX;
        p.shadowOffsetY = param.shadowOffsetY;
        p.minFilter = Texture.TextureFilter.Linear;
        p.magFilter = Texture.TextureFilter.Linear;

        g.scaleForPixelHeight(p.size);
        BitmapFont font = g.generateFont(p);
        font.setUseIntegerPositions(false);
        font.getData().markupEnabled = true;
        if (LocalizedStrings.break_chars != null) {
            font.getData().breakChars = LocalizedStrings.break_chars.toCharArray();
        }

        return font;
    }

    public void render(final SpriteBatch sb) {
        try {
            if (!loaded) {
                font = prepareFont();
                loaded = true;
            }
            panel.render(sb);
            renderText(sb);
            CardCrawlGame.cursor.render(sb);
        } catch (final Exception ex) {
            hide();
            System.out.println("An error occurred while rendering the Amino Acid Guide:");
            ex.printStackTrace();
        }
    }

    private void renderText(final SpriteBatch sb) {
        renderAminoAcidText(sb, new Alanine(), 800 * Settings.scale, 915 * Settings.scale);
        renderAminoAcidText(sb, new Arginine(), 800 * Settings.scale, 865 * Settings.scale);
        renderAminoAcidText(sb, new Asparagine(), 800 * Settings.scale, 815 * Settings.scale);
        renderAminoAcidText(sb, new AsparticAcid(), 800 * Settings.scale, 765 * Settings.scale);
        renderAminoAcidText(sb, new Cysteine(), 800 * Settings.scale, 715 * Settings.scale);
        renderAminoAcidText(sb, new GlutamicAcid(), 800 * Settings.scale, 665 * Settings.scale);
        renderAminoAcidText(sb, new Glutamine(), 800 * Settings.scale, 615 * Settings.scale);
        renderAminoAcidText(sb, new Glycine(), 1120 * Settings.scale, 915 * Settings.scale);
        renderAminoAcidText(sb, new Histidine(), 1120 * Settings.scale, 865 * Settings.scale);
        renderAminoAcidText(sb, new Isoleucine(), 1120 * Settings.scale, 815 * Settings.scale);
        renderAminoAcidText(sb, new Leucine(), 1120 * Settings.scale, 765 * Settings.scale);
        renderAminoAcidText(sb, new Lysine(), 1120 * Settings.scale, 715 * Settings.scale);
        renderAminoAcidText(sb, new Methionine(), 1120 * Settings.scale, 665 * Settings.scale);
        renderAminoAcidText(sb, new Phenylalanine(), 1120 * Settings.scale, 615 * Settings.scale);
        renderAminoAcidText(sb, new Proline(), 1500 * Settings.scale, 915 * Settings.scale);
        renderAminoAcidText(sb, new Serine(), 1500 * Settings.scale, 865 * Settings.scale);
        renderAminoAcidText(sb, new Threonine(), 1500 * Settings.scale, 815 * Settings.scale);
        renderAminoAcidText(sb, new Tryptophan(), 1500 * Settings.scale, 765 * Settings.scale);
        renderAminoAcidText(sb, new Tyrosine(), 1500 * Settings.scale, 715 * Settings.scale);
        renderAminoAcidText(sb, new Valine(), 1500 * Settings.scale, 665 * Settings.scale);
    }

    private void renderAminoAcidText(final SpriteBatch sb, final AbstractAminoAcid aa, final float x, final float y) {
        FontHelper.renderSmartText(sb, font, getAminoAcidText(aa), x, y, getAminoAcidTextColor(aa));
    }

    private String getAminoAcidText(final AbstractAminoAcid aa) {
        Boolean canBeCreated = canAminoAcidBeCreated(aa);
        StringBuilder aminoAcidText = new StringBuilder();
        if (canBeCreated) {
            aminoAcidText.append("#y").append(aa.getName().replace(" ", " #y"));
        } else {
            aminoAcidText.append(aa.getName());
        }
        aminoAcidText.append(" - ").append(aa.getDnaCodons()[0]);
        for (int i = 1; i < aa.getDnaCodons().length; i++) {
            aminoAcidText.append(", ").append(aa.getDnaCodons()[i]);
        }
        aminoAcidText.append(" NL ");
        if (canBeCreated) {
            aminoAcidText.append(aa.getDescription());
        } else {
            aminoAcidText.append(aa.getDescription().replace("#b", "").replace("#y", ""));
        }
        aminoAcidText.append(" NL NL ");
        return aminoAcidText.toString();
    }

    private Color getAminoAcidTextColor(final AbstractAminoAcid aa) {
        if (canAminoAcidBeCreated(aa)) {
            return Color.WHITE;
        }
        return Color.GRAY;
    }

    private Boolean canAminoAcidBeCreated(final AbstractAminoAcid aa) {
        StringBuilder codon = new StringBuilder();
        for (AbstractOrb orb : AbstractDungeon.player.orbs) {
            if (orb instanceof AdenineOrb) {
                codon.append(AdenineOrb.LABEL);
            } else if (orb instanceof CytosineOrb) {
                codon.append(CytosineOrb.LABEL);
            } else if (orb instanceof GuanineOrb) {
                codon.append(GuanineOrb.LABEL);
            } else if (orb instanceof ThymineOrb) {
                codon.append(ThymineOrb.LABEL);
            }
        }

        Pattern codonPattern = Pattern.compile(codon.toString() + ".*");
        for (String possibleCodon : aa.getDnaCodons()) {
            if (codonPattern.matcher(possibleCodon).matches()) {
                return true;
            }
        }

        return false;
    }

    void show() {
        if (AbstractDungeon.player instanceof TheClintock) {
            CardCrawlGame.sound.play("POTION_DROP_2", 0.05F);
            showing = true;
        }
    }

    void hide() {
        if (AbstractDungeon.player instanceof TheClintock && showing) {
            CardCrawlGame.sound.play("POTION_DROP_2", 0.05F);
            showing = false;
        }
    }

    public boolean isShowing() {
        return showing;
    }

    public boolean canShow() {
        return canShow;
    }

    @Override
    public void receiveOnBattleStart(final AbstractRoom r) {
        canShow = true;
    }

    @Override
    public void receivePostBattle(final AbstractRoom r) {
        hide();
        canShow = false;
    }

    @Override
    public void receivePostUpdate() {
        try {
            if (Gdx.input.isKeyJustPressed(toggleKey)) {
                if (isShowing()) {
                    hide();
                } else if (canShow()) {
                    show();
                }
            }
        } catch (Exception ex) {
            System.out.println("An error occurred after the toggle key was pressed:");
            ex.printStackTrace();
        }
    }
}
