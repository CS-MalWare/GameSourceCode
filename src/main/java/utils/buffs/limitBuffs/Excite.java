package utils.buffs.limitBuffs;

import character.MainRole;
import character.Role;
import com.jme3.ui.Picture;
import utils.buffs.BuffFunction;
import utils.buffs.LimitBuff;

public class Excite extends LimitBuff implements BuffFunction {
    private int currentDraw;

    public Excite(String name, String description, Picture buffPicture, Role role, int duration) {
        super(name, description, buffPicture, role, duration);
//        this.currentDraw = ((MainRole) role).getDraw();
    }

    @Override
    public void getFunc() {
        if (this.getRole().getRole() == Role.ROLE.MAINROLE) {
//            ((MainRole) this.getRole()).setDraw(currentDraw + 1);
            //这里逻辑如果按照buff添加顺序是可以的，因为后面的抽牌增加会在这个已经增加的基础上再次刷新这个属性
            //但是如果效果消失会有bug，稍后再调整
        }


    }

    @Override
    public void triggerFunc() {
        if (this.getDuration() > 1) {
            this.decDuration();
        } else if (this.getDuration() == 1) {
//            if (this.getRole().getRole() == Role.ROLE.MAINROLE) {
//                ((MainRole) this.getRole()).decDraw(1);
//            }
            this.decDuration();
        }
    }
}
