package equipment.common;

import character.Enemy;
import equipment.Equipment;

public class BiologicalSample extends Equipment {
    public BiologicalSample() {
        super("biological sample", "生物标本", "monster enemy's hp is reduced by 20%", EquipmentDegree.COMMON, Opportunity.STARTB);
    }

    @Override
    public void fun() {
        for (Enemy enemy : enemies) {
            enemy.setHP((int) (0.8 * enemy.getTotalHP()));
        }
    }
}
