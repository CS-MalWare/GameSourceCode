package equipment.common;

import equipment.Equipment;
import utils.buffs.limitBuffs.Poison;

public class PoisonPill extends Equipment {
    public PoisonPill() {
        super("Poison pill","毒药丸"," endow a random enemy with 4 levels of poison at the beginning of battle", EquipmentDegree.COMMON, Opportunity.STARTB);
    }

    @Override
    public void fun() {
        int size = enemies.size();
        int random = (int)(Math.random()*size);
        enemies.get(random).getBuff(new Poison(enemies.get(random),4));
    }
}
