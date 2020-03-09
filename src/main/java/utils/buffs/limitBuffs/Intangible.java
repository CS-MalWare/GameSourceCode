package utils.buffs.limitBuffs;

import character.Role;
import com.jme3.ui.Picture;
import utils.buffs.BuffFunction;
import utils.buffs.LimitBuff;

public class Intangible extends LimitBuff implements BuffFunction {
    public Intangible(String name, String description, Picture buffPicture, Role role, int duration) {
        super(name, description, buffPicture, role, duration);
    }

    @Override
    public void fun() {
        if(this.getDuration()>0){
            this.getRole().setMultiplyingGetDamage(0.5);
            this.decDuration();
        }
        else{
            this.getRole().setMultiplyingGetDamage(1);
        }
    }
}
