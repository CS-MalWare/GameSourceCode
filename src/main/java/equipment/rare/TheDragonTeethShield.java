package equipment.rare;

import character.MainRole;
import equipment.Equipment;

public class TheDragonTeethShield extends Equipment {
    public TheDragonTeethShield() {
        super(" The dragon teeth shield", "龙牙盾", "Every time you take damage, get 3 blocks", EquipmentDegree.RARE, Opportunity.USE);
    }

    @Override
    public void fun() {
        MainRole.getInstance().setBlock(MainRole.getInstance().getBlock() + MainRole.getInstance().computeBlock(3));
    }
}
