package equipment.epic;

import equipment.Equipment;

public class TheDragonTeethShield extends Equipment {
    public TheDragonTeethShield() {
        super(" The dragon teeth shield", "龙牙盾", "Every time you take damage, get 3 blocks", EquipmentDegree.EPIC, Opportunity.GETD);
    }

    @Override
    public void fun() {
        this.mainRole.setBlock(this.mainRole.getBlock() + this.mainRole.computeBlock(3));
    }
}
