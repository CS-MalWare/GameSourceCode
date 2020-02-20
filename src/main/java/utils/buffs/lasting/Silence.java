package utils.buffs.lasting;

import utils.buffs.Buff;

public class Silence extends Buff {

    public Silence(int layer) {
        super("silence", layer, TYPE.DEBUFF, TIME.LASTING, "disable the target to use skill cards");
    }

    @Override
    public void func() {
        this.role.setUnableSkill(true);
    }


    @Override
    public boolean dec() {
        this.layer -= 1;
        if (this.layer <= 0) {
            this.role.setUnableSkill(false);
        }
        return this.layer <= 0;
    }

    @Override
    public boolean decBy(int time) {
        this.layer -= time;
        if (this.layer <= 0) {
            this.role.setUnableSkill(false);
        }
        return this.layer <= 0;
    }
}
