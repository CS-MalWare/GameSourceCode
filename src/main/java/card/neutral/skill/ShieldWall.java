package card.neutral.skill;

import card.SkillCard;
import character.MainRole;
import character.Role;
import org.codehaus.groovy.tools.shell.Main;

public class ShieldWall extends SkillCard {
    public ShieldWall() {
        super(OCCUPATION.NEUTRAL, "盾墙", 1, RARITY.COMMON, "gain 5 blocks, if my strength is greater than 2, get another 5 blocks");
    }

    public ShieldWall(boolean upgraded) {
        super(OCCUPATION.NEUTRAL, "盾墙+", 1, RARITY.COMMON, "gain 8 blocks, if my strength is greater than 2, get another 8 blocks");
        this.upgraded = true;
    }


    @Override
    public boolean upgrade() {
        if (this.upgraded) {
            return false;
        } else {
            this.upgraded = true;
            this.setCardName("盾墙+");
            this.setDescription("gain 8 blocks, if my strength is greater than 2, get another 8 blocks");
            //TODO
        }
        return true;
    }


    @Override
    public boolean use(Role target) {
        if (!upgraded) {
            MainRole.getInstance().gainBlock(5);
        } else {
            MainRole.getInstance().gainBlock(8);
        }
        if (MainRole.getInstance().getStrength() > 2) {
            if (!upgraded)
                MainRole.getInstance().gainBlock(5);
            else
                MainRole.getInstance().gainBlock(8);
        }
        return true;
    }

}
