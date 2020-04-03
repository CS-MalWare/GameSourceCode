package utils.buffs.foreverBuffs;

import character.Role;
import com.jme3.ui.Picture;
import utils.buffs.BuffFunction;
import utils.buffs.ForeverBuff;

public class Dexterity extends ForeverBuff implements BuffFunction {
    public Dexterity(Role target, int times) {
        super("dexterity", "your block gained from card increase by x", new Picture(), target, times);
        this.getFunc();
    }

    @Override
    public String getDescription() {
        return "your block gained from card increase by x";
    }

    @Override
    public void getFunc() {
        this.getRole().setDexterity(this.getRole().getDexterity() + this.getTimes());
    }

    @Override
    public void triggerFunc() {

    }
}
