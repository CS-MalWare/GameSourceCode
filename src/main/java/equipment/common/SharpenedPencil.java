package equipment.common;

import equipment.Equipment;

public class SharpenedPencil extends Equipment {
    private int attCount = 0;
    public SharpenedPencil() {
        super("Sharpened Pencil", "尖铅笔", "The 10th attack card played is played twice", EquipmentDegree.COMMON, Opportunity.ATTACK);
    }

    @Override
    public void fun() {
        if(attCount%10==0){
            //TODO 打出这个卡两次
        }
        attCount++;
    }
}
