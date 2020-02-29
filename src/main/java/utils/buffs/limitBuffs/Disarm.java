package utils.buffs.limitBuffs;

import character.Role;
import com.jme3.ui.Picture;
import utils.buffs.BuffFunction;
import utils.buffs.LimitBuff;

public class Disarm extends LimitBuff implements BuffFunction {
    public Disarm(String name, String description, Picture buffPicture, Role role, int duration) {
        super(name, description, buffPicture, role, duration);
    }

    @Override
    public void fun() {
        if(this.getDuration()>0) {//如果层数大于0就继续缴械
            this.getRole().setUnableAttack(true);
            this.decDuration();
        }
        else
            this.getRole().setUnableAttack(false);
    }
}
