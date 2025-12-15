package world;

import collectibles.Item;
import controller.Choices.Choice;
import controller.Choices.RaceRequirement;
import controller.GameController;
import creatures.NPC;
import creatures.Player;
import creatures.attributes.Stats;
import util.TextUI;
import world.Location;

import java.util.*;

public class Story {
    TextUI ui = new TextUI();
    // Make map for connecting locations
    private Map<String, Location> locations;
    GameController gc = new GameController();

    // Make each Location accessable individually
    private Location theClearing;
    private Location huntingCabin;
    private Location theLake;
    private Location theForestPath;
    private Location theTravellingMerchant;
    private Location theSwampPath;
    private Location theMurkyWaters;
    private Location theWitchHut;
    private Location theOfferingBog;
    private Location theFrozenBog;
    private Location theMountainPath;
    private Location theRootsOfTheMountain;
    private Location theFreezingPass;
    private Location theCave;
    private Location theCrownOfTheWorld;

    private Map<String, NPC> npcs;

    public Story() {
        locations = new HashMap<>();
        npcs = new HashMap<>();
    }

    // Displaying the welcome message
    public void displayWelcomeMessage() {
        ui.displayMsg("In a world of stone, rocks and drought. The few things that lived were shrunken and weak. Their teeth\n" +
                "brittle, their eyes blinded by the ever-raging sun. They knew nothing but the suffering of burnt skin and the\n" +
                "heat of being trapped in their own fur or feathers. It was an endless cycle of pain, but no cycle exists where\n" +
                "it is never broken.\n" +
                "From a crack in the tallest mountain, one that reached far above the limits of the sky, a single droplet fell.\n" +
                "From the eyes of a young white stag within a cave in the crack, a tear dropped, then another, until a total of\n" +
                "eight tears had tumbled down the ledges and rough terrain of the side of the mountain.\n" +
                "Where the tears fell, eight mighty trees sprouted from the  barren soil. They grew so large they covered the\n" +
                "world in shade for four days and four nights. Once the sun was no longer tormenting life as it was in the\n" +
                "world, what did live knew peace and relief of pain for the first time.\n" +
                "The shade brought life back to the soil, it brought soothing to the burnt and the scorched but most\n" +
                "important of all, it brought water. The tears within the trees spread within the trunks, out to the very leaves,\n" +
                "they fell as rain upon the world.\n" +
                "A new cycle was born, one of balance, for the stag knew that it could not replace drought, with flooding. So,\n" +
                "it only shed the eight tears, no more, no less. To such the seasons were born, the four largest trees took\n" +
                "colours each of their own.\n" +
                "A bright green for when life should return, a deep emerald for when life flourishes, a burning orange to bid\n" +
                "the sun come back, and a barren tree, who had shed it leaves to make space for the sun to shine through.\n" +
                "The four lesser trees were the in between, a mix of colours, their leaves few or many, and the colours of\n" +
                "jewels hidden within the mountain.\n" +
                "But cycles do not exist where it is never broken. The tear of the tree of emerald life has been stolen,\n" +
                "without it, the world is once more in disarray and threatened to go out of balance.");
    }


    // Create all locations
    public void loadStory() {
        createLocations();
        storeLocations();
        connectLocations();
        createNPCs();
        populateCreatures();
    }

    public void createLocations() {
        Location theClearing = new Location(
                "The Clearing",
                "A glistening flower stands dancing in the gentle breeze in the middle of the clearing. It is taller than you are,\n" +
                        "and strangely glowing below the dew that coats it. This is the sense of danger you felt.\n" +
                        "Roots fly up towards you, attempting to grab and strangle you! "
        );
        Location huntingCabin = new Location(
                "The Hunting Cabin",
                "Through the barricaded window opening of the cabin," +
                        "you can see crates. They may contain something useful. " +
                        "The door is locked. While the building looks abanandoned, " +
                        "the lock is not damaged or old enough that you can just break " +
                        "it. You will need a key to enter."
        );

        Location theLake = new Location(
                "The Lake",
                "You follow the scent of water, and after walking for a while, through lush bushes filled with berries and\n" +
                        "flowers, you see the glinting of an unending number of reflections.\n" +
                        "A beautiful shallow lake meets your eyes. Then your ears pick up her presence before your eyes could ever\n" +
                        "see beyond the reflected light. A song, so light and cheery it dares to challenge your heart. The song lures\n" +
                        "you in and the closer you get, the lighter your head and heart feels.\n" +
                        "Soon you stand ankle deep in the shallow waters, face to face with a lake siren. Her skin the colour of spring\n" +
                        "leaves, her eyes big, dewy and deep as the night, and her hair flowing like eel grass. Her song stops and she\n" +
                        "gives you a curious glance. “What seek you here, at my lake?”"

        );

        Location theForestPath = new Location(
                "The Forest Path",
                "You walk towards the path you spotted in the forest to the east. It does not take you long before you find\n" +
                        "yourself walking easier on a well-trodden road. Despite the frequent use you imagine the road has had, you\n" +
                        "spot no one or any signs of anyone using the road but you. After a while of walking you come to a\n" +
                        "crossroads in the path.\n"
        );

        Location theTravellingMerchant = new Location(
                "The Travelling Merchant",
                "An older human man sits and rests with a lit pipe in his mouth up against his small caravan cart. Two work\n" +
                        "horses graze nearby on the greens of the forest floor.\n" +
                        "As you approach the man looks up, blowing out smoke and the smacking his lips into a friendly grin.\n" +
                        "“Why hello stranger, care to see my wares?”\n"
        );

        Location theSwampPath = new Location(
                "The Swamp Path",
                "You walk into the denser and wetter part of the forest, and soon large trees are swapped out for swamp and\n" +
                        "bog. Your steps are slow and the air harder to breathe. The smell attacks your nose and air your eyes. Yet\n" +
                        "you press on.\n" +
                        "It does not take long before a howl pierces the dense air and a large black beast growl near you. A large\n" +
                        "dark furred dire wolf. It launches to attack you."
        );

        Location theMurkyWaters = new Location(
                "The Murky Waters",
                "You go through the swampy landscape, the murky waters now waist deep, and you swear something just\n" +
                        "moved past your legs down below the surface.\n" +
                        "Just ahead bubbles surge from below and up to the surface, once there they pop and release a nasty smell.\n" +
                        "But it is hard to see what might produce them through the opaque waters. It could be fine, or it could be\n" +
                        "deadly to go through them.\n"
        );

        Location theWitchHut = new Location(
                "The Witch's Hut",
                "Having waded through only the gods knows what in the murky waters, soaked and a little low on spirit and\n" +
                        "cheer. You reach a little hut on a patch of dry land in these horrid swamplands.\n" +
                        "Its roof of thatch and its windows crooked but none the less, it looks cozy and warm, the sign of smoke\n" +
                        "rising lazily from the brick chimney suggests a warmth inside.\n" +
                        "You approach the door and knock twice, but before you can even call out, the door swings open with no\n" +
                        "one in sight.\n" +
                        "By foolishness or bravery, you enter the hut. Filled to the brim with herbs and books, scrolls and nooks. In\n" +
                        "front of a cauldron stands a middle-aged elven woman. Her hair fair and ashen, her nose crooked but her\n" +
                        "face radiating beauty and grace. She turns to look at you expectational."
        );

        Location theOfferingBog = new Location(
                "The Offering Bog",
                "You choose to go to the darker, murkier, more dangerous part of the swamp. As soon as you being moving\n" +
                        "further south, your nose, your eyes they burn with a stench worse than rot and death. Or perhaps that is\n" +
                        "exactly what you are smelling.\n" +
                        "While the smell and air are choking you, you can praise the gods that this way is less watery, at least for\n" +
                        "now. You move closer and hide behind a small bramble thicket. In the distance you see two orcs holding a\n" +
                        "small girl in her ankle upside down over the edge of a bog.\n" +
                        "The bog is surrounded by primitive religious symbols, standards and offerings. A whole rotting cow hangs\n" +
                        "out over the still standing waters of the bog on a wooden rack.\n" +
                        "The orcs are discussing what to do with the girl when you accidentally step on a twig, alerting them to your\n" +
                        "presence.\n" +
                        "“WHO IS THERE?!” roars one of them. "
        );

        Location theFrozenBog = new Location(
                "The Frozen Bog",
                "Past the runes and magical symbols that kept you from entering the barrier. You quickly feel the\n" +
                        "temperature drop, you are moving upwards, the bog is becoming more of a frozen wetland. Reeds stand\n" +
                        "frost coated, the still waters have a thin layer of ice mirroring the sky.\n" +
                        "But a clear path is ahead of you. Over the frozen ice, hopping from dirt patch to dirt patch, then up hill to\n" +
                        "the start of the mountain or origin.\n" +
                        "(If ranger) You easily manage to traverse the ice, no broken bones or icy blunt wounds for you. But\n" +
                        "something else also catches your eye. A set of footprints, the thief very likely. It indicates someone adult at\n" +
                        "the very least by the weight, the imprints in the frosted ground pressed down too far to be a child. The frost\n" +
                        "is also new, so you are certain you are not far behind them!\n"
        );

        Location theMountainPath = new Location(
                "The Mountain Path",
                "The path from the forest leads all the way to the beginning of the mountain of origin. By first glance this is\n" +
                        "the fastest way up. But once you begin walking up, you are soon met with a wall of solid rock, snow and ice.\n" +
                        "It looks like an avalanche has tumbled down the mountain side and is now blocking your way up, you will\n" +
                        "have to find another way up.\n"
        );

        Location theRootsOfTheMountain = new Location(
                "The Roots of the Mountain",
                "You start climbing up the roots of the mountain, sharp ledges and rough terrain. But if you are to succeed to\n" +
                        "bringing balance back before it is too late, there is nothing to do but press ahead.\n" +
                        "Your fingers burn with frost, your breath hurt in your lungs and your skin feel like unending needles stabbing\n" +
                        "you. The higher you climb, the harder it gets to breathe.\n" +
                        "The sun’s rays as it slowly moves towards the horizon pains the cliffs and snow upon it a bloody red. It will\n" +
                        "become dark soon, and cold, very cold. You will need to find shelter."
        );

        Location theFreezingPass = new Location(
                "The Freezing Pass",
                "You climb and climb and climb until you reach where the mountain path should have taken you. Exhausted\n" +
                        "you sit down for a moment on a rock, overlooking the view.\n" +
                        "Glittering below in the sunlight, the forest’s leaves twinkle like emeralds. The swamp seems ever greyer and\n" +
                        "darker than you remember it. But what catches your eyes the most is of course what really should. The\n" +
                        "great trees.\n" +
                        "Like giants they stand guards over the mountain, not as tall as the peak of the mountain but even from\n" +
                        "where you are, they are reaching towards the sky. Magnificent is an underwhelming word to describe them,\n" +
                        "but yet you cannot find a better word.\n" +
                        "The view takes your attention away from the cutting frightfully icy winds that gnaw at your exposed skin.\n" +
                        "But it soon becomes too much, and you are reminded to move to produce warmth.\n" +
                        "You follow the mountain road, it is easier than climbing, yet your lungs at this altitude scream ever the\n" +
                        "same. Until your foot becomes stuck on something buried in fresh fallen snow.\n" +
                        "You lean down and remove the fresh layer, only to find the frozen corpse of a dwarf laying there solid and\n" +
                        "stiff as the rock on which you stand. In their hand you uncover the emerald tear. Deep in color as the forest,\n" +
                        "and formed like a tear, the stone relic big enough to fill both your hands.\n" +
                        "But you soon realize it is cracked, damaged by this fool that died on their way up here.\n" +
                        "So now you must go ahead with it, surely the great stag that once shed the tear would also be able to repair\n" +
                        "it.\n" +
                        "Suddenly a scrambling sound behind you, a mountain goat, and not the usual kind stands behind you, it lifts\n" +
                        "it head and huffs hot air out through its nostrils, it swings its horns at you."
        );

        Location theCave = new Location(
                "The Cave",
                "You reach a gigantic cave, it had looked smaller from the distance you saw it in earlier, but now, it is far\n" +
                        "beyond anything you have ever seen. Three houses if not more could be stacked in here on top of each\n" +
                        "other. The very footsteps you take echo briefly through the rock and ice that form the enormous cave.\n" +
                        "But so does another sound, a deep rumbling, no… snoring. You carefully move further into the cave.\n" +
                        "Soon you are met with the sight of what you first think is a large collecting of spiky ice stalagmites, but they\n" +
                        "move slowly up and down, take you steps backwards and prepare yourself.\n" +
                        "Two piercing white eyes open and stare at you as the giant ice dragon awake, shaking itself loose from sleep\n" +
                        "and roars."
        );

        Location theCrownOfTheWorld = new Location(
                "The Crown of the World",
                "The dragon upon its back flies you to the peak of the mountain of origin, you can barely hang on to its\n" +
                        "slippery icy back spikes as the wind tries to knock you off by the sheer speed of the great dragon.\n" +
                        "The air so thin you feel like fainting yet you hold on to that and the emerald tear. But as the world feels like\n" +
                        "its spinning it comes to a halt. The mighty dragon having perched itself just below the crack of the peak,\n" +
                        "where the sagas tell the great white stag resides.\n" +
                        "With careful steps and even tighter grips you manage to climb off the dragon onto the mouth of the crack in\n" +
                        "the snowy rock.\n" +
                        "With a spinning mind and a heavy body, you enter the crack, despite being so high the sun always shines\n" +
                        "upon the peak, the insides of the cave is dark and gloomy.\n" +
                        "“Hello? I call upon you, great white stag, shedder of tears, bringer of life and water.” You respectfully call\n" +
                        "out.\n" +
                        "Out of the dark a pair of antlers glisten with dripping water, then follows the mighty head that balances\n" +
                        "them atop. A great white stag, taller than a house stand before you, its eyes blind and grey as the clouds\n" +
                        "that swirl further down the mountain."
        );
        //--- Traps ---
        Location theMurkyWatersTrap = new Location(
                "The Murky Waters Trap",
                "You go around the bubbles, those are surely deadly right?\n" +
                        "Well, someone thought about that and placed a spiked trap below the waters.\n" +
                        "It snaps around your leg, but you still manage to move forward."
        );
    }


    public void storeLocations () {
            // Add locations to the map
            locations.put("The Clearing", theClearing);
            locations.put("The Hunting Cabin", huntingCabin);
            locations.put("The Lake", theLake);
            locations.put("The Forest Path", theForestPath);
            locations.put("The Travelling Merchant", theTravellingMerchant);
            locations.put("The Swamp Path", theSwampPath);
            locations.put("The Murky Waters", theMurkyWaters);
            locations.put("The Witch's Hut", theWitchHut);
            locations.put("The Offering Bog", theOfferingBog);
            locations.put("The Frozen Bog", theFrozenBog);
            locations.put("The Mountain Path", theMountainPath);
            locations.put("The Roots of the Mountain", theRootsOfTheMountain);
            locations.put("The Freezing Pass", theFreezingPass);
            locations.put("The Cave", theCave);
            locations.put("The Crown of the World", theCrownOfTheWorld);
        }


    // Connects all the locations based on the world map
    private void connectLocations () {
            // The Clearing connected locations
            theClearing.addConnectedLocation("north", huntingCabin);
            theClearing.addConnectedLocation("south", theLake);
            theClearing.addConnectedLocation("east", theForestPath);

            // The Hunting Cabin connected locations
            huntingCabin.addConnectedLocation("south", theClearing);

            // The lake connected locations
            theLake.addConnectedLocation("north", theClearing);

            // The forest path connected locations
            theForestPath.addConnectedLocation("north", theMountainPath);
            theForestPath.addConnectedLocation("south", theTravellingMerchant);
            theForestPath.addConnectedLocation("east", theSwampPath);
            theForestPath.addConnectedLocation("west", theClearing);

            // The offering bog connected locations
            theOfferingBog.addConnectedLocation("north", theSwampPath);

            // The swamp path connected locations
            theSwampPath.addConnectedLocation("south", theOfferingBog);
            theSwampPath.addConnectedLocation("west", theForestPath);
            theSwampPath.addConnectedLocation("north", theFrozenBog);
            theSwampPath.addConnectedLocation("east", theMurkyWaters);

            // The murky waters connected locations
            theMurkyWaters.addConnectedLocation("west", theSwampPath);
            theMurkyWaters.addConnectedLocation("east", theWitchHut);

            // The witch's hut connected locations
            theWitchHut.addConnectedLocation("west", theMurkyWaters);

            // The frozen bog connected locations
            theFrozenBog.addConnectedLocation("south", theSwampPath);
            theFrozenBog.addConnectedLocation("west", theMountainPath);
            theFrozenBog.addConnectedLocation("north", theRootsOfTheMountain);

            // The roots of the mountain connected locations
            theRootsOfTheMountain.addConnectedLocation("east", theFreezingPass);
            theRootsOfTheMountain.addConnectedLocation("south", theFrozenBog);

            //  The freezing pass connected locations
            theFreezingPass.addConnectedLocation("east", theCave);
            theFreezingPass.addConnectedLocation("west", theRootsOfTheMountain);

            // The cave connected locations
            theCave.addConnectedLocation("north", theCrownOfTheWorld);
            theCave.addConnectedLocation("west", theFreezingPass);

            // The crown of the world connected locations
            theCrownOfTheWorld.addConnectedLocation("south", theCave);
        }

    public Location getLocation (String locationName){
            return locations.get(locationName);
        }

    // NPC CREATION
    private void createNPCs(){
        // Travelling Merchant
            NPC merchant = new NPC(
                    "The Travelling Merchant",
                    "merchant",
                    new Stats(0,0,0,0),
                    Arrays.asList(
                            "Why hello stranger, care to see my wares?"
                    ),
                    false, // Not hostile
                    null, // Item Held until items are created, has cabin key
                    null // Dire wolf quest - added later
                    );
            npcs.put("merchant", merchant); // Map NPC id to merchant

        // FIGHTABLE NPCs

        // Lake Siren
            NPC siren = new NPC(
                    "Lake Siren", // Name
                    "siren",            // NPC ID
                    new Stats(10,10,10,10),
                    Arrays.asList(
                            "What seek you here, at my lake?"
                    ),
                    false, // Only hostile when provoked
                    null, // Item held, has the heart of the siren - drops if slayed
                    null // Has no quest
            );
            npcs.put("siren", siren); // Map NPC id to siren

            // Witch
            NPC witch = new NPC(
                    "Witch of the Witch's hut",
                    "witch",
                    new Stats(10,10,10,10), // Base stats
                    Arrays.asList(
                            " "
                    ),
                    false, // Can be hostile
                    null, // Has special loot
                    null // She does have a quest
            );
            npcs.put("witch",witch);

            // Ice dragon
            NPC ice_dragon = new NPC(
                    "Ice Dragon",
                    "ice_dragon",
                    new Stats(200,30,20,50), // Proposed stats
                    Arrays.asList("" +
                            "What brings you to my cave?"
                    ),
                    false, // Not necessarily hostile
                    null, // No item
                    null // No quest
            );
            npcs.put("ice_dragon", ice_dragon);

            // Dire wolf
            NPC dire_wolf = new NPC(
                    "Dire Wolf",
                    "dire_wolf",
                    new Stats(10,10,10,10), // Base stats
                    List.of(""), // No voice lines
                    true, // Is hostile
                    new Item("Head of the Dire Wolf", "Dropped when killing the Dire Wolf", 2), // Drops head of the dire wolf
                    null // Doesn't give quest
            );
            npcs.put("dire_wolf", dire_wolf);

            // Mountain goat
            NPC mountain_goat = new NPC(
                    "Mountain Goat",
                    "mountain_goat",
                    new Stats(30,5,10,5), // Proposed stats
                    Arrays.asList(""),
                    true, // Is hostile towards player
                    null, // Has minor loot
                    null
            );
            npcs.put("mountain_goat", mountain_goat);

            // Great white stag
            NPC stag = new NPC(
                    "Great White Stag",
                    "white_stag",
                    new Stats(90,20,15,10),
                    Arrays.asList(""),
                    false, // Can be hostile if provoked
                    null,
                    null // No quest to give
            );
            npcs.put("great_white_stag", stag);

            // Glistening flower
            NPC flower = new NPC(
                    "Glistening Flower",
                    "glistening_flower",
                    new Stats(40,5,5,5), // Proposed stats
                    Arrays.asList(""),
                    true, // Hostile towards player
                    new Item("Heart of the Flower", "Heart of the flower gained by defeating" +
                            "the Glistening Flower in The Clearing", 1), // Need to implement Item class
                    null // Doesn't give quest
            );
            npcs.put("glistening_flower", flower);

            NPC orc_1 = new NPC(
                    "Offering Bog Orc 1",
                    "offering_orc_1",
                    new Stats(40,10,10,3), // Proposed stats
                    Arrays.asList("Who is there?!"),
                    false, // Only when given the right dialogue
                    new Item("Heart of the Bog", "Looted by defeating the orcs or looted from the already dead orcs", 1),
                    null // No quests to give
            );
            npcs.put("offering_orc_1",orc_1);

            NPC orc_2 = new NPC(
                    "Offering Bog Orc 2",
                    "offering_orc_2",
                    new Stats(40,10,10,3), // Proposed stats
                    Arrays.asList(""),
                    false, // Only when given the right dialogue
                    null, // Only one orc holds item
                    null // No quests to give
            );
            npcs.put("offering_orc_2",orc_2);

        }

    private void populateCreatures(){
            theClearing.addCreature(npcs.get("glistening_flower"));
            theLake.addCreature(npcs.get("siren"));
            theTravellingMerchant.addCreature(npcs.get("merchant"));
            theSwampPath.addCreature(npcs.get("dire_wolf"));
            theWitchHut.addCreature(npcs.get("witch"));
            theFreezingPass.addCreature(npcs.get("mountain_goat"));
            theCave.addCreature(npcs.get("ice_dragon"));
            theCrownOfTheWorld.addCreature(npcs.get("great_white_stag"));
            theOfferingBog.addCreature(npcs.get("offering_orc_1"));
            theOfferingBog.addCreature(npcs.get("offering_orc_2"));
        }

    // DIALOGUE OPTIONS

    // Generic getter for dialogue choices to go to game controller
    public List<Choice> getDialogueChoices(NPC npc, Player player) {
        if (npc == null || npc.isDead()) {
            return new ArrayList<>(); // no choices if NPC doesn't exist or is dead
        }

        switch (npc.getNPC_ID().toLowerCase()) {
            case "siren":
                return getSirenDialogueChoices(player);

            case "merchant":
                return getMerchantDialogueChoices(player);

            case "witch":
                return getWitchDialogueChoices(player);

            default:
                // Generic dialogue fallback
                List<Choice> options = new ArrayList<>();
                options.add(Choice.interactChoice("Hello " + player.getName() + "!", npc));
                return options;
        }
    }

    // Generic handler for dialogue choice to go to game controller
    public void handleDialogue(NPC npc, Player player, Choice selectedChoice) {
        if (npc == null || npc.isDead() || selectedChoice == null) return;

        switch (npc.getNPC_ID().toLowerCase()) {
            case "siren":
                handleSirenDialogue(player, selectedChoice);
                break;

            case "merchant":
                handleMerchantDialogue(player, selectedChoice);
                break;

            case "witch":
                handleWitchDialogue(player, selectedChoice);
                break;

            default:
                npc.speak("I have nothing special to say.");
                break;
        }
    }





    // MERCHANT DIALOGUE
    // Greeting quest is the direwolf quest
    public List<Choice> getMerchantDialogueChoices(Player player) {
        List<Choice> choices = new ArrayList<>();
        NPC merchant = npcs.get("merchant");

        if (merchant == null || merchant.isDead()) return choices;

        // --- Greeting & quest offer for humans ---
        if (player.isHuman() && !player.hasFlag("merchant_quest_given")) {
            Choice greetingQuest = Choice.interactChoice(
                    "Old man, you should be careful around these parts, they are not safe anymore. The " +
                            "world balance has been upset. I have little money left, and cannot buy your wares, " +
                            "but perhaps I can help you in exchange for a few of your things?",
                    merchant
            );
            // Only available to humans
            greetingQuest.addRequirement(new RaceRequirement("Human"));
            choices.add(greetingQuest);
        }

        // --- Quest option for any non-human ---
        if (!player.isHuman() && !player.hasFlag("merchant_quest_given")) {
            Choice questOption = Choice.interactChoice(
                    "I do not have much in the way of money, I have spent most of it on traveling supplies. " +
                            "But perhaps I can lend you my services instead?",
                    merchant
            );
            choices.add(questOption);
        }

        // --- Quest completion if player has killed the dire wolf ---
        if (player.hasFlag("killed_dire_wolf") && player.getInventory().hasItem("Head of the Dire Wolf")) {
            Choice completeQuest = Choice.interactChoice(
                    "I have brought back proof that I have removed the dire wolf from the path.",
                    merchant
            );
            choices.add(completeQuest);
        }

        // --- Kill merchant / steal loot option ---
        Choice killMerchant = Choice.interactChoice(
                "I will take your things for myself old man!",
                merchant
        );
        choices.add(killMerchant);

        // --- Leave option ---
        Choice leave = Choice.interactChoice(
                "Be on your way and I will be on mine.",
                merchant
        );
        choices.add(leave);

        return choices;
    }


    public void handleMerchantDialogue(Player player, Choice selectedChoice) {
        NPC merchant = npcs.get("merchant");
        if (merchant == null || merchant.isDead() || selectedChoice == null) return;

        String text = selectedChoice.getDescription();
        ui.displayMsg("You: " + text);

        // Greeting & quest for humans
        if (text.startsWith("Old man, you should be careful") && player.isHuman()) {
            player.addFlag("merchant_quest_given");
            player.pickUpItem(new Item("Red Potion", "Healing potion. Restore 20 health.", 1));
            ui.displayMsg("Merchant: Thank you. I shall be extremely careful. " +
                    "Well here take this to start with, for your kindness.\n*The old man hands you a red potion." +
                    "A dire wolf blocks my path to visit a town " +
                    "in the swamplands to the east of here. If you kill it and bring back proof, " +
                            "you can get this here key I found near an old cabin in the forest.");
            return;
        }

        // Quest offer for non-humans
        if (text.startsWith("I do not have much in the way of money") && !player.isHuman()) {
            player.addFlag("merchant_quest_given");
            ui.displayMsg("Merchant: Well, a trade then. You see a dire wolf blocks my path to visit a town " +
                    "in the swamplands to the east of here. If you kill it and bring back proof, " +
                    "you can get this here key I found near an old cabin in the forest.");
            return;
        }

        // Quest completion
        if (text.startsWith("I have brought back proof") &&
                player.hasFlag("killed_dire_wolf") &&
                player.getInventory().hasItem("Head of the Dire Wolf")) {

            player.addFlag("merchant_quest_complete");
            player.addFlag("received_cabin_key");
            player.getInventory().removeItem("Head of the Dire Wolf");
            player.getInventory().addItem(new Item("Cabin Key", "Cabin Key received from Merchant", 1));

           ui.displayMsg("Merchant: Thank you stranger! Now I can finally move on. Here take these, as we discussed!");
            return;
        }

        // Kill merchant / steal loot
        if (text.startsWith("I will take your things")) {
            merchant.setDead(true);
            player.addFlag("killed_merchant");
            ui.displayMsg("Merchant: No... please have mercy...");
            return;
        }

        // Leave
        if (text.startsWith("Be on your way")) {
            ui.displayMsg("Merchant: Safe travels, stranger!");
        }
    }




    // SIREN DIALOGUE
    public List<Choice> getSirenDialogueChoices(Player player) {
        List<Choice> choices = new ArrayList<>();
        NPC siren = npcs.get("siren");

        if (siren == null || siren.isDead()) {
            return choices;
        }

        // Special elf dialogue
        Choice elfLine = Choice.interactChoice(
                "Kin of the water, I see your beauty, my heart your song. Could you guide me on my way? I\n" +
                        "fear the unbalance will spread, and I wish to stop it. Do you know what has happened?",
                siren
        );
        elfLine.addRequirement(new RaceRequirement("Elf"));
        choices.add(elfLine);

        // General dialogue option
        Choice generalLine = Choice.interactChoice(
                "Do you know what has happened to the world balance?",
                siren
        );
        // Only available if player is not Elf or has chosen a different option
        choices.add(generalLine);

        // Attack Siren
        Choice attackLine = Choice.interactChoice(
                "I have no choice but to slay you!",
                siren
        );
        choices.add(attackLine);

        // Leave dialogue
        Choice leaveLine = Choice.interactChoice(
                "I was just passing by",
                siren
        );
        choices.add(leaveLine);

        return choices;
    }


    public void handleSirenDialogue(Player player, Choice selectedChoice) {
        NPC siren = npcs.get("siren");
        if (siren == null || siren.isDead()) return;

        String text = selectedChoice.getDescription();

        // Display the chosen dialogue
        ui.displayMsg("You: " + text);

        switch (text) {
            case "Kin of the water, I see your beauty, my heart your song. Could you guide me on my way? I\n" +
                         "fear the unbalance will spread, and I wish to stop it. Do you know what has happened?":
                ui.displayMsg("Siren: I know you not, stranger. But the world is unsettled by a theft so cruel. One of the tears has been taken. It must be returned to its nest in its tree.");
                break;

            case "Do you know what has happened to the world balance?":
                ui.displayMsg("Siren: I know you not, stranger. But the world is unsettled by a theft so cruel. One of the tears has been taken. It must be returned to its nest in its tree.");
                break;

            case "I have no choice but to slay you!":
                // Make Siren hostile
                siren.setHostile(true);
                ui.displayMsg("The Siren becomes hostile!");

                // Start combat via GameController
                gc.handleCombat(siren);

                // If player won, give the heart
                if (siren.isDead()) {
                    player.addFlag("killed_siren");
                    player.pickUpItem(new Item("Heart of the Siren", "Heart of the Siren obtained by killing Siren of The Lake", 2));
                    ui.displayMsg("You have slain the Siren and obtained her Heart!");
                } else {
                    ui.displayMsg("You were defeated.");
                }
                break;

            case "I was just passing by":
                ui.displayMsg("Siren: Farewell.");
                break;
        }
    }



    // WITCH DIALOGUE
    public List<Choice> getWitchDialogueChoices(Player player) {
        List<Choice> options = new ArrayList<>();
        NPC witch = npcs.get("witch");

        if (witch == null || witch.isDead()) {
            return options;
        }

        // Quest completed
        if (player.getInventory().hasItem("Heart of the Flower") &&
                player.getInventory().hasItem("Heart of the Siren") &&
                player.getInventory().hasItem("Heart of the Bog")) {
            options.add(Choice.interactChoice("Here you go witch, I have your spell ingredients.", witch));
            options.add(Choice.interactChoice("You have met your match. Prepare to die!", witch));
            return options;
        }

        // Quest already given but not completed
        if (player.hasFlag("witch_quest_given")) {
            options.add(Choice.interactChoice("I uh, do not have all three of the hearts. I will go now.", witch));
            options.add(Choice.interactChoice("You have met your match. Prepare to die!", witch));
            return options;
        }

        // First meeting
        if (player.isMage()) {
            // Mage-specific dialogue
            options.add(Choice.interactChoice(
                    "What are you, a witch,\na druid? Some sort of nature priestess?", witch
            ));
        } else {
            // Generic greeting only if not a mage
            options.add(Choice.interactChoice(
                    "I am sorry for intruding into your home, Miss. But I am seeking what upsets the balance, do you\nknow something?",
                    witch
            ));
        }

        // Combat / attack choice (available to all)
        options.add(Choice.interactChoice("You have met your match. Prepare to die!", witch));
        // Leave choice (available to all)
        options.add(Choice.interactChoice("Ah… wrong way. Sorry for intruding", witch));

        return options;
    }


    public void handleWitchDialogue(Player player, Choice selectedChoice) {
        NPC witch = npcs.get("witch");
        if (witch == null || witch.isDead()) return;

        String text = selectedChoice.getDescription();

        // Display player's choice
        ui.displayMsg("You: " + text);

        switch (text) {
            // Quest completed - hand in items
            case "Here you go witch, I have your spell ingredients.":
                player.addFlag("witch_barrier_open");
                // TODO: remove quest items from inventory
                player.pickUpItem(new Item("Special Cape", "Special Cape handed to you by the Witch", 2));
                ui.displayMsg("Witch: I thank you, stranger. As promised. A key to my barrier, you may now pass, and take this with you as well.");
                break;

            // Attack / combat choice
            case "You have met your match. Prepare to die!":
                // Make Witch hostile
                witch.setHostile(true);
                ui.displayMsg("Witch: So be it! Prepare yourself!");

                // Start combat via GameController
                gc.handleCombat(witch);

                // If player won
                if (witch.isDead()) {
                    player.addFlag("killed_witch");
                } else {
                    ui.displayMsg("You were defeated.");
                }
                break;

            // Quest given but not completed
            case "I uh, do not have all three of the hearts. I will go now.":
                ui.displayMsg("Witch: Well get to it then! I may be an elf, but my time is still precious!");
                break;

            // First meeting - mage
            case "What are you, a witch,\na druid? Some sort of nature priestess?":
                player.addFlag("witch_quest_given");
                ui.displayMsg("Witch: Oh… a fellow mage I see. I am a witch, and before you start throwing accusations, no, I do not eat children! But yes, I know what has upset the balance...");
                break;

            // Generic greeting for non-mages
            case "I am sorry for intruding into your home, Miss. But I am seeking what upsets the balance, do you\nknow something?":
                player.addFlag("witch_quest_given");
                ui.displayMsg("Witch: Hmph, curious mortal… you seek what disrupts the balance? Listen well...");
                break;

            // Leaving
            case "Ah… wrong way. Sorry for intruding":
                ui.displayMsg("Witch: Leaving so soon?");
                break;
        }
    }


    // Implement dialogue for dragon, stag and orcs

    // ICE DRAGON DIALOGUE
    public List<String> getDragonDialogue(Player player) {
        List<String> options = new ArrayList<>();

        NPC dragon = npcs.get("ice_dragon");

        // Error handling
        if (dragon == null || dragon.isDead()) {
            return options;
        }

        // Dialogue options
        options.add("Mighty dragon, I have not come to harm\n" +
                "you, nor to intrude on your territory. I however must go to the peak of the mountain, the crown of\n" +
                "the world. For a tear of the great white stag has been damaged, and must be repaired and thus\n" +
                "promptly returned to its nest, or the world shall fall into chaos.");
        options.add("I will give you all my treasures if you take me to the peak.");
        options.add("Prepare to fight, dragon!");

        return options;
    }


    public String handleDragonDialogue(int choice, Player player) {
        NPC dragon = npcs.get("ice_dragon");

        // First interaction
        if (choice == 1) {
            player.addFlag("dragon_first_interaction");
            return "A little stranger, here to bring balance to the world, why would the great stag even repair the tear? " +
                    "Why would I take you? What if I am just awaiting the new cycle to come. Hrmm…";
        } else if (choice == 2) {
            // Bartering with the dragon
            player.addFlag("dragon_will_help");
            player.addFlag("dragon_barter");
            for(Item i : player.getInventory().getItems()){ // Removes all items from inventory
                player.getInventory().removeItem(i);
            }
            return "Oh, how humorous, yet I am not one to say no to such frivolous mortal behavior. " +
                    "Come, climb on my back as you have paid your toll.";
        } else if (choice == 3) {
            // Choosing to fight the dragon
            dragon.setHostile(true);
            return "You dare challenge me?! So be it!";
        }

        return "";
    }

    // Dragon follow-up dialogue
    public List<String> getDragonFollowUp(Player player) {
        List<String> options = new ArrayList<>();

        // In case we have already talked to the dragon before
        if (!player.hasFlag("dragon_first_interaction")) {
            return options;
        }

        // Additional dialogue options
        options.add("Oh great dragon, I know my quest might be foolish in your wise eyes, but I must give it a\n" +
                "try none the less, for if I did not try, I would regret it for the rest of my life.");
        options.add("A new cycle can begin another time, for now is my time where I choose to try and mend\n" +
                "the cycle that already is.");

        return options;
    }

    public String handleDragonFollowUp(int choice, Player player) {
        if (choice == 1) {
            player.addFlag("dragon_will_help"); // Helps you both ways
            return "Very well, little one. Your determination moves me. Climb upon my back."; // Added dialogue
        } else if (choice == 2) {
            player.addFlag("dragon_will_not_help"); // Only helps you up
            return "Very well. I shall take you to the peak, but you must find your own way down."; // Added dialogue
        }

        return "";
    }

    // WHITE STAG DIALOGUE
    public List<String> getStagDialogue(Player player) {
        List<String> options = new ArrayList<>();

        // Error handling
        NPC stag = npcs.get("white_stag");
        if (stag == null || stag.isDead()) {
            return options;
        }

        // Dialogue options for stag
        options.add("Please mighty white stag, repair your tear, let the cycle continue as it is now.");
        options.add("*If I can kill a god, maybe I too will become a god.*"); // Added internal dialogue as an option

        return options;
    }

    public String handleStagDialogue(int choice, Player player) {
        NPC stag = npcs.get("white_stag");

        // Checking for different flags to change ending
        if (choice == 1) {
            // Check what player has done to determine ending
            if (player.hasFlag("killed_merchant")) {
                player.addFlag("bad_ending_merchant"); // Killed merchant, tear is not repaired
                return "Killer of the innocent, you speak of hope but forbid it of others yourself? " +
                        "No… let the cycle end, let the mortals learn from their mistakes.";

            } else if (player.hasFlag("killed_siren")) {
                player.addFlag("sacrifice_ending"); // Killed siren, sacrifice yourself to repair tear
                return "Killer of the unknown, of beauty, of nature. You are scared of fear itself. " +
                        "I cannot blame you for such, as it is the flaw of mortals. Yet it fills me with sorrow. " +
                        "I ask of you to lay your own life down, and I will repair the tear, to balance the world, " +
                        "and balance your choice.";

            } else if (player.hasFlag("killed_offering_orc_1") || player.hasFlag("killed_offering_orc_2")) {
                player.addFlag("good_ending");
                return "Worship is a dangerous thing when fear takes the hearts of those who seek favors. " +
                        "I do not blame you for the loss of life to save another. You brought hope to that girl, " +
                        "so I will repay the favor in her stead.";  // Killed the orc keeping the girl captive, good ending

            } else if (player.hasFlag("killed_witch")) {
                player.addFlag("bad_ending_witch");
                return "Nature takes, and nature gives. Nature is not confined to the mortals' standards of evil and good. " +
                        "You kill that of which is just taking their own interest at heart and that of nature. " +
                        "I have nothing to give you that you cannot try and mend yourself."; // Tear is not repaired

            } else if(player.hasFlag("killed_witch") && player.hasFlag("killed_siren")) {
                player.addFlag("bad_ending_death");
                return "To take from nature and not return anything, take its guardians and deprive it of healing is just as\n" +
                        "the scorching sun did ages past. You have shown you are no different, and for that, you have\n" +
                        "discarded my mercy, my hope, my dream for the mortals."; // Player dies and tear is not repaired

            } else if (player.hasFlag("killed_witch") && player.hasFlag("killed_siren") && player.hasFlag("killed_merchant")) {
                player.addFlag("bad_ending_stag_fight");
                return "Murderer…your heart desires nothing but blood, your soul empty like a gaping hole sucking\n" +
                        "everything in without embracing it. Greed rules your mind, bloodlust your heart and I have nothing\n" +
                        "to give you but death, even though that is merciful to you, I shall heal the world by removing your\n" +
                        "stain upon it."; // Bad ending with boss fight
            }

            else {
                player.addFlag("good_ending");
                return "*The great white stag gently bows its head down to you, despite all you have been through a little bit\n" +
                        "of fear run through your body yet its warm breath like a summers breeze warms your body. Its milky\n" +
                        "eyes beholds the cracked gem and then looks carefully at you, even though you feel that it is\n" +
                        "looking -through- you.*"; // Good ending! Didn't kill anyone unnecessarily, tear gets repaired
            }
        } else if (choice == 2) {
            // Attacking the stag
            stag.setHostile(true);
            player.addFlag("attacked_stag");
            return "*Throw the tear to the ground, if you can kill a god like being, maybe you too will become a god. You\n" +
                    "charge at the giant stag.*"; // Boss fight
        }

        return "";
    }

    // OFFERING BOG ORC DIALOGUE
    public List<String> getOrcDialogue(Player player) {
        List<String> options = new ArrayList<>();

        // Deal with just one orc talking for this scenario
        NPC orc1 = npcs.get("offering_orc_1");

        // Check if orc is dead
        if (orc1 == null || orc1.isDead()) {
            return options;
        }

        // Check class / race specific dialogue
        if (player.isOrc()) {
            options.add("I am not here for you. Do your thing, let our god know we still praise him. I just need something from the bog when you are done!");
        }
        if (player.isWarrior()) {
            options.add("That is my sister!"); // Added dialogue for if warrior
        }
        if (player.isOrc() && player.isWarrior()) {
            options.add("The girl is too skinny! Not enough " +
                    "fat or muscle to please our god. Let me take her instead, go hunt a big fat boar!"); // If orc and warrior
        }
        if (!player.isWarrior()) {
            options.add("*Hunker down and hope they think it was a critter.*"); // If anything else than warrior
            options.add("*Crawl away back to the swamp path.*");
        }

        // Remaining options
        options.add("*Rise and charge at them to save the girl*");
        options.add("*Mimic the sound of some horrid and big animal to scare them away.*");

        return options;
    }

    public String handleOrcDialogue(int choice, Player player) {
        NPC orc1 = npcs.get("offering_orc_1");
        NPC orc2 = npcs.get("offering_orc_2");
        List<String> options = getOrcDialogue(player);

        // TODO Implement all the dialogue choices for orcs



    }
    // EPILOGUE
    public String getEpilogue(Player player){
        if (player.hasFlag("dragon_will_help")){
            return "The dragon lets you climb onto its back, it says nothing to you but keeps it promise of safe travel down\n" +
                    "below. It places you at the clearing in the forest and bids you no farewell before surge of wind knocks you\n" +
                    "off your feet as it takes off once more towards its cave."; // If dragon helped you down

        } else if (player.hasFlag("dragon_will_help") && player.isWarrior() && player.hasFlag("killed_merchant")) {
            return "The dragon lets you climb onto its back, it says nothing, but at your request it does take you to your home\n" +
                    "village where your sister took the merchants horses to. It places you down and bids you no farewell before\n" +
                    "surge of wind knocks you off your feet as it takes off once more towards its cave. Your sister and mother\n" +
                    "come running towards you and jumps into your embrace."; // If dragon helped you down as a warrior and merchant is dead

        } else if (player.hasFlag("dragon_will_help") && player.isWarrior() && player.hasFlag("merchant_quest_complete")) {
            return "The dragon lets you climb onto its back, it says nothing, but at your request it does take you to the swamp\n" +
                    "town where your sister and the merchant are. It places you down and bids you no farewell before surge of\n" +
                    "wind knocks you off your feet as it takes off once more towards its cave. Your sister comes running towards\n" +
                    "you and jumps into your embrace."; // Dragon helped you and you're a warrior and you finished merchant quest

        } else if (player.hasFlag("dragon_will_not_help")) {
            return "You look down the dizzying tall peak, you flew up here, it took not a moment for a dragon, but for you it will\n" +
                    "take days, weeks. You have so little food left, but at the very least you can chew snow for water you\n" +
                    "suppose.\n" +
                    "It is one step at a time, and by the time you finally do reach the bottom, you are worse for wear. Scratches,\n" +
                    "frost bite, and disease ravage through your body. Something it will never truly heal from, but at least you\n" +
                    "are still alive."; // Dragon doesn't help you down

        } else if (player.hasFlag("dragon_will_not_help") && player.isWarrior()) {
            return "You look down the dizzying tall peak, you flew up here, it took not a moment for a dragon, but for you it will\n" +
                    "take days, weeks. You have so little food left, but at the very least you can chew snow for water you\n" +
                    "suppose.\n" +
                    "It is one step at a time, the thoughts of your sister fill you with determination, and by the time you finally do\n" +
                    "reach the bottom, you are worse for wear. Scratches, frost bite, and disease ravage through your body.\n" +
                    "Something it will never truly heal from, but at least you are still alive. You are not sure why you still live, but\n" +
                    "likely it is the deep oath to yourself and your mother that kept you going at the hardest of times.\n"; // Dragon didn't help you down as warrior

        } else if (player.hasFlag("attacked_stag")) {
            return "You did it, you stand covered in the blood of godhood, you are bathed by the few sunrays that dare make its\n" +
                    "way inside the crack, the dragon staring in disbelief. You, YOU DID IT. You killed the deity like creature that\n" +
                    "brought life to the world and now, in your lust for power, you stand at the very peak of the world, and it\n" +
                    "should tremble for what is to come.";
        } else {
            return "";
        }
    }
}









