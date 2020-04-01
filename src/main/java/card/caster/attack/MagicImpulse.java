package card.caster.attack;

import card.AttackCard;
import card.Card;
import character.MainRole;
import character.Role;
import utils.buffs.limitBuffs.Weak;

public class MagicImpulse extends AttackCard {



    public MagicImpulse(boolean upgraded) {
        super(OCCUPATION.CASTER, "Magic impulse(+)", 1, RARITY.RARE, "deal 11 damage and keep one turn of cards", 11, 1);
        this.upgraded = true;

    }

    public MagicImpulse() {
        super(OCCUPATION.CASTER, "Magic impulse", 1, RARITY.RARE, "deal 8 damage and keep one turn of cards", 8, 1);
        this.upgraded = false;

    }


    @Override
    public boolean use(Role target) {
        if (!super.use(target)) return false;

        return true;
    }

    public boolean upgrade() {
        if (upgraded = true) return false;

        this.setName("Magic impulse(+)");
        this.setDamage(11);
        this.upgraded = true;
        this.setDescription("deal 8 damage and keep one turn of cards");
        return true;

    }

}
