package equipment.common;

import character.MainRole;
import equipment.Equipment;

public class Battery extends Equipment {

    private int turnCount = 1;//回合计数器
    public Battery() {
        super("battery", "电池", "Every three turns, gain 1 strength in this battle", EquipmentDegree.COMMON, Opportunity.ENDT);
    }

    @Override
    public void fun() {
        if(turnCount%3==0){
            MainRole.getInstance().setStrength(MainRole.getInstance().getStrength()+1);
        }
        turnCount++;
    }

    @Override
    public void resetBuff() {
        turnCount = 1;
    }
}
