package utils.buffs.forever;

import character.Role;
import utils.buffs.Buff;

public class Dodge extends Buff {
    public Dodge(int layer) {
        super("dodge", layer, TYPE.BUFF, TIME.FOREVER, "turn the next damage taken into 1");
    }

    @Override
    public void func() {
        this.role.incDodge(1);
    }


}
