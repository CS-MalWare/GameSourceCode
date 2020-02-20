package utils.buffs.forever;

import utils.buffs.Buff;

public class Artifact extends Buff {
    public Artifact(String name, int layer, TYPE type, TIME time, String description) {
        super("artifact", layer, TYPE.BUFF, TIME.FOREVER, "counter the next debuff you get");
    }

    @Override
    public void func() {
        this.role.incArtifact(this.layer);
    }

}
