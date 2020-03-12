package character.enemy.originalForest;

import character.Enemy;
import character.MainRole;

public class EliteSlime extends Enemy {
    //TODO 固化HP和src等属性
    public EliteSlime(int HP, String src, MainRole target, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {
        super(HP, src, target, block, strength, dexterity, dodge, artifact, shield, disarm, silence);
        this.nextActionSet = new String[]
                {
                        "this enemy will deal 20 damages to you",
                        "this enemy will inflict debuffs on you",
                        "this enemy will inflict strong curses on you",
                        "this enemy will deal x damages to you and gain some block",
                };
        this.nextActionIndex = (int) (Math.random() * this.nextActionSet.length + 0.5);
    }


    @Override
    public void newTurn() {
        super.newTurn();
        this.treat(5);//每回合开始回复5点血量
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
        this.target.getDamage((int) (20 * this.getMultiplyingDealDamage()));
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
    protected void getBlocks() {

    }

    @Override
    protected void getBlockAndAttack() {
        this.setBlock(this.getBlock()+10);
        this.target.getDamage((int) (5 * this.getMultiplyingDealDamage()));
    }

    @Override
    protected void releaseBuff() {

    }

    @Override
    protected void getBlessing() {

    }
}
