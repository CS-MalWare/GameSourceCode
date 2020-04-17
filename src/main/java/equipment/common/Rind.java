package equipment.common;

import character.MainRole;
import equipment.Equipment;


public class Rind extends Equipment {
    public Rind() {
        super("Rind", "瓜皮", "Recovers 2 health after each battle", EquipmentDegree.COMMON, Opportunity.ENDB);
    }

    @Override
    public void fun() {
        MainRole.getInstance().treat(2);
    }
}
