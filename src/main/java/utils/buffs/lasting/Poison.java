package utils.buffs.lasting;

import character.Role;
import utils.buffs.Buff;

public class Poison extends Buff {
    public Poison(int layer) {
        super("poison", layer, TYPE.DEBUFF, TIME.LASTING, "take x damage at the end of turn");
    }

    @Override
    public void func() {
    }

    @Override
    public boolean dec() {
        this.role.getTrueDamage(this.layer);
        return super.dec();
    }

    @Override
    public boolean decBy(int time) {
        this.role.getTrueDamage(this.layer);
        return super.decBy(time);
    }
}
