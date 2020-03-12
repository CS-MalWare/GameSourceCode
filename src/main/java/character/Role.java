package character;

import com.jme3.app.SimpleApplication;
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


    // 以下三条可以用于逐渐增加难度使用

    private double multiplyingDealDamage; //造成伤害的倍率
    private double multiplyingGetDamage; //受到伤害的倍率
    private double multiplyingGetBlock; //从卡牌中获取格挡值的倍率


    protected Vulnerable vulnerable;
    protected Weak weak;
    protected Stun stun;
    protected Silence silence;
    protected Sheild sheild;
    protected Posion posion;
    protected Intangible intangible;
    protected Excite excite;
    protected Erode erode;
    protected Disarm disarm;
    protected Bleeding bleeding;
    protected Artifact artifact;
    protected Dodge dodge;


    public Role(int HP, String src, ROLE role) {
        this.totalHP = HP;
        this.HP = HP;
        this.src = src;
        this.role = role;

        Vulnerable vulnerable = new Vulnerable(this, 0);
        Weak weak = new Weak(this, 0);
        Stun stun = new Stun(this, 0);
        Silence silence = new Silence(this, 0);
        Sheild sheild = new Sheild(this, 0);
        Posion posion = new Posion(this, 0);
        Intangible intangible = new Intangible(this, 0);
        Excite excite = new Excite(this, 0);
        Erode erode = new Erode(this, 0);
        Disarm disarm = new Disarm(this, 0);
        Bleeding bleeding = new Bleeding(this, 0);
        Artifact artifact = new Artifact(this, 0);
        Dodge dodge = new Dodge(this, 0);
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
        damage = (int) (damage * multiplyingDealDamage);
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
        this.silence.triggerFunc();
        this.stun.triggerFunc();
        this.excite.triggerFunc();
        this.erode.triggerFunc();

    }

    public void bindApp(SimpleApplication app) {
        this.app = app;
    }

    public void getBuff(Buff... buffs) {
        for (Buff buff : buffs) {

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
                } else if (buff instanceof Silence) {
                    this.silence.incDuration(((Silence) buff).getDuration());
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


    public double getMultiplyingDealDamage() {
        return multiplyingDealDamage;
    }

    public void setMultiplyingDealDamage(double multiplyingDealDamage) {
        this.multiplyingDealDamage = multiplyingDealDamage;
    }

    public double getMultiplyingGetDamage() {
        return multiplyingGetDamage;
    }

    public void setMultiplyingGetDamage(double multiplyingGetDamage) {
        this.multiplyingGetDamage = multiplyingGetDamage;
    }

    public double getMultiplyingGetBlock() {
        return multiplyingGetBlock;
    }

    public void setMultiplyingGetBlock(double multiplyingGetBlock) {
        this.multiplyingGetBlock = multiplyingGetBlock;
    }

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
