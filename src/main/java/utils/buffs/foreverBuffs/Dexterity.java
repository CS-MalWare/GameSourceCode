package utils.buffs.foreverBuffs;

import character.Role;
import com.jme3.ui.Picture;
import utils.buffs.BuffFunction;
import utils.buffs.ForeverBuff;

public class Dexterity extends ForeverBuff implements BuffFunction {
    public Dexterity(String name, String description, Picture buffPicture, Role role, int times) {
        super(name, description, buffPicture, role, times);
        this.fun();
    }

    @Override
    public String getDescription(){
        return "description拼接";
    }
    @Override
    public void fun() {
        this.getRole().incDexterity(this.getTimes());
    }
}
