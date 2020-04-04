package card.saber.attack;

import card.AttackCard;
import character.MainRole;
import character.Role;

public class WoudedStrike extends AttackCard {
    public WoudedStrike() {
        super(OCCUPATION.SABER, "负伤打击", 4, RARITY.COMMON, "deal 10 damage plus 50% of lost HP", 10, 1);

    }


    public WoudedStrike(boolean upgraded) {
        super(OCCUPATION.SABER, "负伤打击+", 4, RARITY.COMMON, "deal 18 damage plus 50% of lost HP", 18, 1);
        this.upgraded = true;
    }

    @Override
    public boolean upgrade() {
        if (upgraded = true) return false;
        this.setCardName("奋力打击+");
        this.setDamage(18);
        this.upgraded = true;
        this.setDescription("deal 18 damage plus 50% of lost HP");
        return true;
    }

    @Override
    public boolean use(Role target) {
        if (!super.use(target)) {
            return false;
        }
        MainRole mainRole = MainRole.getInstance();
        int lostHP = mainRole.getTotalHP() - mainRole.getHP();
        target.getDamage(mainRole.computeDamage(lostHP));
        return true;
    }


}
