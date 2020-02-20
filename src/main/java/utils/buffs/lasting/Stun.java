package utils.buffs.lasting;

import utils.buffs.Buff;

public class Stun extends Buff {
    public Stun(int layer) {
        super("stun", layer, TYPE.DEBUFF, TIME.LASTING, "skip your turn");
    }

    @Override
    public void func() {

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
