package character.enemy.originalForest;

import character.Enemy;
import character.MainRole;

public class OneEyedWolfman extends Enemy {
    //TODO 固化HP和src等属性
    public OneEyedWolfman(int HP, String src, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {
        super(HP, src, block, strength, dexterity, dodge, artifact, shield, disarm, silence);
        this.nextActionSet = new String[]
                {
                        "this enemy will deal 5 damages to you",
                        "this enemy will gain some buff",
                };
        this.nextActionIndex = (int) (Math.random() * this.nextActionSet.length + 0.5);
    }

    @Override
    protected void attack() {
        int damage = (int) (7 * this.getMultiplyingDealDamage());
        this.target.getDamage(damage);

        //回复50%伤害值的血量，需要修改，因为伤害值计算还没有准确
        int treatValue = damage/2;
        this.treat(treatValue);
        //TODO 3层流血
    }

    @Override
    protected void releaseDebuff(){
    }

    @Override
    protected void releaseCurses() {

    }
    @Override
    protected void getBlocks(){

    }

    @Override
    protected void getBlockAndAttack() {

    }

    @Override
    protected void releaseBuff() {
        this.setStrength(this.getStrength()+3);
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
                releaseBuff();
                break;
            default:
                return;
        }
    }

}
