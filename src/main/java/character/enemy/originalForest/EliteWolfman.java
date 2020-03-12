package character.enemy.originalForest;

import character.Enemy;
import character.MainRole;

public class EliteWolfman extends Enemy {
    //TODO 固化HP和src等属性
    private boolean canSummon = true;//是否能召唤狼人
    private boolean isWeak = false;//用于判断是否能够攻击吸血

    public EliteWolfman(int HP, String src, MainRole target, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {
        super(HP, src, target, block, strength, dexterity, dodge, artifact, shield, disarm, silence);
        this.nextActionSet = new String[]
                {
                        "this enemy will deal 3*2 damages to you",
                        "this enemy will deal 15 damages to you and gain some block",
                        "this enemy will gain some block",
                        "this enemy will inflict debuffs on you",
                        "this enemy will inflict strong curses on you",
                        "???",//这个是召唤狼人
                };

        this.nextActionIndex = (int)(Math.random()*this.nextActionSet.length+0.5);
    }

    @Override
    public void newTurn() {
        super.newTurn();

        //是否能够触发吸血效果
        if(this.getHP()<0.3*this.getTotalHP()){
            this.setStrength(this.getStrength() + 2);
            this.isWeak = true;
        }
        else{
            this.isWeak = false;
        }
        //TODO 判断能不能刷新出召唤狼人的事件
    }

    private void $(){
        //TODO 这里写召唤狼人等逻辑处理
    }
    @Override
    protected void attack() {
        if(!this.isWeak) {
            for (int i = 0; i < 2; i++) {
                this.target.getDamage((int) (3 * this.getMultiplyingDealDamage()));
            }
        }
        else{
            for (int i = 0; i < 2; i++) {
                this.target.getDamage((int) (3 * this.getMultiplyingDealDamage()));
                //TODO 吸血效果处理
            }
        }
        //TODO 3层流血
    }

    @Override
    protected void releaseDebuff(){
//        this.target.setShield(this.target.getShield()-2);
    }

    @Override
    protected void releaseCurses() {
        //TODO 3层 脆弱 与 2层 虚弱
    }
    @Override
    protected void getBlocks(){
        this.setBlock(this.getBlock() + 20);
        this.setStrength(this.getStrength() + 1);
    }

    @Override
    protected void getBlockAndAttack() {
        if(!this.isWeak) {
            this.target.getDamage((int) (15 * this.getMultiplyingDealDamage()));
        }
        else{
            this.target.getDamage((int) (15 * this.getMultiplyingDealDamage()));
            //TODO 吸血效果处理
        }
        this.setBlock(this.getBlock() + 8);
    }

    @Override
    protected void releaseBuff() {
    }

    @Override
    protected void getBlessing() {

    }

    //敌人行动
    @Override
    public void enemyAction(){
        switch (this.nextActionIndex){
            case 0:
                attack();
                break;
            case 1:
                getBlockAndAttack();
                break;
            case 2:
                getBlocks();
                break;
            case 3:
                releaseDebuff();
                break;
            case 4:
                releaseCurses();
                break;
            case 5:
                $();
                break;
            default:
                return;
        }
    }

}
