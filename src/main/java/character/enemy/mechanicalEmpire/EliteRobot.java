package character.enemy.mechanicalEmpire;

import character.Enemy;
import character.MainRole;
import utils.buffs.limitBuffs.*;

public class EliteRobot extends Enemy {
    //TODO 固化HP和SRC等属性
    public EliteRobot(int HP, String src, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {
        super(HP, src, block, strength, dexterity, dodge, artifact, shield, disarm, silence);
        //TODO 10层护盾
        this.nextActionSet = new String[]
                {
                        "this enemy will deal 20 damages to you",
                        "this enemy will inflict strong curses on you",
                        "this enemy will gain 25 blocks",
                        "this enemy will gain some buff",
                        "this enemy will deal 15*2 damages to you",
                        "???"//这里是恢复15%已损失生命值，造成等量伤害（hp处于50%以下的时候可能使用）
                };
        this.nextActionIndex = (int)(Math.random()*(this.nextActionSet.length-1));//第一回合不可能随机到最后一个事件
    }

    @Override
    public void newTurn() {
        super.newTurn();

        // 确保50%以下血量才能触发最后一个事件
        while(this.nextActionIndex==this.nextActionSet.length-1){
            if(this.getHP()<0.5*this.getTotalHP()){
                break;
            }
            else{
                this.nextActionIndex = (int) (Math.random() * this.nextActionSet.length);
            }
        }
    }


    @Override
    public void enemyAction() {
        switch (this.nextActionIndex){
            case 0:
                this.attack();
                break;
            case 1:
                this.releaseCurses();
                break;
            case 2:
                this.getBlocks();
                break;
            case 3:
                this.releaseBuff();
                break;
            case 4:
                this.attack2();
                break;
            case 5:
                this.$();
                break;
            default:
                break;
        }

    }

    protected void $(){
        int treatValue = computeDamage((int)(0.15*(this.getTotalHP()-this.getHP())));
        this.target.getDamage(treatValue);
        this.treat(treatValue);
    }

    @Override
    protected void attack() {
        this.target.getDamage(computeDamage(20));
        this.target.getBuff(new Weak(this.target,2), new Vulnerable(this.target,2));
    }

    protected void attack2(){
        for(int i = 0;i<2;i++){
            this.target.getDamage(computeDamage(15));
        }
    }

    @Override
    protected void releaseDebuff() {
    }

    @Override
    protected void releaseCurses() {
        this.setStrength(this.getStrength()+2);
        this.target.getBuff(new Disarm(this.target,1),new Silence(this.target,1));

    }

    @Override
    protected void getBlocks() {
        this.setBlock(this.getBlock()+computeDamage(25));
    }

    @Override
    protected void getBlockAndAttack() {

    }

    @Override
    protected void releaseBuff() {
        this.getBuff(new Intangible(this,2));

    }

    @Override
    protected void getBlessing() {

    }
}
