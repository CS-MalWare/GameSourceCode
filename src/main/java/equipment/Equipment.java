package equipment;

import character.Enemy;
import character.MainRole;

import java.util.ArrayList;

public abstract class Equipment {
    public static enum EquipmentDegree {NORMAL, SENIOR, RARE, LEGEND}
    //装备触发的时机，分为以下几个时机
    //：
    // 1. 战斗结束时触发
    // 2. 回合结束时触发（包括 每几个回合 触发）
    // 3. 战斗开始时触发
    // 4. 拾取时触发
    // 5. 打出牌时触发（包含 各种类型 牌）
    // 6. 受到伤害时触发
    // 7. 死亡时触发

    // 实现思路： 将角色所有装备作为一个装备数组添加到主角类中。每次进行动作，遍历装备数组，找到对应触发时机的装备触发。
    public static enum Opportunity {ENDB, ENDT, STARTB, GET, USE, GETD, DEAD}

    protected String name;
    protected String description;//装备效果的描述
    protected String imgSrc;//装备图片的路径
    protected ArrayList<Enemy> enemies;//当前对局的敌人链表
    protected MainRole mainRole;//装备着这个装备的角色
    protected EquipmentDegree degree;
    protected Opportunity op;
    public Equipment(String name, String description, String imgSrc, MainRole mainRole, EquipmentDegree degree,Opportunity op) {
        this.name = name;
        this.description = description;
        this.imgSrc = imgSrc;
        this.mainRole = mainRole;
        this.degree = degree;
        this.op = op;
    }

    public String getDescription() {
        return description;
    }

    //可以考虑删除，描述一般不改变
    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    //可以考虑删除，因为图片路径一般不改变
    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    //部分卡牌可能和敌人有联动效果，这里可以设置和清除敌人, 每次战斗完需要吧敌人链表设置为null
    //便于触发战斗结束的装备
    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public MainRole getMainRole() {
        return mainRole;
    }

    public void setMainRole(MainRole mainRole) {
        this.mainRole = mainRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EquipmentDegree getDegree() {
        return degree;
    }

    public void setDegree(EquipmentDegree degree) {
        this.degree = degree;
    }

    public Opportunity getOpportunity() {
        return op;
    }

    //可以删除
    public void setOpportunity(Opportunity op) {
        this.op = op;
    }

    // 在这里写装备的功能
    public abstract void fun();
}
