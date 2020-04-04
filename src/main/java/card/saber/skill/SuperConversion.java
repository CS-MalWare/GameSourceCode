package card.saber.skill;

import card.AttackCard;
import card.SkillCard;
import character.Enemy;
import character.MainRole;
import character.Role;

public class SuperConversion extends SkillCard {
    public SuperConversion() {
        super(OCCUPATION.SABER, "超能转化", 3, RARITY.LEGENDARY, "specify an enemy and exchange its buff and debuff with yours");

    }

    public SuperConversion(boolean upgraded) {
        super(OCCUPATION.SABER, "超能转化+", 3, RARITY.LEGENDARY, "specify an enemy and exchange its buff and debuff with yours, and clear its buff and debuff");

        this.upgraded = true;
    }


    @Override
    public boolean upgrade() {
        if (this.upgraded) {
            return false;
        } else {
            this.upgraded = true;
            this.setCardName("超能转化+");
            this.setDescription("specify an enemy and exchange its buff and debuff with yours, and clear its buff and debuff");
        }
        return true;
    }


    @Override
    public boolean use(Role target) {
        if (!(target instanceof Enemy)) {
            return false;
        }
        // TODO 互换buff和debuff
        return true;
    }

}