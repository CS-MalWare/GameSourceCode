package equipment.common;

import character.MainRole;
import equipment.Equipment;

public class KnightGloves extends Equipment {
    private boolean firstTurn = true;
    public KnightGloves() {
        super("Knight gloves", "骑士手套", "Draw 2 extra cards in the first turn of a battle", EquipmentDegree.COMMON, Opportunity.STARTT);
    }

    @Override
    public void fun() {
        if(firstTurn){
            MainRole.getInstance().drawCards(2);
            firstTurn = false;
        }
    }

    @Override
    public void resetBuff() {
        firstTurn = true;
    }
}
