package character.enemy.dragonWat;

import character.Enemy;

public class EliteDarkDragon extends Enemy {
    public EliteDarkDragon(int HP, String src, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {
        super(HP, src, block, strength, dexterity, dodge, artifact, shield, disarm, silence);
        this.nextActionSet = new String[]{
                "this enemy will deal 35 damages to you",
                "this enemy will inflict debuffs on you",
                "this enemy will gain 10 blocks",
                "this enemy will deal 6*4 damages to you",
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
        this.target.getDamage((int)(35*this.getMultiplyingDealDamage()));
    }

    protected void attack2() {
        for(int i = 0;i<4;i++){
            this.target.getDamage((int)(6*this.getMultiplyingDealDamage()));
        }
        //TODO 1层闪避
    }

    @Override
    protected void releaseDebuff() {
        //TODO 玩家减2力量
    }

    @Override
    protected void releaseCurses() {

    }

    @Override
    protected void getBlocks() {
        this.setBlock(this.getBlock()+10);
        this.setStrength(this.getStrength()+4);
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
