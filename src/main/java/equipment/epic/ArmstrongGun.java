package equipment.epic;

import character.MainRole;
import equipment.Equipment;

public class ArmstrongGun extends Equipment {

    public ArmstrongGun() {
        super("Armstrong Gun", "阿姆斯特朗回旋炮", "When playing an attack card, you have a 25% chance to give the target a layer of vulnerable first", EquipmentDegree.EPIC, Opportunity.ATTACK);

    }

    @Override
    public void fun() {
        //TODO
    }
}
