package equipment.epic;

import appState.EnemyState;
import character.Enemy;
import character.MainRole;
import equipment.Equipment;

public class TheFlagOfTheFFRegiment extends Equipment {

    public TheFlagOfTheFFRegiment() {
        super("The flag of the FF regiment", "FF团的大旗", "When facing 2 or more enemies, the damage caused by the player is increased by 20%", EquipmentDegree.EPIC, Opportunity.ATTACK);

    }

    @Override
    public void fun() {
        if (EnemyState.getInstance().getEnemies().size() > 1) {
            MainRole.getInstance().setMultiplyingDealDamage(1.25);
        } else {
            MainRole.getInstance().setMultiplyingDealDamage(1);

        }
    }
}
