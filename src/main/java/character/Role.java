package character;

import com.jme3.app.SimpleApplication;
import com.jme3.ui.Picture;
import utils.buffs.Buff;
import utils.buffs.foreverBuffs.Artifact;
import utils.buffs.foreverBuffs.Dodge;
import utils.buffs.limitBuffs.*;

public class Role {
    public enum ROLE {MAINROLE, NPC, ENEMY}

    protected int totalHP; //总血量
    protected int HP; // 当前血量
    protected String src; //模型路径
    protected ROLE role; // 属于什么角色


    protected SimpleApplication app; //主要用于调用各个state中的方法
    //战斗中的实际属性
    protected int block;
    protected int strength;
    protected int dexterity;


//    private boolean unableAttack;
//    private boolean unableSkill;
//    private double multiplyingDealDamage; //造成伤害的倍率
//    private double multiplyingGetDamage; //受到伤害的倍率
//    private double multiplyingGetBlock; //从卡牌中获取格挡值的倍率

    protected Vulnerable vulnerable = new Vulnerable("vulnerable", "increase the damage recieved by 50%", new Picture(), this, 0);
    protected Weak weak = new Weak("weak", "decrease the damage dealt by 50%", new Picture(), this, 0);
    protected Stun stun = new Stun("stun", "skip this turn", new Picture(), this, 0);
    protected Slience slience = new Slience("slience", "unable to use skill card", new Picture(), this, 0);
    protected Sheild sheild = new Sheild("sheild", "get x blocks at the end of turn", new Picture(), this, 0);
    protected Posion posion = new Posion("posion", "get x damage at the end of turn", new Picture(), this, 0);
    protected Intangible intangible = new Intangible("intangible", "reduce the damage received by 50%", new Picture(), this, 0);
    protected Excite excite = new Excite("excite", "draw 1 card at the start of turn", new Picture(), this, 0);
    protected Erode erode = new Erode("erode", "reduce the block get from cards by 1/3", new Picture(), this, 0);
    protected Disarm disarm = new Disarm("disarm", "unable to use attack card", new Picture(), this, 0);
    protected Bleeding bleeding = new Bleeding("bleeding", "get x damage when attacked", new Picture(), this, 0);
    protected Artifact artifact = new Artifact("artifact", "become immune to debuffs", new Picture(), this, 0);
    protected Dodge dodge = new Dodge("dodge", "avoid one attack from enemy", new Picture(), this, 0);


    public Role(int HP, String src, ROLE role) {
        this.totalHP = HP;
        this.HP = HP;
        this.src = src;
        this.role = role;
//        this.multiplyingDealDamage = 1;
//        this.multiplyingGetDamage = 1;
//        this.multiplyingGetBlock = 1;
    }

    public String getSrc() {
        return src;
    }

    public void getDamage(int damage) {
        if (this.dodge.getTimes() > 0) {
            this.dodge.decTimes();
            if (this.block >= 1) {
                this.block -= 1;
            } else {
                this.HP -= 1;
                if (this.bleeding.getDuration() > 0)
                    this.bleeding.triggerFunc();
            }
            return;
        }

        if (this.vulnerable.getDuration() > 0) {
            damage = (int) (damage * 1.5);
        }

        if (this.intangible.getDuration() > 0) {
            damage = (int) (damage * 0.5);
        }
        if (this.block >= damage) {
            this.block -= damage;
        } else {
            this.HP -= damage - block;
            if (this.bleeding.getDuration() > 0) {
                this.bleeding.triggerFunc();
            }
        }
    }


    public void endTurn() {
        this.sheild.triggerFunc();
        this.posion.triggerFunc();
        this.bleeding.decDuration();
        this.vulnerable.triggerFunc();
        this.intangible.triggerFunc();
        this.disarm.triggerFunc();
        this.slience.triggerFunc();
        this.stun.triggerFunc();
        this.excite.triggerFunc();
        this.erode.triggerFunc();

    }

    public void bindApp(SimpleApplication app) {
        this.app = app;
    }

    public void getBuff(Buff buff) {
        if (buff instanceof Sheild) {
            this.sheild.incDuration(((Sheild) buff).getDuration());
            return;
        } else if (buff instanceof Excite) {
            this.excite.incDuration(((Excite) buff).getDuration());
            return;
        } else if (buff instanceof Artifact) {
            this.artifact.incTimes(((Artifact) buff).getTimes());
            return;
        } else if (buff instanceof Dodge) {
            this.dodge.incTimes(((Dodge) buff).getTimes());
            return;
        }


        if (this.artifact.getTimes() > 0) {
            this.artifact.triggerFunc();
        } else {
            if (buff instanceof Vulnerable) {
                this.vulnerable.incDuration(((Vulnerable) buff).getDuration());
            } else if (buff instanceof Weak) {
                this.weak.incDuration(((Weak) buff).getDuration());
            } else if (buff instanceof Stun) {
                this.stun.incDuration(((Stun) buff).getDuration());
            } else if (buff instanceof Slience) {
                this.slience.incDuration(((Slience) buff).getDuration());
            } else if (buff instanceof Posion) {
                this.posion.incDuration(((Posion) buff).getDuration());
            } else if (buff instanceof Intangible) {
                this.intangible.incDuration(((Intangible) buff).getDuration());
            } else if (buff instanceof Erode) {
                this.erode.incDuration(((Erode) buff).getDuration());
            } else if (buff instanceof Disarm) {
                this.disarm.incDuration(((Disarm) buff).getDuration());
            } else if (buff instanceof Bleeding) {
                this.bleeding.incDuration(((Bleeding) buff).getDuration());
            }

        }

    }

    public int getTotalHP() {
        return totalHP;
    }

    public void setTotalHP(int totalHP) {
        this.totalHP = totalHP;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public int getBlock() {
        return block;
    }

    public void gainBlock(int num) {
        this.block += num;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }


//    public double getMultiplyingDealDamage() {
//        return multiplyingDealDamage;
//    }
//
//    public void setMultiplyingDealDamage(double multiplyingDealDamage) {
//        this.multiplyingDealDamage = multiplyingDealDamage;
//    }
//
//    public double getMultiplyingGetDamage() {
//        return multiplyingGetDamage;
//    }
//
//    public void setMultiplyingGetDamage(double multiplyingGetDamage) {
//        this.multiplyingGetDamage = multiplyingGetDamage;
//    }
//
//    public double getMultiplyingGetBlock() {
//        return multiplyingGetBlock;
//    }
//
//    public void setMultiplyingGetBlock(double multiplyingGetBlock) {
//        this.multiplyingGetBlock = multiplyingGetBlock;
//    }

    public void getTrueDamage(int damage) {
        this.HP -= damage;
    }

    public ROLE getRole() {
        return role;
    }

//    public boolean getUnableAttack() {
//        return this.unableAttack;
//    }
//
//    public void setUnableAttack(boolean unableAttack) {
//        this.unableAttack = unableAttack;
//    }
//
//    public boolean getUnableSkill() {
//        return this.unableSkill;
//    }
//
//    public void setUnableSkill(boolean unableSkill) {
//        this.unableSkill = unableSkill;
//    }

}
