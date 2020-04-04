package card.saber.skill;

import card.SkillCard;
import character.MainRole;
import character.Role;

public class MagicSheild extends SkillCard {
    public MagicSheild() {
        super(OCCUPATION.SABER, "魔法盾", 2, RARITY.RARE, "gain 6 block, reduce the damage taken next time to half");

    }

    public MagicSheild(boolean upgraded) {
        super(OCCUPATION.SABER, "魔法盾+", 2, RARITY.RARE, "gain 10 block, reduce the damage taken next time to half");
        this.upgraded = true;
    }


    @Override
    public boolean upgrade() {
        if (this.upgraded) {
            return false;
        } else {
            this.upgraded = true;
            this.setCardName("魔法盾+");
            this.setDescription("gain 10 block, reduce the damage taken next time to half");
        }
        return true;
    }


    @Override
    public boolean use(Role target) {
        if (!upgraded) {
            MainRole.getInstance().gainBlock(6);
        } else {
            MainRole.getInstance().gainBlock(10);
        }
        //TODO 使下次受到伤害减半
        return true;
    }

}
