(ns ironsworn-companion.moves)

(def all-moves
  [
   {:name "Face Danger"
    :move-type :normal
    :description "When you attempt something risky or react to an imminent threat, envision your action and roll on any stat."
    :results {"Strong Hit" {:description "You are successful. Take +1 momentum."
                            :options nil
                            :random-event nil}
              "Weak Hit" {:description "You succed but face a troublesome cost. Choose one."
                          :options ["You are delayed, lose advantage or face a new danger: Suffer -1 momentum."
                                    "You are tired or hurt. Endure Harm (1 Harm)."
                                    "You are dispirited or afraid. Endure Stress (1 Stress)."
                                    "You sacrifice resources. Suffer -1 supply."]
                          :random-event nil}
              "Miss" {:description "You fail or your progress is undermined by a dramatic and costly turn of events. Pay the Price."
                      :options nil
                      :random-event nil}}}
   {:name "Secure an Advantage"
    :move-type :normal
    :description "When you assess a situation, make preparations, or attempt to gain leverage, envision your action and roll any stat."
    :results {"Strong Hit" {:description "You gain advantage. Choose one."
                            :options ["Take control: Make another move now (not a progress move); when you do, add +1."
                                      "Prepare to act: Take +2 momentum."]
                            :random-event nil}
              "Weak Hit" {:description "Your advantage is short-lived. Take +1 momentum."
                          :options nil
                          :random-event nil}
              "Miss" {:description "Your fail or your assumptions betray you. Pay the Price"}}}
   {:name "Gather Information"
    :move-type :normal
    :description "When you search an area, ask questions, conduct an investigation, or follow a track, roll +wits. If you act within a community or ask questions of a person with whom you share a bond, add +1."
    :results {"Strong Hit" {:description "You discover something helpful and specific. The path you must follow or action you must take to make progress is made clear. Envision what you lean (Ask the Oracle if unsure), and take +2 momentum."
                            :options nil
                            :random-event nil}
              "Weak Hit" {:description "The information complicates your quest or introduces a new danger. Envision what you discover (Ask the Oracle if unsure), and take +1 momentum."
                          :options nil
                          :random-event nil}
              "Miss" {:description "Your investigation unearths a dire threat or reveals an unwelcome truth that undermines your quest. Pay the Price."
                      :options nil
                      :random-event nil}}}
   {:name "Heal"
    :move-type :normal
    :description "When you treat an injure or ailment, roll +wits. If you are mending your own wounds, roll +wits or +iron, whichever is lower."
    :results {"Strong Hit" {:description "Your care is helpful. If you (or the ally under your care) have the wounded condition, you may clear it. Then, take or give up to +2 health."
                            :options nil
                            :random-event nil}
              "Weak Hit" {:description "Your care is helpful. If you (or the ally under your care) have the wounded condition, you may clear it. Then, take or give up to +2 health. Choose one."
                          :options ["Suffer -1 supply."
                                    "Suffer -1 momentum."]
                          :random-event nil}
              "Miss" {:description "Your aid is ineffective. Pay the Price."
                      :options nil
                      :random-event nil}}}
   {:name "Resupply"
    :move-type :normal
    :description "When you hunt, forage, or scavenge, roll +wits."
    :results {"Strong Hit" {:description "You bolster your resources. Take +2 supply."
                            :options nil
                            :random-event nil}
              "Weak Hit" {:description "Take up to +2 supply, but suffer -1 momentum for each."
                          :options nil
                          :random-event nil}
              "Miss" {:description "You find nothing helpful. Pay the Price."
                      :options nil
                      :random-event nil}}}
   {:name "Make Camp"
    :move-type :normal
    :description "When you rest and recover for several hours in the wild, roll +supply."
    :results {"Strong Hit" {:description "Your and your allies may choose two."
                            :options ["Recuperate: Take +1 health for you and any companions."
                                      "Partake: Suffer -1 supply and take +1 health for you and any companions."
                                      "Relax: Take +1 spirit."
                                      "Focus: Take +1 momentum."
                                      "Prepare: When you break camp, add +1 if you Undertake a Journey."]
                            :random-event nil}
              "Weak Hit" {:description "Your and your allies may choose one."
                          :options ["Recuperate: Take +1 health for you and any companions."
                                      "Partake: Suffer -1 supply and take +1 health for you and any companions."
                                      "Relax: Take +1 spirit."
                                      "Focus: Take +1 momentum."
                                    "Prepare: When you break camp, add +1 if you Undertake a Journey."]
                          :random-event nil}
              "Miss" {:description "You take no comfort. Pay the Price."
                      :options nil
                      :random-event nil}}}
   {:name "Undertake a Journey"
    :move-type :normal
    :description "When you travel across hazardous or unfamiliar lands, first set the rank of your journey and make a progress track for it. Then, for each segment of your journey, roll +wits. If you are setting off from a community with which you share a bond, add +1 to your initial roll."
    :results {"Strong Hit" {:description "You reach a waypoint. If the waypoint is unknown to you, envision it (Ask the Oracle if unsure). Then, choose one."
                            :options ["You make good use of your resources: Mark progress."
                                      "You move at speed: Mark progress and take +1 momentum, but suffer -1 supply."]
                            :random-event nil}
              "Weak Hit" {:description "You reach a waypoint and mark progress, but suffer -1 supply."
                          :options nil
                          :random-event nil}
              "Miss" {:description "You are waylaid by a perilous event. Pay the Price."
                      :options nil
                      :random-event nil}}}
   {:name "Reach your Destination"
    :move-type :progress-track
    :description "When your journey comes to an end, roll the challenge dice and compare to your progress. Momentum is ignored on this roll."
    :results {"Strong Hit" {:description "The situation at your destination favors you. Choose one."
                            :options ["Make another move now (not a progress move), and add +1."
                                      "Take +1 momentum."]
                            :random-event nil}
              "Weak Hit" {:description "Your arrive, but face an unforseen hazard or complication. Envision what you find (Ask the oracle if unsure)."
                          :options nil
                          :random-event nil}
              "Miss" {:description "You have gone hopelessly astray, your objective is lost to you, or you were misled about your destination. If your journey continues, clear all but one filled progress., and raise the journey's rank by one (if not already epic)."
                      :options nil
                      :random-event nil}}}
   {:name "Compel"
    :move-type :normal
    :description "When you attempt to persuade someone to do something, envision your approach and roll +heart +iron or +shadow. If you roll +heart and share a bond with them, add +1."
    :results {"Strong Hit" {:description "They'll do what you want or share what you know. Take +1 momentum. If you use this exchange to Gather Information, make that move now and add +1."
                            :options nil
                            :random-event nil}
              "Weak Hit" {:description "They'll do what you want or share what you know. Take +1 momentum. If you use this exchange to Gather Information, make that move now and add +1. They ask something of you in return. Envision what they want (Ask the Oracle, if unsure)."
                          :options nil
                          :random-event nil}
              "Miss" {:description "They refuse or make a demand which costs you greatly. Pay the Price."
                      :options nil
                      :random-event nil}}}
   {:name "Sojourn"
    :move-type :normal
    :description "When you spend time in a community seeking assisstance, roll +heart. If you share a bond, add +1."
    :results {"Strong Hit" {:description "You and your allies may each choose two from within the categories below. If you share a bond, choose one more."
                            :options ["Mend: Clear a wounded debility and take +1 health."
                                      "Hearten: Clear a shaken debility and take +1 spirit."
                                      "Equip: Clear an unprepared debility and take +1 supply."
                                      "Recuperate: Take +2 health for yourself and any companions."
                                      "Consort: Take +2 spirit."
                                      "Provision: Take +2 supply."
                                      "Plan: Take +2 momentum."
                                      "Provide Aid: Take a quest: Envision what this community needs, or what trouble it is facing (Ask the Oracle, if unsure). If you chose to help, Swear an Iron Vow and add +1."]
                            :random-event nil}
              "Weak Hit" {:description "You and your allies may each choose one from within the categories below. If you share a bond, choose one more."
                          :options ["Mend: Clear a wounded debility and take +1 health."
                                      "Hearten: Clear a shaken debility and take +1 spirit."
                                      "Equip: Clear an unprepared debility and take +1 supply."
                                      "Recuperate: Take +2 health for yourself and any companions."
                                      "Consort: Take +2 spirit."
                                      "Provision: Take +2 supply."
                                      "Plan: Take +2 momentum."
                                    "Provide Aid: Take a quest: Envision what this community needs, or what trouble it is facing (Ask the Oracle, if unsure). If you chose to help, Swear an Iron Vow and add +1."]
                          :random-event nil}
              "Miss" {:description "You find no help here. Pay the Price."
                      :options nil
                      :random-event nil}}}
   {:name "Draw the Circle"
    :move-type :normal
    :description "When you challenge someone to a formal duel, or accept a challenge, roll +heart. If you share a bond with this community, add +1. After the duel, if you are the victor, you may make a lawful demand and your opponent must comply or forfeit their honor and standing. If you refuse the challenge, surrender, or are defeated, they make a demand of you."
    :results {"Strong Hit" {:description "Take +1 momentum. You may also choose up to three boasts and take +1 momentum for each."
                            :options ["Grant first strike: Your foe has initiative."
                                      "Bare yourself: Take no benefit of armor or shield; your foe's harm is +1."
                                      "Hold no iron: Take no benefit of weapons; your harm is 1."
                                      "Bloody yourself: Endure harm (1 harm)."
                                      "To the death: One way or another, this fight must end with death."]
                            :random-event nil}
              "Weak Hit" {:description "You may choose one boast in exchange for +1 momentum."
                          :options ["Grant first strike: Your foe has initiative."
                                      "Bare yourself: Take no benefit of armor or shield; your foe's harm is +1."
                                      "Hold no iron: Take no benefit of weapons; your harm is 1."
                                      "Bloody yourself: Endure harm (1 harm)."
                                    "To the death: One way or another, this fight must end with death."]
                          :random-event nil}
              "Miss" {:description "You begin the duel at a disadvantage. Your foe has initiative. Pay the Price."
                      :options nil
                      :random-event nil}}}
   {:name "Forge a Bond"
    :move-type :normal
    :description "When you spend significant time with a person or community, stand together to face harships, or make sacrifices to their cause, you can attempt to create a bond. When you do, roll +heart. If you make this move after you successfully Fullfill your Vow to their benefit, you may reroll any dice."
    :results {"Strong Hit" {:description "Make note of the bond, mark a tick on your bond progress track, and choose one."
                            :options ["Take +1 spirit."
                                      "Take +2 momentum."]
                            :random-event nil}
              "Weak Hit" {:description "They ask something more of you first. Envision what it is (Ask the Oracle, if unsure), do it (or Swear an Iron Vow) and mark the bond. Make note of the bond, mark a tick on your bond progress track, and choose one. If you decline or fail, Pay the Price."
                          :options nil
                          :random-event nil}
              "Miss" {:description "You are refused. Pay the Price."
                      :options nil
                      :random-event nil}}}
   {:name "Test your Bond"
    :move-type :normal
    :description "When your bond is tested through conflict, betrayal, or circumstance, roll +heart. If you have no interest in maintaining this relationship, just clear the bond and Pay the Price."
    :results {"Strong Hit" {:description "This test has strengthened your bond. Choose one."
                            :options ["Take +1 spirit."
                                      "Take +2 momentum"]
                            :random-event nil}
              "Weak Hit" {:description "Your bond is fragile and you must prove your loyalty. Envision what they ask of you (Ask the Oracle, if unsure), and do it (or Swear an Iron Vow). If you decline or fail, clear the bond and Pay the Price."
                          :options nil
                          :random-event nil}
              "Miss" {:description "Clear the bond and Pay the Price."
                      :options nil
                      :random-event nil}}}
   ])
