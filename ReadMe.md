# Etali Data Collection
Welcome to my cEDH data collection tool. Currently, I am using this to gather data on the odds of casting Etali, Primal Conqueror by turn 2 in my list. The goal of this testing is to optimize my decklist and build a mulligan guide to share with the community. If you are interested in mathematically analyzing Magic: the Gathering decks or helping with this tool, reach out to me on Discord: @thepapajohns
## Setup
1. Install Google Chrome (The tool opens chrome to moxfield, other browsers are not supported currently)
2. Download the executable
3. Contact me to get the Google credentials file and place it in the same directory as the executable
4. Run the executable (you will be told it is from an unknown publisher, select more info, then run anyway to start the program [Source code is provided if you are concerned about what the program does])
5. Start testing hands!
## Using This Tool
First, you should be prompted to enter your Discord username. This is optional, but it allows me to credit users for their contributions. If you choose not to use your Discord name, feel free to enter some other unique identifier so that I can distinguish which data was submitted by the same user.

Second, it should open a browser and navigate to my Etali list on Moxfield (https://moxfield.com/decks/JhWK-MfmY0W1V5feboKLxQ). You will notice some random output and a warning that is produced at this point. These are artifacts from the library I use to open the browser, and can be ignored. The tool may request various permissions which are used to access the browser. I have provided the source code behind the executable. Feel free to look through it if you have any concerns, and if you have any suggestions, feel free to reach out to me. I take the security of users seriously, so if there are any concerns, I will make sure to remedy them.

Third, you will be asked whether the hand that was shown in Moxfield represents a turn 2 Etali. There is a guide below for determining this, so I will not go into too much detail here. However, I will mention that at this stage is when you can break out of the program by typing Q or q. In addition, if you are unsure whether the hand is a turn 2 Etali or not (whether you are unsure of the line, if the rules are unclear, or you otherwise want a second opinion on the hand), you can enter a ? and the data will be marked for me or another player to review later.

Fourth, if you selected yes, then you will be asked how many cards were needed. This must be a number between 3 and 7 and reflect how many of the 7 cards in the opener were needed to cast a turn 2 Etali.

Finally, the program will reset Moxfield with a new hand. Please do not restart on your own, since the program will be storing the card names of the cards that appear in Moxfield. The card names are important for data analysis, so stick to using the Moxfield page that is opened or reset by the executable. Be aware that the program may have issues doing this if your aspect ratio or window size is especially unique. If there are any issues with this, reach out to me, and I may look into updating the selectors to resolve this. At this point, you will return to step 3 and keep gathering data!

## Rules for Determining Turn 2 Hands
You must only look at the 7 cards in the opener unless otherwise specified. You will not draw cards, but you will resolve tutors and other effects. You can goldfish on the moxfield page as much as you like to test out different sequencing. Hands that win the game with a dualcaster combo will count as a Turn 2 Etali as well (not technically the same thing, but you are winning the game, which is the goal of casting Etali). A few cards below will have special behaviours, which will be detailed. Remember, if you are ever confused, simply enter a ? and I can review the hand.

One time-saving trick I would recommend. Always check if the hand can turn 2, assuming that a particular card does what you want it to or is not used, before following the processes below. For example, if a Gemstone Caverns turn 0 doesn't get you there, then no need to roll for it. On the flip side, if you can get there without using the Gemstone Caverns, then you can simply ignore it (unless it would help you get there with fewer cards). Same is true for any other card that requires rolling or further analysis (Gamble, ____ Goblin, etc).

| Card  | How to Play |
| ------------- |:-------------:|
| Worldly/Sylvan Tutor     | If you cast these on turn 1, you may draw the card you tutored for on turn 2.    |
| Jeska's Will | Roll for seat order. If you are not 4th and cast this turn 1, we will treat it as if it adds 7 mana. Otherwise, treat it as if it adds 5 mana. |
| Gemstone Caverns | Roll for seat order, then take the pregame action if you are not first. |
|Carpet of Flowers| This one is really tricky, and I am still struggling to find a good solution. For the sake of this simulation carpet will only ever generate 1 mana. If you need carpet to generate mana for the hand to work, roll for seat order, then for each position past 1st that you are roll a d20 to determine which decks played before you using edhtop16. If one of them is predominantly blue, then you can proceed with Carpet, adding one mana. Otherwise, it does nothing on turn 1. If you need it turn 2, roll for all three other players, and follow your best judgment. ex) I need carpet to add 1 mana on turn 1 to play an Orcish Lumberjack, then to be the 7th mana on turn 2. I rolled 3rd and then rolled a 2 and a 5 on the 2 d20s. This results in the decks before me being Kinnan and Sisay. Sisay is unlikely to have an island, but Kinnan should have one a good amount of the time, so I will say it adds one mana.  |
|Ragavan|You can assume Ragavan can generate a treasure on turn 2, but you will ignore the card that it exiles.|
|Wheel of Fortune|For the sake of this simulation, this card cannot be used. There are too many random elements to wheeling and too few hands where it is relevant enough for me to resolve these.|
|Flare of Duplication|Can only be used to copy spells you cast.|
|____ Goblin|If you see this card in a hand, roll for the stickers using the rules commonly used. Roll 3 d20s, every 2 numbers is a sticker, so 1-2 is a 3, 3-4, is a 4, etc with 15-16 and 17-18 being the two 5s and 19-20 being the 6. You reroll a d20 if it rolled the same sticker as the previous one. An easier solution may be to keep the stickers on hand and select three randomly. If you copy ____ Goblin, follow the same rules as a real game for placing another sticker (not already on the first one) and adding that mana. Avoid using the MTGO version since the odds are different.|
| Gamble      | The classic mono-red entomb. To play this one out, currently, we are simply drawing the cards that you would have drawn tutoring for the card, then rolling for it and seeing if you can still get there. Put those cards back, then repeat as if the hand were a 6-card hand, 5-card hand, etc, until it does not work. This is obviously a flawed solution, but it is the most statistically sound method I have at the moment.  |

## Fixing Errors
If you make a mistake in data entry and catch it, feel free to go and fix it in the Google sheet! Your data should be at the bottom with a list of cards and your username that you entered. Make sure you are editing the right one and follow the format that others follow (place a 1 wherever the hand was successful and a 0 in the 7 card hand spot if the hand failed to cast Etali). Avoid entering/editing card names since they need to be exact for everything to work.

https://docs.google.com/spreadsheets/d/1JYxdNA1i9XMg652Tqwb4wqFOQsmMKsBvNPzf-NqO-i8/edit?usp=sharing

## Goal for The Data
There is a series of goals for the use of this data. First, I want to mathematically determine the optimal number of lands to run to maximize the odds of a turn 2 Etali. 

Once I have made the necessary changes to maximize the odds of a turn 2 Etali based on lands, I will go back through the data and reevaluate hands with cards that were swapped for lands. This process will be used to reevaluate the change in the number of lands until we reach an optimal amount.

Next, I will be modifying the tool to evaluate the odds of hands based on various changes to the deck. The goal will be to optimize the cards that are run in the deck for the purpose of a Turn 2 Etali. This will be testing various rituals and batteries that are on the edge of being run against the worst rituals in the deck, currently based on the data.

Finally, I want to evaluate the decision to cut certain cards that have a minimal impact on getting to a turn 2 Etali for cards that may be better off the flip or later in the game. This last step is basically to try and maximize our odds of getting a turn 2 Etali with the fewest slots dedicated to that as possible, so that the remaining slots can have higher card quality.

## THANK YOU FOR YOUR HELP!!!
If you have any issues or need help, please reach out to me on Discord: @thepapajohns
