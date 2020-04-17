package equipment.senior;

import character.Enemy;
import character.MainRole;
import equipment.Equipment;

import java.util.ArrayList;

public class TheDragonTeethShield extends Equipment {
    public TheDragonTeethShield( MainRole mainRole, EquipmentDegree degree) {
        super(" The dragon teeth shield", "Every time you take damage, get 3 blocks", "Equipments/senior/龙牙盾.png", mainRole, degree, Opportunity.GETD);
    }

    @Override
    public void fun() {
        this.mainRole.setBlock(this.mainRole.getBlock()+this.mainRole.computeBlock(3));
    }
}
