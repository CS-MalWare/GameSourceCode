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
        this.nextActionSet = new String[]
                {
                        "this enemy will deal 9 damages to you",
                        "this enemy will inflict debuffs on you",
                        "this enemy will deal 5 damages to you and gain 7 block",
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
        newTurn();
    }

    @Override
    protected void attack() {
        this.target.getDamage((int) (9 * this.getMultiplyingDealDamage()));

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
    public void enemyAction(){
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

}
