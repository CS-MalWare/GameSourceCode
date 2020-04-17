package equipment.common;

import character.Enemy;
import equipment.Equipment;

public class CurseBook extends Equipment {
    private int timeCount = 1;
    public CurseBook() {
        super("Curse book", "诅咒魔法书", "The next three battles against ordinary monsters will turn all enemies'HP into 1 at the beginning of the battle", EquipmentDegree.COMMON, Opportunity.STARTB);
    }

    @Override
    public void fun() {
        if(timeCount<=3){
            for(Enemy enemy:enemies ){
                enemy.setHP(1);
            }
            timeCount++;
        }
    }
}
