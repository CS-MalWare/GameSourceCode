package utils.buffs.foreverBuffs;

import character.Role;
import com.jme3.ui.Picture;
import utils.buffs.BuffFunction;
import utils.buffs.ForeverBuff;

public class Strength extends ForeverBuff implements BuffFunction {
    public Strength(String name, String description, Picture buffPicture, Role role, int times) {
        super(name, description, buffPicture, role, times);
        this.getFunc();
    }

    @Override
    public String getDescription() {
        return "description拼接";
    }

    @Override
    public void getFunc() {
        this.getRole().setStrength(this.getRole().getStrength() + this.getTimes());
    }

    @Override
    public void triggerFunc() {

    }
}
