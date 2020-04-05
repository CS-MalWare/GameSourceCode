package card.saber.skill;

import card.SkillCard;
import character.MainRole;
import character.Role;
import utils.buffs.limitBuffs.Intangible;

public class SealingSpell extends SkillCard {
    public SealingSpell() {
        super(OCCUPATION.SABER, "缄咒", 1, RARITY.COMMON, "can only be released after card rare silence is released. the enemies's block will be removed, if the block is less than 15");
        this.AOE = true;
    }

    public SealingSpell(boolean upgraded) {
        super(OCCUPATION.SABER, "缄咒+", 1, RARITY.COMMON, "can only be released after card rare silence is released. the enemies's block will be removed, if the block is less than 23");
        this.AOE = true;
        this.upgraded = true;
    }


    @Override
    public boolean upgrade() {
        if (this.upgraded) {
            return false;
        } else {
            this.upgraded = true;
            this.setCardName("缄咒+");
            this.setDescription("can only be released after card rare silence is released. the enemies's block will be removed, if the block is less than 23");
        }
        return true;
    }


    @Override
    public boolean use(Role target) {
        // TODO 待实现
        return true;
    }

}
