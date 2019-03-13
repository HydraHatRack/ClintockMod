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
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import hydrahatrack.clintock.aminoacids.*;

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
        String firstColumn = renderAminoAcidText(new Alanine()) +
                renderAminoAcidText(new Arginine()) +
                renderAminoAcidText(new Asparagine()) +
                renderAminoAcidText(new AsparticAcid()) +
                renderAminoAcidText(new Cysteine()) +
                renderAminoAcidText(new GlutamicAcid()) +
                renderAminoAcidText(new Glutamine());
        FontHelper.renderSmartText(
                sb,
                font,
                firstColumn,
                800 * Settings.scale,
                915 * Settings.scale,
                Color.WHITE
        );

        String secondColumn = renderAminoAcidText(new Glycine()) +
                renderAminoAcidText(new Histidine()) +
                renderAminoAcidText(new Isoleucine()) +
                renderAminoAcidText(new Leucine()) +
                renderAminoAcidText(new Lysine()) +
                renderAminoAcidText(new Methionine()) +
                renderAminoAcidText(new Phenylalanine());
        FontHelper.renderSmartText(
                sb,
                font,
                secondColumn,
                1120 * Settings.scale,
                915 * Settings.scale,
                Color.WHITE
        );

        String thirdColumn = renderAminoAcidText(new Proline()) +
                renderAminoAcidText(new Serine()) +
                renderAminoAcidText(new Threonine()) +
                renderAminoAcidText(new Tryptophan()) +
                renderAminoAcidText(new Tyrosine()) +
                renderAminoAcidText(new Valine());
        FontHelper.renderSmartText(
                sb,
                font,
                thirdColumn,
                1500 * Settings.scale,
                915 * Settings.scale,
                Color.WHITE
        );
    }

    private String renderAminoAcidText(final AbstractAminoAcid aa) {
        StringBuilder aminoAcidText = new StringBuilder();
        aminoAcidText.append("#y").append(aa.getName().replace(" ", " #y")).append(" - ");
        aminoAcidText.append(aa.getDnaCodons()[0]);
        for (int i = 1; i < aa.getDnaCodons().length; i++) {
            aminoAcidText.append(", ").append(aa.getDnaCodons()[i]);
        }
        aminoAcidText.append(" NL ");
        aminoAcidText.append(aa.getDescription());
        aminoAcidText.append(" NL NL ");
        return aminoAcidText.toString();
    }

    void show() {
        CardCrawlGame.sound.play("POTION_DROP_2", 0.05F);
        showing = true;
    }

    void hide() {
        CardCrawlGame.sound.play("POTION_DROP_2", 0.05F);
        showing = false;
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
