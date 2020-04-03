package card.neutral.skill;

import card.SkillCard;
import character.Role;

public class Crystallization extends SkillCard {
    public Crystallization() {
        super(OCCUPATION.NEUTRAL, "晶化", 4, RARITY.COMMON, "next 5 attacks become gold property, draw 5 more cards in the next round");
    }

    public Crystallization(boolean upgraded) {
        super(OCCUPATION.NEUTRAL, "晶化+", 4, RARITY.COMMON, "next 8 attacks become gold property, draw 8 more cards in the next round");
        this.upgraded = true;
    }

    @Override
    public boolean upgrade() {
        if (this.upgraded) {
            return false;
        } else {
            this.upgraded = true;
            this.setCardName("晶化+");
            this.setDescription("next 8 attacks become gold property, draw 8 more cards in the next round");
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
