package character.enemy.originalForest;

import character.Enemy;
import character.MainRole;

public class KingWolfman extends Enemy {
    //TODO 固化HP和src等属性
    private boolean canIncreaseStrength = true;//增加力量，全局只能使用一次，因此这里写一个布尔值限制只能用一次

    public KingWolfman(int HP, String src, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {
        super(HP, src, block, strength, dexterity, dodge, artifact, shield, disarm, silence);
        this.nextActionSet = new String[]
                {
                        "this enemy will deal 20 damages to you and gain some block",
                        "this enemy will inflict debuffs on you",
                        "this enemy will deal 5*3 damages to you",
                        "this enemy will inflict strong curses on you",
                        "this enemy will gain some buff",
                        "this enemy will gain some block",
                };

        this.nextActionIndex = (int)(Math.random()*this.nextActionSet.length);
    }


    @Override
    public void newTurn() {
        super.newTurn();

        //血量50%以下增加5点力量
        if(this.getHP() == this.getTotalHP()/2 && canIncreaseStrength){
            this.setStrength(this.getStrength()+5);
        }

    }


    @Override
    protected void attack() {
        int demage = (int) (5 * this.getMultiplyingDealDamage());
        for(int i = 0;i<3;i++) {
            this.target.getDamage(demage);

            this.treat(demage/4);
        }
    }

    @Override
    protected void releaseDebuff(){
        //TODO 5层 流血给玩家
    }

    @Override
    protected void releaseCurses() {
        this.setStrength(this.getStrength()+2);
        //TODO 2 层迟钝给玩家
    }
    @Override
    protected void getBlocks(){
        this.setBlock(this.getBlock() + 20);
    }

    @Override
    protected void getBlockAndAttack() {
        int demage = (int)(20*this.getMultiplyingDealDamage());
        this.target.getDamage(demage);

        this.treat(demage/4);
        this.setBlock(this.getBlock()+10);
        //TODO 2层 虚弱，2 层 脆弱
    }

    @Override
    protected void releaseBuff() {
        //TODO 4 层荆棘
    }

    @Override
    protected void getBlessing() {

    }

    //敌人行动
    @Override
    public void enemyAction(){
        switch (this.nextActionIndex){
            case 0:
                getBlockAndAttack();
                break;
            case 1:
                releaseDebuff();
                break;
            case 2:
                attack();
                break;
            case 3:
                releaseCurses();
                break;
            case 4:
                releaseBuff();
                break;
            case 5:
                getBlocks();
                break;
            default:
                return;
        }
    }

}
