package equipment.common;

import character.MainRole;
import equipment.Equipment;


public class Rind extends Equipment {
    public Rind(MainRole mainRole, EquipmentDegree degree) {
        super("Rind", "Recovers 2 health after each battle", "Equipments/common/瓜皮.png", mainRole, degree, Opportunity.ENDB);
    }

    @Override
    public void fun() {
        this.mainRole.treat(2);
    }
}
