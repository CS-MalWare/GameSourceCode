package equipment.rare;

import character.MainRole;
import equipment.Equipment;

public class ComboStar extends Equipment {
    private int useCount = 1;
    public ComboStar() {
        super("Combo star", "连击星", "In each turn, when a third card is played, a card is drawn", EquipmentDegree.RARE, Opportunity.USE);
    }

    @Override
    public void fun() {
        if(useCount==3){
            MainRole.getInstance().drawCards(1);
        }
        useCount++;
    }

    @Override
    public void resetBuff() {
        useCount = 1;
    }
}
