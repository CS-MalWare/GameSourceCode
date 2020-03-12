package character.enemy.originalForest;

import character.Enemy;
import character.MainRole;

public class RedSilme extends Enemy {
    //TODO 固化HP和src等属性
    public RedSilme(int HP, String src, MainRole target, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {
        super(HP, src, target, block, strength, dexterity, dodge, artifact, shield, disarm, silence);
        this.nextActionSet = new String[]
                {
                        "this enemy will deal 9 damages to you",
                        "this enemy will inflict debuffs on you",
                        "this enemy will deal x damages to you and gain some block",
                };
        this.nextActionIndex = (int) (Math.random() * this.nextActionSet.length + 0.5);
    }


    @Override
    protected void attack() {
        this.target.getDamage((int) (9 * this.getMultiplyingDealDamage()));
        //TODO 2层流血
    }

    @Override
    protected void releaseDebuff(){
        //TODO 等待buff类做完,3层虚弱
    }

    @Override
    protected void releaseCurses() {
        
    }

    @Override
    protected void getBlocks() {

    }

    @Override
    protected void getBlockAndAttack(){
        this.setBlock(this.getBlock()+7);
        this.target.getDamage((int) (5 * this.getMultiplyingDealDamage()));

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
