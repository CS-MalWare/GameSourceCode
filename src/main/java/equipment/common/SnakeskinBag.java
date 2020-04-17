package equipment.common;

import character.MainRole;
import equipment.Equipment;

public class SnakeskinBag extends Equipment {
    public SnakeskinBag() {
        super("Snakeskin bag","蛇皮袋", "Gain 5 blocks at the beginning of each battle", EquipmentDegree.COMMON, Opportunity.STARTB);
    }

    @Override
    public void fun() {
        MainRole.getInstance().setBlock(MainRole.getInstance().getBlock()+MainRole.getInstance().computeBlock(5));
    }
}
