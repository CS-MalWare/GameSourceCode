package card.neutral.skill;

import card.SkillCard;
import character.MainRole;
import character.Role;

public class Twice extends SkillCard {


    public Twice() {
        super(OCCUPATION.NEUTRAL, "分身", 2, RARITY.COMMON, "next damage to target will be double, gain -1 strength");
    }

    public Twice(boolean upgraded) {
        super(OCCUPATION.NEUTRAL, "分身+", 2, RARITY.COMMON, "next damage to target will be double");
        this.upgraded = true;
    }


    @Override
    public boolean upgrade() {
        if (this.upgraded) {
            return false;
        } else {
            this.upgraded = true;
            this.setCardName("分身+");
            this.setDescription("next damage to target will be double");
        }
        return true;
    }

    @Override
    public boolean use(Role target) {
        // TODO 这个得之后实现一下

        if (!upgraded) {
            MainRole.getInstance().setStrength(MainRole.getInstance().getStrength() - 1);
        }
        return true;
    }

}
