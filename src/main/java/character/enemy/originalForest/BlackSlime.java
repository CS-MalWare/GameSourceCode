package character.enemy.originalForest;

import character.Enemy;
import character.MainRole;
import utils.buffs.limitBuffs.Weak;

public class BlackSlime extends Enemy {
    //TODO 固化HP和src等属性
    public BlackSlime(int HP, String src, MainRole target, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {
        super(HP, src, target, block, strength, dexterity, dodge, artifact, shield, disarm, silence);
        this.nextActionSet = new String[]
                {
                        "this enemy will deal 7 damages to you",
                        "this enemy will inflict debuffs on you",
                        "this enemy will exert strong blessing on itself",
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
                String.format(hints[0], computeDamage(7)),
                hints[1],
                hints[6]
        };
        newTurn();
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
    public void enemyAction(){
        switch (this.nextActionIndex){
            case 0:
                attack();
                break;
            case 1:
                releaseDebuff();
                break;
            case 2:
                releaseBuff();
                break;
            default:
                return;
        }
    }

}
