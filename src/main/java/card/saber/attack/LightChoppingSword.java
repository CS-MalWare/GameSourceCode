package card.saber.attack;

import card.AttackCard;
import character.MainRole;
import character.Role;
import utils.buffs.limitBuffs.Excite;

public class LightChoppingSword extends AttackCard {
    public LightChoppingSword() {
        super(OCCUPATION.SABER, "光剑斩", 1, RARITY.EPIC, "deal 8 light property damage and gains 1 excite", 8, 1);

    }


    public LightChoppingSword(boolean upgraded) {
        super(OCCUPATION.SABER, "光剑斩+", 1, RARITY.EPIC, "deal 13 light property damage and gains 3 excite", 13, 1);
        this.upgraded = true;
    }

    @Override
    public boolean upgrade() {
        if (upgraded) return false;
        this.setCardName("光剑斩+");
        this.setDamage(13);
        this.upgraded = true;
        this.setDescription("deal 13 light property damage and gains 3 excite");
        return true;
    }

    @Override
    public boolean use(Role target) {
        if (!super.use(target)) {
            return false;
        }
        if (!upgraded) {
            MainRole.getInstance().getBuff(new Excite(MainRole.getInstance(), 1));
        } else {
            MainRole.getInstance().getBuff(new Excite(MainRole.getInstance(), 3));
        }
        return true;
    }


}
