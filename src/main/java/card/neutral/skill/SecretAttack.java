package card.neutral.skill;

import card.SkillCard;
import character.Role;

public class SecretAttack extends SkillCard {
    public SecretAttack() {
        super(OCCUPATION.NEUTRAL, "秘密力量", 0, RARITY.COMMON, "draw a attack card, exhaust");
        this.exhaust = true;

    }

    public SecretAttack(boolean upgraded) {
        super(OCCUPATION.NEUTRAL, "秘密力量+", 0, RARITY.COMMON, "draw a attack card");

    }


    @Override
    public boolean upgrade() {
        if (this.upgraded) {
            return false;
        } else {
            this.upgraded = true;
            this.setCardName("秘密力量+");
            this.setDescription("draw a attack card");

        }
        return true;
    }


    @Override
    public boolean use(Role target) {
        // TODO 抽一张能力牌

        return true;
    }

}
