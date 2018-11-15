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
                      :options ["Add +2."
                                "On a hit, take or give an additional +1 health."]
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
   ])
