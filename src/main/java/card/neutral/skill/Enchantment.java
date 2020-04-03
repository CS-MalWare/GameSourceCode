package card.neutral.skill;

import card.SkillCard;
import character.MainRole;
import character.Role;

public class Enchantment extends SkillCard {
    public Enchantment() {
        super(OCCUPATION.NEUTRAL, "魔化", 3, RARITY.LEGENDARY, "upgrade all skill cards in this battle, exhaust");
        this.exhaust = true;

    }

    public Enchantment(boolean upgraded) {
        super(OCCUPATION.NEUTRAL, "魔化+", 3, RARITY.LEGENDARY, "upgrade all skill cards in this battle, gain 3 MP, exhaust");
        this.exhaust = true;
    }


    @Override
    public boolean upgrade() {
        if (this.upgraded) {
            return false;
        } else {
            this.upgraded = true;
            this.setCardName("魔化+");
            this.setDescription("upgrade all skill cards in this battle, gain 3 MP, exhaust");
            //TODO
        }
        return true;
    }


    @Override
    public boolean use(Role target) {
        //TODO 升级所有攻击卡
        if (upgraded) {
            MainRole.getInstance().getMP(3);
        }
        return true;
    }

}
