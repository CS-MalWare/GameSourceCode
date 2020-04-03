package card.neutral.skill;

import card.SkillCard;
import character.MainRole;
import character.Role;

public class SecretSkill extends SkillCard {
    public SecretSkill() {
        super(OCCUPATION.NEUTRAL, "秘密技能", 0, RARITY.COMMON, "draw a skill card, exhaust");
        this.exhaust = true;

    }

    public SecretSkill(boolean upgraded) {
        super(OCCUPATION.NEUTRAL, "秘密技能+", 0, RARITY.COMMON, "draw a skill card");

    }


    @Override
    public boolean upgrade() {
        if (this.upgraded) {
            return false;
        } else {
            this.upgraded = true;
            this.setCardName("秘密技能+");
            this.setDescription("draw a skill card");

        }
        return true;
    }


    @Override
    public boolean use(Role target) {
        // TODO 抽一张技能牌

        return true;
    }

}
