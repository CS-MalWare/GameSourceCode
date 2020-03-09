package character;

public class Role {
    public enum ROLE {MAINROLE, NPC, ENEMY}

    ;
    private int totalHP; //总血量
    private int HP; // 当前血量
    private String src; //模型路径
    private ROLE role; // 属于什么角色

    //战斗中的实际属性
    private int block;
    private int strength;
    private int dexterity;
    private int dodge;
    private int artifact;
    private int shield;
    private boolean unableAttack;
    private boolean unableSkill;
    private double multiplyingDealDamage; //造成伤害的倍率
    private double multiplyingGetDamage; //受到伤害的倍率
    private double multiplyingGetBlock; //从卡牌中获取格挡值的倍率

    public Role(int HP, String src, ROLE role) {
        this.totalHP = HP;
        this.HP = HP;
        this.src = src;
        this.role = role;
        this.multiplyingDealDamage = 1;
        this.multiplyingGetDamage = 1;
        this.multiplyingGetBlock = 1;
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

    public int getDodge() {
        return dodge;
    }

    public void setDodge(int dodge) {
        this.dodge = dodge;
    }

    public int getArtifact() {
        return artifact;
    }

    public void setArtifact(int artifact) {
        this.artifact = artifact;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
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

    public boolean getUnableAttack() {
        return this.unableAttack;
    }

    public void setUnableAttack(boolean unableAttack) {
        this.unableAttack = unableAttack;
    }

    public boolean getUnableSkill() {
        return this.unableSkill;
    }

    public void setUnableSkill(boolean unableSkill) {
        this.unableSkill = unableSkill;
    }

}
