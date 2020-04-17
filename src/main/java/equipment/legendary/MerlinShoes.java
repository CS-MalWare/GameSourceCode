package equipment.legendary;

import character.MainRole;
import equipment.Equipment;

public class MerlinShoes extends Equipment {

    public MerlinShoes() {
        super("Merlin's shoes", "梅林之靴", "Increase the maximum MP by 1.", EquipmentDegree.LEGENDARY, Opportunity.GET);

    }

    @Override
    public void fun() {
        MainRole.getInstance().setMP_max(MainRole.getInstance().getMP_max() + 1);
    }
}
