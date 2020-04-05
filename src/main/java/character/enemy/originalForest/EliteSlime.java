package character.enemy.originalForest;

import character.Enemy;
import character.MainRole;
import utils.buffs.limitBuffs.Stun;
import utils.buffs.limitBuffs.Weak;

public class EliteSlime extends Enemy {
    //TODO 固化HP和src等属性
    public EliteSlime(int HP, String src, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {
        super(HP, src, block, strength, dexterity, dodge, artifact, shield, disarm, silence);
        this.nextActionSet = new String[]
                {
                        "this enemy will deal 20 damages to you",
                        "this enemy will inflict debuffs on you",
                        "this enemy will deal 5 damages to you and 10  block",
                };
        this.nextActionIndex = (int) (Math.random() * this.nextActionSet.length);
    }

    @Override
    public void startTurn() {
        newTurn();
        super.startTurn();
        if (stun.getDuration() > 0) {
            return;
        }
        this.nextActionSet = new String[]{
                String.format(hints[0], computeDamage(20)),
                hints[1],
                String.format(hints[4], computeDamage(5), computeBlock(10)),
        };
        newTurn();
    }


    @Override
    public void newTurn() {
        super.newTurn();
        this.treat(5);//每回合开始回复5点血量
    }

    @Override
    public void enemyAction() {
        switch (this.nextActionIndex){
            case 0:
                attack();
                break;
            case 1:
                releaseDebuff();
                break;
            case 2:
                getBlockAndAttack();
                break;
            default:
                return;
        }
    }

    @Override
    protected void attack() {
        this.target.getDamage(computeDamage(20));
        this.getBuff(new Stun(this, 1));
    }

    @Override
    protected void releaseDebuff() {
        this.target.getBuff(new Weak(this.target, 3));
    }

    @Override
    protected void releaseCurses() {
    }

    @Override
    protected void getBlocks() {

    }

    @Override
    protected void getBlockAndAttack() {
        this.gainBlock(computeBlock(10));
        this.target.getDamage(computeDamage(5));
    }

    @Override
    protected void releaseBuff() {

    }

    @Override
    protected void getBlessing() {

    }
}
