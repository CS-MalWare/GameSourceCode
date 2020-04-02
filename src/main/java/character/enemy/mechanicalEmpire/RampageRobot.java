package character.enemy.mechanicalEmpire;

import character.Enemy;
import character.MainRole;

public class RampageRobot extends Enemy {
    //TODO 固化HP和SRC等属性
    public RampageRobot(int HP, String src, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {
        super(HP, src, block, strength, dexterity, dodge, artifact, shield, disarm, silence);
        this.nextActionSet = new String[]
                {
                        "this enemy will deal 20 damages to you",
                        "this enemy will inflict debuffs on you",
                        "this enemy will deal 10 damage to you and gain 10 blocks"

                };
        //TODO 5层荆棘
    }

    @Override
    public void enemyAction() {
        switch (this.nextActionIndex){
            case 0:
                this.attack();
                break;
            case 1:
                this.releaseDebuff();
                break;
            case 2:
                this.getBlockAndAttack();
                break;
            default:
                break;
        }
    }

    @Override
    protected void attack() {
        this.target.getDamage((int)(20*this.getMultiplyingDealDamage()));
        //TODO 2层虚弱
    }

    @Override
    protected void releaseDebuff() {
        if(Math.random()>0.5){
            //TODO 1层缴械
        }
        else{
            //TODO 1层沉默
        }
        this.treat(5);
    }

    @Override
    protected void releaseCurses() {

    }

    @Override
    protected void getBlocks() {

    }

    @Override
    protected void getBlockAndAttack() {
        this.target.getDamage((int)(10*this.getMultiplyingDealDamage()));
        this.setBlock(this.getBlock()+10);
    }

    @Override
    protected void releaseBuff() {

    }

    @Override
    protected void getBlessing() {

    }
}
