package equipment.common;

import character.MainRole;
import equipment.Equipment;

public class BrokenRolex extends Equipment {
    public BrokenRolex() {
        super("Broken rolex", "破损的劳力士", "After each battle, you lose 10 gold", EquipmentDegree.COMMON, Opportunity.ENDB);
    }

    @Override
    public void fun() {
        MainRole.getInstance().setGold(MainRole.getInstance().getGold()-15);
    }
}
