package utils.buffs.limitBuffs;

import character.Role;
import com.jme3.ui.Picture;
import utils.buffs.BuffFunction;
import utils.buffs.LimitBuff;

public class Bleeding extends LimitBuff implements BuffFunction {
    public Bleeding(Role role, int duration) {
        super("bleeding", "get x damage when attacked", new Picture(), role, duration);
    }

    @Override
    public void getFunc() {

    }

    @Override
    public void triggerFunc() {
        if (this.getDuration() > 0) {//层数大于0就触发
            this.getRole().getTrueDamage(this.getDuration());
            this.decDuration();
        }
    }


}
