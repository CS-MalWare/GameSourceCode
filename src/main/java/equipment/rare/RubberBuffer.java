package equipment.rare;

import character.MainRole;
import equipment.Equipment;

public class RubberBuffer extends Equipment {
    private boolean firstInjured = true;
    public RubberBuffer() {
        super("rubber buffer", "橡皮缓冲", "Heal 12 HP when get damage in this battle at the first time", EquipmentDegree.RARE, Opportunity.GETD);
    }

    @Override
    public void fun() {
        if(firstInjured){
            MainRole.getInstance().treat(12);
            firstInjured = false;
        }
    }

    @Override
    public void resetBuff() {
        firstInjured = true;
    }
}
