package equipment.legendary;

import appState.EnemyState;
import character.Enemy;
import character.MainRole;
import equipment.Equipment;

public class BalancedLibra extends Equipment {
    private boolean work;

    public BalancedLibra() {
        super("Balanced Libra", "平衡的天秤", "Increase the maximum MP by 1. All enemies gain two strength at the beginning of the game", EquipmentDegree.LEGENDARY, Opportunity.STARTB);
        work = true;
    }

    @Override
    public void fun() {
        if (work) {
            MainRole.getInstance().setMP_max(MainRole.getInstance().getMP_max() + 1);
            work = false;
        }

        for (Enemy enemy : EnemyState.getInstance().getEnemies()) {
            enemy.setStrength(enemy.getStrength() + 2);
        }
    }
}
