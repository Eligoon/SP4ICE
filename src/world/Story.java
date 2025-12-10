package world;

import util.TextUI;
import world.Location;

import java.util.HashMap;
import java.util.Map;

public class Story {
    TextUI ui = new TextUI();
    // Make map for connecting locations
    private Map<String, Location> locations;

    // Make each Location available individually
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

    public Story(){
        locations = new HashMap<>();
    }

    // Displaying the welcome message
    public void displayWelcomeMessage(){
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
    public void loadStory(){
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

        // Connect locations
        connectLocations();
    }

    // Connects all the locations based on the world map
    private void connectLocations(){
        // The Clearing connected locations
        theClearing.addConnectedLocation("north", huntingCabin);
        theClearing.addConnectedLocation("south",theLake);
        theClearing.addConnectedLocation("east",theForestPath);

        // The Hunting Cabin connected locations
        huntingCabin.addConnectedLocation("south", theClearing);

        // The lake connected locations
        theLake.addConnectedLocation("north", theClearing);

        // The forest path connected locations
        theForestPath.addConnectedLocation("north", theMountainPath);
        theForestPath.addConnectedLocation("south", theTravellingMerchant);
        theForestPath.addConnectedLocation("east", theSwampPath);
        theForestPath.addConnectedLocation("west",theClearing);

        // The offering bog connected locations
        theOfferingBog.addConnectedLocation("north", theSwampPath);

        // The swamp path connected locations
        theSwampPath.addConnectedLocation("south",theOfferingBog);
        theSwampPath.addConnectedLocation("west", theForestPath);
        theSwampPath.addConnectedLocation("north", theFrozenBog);
        theSwampPath.addConnectedLocation("east",theMurkyWaters);

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
        theRootsOfTheMountain.addConnectedLocation("east",theFreezingPass);
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

    public Location getLocation(String locationName){
        return locations.get(locationName);
    }
}
