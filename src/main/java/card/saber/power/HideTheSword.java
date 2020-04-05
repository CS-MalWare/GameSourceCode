package card.saber.power;

import card.PowerCard;
import character.Role;

public class HideTheSword extends PowerCard {
    public HideTheSword() {
        super(OCCUPATION.SABER, "藏剑", 0, RARITY.EPIC, "The next attack gets 2^n ATK(n is the round)");
    }

    public HideTheSword(boolean upgraded) {
        super(OCCUPATION.SABER, "藏剑", 0, RARITY.EPIC, "The next attack gets 3^n ATK(n is the round)");
    }


    @Override
    public boolean upgrade() {
        if (upgraded = true) return false;
        this.setCardName("藏剑+");
        this.upgraded = true;
        this.setDescription("The next attack gets 3^n ATK(n is the round)");
        return true;
    }


    public boolean use(Role target) {

        //TODO

        return true;
    }
}