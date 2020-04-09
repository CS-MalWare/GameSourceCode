package card.saber.power;

import card.PowerCard;
import character.MainRole;
import character.Role;
import utils.buffs.limitBuffs.Excite;

public class CounterStrikeGesture extends PowerCard {
    public CounterStrikeGesture() {
        super(OCCUPATION.SABER, "逆转反击", 2, RARITY.EPIC, "every time get attacked, deal 30% of damage to the attacker");
    }

    public CounterStrikeGesture(boolean upgraded) {
        super(OCCUPATION.SABER, "逆转反击+", 2, RARITY.EPIC, "every time get attacked, deal 40% of damage to the attacker");
        this.intrinsic = true;
    }


    @Override
    public boolean upgrade() {
        if (upgraded) return false;
        this.setCardName("逆转反击+");
        this.upgraded = true;
        this.setDescription("every time get attacked, deal 40% of damage to the attacker");
        return true;
    }


    public boolean use(Role target) {

        // TODO 反伤

        return true;
    }
}
