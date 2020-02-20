package utils.buffs.lasting;


import utils.buffs.Buff;

public class Erode extends Buff {

    public Erode(int layer) {
        super("erode", layer, TYPE.DEBUFF, TIME.LASTING, "reduce block you get from card by 1/3");
    }

    @Override
    public void func() {
        this.role.multiplyingGetBlock *= 0.66;
    }

    @Override
    public boolean dec() {
        this.layer -= 1;
        if (this.layer<=0) {
            this.role.multiplyingGetBlock /=0.66;
        }
        return this.layer<=0;
    }
    @Override
    public boolean decBy(int time) {
        this.layer -= time;
        if (this.layer<=0) {
            this.role.multiplyingGetBlock /=0.66;
        }
        return this.layer<=0;
    }
}
