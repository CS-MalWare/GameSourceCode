package card.neutral.attack;

import card.AttackCard;
import character.MainRole;
import character.Role;
import utils.buffs.limitBuffs.Posion;

public class WindingByWoodSpirit extends AttackCard {
    public WindingByWoodSpirit() {
        super(OCCUPATION.NEUTRAL, "木灵缠绕", 3, RARITY.RARE, "deal 15 wood property damage to all enemies and apply 1 disarm and 5 poison to them", 15, 1);
        this.property = PROPERTY.WATER;
        this.AOE = true;

    }

    public WindingByWoodSpirit(boolean upgrade) {
        super(OCCUPATION.NEUTRAL, "木灵缠绕+", 3, RARITY.RARE, "deal 20 wood property damage to all enemies and apply 2 disarm and 6 poison to them", 20, 1);
        this.upgraded = true;
        this.property = PROPERTY.WOOD;
        this.AOE = true;

    }

    @Override
    public boolean upgrade() {
        if (upgraded = true) return false;
        this.setCardName("木灵缠绕+");
        this.setDamage(20);
        this.upgraded = true;
        this.setDescription("deal 20 wood property damage to all enemies and apply 2 disarm and 6 poison to them");

        return true;
    }

    @Override
    public boolean use(Role... targets) {
        if (!super.use(targets)) return false;
        if (upgraded) {
            for (Role target : targets)
                target.getBuff(new Posion(target, 5));
        } else {
            for (Role target : targets)
                target.getBuff(new Posion(target, 6));
        }
        return true;
    }


    @Override
    public boolean use(Role target) {
        if (!super.use(target)) return false;
        if (!upgraded) {
            target.getBuff(new Posion(target, 5));
        } else {
            target.getBuff(new Posion(target, 6));
        }
        return true;
    }
}
