package card.saber.power;

import card.PowerCard;
import character.MainRole;
import character.Role;
import org.codehaus.groovy.tools.shell.Main;
import utils.buffs.limitBuffs.Excite;

public class ManaBoost extends PowerCard {
    public ManaBoost() {
        super(OCCUPATION.SABER, "魔力激发", 1, RARITY.RARE, "gain 99 excite");
    }

    public ManaBoost(boolean upgraded) {
        super(OCCUPATION.SABER, "魔力激发+", 1, RARITY.RARE, "gain 99 excite, innate");
        this.intrinsic = true;
    }


    @Override
    public boolean upgrade() {
        if (upgraded = true) return false;
        this.setCardName("魔力激发+");
        this.upgraded = true;
        this.setDescription("gain 99 excite, innate");
        return true;
    }


    public boolean use(Role target) {

        MainRole.getInstance().getBuff(new Excite(MainRole.getInstance(), 99));

        return true;
    }
}
