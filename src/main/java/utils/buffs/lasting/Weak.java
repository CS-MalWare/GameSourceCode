package utils.buffs.lasting;


import utils.buffs.Buff;

public class Weak extends Buff {

    public Weak(int layer) {
        super("weak", layer, TYPE.DEBUFF, TIME.LASTING, "reduce damage dealt by 25%");
    }

    @Override
    public void func() {
        this.role.multiplyingDealDamage *= 0.75;
    }

    @Override
    public boolean dec() {
        this.layer -= 1;
        if (this.layer<=0) {
            this.role.multiplyingDealDamage /= 0.75;
        }
        return this.layer<=0;
    }

    @Override
    public boolean decBy(int time) {
        this.layer -= time;
        if (this.layer<=0) {
            this.role.multiplyingDealDamage /= 0.75;
        }
        return this.layer<=0;
    }
}
