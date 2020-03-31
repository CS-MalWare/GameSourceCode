package character.enemy.mechanicalEmpire;

import character.Enemy;
import character.MainRole;

public class Robot extends Enemy {
    //TODO 固化HP和SRC等属性
    public Robot(int HP, String src, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {
        super(HP, src, block, strength, dexterity, dodge, artifact, shield, disarm, silence);
        this.nextActionSet = new String[]
                {
                        "this enemy will gain some buff",
                        "this enemy will deal 10 damages to you and gain 10 blocks",
                        "this enemy will inflict strong curses on you",
                };
        this.nextActionIndex = (int) (Math.random() * this.nextActionSet.length);
    }

    @Override
    public void enemyAction() {
        switch (this.nextActionIndex){
            case 0:
                releaseBuff();
                break;
            case 1:
                getBlockAndAttack();
                break;
            case 2:
                releaseCurses();
                break;
            default:
                break;
        }
    }

    @Override
    protected void attack() {

    }

    @Override
    protected void releaseDebuff() {

    }

    @Override
    protected void releaseCurses() {
        //TODO 随机消耗玩家一张牌
        this.setBlock(this.getBlock()+5);
    }

    @Override
    protected void getBlocks() {

    }

    @Override
    protected void getBlockAndAttack() {
        this.target.getDamage((int)(5*this.getMultiplyingDealDamage()));
        this.setBlock(this.getBlock()+10);
    }

    @Override
    protected void releaseBuff() {
        //TODO 3层荆棘
    }

    @Override
    protected void getBlessing() {

    }
}
