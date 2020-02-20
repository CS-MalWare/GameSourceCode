package utils.buffs;

import character.Role;


public class Buff {
    public enum TYPE {BUFF, DEBUFF}

    public enum TIME {LASTING,FOREVER}

    private String name;
    protected int layer;
    private TYPE type;
    private TIME time;
    protected Role role;
    private String description;


    public Buff(String name, int layer, TYPE type, TIME time, String description) {
        this.name = name;
        this.layer = layer;
        this.type = type;
        this.time = time;
        this.description = description;
    }

    public void func(){

    }


    public void bind(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public String getDescription() {
        return description;
    }

    public int getLayer() {
        return layer;
    }

    public TYPE getType() {
        return type;
    }

    public TIME getTime() {
        return time;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public boolean dec() {
        this.layer -= 1;
        return this.layer <= 0;
    }

    public boolean decBy(int time) {
        this.layer -= time;
        return this.layer <= 0;
    }

    public void inc() {
        this.layer += 1;
    }

    public void incBy(int time) {
        this.layer += time;
    }

    public void clean(){
        this.layer =0;
    }

}
