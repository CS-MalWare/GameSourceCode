package utils.buffs.limitBuffs;

import character.Role;
import com.jme3.ui.Picture;
import utils.buffs.BuffFunction;
import utils.buffs.LimitBuff;

public class Slience extends LimitBuff implements BuffFunction {

    public Slience(String name, String description, Picture buffPicture, Role role, int duration) {
        super(name, description, buffPicture, role, duration);
    }

    @Override
    public void getFunc() {
//        this.getRole().setUnableSkill(true);
    }

    @Override
    public void triggerFunc() {
        if (this.getDuration() > 1) {
            this.decDuration();
        } else if (this.getDuration() == 1) {
//            this.getRole().setUnableSkill(true);
            this.decDuration();
        }
    }
}
