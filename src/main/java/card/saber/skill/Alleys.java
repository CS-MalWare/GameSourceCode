package card.saber.skill;

import card.SkillCard;
import character.MainRole;
import character.Role;
import utils.buffs.foreverBuffs.Dodge;
import utils.buffs.limitBuffs.Intangible;

public class Alleys extends SkillCard {
    public Alleys() {
        super(OCCUPATION.SABER, "苟活", 1, RARITY.COMMON, "earn a round intangible, No other cards can be played in this turn, exhaust");
        this.exhaust = true;
    }

    public Alleys(boolean upgraded) {
        super(OCCUPATION.SABER, "苟活+", 1, RARITY.COMMON, "earn a round intangible, No other cards can be played in this turn");
        this.upgraded = true;
    }


    @Override
    public boolean upgrade() {
        if (this.upgraded) {
            return false;
        } else {
            this.upgraded = true;
            this.setCardName("苟活+");
            this.setDescription("earn a round intangible, No other cards can be played in this turn");
        }
        return true;
    }


    @Override
    public boolean use(Role target) {
        MainRole.getInstance().getBuff(new Intangible(MainRole.getInstance(), 1));
        MainRole.getInstance().setMP_current(0);
        return true;
    }

}
