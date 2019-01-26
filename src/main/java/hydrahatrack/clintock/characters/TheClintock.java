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
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import hydrahatrack.clintock.cards.ClintockDefend;
import hydrahatrack.clintock.cards.ClintockStrike;
import hydrahatrack.clintock.cards.GeneDrive;
import hydrahatrack.clintock.enums.AbstractCardEnum;
import hydrahatrack.clintock.enums.TheClintockEnum;
import hydrahatrack.clintock.relics.NTerminusArginine;

import java.util.ArrayList;

public class TheClintock extends CustomPlayer {
    private static final int ENERGY_PER_TURN = 3;

    private static final String CLINTOCK_SKELETON_ATLAS_PATH = "img/characters/clintock/idle/skeleton.atlas";
    private static final String CLINTOCK_SKELETON_JSON_PATH = "img/characters/clintock/idle/skeleton.json";
    private static final String CLINTOCK_SHOULDER_1 = "img/characters/clintock/shoulder.png";
    private static final String CLINTOCK_SHOULDER_2 = "img/characters/clintock/shoulder2.png";
    private static final String CLINTOCK_CORPSE = "img/characters/clintock/corpse.png";

    public TheClintock(String name, PlayerClass setClass) {
        super(name, setClass, null, null, (String) null, null);

        initializeClass(null, CLINTOCK_SHOULDER_2, CLINTOCK_SHOULDER_1, CLINTOCK_CORPSE,
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

        this.loadAnimation(CLINTOCK_SKELETON_ATLAS_PATH, CLINTOCK_SKELETON_JSON_PATH, 1.0f);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
        e.setTime(e.getEndTime() * MathUtils.random());
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
        startingDeck.add(ClintockStrike.ID);
        startingDeck.add(ClintockDefend.ID);

        return startingDeck;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> startingRelics = new ArrayList<>();
        startingRelics.add(NTerminusArginine.ID);
        UnlockTracker.markRelicAsSeen(NTerminusArginine.ID);
        return startingRelics;
    }

    public CharSelectInfo getLoadout() {
        return new CharSelectInfo("The Clintock",
                "A genetic engineer who got tired of CRISPR getting all the credit in TV shows and movies. NL" +
                        "She wields the tools of biology to slay the parasites of the spire.",
                75, 75, 0, 99, 5,
                this, getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return "The Clintock";
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return AbstractCardEnum.CLINTOCK_COLOR;
    }

    @Override
    public Color getCardRenderColor() {
        return Color.PURPLE;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new GeneDrive();
    }

    @Override
    public Color getCardTrailColor() {
        return Color.PURPLE;
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
        CardCrawlGame.sound.playA("ATTACK_HEAVY", MathUtils.random(-0.2f, 0.2f));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_HEAVY";
    }

    @Override
    public String getLocalizedCharacterName() {
        return "The Clintock";
    }

    @Override
    public AbstractPlayer newInstance() {
        return new TheClintock("The Clintock", TheClintockEnum.CLINTOCK_CLASS);
    }

    @Override
    public String getSpireHeartText() {
        return "NL You ready your scalpel...";
    }

    @Override
    public Color getSlashAttackColor() {
        return Color.PURPLE;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.BLUNT_HEAVY};
    }

    @Override
    public String getVampireText() {
        return "Navigating an unlit street, you come across several hooded figures in the midst of some dark ritual. As you approach, they turn to you in eerie unison. The tallest among them bares fanged teeth and extends a long, pale hand towards you. NL ~\"Join~ ~us,~ ~oh~ ~Great~ ~Clintock,~ ~and~ ~feel~ ~the~ ~warmth~ ~of~ ~the~ ~Spire.\"~";
    }
}
