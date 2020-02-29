package utils.buffs.limitBuffs;

import character.Role;
import com.jme3.ui.Picture;
import utils.buffs.BuffFunction;
import utils.buffs.LimitBuff;

public class Bleeding extends LimitBuff implements BuffFunction {
    public Bleeding(String name, String description, Picture buffPicture, Role role, int duration) {
        super(name, description, buffPicture, role, duration);
    }

    @Override
    public void fun() {
        if(this.getDuration()>0) {//层数大于0就触发
            this.getRole().getTrueDamage(this.getDuration());
            this.decDuration();
        }
    }


}