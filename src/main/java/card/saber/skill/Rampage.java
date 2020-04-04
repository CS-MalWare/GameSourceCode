package card.saber.skill;

import card.SkillCard;
import character.MainRole;
import character.Role;
import utils.buffs.foreverBuffs.Dodge;

public class Rampage extends SkillCard {
    public Rampage() {
        super(OCCUPATION.SABER, "暴走", 4, RARITY.LEGENDARY, "gain 1 dodge, replace all normal cards in the card group with bloodthirsty chop.");

    }

    public Rampage(boolean upgraded) {
        super(OCCUPATION.SABER, "暴走+", 4, RARITY.LEGENDARY, "gain 6 dodge, replace all normal cards in the card group with bloodthirsty chop.");
        this.upgraded = true;
    }


    @Override
    public boolean upgrade() {
        if (this.upgraded) {
            return false;
        } else {
            this.upgraded = true;
            this.setCardName("暴走+");
            this.setDescription("gain 6 dodge, replace all normal cards in the card group with bloodthirsty chop.");
        }
        return true;
    }


    @Override
    public boolean use(Role target) {
        // TODO 讲卡组中所有普通卡都替换成嗜血斩击
        if (!upgraded) {
            MainRole.getInstance().getBuff(new Dodge(MainRole.getInstance(), 1));
        } else {
            MainRole.getInstance().getBuff(new Dodge(MainRole.getInstance(), 6));

        }
        return true;
    }

}
