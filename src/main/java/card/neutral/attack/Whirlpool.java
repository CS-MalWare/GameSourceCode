package card.neutral.attack;

import card.AttackCard;
import character.MainRole;
import character.Role;
import utils.buffs.limitBuffs.Bleeding;

public class Whirlpool extends AttackCard {
    public Whirlpool() {
        super(OCCUPATION.NEUTRAL, "旋涡", 2, RARITY.RARE, "deal 10 water property damage to all enemies, if target is killed, then gain 2 MP and draw 3 cards", 10, 1);
        this.property = PROPERTY.WATER;
    }

    public Whirlpool(boolean upgrade) {
        super(OCCUPATION.NEUTRAL, "旋涡+", 3, RARITY.RARE, "deal 12 water property damage to all enemies, if target is killed, then gain 3 MP and draw 3 cards", 12, 1);
        this.upgraded = true;
        this.property = PROPERTY.WATER;
    }

    @Override
    public boolean upgrade() {
        if (upgraded = true) return false;
        this.setCardName("旋涡+");
        this.setDamage(12);
        this.upgraded = true;
        this.setDescription("deal 12 water property damage to all enemies, if target is killed, then gain 3 MP and draw 3 cards");
        return true;
    }

    @Override
    public boolean use(Role... targets) {
        if (!super.use(targets)) return false;
        if (upgraded) {
            for (Role target : targets)
                if (target.getHP() <= 0) {
                    MainRole.getInstance().getMP(2);
                    MainRole.getInstance().drawCards(3);
                }
        } else {
            for (Role target : targets)
                if (target.getHP() <= 0) {
                    MainRole.getInstance().getMP(3);
                    MainRole.getInstance().drawCards(3);
                }
        }
        return true;
    }
}
