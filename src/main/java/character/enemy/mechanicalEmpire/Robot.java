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
                        "this enemy will gain 14 blocks",
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
                getBlocks();
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
    }

    @Override
    protected void getBlocks() {
        this.setBlock(this.getBlock()+14);

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
