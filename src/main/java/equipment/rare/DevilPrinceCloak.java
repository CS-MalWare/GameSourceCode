package equipment.rare;

import character.MainRole;
import equipment.Equipment;

public class DevilPrinceCloak extends Equipment {
    public DevilPrinceCloak() {
        super("devil prince's cloak", "鬼王披风", "every attack card played restores 1 health",EquipmentDegree.RARE, Opportunity.ATTACK);
    }

    @Override
    public void fun() {
        MainRole.getInstance().treat(1);
    }
}
