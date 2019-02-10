package hydrahatrack.clintock;

import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.*;
import hydrahatrack.clintock.cards.*;
import hydrahatrack.clintock.characters.TheClintock;
import hydrahatrack.clintock.enums.AbstractCardEnum;
import hydrahatrack.clintock.enums.TheClintockEnum;
import hydrahatrack.clintock.relics.BiomoleculePool;
import hydrahatrack.clintock.relics.NTerminusArginine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@SpireInitializer
public class ClintockMod implements
        EditCardsSubscriber,
        EditCharactersSubscriber,
        EditKeywordsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        PostInitializeSubscriber {

    private final Logger logger = LogManager.getLogger(TheClintock.class.getName());

    // Mod metadata
    private static final String MOD_NAME = "The Clintock";
    private static final String AUTHOR = "HydraHatRack";
    private static final String DESCRIPTION = "Adds a new playable character, The Clintock.";

    // In-game messages
    public static final String NEEDS_MORE_SUGAR = "I need more sugar!";
    public static final String NEEDS_MORE_PHOSPHATE = "I need more phosphate!";
    public static final String NEEDS_MORE_RESOURCES = "I need more resources!";
    public static final String NEEDS_PEPTIDE_CHAIN = "I don't have a peptide chain yet!";
    public static final String NEEDS_PURINE_NUCLEOBASE = "I need at least one purine!";

    // Color theme
    private static final Color CLINTOCK_THEME_COLOR = CardHelper.getColor(108.0f, 48.0f, 130.0f);

    // Asset paths
    private static final String CLINTOCK_ATTACK_BACKGROUND = "img/512/attackCardBackground.png";
    private static final String CLINTOCK_SKILL_BACKGROUND = "img/512/skillCardBackground.png";
    private static final String CLINTOCK_POWER_BACKGROUND = "img/512/powerCardBackground.png";
    private static final String CLINTOCK_ENERGY_ORB = "img/512/energyOrb.png";
    private static final String CLINTOCK_ATTACK_PORTRAIT_BACKGROUND = "img/1024/attackCardBackground.png";
    private static final String CLINTOCK_SKILL_PORTRAIT_BACKGROUND = "img/1024/skillCardBackground.png";
    private static final String CLINTOCK_POWER_PORTRAIT_BACKGROUND = "img/1024/powerCardBackground.png";
    private static final String CLINTOCK_ENERGY_ORB_PORTRAIT = "img/1024/energyOrb.png";
    private static final String CLINTOCK_ENERGY_ORB_IN_DESCRIPTION = "img/energy/energyOrbInDescription.png";
    private static final String CLINTOCK_BUTTON = "img/characters/clintock/characterSelectButton.png";
    private static final String CLINTOCK_PORTRAIT = "img/characters/clintock/portrait.jpg";
    private static final String CLINTOCK_BADGE = "img/ModBadge.png";

    // Localization strings
    private static final String CARD_STRINGS_PATH = "localization/TheClintock-CardStrings.json";
    private static final String KEYWORD_STRINGS_PATH = "localization/TheClintock-KeywordStrings.json";
    private static final String ORB_STRINGS_PATH = "localization/TheClintock-OrbStrings.json";
    private static final String POWER_STRINGS_PATH = "localization/TheClintock-PowerStrings.json";
    private static final String RELIC_STRINGS_PATH = "localization/TheClintock-RelicStrings.json";

    public ClintockMod() {
        BaseMod.subscribe(this);

        logger.info("Creating the color " + AbstractCardEnum.CLINTOCK_COLOR.toString());

        BaseMod.addColor(AbstractCardEnum.CLINTOCK_COLOR,
                CLINTOCK_THEME_COLOR,
                CLINTOCK_ATTACK_BACKGROUND,
                CLINTOCK_SKILL_BACKGROUND,
                CLINTOCK_POWER_BACKGROUND,
                CLINTOCK_ENERGY_ORB,
                CLINTOCK_ATTACK_PORTRAIT_BACKGROUND,
                CLINTOCK_SKILL_PORTRAIT_BACKGROUND,
                CLINTOCK_POWER_PORTRAIT_BACKGROUND,
                CLINTOCK_ENERGY_ORB_PORTRAIT,
                CLINTOCK_ENERGY_ORB_IN_DESCRIPTION
        );
    }

    public static void initialize() {
        new ClintockMod();
    }

    @Override
    public void receivePostInitialize() {
        logger.info("Begin setting up post-initialization");

        // Add mod badge
        Texture badgeTexture = ImageMaster.loadImage(CLINTOCK_BADGE);
        ModPanel settingsPanel = new ModPanel();
        BaseMod.registerModBadge(badgeTexture, MOD_NAME, AUTHOR, DESCRIPTION, settingsPanel);

        logger.info("Done setting up post-initialization");
    }

    @Override
    public void receiveEditCards() {
        logger.info("Begin editing cards");

        BaseMod.addCard(new A1());
        BaseMod.addCard(new A2());
        BaseMod.addCard(new A3());
        BaseMod.addCard(new A4());
        BaseMod.addCard(new Acupuncture());
        BaseMod.addCard(new Adenine());
        BaseMod.addCard(new ClintockDefend());
        BaseMod.addCard(new ClintockStrike());
        BaseMod.addCard(new Cytosine());
        BaseMod.addCard(new Deoxyadenosine());
        BaseMod.addCard(new Deoxycytidine());
        BaseMod.addCard(new Deoxyguanosine());
        BaseMod.addCard(new DnaMicroarray());
        BaseMod.addCard(new EnergyDrink());
        BaseMod.addCard(new Excavate());
        BaseMod.addCard(new Fluorescein());
        BaseMod.addCard(new GeneAmplification());
        BaseMod.addCard(new Guanine());
        BaseMod.addCard(new Ketosis());
        BaseMod.addCard(new MonomerSynthesis());
        BaseMod.addCard(new Nanopore());
        BaseMod.addCard(new Polymerize());
        BaseMod.addCard(new PurineMetabolism());
        BaseMod.addCard(new Reductase());
        BaseMod.addCard(new Replenish());
        BaseMod.addCard(new Thymidine());
        BaseMod.addCard(new Thymine());

        logger.info("Done editing cards");
    }

    @Override
    public void receiveEditCharacters() {
        logger.info("Begin editing characters");

        BaseMod.addCharacter(
                new TheClintock(MOD_NAME, TheClintockEnum.CLINTOCK_CLASS),
                CLINTOCK_BUTTON,
                CLINTOCK_PORTRAIT,
                TheClintockEnum.CLINTOCK_CLASS
        );

        logger.info("Done editing characters");
    }

    @Override
    public void receiveEditKeywords() {
        logger.info("Begin editing keywords");

        Gson gson = new Gson();

        String keywordStrings = Gdx.files.internal(KEYWORD_STRINGS_PATH)
                .readString(String.valueOf(StandardCharsets.UTF_8));
        Type typeToken = new TypeToken<Map<String, Keyword>>() {}.getType();

        Map<String, Keyword> keywords = gson.fromJson(keywordStrings, typeToken);
        keywords.forEach((k, v) -> BaseMod.addKeyword(v.NAMES, v.DESCRIPTION));

        logger.info("Done editing keywords");
    }

    @Override
    public void receiveEditRelics() {
        logger.info("Begin editing relics");

        BaseMod.addRelicToCustomPool(new BiomoleculePool(), AbstractCardEnum.CLINTOCK_COLOR);
        BaseMod.addRelicToCustomPool(new NTerminusArginine(), AbstractCardEnum.CLINTOCK_COLOR);

        logger.info("Done editing relics");
    }

    @Override
    public void receiveEditStrings() {
        logger.info("Begin editing strings");

        BaseMod.loadCustomStringsFile(CardStrings.class, CARD_STRINGS_PATH);
        BaseMod.loadCustomStringsFile(OrbStrings.class, ORB_STRINGS_PATH);
        BaseMod.loadCustomStringsFile(PowerStrings.class, POWER_STRINGS_PATH);
        BaseMod.loadCustomStringsFile(RelicStrings.class, RELIC_STRINGS_PATH);

        logger.info("Done editing strings");
    }

    public static String getCardImagePath(final String cardID) {
        return "img/cards/" + cardID.replaceFirst("clintock:", "") + ".png";
    }

    public static String getOrbImagePath(final String orbID) {
        return "img/orbs/" + orbID.replaceFirst("clintock:", "") + ".png";
    }

    public static String getPowerImagePath(final String powerID) {
        return "img/powers/" + powerID.replaceFirst("clintock:", "") + ".png";
    }

    public static String getRelicImagePath(final String relicID) {
        return "img/relics/" + relicID.replaceFirst("clintock:", "") + ".png";
    }

    public static String getRelicOutlineImagePath(final String relicID) {
        return "img/relics/outlines/" + relicID.replaceFirst("clintock:", "") + ".png";
    }
}

// ERRORS

// Happened at end of battle
//05:08:36.433 INFO helpers.File> Attempting to save file=C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\preferences\CLINTOCK_CLASS
//        05:08:36.434 ERROR core.CardCrawlGame> Exception caught
//        java.lang.NullPointerException: null
//        at com.megacrit.cardcrawl.actions.GameActionManager.clearPostCombatActions(GameActionManager.java:120) ~[?:?]
//        at com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction.update(DamageRandomEnemyAction.java:50) ~[desktop-1.0.jar:?]
//        at com.megacrit.cardcrawl.actions.GameActionManager.update(GameActionManager.java:155) ~[?:?]
//        at com.megacrit.cardcrawl.rooms.AbstractRoom.update(AbstractRoom.java:279) ~[?:?]
//        at com.megacrit.cardcrawl.dungeons.AbstractDungeon.update(AbstractDungeon.java:2552) ~[?:?]
//        at com.megacrit.cardcrawl.core.CardCrawlGame.update(CardCrawlGame.java:878) ~[?:?]
//        at com.megacrit.cardcrawl.core.CardCrawlGame.render(CardCrawlGame.java:429) [?:?]
//        at com.badlogic.gdx.backends.lwjgl.LwjglApplication.mainLoop(LwjglApplication.java:225) [?:?]
//        at com.badlogic.gdx.backends.lwjgl.LwjglApplication$1.run(LwjglApplication.java:126) [desktop-1.0.jar:?]
//        Controllers: removed manager for application, 0 managers active
