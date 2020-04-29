package character.enemy.originalForest;

import character.Enemy;
import character.MainRole;
import utils.buffs.Buff;
import utils.buffs.limitBuffs.Bleeding;
import utils.buffs.limitBuffs.Weak;

public class RedSilme extends Enemy {
    //TODO 固化HP和src等属性
    public RedSilme(int HP, String src, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {
        super(HP, src, block, strength, dexterity, dodge, artifact, shield, disarm, silence);

        this.nextActionSet = new String[]{
                String.format(hints[0], computeDamage(9)),
                hints[1],
                String.format(hints[4], computeDamage(5), computeBlock(7)),
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
                String.format(hints[0], computeDamage(9)),
                hints[1],
                String.format(hints[4], computeDamage(5), computeBlock(7)),
        };
    }

    @Override
    protected void attack() {
        this.target.getDamage(computeDamage(9));

        this.target.getBuff(new Bleeding(target, 2));
    }

    @Override
    protected void releaseDebuff() {
        this.target.getBuff(new Weak(target, 2));
    }

    @Override
    protected void releaseCurses() {
        
    }

    @Override
    protected void getBlocks() {

    }

    @Override
    protected void getBlockAndAttack() {
        this.gainBlock(computeBlock(7));
        this.target.getDamage(computeDamage(5));

    }

    @Override
    protected void releaseBuff() {

    }

    @Override
    protected void getBlessing() {

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
                getBlockAndAttack();
                this.newAction();
                return "slime attack";
            default:
                return "";
        }
    }

}
