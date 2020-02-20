package utils.buffs.lasting;

import character.MainRole;
import character.Role;
import utils.buffs.Buff;

public class Intangible extends Buff {

    public Intangible(int layer) {
        super("intangible", layer, TYPE.BUFF, TIME.LASTING, "reduce damage taken by 50%");
    }

    @Override
    public void func() {
        this.role.multiplyingGetDamage *= 0.5;
    }

    @Override
    public boolean dec() {
        this.layer -= 1;
        if (this.layer<=0) {
            this.role.multiplyingGetDamage *=2;
        }
        return this.layer<=0;
    }

    @Override
    public boolean decBy(int time) {
        this.layer -= time;
        if (this.layer<=0) {
            this.role.multiplyingGetDamage *=2;
        }
        return this.layer<=0;
    }
}
