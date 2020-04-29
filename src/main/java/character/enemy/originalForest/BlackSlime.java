package character.enemy.originalForest;

import character.Enemy;
import character.MainRole;
import utils.buffs.limitBuffs.Weak;

public class BlackSlime extends Enemy {
    //TODO 固化HP和src等属性
    public BlackSlime(int HP, String src, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {
        super(HP, src, block, strength, dexterity, dodge, artifact, shield, disarm, silence);
        this.nextActionSet = new String[]{
                String.format(hints[0], computeDamage(7)),
                hints[1],
                hints[6]
        };
        this.nextActionIndex = (int) (Math.random() * this.nextActionSet.length);
    }


    @Override
    public void startTurn() {
        super.startTurn();
        if (stun.getDuration() > 0) {
            return;
        }

    }

    @Override
    protected void attack() {
        this.target.getDamage((int) (computeDamage(7)));
    }

    @Override
    protected void releaseDebuff() {
        this.target.getBuff(new Weak(target, 3));
    }

    @Override
    protected void releaseCurses() {

    }

    @Override
    protected void getBlocks() {

    }

    @Override
    protected void getBlockAndAttack() {

    }

    @Override
    protected void releaseBuff() {
        this.gainBlock(computeBlock(5));
        this.strength += 1;
    }
    @Override
    protected void getBlessing(){
    }

    //敌人行动
    @Override
    public String enemyAction(){
        switch (this.nextActionIndex){
            case 0:
                attack();
                this.newAction();
                return "slime attack";
            case 1:
                releaseDebuff();
                this.newAction();
                return "slime skill";
            case 2:
                releaseBuff();
                this.newAction();
                return "slime skill";
            default:
                return "";
        }
    }

}
