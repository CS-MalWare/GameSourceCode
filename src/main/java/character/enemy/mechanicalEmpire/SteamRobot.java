package character.enemy.mechanicalEmpire;

import character.Enemy;
import character.MainRole;

public class SteamRobot extends Enemy {
    //TODO 固化HP和SRC等属性
    public SteamRobot(int HP, String src, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {
        super(HP, src, block, strength, dexterity, dodge, artifact, shield, disarm, silence);
        //TODO 玩家每打出一张能力牌，自己获得一层 荆棘
        this.nextActionSet = new String[]
                {
                        "this enemy will gain 20 blocks",
                        "this enemy will inflict debuffs on you",
                        "this enemy will gain some buff",
                        "this enemy will deal 45 damages to you",
                        "this enemy will inflict strong curses on you",
                        "this enemy will deal some damages to you and gain some blocks",
                        "this enemy will deal 10*3 damages to you",
                        "this enemy will deal 15 damages to you"
                };
        this.nextActionIndex = (int)(Math.random()*this.nextActionSet.length);
    }

    @Override
    public void enemyAction() {
        switch (this.nextActionIndex){
            case 0:
                this.getBlocks();
                break;
            case 1:
                this.releaseDebuff();
                break;
            case 2:
                this.releaseBuff();
                break;
            case 3:
                this.attack();
                break;
            case 4:
                this.releaseCurses();
                break;
            case 5:
                this.getBlockAndAttack();
                break;
            case 6:
                this.attack2();
                break;
            case 7:
                this.attack3();
                break;
            default:
                break;
        }
    }

    @Override
    protected void attack() {
        this.target.getDamage((int)(45*this.getMultiplyingDealDamage()));
        //todo 眩晕自己1层
    }

    protected void attack2(){
        for(int i = 0;i<3;i++)
            this.target.getDamage((int)(10*this.getMultiplyingDealDamage()));
    }

    protected void attack3(){
        this.target.getDamage((int)(15*this.getMultiplyingDealDamage()));
        //TODO 2层沉默
    }

    @Override
    protected void releaseDebuff() {
        //TODO 2层虚弱，2层脆弱

    }

    @Override
    protected void releaseCurses() {
        //TODO 5层虚弱，5层脆弱
    }

    @Override
    protected void getBlocks() {
        this.setBlock(this.getBlock()+20);
    }

    @Override
    protected void getBlockAndAttack() {
        this.setBlock(this.getBlock()+15);
        this.target.getDamage((int)(15*this.getMultiplyingDealDamage()));
    }

    @Override
    protected void releaseBuff() {
        //TODO 1层闪避
    }

    @Override
    protected void getBlessing() {

    }
}
