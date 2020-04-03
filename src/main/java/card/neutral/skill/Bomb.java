package card.neutral.skill;

import card.SkillCard;
import character.Role;

public class Bomb extends SkillCard {
    public Bomb() {
        super(OCCUPATION.NEUTRAL, "炸弹", 2, RARITY.EPIC, "deal 35 damage to all enemies after 3 rounds,exhaust");
    }

    public Bomb(boolean upgraded) {
        super(OCCUPATION.NEUTRAL, "炸弹+", 2, RARITY.EPIC, "deal 40 damage to all enemies after 3 rounds");
        this.upgraded = true;
    }


    @Override
    public boolean upgrade() {
        if (this.upgraded) {
            return false;
        } else {
            this.upgraded = true;
            this.setCardName("炸弹+");
            this.setDescription("deal 40 damage to all enemies after 3 rounds");
            //TODO
        }
        return true;
    }


    @Override
    public boolean use(Role target) {
        // TODO 这个得之后实现一下
        return true;
    }

}
