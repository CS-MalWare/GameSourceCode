package card.saber.skill;

import card.SkillCard;
import character.MainRole;
import character.Role;
import utils.buffs.limitBuffs.Disarm;
import utils.buffs.limitBuffs.Intangible;

public class OutOfBody extends SkillCard {
    public OutOfBody() {
        super(OCCUPATION.SABER, "灵魂出窍", 1, RARITY.EPIC, "gain 5 intangible and disarm, intrinsic");
        this.intrinsic = true;
    }

    public OutOfBody(boolean upgraded) {
        super(OCCUPATION.SABER, "灵魂出窍+", 1, RARITY.EPIC, "gain 5 intangible, intrinsic");
        this.intrinsic = true;
        this.upgraded = true;
    }


    @Override
    public boolean upgrade() {
        if (this.upgraded) {
            return false;
        } else {
            this.upgraded = true;
            this.setCardName("灵魂出窍+");
            this.setDescription("ggain 5 intangible, intrinsic");
        }
        return true;
    }

    @Override
    public boolean use(Role target) {
        MainRole.getInstance().getBuff(new Intangible(MainRole.getInstance(), 5));
        if (!upgraded) {
            MainRole.getInstance().getBuff(new Disarm(MainRole.getInstance(), 5));
        }
        return true;
    }

}
