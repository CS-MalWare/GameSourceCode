package character.enemy.mechanicalEmpire;

import character.Enemy;
import character.MainRole;

public class EliteRobot extends Enemy {
    //TODO 固化HP和SRC等属性
    public EliteRobot(int HP, String src, MainRole target, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {
        super(HP, src, target, block, strength, dexterity, dodge, artifact, shield, disarm, silence);
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
        if(this.getHP()<0.5*this.getTotalHP()){
            //TODO 自爆
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
        int treatValue = (int)(0.15*(this.getTotalHP()-this.getHP()));
        this.target.getDamage((int)(treatValue*this.getMultiplyingDealDamage()));
        this.treat(treatValue);
    }

    @Override
    protected void attack() {
        this.target.getDamage((int)(20*this.getMultiplyingDealDamage()));
        //TODO 2层虚弱，2层脆弱
    }

    protected void attack2(){
        for(int i = 0;i<2;i++){
            this.target.getDamage((int)(15*this.getMultiplyingDealDamage()));
        }
    }

    @Override
    protected void releaseDebuff() {
        //TODO 4层荆棘
    }

    @Override
    protected void releaseCurses() {
        //TODO 1层沉默，缴械
        this.setStrength(this.getStrength()+2);

    }

    @Override
    protected void getBlocks() {
        this.setBlock(this.getBlock()+25);
        //TODO 随机消耗玩家一张牌
    }

    @Override
    protected void getBlockAndAttack() {

    }

    @Override
    protected void releaseBuff() {

    }

    @Override
    protected void getBlessing() {

    }
}
