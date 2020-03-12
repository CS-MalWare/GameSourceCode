package utils.buffs.limitBuffs;

import character.Role;
import com.jme3.ui.Picture;
import utils.buffs.BuffFunction;
import utils.buffs.LimitBuff;

public class Posion extends LimitBuff implements BuffFunction {


    public Posion(Role role, int duration) {
        super("posion", "get x damage at the end of turn", new Picture(), role, duration);
    }

    @Override
    public void getFunc() {


    }

    @Override
    public void triggerFunc() {
        if (this.getDuration() > 0) {
            this.getRole().getTrueDamage(this.getDuration());
            this.decDuration();
        }
    }
}
