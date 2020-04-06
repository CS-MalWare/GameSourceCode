package character.enemy.dragonWat;

import character.Enemy;
import utils.buffs.limitBuffs.Bleeding;

public class KingDarkDragon extends Enemy {
    public KingDarkDragon(int HP, String src, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {

        super(HP, src, block, strength, dexterity, dodge, artifact, shield, disarm, silence);
        this.nextActionSet = new String[]{
                "this enemy will deal 20 damages to you",
                "this enemy will inflict debuffs on you",
                "this enemy will gain 25 blocks",
                "this enemy will deal 4*4 damages to you",
                "this enemy will deal 10 damages to you and gain 20 blocks"
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
                String.format(hints[0], computeDamage(20)),
                hints[1],
                String.format(hints[3],computeBlock(25)),
                String.format(hints[7],computeDamage(4),4),
                String.format(hints[4],computeDamage(10),computeBlock(20))
        };
        newTurn();
    }

    @Override
    public void newTurn() {
        super.newTurn();
        this.target.getTrueDamage(1);
        this.treat(2);
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
            case 4:
                this.getBlockAndAttack();
            default:
                break;
        }
    }

    @Override
    protected void attack() {
        this.target.getDamage(computeDamage(20));
        this.setStrength(this.getStrength()+1);
        this.target.getBuff(new Bleeding(this.target,2));
    }

    protected void attack2() {
        for(int i = 0;i<4;i++){
            this.target.getDamage(computeDamage(4));
        }
    }

    @Override
    protected void releaseDebuff() {
        this.target.setStrength(this.target.getStrength()-2);
        this.target.getBuff(new Bleeding(this.target,3));
    }

    @Override
    protected void releaseCurses() {

    }

    @Override
    protected void getBlocks() {
        this.setBlock(this.getBlock()+computeBlock(20));
    }

    @Override
    protected void getBlockAndAttack() {
        this.target.getDamage(computeDamage(10));
        this.setBlock(this.getBlock()+computeBlock(20));
    }

    @Override
    protected void releaseBuff() {

    }

    @Override
    protected void getBlessing() {

    }
}
