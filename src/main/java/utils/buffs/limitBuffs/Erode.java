package utils.buffs.limitBuffs;

import character.Role;
import com.jme3.ui.Picture;
import utils.buffs.BuffFunction;
import utils.buffs.LimitBuff;

public class Erode extends LimitBuff implements BuffFunction {
    public Erode(Role role, int duration) {
        super("erode", "reduce the block get from cards by 1/3", new Picture(), role, duration);
    }

    @Override
    public void getFunc() {
//        this.getRole().setMultiplyingGetBlock(0.66);
    }

    @Override
    public void triggerFunc() {
        if (this.getDuration() > 1) {
            this.decDuration();
        } else if (this.getDuration() == 1) {
//            this.getRole().setMultiplyingGetBlock(1);
            this.decDuration();
        }
    }
}
