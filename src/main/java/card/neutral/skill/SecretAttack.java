package card.neutral.skill;

import appState.DecksState;
import appState.HandCardsState;
import card.Card;
import card.SkillCard;
import character.Role;

import java.util.ArrayList;

public class SecretAttack extends SkillCard {
    public SecretAttack() {
        super(OCCUPATION.NEUTRAL, "秘密力量", 0, RARITY.COMMON, "draw a attack card, exhaust");
        this.exhaust = true;

    }

    public SecretAttack(boolean upgraded) {
        super(OCCUPATION.NEUTRAL, "秘密力量+", 0, RARITY.COMMON, "draw a attack card");

    }


    @Override
    public boolean upgrade() {
        if (this.upgraded) {
            return false;
        } else {
            this.upgraded = true;
            this.setCardName("秘密力量+");
            this.setDescription("draw a attack card");

        }
        return true;
    }


    @Override
    public boolean use(Role target) {
        ArrayList<Card> drawDeck = DecksState.getInstance().getDrawDeck();
        for (Card card : drawDeck) {
            if (card.getType() == TYPE.ATTACK) {
                drawDeck.remove(card);
                DecksState.getInstance().updateDrawNum();
                HandCardsState.getInstance().addToHand(card);
            }
            return true;
        }
        return true;
    }

}
