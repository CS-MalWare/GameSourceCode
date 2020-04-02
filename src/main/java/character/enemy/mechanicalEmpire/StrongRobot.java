package character.enemy.mechanicalEmpire;

import character.Enemy;
import character.MainRole;

public class StrongRobot extends Enemy {
    //TODO 固化HP和SRC等属性
    public StrongRobot(int HP, String src, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {
        super(HP, src, block, strength, dexterity, dodge, artifact, shield, disarm, silence);
        this.nextActionSet = new String[]
                {
                        "this enemy will deal 20 damages to you",
                        "this enemy will exert strong blessing on itself",
                        "this enemy will gain some block"
                };
        this.nextActionIndex = (int) (Math.random() * this.nextActionSet.length);
    }

    @Override
    public void enemyAction() {
        switch (this.nextActionIndex){
            case 0:
                this.attack();
                break;
            case 1:
                this.getBlessing();
                break;
            case 2:
                this.getBlocks();
                break;
            default:
                break;
        }
    }

    @Override
    protected void attack() {
        this.target.getDamage((int)(20*this.getMultiplyingDealDamage()));
        this.treat(5);
    }

    @Override
    protected void releaseDebuff() {

    }

    @Override
    protected void releaseCurses() {
    }

    @Override
    protected void getBlocks() {
        this.setBlock(this.getBlock()+15);
    }

    @Override
    protected void getBlockAndAttack() {

    }

    @Override
    protected void releaseBuff() {

    }

    @Override
    protected void getBlessing() {
        this.setStrength(this.getStrength()+3);
        this.treat(5);
    }
}
