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
    public void getFunc() {
//        this.getRole().setUnableAttack(true);
    }

    @Override
    public void triggerFunc() {
        if (this.getDuration() > 1) {//如果层数大于0就继续缴械
            this.decDuration();
        } else if (this.getDuration() == 1) {
//            this.getRole().setUnableAttack(false);
            this.decDuration();
        }
    }
}
