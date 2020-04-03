package card.neutral.skill;

import card.SkillCard;
import character.MainRole;
import character.Role;

public class Unyielding extends SkillCard {
    public Unyielding() {
        super(OCCUPATION.NEUTRAL, "不屈", 1, RARITY.RARE, "after using this card, prevent a death and keep 1 HP, exhaust");
    }

    public Unyielding(boolean upgraded) {
        super(OCCUPATION.NEUTRAL, "不屈+", 1, RARITY.RARE, "after using this card, prevent a death and keep current HP, exhaust");
        this.upgraded = true;
    }


    @Override
    public boolean upgrade() {
        if (this.upgraded) {
            return false;
        } else {
            this.setCardName("不屈+");
            this.setDescription("after using this card, prevent a death and keep current HP, exhaust");

        }
        return true;
    }

    @Override
    public boolean use(Role target) {
        // TODO 这个得之后实现一下
        return true;
    }

}
