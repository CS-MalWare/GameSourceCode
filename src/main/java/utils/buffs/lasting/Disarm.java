package utils.buffs.lasting;

import utils.buffs.Buff;

public class Disarm extends Buff {

    public Disarm(int layer) {
        super("disarm", layer, TYPE.DEBUFF, TIME.LASTING, "disable the target to use attack cards");
    }

    @Override
    public void func() {
        this.role.setUnableAttack(true);
    }

    @Override
    public boolean dec() {
        this.layer -= 1;
        if (this.layer<=0) {
            this.role.setUnableAttack(false);
        }
        return this.layer<=0;
    }
    @Override
    public boolean decBy(int time) {
        this.layer -= time;
        if (this.layer<=0) {
            this.role.setUnableAttack(false);
        }
        return this.layer<=0;
    }
}
