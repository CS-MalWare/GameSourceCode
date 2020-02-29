package utils.buffs.limitBuffs;

import character.Role;
import com.jme3.ui.Picture;
import utils.buffs.BuffFunction;
import utils.buffs.LimitBuff;

public class Vulnerable extends LimitBuff implements BuffFunction {
    public Vulnerable(String name, String description, Picture buffPicture, Role role, int duration) {
        super(name, description, buffPicture, role, duration);
    }

    @Override
    public void fun() {
        if (this.getDuration() > 0) {
            this.getRole().multiplyingGetDamage = 1.5;
            this.decDuration();
        }
        else{
            this.getRole().multiplyingGetDamage = 1;
        }
    }
}
