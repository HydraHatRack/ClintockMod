package hydrahatrack.clintock.characters;

import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import hydrahatrack.clintock.cards.*;
import hydrahatrack.clintock.enums.AbstractCardEnum;
import hydrahatrack.clintock.enums.TheClintockEnum;
import hydrahatrack.clintock.relics.BiomoleculePool;

import java.util.ArrayList;

public class TheClintock extends CustomPlayer {
    private static final int ENERGY_PER_TURN = 3;
    private static final String CLINTOCK_NAME = "The Clintock";
    private static final Color CLINTOCK_COLOR = CardHelper.getColor(108.0f, 48.0f, 130.0f);

    private static final String CLINTOCK_SKELETON_ATLAS_PATH = "img/characters/clintock/idle/skeleton.atlas";
    private static final String CLINTOCK_SKELETON_JSON_PATH = "img/characters/clintock/idle/skeleton.json";
    private static final String CLINTOCK_SHOULDER = "img/characters/clintock/shoulder.png";
    private static final String CLINTOCK_CORPSE = "img/characters/clintock/corpse.png";

    public float[] orbPositionsX = {0, 0, 0};
    public float[] orbPositionsY = {0, 0, 0};

    private int cardExhaustCount;

    public TheClintock(String name, PlayerClass setClass) {
        super(name, setClass, null, null, (String) null, null);

        initializeClass(null, CLINTOCK_SHOULDER, CLINTOCK_SHOULDER, CLINTOCK_CORPSE,
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

        this.loadAnimation(CLINTOCK_SKELETON_ATLAS_PATH, CLINTOCK_SKELETON_JSON_PATH, 1.0f);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
        e.setTime(e.getEndTime() * MathUtils.random());

        // Initialize slot positions
        float xStartOffset = (float)Settings.WIDTH * 0.22F;
        float yStartOffset = AbstractDungeon.floorY + (100 * Settings.scale) + 200;
        float xSpaceBetweenSlots = 90 * Settings.scale;

        orbPositionsX[0] = xStartOffset;
        orbPositionsX[1] = xStartOffset + (xSpaceBetweenSlots * 1);
        orbPositionsX[2] = xStartOffset + (xSpaceBetweenSlots * 2);

        orbPositionsY[0] = yStartOffset;
        orbPositionsY[1] = yStartOffset;
        orbPositionsY[2] = yStartOffset;

        resetCardExhaustCount();
    }

    public ArrayList<String> getStartingDeck() {
        ArrayList<String> startingDeck = new ArrayList<>();

        startingDeck.add(ClintockStrike.ID);
        startingDeck.add(ClintockStrike.ID);
        startingDeck.add(ClintockStrike.ID);
        startingDeck.add(ClintockStrike.ID);
        startingDeck.add(ClintockDefend.ID);
        startingDeck.add(ClintockDefend.ID);
        startingDeck.add(ClintockDefend.ID);
        startingDeck.add(ClintockDefend.ID);
        startingDeck.add(BaseSynthesis.ID);
        startingDeck.add(Replenish.ID);

        return startingDeck;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> startingRelics = new ArrayList<>();
        startingRelics.add(BiomoleculePool.ID);
        UnlockTracker.markRelicAsSeen(BiomoleculePool.ID);
        return startingRelics;
    }

    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(CLINTOCK_NAME,
                "A genetic engineer tired of CRISPR getting all the credit in movies. NL " +
                        "Wields the tools of biology to slay the parasites of the spire.",
                80, 80, 3, 99, 5,
                this, getStartingRelics(), getStartingDeck(), false);
    }

    public void resetCardExhaustCount() {
        cardExhaustCount = 0;
    }

    public void incrementCardExhaustCount() {
        cardExhaustCount++;
    }

    public int getCardExhaustCount() {
        return cardExhaustCount;
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return CLINTOCK_NAME;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return AbstractCardEnum.CLINTOCK;
    }

    @Override
    public Color getCardRenderColor() {
        return CLINTOCK_COLOR;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new BaseSynthesis();
    }

    @Override
    public Color getCardTrailColor() {
        return CLINTOCK_COLOR;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 4;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("POTION_DROP_2", MathUtils.random(-0.2f, 0.2f));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "POTION_DROP_2";
    }

    @Override
    public String getLocalizedCharacterName() {
        return CLINTOCK_NAME;
    }

    @Override
    public AbstractPlayer newInstance() {
        return new TheClintock(CLINTOCK_NAME, TheClintockEnum.CLINTOCK_CLASS);
    }

    @Override
    public String getSpireHeartText() {
        return "NL You ready your pipette...";
    }

    @Override
    public Color getSlashAttackColor() {
        return CLINTOCK_COLOR;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.SLASH_HEAVY,
                AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY};
    }

    @Override
    public String getVampireText() {
        return "Navigating an unlit street, you come across several hooded figures in the midst of some dark ritual. " +
                "As you approach, they turn to you in eerie unison. The tallest among them bares fanged teeth and" +
                " extends a long, pale hand towards you. NL ~\"Join~ ~us,~ ~oh~ ~Great~ ~Clintock,~ ~and~ ~feel~" +
                " ~the~ ~warmth~ ~of~ ~the~ ~Spire.\"~";
    }
}
