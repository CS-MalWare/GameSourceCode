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
                        "this enemy will gain 15 block"
                };
        this.nextActionIndex = (int) (Math.random() * this.nextActionSet.length);
    }

    @Override
    public void startTurn() {
        super.startTurn();
        if (stun.getDuration() > 0) {
            return;
        }
        this.nextActionSet = new String[]{
                String.format(hints[0], computeDamage(20)),
                hints[6],
                String.format(hints[3],computeBlock(15)),
        };
        newTurn();
    }

    @Override
    public String enemyAction() {
        switch (this.nextActionIndex){
            case 0:
                this.attack();
                return "robot attack";
            case 1:
                this.getBlessing();
                return "robot skill";
            case 2:
                this.getBlocks();
                return "robot skill";
            default:
                return "";
        }
    }

    @Override
    protected void attack() {
        this.target.getDamage(computeDamage(20));
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
        this.setBlock(this.getBlock()+computeBlock(15));
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
