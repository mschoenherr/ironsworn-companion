(ns ironsworn-companion.moves)

;; This work is based on Ironsworn (found at www.ironswornrpg.com), created by Shawn Tomkin,
;; and licensed for our use under the Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International license
;; (creativecommons.org/licenses/by-nc-sa/4.0/).

(def all-moves
  [
   {:name "Asset Move"
    :move-type :normal
    :description "Use this move for any rolls triggered by assets."
    :results {"Strong Hit" "Strong Hit"
              "Weak Hit" "Weak Hit"
              "Miss" "Miss"}}
   {:name "Face Danger"
    :move-type :normal
    :description "When you attempt something risky or react to an imminent threat, envision your action and roll on any stat."
    :results {"Strong Hit" {:description "You are successful. Take +1 momentum."
                            :options nil
                            :random-event nil}
              "Weak Hit" {:description "You succed but face a troublesome cost. Choose one."
                          :options [["You are delayed..." "... , lose advantage or face a new danger: Suffer -1 momentum."]
                                    ["You are tired or hurt." "Endure Harm (1 Harm)."]
                                    ["You are dispirited or afraid" "Endure Stress (1 Stress)."]
                                    ["You sacrifice resources" "Suffer -1 supply."]]
                          :random-event nil}
              "Miss" {:description "You fail or your progress is undermined by a dramatic and costly turn of events. Pay the Price."
                      :options nil
                      :random-event nil}}}
   {:name "Secure an Advantage"
    :move-type :normal
    :description "When you assess a situation, make preparations, or attempt to gain leverage, envision your action and roll any stat."
    :results {"Strong Hit" {:description "You gain advantage. Choose one."
                            :options [["Take control" "Make another move now (not a progress move); when you do, add +1."]
                                      ["Prepare to act" "Take +2 momentum."]]
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
                          :options [["Expend supplies" "Suffer -1 supply."]
                                    ["Use time" "Suffer -1 momentum."]]
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
                            :options [["Recuperate" "Take +1 health for you and any companions."]
                                      ["Partake" "Suffer -1 supply and take +1 health for you and any companions."]
                                      ["Relax" "Take +1 spirit."]
                                      ["Focus" "Take +1 momentum."]
                                      ["Prepare" "When you break camp, add +1 if you Undertake a Journey."]]
                            :random-event nil}
              "Weak Hit" {:description "Your and your allies may choose one."
                          :options [["Recuperate" "Take +1 health for you and any companions."]
                                    ["Partake" "Suffer -1 supply and take +1 health for you and any companions."]
                                    ["Relax" "Take +1 spirit."]
                                    ["Focus" "Take +1 momentum."]
                                    ["Prepare" "When you break camp, add +1 if you Undertake a Journey."]]
                          :random-event nil}
              "Miss" {:description "You take no comfort. Pay the Price."
                      :options nil
                      :random-event nil}}}
   {:name "Undertake a Journey"
    :move-type :normal
    :description "When you travel across hazardous or unfamiliar lands, first set the rank of your journey and make a progress track for it. Then, for each segment of your journey, roll +wits. If you are setting off from a community with which you share a bond, add +1 to your initial roll."
    :results {"Strong Hit" {:description "You reach a waypoint. If the waypoint is unknown to you, envision it (Ask the Oracle if unsure). Then, choose one."
                            :options [["You make good use of your resources" "Mark progress."]
                                      ["You move at speed" "Mark progress and take +1 momentum, but suffer -1 supply."]]
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
                            :options [["Act" "Make another move now (not a progress move), and add +1."]
                                      ["Prepare" "Take +1 momentum."]]
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
                            :options [["Mend" "Clear a wounded debility and take +1 health."]
                                      ["Hearten" "Clear a shaken debility and take +1 spirit."]
                                      ["Equip" "Clear an unprepared debility and take +1 supply."]
                                      ["Recuperate" "Take +2 health for yourself and any companions."]
                                      ["Consort" "Take +2 spirit."]
                                      ["Provision" "Take +2 supply."]
                                      ["Plan" "Take +2 momentum."]
                                      ["Provide Aid" "Take a quest: Envision what this community needs, or what trouble it is facing (Ask the Oracle, if unsure). If you chose to help, Swear an Iron Vow and add +1."]]
                            :random-event nil}
              "Weak Hit" {:description "You and your allies may each choose one from within the categories below. If you share a bond, choose one more."
                          :options [["Mend" "Clear a wounded debility and take +1 health."]
                                    ["Hearten" "Clear a shaken debility and take +1 spirit."]
                                    ["Equip" "Clear an unprepared debility and take +1 supply."]
                                    ["Recuperate" "Take +2 health for yourself and any companions."]
                                    ["Consort" "Take +2 spirit."]
                                    ["Provision" "Take +2 supply."]
                                    ["Plan" "Take +2 momentum."]
                                    ["Provide Aid" "Take a quest: Envision what this community needs, or what trouble it is facing (Ask the Oracle, if unsure). If you chose to help, Swear an Iron Vow and add +1."]]
                          :random-event nil}
              "Miss" {:description "You find no help here. Pay the Price."
                      :options nil
                      :random-event nil}}}
   {:name "Draw the Circle"
    :move-type :normal
    :description "When you challenge someone to a formal duel, or accept a challenge, roll +heart. If you share a bond with this community, add +1. After the duel, if you are the victor, you may make a lawful demand and your opponent must comply or forfeit their honor and standing. If you refuse the challenge, surrender, or are defeated, they make a demand of you."
    :results {"Strong Hit" {:description "Take +1 momentum. You may also choose up to three boasts and take +1 momentum for each."
                            :options [["Grant first strike" "Your foe has initiative."]
                                      ["Bare yourself" "Take no benefit of armor or shield; your foe's harm is +1."]
                                      ["Hold no iron" "Take no benefit of weapons; your harm is 1."]
                                      ["Bloody yourself" "Endure harm (1 harm)."]
                                      ["To the death" "One way or another, this fight must end with death."]]
                            :random-event nil}
              "Weak Hit" {:description "You may choose one boast in exchange for +1 momentum."
                          :options [["Grant first strike" "Your foe has initiative."]
                                    ["Bare yourself" "Take no benefit of armor or shield; your foe's harm is +1."]
                                    ["Hold no iron" "Take no benefit of weapons; your harm is 1."]
                                    ["Bloody yourself" "Endure harm (1 harm)."]
                                    ["To the death" "One way or another, this fight must end with death."]]
                          :random-event nil}
              "Miss" {:description "You begin the duel at a disadvantage. Your foe has initiative. Pay the Price."
                      :options nil
                      :random-event nil}}}
   {:name "Forge a Bond"
    :move-type :normal
    :description "When you spend significant time with a person or community, stand together to face harships, or make sacrifices to their cause, you can attempt to create a bond. When you do, roll +heart. If you make this move after you successfully Fullfill your Vow to their benefit, you may reroll any dice."
    :results {"Strong Hit" {:description "Make note of the bond, mark a tick on your bond progress track, and choose one."
                            :options [["Cheer up" "Take +1 spirit."]
                                      ["Envigorate" "Take +2 momentum."]]
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
                            :options [["Cheer up" "Take +1 spirit."]
                                      ["Envigorate" "Take +2 momentum."]]
                            :random-event nil}
              "Weak Hit" {:description "Your bond is fragile and you must prove your loyalty. Envision what they ask of you (Ask the Oracle, if unsure), and do it (or Swear an Iron Vow). If you decline or fail, clear the bond and Pay the Price."
                          :options nil
                          :random-event nil}
              "Miss" {:description "Clear the bond and Pay the Price."
                      :options nil
                      :random-event nil}}}
   {:name "Aid your ally"
    :move-type :no-roll
    :description "When you Secure an Advantage in direct support of an ally, and score a hit, they (instead of you) can take the benefits of the move. If you are in combat and score a strong hit, you and your ally have initiative."
    :results nil}
   {:name "Write your Epilogue"
    :move-type :bond-move
    :description "When you retire from your life as an Ironsworn, envision two things: What you hope for and what you feat. Then, roll the challenge dice and compare to your bonds. Momentum is ignored on the roll."
    :results {"Strong Hit" {:description "Things come to pass as you hoped."
                            :options nil
                            :random-event nil}
              "Weak Hit" {:description "Your life takes an unexpected turn, but not necessarily for the worse. You find yourself spending your days with someone or in a place you did not foresee. Envision it (Ask the Oracle if unsure)."
                          :options nil
                          :random-event nil}
              "Miss" {:description "Your fears are realized."
                      :options nil
                      :random-event nil}}}
   {:name "Enter the Fray"
    :move-type :normal
    :description "When you enter into combat, first set the rank of each of your foes. Then roll +heart, +shadow or +wits."
    :results {"Strong Hit" {:description "Take +2 momentum. You have initiative."
                            :options nil
                            :random-event nil}
              "Weak Hit" {:description "You choose one."
                          :options [["Bolster your position" "Take +2 momentum."]
                                    ["Prepare to act" "Take initiative."]]
                          :random-event nil}
              "Miss" {:description "Combat begins with you at a disadvantage. Pay the Price. Your foe has initiative."
                      :options nil
                      :random-event nil}}}
   {:name "Strike"
    :move-type :normal
    :description "When you have initiative and attack in close quarters, roll +iron. When you have initiative and attack at range, roll +edge."
    :results {"Strong Hit" {:description "Inflict +1 harm. You retain initiative."
                            :options nil
                            :random-event nil}
              "Weak Hit" {:description "Inflict your harm and lose initiative."
                          :options nil
                          :random-event nil}
              "Miss" {:description "Your attack fails and you must Pay the Price. Your foe has initiative."
                      :options nil
                      :random-event nil}}}
   {:name "Clash"
    :move-type :normal
    :description "When your foe has initiative and you fight with them in close quarters, roll +iron. When you exchange a volley at range, or shoot at an advancing foe, roll +edge."
    :results {"Strong Hit" {:description "Inflict your harm and choose one. You have the initiative."
                            :options [["Bolster your position" "Take +1 momentum"]
                                      ["Find an opening" "Inflict +1 harm."]]
                            :random-event nil}
              "Weak Hit" {:description "Inflict your harm, but then Pay the Price. Your foe has initiative."
                          :options nil
                          :random-event nil}}}
   {:name "Turn the Tide"
    :move-type :no-roll
    :description "Once per fight, when you risk it all, you may steal initiative from your foe to make a move (not a progress move). When you do, add +1 and take +1 momentum on hit. If you fail to score a hit on that move, you must suffer a dire outcome. Pay the Price."
    :results nil}
   {:name "End the Fight"
    :move-type :progress-track
    :description "When you make a move to take decisive action, and score a strong hit, you may resolve the outcome for this fight. If you do, roll the challenge dice and compare to your progress. Momentum is ignored on this roll."
    :results {"Strong Hit" {:description "This foe is no longer in this fight. They are killed, out of action, flee, or surrender as appropriate to the situation and your intent (Ask the Oracte if unsure)."
                            :options nil
                            :random-event nil}
              "Weak Hit" {:description "This foe is no longer in this fight. They are killed, out of action, flee, or surrender as appropriate to the situation and your intent (Ask the Oracte if unsure). Choose one."
                          :options [["It's worse than you thought" "Endure Harm:"]
                                    ["You are overcome" "Endure Stress."]
                                    ["Your victory is short-lived" "A new danger or foe appears, or an existing danger worsens."]
                                    ["You suffer colleteral damage" "Something of value is lost or broken, or someone must pay an important cost."]
                                    ["You'll pay for it" "An objective falls out of reach."]
                                    ["Others won't forget" "You are marked for vengeance."]]
                          :random-event nil}
              "Miss" {:description "You have lost this fight. Pay the Price"
                      :options nil
                      :random-event nil}}}
   {:name "Battle"
    :move-type :normal
    :description "When you fight a battle, and it happens in a blur, envision your objective and roll any stat."
    :results {"Strong Hit" {:description "You achieve your objective unconditionally. Take +2 momentum."
                            :options nil
                            :random-event nil}
              "Weak Hit" {:description "You achieve your objective, but not without a cost. Pay the Price."
                          :options nil
                          :random-event nil}
              "Miss" {:description "You are defeated and the objective is lost to you. Pay the Price."
                      :options nil
                      :random-event nil}}}
   {:name "Endure Harm"
    :move-type :normal
    :description "When you face physical damage, suffer -health equal to your foe's rank or as appropriate to the situation. If your health is 0, suffer -momentum equal to any remaining -health. Then roll +health or +iron, whichever is higher."
    :results { "Strong Hit" {:description "Choose one."
                             :options [["Shake it off" "If your health is greater than 0, suffer -1 momentum in exchange for +1 health."]
                                       ["Embrace the pain" "Take +1 momentum."]]
                             :random-event nil}
              "Weak Hit" {:description "You press on."
                          :options nil
                          :random-event nil}
              "Miss" {:description "Also suffer -1 momentum. If you are at 0 health, you must mark wounded or maimed (if currently unmarked) or roll on the following table."
                      :options nil
                      :random-event [[10 {:description "The harm is mortal. Face Death."
                                          :options nil
                                          :random-event nil}]
                                     [20 {:description "You are dying. You need to Heal within an hour or two, or Face Death."
                                          :options nil
                                          :random-event nil}]
                                     [35 {:description "You are unconscious and out of action. If left alone, you come back to your senses in an hour or two. If you are are vulnerable to a foe not inclined to show mercy, Face Death."
                                          :options nil
                                          :random-event nil}]
                                     [50 {:description "You are reeling and fighting to stay conscious. If you engage in any vigorous activity (such as running or fighting) before taking a breather for a few minutes, roll on this table again (before resolving the other move)."
                                          :options nil
                                          :random-event nil}]
                                     [100 {:description "You are battered but still standing."
                                           :options nil
                                           :random-event nil}]]}}} ;; this one needs a random-table
   {:name "Face Death"
    :move-type :normal
    :description "When your are brought to the brink of death, and glimpse the world beyond, roll +heart."
    :results {"Strong Hit" {:description "Death rejects you. You are cast back into the mortal world."
                            :options nil
                            :random-event nil}
              "Weak Hit" {:description "Choose one."
                          :options [["You die ..." "... but not before making a noble sacrifice. Envision your final moment."]
                                    ["Death desires something of you ..." "... in exchange for lift. Envision what it wants (Ask the Oracle if unsure), and Swear an Iron Vow (formidable or extreme) to complete that quest. If you fail to score a hit when you swear an Iron Vow, or refuse the quest, you are dead. Otherwise, you return to the mortal world and are now cursed. You may only clear the cursed debility by completing that quest."]]
                          :random-event nil}
              "Miss" {:description "You are dead."
                      :options nil
                      :random-event nil}}}
   {:name "Companion Endure Harm"
    :move-type :normal
    :description "When your companion faces physical damage, they suffer -health equal to the amount of harm inflicted. If your companions health is 0, exchange any leftover -health for -momentum."
    :results {"Strong Hit" {:description "Your companion rallies. Give them +1 health."
                            :options nil
                            :random-event nil}
              "Weak Hit" {:description "Your companion is battered. If their health is 0, they cannot assisst you until they gain at least +1 health."
                          :options nil
                          :random-event nil}
              "Miss" {:description  "Also suffer -1 momentum. If your companions health is 0, they are gravely wounded and out of action. Without aid, they die in an hour or two. If you roll a miss with a 1 on your action die, and your companions health is 0, they are now dead. Take +1 experience for each marked ability on your companion asset, and remove it."
                      :options nil
                      :random-event nil}}}
   {:name "Endure Stress"
    :move-type :normal
    :description "When you face mental shock or despair, suffer -spirit equal to your foe's rank or as appropriate to the situation. If your spirit is 0, suffer -momentum equal to the remaining -spirit. Then roll +heart or +spirit, whichever is higher."
    :results {"Strong Hit" {:description "Choose one."
                            :options [["Shake it off" "If your spirit is greater than 0, suffer -1 momentum in exchange for +1 spirit."]
                                      ["Embrace the darkness" "Take +1 momentum."]]
                            :random-event nil}
              "Weak Hit" {:description "You press on."
                          :options nil
                          :random-event nil}
              "Miss" {:description "Also suffer -1 momentum. If you are at spirit 0, you must mark shaken or corrupted (if currently unmarked) or roll on the following table."
                      :options nil
                      :random-event [[10 "You are overwhelmed. Face Desolation."]
                                     [25 "You give up. Forsake Your Vow (if possible, one relevant to your current crisis."]
                                     [50 "You give in to fear or compulsion, and act against your better instincts."]
                                     [100 "You persevere."]]}}}
   {:name "Face Desolation"
    :move-type :normal
    :description "When you are brought to the brink of desolation, roll +heart."
    :results {"Strong Hit" {:description "You resist and press on."
                            :options nil
                            :random-event nil}
              "Weak Hit" {:description "Choose one."
                          :options [["Your spirit or your sanity breaks ..." "... but not before you make a noble sacrifice. Envision your final moments."]
                                    ["You see a vision ..." "of dreaded moment coming to pass. Envision that dark future (Ask the Oracle if unsure), and Swear an Iron Vow (formidable or extreme) to prevent it. If you fail to score a hit when you swear an Iron Vow, or refuse the quest, you are lost. Otherwise, you return to your senses and are now tormented. You may only clear the tormented debility by completing the quest."]]
                          :random-event nil}
              "Miss" {:description "You succumb to horror or despair and are lost."
                      :options nil
                      :random-event nil}}}
   {:name "Out of supply"
    :move-type :no-roll
    :description "When your supply is exhausted (reduced to 0), mark unprepared. If you suffer additional -supply while unprepared, you must exchange each -supply for any combination of -health, -spirit or -momentum, as appropriate to the circumstances."
    :results nil}
   {:name "Face a Setback"
    :move-type :no-roll
    :description "When your momentum is at its minimum (-6), and you suffer additional -momentum."
    :results {"Other" {:description "Choose one."
                       :options [["To the last" "Exchange each additional -momentum for any combination of -health, -spirit or -supply, as appropriate to the circumstances."]
                                 ["Sacrific progress" "Envision an event or discovery (Ask the Orace if unsure), which undermines your progress in a current quest, journey or fight. Then, for each additional -momentum, clear 1 unit of progress that track per its rank."]]
                       :random-event nil}}}
   {:name "Swear an Iron Vow"
    :move-type :normal
    :description "When you swear upon iron to complete a quest, write your vow and give the quest a rank. Then, roll +heart. If you make this vow to a person or commmunity with whom you share a bond, add +1."
    :results {"Strong Hit" {:description "You are emboldened and it is clear what you must do next (Ask the Oracle if unsure). Take +2 momentum."
                            :options nil
                            :random-event nil}
              "Weak Hit" {:description "You are determined but begin your quest with more questions than answers. Take +1 momentum, and envision what you do to find your path."
                          :options nil
                          :random-event nil}
              "Miss" {:description "You face a significant obstacle before you can begin your quest. Envision what stands in your way (Ask the Oracle if unsure), and choose one."
                      :options [["You press on" "Suffer -2 momentum, and do what you must to overcome this obstacle."]
                                ["You give up" "Forsake your Vow"]]
                      :random-event nil}}}
   {:name "Reach a Milestone"
    :move-type :no-roll
    :description "When you make significant progress in your quest by overcoming a critical obstacle, completing a perilous journey, solving a complex mystery, defeating a powerful threat, gaining vital support, or acquiring a crucial item, you may mark progress."
    :results nil}
   {:name "Fullfill your Vow"
    :move-type :vow-move
    :description "When you achieve what you believe to be the fullfillment of you vow, roll the challenge dice and compare to your progress. Momentum is ignored on this roll."
    :results {"Strong Hit" {:description "Your quest is complete mark experience according to its rank (troublesome = 1, epic = 5)."
                            :options nil
                            :random-event nil}
              "Weak Hit" {:description "There is more to be done, or you realize the truth of your quest. Envision what you discover (Ask the Oracle if unsure). Then, mark experience (troublesome = 0, epic = 4). You may Swear an Iron Vow to set things right. If you do, add +1."
                          :options nil
                          :random-event nil}
              "Miss" {:description "Your quest is undone. Envision what happens (Ask the Oracle if unsure), and choose one."
                      :options [["You recommit" "Clear all but one filled progress, and raise the quest's rank by one (if not already epic)."]
                                ["You give up" "Forsake your Vow."]]
                      :random-event nil}}}
   {:name "Forsake your Vow"
    :move-type :no-roll
    :description "When you renouce your quest, betray your promise, or the goal is lost to you, clear the vow and Endure Stress. You suffer -spirit equal to the rank of your quest (troublesome = 1, epic = 5)."
    :results nil}
   {:name "Advance"
    :move-type :no-roll
    :description "When you focus on your skills, receive training, find inspiration, earn a reward, or gain a companion, you may spend 3 experience to add a new asset, or 2 experience to upgrade an asset."
    :results nil}

   {:name "Pay the Price"
    :move-type :no-roll
    :description "When you suffer the outcome of a move."
    :results {"Other" {:description "Choose one."
                       :options [["Choose the obvious" "Make the most obvious negative outcome happen."]
                                 ["Ask yes/no" "Envision two negative outcomes. Rate one as likely, and Ask the Oracle using the yes/no table. On a yes, make that outcome happen. Otherwise make it the other."]
                                 ["Random" "Roll on the following table."]]
                       :random-event [[2 "Roll again and apply that result but make it worse. If you roll this result yet again, think of something dreadful that changes the course of your quest (Ask the Oracle if unsure) and make it happen."]
                                      [5 "A person or community you trusted loses faith in you, or acts against you."]
                                      [9 "A person or community you care about is exposed to danger."]
                                      [16 "You are separated from something or someone."]
                                      [23 "Your action has an unintended effect."]
                                      [32 "Something of value is lost destroyed."]
                                      [41 "The current situation worsens."]
                                      [50 "A new danger or foe is revealed."]
                                      [59 "It causes delay or puts you at a disadvantage."]
                                      [68 "It is harmful."]
                                      [77 "It is stressful."]
                                      [85 "A suprising devolpment complicates your quest."]
                                      [90 "It wastes resources."]
                                      [94 "It forces you to act against your best intentions."]
                                      [98 "A friend, companion, or ally is put in harm's way (or you are, if alone)."]
                                      [100 "Roll twice more on this table. Both results occur. If they are the same result, make it worse."]]}}} ;; random-table here
   {:name "Ask the Oracle"
    :move-type :no-roll
    :description "When you seek to resolve questions, discover details in the world, determine how other characters respond, or trigger encounters or events."
    :results {"Other" {:description "You may:"
                       :options [["Draw a conclusion" "Decide the answer based on the most interesting and obvious result."]
                                 ["Ask yes/no" {:description "Ask a yes/no question. Decide the odds of a 'yes', and roll."
                                                :options [["Almost Certain" {:description "Almost Certain."
                                                                             :options nil
                                                                             :random-event [[10 "No"]
                                                                                            [100 "Yes"]]}]
                                                          ["Likely" {:description "Likely."
                                                                     :options nil
                                                                     :random-event [[25 "No"]
                                                                                    [100 "Yes"]]}]
                                                          ["50/50" {:description "50/50."
                                                                    :options nil
                                                                    :random-event [[50 "No"]
                                                                                   [100 "Yes"]]}]
                                                          ["Unlikely" {:description "Unlikely."
                                                                       :options nil
                                                                       :random-event [[75 "No"]
                                                                                      [100 "Yes"]]}]
                                                          ["Small Chance" {:description "Small Chance."
                                                                           :options nil
                                                                           :random-event [[90 "No"]
                                                                                          [100 "Yes"]]}]]
                                                :random-event nil}]
                                 ["Pick two" {:description "Pick two: Envision two options, the first being 'likely' and roll to see which one happens."
                                              :options nil
                                              :random-event [[25 "First option."]
                                                             [100 "The other option."]]}]
                                 ["Spark an idea" "Brainstorm or use a random prompt."]
                                 ["Action" {:description "Divine the action"
                                            :options nil
                                            :random-event [[1 "Scheme"]
                                                           [2 "Clash"]
                                                           [3 "Weaken"]
                                                           [4 "Initiate"]
                                                           [5 "Create"]
                                                           [6 "Swear"]
                                                           [7 "Avenge"]
                                                           [8 "Guard"]
                                                           [9 "Defeat"]
                                                           [10 "Control"]
                                                           [11 "Break"]
                                                           [12 "Risk"]
                                                           [13 "Surrender"]
                                                           [14 "Inspect"]
                                                           [15 "Raid"]
                                                           [16 "Evade"]
                                                           [17 "Assault"]
                                                           [18 "Deflect"]
                                                           [19 "Threaten"]
                                                           [20 "Attack"]
                                                           [21 "Leave"]
                                                           [22 "Preserve"]
                                                           [23 "Manipulate"]
                                                           [24 "Remove"]
                                                           [25 "Eliminate"]
                                                           [26 "Withdraw"]
                                                           [27 "Abandon"]
                                                           [28 "Investigate"]
                                                           [29 "Hold"]
                                                           [30 "Focus"]
                                                           [31 "Uncover"]
                                                           [32 "Breach"]
                                                           [33 "Aid"]
                                                           [34 "Uphold"]
                                                           [35 "Falter"]
                                                           [36 "Suppress"]
                                                           [37 "Hunt"]
                                                           [38 "Share"]
                                                           [39 "Destroy"]
                                                           [40 "Avoid"]
                                                           [41 "Reject"]
                                                           [42 "Demand"]
                                                           [43 "Explore"]
                                                           [44 "Bolster"]
                                                           [45 "Seize"]
                                                           [46 "Mourn"]
                                                           [47 "Reveal"]
                                                           [48 "Gather"]
                                                           [49 "Defy"]
                                                           [50 "Transform"]
                                                           [51 "Persevere"]
                                                           [52 "Serve"]
                                                           [53 "Begin"]
                                                           [54 "Move"]
                                                           [55 "Coordinate"]
                                                           [56 "Resist"]
                                                           [57 "Await"]
                                                           [58 "Impress"]
                                                           [59 "Take"]
                                                           [60 "Oppose"]
                                                           [61 "Capture"]
                                                           [62 "Overwhelm"]
                                                           [63 "Challenge"]
                                                           [64 "Acquire"]
                                                           [65 "Protect"]
                                                           [66 "Finish"]
                                                           [67 "Strengthen"]
                                                           [68 "Restore"]
                                                           [69 "Advance"]
                                                           [70 "Command"]
                                                           [71 "Refuse"]
                                                           [72 "Find"]
                                                           [73 "Deliver"]
                                                           [74 "Hide"]
                                                           [75 "Fortify"]
                                                           [76 "Betray"]
                                                           [77 "Secure"]
                                                           [78 "Arrive"]
                                                           [79 "Affect"]
                                                           [80 "Change"]
                                                           [81 "Defend"]
                                                           [82 "Debate"]
                                                           [83 "Support"]
                                                           [84 "Follow"]
                                                           [85 "Construct"]
                                                           [86 "Locate"]
                                                           [87 "Endure"]
                                                           [88 "Release"]
                                                           [89 "Lose"]
                                                           [90 "Reduce"]
                                                           [91 "Escalate"]
                                                           [92 "Distract"]
                                                           [93 "Journey"]
                                                           [94 "Escort"]
                                                           [95 "Learn"]
                                                           [96 "Communicate"]
                                                           [97 "Depart"]
                                                           [98 "Search"]
                                                           [99 "Charge"]
                                                           [100 "Summon"]]}]
                                 ["Theme" {:description "Divine the theme"
                                           :options nil
                                           :random-event [[1 "Risk"]
                                                          [2 "Ability"]
                                                          [3 "Price"]
                                                          [4 "Ally"]
                                                          [5 "Battle"]
                                                          [6 "Safety"]
                                                          [7 "Survival"]
                                                          [8 "Weapon"]
                                                          [9 "Wound"]
                                                          [10 "Shelter"]
                                                          [11 "Leader"]
                                                          [12 "Fear"]
                                                          [13 "Time"]
                                                          [14 "Duty"]
                                                          [15 "Secret"]
                                                          [16 "Innocence"]
                                                          [17 "Renown"]
                                                          [18 "Direction"]
                                                          [19 "Death"]
                                                          [20 "Honor"]
                                                          [21 "Labor"]
                                                          [22 "Solution"]
                                                          [23 "Tool"]
                                                          [24 "Balance"]
                                                          [25 "Love"]
                                                          [26 "Barrier"]
                                                          [27 "Creation"]
                                                          [28 "Decay"]
                                                          [29 "Trade"]
                                                          [30 "Bond"]
                                                          [31 "Hope"]
                                                          [32 "Superstition"]
                                                          [33 "Peace"]
                                                          [34 "Deception"]
                                                          [35 "History"]
                                                          [36 "World"]
                                                          [37 "Vow"]
                                                          [38 "Protection"]
                                                          [39 "Nature"]
                                                          [40 "Opinion"]
                                                          [41 "Burden"]
                                                          [42 "Vengeance"]
                                                          [43 "Opportunity"]
                                                          [44 "Faction"]
                                                          [45 "Danger"]
                                                          [46 "Corruption"]
                                                          [47 "Freedom"]
                                                          [48 "Debt"]
                                                          [49 "Hate"]
                                                          [50 "Possession"]
                                                          [51 "Stranger"]
                                                          [52 "Passage"]
                                                          [53 "Land"]
                                                          [54 "Creature"]
                                                          [55 "Disease"]
                                                          [56 "Advantage"]
                                                          [57 "Blood"]
                                                          [58 "Language"]
                                                          [59 "Rumour"]
                                                          [60 "Weakness"]
                                                          [61 "Greed"]
                                                          [62 "Family"]
                                                          [63 "Resource"]
                                                          [64 "Structure"]
                                                          [65 "Dream"]
                                                          [66 "Community"]
                                                          [67 "War"]
                                                          [68 "Portent"]
                                                          [69 "Prize"]
                                                          [70 "Destiny"]
                                                          [71 "Momentum"]
                                                          [72 "Power"]
                                                          [73 "Memory"]
                                                          [74 "Ruin"]
                                                          [75 "Mysticism"]
                                                          [76 "Rival"]
                                                          [77 "Problem"]
                                                          [78 "Idea"]
                                                          [79 "Revenge"]
                                                          [80 "Health"]
                                                          [81 "Fellowship"]
                                                          [82 "Enemy"]
                                                          [83 "Religion"]
                                                          [84 "Spirit"]
                                                          [85 "Fame"]
                                                          [86 "Desolation"]
                                                          [87 "Strength"]
                                                          [88 "Knowledge"]
                                                          [89 "Truth"]
                                                          [90 "Quest"]
                                                          [91 "Pride"]
                                                          [92 "Loss"]
                                                          [93 "Law"]
                                                          [94 "Path"]
                                                          [95 "Warning"]
                                                          [96 "Relationship"]
                                                          [97 "Wealth"]
                                                          [98 "Home"]
                                                          [99 "Strategy"]
                                                          [100 "Supply"]]}]
                                 ["Region" {:description "Divine the region"
                                            :options nil
                                            :random-event [[12 "Barrier Islands"]
                                                           [24 "Ragged Coast"]
                                                           [34 "Deep Wilds"]
                                                           [46 "Flooded Lands"]
                                                           [60 "Havens"]
                                                           [72 "Hinterlands"]
                                                           [84 "Tempest Hills"]
                                                           [94 "Veiled Mountains"]
                                                           [99 "Shattered Wastes"]
                                                           [100 "Elsewhere"]]}]
                                 ["Location" {:description "Divine the location"
                                              :options nil
                                              :random-event [[1 "Hideout"]
                                                             [2 "Ruin"]
                                                             [3 "Mine"]
                                                             [4 "Waste"]
                                                             [5 "Mystical Path"]
                                                             [6 "Path"]
                                                             [7 "Outpost"]
                                                             [8 "Wall"]
                                                             [9 "Battlefield"]
                                                             [10 "Hovel"]
                                                             [11 "Spring"]
                                                             [12 "Lair"]
                                                             [13 "Fort"]
                                                             [14 "Bridge"]
                                                             [15 "Camp"]
                                                             [16 "Cairn/Grave"]
                                                             [18 "Caravan"]
                                                             [20 "Waterfall"]
                                                             [22 "Cave"]
                                                             [24 "Swamp"]
                                                             [26 "Fen"]
                                                             [28 "Ravine"]
                                                             [30 "Road"]
                                                             [32 "Tree"]
                                                             [34 "Pond"]
                                                             [36 "Fields"]
                                                             [38 "March"]
                                                             [40 "Steading"]
                                                             [42 "Rapids"]
                                                             [44 "Pass"]
                                                             [46 "Trail"]
                                                             [48 "Glade"]
                                                             [50 "Plain"]
                                                             [52 "Ridge"]
                                                             [54 "Cliff"]
                                                             [56 "Grove"]
                                                             [58 "Village"]
                                                             [60 "Moor"]
                                                             [62 "Thicket"]
                                                             [64 "River Ford"]
                                                             [66 "Valley"]
                                                             [68 "Bay/Fjord"]
                                                             [70 "Foothills"]
                                                             [72 "Lake"]
                                                             [75 "River"]
                                                             [79 "Forest"]
                                                             [83 "Coast"]
                                                             [88 "Hill"]
                                                             [93 "Mountain"]
                                                             [99 "Woods"]
                                                             [100 "Anomaly"]]}]
                                 ["Coastal Waters Location" {:description "Divine a coastal waters location"
                                                             :options nil
                                                             :random-event [[1 "Fleet"]
                                                                            [2 "Sargassum"]
                                                                            [3 "Flotsam"]
                                                                            [4 "Mystical Site"]
                                                                            [5 "Lair"]
                                                                            [10 "Wreck"]
                                                                            [15 "Harbour"]
                                                                            [23 "Ship"]
                                                                            [30 "Rocks"]
                                                                            [38 "Fjord"]
                                                                            [46 "Estuary"]
                                                                            [54 "Cove"]
                                                                            [62 "Bay"]
                                                                            [70 "Ice"]
                                                                            [85 "Island"]
                                                                            [99 "Open Water"]
                                                                            [100 "Anomaly"]]}]
                                 ["Location Descriptor" {:description "Divine a descriptor for your location"
                                                         :options nil
                                                         :random-event [[2 "High"]
                                                                        [4 "Remote"]
                                                                        [6 "Exposed"]
                                                                        [8 "Small"]
                                                                        [10 "Broken"]
                                                                        [12 "Diverse"]
                                                                        [14 "Rough"]
                                                                        [16 "Dark"]
                                                                        [18 "Shadowy"]
                                                                        [20 "Contested"]
                                                                        [22 "Grim"]
                                                                        [24 "Wild"]
                                                                        [26 "Fertile"]
                                                                        [28 "Blocked"]
                                                                        [30 "Ancient"]
                                                                        [32 "Perilous"]
                                                                        [34 "Hidden"]
                                                                        [36 "Occupied"]
                                                                        [38 "Rich"]
                                                                        [40 "Big"]
                                                                        [42 "Savage"]
                                                                        [44 "Defended"]
                                                                        [46 "Withered"]
                                                                        [48 "Mystical"]
                                                                        [50 "Inaccessible"]
                                                                        [52 "Protected"]
                                                                        [54 "Abandoned"]
                                                                        [56 "Wide"]
                                                                        [58 "Foul"]
                                                                        [60 "Dead"]
                                                                        [62 "Ruined"]
                                                                        [64 "Barren"]
                                                                        [66 "Cold"]
                                                                        [68 "Blighted"]
                                                                        [70 "Low"]
                                                                        [72 "Beautiful"]
                                                                        [74 "Abundant"]
                                                                        [76 "Lush"]
                                                                        [78 "Flooded"]
                                                                        [80 "Empty"]
                                                                        [82 "Strange"]
                                                                        [84 "Corrupted"]
                                                                        [86 "Peaceful"]
                                                                        [88 "Forgotten"]
                                                                        [90 "Expansive"]
                                                                        [92 "Settled"]
                                                                        [94 "Dense"]
                                                                        [96 "Civilized"]
                                                                        [98 "Desolate"]
                                                                        [100 "Isolated"]]}]
                                 ["Settlement Name" {:description "Divine the name of a settlement"
                                                     :options nil
                                                     :random-event [[15 {:description "A feature of the landscape. Envision what it is. What makes it unusual or distinctive?"
                                                                         :options nil
                                                                         :random-event [[10 "Highmount"]
                                                                                        [20 "Brackwater"]
                                                                                        [30 "Frostwood"]
                                                                                        [40 "Redcrest"]
                                                                                        [50 "Grimtree"]
                                                                                        [60 "Stoneford"]
                                                                                        [70 "Deepwater"]
                                                                                        [80 "Whitefall"]
                                                                                        [90 "Graycliff"]
                                                                                        [100 "Three Rivers"]]}
                                                                     30 {:description "A manmade edifice. What is it? Why is it important to this settlement's history?"
                                                                         :options nil
                                                                         :random-event [[10 "Whitebridge"]
                                                                                        [20 "Lonefort"]
                                                                                        [30 "Highcairn"]
                                                                                        [40 "Redhall"]
                                                                                        [50 "Darkwell"]
                                                                                        [60 "Timberwall"]
                                                                                        [70 "Stonetower"]
                                                                                        [80 "Thornhall"]
                                                                                        [90 "Cinderhome"]
                                                                                        [100 "Fallowfield"]]}
                                                                     45 {:description "A creature. Why have the people of this settlement chosen this creature as their totem? How is it represented in art or rituals?"
                                                                         :options nil
                                                                         :random-event [[10 "Ravencliff"]
                                                                                        [20 "Bearmark"]
                                                                                        [30 "Wolfcrag"]
                                                                                        [40 "Eaglesprite"]
                                                                                        [50 "Wyvern's Rest"]
                                                                                        [60 "Boarwood"]
                                                                                        [70 "Foxhollow"]
                                                                                        [80 "Elderwatch"]
                                                                                        [90 "Elkfield"]
                                                                                        [100 "Dragonshadow"]]}
                                                                     60 {:description "A historical event. What happened here? What place or practice commemorates this event?"
                                                                         :options nil
                                                                         :random-event [[10 "Swordbreak"]
                                                                                        [20 "Fool's Fall"]
                                                                                        [30 "Firstmeet"]
                                                                                        [40 "Brokenhelm"]
                                                                                        [50 "Mournhaunt"]
                                                                                        [60 "Olgar's Stand"]
                                                                                        [70 "Lostwater"]
                                                                                        [80 "Rojirra's Lament"]
                                                                                        [90 "Lastmarch"]
                                                                                        [100 "Rockfall"]]}
                                                                     75 {:description "A word in an Old World language. What culture is represented by this word. What does it translate to?"
                                                                         :options nil
                                                                         :random-event [[10 "Abon"]
                                                                                        [20 "Daveza"]
                                                                                        [30 "Damula"]
                                                                                        [40 "Essus"]
                                                                                        [50 "Sina"]
                                                                                        [60 "Kazeera"]
                                                                                        [70 "Khazu"]
                                                                                        [80 "Sova"]
                                                                                        [90 "Nabuma"]
                                                                                        [100 "Tiza"]]}
                                                                     90 {:description "A season or environmental aspect. What influence does the weather have on this settlement?"
                                                                         :options nil
                                                                         :random-event [[10 "Winterhome"]
                                                                                        [20 "Windhaven"]
                                                                                        [30 "Stormrest"]
                                                                                        [40 "Bleakfrost"]
                                                                                        [50 "Springtide"]
                                                                                        [60 "Duskmoor"]
                                                                                        [70 "Frostcrag"]
                                                                                        [80 "Springbrook"]
                                                                                        [90 "Icebreak"]
                                                                                        [100 "Summersong"]]}
                                                                     100 {:description "Something else..."
                                                                          :options nil
                                                                          :random-event [[10 "A trade good (Ironhome)"]
                                                                                         [20 "An Old World city (New Arkesh)"]
                                                                                         [30 "A founder or famous settler (Kei's Hall)"]
                                                                                         [40 "A god (Elisora"]
                                                                                         [50 "A historical item (Blackhelm)"]
                                                                                         [60 "A firstborn race (Elfbrook)"]
                                                                                         [70 "An elvish word or name (Nessana)"]
                                                                                         [80 "A mythic belief or event (Ghostwalk)"]
                                                                                         [90 "A positive term (Hope)"]
                                                                                         [100 "A negative term (Forsaken)"]]}]]}]
                                 ["Quick Settlement Name Generator" {:description "Quickly divine the name of a settlement."
                                                                     :options [["Prefix" {:description "Divine the prefix"
                                                                                          :options nil
                                                                                          :random-event [[4 "Bleak-"]
                                                                                                         [8 "Green-"]
                                                                                                         [12 "Wolf-"]
                                                                                                         [16 "Raven-"]
                                                                                                         [20 "Gray-"]
                                                                                                         [24 "Red-"]
                                                                                                         [28 "Axe-"]
                                                                                                         [32 "Great-"]
                                                                                                         [36 "Wood-"]
                                                                                                         [40 "Low-"]
                                                                                                         [44 "White-"]
                                                                                                         [48 "Storm-"]
                                                                                                         [52 "Black-"]
                                                                                                         [56 "Mourn-"]
                                                                                                         [60 "New-"]
                                                                                                         [64 "Stone-"]
                                                                                                         [68 "Grim-"]
                                                                                                         [72 "Lost-"]
                                                                                                         [76 "High-"]
                                                                                                         [80 "Rock-"]
                                                                                                         [84 "Shield-"]
                                                                                                         [88 "Sword-"]
                                                                                                         [92 "Frost-"]
                                                                                                         [96 "Thorn-"]
                                                                                                         [100 "Long-"]]}]
                                                                               ["Suffix" {:description "Divine the suffix"
                                                                                          :options nil
                                                                                          :random-event [[4 "-moor"]
                                                                                                         [8 "-ford"]
                                                                                                         [12 "-crag"]
                                                                                                         [16 "-watch"]
                                                                                                         [20 "-hope"]
                                                                                                         [24 "-wood"]
                                                                                                         [28 "-ridge"]
                                                                                                         [32 "-stone"]
                                                                                                         [36 "-haven"]
                                                                                                         [40 "-fall(s)"]
                                                                                                         [44 "-river"]
                                                                                                         [48 "-field"]
                                                                                                         [52 "-hill"]
                                                                                                         [56 "-bridge"]
                                                                                                         [60 "-mark"]
                                                                                                         [64 "-cairn"]
                                                                                                         [68 "-land"]
                                                                                                         [72 "-hall"]
                                                                                                         [76 "-mount"]
                                                                                                         [80 "-rock"]
                                                                                                         [84 "-brook"]
                                                                                                         [88 "-barrow"]
                                                                                                         [92 "-stead"]
                                                                                                         [96 "-home"]
                                                                                                         [100 "-wick"]]}]]}]
                                 ["Settlement Trouble" {:description "Divine the settlement trouble"
                                                        :options nil
                                                        :random-event [[2 "Outsiders rejected"]
                                                                       [4 "Dangerous discovery"]
                                                                       [6 "Dreadful omens"]
                                                                       [8 "Natural disaster"]
                                                                       [10 "Old wounds reopened"]
                                                                       [12 "Important object is lost"]
                                                                       [14 "Someone is captured"]
                                                                       [16 "Mysterious phenomenon"]
                                                                       [18 "Revolt against a leader"]
                                                                       [20 "Vengeful outcast"]
                                                                       [22 "Rival settlement"]
                                                                       [24 "Nature strikes back"]
                                                                       [26 "Someone is missing"]
                                                                       [28 "Production halts"]
                                                                       [30 "Mysterious murders"]
                                                                       [32 "Debt comes due"]
                                                                       [34 "Unjust leadership"]
                                                                       [36 "Disastrous accident"]
                                                                       [38 "In league with the enemy"]
                                                                       [40 "Raiders prey on the weak"]
                                                                       [42 "Cursed past"]
                                                                       [44 "An innocent is accused"]
                                                                       [46 "Corrupted by dark magic"]
                                                                       [48 "Isolated by brutal weather"]
                                                                       [50 "Provisions are scarce"]
                                                                       [52 "Sickness runs amok"]
                                                                       [54 "Allies become enemies"]
                                                                       [56 "Attack is imminent"]
                                                                       [58 "Lost caravan"]
                                                                       [60 "Dark secret revealed"]
                                                                       [62 "Urgent expedition"]
                                                                       [64 "A leader falls"]
                                                                       [66 "Families in conflict"]
                                                                       [68 "Incompetent leadership"]
                                                                       [70 "Reckless warmongering"]
                                                                       [72 "Beast on the hunt"]
                                                                       [74 "Betrayed from within"]
                                                                       [76 "Broken truce"]
                                                                       [78 "Wrathful haunt"]
                                                                       [80 "Conflict with the firstborn"]
                                                                       [82 "Trade route blocked"]
                                                                       [84 "In the crossfire"]
                                                                       [86 "Stranger causes discord"]
                                                                       [88 "Important event threatened"]
                                                                       [90 "Dangerous tradition"]
                                                                       [100 "Roll twice"]]}]
                                 ["Character Role" {:description "Divine the role of a character."
                                                    :options nil
                                                    :random-event [[2 "Criminal"]
                                                                   [4 "Healer"]
                                                                   [6 "Bandit"]
                                                                   [9 "Guide"]
                                                                   [12 "Performer"]
                                                                   [15 "Miner"]
                                                                   [18 "Mercenary"]
                                                                   [21 "Outcast"]
                                                                   [24 "Vagrant"]
                                                                   [27 "Forester"]
                                                                   [30 "Traveler"]
                                                                   [33 "Mystic"]
                                                                   [36 "Priest"]
                                                                   [39 "Sailor"]
                                                                   [42 "Pilgrim"]
                                                                   [45 "Thief"]
                                                                   [48 "Adventurer"]
                                                                   [51 "Forager"]
                                                                   [54 "Leader"]
                                                                   [58 "Guard"]
                                                                   [62 "Artisan"]
                                                                   [66 "Scout"]
                                                                   [70 "Herder"]
                                                                   [74 "Fisher"]
                                                                   [79 "Warrior"]
                                                                   [84 "Hunter"]
                                                                   [89 "Raider"]
                                                                   [94 "Trader"]
                                                                   [99 "Farmer"]
                                                                   [100 "Unusual role"]]}]
                                 ["Character Goal" {:description "Divine the characters goal."
                                                    :options nil
                                                    :random-event [[3 "Obtain an object"]
                                                                   [6 "Make an agreement"]
                                                                   [9 "Build a relationship"]
                                                                   [12 "Undermine a relationship"]
                                                                   [15 "Seek a truth"]
                                                                   [18 "Pay a debt"]
                                                                   [21 "Refute a falsehood"]
                                                                   [24 "Harm a rival"]
                                                                   [27 "Cure an ill"]
                                                                   [30 "Find a person"]
                                                                   [33 "Find a home"]
                                                                   [36 "Seize power"]
                                                                   [39 "Restore a relationship"]
                                                                   [42 "Create an item"]
                                                                   [45 "Travel to a place"]
                                                                   [48 "Secure provisions"]
                                                                   [51 "Rebel against power"]
                                                                   [54 "Collect a debt"]
                                                                   [57 "Protect a secret"]
                                                                   [60 "Spread faith"]
                                                                   [63 "Enrich themselves"]
                                                                   [66 "Protect a person"]
                                                                   [69 "Protect the status quo"]
                                                                   [72 "Advance status"]
                                                                   [75 "Defend a place"]
                                                                   [78 "Avenge a wrong"]
                                                                   [81 "Fulfill a duty"]
                                                                   [84 "Gain Knowledge"]
                                                                   [87 "Prove worthiness"]
                                                                   [90 "Find redemption"]
                                                                   [92 "Escape something"]
                                                                   [95 "Resolve a dispute"]
                                                                   [100 "Roll twice"]]}]
                                 ["Character Descriptor" {:description "Divine a character descriptor."
                                                          :options nil
                                                          :random-event [[1 "Stoic"]
                                                                         [2 "Attractive"]
                                                                         [3 "Passive"]
                                                                         [4 "Aloof"]
                                                                         [5 "Affectionate"]
                                                                         [6 "Generous"]
                                                                         [7 "Smug"]
                                                                         [8 "Armed"]
                                                                         [9 "Clever"]
                                                                         [10 "Brave"]
                                                                         [11 "Ugly"]
                                                                         [12 "Sociable"]
                                                                         [13 "Doomed"]
                                                                         [14 "Connected"]
                                                                         [15 "Bold"]
                                                                         [16 "Jealous"]
                                                                         [17 "Angry"]
                                                                         [18 "Active"]
                                                                         [19 "Suspicious"]
                                                                         [20 "Hostile"]
                                                                         [21 "Hardhearted"]
                                                                         [22 "Successful"]
                                                                         [23 "Talented"]
                                                                         [24 "Experienced"]
                                                                         [25 "Deceitful"]
                                                                         [26 "Ambitious"]
                                                                         [27 "Aggressive"]
                                                                         [28 "Conceited"]
                                                                         [29 "Proud"]
                                                                         [30 "Stern"]
                                                                         [31 "Dependent"]
                                                                         [32 "Wary"]
                                                                         [33 "Strong"]
                                                                         [34 "Insightful"]
                                                                         [35 "Dangerous"]
                                                                         [36 "Quirky"]
                                                                         [37 "Cheery"]
                                                                         [38 "Disfigured"]
                                                                         [39 "Intolerant"]
                                                                         [40 "Skilled"]
                                                                         [41 "Stingy"]
                                                                         [42 "Timid"]
                                                                         [43 "Insensitive"]
                                                                         [44 "Wild"]
                                                                         [45 "Bitter"]
                                                                         [46 "Cunning"]
                                                                         [47 "Remorseful"]
                                                                         [48 "Kind"]
                                                                         [49 "Charming"]
                                                                         [50 "Oblivious"]
                                                                         [51 "Critical"]
                                                                         [52 "Cautious"]
                                                                         [53 "Resourceful"]
                                                                         [54 "Weary"]
                                                                         [55 "Wounded"]
                                                                         [56 "Anxious"]
                                                                         [57 "Powerful"]
                                                                         [58 "Athletic"]
                                                                         [59 "Driven"]
                                                                         [60 "Cruel"]
                                                                         [61 "Quiet"]
                                                                         [62 "Honest"]
                                                                         [63 "Infamous"]
                                                                         [64 "Dying"]
                                                                         [65 "Reclusive"]
                                                                         [66 "Artistic"]
                                                                         [67 "Disabled"]
                                                                         [68 "Confused"]
                                                                         [69 "Manipulative"]
                                                                         [70 "Relaxed"]
                                                                         [71 "Stealthy"]
                                                                         [72 "Confident"]
                                                                         [73 "Weak"]
                                                                         [74 "Friendly"]
                                                                         [75 "Wise"]
                                                                         [76 "Influental"]
                                                                         [77 "Young"]
                                                                         [78 "Adventurous"]
                                                                         [79 "Oppressed"]
                                                                         [80 "Vengeful"]
                                                                         [81 "Cooperative"]
                                                                         [82 "Armored"]
                                                                         [83 "Apathetic"]
                                                                         [84 "Determined"]
                                                                         [85 "Loyal"]
                                                                         [86 "Sick"]
                                                                         [87 "Religious"]
                                                                         [88 "Selfish"]
                                                                         [89 "Old"]
                                                                         [90 "Fervent"]
                                                                         [91 "Violent"]
                                                                         [92 "Agreeable"]
                                                                         [93 "Hot-tempered"]
                                                                         [94 "Stubborn"]
                                                                         [95 "Incompetent"]
                                                                         [96 "Greedy"]
                                                                         [97 "Cowardly"]
                                                                         [98 "Obsessed"]
                                                                         [99 "Careless"]
                                                                         [100 "Ironsworn"]]}]
                                 ;; here go the name oracles
                                 ["Combat Action" {:description "Divine a combat action."
                                                   :options nil
                                                   :random-event [[3 "Compel a surrender"]
                                                                  [6 "Coordinate with allies"]
                                                                  [9 "Gather reinforcments"]
                                                                  [13 "Seize something or someone"]
                                                                  [17 "Provoke a reckless response"]
                                                                  [21 "Intimidate or frighten"]
                                                                  [25 "Reveal a surprising truth"]
                                                                  [29 "Shift focus to someone or something else"]
                                                                  [33 "Destroy something, or render it useless"]
                                                                  [39 "Take a decisive action"]
                                                                  [45 "Reinforce defenses"]
                                                                  [52 "Ready an action"]
                                                                  [60 "Use the terrain to gain advantage"]
                                                                  [68 "Leverage the advantage of a weapon or ability"]
                                                                  [78 "Create an opportunity"]
                                                                  [89 "Attack with precision"]
                                                                  [99 "Attack with power"]
                                                                  [100 "Completely unexpected action"]]}]
                                 ["Mystic Backlash" {:description "Divine a mystic backlash."
                                                     :options nil
                                                     :random-event [[4 "Your ritual has the opposite effect"]
                                                                    [8 "You are sapped of strength"]
                                                                    [12 "Your friend, ally, or companion is adversely effected"]
                                                                    [16 "You destroy an important object"]
                                                                    [20 "You inadvertently summon a horror"]
                                                                    [24 "You collapse, and drift into a troubled sleep"]
                                                                    [28 "You undergo a physical torment which leaves its mark upon you"]
                                                                    [32 "You hear ghostly voices whispering of dark portents"]
                                                                    [36 "You are lost in shadow, and find yourself in another place without memory of how you got here"]
                                                                    [40 "You alert someone or something to your presence"]
                                                                    [44 "You are not yourself, and act against a friend, ally, or companion"]
                                                                    [48 "You effect or damage your surroundings, causing a disturbance or potential harm"]
                                                                    [52 "You waste resources"]
                                                                    [56 "You suffer the loss of a sense for several hours"]
                                                                    [60 "You lose connection to magic for a day or so, and cannot perform rituals"]
                                                                    [64 "Your ritual effects the target in an unexpected and problematic way"]
                                                                    [68 "Your ritual reveals a surprising and troubling truth"]
                                                                    [72 "You are tempted by dark powers"]
                                                                    [76 "You see a troubling vision of your future"]
                                                                    [80 "You can't perform this ritual again until you acquire an important component"]
                                                                    [84 "You develop a strange fear or compulsion"]
                                                                    [88 "Your ritual causes creatures to exhibit strange or aggressive behavior"]
                                                                    [92 "You are tormented by an apparition from your past"]
                                                                    [96 "You are wracked with sudden sickness"]
                                                                    [100 "Roll twice more on this table. Both results occur. If they are the same result, make it worse."]]}]
                                 ["Major Plot Twist" {:description "Divine a major plot twist"
                                                      :options nil
                                                      :random-event [[5 "It was all a diversion"]
                                                                     [10 "A dark secret is revealed"]
                                                                     [15 "A trap is sprung"]
                                                                     [20 "An assumption is revealed to be false"]
                                                                     [25 "A secret alliance is revealed"]
                                                                     [30 "Your actions benefit an enemy"]
                                                                     [35 "Someone returns unexpectedly"]
                                                                     [40 "A more dangerous foe is revealed"]
                                                                     [45 "You and an enemy share a common goal"]
                                                                     [50 "A true identity is revealed"]
                                                                     [55 "You are betrayed by someone who was trusted"]
                                                                     [60 "You are too late"]
                                                                     [65 "The true enemy is revealed"]
                                                                     [70 "The enemy gains new allies"]
                                                                     [75 "A new danger appears"]
                                                                     [80 "Someone or something goes missing"]
                                                                     [85 "The truth of a relationship is revealed"]
                                                                     [90 "Two seemingly unrelated situations are shown to be connected"]
                                                                     [95 "Unexpected powers or abilities are revealed"]
                                                                     [100 "Roll twice more. Both results occur. If they are the same result, make it more dramatic."]]}]
                                 ["Challenge Rank" {:desription "Divine the rank of a challenge"
                                                    :options nil
                                                    :random-event [[20 "Troublesome"]
                                                                   [55 "Dangerous"]
                                                                   [80 "Formidable"]
                                                                   [93 "Extreme"]
                                                                   [100 "Epic"]]}]
                                 ] 
                       :random-event nil}}}
   ])
