package utils.buffs.limitBuffs;

import character.Role;
import com.jme3.ui.Picture;
import utils.buffs.BuffFunction;
import utils.buffs.LimitBuff;

public class Silence extends LimitBuff implements BuffFunction {

    public Silence(Role role, int duration) {
        super("slience", "unable to use skill card", new Picture(), role, duration);
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
