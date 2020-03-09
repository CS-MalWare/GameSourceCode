package character;

public class Role {
    public enum ROLE {MAINROLE, NPC, ENEMY}

    ;
    protected int totalHP; //总血量
    protected int HP; // 当前血量
    private String src; //模型路径
    private ROLE role; // 属于什么角色

    //战斗中的实际属性
    protected int block;
    protected int strength;
    protected int dexterity;
    protected int dodge;
    protected int artifact;
    protected int shield;


    protected boolean unableAttack;
    protected boolean unableSkill;


    public double multiplyingDealDamage; //造成伤害的倍率

    public double multiplyingGetDamage; //受到伤害的倍率

    public double multiplyingGetBlock; //从卡牌中获取格挡值的倍率

    public Role(int HP, String src, ROLE role) {
        this.totalHP = HP;
        this.HP = HP;
        this.src = src;
        this.role = role;
        this.multiplyingDealDamage = 1;
        this.multiplyingGetDamage = 1;
        this.multiplyingGetBlock = 1;
    }

    public int getTotalHP() {
        return totalHP;
    }

    public int getHP() {
        return HP;
    }

    public String getSrc() {
        return src;
    }

    public void getDamage(int damage) {
        if (this.dodge >= 0) {
            this.dodge -= 1;
            if (this.block >= 1) {
                this.block -= 1;
            } else {
                this.HP -= 1;
            }
        }

        damage = (int) (damage * this.multiplyingGetDamage);
        if (this.block >= damage) {
            this.block -= damage;
        } else {
            this.HP -= damage - block;
        }
    }

    public void getTrueDamage(int damage) {
        this.HP -= damage;
    }

    public void getHealed(int heal) {
        this.HP += heal;
    }

    public void attack(Role role, int damage) {
        damage = (int) (damage * this.multiplyingDealDamage);
        role.getDamage(damage);
    }


    public ROLE getRole() {
        return role;
    }

    public void incDodge(int num) {
        this.dodge += num;
    }

    public void incArtifact(int num) {
        this.artifact += num;
    }

    public void incDexterity(int num) {
        this.dexterity += num;
    }

    public void incStrength(int num) {
        this.strength += num;
    }

    public boolean isUnableAttack() {
        return unableAttack;
    }

    public void setUnableAttack(boolean unableAttack) {
        this.unableAttack = unableAttack;
    }

    public boolean isUnableSkill() {
        return unableSkill;
    }

    public void setUnableSkill(boolean unableSkill) {
        this.unableSkill = unableSkill;
    }

}
