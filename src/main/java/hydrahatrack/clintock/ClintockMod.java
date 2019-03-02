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
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import hydrahatrack.clintock.cards.*;
import hydrahatrack.clintock.characters.TheClintock;
import hydrahatrack.clintock.enums.AbstractCardEnum;
import hydrahatrack.clintock.enums.TheClintockEnum;
import hydrahatrack.clintock.powers.NextGenBoostPower;
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
        PostExhaustSubscriber,
        PostBattleSubscriber,
        PostInitializeSubscriber {

    private final Logger logger = LogManager.getLogger(TheClintock.class.getName());

    // Mod metadata
    private static final String MOD_NAME = "The Clintock";
    private static final String AUTHOR = "HydraHatRack";
    private static final String DESCRIPTION = "Adds a new playable character, The Clintock.";

    // In-game messages
    public static final String CANNOT_LINK = "I cannot #rLink right now!";
    public static final String CANNOT_TARGET_ENEMY = "I cannot target that enemy!";
    public static final String NEEDS_MORE_SUGAR = "I need more #rSugar!";
    public static final String NEEDS_MORE_PHOSPHATE = "I need more #rPhosphate!";
    public static final String NEEDS_MORE_RESOURCES = "I need more resources!";
    public static final String NEEDS_PEPTIDE_CHAIN = "I don't have a #rPeptide-Chain yet!";
    public static final String NEEDS_PURINE_NUCLEOBASE = "I need at least one #rPurine!";

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

        BaseMod.addCard(new Acupuncture());
        BaseMod.addCard(new Adenine());
        BaseMod.addCard(new Alkylation());
        BaseMod.addCard(new ATPReserves());
        BaseMod.addCard(new BaseSynthesis());
        BaseMod.addCard(new Bateson9000());
        BaseMod.addCard(new BiolelePrime());
        BaseMod.addCard(new BoloPunch());
        BaseMod.addCard(new BruteForce());
        BaseMod.addCard(new Centrifuge());
        BaseMod.addCard(new Chromosome());
        BaseMod.addCard(new ClintockDefend());
        BaseMod.addCard(new ClintockStrike());
        BaseMod.addCard(new CompostBin());
        BaseMod.addCard(new Contamination());
        BaseMod.addCard(new Cytosine());
        BaseMod.addCard(new DAMP());
        BaseMod.addCard(new DCMP());
        BaseMod.addCard(new DealersLuck());
        BaseMod.addCard(new DegradedPrimer());
        BaseMod.addCard(new Deletion());
        BaseMod.addCard(new Deoxyadenosine());
        BaseMod.addCard(new Deoxycytidine());
        BaseMod.addCard(new Deoxyguanosine());
        BaseMod.addCard(new DGMP());
        BaseMod.addCard(new DnaMicroarray());
        BaseMod.addCard(new DTMP());
        BaseMod.addCard(new Efflux());
        BaseMod.addCard(new EnergyDrink());
        BaseMod.addCard(new EnergyMetabolism());
        BaseMod.addCard(new Epidemic());
        BaseMod.addCard(new Eureka());
        BaseMod.addCard(new Excavate());
        BaseMod.addCard(new Fluorescein());
        BaseMod.addCard(new GeneAmplification());
        BaseMod.addCard(new GeneticPollution());
        BaseMod.addCard(new GeneticsLab());
        BaseMod.addCard(new GlucoseToxicity());
        BaseMod.addCard(new Guanine());
        BaseMod.addCard(new Immunodeficiency());
        BaseMod.addCard(new ImpulsiveShot());
        BaseMod.addCard(new Interrupt());
        BaseMod.addCard(new Intron());
        BaseMod.addCard(new Irradiate());
        BaseMod.addCard(new Jab());
        BaseMod.addCard(new Ketosis());
        BaseMod.addCard(new Knockout());
        BaseMod.addCard(new LifeSager());
        BaseMod.addCard(new MendelPro());
        BaseMod.addCard(new MetabolicPathways());
        BaseMod.addCard(new Miasma());
        BaseMod.addCard(new MineAndDine());
        BaseMod.addCard(new Mitosis());
        BaseMod.addCard(new Nanopore());
        BaseMod.addCard(new NextGenBoost());
        BaseMod.addCard(new Obstruct());
        BaseMod.addCard(new Outbreak());
        BaseMod.addCard(new Polymerize());
        BaseMod.addCard(new ProteinUtilization());
        BaseMod.addCard(new PurineMetabolism());
        BaseMod.addCard(new RandomForest());
        BaseMod.addCard(new Recombination());
        BaseMod.addCard(new Reductase());
        BaseMod.addCard(new Replenish());
        BaseMod.addCard(new SampleSwap());
        BaseMod.addCard(new SinsheimerT3());
        BaseMod.addCard(new Splinter());
        BaseMod.addCard(new Sting());
        BaseMod.addCard(new SuperSeqX1());
        BaseMod.addCard(new SuspendAnimation());
        BaseMod.addCard(new TelomereErosion());
        BaseMod.addCard(new TheGenomeProject());
        BaseMod.addCard(new Thymidine());
        BaseMod.addCard(new Thymine());
        BaseMod.addCard(new VectorFlow());

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

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        if (AbstractDungeon.player instanceof TheClintock) {
            ((TheClintock) AbstractDungeon.player).resetCardExhaustCount();
        }
    }

    @Override
    public void receivePostExhaust(final AbstractCard c) {
        if (AbstractDungeon.player instanceof TheClintock) {
            ((TheClintock) AbstractDungeon.player).incrementCardExhaustCount();
            if (AbstractDungeon.player.hasPower(NextGenBoostPower.POWER_ID) &&
                    null != c &&
                    !c.upgraded &&
                    !(c instanceof DealersLuck) &&
                    !(c instanceof Jab) &&
                    !(c instanceof Miasma) &&
                    !(c instanceof Obstruct) &&
                    !(c instanceof Splinter) &&
                    !(c instanceof Sting) &&
                    !(c instanceof SuspendAnimation) &&
                    AbstractCard.CardType.CURSE != c.type &&
                    AbstractCard.CardType.STATUS != c.type) {
                AbstractCard copy = c.makeCopy();
                copy.upgrade();
                AbstractDungeon.player.getPower(NextGenBoostPower.POWER_ID).flash();
                AbstractDungeon.actionManager.addToBottom(
                        new MakeTempCardInDrawPileAction(copy, 1, true, false));
            }
        }
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
