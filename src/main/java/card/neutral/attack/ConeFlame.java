package card.neutral.attack;

import card.AttackCard;
import character.Role;
import utils.buffs.limitBuffs.Bleeding;

public class ConeFlame extends AttackCard {
    public ConeFlame() {
        super(OCCUPATION.NEUTRAL, "艳阳锥", 2, RARITY.COMMON, "deal 10 fire property damage and double damage to vulnerable enemies", 10, 1);
        this.property = PROPERTY.FIRE;
    }

    public ConeFlame(boolean upgrade) {
        super(OCCUPATION.NEUTRAL, "艳阳锥+", 2, RARITY.COMMON, "deal 13 fire property damage and double damage to vulnerable enemies", 13, 1);
        this.upgraded = true;
        this.property = PROPERTY.FIRE;
    }

    @Override
    public boolean upgrade() {
        if (upgraded = true) return false;
        this.setCardName("艳阳锥+");
        this.setDamage(22);
        this.upgraded = true;
        this.setDescription("deal 13 fire property damage and double damage to vulnerable enemies");
        return true;
    }

    @Override
    public boolean use(Role target) {
        int oldDamge = this.damage;

        if (target.getVulnerable().getDuration() > 0) {
            this.damage *= 2;
        }
        if (!super.use(target)) {
            this.damage = oldDamge;
            return false;
        }
        this.damage = oldDamge;
        return true;
    }
}
