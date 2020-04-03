package card.neutral.skill;

import appState.DecksState;
import appState.HandCardsState;
import card.Card;
import card.SkillCard;
import character.MainRole;
import character.Role;

import java.util.ArrayList;

public class Winding extends SkillCard {
    public Winding() {
        super(OCCUPATION.NEUTRAL, "神化", 3, RARITY.LEGENDARY, "upgrade all attack cards in this battle, exhaust");
        this.exhaust = true;

    }

    public Winding(boolean upgraded) {
        super(OCCUPATION.NEUTRAL, "神化+", 3, RARITY.LEGENDARY, "upgrade all attack cards in this battle, heal me for 10 HP, exhaust");
        this.exhaust = true;
    }


    @Override
    public boolean upgrade() {
        if (this.upgraded) {
            return false;
        } else {
            this.upgraded = true;
            this.setCardName("神化+");
            this.setDescription("upgrade all attack cards in this battle, heal me for 10 HP, exhaust");
            //TODO
        }
        return true;
    }


    @Override
    public boolean use(Role target) {

        ArrayList<Card> handCards = HandCardsState.getInstance().getHandCards();
        for (Card card : handCards) {
            if (card.getType() == TYPE.ATTACK)
                card.upgrade();
        }
        ArrayList<Card> drawDeck = DecksState.getInstance().getDrawDeck();
        for (Card card : drawDeck) {
            if (card.getType() == TYPE.ATTACK)
                card.upgrade();
        }
        ArrayList<Card> dropDeck = DecksState.getInstance().getDropDeck();
        for (Card card : dropDeck) {
            if (card.getType() == TYPE.ATTACK)
                card.upgrade();
        }
        ArrayList<Card> exhaustDeck = DecksState.getInstance().getExhaustDeck();
        for (Card card : exhaustDeck) {
            if (card.getType() == TYPE.ATTACK)
                card.upgrade();
        }
        if (upgraded) {
            MainRole.getInstance().treat(10);
        }
        return true;
    }

}
