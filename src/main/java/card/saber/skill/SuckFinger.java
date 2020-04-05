package card.saber.skill;

import card.SkillCard;
import character.MainRole;
import character.Role;
import utils.buffs.foreverBuffs.Dexterity;
import utils.buffs.foreverBuffs.Dodge;
import utils.buffs.limitBuffs.Excite;
import utils.buffs.limitBuffs.Weak;

public class SuckFinger extends SkillCard {
    public SuckFinger() {
        super(OCCUPATION.SABER, "吮手指", 1, RARITY.COMMON, "Earn 1 excite and 1 dexterity, if you are in intangible, get 2 weak");

    }

    public SuckFinger(boolean upgraded) {
        super(OCCUPATION.SABER, "吮手指+", 1, RARITY.COMMON, "Earn 2 excite and 2 dexterity, if you are in intangible, get 2 weak");
        this.upgraded = true;
    }


    @Override
    public boolean upgrade() {
        if (this.upgraded) {
            return false;
        } else {
            this.upgraded = true;
            this.setCardName("吮手指+");
            this.setDescription("Earn 2 excite and 2 dexterity, if you are in intangible, get 2 weak");
        }
        return true;
    }


    @Override
    public boolean use(Role target) {
        if (!upgraded) {
            MainRole.getInstance().getBuff(new Excite(MainRole.getInstance(), 1));
            MainRole.getInstance().getBuff(new Dexterity(MainRole.getInstance(), 1));
        } else {
            MainRole.getInstance().getBuff(new Excite(MainRole.getInstance(), 2));
            MainRole.getInstance().getBuff(new Dexterity(MainRole.getInstance(), 2));
        }

        if (MainRole.getInstance().getIntangible().getDuration() > 0) {
            MainRole.getInstance().getBuff(new Weak(MainRole.getInstance(), 2));
        }
        return true;
    }

}
