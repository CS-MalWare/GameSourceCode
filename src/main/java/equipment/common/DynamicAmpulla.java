package equipment.common;

import character.MainRole;
import equipment.Equipment;

public class DynamicAmpulla extends Equipment {
    public DynamicAmpulla() {
        super(" Dynamic ampulla", "活力圣瓶", "heal 30% hp when gained", EquipmentDegree.COMMON, Opportunity.GET);
    }

    @Override
    public void fun() {
        MainRole.getInstance().treat((int)(0.3*MainRole.getInstance().getTotalHP()));
    }
}
