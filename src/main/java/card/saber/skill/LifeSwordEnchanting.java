package card.saber.skill;

import appState.DecksState;
import appState.HandCardsState;
import card.Card;
import card.SkillCard;
import character.Enemy;
import character.MainRole;
import character.Role;

import java.util.ArrayList;

public class LifeSwordEnchanting extends SkillCard {
    public LifeSwordEnchanting() {
        super(OCCUPATION.SABER, "生命剑术", 3, RARITY.COMMON, "the next 5 attacks become wood property. Draw 3 more cards in the next round");

    }

    public LifeSwordEnchanting(boolean upgraded) {
        super(OCCUPATION.SABER, "生命剑术+", 3, RARITY.COMMON, "the next 7 attacks become wood property. Draw 3 more cards in the next round");
        this.upgraded = true;
    }


    @Override
    public boolean upgrade() {
        if (this.upgraded) {
            return false;
        } else {
            this.upgraded = true;
            this.setCardName("生命剑术+");
            this.setDescription("the next 7 attacks become wood property. Draw 3 more cards in the next round");
        }
        return true;
    }


    @Override
    public boolean use(Role target) {
        if (!upgraded) {
            MainRole.getInstance().cardEffectsMap.put(this.getCardName(), 5);
        } else {
            MainRole.getInstance().cardEffectsMap.put(this.getCardName(), 7);
        }

        MainRole.getInstance().addCardEffect(this.getCardName());
        return true;
    }

}
