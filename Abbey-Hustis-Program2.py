# ---------------------------------------
# CSCI 127, Joy and Beauty of Data
# Program 2: Cribbage Hand
# Abbey Hustis
# ---------------------------------------
# A hand of cribbage is inputed and then the score you would recieve is the output
# ---------------------------------------

def print_hand(listOfHand):
    print("Cribbage Hand")
    print("-------------")
    for i in range(len(listOfHand)):
        print("Card", str(i+1) + ":", str(listOfHand[i][0]), "of", listOfHand[i][1].capitalize())
    print("Points scored:", evaluate_hand(listOfHand))
    print("")

        
#--------------------------------------
    

def evaluate_hand(listOfHand):
    scoreKey = {
        "threeKind": 6,
        "pair": 2,
        "sequence": 3,
        "fifteen": 2
    }
    
    score = 0
    ave = 0
    if (listOfHand[0][0] + listOfHand[1][0] + listOfHand[2][0]) >= 15:  #fifteen
        score += scoreKey["fifteen"] * ((listOfHand[0][0] + listOfHand[1][0] + listOfHand[2][0]) // 15)
    else:
        score += 0
        
    if listOfHand[0][0] == listOfHand[1][0] and listOfHand[0][0] == listOfHand[2][0]:     #three of a kind
        score += scoreKey["threeKind"]
        return score
        
    elif listOfHand[0][0] == listOfHand[1][0] or listOfHand[0][0] == listOfHand[2][0] or listOfHand[1][0] == listOfHand[2][0]:  #pair
        score += scoreKey["pair"]
        return score
    elif listOfHand[1][0] - 1 == listOfHand[0][0] or listOfHand[1][0] + 1 == listOfHand[0][0] or listOfHand[1][0] - 1 == listOfHand[2][0] or listOfHand[1][0] + 1 == listOfHand[2][0]:  #sequence
        ave = (listOfHand[1][0] + listOfHand[2][0] + listOfHand[0][0]) / 3
        if ave == listOfHand[1][0] or ave == listOfHand[2][0] or ave == listOfHand[0][0]:
            score += scoreKey["sequence"]
            return score
    else:
        score += 0
        return score
   
    

# --------------------------------------
# Do not change anything below this line
# --------------------------------------


def process_hands(cribbage_input, cards_in_hand):    

    for hand in cribbage_input:
        hand = hand.split()
        hand_as_list = []
        for i in range(cards_in_hand):
            hand_as_list.append([int(hand[0]), hand[1]])
            hand = hand[2:]
        print_hand(hand_as_list)
        evaluate_hand(hand_as_list)

# --------------------------------------

def main():
    cribbage_file= open("cribbage.txt", "r")
    process_hands(cribbage_file, 3)
    cribbage_file.close()

# --------------------------------------

main()
