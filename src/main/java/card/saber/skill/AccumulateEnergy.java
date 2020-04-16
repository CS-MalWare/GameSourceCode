package card.saber.skill;

import card.SkillCard;
import character.MainRole;
import character.Role;
import org.codehaus.groovy.tools.shell.Main;

public class AccumulateEnergy extends SkillCard {
    public AccumulateEnergy() {
        super(OCCUPATION.SABER, "蓄势", 0, RARITY.COMMON, "Gain 2 strength in this turn");

    }

    public AccumulateEnergy(boolean upgraded) {
        super(OCCUPATION.SABER, "蓄势+", 0, RARITY.COMMON, "Gain 3 strength in this turn");
        this.upgraded = true;
    }


    @Override
    public boolean upgrade() {
        if (this.upgraded) {
            return false;
        } else {
            this.upgraded = true;
            this.setCardName("蓄势+");
            this.setDescription("Gain 3 strength in this turn");
        }
        return true;
    }


    @Override
    public boolean use(Role target) {
        if (!upgraded)
            MainRole.getInstance().setStrength(MainRole.getInstance().getStrength() + 2);
        else
            MainRole.getInstance().setStrength(MainRole.getInstance().getStrength() + 3);

        MainRole.getInstance().cardEffects.add(this.getCardName());

        return true;
    }

}
