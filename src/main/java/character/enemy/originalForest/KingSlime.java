package character.enemy.originalForest;

import character.Enemy;
import character.MainRole;
import utils.buffs.limitBuffs.Stun;
import utils.buffs.limitBuffs.Vulnerable;
import utils.buffs.limitBuffs.Weak;

import java.time.temporal.WeekFields;

public class KingSlime extends Enemy {
    //TODO 固化HP和src等属性
    public KingSlime(int HP, String src, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {
        super(HP, src, block, strength, dexterity, dodge, artifact, shield, disarm, silence);
        this.nextActionSet = new String[]
                {
                        "this enemy will deal 35 damages to you",
                        "this enemy will inflict debuffs on you",
                        "this enemy will inflict strong curses on you",
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
                String.format(hints[0], computeDamage(35)),
                hints[1],
                hints[2]
        };
        this.nextActionIndex = (int) (Math.random() * this.nextActionSet.length);
        newTurn();
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
                releaseCurses();
                break;
            default:
                return;
        }
    }

    @Override
    protected void attack() {
        this.target.getDamage(computeDamage(35));
        this.getBuff(new Stun(this, 1));
    }

    @Override
    protected void releaseDebuff() {
        this.target.getBuff(new Weak(target, 3));

        //回血效果
        this.treat(10);


    }

    @Override
    protected void releaseCurses() {
        this.target.getBuff(new Weak(this.target,3),new Vulnerable(this.target,3));
    }

    @Override
    protected void getBlocks() {

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
}
