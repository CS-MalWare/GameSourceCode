package character.enemy.originalForest;

import character.Enemy;
import character.MainRole;

public class EliteSlime extends Enemy {
    //TODO 固化HP和src等属性
    public EliteSlime(int HP, String src, MainRole target, int block, int strength, int dexterity, int dodge, int artifact, int shield, boolean unableAttack, boolean unableSkill) {
        super(HP, src, target, block, strength, dexterity, dodge, artifact, shield, unableAttack, unableSkill);
        this.nextActionSet = new String[]
                {
                        "this enemy will deal 20 damages to you",
                        "this enemy will inflict debuffs on you",
                        "this enemy will inflict strong curses on you",
                        "this enemy will deal x damages to you and gain some block",
                };
        this.nextActionIndex = (int)(Math.random()*this.nextActionSet.length+0.5);
    }


    //每回合回血效果
    //TODO 放在哪个时间点
    private void incHP(){
        if(this.getHP()<this.getTotalHP()-5){
            this.HP += 5;
        }
        else{
            this.HP = this.totalHP;
        }
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
            case 3:
                getBlockAndAttack();
                break;
            default:
                return;
        }
    }

    @Override
    protected void attack() {
        this.target.getDamage((int) (20 * this.multiplyingDealDamage));
        //TODO 眩晕自己
    }

    @Override
    protected void releaseDebuff() {
        //TODO 3层虚弱
    }

    @Override
    protected void releaseCurses() {
        //TODO 将1张玩家卡组品质最高的卡 消耗
    }

    @Override
    protected void getBlock() {

    }

    @Override
    protected void getBlockAndAttack() {
        this.block+=10;
        this.target.getDamage((int) (5 * this.multiplyingDealDamage));
    }

    @Override
    protected void releaseBuff() {

    }

    @Override
    protected void getBlessing() {

    }
}
