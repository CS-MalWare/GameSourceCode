package card.saber.skill;

import card.AttackCard;
import card.SkillCard;
import character.MainRole;
import character.Role;
import utils.buffs.limitBuffs.Intangible;

public class PrayForLight extends SkillCard {
    public PrayForLight() {
        super(OCCUPATION.SABER, "光之祈祷", 4, RARITY.EPIC, "Heal 20% of my lost HP and deal equal damage to all enemies");
        this.AOE = true;
    }

    public PrayForLight(boolean upgraded) {
        super(OCCUPATION.SABER, "光之祈祷+", 4, RARITY.EPIC, "Heal 25% of my lost HP and deal equal damage to all enemies");
        this.AOE = true;
        this.upgraded = true;
    }


    @Override
    public boolean upgrade() {
        if (this.upgraded) {
            return false;
        } else {
            this.upgraded = true;
            this.setCardName("光之祈祷+");
            this.setDescription("Heal 25% of my lost HP and deal equal damage to all enemies");
        }
        return true;
    }


    @Override
    public boolean use(Role... targets) {
        MainRole mainRole = MainRole.getInstance();
        int lostHP = mainRole.getTotalHP() - mainRole.getHP();
        int num;
        if (!upgraded) num = (int) (lostHP * 0.2);
        else num = (int) (lostHP * 0.25);
        mainRole.treat(lostHP);
        for (Role target : targets)
            target.getDamage(MainRole.getInstance().computeDamage(num, AttackCard.PROPERTY.NONE));
        return true;
    }
}
