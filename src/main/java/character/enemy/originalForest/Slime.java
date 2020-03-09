package character.enemy.originalForest;

import character.Enemy;
import character.MainRole;

class Slime extends Enemy {
    //TODO 固化HP和src等属性
    public Slime(int HP, String src, MainRole target, int block, int strength, int dexterity, int dodge, int artifact, int shield, boolean unableAttack, boolean unableSkill) {
        super(HP, src, target, block, strength, dexterity, dodge, artifact, shield, unableAttack, unableSkill);
        this.nextActionSet = new String[]
                {
                        "this enemy will deal 5 damages to you",
                        "this enemy will inflict debuffs on you",
                        "this enemy will gain some block",
                };
        this.nextActionIndex = (int)(Math.random()*this.nextActionSet.length+0.5);
    }
    @Override
    protected void attack() {
        this.target.getDamage((int) (5 * this.getMultiplyingDealDamage()));
    }

    @Override
    protected void releaseDebuff(){
        //TODO 等待buff类做完 1层虚弱
    }

    @Override
    protected void releaseCurses() {

    }
    @Override
    protected void getBlocks(){
        this.setBlock(this.getBlock()+5);
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
    public void enemyAction(){
        switch (this.nextActionIndex){
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