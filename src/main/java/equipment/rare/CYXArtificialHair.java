package equipment.rare;

import equipment.Equipment;
import utils.buffs.limitBuffs.Stun;

public class CYXArtificialHair extends Equipment {
    public CYXArtificialHair() {
        super("CYX's artificial hair", "陈钰炫的假发", "When I get damage, there is 10% of stunning a random enemy", EquipmentDegree.RARE, Opportunity.GETD);
    }

    @Override
    public void fun() {
        if(Math.random()<0.1){
            int random = (int)(Math.random()*enemies.size());
            enemies.get(random).getBuff(new Stun(enemies.get(random),1));
        }
    }
}
