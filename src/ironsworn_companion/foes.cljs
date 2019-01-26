(ns ironsworn-companion.foes)

;; This work is based on Ironsworn (found at www.ironswornrpg.com), created by Shawn Tomkin,
;; and licensed for our use under the Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International license
;; (creativecommons.org/licenses/by-nc-sa/4.0/).

(def all-foes
  [{:name "Broken"
    :npc-type "Ironlander"
    :lvl "Troublesome"
    :features ["Crazed eyes"
               "Painted skin"
               "Feral screams"
               "Scavenged clothing and weapons"]
    :drives ["Show my power"
             "Share my pain"]
    :tactics ["Spring from hiding"
              "Ferocious attack"]
    :description "Another people sailed to the Ironlands from the Old World long before our kin settled here. Something happened. Something changed them. Whether it was the long struggle in a harsh land, the ravages of war, or the corruption of some dark force, they left their humanity behind and became what we call the broken. Now, they exist only to kill, to destroy. We fear the broken for their savagery. But, more than this, we fear them as a dark portent of what we might one day become."
    :starter "Years ago, an Ironlander child was taken by a broken tribe. Now they are seen living among them. What is your connection to this person? Can they be brought home, or are they forever lost?"}
   {:name "Common Folk"
    :npc-type "Ironlander"
    :lvl "Troublesome"
    :features ["Diverse looks"
               "Weary and worried"
               "Suspicious of strangers"]
    :drives ["Prepare for the winter"
             "Protect family"]
    :tactics ["Desperate defense"
              "Stand together"]
    :description "Most of us in the Ironlands are common folk. We are farmers, laborers, crafters, sailors, and traders. When trouble comes, we know which way the pointy end goes, and we stand together to protect our homes and kin."
    :starter "Two prominent families are at odds. What is the source of the conflict? What is your relationship to them? What danger threatens to destroy their community if they can’t put aside their petty squabble?"}
   {:name "Hunter"
    :npc-type "Ironlander"
    :lvl "Dangerous"
    :features ["Wearing hides and furs to ward away the cold"
               "Steely gaze"
               "At home in the woodlands"]
    :drives ["A clean kill"
             "Survive the hunt"]
    :tactics ["Set traps"
              "Keep to the shadows"
              "Deadly shot"]
    :description "Hunters face brutal weather, difficult terrain, dangerous animals, and worse. Many never return from their hunts. Others return, but are forever changed."
    :starter "A hunter returns to her village, panic-stricken and pleading for help. The rest of her party is still out there. What happened to them?"}
   {:name "Mystic"
    :npc-type "Ironlander"
    :lvl "Dangerous"
    :features ["Knowing eyes"
               "Tattooed skin"]
    :drives ["Respect the old ways"
             "Seek the paths of power"]
    :tactics ["Foresee the intent of my enemies"
              "Prepare rituals"
              "Use trickery"]
    :description "Some say you can tell a mystic by looking them in the eye. They walk in two worlds, and their eyes shimmer with that dark reflection of realms beyond our own. We call it the sight. Some hold that darkness in check. Others are consumed by it."
    :starter "A mystic returns to their home after a years-long journey. They are changed. What new power or knowledge do now they wield? What do they seek to do with it? Why do you oppose them?"}
   {:name "Raider"
    :npc-type "Ironlander"
    :lvl "Dangerous"
    :features ["Geared for war"
               "Battle fervor"]
    :drives ["What is theirs will be ours"
             "Stand with my kin"
             "Die a glorious death"]
    :tactics ["Intimidate"
              "Shield wall"
              "Burn it down"]
    :description "Raiders survive by seizing what they need from others. Our grain. Our meat. Our animals. Our iron. They’ll take it all, and leave us facing the long winter with nothing to sustain us but prayers to indifferent gods."
    :starter "You were raised as a raider, born to battle, but long ago left that life. Troubled by your past, you vow to wipe this powerful clan from the Ironlands. How can you defeat them? What will happen when you must face your former shield-kin?"}
   {:name "Warrior"
    :npc-type "Ironlander"
    :lvl "Dangerous"
    :features ["Battle-hardened"
               "Scarred"]
    :drives ["The thrill of the fight"
             "Protect those in my charge"
             "Survive another day"]
    :tactics ["Maneuver for advantage"
              "Find an opening"]
    :description "Some Ironlanders, through strength of arms, set themselves apart from the common rabble. They are trained to fight, or simply born to it. For them, a sword, spear, or axe is as natural a tool as any hammer or spade."
    :starter "A legendary warrior, now well past their prime, swears to face a daunting foe in one final battle. What help do they ask of you and why? Who is their enemy?"}
   {:name "Elf"
    :npc-type "Firstborn"
    :lvl "Dangerous"
    :features ["Large, luminous eyes seen through a wooden mask"
               "Gray-green skin the texture of dry leaves"
               "Sonorous voice"
               "Wielding bow and spear"]
    :drives ["Protect the wilds"
             "Drive out trespassers, or see them pay"]
    :tactics ["Strike from shadow"
              "Force their surrender"
              "Turn the forest against them"]
    :description "Elves are strange beings of the forest, seldom seen beyond the ancient woods of the Deep Wilds. They are fiercely protective of their lands and suspicious of humans. Their scouts patrol the borderlands, riding the fearsome mounts we call gaunts. Others of their kind watch us from the shadow of the deep woods, spears and bow at the ready. Some say elven mystics can bind the animals and beasts of the forest to aid in the defense of the Wilds. A few warn that the elves are biding their time, readying the attack which will drive us from these lands."
    :starter "The leader of an Ironlander community seeks an audience with the elves. For what purpose? Why are you compelled to help?"}
   {:name "Giant"
    :npc-type "Firstborn"
    :lvl "Extreme"
    :features ["Dark hair and ruddy skin"
               "Twice the size of a tall human, or more"
               "Wearing layers of wool, hide, and furs"
               "Stoic and observant"]
    :drives ["Survive the winter"
             "Protect the herd"]
    :tactics ["Fight as a last resort"
              "Sweeping strike"
              "Make them flee"]
    :description "Giants dwell in the Tempest Hills and Veiled Mountains. They live a nomadic life alone or in small family units, herding oxen, mountain goats, and sheep. In their own language they are called the Jokul. Many Ironlanders misinterpret their quiet nature for dullness, but giants are keenly intelligent and observant. They have a great respect for life, even for our kind, and use trickery and negotiation to avoid fights. When they are left without other options, an enraged giant is a devastating, relentless force."
    :starter "A pair of giants are raiding human settlements, stealing supplies and livestock. With winter coming, the survival of those settlements is threatened. What is driving the giants down from the hills?"}
   {:name "Primordial"
    :npc-type "Firstborn"
    :lvl "Extreme"
    :features ["Personification of the natural world"
               "Turbulent, changing visage"
               "Vaguely human-like or animal-like form"]
    :drives ["Embody chaos"
             "Cling to vestiges of power"]
    :tactics ["Control the elements"
              "Destroy with primal rage"]
    :description "The primordials, said to be the vestigial spirits of long-forgotten gods, are the most ancient of the firstborn. Each embodies some aspect of the natural world, bound in a crude mimicry of a human or large animal. A river primordial is a mass of rock, gravel, and flowing water. A forest primordial is formed of wood, earth, rocks, and plants. A mountain primordial is a lumbering being of glacier stone and ice. A fire primordial, depending on its mood, might take form as embers, ash, and smoke—or as a raging pyre. They range in size from the height of an Ironlander to half-again as tall as a giant. Rumors persist of primordials who dwell in the deepest parts of the Wilds, or high in the ranges of the Veiled Mountains, who are as tall as an ancient tree. Beyond, some suggest, in the Shattered Wastes, live primordials who tower into the clouds. Is the sound of distant thunder sometimes the footfalls of mountain-sized primordials who dwell beyond the edge of the known world? Primordials are solitary beings as unpredictable as the natural forces they personify. They might ignore you. They might lurk at a distance, as if observing you. Or, they might attack. They do not speak in any language we can understand. Some suggest they have no intelligence, and are merely a manifestation of the natural world, no different than a winter storm. How do you kill a primordial? Most scoff at the idea. You are just as likely to kill the rain or the sea. A mystic might tell you to use a weapon imbued with elemental power. Don’t trust them. If you see a primordial, keep your distance. Better yet, run."
    :starter "In the dead of winter, a fire primordial is razing homes and burning a nearby wood. At night, orange flames light the sky. What can be done to stop this destruction?"}
   {:name "Troll"
    :npc-type "Firstborn"
    :lvl "Formidable"
    :features ["Long limbs"
               "Sunken, beady eyes"
               "Translucent skin camouflaged to the environment"
               "Keen sense of smell"
               "Speaks in gibberish"]
    :drives ["Find pretty things"
             "Keep it secret"]
    :tactics ["Be sneaky"
              "Bite and claw"
              "Run and hide"]
    :description "Trolls mostly live in the Flooded Land, but it’s not unusual to encounter one in the Hinterlands or even in the southern reaches of the Havens. They are solitary creatures, wary of contact with Ironlanders but likely to attack if scared or provoked. They move with their back hunched, often skulking on all four gangly limbs. When they stand straight they are much taller than humans—nearly as tall as a giant. Their skin is a sickly pale gray, but they can camouflage themselves by changing it to match their environment. Trolls collect objects of all sorts, and particularly value Ironlander trinkets. They are tormented by the fear of others stealing their hoard, and are constantly seeking out new, better hiding places. The items are mostly junk to anyone but a troll, but occasionally an object of real value finds its way into the dregs."
    :starter "The villagers tolerate the troll who lives nearby because its presence serves to dissuade a greater threat. They even donate items for its hoard, and put up with its occasional thievery. But now, the troll is missing. What is the looming threat the troll helped avert?"}
   {:name "Varou"
    :npc-type "Firstborn"
    :lvl "Dangerous"
    :features ["Yellow eyes shining in moonlight"
               "Pointed ears and snout-like face"]
    :drives ["Take their land"
             "Defend my kin"
             "Keep the bloodcall at bay"]
    :tactics ["Strike at night"
              "Leap into combat"
              "Let loose the bloodcall"]
    :description "The varou are humanoid beings who dwell within the Deep Wilds and in the woods of the Hinterlands. Their features are fierce and wolf-like. They are broad-shouldered and a head taller than the average Ironlander. Their long hair is ornately groomed and decorated with beads and other trinkets. The varou value territory above all things. They often war amongst themselves and against the elves to gain or defend holdings. They mark their claims by carving clan symbols into trees. Only the foolish ignore the warning of these border signs. Several of our settlements —built too close to varou territory— are now abandoned ruins bearing the mark of a victorious varou clan."
    :starter "A varou clan has carved their mark into the trees surrounding an Ironlander community, claiming it as their territory. An attack is surely imminent. What will you do to prevent it?"}
   {:name "Bear"
    :npc-type "Animals"
    :lvl "Formidable"
    :features ["Fearsome teeth and claws"
               "Thick hide"]
    :drives ["Find food"
             "Defend cubs"]
    :tactics ["Roar"
              "Pin down"
              "Maul with savage force"]
    :description "Most bears are not aggressive. They avoid Ironlanders and are unlikely to attack unless they see you as a threat. There are exceptions. The silver bears of the Veiled Mountains, which sometimes range as far south as the Tempest Hills, are territorial, powerful, and aggressive. Likewise, the ash bear, encountered in woodlands throughout the Ironlands, is known for its ferocity and cunning. If either catch your scent, they are likely to hunt you down and attack."
    :starter "A group of hunters felled a large ash bear with several arrows. It tumbled into a river and was swept away. Unfortunately, the bear they thought dead is now stalking the group as they make their way back home."}
   {:name "Boar"
    :npc-type "Animals"
    :lvl "Dangerous"
    :features ["Wiry coat"
               "Long tusks"
               "Vicious"]
    :drives ["Forage"
             "Protect territory"
             "Defend sows"]
    :tactics ["Charge and gore"
              "Circle and attack again"]
    :description "In the Old World, wild boars were belligerent and dangerous animals. Here inthe Ironlands? They are even bigger and meaner. They attack without warning or provocation. They will run you down, gore you, bite you, and circle around to do it all again. And again. And again."
    :starter "A boar hunt ends in tragedy when an Ironlander is gored and grievously wounded. How do you know this person? What terrible truth do they reveal as they lay dying?"}
   {:name "Gaunt"
    :npc-type "Animals"
    :lvl "Dangerous"
    :features ["Horse-like creature with a lean, skeletal frame"
               "Ghostly pale eyes"
               "Black, scaled hide"]
    :drives ["Run like the wind"]
    :tactics ["Rear up"
              "Charge"
              "Trample"]
    :description "A gaunt is a creature unique to the Ironlands. They maneuver across the rough, dense terrain of the Deep Wilds and Hinterlands with uncanny speed and grace. This makes them ideal as mounts for the elves, who breed and train them. A gaunt will not usually act aggressively without provocation, but they are as deadly as the fiercest warhorse under the command of a talented rider."
    :starter "Villages in the Hinterlands have fallen prey to a large band of gaunt-riding elves. They attack with sudden and violent force, and are gone before any sort of defense can be mustered. Their leader, a warrior of unmatched skill, rides a distinctive white gaunt. What has driven these elves to strike out against the Ironlanders?"}
   {:name "Marsh Rat"
    :npc-type "Animals"
    :lvl "Troublesome"
    :features ["Beady eyes"
               "Long tail"]
    :drives ["Eat everything"
             "Breed"]
    :tactics ["Swarm and bite"]
    :description "The marsh rat is a rodent of unusual size. They are all-too-common in the Flooded Lands or in wetlands within the Hinterlands and Deep Wilds. They eat almost anything, including carrion and waste. Our grain stores and pantries are an easy target for marsh rats, who dig tunnels or chew through walls to get at the food. They will also try to make a meal out of living prey — deer, cattle, or even an unlucky Ironlander. A pack of marsh rats can kill a horse and reduce it to bone in a matter of hours."
    :starter "Marsh rats raided the stores of an isolated settlement. How will you ensure the Ironlanders have enough food to survive the coming winter?"}
   {:name "Wolf"
    :npc-type "Animals"
    :lvl "Dangerous"
    :features ["Keen senses"]
    :drives ["Fight rivals"
             "Mark territory"
             "Run with the pack"]
    :tactics ["Stalk"
              "Pack rush"
              "Drag to the ground"]
    :description "The Ironlands are home to several breeds of wolves. Most are not aggressive and stay clear of settlements and travelers. Despite that, attacks against Ironlanders are not unknown. A harsh winter and insufficient prey can drive a pack to hunt livestock or even an unwary Ironlander. As night falls we hear their howls, and hope they are well fed."
    :starter "You find the grisly remains of a pack of wolves. All are dead, even the cubs. What caused this? Why is it a harbinger of a greater danger?"}
   {:name "Basilisk"
    :npc-type "Beasts"
    :lvl "Extreme"
    :features ["Giant snake"
               "Dull yellow-brown skin"
               "Vibrant yellow eyes"]
    :drives ["Devour"]
    :tactics ["Lay in wait"
              "Mesmerizing gaze"
              "Sudden bite"
              "Crush"]
    :description "Basilisks dwell in the Flooded Lands, lurking in the murky waters of the swamps or within marshy thickets. There, they wait patiently for prey. They regularly feed on marsh rats or deer, but will eagerly make a meal out of a passing Ironlander."
    :starter "The adventurer set out to slay a basilisk, only to become its next meal. Because the serpent digests its prey slowly, the remains of the adventurer are still undoubtedly within the beast—along with the heirloom sword he wielded. What is your relationship to this person? Why is recovering the sword so important to you?"}
   {:name "Elder Beast"
    :npc-type "Beasts"
    :lvl "Extreme"
    :features ["Twice the size of their common kin, or more"]
    :drives ["Dominate"
             "Protect territory"]
    :tactics ["Intimidating display"
              "Overwhelming attack"]
    :description "Elder beasts—including wolves, bears, and boars—are huge, monstrous versions of their common kin. They are primarily solitary creatures, though elder wolves have been known to lead a pack of normal wolves. Some call them guardians, avatars of the land itself, and say they are as long-lived as the oldest trees."
    :starter "An elder wolf, white as snow, appears to you in a dream. When you wake, the memory of its piercing gaze lingers. Is the vision a dark portent or a promise? Why are you compelled to seek this beast out?"}
   {:name "Harrow Spider"
    :npc-type "Beasts"
    :lvl "Dangerous"
    :features ["Massive fangs"
               "Long legs and bloated body"
               "Eight iridescent black eyes"]
    :drives ["Lurk"
             "Feed"]
    :tactics ["Drop atop prey"
              "Bite with pincers"
              "Trap in webbing"]
    :description "These gigantic creatures are a menace in woodlands throughout the Ironlands. Despite their size, they move through high branches with uncanny grace, dropping suddenly to grapple their prey and entomb them in webbing."
    :starter "A brood of harrow spiders attacked a contingent of Ironlanders. The single survivor tells of the horrifying encounter and the monstrous brood mother — a harrow spider larger and stronger than a warhorse. What was this group’s mission? What important item are you sworn to recover from one of the victims?"}
   {:name "Leviathan"
    :npc-type "Beasts"
    :lvl "Epic"
    :features ["Massive bulk"
               "Flesh as tough as iron"
               "Cold black eyes"
               "Sinuous grace"]
    :drives ["Slumber in the depths"
             "Destroy those who trespass"]
    :tactics ["Rise from the depths"
              "Ram and swamp ships"
              "Devour prey whole"]
    :description "These massive sea beasts lurk in the darkness of the deepest fjords and in the abyssal depths beyond the Barrier Islands. They sometimes surface to hunt within shallower waters. They will indiscriminately destroy any Ironlander vessel which strays too close to their hunting grounds. Watchful sailors might catch sight of a leviathan circling their boat, studying them, in the moments before it attacks. Their dagger-shaped head is as tough and destructive as any battering ram, able to shatter a ship in a single blow."
    :starter "A leviathan lurks off the coast, preying on fishing boats and trade ships. Among the dead is someone important to you. Who is it? You have vowed to send this beast back to the depths, but doing so will require a mythic weapon — The Abyssal Harpoon, an Old World artifact said to be carved from the bones of a long-dead sea god. Where is this weapon rumored to be held?"}
   {:name "Mammoth"
    :npc-type "Beasts"
    :lvl "Extreme"
    :features ["Woolly fur"
               "Large head and curved tusks"
               "Prehensile trunk"]
    :drives ["Migrate to fertile ground"
             "Forage for food"
             "Protect the young of the herd"]
    :tactics ["Form a protective circle"
              "Charge"
              "Trample"
              "Gore"]
    :description "These beasts resemble the elephants of the Old World’s southern realms, but are larger and covered in a coat of thick fur. They travel in herds among the Tempest Hills, migrating south with the winter and north with the spring. They are not aggressive creatures, but are fearless and will fight to the death to protect their young. A herd of mammoths is an amazing and humbling sight, but smart Ironlanders keep their distance and stay downwind."
    :starter "A mammoth calf wanders alone into an Ironlander settlement. Why do you swear to reunite it with its herd?"}
   {:name "Wyvern"
    :npc-type "Beasts"
    :lvl "Extreme"
    :features ["Huge, bat-like wings"
               "Rows of knife-sized teeth"
               "Thick hide with a metallic sheen"
               "Long tail"]
    :drives [" Watch for prey from high above"
             "Feed"]
    :tactics ["Swoop down"
              "Snap up prey"
              "Fearsome roar"
              "Bash with tail"]
    :description "There are several breeds of wyverns in the Ironlands. To the west, tawny wyverns nest in the cliffs of the Barrier Islands and Ragged Coast, diving for fish in the surrounding waters. Inland, the verdant wyverns dwell in forested regions. The largest and most fearsome breed, the iron wyverns, hunt among the Tempest Hills and along the flanks of the Veiled Mountains. All wyverns have wolfish heads with wide jaws, thick bodies, and sinuous tails. They have short hind limbs and elongated forelimbs which extend along their wings. In flight, they are a terrifying but awe-inspiring creature. On the ground, they lumber heavily on all four limbs, their wings folded back, jaws agape, gaze fixed on their prey. They are the grim cruelty of the Ironlands given form. They are death."
    :starter "Ancient cave paintings in the Tempest Hills show humanoids riding atop wyverns. Perhaps these beasts can be tamed. Why are you obsessed with this possibility?"}
   {:name "Bonewalker"
    :npc-type "Horrors"
    :lvl "Dangerous"
    :features ["Skeletal corpse"
               "Yellowed bones"
               "Tattered remains of clothing and armor"]
    :drives ["Destroy life"]
    :tactics ["Rush with unexpected speed"
              "Attack with the weapons they bore in life"
              "Grasp and claw"]
    :description "Bonewalkers are human remains given unnatural life. The source of the dark energy animating them is a mystery. Some say it is the will of dark gods. Others say an ancient evil permeates this land and seeps into porous bones of the dead. Or, perhaps it is the work of corrupt mystics. Bonewalkers usually roam the location of their final resting place — a burial site, a cursed battlefield, or a settlement blighted by disease or violence. Nothing remains of their previous selves. They are soulless monsters driven only to destroy the living."
    :starter "A horde of bonewalkers marches relentlessly towards the Havens. What dark force has gathered this army of the undead? How will you stop them?"}
   {:name "Chimera"
    :npc-type "Horrors"
    :lvl "Extreme"
    :features ["Shambling mass of dead creatures and offal"
               "Rotting stench"]
    :drives ["Insatiable hunger"]
    :tactics ["Horrifying wail"
              "Relentless assault"
              "Claw, bite and rend"]
    :description "A chimera is the corrupted form of dead animal flesh given horrible life. Its body is a collection of various creatures, fused together into a twisted, massive entity which knows only pain and hunger. When a dozen blood-tinged eyes focus on you, when its gibbering mouths open at once to scream, your only hope is a quick death."
    :starter "Multiple chimera have spawned from the heart of a deep wood. What evil is at work there?"}
   {:name "Frostbound"
    :npc-type "Horrors"
    :lvl "Formidable"
    :features ["Mummified, desiccated flesh"
               "Frozen blue eyes"
               "A sorrowful, hollow scream"]
    :drives ["Absorb the warmth of the living"]
    :tactics ["Sense heat"
              "Life-draining grasp"]
    :description "Some who fall prey to the long winters or the wild storms of the northern regions are given a horrible new life as the frostbound. These animated corpses are cursed to forever seek out the warmth their death took from them."
    :starter "A group of frostbound lurk along a mountain trail. This path is the only safe route to the lowlands from a mining village."}
   {:name "Haunt"
    :npc-type "Horrors"
    :lvl "Formidable"
    :features ["Subtle, unsettling manifestations"
               "Appear as they did in life"
               "Lay bare the ravages of death"
               "Stench of the grave"]
    :drives ["Torment the living"
             "Find rest"]
    :tactics ["Vanish and reappear"
              "Horrifying visage"
              "Unleash chaos"]
    :description "Haunts are restless spirits bound to this world by a traumatic or unjust death. They may be tied to a location, an object, or even a person. A haunt who manifests as a physical being can be dispelled by overcoming them in a fight, but only temporarily. They will only be at peace when their death is avenged or resolved. Some say a haunt can be banished through a ritual, but few possess the knowledge."
    :starter "You are plagued by a haunt. Who is it? What do they want of you?"}
   {:name "Hollow"
    :npc-type "Horrors"
    :lvl "Extreme"
    :features ["Vaguely humanoid shape formed of earth, plants, and insects"
               "Empty black eyes behind an elven mask"
               "Smells of wet soil and dead things"]
    :drives ["See justice done"]
    :tactics ["Bash with savage strength"
              "Draw in a whirlwind of materials to reform and enlarge"
              "Envelop and suffocate"]
    :description "It is said that elves who die an unjust death or have cause to seek retribution can rise as a hollow. Their form is a rippling mass of dead leaves, plants, soil, carrion, and insects. They move with a nightmarish, shambling gait. Their face is the wooden mask they wore in life. Their voice is the rattle of the wind through dry leaves. As with haunts, they can be temporarily defeated but cannot be killed by physical means. They are a relentless force, bound to this world by a singular motivation — vengeance."
    :starter "A hollow terrorizes an Ironlander village. What does it seek? What will you do to stop it?"}
   {:name "Iron Revenant"
    :npc-type "Horrors"
    :lvl "Extreme"
    :features ["Empty, patchwork shell of armor and other hunks of metal"
               "Wielding iron weapons"
               "A low, reverberating voice"]
    :drives ["Fulfill the vow"
             "Destroy any who stand in their way"]
    :tactics ["Steadfast attacks"
              "Pull in iron with an unyielding, magnetic force"]
    :description "Some vows are held so fiercely that they survive even after death. An iron revenant is an incorporeal force of furious resolve, the unfinished vow of an Ironsworn given horrible form as a construct of metal. Attacks may slow them down or temporarily break apart their armored form, but they have no flesh to pierce and cannot be killed. An iron revenant won’t stop until their vow is fulfilled.A"
    :starter "Someone you knew has taken form as an iron revenant. Who is it? What is their vow?"}
   {:name "Sodden"
    :npc-type "Horrors"
    :lvl "Formidable"
    :features ["Milky eyes"
               "Mottled flesh"]
    :drives ["Drown the living"]
    :tactics ["Draw victims to the water"
              "Grab and scratch with jagged claws"
              "Chilling embrace"
              "Drag into the depths"]
    :description "A sodden is the restless spirit of someone who drowned or was put to rest in water. They can appear in seas, rivers, lakes, ponds, or marshes. Their loneliness and grief compels them to draw living victims into their watery lairs. A sodden is not confined to its resting place. In fact, some believe that surviving an encounter with a sodden leaves you vulnerable around any body of water until the spirit finishes its work."
    :starter "Someone you know died and appears to you as a sodden. Who are they? Can anything be done to put them to rest?"}])
