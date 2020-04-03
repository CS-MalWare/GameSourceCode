package card.neutral.skill;

import card.SkillCard;
import character.MainRole;
import character.Role;

public class Winding extends SkillCard {
    public Winding() {
        super(OCCUPATION.NEUTRAL, "神化", 3, RARITY.LEGENDARY, "upgrade all attack cards in this battle, exhaust");
        this.exhaust = true;

    }

    public Winding(boolean upgraded) {
        super(OCCUPATION.NEUTRAL, "神化+", 3, RARITY.LEGENDARY, "upgrade all attack cards in this battle, heal me for 10 HP, exhaust");
        this.exhaust = true;
    }


    @Override
    public boolean upgrade() {
        if (this.upgraded) {
            return false;
        } else {
            this.upgraded = true;
            this.setCardName("神化+");
            this.setDescription("upgrade all attack cards in this battle, heal me for 10 HP, exhaust");
            //TODO
        }
        return true;
    }


    @Override
    public boolean use(Role target) {
        //TODO 升级所有攻击卡
        if (upgraded) {
            MainRole.getInstance().treat(10);
        }
        return true;
    }

}
