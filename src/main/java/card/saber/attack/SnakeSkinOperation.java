package card.saber.attack;

import card.AttackCard;
import character.Enemy;
import character.MainRole;
import character.Role;

public class SnakeSkinOperation extends AttackCard {
    public SnakeSkinOperation() {
        super(OCCUPATION.SABER, "蛇皮操作", 4, RARITY.EPIC, "deal sum of the damage dealt in this battle to the target, ethereal, exhaust", 0, 1);
        this.ethereal = true;
        this.exhaust = true;
    }


    public SnakeSkinOperation(boolean upgraded) {
        super(OCCUPATION.SABER, "蛇皮操作+", 4, RARITY.EPIC, "deal sum of the damage dealt in this battle to the target, exhaust", 0, 1);
        this.exhaust = true;
        this.upgraded = true;
    }

    @Override
    public boolean upgrade() {
        if (upgraded = true) return false;
        this.setCardName("蛇皮操作+");
        this.setDamage(0);
        this.upgraded = true;
        this.setDescription("deal sum of the damage dealt in this battle to the target, exhaust");
        return true;
    }

    @Override
    public boolean use(Role target) {
        if (!(target instanceof Enemy)) {
            return false;
        }
        target.getDamage(MainRole.getInstance().computeDamage(target.getTotalHP() - target.getHP()));
        return true;
    }


}
