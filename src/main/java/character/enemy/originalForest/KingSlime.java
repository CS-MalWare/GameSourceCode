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
        this.target.getDamage((int) (35 * this.multiplyingDealDamage));
        //TODO 眩晕自己
    }

    @Override
    protected void releaseDebuff() {
        //TODO 3层虚弱

        //回血效果
        if(this.getHP()<this.getTotalHP()-10){
            this.HP += 10;
        }
        else{
            this.HP = this.totalHP;
        }


    }

    @Override
    protected void releaseCurses() {
        //TODO 释放3张粘液到玩家卡组
    }

    @Override
    protected void getBlock() {

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
