package character.enemy.dragonWat;

import character.Enemy;
import utils.buffs.foreverBuffs.Dodge;
import utils.buffs.limitBuffs.Vulnerable;
import utils.buffs.limitBuffs.Weak;

public class DarkDragon extends Enemy {
    public DarkDragon(int HP, String src, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {
        super(HP, src, block, strength, dexterity, dodge, artifact, shield, disarm, silence);
        this.nextActionSet = new String[]{
                "this enemy will deal 25 damages to you",
                "this enemy will inflict debuffs on you",
                "this enemy will gain 15 blocks",
                "this enemy will deal 5*3 damages to you",
        };
        this.nextActionIndex = (int)(Math.random()*(this.nextActionSet.length));
    }

    @Override
    public void startTurn() {
        super.startTurn();
        if (stun.getDuration() > 0) {
            return;
        }
        this.nextActionSet = new String[]{
                String.format(hints[0], computeDamage(25)),
                hints[1],
                String.format(hints[3], computeBlock(15)),
                String.format(hints[7],computeDamage(5),3),
        };
        newTurn();
    }

    @Override
    public void enemyAction() {
        switch (this.nextActionIndex){
            case 0:
                this.attack();
                break;
            case 1:
                this.releaseDebuff();
                break;
            case 2:
                this.getBlocks();
                break;
            case 3:
                this.attack2();
                break;
            default:
                break;
        }
    }

    @Override
    protected void attack() {
        this.target.getDamage(computeDamage(25));
    }

    protected void attack2() {
        for(int i = 0;i<3;i++){
            this.target.getDamage(computeDamage(5));
        }
        this.getBuff(new Dodge(this,1));
    }

    @Override
    protected void releaseDebuff() {
        this.target.getBuff(new Weak(this.target,2),new Vulnerable(this.target,2));
    }

    @Override
    protected void releaseCurses() {

    }

    @Override
    protected void getBlocks() {
        this.setBlock(this.getBlock()+computeBlock(15));
        this.setStrength(this.getStrength()+2);
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
