package utils.buffs.lasting;

import character.Role;
import utils.buffs.Buff;

public class Vulnerable extends Buff {

    public Vulnerable(int layer) {
        super("vulnerable", layer, TYPE.DEBUFF, TIME.LASTING, "increase damage taken by 50%");
    }

    @Override
    public void func() {
        this.role.multiplyingGetDamage *= 1.5;
    }

    @Override
    public boolean dec() {
        this.layer -= 1;
        if (this.layer<=0) {
            this.role.multiplyingGetDamage /=1.5;
        }
        return this.layer<=0;
    }

    @Override
    public boolean decBy(int time) {
        this.layer -= time;
        if (this.layer<=0) {
            this.role.multiplyingGetDamage /=1.5;
        }
        return this.layer<=0;
    }
}
