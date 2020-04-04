package card.saber.attack;

import card.AttackCard;
import character.Role;

public class MagicalLightSlash extends AttackCard {
    public MagicalLightSlash() {
        super(OCCUPATION.SABER, "魔光斩", 3, RARITY.COMMON, "deal 21 damage", 21, 1);

    }


    public MagicalLightSlash(boolean upgraded) {
        super(OCCUPATION.SABER, "魔光斩+", 3, RARITY.COMMON, "deal 28 damage", 28, 1);
        this.upgraded = true;
    }

    @Override
    public boolean upgrade() {
        if (upgraded = true) return false;
        this.setCardName("魔光斩+");
        this.setDamage(28);
        this.upgraded = true;
        this.setDescription("deal 28 damage");
        return true;
    }

    @Override
    public boolean use(Role target) {
        if (!super.use(target)) {
            return false;
        }
        return true;
    }


}
