(ns ironsworn-companion.assets)

;; This work is based on Ironsworn (found at www.ironswornrpg.com), created by Shawn Tomkin,
;; and licensed for our use under the Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International license
;; (creativecommons.org/licenses/by-nc-sa/4.0/).

(def all-assets
  [
   {:name "Hawk"
    :asset-type "Companion"
    :description "Your hawk can aid you when it is aloft."
    :perks [{:id "Far-seeing"
             :enabled false
             :result "When you Untertake a Journey, or when you Resupply by hunting small game, add +1."}
            {:id "Fierce"
             :enabled false
             :result "When you Secure an Advantage +edge using your hawk to harass and distract your foes, add +1 and take +1 momentum on a hit."}
            {:id "Vigilant"
             :enabled false
             :result "When you Face Danger +wits to detect an approaching threat, or when you Enter the Fray +wits against an ambush, add +2."}]
    :res-counter {:current 3
                  :max 3}
    :custom-note ["Name" "Hawk"]}
   {:name "Horse"
    :asset-type "Companion"
    :description "You and your horse ride as one."
    :perks [{:id "Swift"
             :enabled false
             :result "When you Face Danger +edge using your horse's speed and grace, or when you Undertake a Journey, add +1."}
            {:id "Fearless"
             :enabled false
             :result "When you Enter the Fray or Secure an Advantage +heart by charging into combat, add +1 and take +1 momentum on a hit."}
            {:id "Mighty"
             :enabled false
             :result "When you Strike or Clash at close range while mounted, add +1 and inflict +1 harm on a hit."}]
    :res-counter {:current 5
                  :max 5}
    :custom-note ["Name" "Horse"]}
   {:name "Hound"
    :asset-type "Companion"
    :description "Your hound is your steadfast companion."
    :perks [{:id "Sharp"
             :enabled false
             :result "When you Gather Information using your hound's keen senses to track your quarry or investigate a scene, add +1 and take +1 momentum on a hit."}
            {:id "Ferocious"
             :enabled false
             :result "When you Strike or Clash alongside your hound and score a hit, inflict +1 harm or take +1 momentum."}
            {:id "Loyal"
             :enabled false
             :result "When you Endure Stress in the company of your hound, add +1."}]
    :res-counter {:current 4
                  :max 4}
    :custom-note ["Name" "Hound"]}
   {:name "Raven"
    :asset-type "Companion"
    :description "Your raven heeds your call."
    :perks [{:id "Sly"
             :enabled false
             :result "When you Secure an Advantage or Face Danger +shadow using your raven to perform trickery (such as creating a distraction or stealing a small object) add +1 and take +1 momentum on a hit."}
            {:id "Knowing"
             :enabled false
             :result "When you Face Death, add +2 and take +1 momentum on a hit."}
            {:id "Diligent"
             :enabled false
             :result "When your raven carries messages for you, you may Secure an Advantage, Gather Information or Compel from a distance."}]
    :res-counter {:current 2
                  :max 2}
    :custom-note ["Name" "Raven"]}
   {:name "Young Wyvern"
    :asset-type "Companion"
    :description "Your wyvern won't devour you. For now."
    :perks [{:id "Insatiable"
             :enabled false
             :result "When you Undertake a Journey and score a hit, you may suffer -1 supply in exchange for +1 momentum."}
            {:id "Indomitable"
             :enabled false
             :result "When you make the Companion Endure Harm move for your wyvern, add +2 and take +1 momentum on a hit."}
            {:id "Savage"
             :enabled false
             :result "When you Strike by commanding your wyvern to attack, roll +heart. Your wyvern inflicts 3 harm on a hit."}]
    :res-counter {:current 5
                  :max 5}
    :custom-note ["Name" "Young Wyvern"]}
   {:name "Banner-Sworn"
    :asset-type "Path"
    :description "Once you mark a bond with a leader or faction ..."
    :perks [{:id :first
             :enabled true
             :result "When you Sojourn or Make Camp in the company of your banner-kin, add +1 and take +1 momentum on a hit."}
            {:id :second
             :enabled false
             :result "When you Swear an Iron Vow to serve your leader or faction on a mission, you may reroll any dice. When you Fullfill Your Vow and mark experience, take +1 experience."}
            {:id :third
             :enabled false
             :result "When you Enter the Fray bearing your banner, add +1 and take +1 momentum on a hit. When you burn momentum while carrying your banner in combat, take +1 momentum after you reset."}]
    :custom-note ["Name" "To Whom?"]}
   {:name "Battle-Scarred"
    :asset-type "Path"
    :description "Once you become maimed..."
    :perks [{:id :first
             :enabled false
             :result "You focus your energies: Reduce your edge or iron by 1, and add up to +2 to wits or heart, or +1 to each (to a maximum of +4)."}
            {:id :second
             :enabled false
             :result "You overcome your limitations: Reduce your maximum health or spirit by 1 and ignore the max momentum penalty for being maimed."}
            {:id :third
             :enabled false
             :result "You have stared down death before: When you are reduced to 0 health, take +1 momentum. When you Face Death, add +1."}]}
   {:name "Blade-Bound"
    :asset-type "Path"
    :description "Once you mark a bond with a kin-blade, a sentient weapon imbued with the spirit of your ancestor..."
    :perks [{:id :first
             :enabled false
             :result "When you Enter the Fray or Draw the Circle while wielding your kin-blade, take +1 momentum on a hit."}
            {:id :second
             :enabled false
             :result "When you Gather Information and fail to score a strong hit, you may listen to the whispers of your kin-blade for guidance and reroll any dice. If you do, Endure Stress (2 stress)."}
            {:id :third
             :enabled false
             :result "When you inflict harm with your kin-blade, you may inflict +2 harm. If you do, Endure Stress (2 stress)."}]
    :custom-note ["Name" "Kin-Blade"]}
   {:name "Devotant"
    :asset-type "Path"
    :perks [{:id :first
             :enabled true
             :result "When you say your daily prayers, you may Secure an Advantage by asking your god to grant you a blessing. If you do, roll +your god's stat. On a hit, take +1 momentum."}
            {:id :second
             :enabled false
             :result "When you Swear an Iron Vow to serve your god on a divine quest, you may roll +your god's stat and reroll any dice. When you Fullfill Your Vow and mark experience, take +1 experience."}
            {:id :third
             :enabled false
             :result "When you Sojourn, and spread the word of your god, you may roll +your god's stat. If you do, take +1 momentum on a hit."}]
    :custom-note ["God Name (Stat)" "Name (Stat)"]}
   {:name "Herbalist"
    :asset-type "Path"
    :perks [{:id :first
             :enabled true
             :result {:description "When you attempt to Heal using herbal remedies, and you have at least +1 supply, you may choose one (decide before rolling)."
                      :options [["Prepare" "Add +2."]
                                ["Expend" "On a hit, take or give an additional +1 health."]]
                      :random-event nil}}
            {:id :second
             :enabled false
             :result "When you Heal a companion, ally, or other character, and you score a hit, take +1 spirit or +1 momentum."}
            {:id :third
             :enabled false
             :result "When you Make Camp and choose the option to partake, you can use your supplies to create a restorative meal. If you do, you and your companions may take +1 health. Any allies who choose to partake may also take +1 health."}]}
   {:name "Honorbound"
    :asset-type "Path"
    :perks [{:id :first
             :enabled true
             :result "When you Turn the Tide, envision how your vows give you strength in this moment. Then, add +2 (instead of +1) when you make your move."}
            {:id :second
             :enabled false
             :result "When you Secure an Advantage or Compel by telling a hard truth, add +1 and take +1 momentum on a hit. On a weak hit or miss, envision how this truth complicates your current situation."}
            {:id :third
             :enabled false
             :result "When you Fullfill Your Vow and score a weak hit or miss, you may reroll any dice as you Swear an Iron Vow to set things right. On a hit, take +2 momentum."}]}
   {:name "Loyalist"
    :asset-type "Path"
    :perks [{:id :first
             :enabled true
             :result "When you Aid Your Ally, add +1 and take +1 momentum on a hit. This is in addition to the benefits taken by your ally."}
            {:id :second
             :enabled false
             :result "When an ally makes the Endure Stress move in your company, they add +1 and you take +1 momentum on a hit."}
            {:id :third
             :enabled false
             :result {:description "When you stand with your ally as they make a progress move, envision how you support them."
                      :options nil
                      :random-event [[10 "Your ally may replace one of their challenge dice with a 1."]
                                     [20 "Your ally may replace one of their challenge dice with a 2."]
                                     [30 "Your ally may replace one of their challenge dice with a 3."]
                                     [40 "Your ally may replace one of their challenge dice with a 4."]
                                     [50 "Your ally may replace one of their challenge dice with a 5."]
                                     [60 "Your ally may replace one of their challenge dice with a 6."]
                                     [70 "Your ally may replace one of their challenge dice with a 7."]
                                     [80 "Your ally may replace one of their challenge dice with a 8."]
                                     [90 "Your ally may replace one of their challenge dice with a 9."]
                                     [100 "Envision how you inadvertently undermine their action. Your ally must replace their lowest challenge die with a 10."]]}}]}
   {:name "Masked"
    :asset-type "Path"
    :description "Once you mark a bond with elves, and are gifted with a mask of precious elderwood..."
    :custom-note ["Mask" "X-wood: Stat / Resource"]
    :perks [{:id :first
             :enabled true
             :result {:description "Choose your mask's material. When you wear the mask and make a move which uses its stat, add +1. If you roll a 1 on your action die, suffer -1 to the associated track (in addtion to any other outcome of the move)."
                      :options [["Thunderwood" "Edge / Health"]
                                ["Bloodwood" "Iron / Health"]
                                ["Ghostwood" "Shadow / Spirit"]
                                ["Whisperwood" "Wits /Spirit"]]
                      :random-event nil}}
            {:id :second
             :enabled false
             :result "As above, and you may instead add +2 and suffer -2 (decide before rolling)."}
            {:id :third
             :enabled false
             :result "When you Face Death or Face Desolation while wearing the mask, you may roll +its stat (instead of +heard)."}]}
   {:name "Ritualist"
    :asset-type "Path"
    :description "Once you Fullfill Your Vow (formidable or greater) in service to an elder mystic, and Forge a Bond to train with them..."
    :perks [{:id :first
             :enabled true
             :result "When you Secure an Advantage to ready yourself for a ritual, envision how you prepare. Then, add +1 and take +1 momentum on a hit."}
            {:id :second
             :enabled false
             :result "When you perform a ritual, you may suffer -1 supply and add +1 (decide before rolling)."}
            {:id :third
             :enabled false
             :result "When you tattoo the essence of a new ritual onto your skin, envision the mark you create. You may then purchase and upgrade that ritual asset for 1 less experience."}]}
   {:name "Shadow-Kin"
    :asset-type "Path"
    :description "Once you become corrupted..."
    :perks [{:id :first
             :enabled false
             :result "You harden your heart: Reduce your heart stat by 1 and add up to +2 to shadow (to a maximum of +4)."}
            {:id :second
             :enabled false
             :result "You are attuned to the realms of shadow: When you perform a ritual, add +1."}
            {:id :third
             :enabled false
             :result "You know the sly ways of death: When you Face Death, you may roll +shadow (instead of +heart). You may also suffer -1 momentum and add +1 (decide before rolling):"}]}
   {:name "Sighted"
    :asset-type "Path"
    :perks [{:id :first
             :enabled true
             :result "When you Face Danger or Gather Information to identify or detect mystic forces, add +1 and take +1 momentum on a hit."}
            {:id :second
             :enabled false
             :result "When you Compel, Forge a Bond, or Test a Bond with a fellow mystic or mystical being, add +1 and take +1 momentum on a hit."}
            {:id :third
             :enabled false
             :result "When you Secure an Advantage by studying someone or something in a charged situation, add +1 and take +1 momentum on a hit. When you also pierce the veil to explore deeper truths (decide before rolling), you may reroll any dice. If you do, count a weak hit as a miss."}]}
   {:name "Slayer"
    :asset-type "Path"
    :perks [{:id :first
             :enabled true
             :result "When you Gather Information by tracking a beast or horror, or when you Secure an Advantage by readying yourself to fight against them, add +1 and take +1 momentum on a hit."}
            {:id :second
             :enabled false
             :result "When you Swear an Iron Vow to slay a beast or horror, you may reroll any dice. When you Fulfill Your Vow and mark experience, take +1 experience."}
            {:id :third
             :enabled false
             :result {:description "When you slay a beast or horror (at least formidable), you may take a trophy and choose one."
                      :options [["Power a ritual" "When you or an ally make a ritual move, reroll any dice (one time only)."]
                                ["Prove your worth" "When you Sojourn, reroll any dice (one time only)."]]
                      :random-event nil}}]}
   {:name "Storyweaver"
    :asset-type "Path"
    :perks [{:id :first
             :enabled true
             :result "When you Secure an Advantage, Compel or Forge a Bond by sharing an inspiring or enlightening song, poem, or tale, envision the story you tell. Then, add +1 and take +1 momentum on a hit."}
            {:id :second
             :enabled false
             :result "When you Make Camp and choose the option to relax, you may share a story with your allies or compose a new story if alone. If you do, envision the story you tell and take +1 spirit or +1 momentum. Any allies who choose to relax in your company may also take +1 spirit or +1 momentum."}
            {:id :third
             :enabled false
             :result "When you Sojourn within a community with which you share a bond, add +2 (instead of +1)."}]}
   {:name "Trickster"
    :asset-type "Path"
    :perks [{:id :first
             :enabled true
             :result "When you Face Danger, Secure an Advantage, or Compel by lying, bluffing, stealing, or cheating, add +1."}
            {:id :second
             :enabled false
             :result "When you Gather Information by investigating a devious scheme, you may roll +shadow (instead of +wits). If you do, take +2 momentum on a hit."}
            {:id :third
             :enabled false
             :result "When you confess a lie as you Forge a Bond, you risk rejection for the chance of a stronger bond. On a strong hit, take +2 momentum and mark one more tick. A weak hit counts as a miss."}]}
   {:name "Veteran"
    :asset-type "Path"
    :perks [{:id :first
             :enabled true
             :result "When you burn momentum to improve your result in combat, envision how your hard-won fighting experience gives you the upper hand. Then, take +1 momentum after you reset."}
            {:id :second
             :enabled false
             :result "When you Swear an Iron Vow to someone who fought beside you, or Forge a Bond with them, add +2 and take +2 momentum on a hit."}
            {:id :third
             :enabled false
             :result "When you Resupply by looting the dead on a field of battle, add +1 and take +1 momentum on a hit."}]}
   {:name "Wayfinder"
    :asset-type "Path"
    :perks [{:id :first
             :enabled true
             :result "When you Untertake a Journey and burn momentum to improve your result, envision how you reorient yourself. Then, take +2 momentum after you reset."}
            {:id :second
             :enabled false
             :result "When you Secure an Advantage or Gather Information by carefully surveying the landscape or scouting ahead, add +1 and take +1 momentum on a hit."}
            {:id :third
             :enabled false
             :result "When you Swear an Iron Vow to safely guide someone on a perilous journey, you may reroll any dice. When you Fullfill Your Vow and mark experience take +1 experience."}]}
   {:name "Wildblood"
    :asset-type "Path"
    :perks [{:id :first
             :enabled true
             :result "When you Face Danger, Secure an Advantage, or Gather Information using your knowledge of tracking, woodcraft, or woodland creatures, add+1."}
            {:id :second
             :enabled false
             :result "When you Face Danger or Secure an Advantage  by hiding or sneaking in the woodlands, add +1 and take +1 momentum on a hit."}
            {:id :third
             :enabled false
             :result "When you Make Camp in the woodlands, you may roll +wits (instead of +supply). If you do, you and your allies may each choose 1 more option on a hit."}]}
   {:name "Weaponmaster"
    :asset-type "Path"
    :description "Once you Fullfill Your Vow (formidable or greater in service to a seasoned warrior, and Forge a Bond to train with them..."
    :perks [{:id :first
             :enabled true
             :result "When you Secure an Advantage by sizing up you foe in a fight, or in a charged situation which may lead to a fight, add +1 and take +1 momentum on a hit."}
            {:id :second
             :enabled false
             :result "When you study or train in a new weapon or technique, you may obtain and upgrade that combat talent for 1 less experience."}
            {:id :third
             :enabled false
             :result "When you Turn the Tide with a sudden change of weapon or technique, and your next move is a Strike, you may add +1 and inflict +2 harm on a strong hit."}]}
   {:name "Wright"
    :asset-type "Path"
    :custom-note ["Speciality" "Choose one"]
    :perks [{:id :first
             :enabled true
             :result "When you Secure an Advantage by crafting a useful item using your speciality, or when you Face Danger to create or repair an item in a perilous situation, add +1 and take +1 momentum on a hit."}
            {:id :second
             :enabled false
             :result "As above, and you may suffer -1 supply (after you roll) to add an additional +1."}
            {:id :third
             :enabled false
             :result "When you give the item you create as a gift to commemorate an important event or relationship, you may (one time only) reroll any dice when you Compel, Forge a Bond, or Test a Bond."}]}
   {:name "Archer"
    :asset-type "Combat Talent"
    :description "If you wield a bow..."
    :perks [{:id :first
             :enabled true
             :result "When you Secure an Advantage +wits by taking a moment to aim, envision where you intend to land your shot. Then, add +1 and take +1 momentum on a hit."}
            {:id :second
             :enabled false
             :result "Once per fight, when you Strike or Clash, you may take extra shots and suffer -1 supply. If you do, reroll any dice. On a hit, inflict +2 harm and take +1 momentum."}
            {:id :third
             :enabled false
             :result "When you Resupply by hunting, add +1 and take +1 momentum on a hit."}]}
   {:name "Brawler"
    :asset-type "Combat Talent"
    :description "If you are unarmed or fighting with a non-deadly weapon..."
    :perks [{:id :first
             :enabled true
             :result "When you Secure an Advantage +iron by engagin in close-quarters brawling (such as hitting, tripping, or smashing), add +1. If you score a hit, you may also inflict 1 harm."}
            {:id :second
             :enabled false
             :result "When you Strike or Clash with brutal intent, you may inflict 2 harm (instead of 1)."}
            {:id :third
             :enabled false
             :result "When you Face Danger or Clash against a brawling attack (such as punches, kicks, slams, trips, or grapples), add +1."}]}
   {:name "Cutthroat"
    :asset-type "Combat Talent"
    :description "If you wield a dagger or knife..."
    :perks [{:id :first
             :enabled true
             :result {:description "When you are in postion to Strike at an unsuspecting foe, choose one (before rolling)."
                      :options [["Strike quickly" "Add +2 and take +1 momentum on a hit."]
                                ["Take aim" "Inflict +2 harm on a hit."]]
                      :random-event nil}}
            {:id :second
             :enabled false
             :result "When you Compel someone at the point of your blade, or when you rely on your blade to Face Danger, add +1."}
            {:id :third
             :enabled false
             :result "Once per fight, when you Secure an Advantage +shadow by performing a feint or misdirection, reroll any dice and +1 momentum on a hit."}]}
   {:name "Duelist"
    :asset-type "Combat Talent"
    :description "If you wield a bladed weapon in each hand..."
    :perks [{:id :first
             :enabled true
             :result "When you Clash or Strike, you may add +2. If you do (decide before rolling), inflict +1 harm on a strong hit and count a weak hit as a miss."}
            {:id :second
             :enabled false
             :result "Once per fight, when you Secure an Advantage +edge by making a bold display of your combat prowess, you may reroll any dice."}
            {:id :third
             :enabled false
             :result {:description "When you Draw the Circle, choose one (before rolling)"
                      :options [["Add..." "... +2"]
                                ["Take..." "... +2 momentum on a hit."]]
                      :random-event nil}}]}
   {:name "Ironclad"
    :asset-type "Combat Talent"
    :description "If you wear armor"
    :perks [{:id :first
             :enabled true
             :result {:description "When you equip or adjust your armor, choose one."
                      :options [["Lightly armored" "When you Endure Harm in a fight, add +1 and take +1 momentum on a hit."]
                                ["Geared for war:" "Mark encumbered. When you Endure Harm in a fight, add +2 and take +1 momenutm on a hit."]]
                      :random-event nil}}
            {:id :second
             :enabled false
             :result "When you Clash while you are geared for war, add +1."}
            {:id :third
             :enabled false
             :result "When you Compel in a situation where strength of arms is a factor, add +2."}]}
   {:name "Long-Arm"
    :asset-type "Combat Talent"
    :description "If you wield a staff..."
    :perks [{:id :first
             :enabled true
             :result "In your hands, a humble staff is a deadly weapon. You may inflict 2 harm (instead of 1)."}
            {:id :second
             :enabled false
             :result "When you Strike or Clash, you may roll +edge (instead of +iron). If you do, inflict 1 less harm in exchange for +1 momentum on a hit."}
            {:id :third
             :enabled false
             :result "When you Secure an Advantage using your staff to disarm, trip, shove or stun your foe, you may roll +edge (instead of +iron) and take +1 momentum on a hit."}]}
   {:name "Shield-Bearer"
    :asset-type "Combat Talent"
    :description "If you wield a shield..."
    :perks [{:id :first
             :enabled true
             :result "When you Face Danger using your shield as a cover, add +1. When you Clash in close quarters, take +1 momentum on a strong hit."}
            {:id :second
             :enabled false
             :result "When you bear a shield painted with a meaningful symbol, and you Endure Stress as you face off against a fearsome foe, add +1 and take +1 momentum on a hit."}
            {:id :third
             :enabled false
             :result "When forced to Endure Harm in a fight, you may instead sacrifice your shield and ignore all harm. If you do, your shield is detroyed or will require extensive repair; suffer -2 momentum."}]}
   {:name "Skirmisher"
    :asset-type "Combat Talent"
    :description "If you wield a spear..."
    :perks [{:id :first
             :enabled true
             :result {:description "When you Face Danger by holding a foe at bay using your spear's reach, roll +iron or +edge (your choice). If you score a strong hit, choose one."
                      :options [["Iron" "Strike now, and add +1."]
                                ["Edge" "Take +1 momentum"]]
                      :random-event nil}}
            {:id :second
             :enabled false
             :result "When you Strike or Clash in close combat and score a strong hit, you may drive your spear home and inflict +2 harm. If the fight continues, Face Danger +iron to retrieve your spear before using it again."}
            {:id :third
             :enabled false
             :result "When you Secure an Advantage by bracing your spear against a charging foe, add +1 and take +1 momentum on a hit."}]}
   {:name "Sunderer"
    :asset-type "Combat Talent"
    :description "If you wield an axe..."
    :perks [{:id :first
             :enabled true
             :result "When you Strike or Clash in close quarters, you may suffer -1 momentum and inflict +1 harm on a hit (decide before rolling)."}
            {:id :second
             :enabled false
             :result "When you have your axe in a hand, and use the promise of violence to Compel or Secure an Advantage, add +1 and take +1 momentum on a hit."}
            {:id :third
             :enabled false
             :result "When you make a tribute to a fallen foe (formidable or greater) by carving a rune in the haft of your axe, roll +heart. On a strong hit, inflict +1d6 harm (one time only) when you Strike or Clash. On a weak hit, as above, but this death weighs on you; Endure Stress (2 stress)."}]} ;; need to implement a no-move-roll-screen
   {:name "Swordmaster"
    :asset-type "Combat Talent"
    :description "If you wield a sword..."
    :perks [{:id :first
             :enabled true
             :result "When you Strike or Clash and burn momentum to improve your result, inflict +2 harm."}
            {:id :second
             :enabled false
             :result "When you Clash and score a strong hit, you may add +1 if you immediately follow with a Strike."}
            {:id :third
             :enabled false
             :result "When you Swear an Iron Vow by kneeling and grasping your sword's blade, add +1 and take +1 momentum on a hit. If you let the edge draw blood from your hands, Endure Harm (1 harm) in exchange for an additional +1 momentum on a hit."}]}
   {:name "Augur"
    :asset-type "Ritual"
    :perks [{:id :first
             :enabled true
             :result "When you summon a flock of crows and ask a single question, roll +wits. On a strong hit, you interpret their calls as a helpful omen. Envision the response (Ask the Oracle if unsure) and take +2 momentum. On a weak hit, the crows ignor your question and offer a clue to an unrelated problem or opportunity in this area. Envision what you learn (Ask the Oracle if unsure), and take +1 momentum."}
            {:id :second
             :enabled false
             :result "As above, and the crows will also help guide you on the proper path. On a hit, add +1 on the next segment when you Undertake a Journey."}
            {:id :third
             :enabled false
             :result "When you perform this ritual, add +1 and take +1 momentum on a hit."}]}
   {:name "Bind"
    :asset-type "Ritual"
    :perks [{:id :first
             :enabled true
             :result "When you wear an animal pelt and dance in moonlight, roll +wits. On a strong hit, you or an ally may wear the pelt and add +1 when making moves with the related stat (wolf-edge; bear-iron; deer-heart; fox-shadow; boar-wits). If the wearer rolls a 1 on their action die while making a move using the pelt, the magic is spent. On a weak hit, as above, but the wilds call as you dance; Endure Stress (2 stress)."}
            {:id :second
             :enabled false
             :result "As above, and you may instead perform this ritual wearing the pelt of a beast. If you do, name the related stat and add +2 instead of +1."}
            {:id :third
             :enabled false
             :result "When you perform this ritual, add +1 and take +1 momentum on a hit."}]}
   {:name "Communion"
    :asset-type "Ritual"
    :perks [{:id :first
             :enabled true
             :result "When you surround the remains of a recentyl deceased intelligent creature with lit candles, and summon its spirit, roll +heart. Add +1 if you share a bond. On a strong hit, the spirit appears and you may converse for a few minutes. Make moves as appropriate (add +1). On a weak hit, as above, but the spirit delivers troubling news unrelated to your purpose. Envision what it tells you (Ask the Oracle if unsure) and Endure Stress (1 stress)."}
            {:id :second
             :enabled false
             :result "As above, and you may also commune with the long-dead."}
            {:id :third
             :enabled false
             :result "When you perform this ritual, add +1 and take +1 momentum on a hit."}]}
   {:name "Divination"
    :asset-type "Ritual"
    :perks [{:id :first
             :enabled true
             :result "When you take a drop of blood from a willing subject (not yourself) and cast the rune-carved stones, roll +heart. On a strong hit, you may Gather Information about that person and people close to them (including insight you and the subject have no knowledge of) by reading the runes. If you do, add +1. On a weak hit, as above, but the answers are revealed only with extra time and focus; suffer -2 momentum."}
            {:id :second
             :enabled false
             :result "As above, and your divination can also reveal information about this person's future."}
            {:id :third
             :enabled false
             :result "When you perform this ritual, add +1 and take +1 momentum on a hit."}
            ]}
   {:name "Keen"
    :asset-type "Ritual"
    :perks [{:id :first
             :enabled true
             :result "When you hold a weapon and sing a keen for those it has killed, roll +heart. On a strong hit, the wielder inflicts +1 harm when they Strike or Clash. If they roll a 1 on their action die when making a move to inflict harm, the magic is spent. On a weak hit, as above, but the voices of those who were slain join in your song; Endure Stress (2 stress)."}
            {:id :second
             :enabled false
             :result "As above, and the wielder may also (one time only) add +1 and take +2 momentum on a hit when they Draw the Circle, Enter the Fray, or Batle."}
            {:id :third
             :enabled false
             :result "When you perform this ritual, add +1 and take +1 momentum on a hit."}]}
   {:name "Scry"
    :asset-type "Ritual"
    :perks [{:id :first
             :enabled true
             :result {:description "When you look into flames to study a remote person or location, roll +shadow. You or someone with you must have knowledge of the target. On a strong hit, you may Gather Information through observation using +shadow or +wits. On a weak hit, as above but the flames are hungry; choose one to sacrifice."
                      :options [["Your blood" "Endure Harm (2 harm)."]
                                ["A precious thing" "Endure Stress (2 stress)."]
                                ["Provisions" "Suffer -2 supply."]]
                      :random-event nil}}
            {:id :second
             :enabled false
             :result "As above, and you may study a past event."}
            {:id :third
             :enabled false
             :result "When you perform this ritual, add +1 and take +1 momentum on a hit."}]}
   {:name "Shadow-Walk"
    :asset-type "Ritual"
    :perks [{:id :first
             :enabled true
             :result "When you cloak yourself with the gossamer veil of the shadow realms, roll +shadow. On a strong hit, take +1 momentum. Then reroll any dice (one time only) when you make a move by ambushing, hiding, or sneaking. On a weak hit, as above, but the shadows try to lead you astray. You must first Face Danger to find your way."}
            {:id :second
             :enabled false
             :result "As above, and you may also travel along the hidden paths of the shadow realms to Undertake a Journey using +shadow (instead of +wits). If you do, Endure Stress (1 stress) and mark one extra unit of progress on a strong hit."}
            {:id :third
             :enabled false
             :result "When you perform this ritual, add +1 and take +1 momentum on a hit."}]}
   {:name "Sway"
    :asset-type "Ritual"
    :perks [{:id :first
             :enabled true
             :result "When you speak a person's name three times to the wind, roll +wits. On a strong hit, the wind whispers of this person's need. Envision what you hear (Ask the Oracle if unsure). If you use this information or fullfill this need when you Compel them, you may reroll any dice (one time only). On a weak hit, as above, but this person's need creates a troubling dilemma or complication; Endure Stress (1 stress)."}
            {:id :second
             :enabled false
             :result "As above, and if you roll a strong hit when you Compel, you may also reroll any dice (one time only) when you Gather Information from this person."}
            {:id :third
             :enabled false
             :result "When you perform this ritual, add +1 and take +1 momentum on a hit."}]}
   {:name "Totem"
    :asset-type "Ritual"
    :perks [{:id :first
             :enabled true
             :result "When you hold a totem of your animal companion and focus on it, roll +heart. On a strong hit, you are bound together. Add +1 and take +1 momentum on a thit when you use a companion ability. If you roll a 1 on your action die when using a companion ability, the magic is spent. On a weak hit, as above, but creating this connection is unsettling; Endure Stress (1 stress)."}
            {:id :second
             :enabled false
             :result "As above, and you may also perceive the world through your companion's senses while you make moves aided by them (even when you are apart)."}
            {:id :third
             :enabled false
             :result "When you perform this ritual, add +1 and take +1 momentum on a hit."}]}
   {:name "Visage"
    :asset-type "Ritual"
    :perks [{:id :first
             :enabled true
             :result "When you paint yourself in blood and ash, roll +wits. On a strong hit, you may add +2 and take +1 momentum on a hit when you Secure an Advantage or Compel using fear or intimidation. If you roll a 1 on your action die when making a move aided by your visage, the magic is spent. On a weak hit, as above, but the blood must be your own; Endure Harm (2 stress)."}
            {:id :second
             :enabled false
             :result "As above, and you may also add +1 when you Strike, Clash, or Battle."}
            {:id :third
             :enabled false
             :result "When you perform this ritual, add +1 and take +1 momentum on a hit."}]}
   {:name "Ward"
    :asset-type "Ritual"
    :perks [{:id :first
             :enabled true
             :result {:description "When you walk a wide circle, sprinkling the ground with salt, roll +wits. On a strong hit, choose two. On a weak hit, choose one."
                      :options [["Alert" "When a foe first crosses the boundary, take +1 momentum."]
                                ["Inflict" "When you first inflict harm against a foe within the boundary, inflict +1 harm."]
                                ["Trap" "Your ward is 'likely' (Ask the Oracle) to trap a foe within the boundary."]]
                      :random-event nil}}
            {:id :second
             :enabled false
             :result "As above, and improve the effect of your ward (+2 momentum, +2 harm, and 'almost certain')."}
            {:id :third
             :enabled false
             :result "When you perform this ritual, add +1 and take +1 momentum on a hit."}]}
   ;; now adding expansion assets
   {:name "Kindred"
    :asset-type "Companion"
    :description "Your friend stands by you."
    :perks [{:id "Skilled"
             :enabled false
             :result "When you make a move outside of combat (not a progress move) aided by your companion's expertise, add +1."}
            {:id "Shield-Kin"
             :enabled false
             :result "When you Clash or Battle alongside your companion, or when you Face Danger against an attack by standing together, add +1."}
            {:id "Bonded"
             :enabled false
             :result "Once you mark a bond with your companion, add +1 when you Face Desolation in their presence."}]
    :res-counter {:current 4
                  :max 4}
    :custom-note ["Name/Expertise:" "Some dood/stuff"]}
   {:name "Outcast"
    :asset-type "Path"
    :perks [{:id :first
             :enabled true
             :result "When your supply is reduced to 0, suffer any remaining -supply as -momentum. THen, roll +wits. On a strong hit, you manage to scrape by and take +1 supply. On a weak hit, you may suffer -2 momentum in exchange for +1 supply. On a miss, you are Out of Supply."}
            {:id :second
             :enabled false
             :result "When you Sojourn, you may reroll any dice. If you do (decide before your first roll), your needs are few, but your isolation sets you apart from others. A strong hit counts as a weak hit."}
            {:id :third
             :enabled false
             :result "When you Reach Your Destination and roll a strong hit, you recall or recognize something helpful about this place. Envision what it is, and take +2 momentum."}]}
   {:name "Pretender"
    :asset-type "Path"
    :perks [{:id :first
             :enabled true
             :result "When you establish a false identity, roll +shadow. On a strong hit, you may add +2 when you make moves using this identity to deceive or influence others. If you roll a 1 on your action die when using your false identity, someone doubts you. Make appropriate moves to reassure them or prevent them from revealing the truth. On a weak hit, as above, but add +1 instead of +2."}
            {:id :second
             :enabled false
             :result "As above, and you may roll +shadow (instead of +heart) when you Sojourn under your false identity. If you do, take +1 momentum on a hit."}
            {:id :third
             :enabled false
             :result "When you Secure an Advantage by revealing your true identity in a dramatic moment, reroll any dice."}]}
   {:name "Revenant"
    :asset-type "Path"
    :description "Once you Face Death and return to the world of the living."
    :perks [{:id :first
             :enabled true
             :result "When you are at 0 health, and Endure Harm or Face Death, add +1. If you then burn momentum to improve your result, envision what bond or vow binds you to this world, and take +2 momentum after you reset."}
            {:id :second
             :enabled false
             :result "When you make a move to investigate, oppose, or interact with a horror, spirit, or other undead being, add +1."}
            {:id :third
             :enabled false
             :result "When you bring death to your foe to End the Fight, you may burn momentum to cancel one (not both) of the challenge dice if your momentum is greater than the value of that die. If you do, Endure Stress (2 stress)."}]}
   {:name "Rider"
    :asset-type "Path"
    :description "If you are with your horse companion..."
    :perks [{:id :first
             :enabled true
             :result "When you Heal your horse, or when you Face Danger to calm or encourage it, add +1 and take +1 momentum on a hit."}
            {:id :second
             :enabled false
             :result "When you Undertake a Journey, and your action score is equal to one or both challenge dice, you may push your horse harder and add +1. If you do, make the Companion Endure Harm move (1 harm)."}
            {:id :third
             :enabled false
             :result "When you Reach Your Destination and roll a strong hit, you recall or recognize something helpful about this place. Envision what it is, and take +2 momentum."}]}
   {:name "Waterborn"
    :asset-type "Path"
    :perks [{:id :first
             :enabled true
             :result "When you Face Danger, Gather Information, or Secure an Advantage related to your knowledge of watercraft, water travel, or aquatic environments or creatures, add +1 and take +1 momentum on a hit."}
            {:id :second
             :enabled false
             :result {:description "When you Undertake a Journey by boat or ship, add +1. On a strong hit, also choose one."
                      :options [["The wind is at your back" "Mark one extra unit of progress."]
                                ["Find safe anchor" "Make Camp now and reroll any dice."]
                                ["Reap bounty" "Resupply now and reroll any dice."]]
                      :random-event nil}}
            {:id :third
             :enabled false
             :result "When you Enter the Fray aboard a boat or ship, reroll any dice."}]}
   {:name "Thunderbringer"
    :asset-type "Combat Talent"
    :description "If you wield a mighty hammer..."
    :perks [{:id :first
             :enabled true
             :result "When you Face Danger, Secure an Advantage, or Compel by hitting or breaking an inanimate object, add +1 and take +1 momentum on a hit."}
            {:id :second
             :enabled false
             :result "When you Strike a foe and score a strong hit, take +1 momentum as you also knock them back, stun them, or put them off-balance."}
            {:id :third
             :enabled false
             :result "When you Turn the Tide, you may Strike with all the fury and power you can muster. If you do (decide before rolling), you may reroll any dice and inflict +1 harm on a strong hit, but count a weak hit as a miss."}]}
   {:name "Awakening"
    :asset-type "Ritual"
    :perks [{:id :first
             :enabled true
             :result "When you create a simulacrum, envision your process and materials. Then, roll +heart. On a strong hit, your creation is given unnatural life. If you make a move using the simulacrum to assault or overcome an obstacle through strength or intimidation, add +2. It has 3 health and suffers harm as appropriate, but is not a companion and may not be healed. At 0 health, it is dead. On a weak hit, as above, but if you roll a 1 on your action die when aided by your simulacrum, it will betray you or turn on you (as at least a formidable foe)."}
            {:id :second
             :enabled false
             :result "Your simulacrum has 6 health."}
            {:id :third
             :enabled false
             :result "When you perform this ritual, add +1 and take +1 momentum on a hit."}]}
   {:name "Talisman"
    :asset-type "Ritual"
    :perks [{:id :first
             :enabled true
             :result "When you fashion a charm, envision it and name the specific person or creature it protects against. Then roll +wits. On a strong hit, when the wearer opposes the target through a move, add +2. If a 1 is rolled on the action die while making a move using the charm, the magic is spent. Ona a weak hit, as above, but the wearer adds +1 instead of +2."}
            {:id :second
             :enabled false
             :result "As above, and you may instead fashion a charm which aids the wearer against all supernatural threats, such as mystic rituals or horrors."}
            {:id :third
             :enabled false
             :result "When you perform this ritual, add +1 and take +1 momentum on a hit."}]}
   ])
