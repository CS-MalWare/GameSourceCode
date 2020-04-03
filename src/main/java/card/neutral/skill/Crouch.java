package card.neutral.skill;

import card.SkillCard;
import character.MainRole;
import character.Role;

public class Crouch extends SkillCard {
    public Crouch() {
        super(OCCUPATION.NEUTRAL, "蹲伏", 1, RARITY.COMMON, "gain 13 blocks, exhaust");
        this.exhaust = true;
    }

    public Crouch(boolean upgraded) {
        super(OCCUPATION.NEUTRAL, "蹲伏+", 1, RARITY.COMMON, "gain 13 blocks");
        this.upgraded = true;
    }


    @Override
    public boolean upgrade() {
        if (this.upgraded) {
            return false;
        } else {
            this.upgraded = true;
            this.setCardName("蹲伏+");
            this.setDescription("gain 13 blocks");
        }
        return true;
    }


    @Override
    public boolean use(Role target) {
        MainRole.getInstance().gainBlock(13);
        return true;
    }

}
