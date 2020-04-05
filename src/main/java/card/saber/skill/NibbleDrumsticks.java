package card.saber.skill;

import card.SkillCard;
import character.Enemy;
import character.MainRole;
import character.Role;
import utils.buffs.limitBuffs.Vulnerable;
import utils.buffs.limitBuffs.Weak;

public class NibbleDrumsticks extends SkillCard {
    public NibbleDrumsticks() {
        super(OCCUPATION.SABER, "啃鸡腿", 2, RARITY.COMMON, "gain 2 strength, exhaust");
        this.exhaust = true;
    }

    public NibbleDrumsticks(boolean upgraded) {
        super(OCCUPATION.SABER, "啃鸡腿+", 2, RARITY.COMMON, "gain 3 strength, exhaust");
        this.exhaust = true;
        this.upgraded = true;
    }


    @Override
    public boolean upgrade() {
        if (this.upgraded) {
            return false;
        } else {
            this.upgraded = true;
            this.setCardName("啃鸡腿+");
            this.setDescription("gain 3 strength, exhaust");
        }
        return true;
    }

    @Override
    public boolean use(Role target) {
        if (!upgraded) {
            MainRole.getInstance().setStrength(MainRole.getInstance().getStrength() + 2);
        } else {
            MainRole.getInstance().setStrength(MainRole.getInstance().getStrength() + 3);
        }
        return true;
    }

}
