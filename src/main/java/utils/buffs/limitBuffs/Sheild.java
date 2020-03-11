package utils.buffs.limitBuffs;

import character.Role;
import com.jme3.ui.Picture;
import utils.buffs.BuffFunction;
import utils.buffs.LimitBuff;

public class Sheild extends LimitBuff implements BuffFunction {

    public Sheild(String name, String description, Picture buffPicture, Role role, int duration) {
        super(name, description, buffPicture, role, duration);
    }

    @Override
    public void getFunc() {

    }

    @Override
    public void triggerFunc() {
        if (this.getDuration() > 0) {
            this.getRole().gainBlock(this.getDuration());
            this.decDuration();
        }
    }
}
