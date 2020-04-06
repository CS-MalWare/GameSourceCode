package character.enemy.mechanicalEmpire;

import character.Enemy;
import character.MainRole;
import utils.buffs.limitBuffs.Intangible;

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
    public void startTurn() {
        super.startTurn();
        if (stun.getDuration() > 0) {
            return;
        }
        this.nextActionSet = new String[]{
                hints[5],
                String.format(hints[0], computeDamage(10)),
                String.format(hints[3],computeBlock(14)),
        };
        newTurn();
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
        this.setBlock(this.getBlock()+computeBlock(14));

    }

    @Override
    protected void getBlockAndAttack() {
        this.target.getDamage(computeDamage(5));
        this.setBlock(this.getBlock()+computeBlock(10));
    }

    @Override
    protected void releaseBuff() {
        this.getBuff(new Intangible(this,1));
    }

    @Override
    protected void getBlessing() {

    }
}
