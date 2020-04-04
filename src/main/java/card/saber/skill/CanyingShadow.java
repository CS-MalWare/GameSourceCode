package card.saber.skill;

import card.SkillCard;
import character.MainRole;
import character.Role;
import utils.buffs.foreverBuffs.Dodge;

public class CanyingShadow extends SkillCard {
    public CanyingShadow() {
        super(OCCUPATION.SABER, "残影", 4, RARITY.EPIC, "gain 30 blocks. keep your block for extra one turn");

    }

    public CanyingShadow(boolean upgraded) {
        super(OCCUPATION.SABER, "残影+", 4, RARITY.EPIC, "gain 45 blocks. keep your block for extra one turn");
        this.upgraded = true;
    }


    @Override
    public boolean upgrade() {
        if (this.upgraded) {
            return false;
        } else {
            this.upgraded = true;
            this.setCardName("残影+");
            this.setDescription("gain 45 blocks. keep your block for extra one turn");
        }
        return true;
    }


    @Override
    public boolean use(Role target) {
        // TODO 讲卡组中所有普通卡都替换成嗜血斩击
        if (!upgraded) {
            MainRole.getInstance().gainBlock(30);
        } else {
            MainRole.getInstance().gainBlock(45);
        }

        // TODO 格挡在当前下个回合开始时不会消失
        return true;
    }

}
