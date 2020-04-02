package character.enemy.dragonWat;

import character.Enemy;

public class RampageDarkDragon extends Enemy {
    public RampageDarkDragon(int HP, String src, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {
        super(HP, src, block, strength, dexterity, dodge, artifact, shield, disarm, silence);
        this.nextActionSet = new String[]{
                "this enemy will deal 30 damages to you",
                "this enemy will inflict debuffs on you",
                "this enemy will gain 20 blocks",
                "this enemy will deal 5*4 damages to you",
        };
        this.nextActionIndex = (int)(Math.random()*(this.nextActionSet.length));
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
        this.target.getDamage((int)(30*this.getMultiplyingDealDamage()));
    }

    protected void attack2() {
        for(int i = 0;i<4;i++){
            this.target.getDamage((int)(5*this.getMultiplyingDealDamage()));
        }
        //TODO 1层闪避
    }

    @Override
    protected void releaseDebuff() {
        //TODO 2层虚弱，3层脆弱
    }

    @Override
    protected void releaseCurses() {

    }

    @Override
    protected void getBlocks() {
        this.setBlock(this.getBlock()+20);
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
