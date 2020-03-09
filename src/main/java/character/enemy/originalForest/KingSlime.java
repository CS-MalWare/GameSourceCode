package character.enemy.originalForest;

import character.Enemy;
import character.MainRole;

public class KingSlime extends Enemy {
    //TODO 固化HP和src等属性
    public KingSlime(int HP, String src, MainRole target, int block, int strength, int dexterity, int dodge, int artifact, int shield, boolean unableAttack, boolean unableSkill) {
        super(HP, src, target, block, strength, dexterity, dodge, artifact, shield, unableAttack, unableSkill);
        this.nextActionSet = new String[]
                {
                        "this enemy will deal 35 damages to you",
                        "this enemy will inflict debuffs on you",
                        "this enemy will inflict strong curses on you",
                };
        this.nextActionIndex = (int)(Math.random()*this.nextActionSet.length+0.5);
    }

    //分裂
    private void split(){
        //TODO 暂时写成void返回值类型，后面可能直接返回两个 Spatial 或者两个其他类型的史莱姆数组
        //TODO 调用的方式为重写 newTurn 方法或者在 enemyAction 中 检测当前血量再触发，这里等待王逸润巨佬意见
        //return new RedSilme[]();
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
        this.target.getDamage((int) (35 * this.getMultiplyingDealDamage()));
        //TODO 眩晕自己
    }

    @Override
    protected void releaseDebuff() {
        //TODO 3层虚弱

        //回血效果
        this.treat(10);


    }

    @Override
    protected void releaseCurses() {
        //TODO 释放3张粘液到玩家卡组
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
