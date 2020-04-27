package equipment.epic;

import character.MainRole;
import equipment.Equipment;

public class WomenClothesOfCYX extends Equipment {
    boolean work;

    public WomenClothesOfCYX() {
        super("Women clothes of CYX", "陈宇轩的女装", "When receiving fatal damage, restore HP to 20% and discard this equipment", EquipmentDegree.EPIC, Opportunity.DEAD);
        work = true;
    }

    @Override
    public void fun() {
        if (work) {
            MainRole.getInstance().setHP((int) (MainRole.getInstance().getTotalHP() * 0.2));
            work = false;
        }
    }
}
