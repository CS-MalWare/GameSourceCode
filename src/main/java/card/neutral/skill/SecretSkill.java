package card.neutral.skill;

import appState.DecksState;
import appState.HandCardsState;
import card.Card;
import card.SkillCard;
import character.MainRole;
import character.Role;

import java.util.ArrayList;

public class SecretSkill extends SkillCard {
    public SecretSkill() {
        super(OCCUPATION.NEUTRAL, "秘密技能", 0, RARITY.COMMON, "draw a skill card, exhaust");
        this.exhaust = true;

    }

    public SecretSkill(boolean upgraded) {
        super(OCCUPATION.NEUTRAL, "秘密技能+", 0, RARITY.COMMON, "draw a skill card");

    }


    @Override
    public boolean upgrade() {
        if (this.upgraded) {
            return false;
        } else {
            this.upgraded = true;
            this.exhaust = false;
            this.setCardName("秘密技能+");
            this.setDescription("draw a skill card");

        }
        return true;
    }


    @Override
    public boolean use(Role target) {
        ArrayList<Card> drawDeck = DecksState.getInstance().getDrawDeck();
        for (Card card : drawDeck) {
            if (card.getType() == TYPE.SKILL) {
                drawDeck.remove(card);
                DecksState.getInstance().updateDrawNum();
                HandCardsState.getInstance().addToHand(card);
            }
            return true;
        }

        return true;
    }

}
