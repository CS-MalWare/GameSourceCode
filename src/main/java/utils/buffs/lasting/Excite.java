package utils.buffs.lasting;

import character.MainRole;
import character.Role;
import utils.buffs.Buff;

public class Excite extends Buff {
    public Excite(int layer) {
        super("excite", layer, TYPE.BUFF, TIME.LASTING, "draw 1 more card at the start of turn");
    }

    @Override
    public void func(){
        if (this.role.getRole() == Role.ROLE.MAINROLE) {
            ((MainRole) this.role).incDraw(1);
        }
    }

    @Override
    public boolean dec() {
        this.layer -= 1;
        if (this.layer<=0 && this.role.getRole() == Role.ROLE.MAINROLE) {
            ((MainRole) this.role).decDraw(1);
        }
        return this.layer<=0;
    }

    @Override
    public boolean decBy(int time) {
        this.layer -= time;
        if (this.layer<=0 && this.role.getRole() == Role.ROLE.MAINROLE) {
            ((MainRole) this.role).decDraw(1);
        }
        return this.layer<=0;
    }
}
