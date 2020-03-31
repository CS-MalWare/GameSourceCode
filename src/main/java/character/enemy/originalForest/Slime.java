package character.enemy.originalForest;

import character.Enemy;
import character.MainRole;
import utils.buffs.limitBuffs.Weak;

class Slime extends Enemy {
    //TODO 固化HP和src等属性
    public Slime(int HP, String src, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {
        super(HP, src, block, strength, dexterity, dodge, artifact, shield, disarm, silence);
        this.nextActionSet = new String[]
                {
                        "this enemy will deal 5 damages to you", //5
                        "this enemy will inflict debuffs on you",
                        "this enemy will gain 5 block", //5
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
                String.format(hints[0], computeDamage(5)),
                hints[1],
                String.format(hints[3], computeBlock(5)),
        };

        newTurn();
    }

    @Override
    protected void attack() {
        this.target.getDamage(computeDamage(5));
    }

    @Override
    protected void releaseDebuff() {
        this.target.getBuff(new Weak(target, 1));
    }

    @Override
    protected void releaseCurses() {

    }

    @Override
    protected void getBlocks() {
        this.gainBlock(computeBlock(5));
    }

    @Override
    protected void getBlockAndAttack() {

    }

    @Override
    protected void releaseBuff() {

    }

    @Override
    protected void getBlessing() {

    }

    //敌人行动
    @Override
    public void enemyAction() {
        switch (this.nextActionIndex) {
            case 0:
                attack();
                break;
            case 1:
                releaseDebuff();
                break;
            case 2:
                getBlocks();
                break;
            default:
                return;
        }
    }


}