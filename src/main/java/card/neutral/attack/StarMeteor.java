package card.neutral.attack;

import card.AttackCard;
import character.Role;
import utils.buffs.limitBuffs.Bleeding;
import utils.buffs.limitBuffs.Vulnerable;

public class StarMeteor extends AttackCard {
    public StarMeteor() {
        super(OCCUPATION.NEUTRAL, "星陨", 4, RARITY.RARE, "deal 20 soil property damage to all enemies, and apply 2 vulnerable to them", 20, 1);
        this.property = PROPERTY.SOIL;
        this.AOE = true;
    }

    public StarMeteor(boolean upgrade) {
        super(OCCUPATION.NEUTRAL, "星陨+", 4, RARITY.RARE, "deal 28 soil property damage to all enemies, and apply 4 vulnerable to them", 28, 1);
        this.upgraded = true;
        this.property = PROPERTY.SOIL;
        this.AOE = true;
    }

    @Override
    public boolean upgrade() {
        if (upgraded = true) return false;
        this.setCardName("星陨+");
        this.setDamage(28);
        this.upgraded = true;
        this.setDescription("deal 28 soil property damage to all enemies, and apply 4 vulnerable to them");
        return true;
    }

    @Override
    public boolean use(Role... targets) {
        if (!super.use(targets)) return false;
        if (!upgraded) {
            for (Role target : targets)
                target.getBuff(new Vulnerable(target, 2));
        } else {
            for (Role target : targets)
                target.getBuff(new Bleeding(target, 4));
        }
        return true;
    }
}
