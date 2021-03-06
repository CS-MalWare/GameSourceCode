package utils.buffs.limitBuffs;

import character.Role;
import com.jme3.ui.Picture;
import utils.buffs.BuffFunction;
import utils.buffs.LimitBuff;

public class Vulnerable extends LimitBuff implements BuffFunction {
    public Vulnerable(Role role, int duration) {
        super("vulnerable", "increase the damage recieved by 50%", new Picture(), role, duration);
    }

    @Override
    public void getFunc() {
//        this.getRole().setMultiplyingGetDamage (1.5);
    }

    @Override
    public void triggerFunc() {
        if (this.getDuration() > 1) {
            this.decDuration();
        } else if (this.getDuration() == 1) {
//            this.getRole().setMultiplyingGetDamage(1);
            this.decDuration();
        }
    }
}
