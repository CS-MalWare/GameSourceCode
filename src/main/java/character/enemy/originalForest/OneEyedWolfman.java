package character.enemy.originalForest;

import character.Enemy;
import character.MainRole;

public class OneEyedWolfman extends Enemy {
    //TODO 固化HP和src等属性
    public OneEyedWolfman(int HP, String src, MainRole target, int block, int strength, int dexterity, int dodge, int artifact, int shield, boolean unableAttack, boolean unableSkill) {
        super(HP, src, target, block, strength, dexterity, dodge, artifact, shield, unableAttack, unableSkill);
        this.nextActionSet = new String[]
                {
                        "this enemy will deal 5 damages to you",
                        "this enemy will gain some buff",
                };
        this.nextActionIndex = (int)(Math.random()*this.nextActionSet.length+0.5);
    }
    @Override
    protected void attack() {
        int damage = (int)(7 * this.getMultiplyingDealDamage());
        this.target.getDamage(damage);
        int treat = damage/2;
        if(this.getHP()<this.getTotalHP()-treat){
            this.setHP(this.getHP() + treat);
        }
        else{
            this.setHP(this.getTotalHP());
        }
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
