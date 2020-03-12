package utils.buffs.limitBuffs;

import character.Role;
import com.jme3.ui.Picture;
import utils.buffs.BuffFunction;
import utils.buffs.LimitBuff;

public class Stun extends LimitBuff implements BuffFunction {

    public Stun(Role role, int duration) {
        super("stun", "skip this turn", new Picture(), role, duration);
    }

    @Override
    public void getFunc() {
        //TODO
    }

    @Override
    public void triggerFunc() {
        //TODO
    }
}
