package utils.buffs.foreverBuffs;

import character.Role;
import com.jme3.ui.Picture;
import utils.buffs.BuffFunction;
import utils.buffs.ForeverBuff;

public class Dodge extends ForeverBuff implements BuffFunction {
    public Dodge(Role role, int times) {
        super("dodge", "avoid one attack from enemy", new Picture(), role, times);
        this.getFunc();
    }

    @Override
    public String getDescription() {
        return "description拼接";
    }

    @Override
    public void getFunc() {
//        this.getRole().setDodge(this.getRole().getDodge() + this.getTimes());
    }

    @Override
    public void triggerFunc() {

    }
}
