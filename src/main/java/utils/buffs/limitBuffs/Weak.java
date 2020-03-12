package utils.buffs.limitBuffs;

import character.Role;
import com.jme3.ui.Picture;
import utils.buffs.BuffFunction;
import utils.buffs.LimitBuff;

public class Weak extends LimitBuff implements BuffFunction {

    public Weak(Role role, int duration) {
        super("weak", "decrease the damage dealt by 25%", new Picture(), role, duration);
    }

    @Override
    public void getFunc() {
//        this.getRole().setMultiplyingDealDamage(0.75);

    }

    @Override
    public void triggerFunc() {
        if (this.getDuration() > 1) {
            this.decDuration();
        } else if (this.getDuration() == 1) {
//            this.getRole().setMultiplyingDealDamage(1);
            this.decDuration();
        }
    }
}
