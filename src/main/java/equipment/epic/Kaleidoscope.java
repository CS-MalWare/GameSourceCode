package equipment.epic;

import character.MainRole;
import equipment.Equipment;

public class Kaleidoscope extends Equipment {


    public Kaleidoscope() {
        super("Kaleidoscope", "万花筒", "The cost of all drawn cards fluctuates randomly between 0 and 4", EquipmentDegree.EPIC, Opportunity.STARTB);

    }

    @Override
    public void fun() {
    }
}
