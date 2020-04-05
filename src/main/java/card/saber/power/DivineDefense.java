package card.saber.power;

import card.PowerCard;
import character.MainRole;
import character.Role;
import utils.buffs.limitBuffs.Excite;

public class DivineDefense extends PowerCard {
    public DivineDefense() {
        super(OCCUPATION.SABER, "神御格挡", 4, RARITY.RARE, "gain 5 blocks, 20% chance get 1 dodge before take damage each time, exhaust");
        this.exhaust = true;
    }

    public DivineDefense(boolean upgraded) {
        super(OCCUPATION.SABER, "神御格挡+", 4, RARITY.RARE, "gain 8 blocks, 20% chance get 1 dodge before take damage each time, exhaust, innate");
        this.exhaust = true;
        this.intrinsic = true;
    }


    @Override
    public boolean upgrade() {
        if (upgraded = true) return false;
        this.setCardName("神御格挡+");
        this.upgraded = true;
        this.setDescription("gain 8 blocks, 20% chance get 1 dodge before take damage each time, exhaust, innate");
        return true;
    }


    public boolean use(Role target) {

        if (!upgraded) {
            MainRole.getInstance().gainBlock(5);
        } else {
            MainRole.getInstance().gainBlock(8);
        }

        // TODO 20% 闪避几率

        return true;
    }
}
