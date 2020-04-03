package card.neutral.skill;

import card.SkillCard;
import character.Role;

public class ChargeMoney extends SkillCard {
    public ChargeMoney() {
        super(OCCUPATION.NEUTRAL, "充钱", 1, RARITY.EPIC, "select a card from anywhere, gain 2 copies of this card");
    }

    public ChargeMoney(boolean upgraded) {
        super(OCCUPATION.NEUTRAL, "充钱+", 1, RARITY.EPIC, "select a card from anywhere, gain 4 copies of this card");
        this.upgraded = true;
    }


    @Override
    public boolean upgrade() {
        if (this.upgraded) {
            return false;
        } else {
            this.upgraded = true;
            this.setCardName("充钱+");
            this.setDescription("select a card from anywhere, gain 4 copies of this card");
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
