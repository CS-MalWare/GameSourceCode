package utils.buffs.lasting;

import utils.buffs.Buff;

public class Bleeding extends Buff {
    public Bleeding(int layer) {
        super("bleeding", layer, TYPE.DEBUFF, TIME.LASTING, "draw 1 more card at the start of turn");
    }

    @Override
    public void func() {
        this.role.getTrueDamage(this.layer);
        this.dec();
    }

    @Override
    public boolean dec() {
        return super.dec();
    }

    @Override
    public boolean decBy(int time) {
        return super.decBy(time);
    }



}
