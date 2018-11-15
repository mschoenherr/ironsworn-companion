(ns ironsworn-companion.moves)

;; This work is based on Ironsworn (found at www.ironswornrpg.com), created by Shawn Tomkin,
;; and licensed for our use under the Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International license
;; (creativecommons.org/licenses/by-nc-sa/4.0/).

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
                          :options ["Bolster your position: Take +2 momentum."
                                    "Prepare to act: Take initiative."]
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
                            :options ["You bolster your position: Take +1 momentum"
                                      "You find an opening. Inflict +1 harm."]
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
                          :options ["It's worse than you thought: Endure Harm:"
                                    "You are overcome: Endure Stress."
                                    "Your victory is short-lived: A new danger or foe appears, or an existing danger worsens."
                                    "You suffer colleteral damage: Something of value is lost or broken, or someone must pay an important cost."
                                    "You'll pay for it: An objective falls out of reach."
                                    "Others won't forget: You are marked for vengeance."]
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
                             :options ["Shake it off: If your health is greater than 0, suffer -1 momentum in exchange for +1 health."
                                       "Embrace the pain: Take +1 momentum."]
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
                          :options ["You die, but not before making a noble sacrifice. Envision your final moment."
                                    "Death desires something of you in exchange for lift. Envision what it wants (Ask the Oracle if unsure), and Swear an Iron Vow (formidable or extreme) to complete that quest. If you fail to score a hit when you swear an Iron Vow, or refuse the quest, you are dead. Otherwise, you return to the mortal world and are now cursed. You may only clear the cursed debility by completing that quest."]
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
                            :options ["Shake it off: If your spirit is greater than 0, suffer -1 momentum in exchange for +1 spirit."
                                      "Embrace the darkness: Take +1 momentum."]
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
                          :options ["Your spirit or your sanity breaks, but not before you make a noble sacrifice. Envision your final moments."
                                    "You see a vision of dreaded moment coming to pass. Envision that dark future (Ask the Oracle if unsure), and Swear an Iron Vow (formidable or extreme) to prevent it. If you fail to score a hit when you swear an Iron Vow, or refuse the quest, you are lost. Otherwise, you return to your senses and are now tormented. You may only clear the tormented debility by completing the quest."]
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
                       :options ["Exchange each additional -momentum for any combination of -health, -spirit or -supply, as appropriate to the circumstances."
                                 "Envision an event or discovery (Ask the Orace if unsure), which undermines your progress in a current quest, journey or fight. Then, for each additional -momentum, clear 1 unit of progress that track per its rank."]
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
                      :options ["You press on: Suffer -2 momentum, and do what you must to overcome this obstacle."
                                "You give up: Forsake your Vow"]
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
                      :options ["You recommit: Clear all but one filled progress, and raise the quest's rank by one (if not already epic)."
                                "You give up: Forsake your Vow."]
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
                       :options ["Make the most obvious negative outcome happen."
                                 "Envision two negative outcomes. Rate one as likely, and Ask the Oracle using the yes/no table. On a yes, make that outcome happen. Otherwise make it the other."
                                 "Roll on the following table."]
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
                       :options ["Draw a conclusion: Decide the answer based on the most interesting and obvious result."
                                 {:description "Ask a yes/no question. Decide the odds of a 'yes', and roll."
                                  :options [{:description "Almost Certain."
                                             :options nil
                                             :random-event [[10 "No"]
                                                            [100 "Yes"]]}
                                            {:description "Likely."
                                             :options nil
                                             :random-event [[25 "No"]
                                                            [100 "Yes"]]}
                                            {:description "50/50."
                                             :options nil
                                             :random-event [[50 "No"]
                                                            [100 "Yes"]]}
                                            {:description "Unlikely."
                                             :options nil
                                             :random-event [[75 "No"]
                                                            [100 "Yes"]]}
                                            {:description "Small Chance."
                                             :options nil
                                             :random-event [[90 "No"]
                                                            [100 "Yes"]]}]
                                  :random-event nil}
                                 {:description "Pick two: Envision two options, the first being 'likely' and roll to see which one happens."
                                  :options nil
                                  :random-event [[25 "First option."]
                                                 [100 "The other option."]]}
                                 "Spark an idea: Brainstorm or use a random prompt."] 
                       :random-event nil}}}
   ])
