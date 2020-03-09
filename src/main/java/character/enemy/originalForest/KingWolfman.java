package character.enemy.originalForest;

import character.Enemy;
import character.MainRole;

public class KingWolfman extends Enemy {
    //TODO 固化HP和src等属性
    private boolean canSummon = true;//是否能召唤狼人
    private boolean canIncreaseStrength = true;//增加力量，全局只能使用一次，因此这里写一个布尔值限制只能用一次
    public KingWolfman(int HP, String src, MainRole target, int block, int strength, int dexterity, int dodge, int artifact, int shield, boolean unableAttack, boolean unableSkill) {
        super(HP, src, target, block, strength, dexterity, dodge, artifact, shield, unableAttack, unableSkill);
        this.nextActionSet = new String[]
                {
                        "this enemy will deal 20 damages to you and gain some block",
                        "this enemy will inflict debuffs on you",
                        "this enemy will deal 5*3 damages to you",
                        "this enemy will inflict strong curses on you",
                        "this enemy will gain some buff",
                        "this enemy will gain some block",
                        "???",//这个是召唤狼人
                };

        this.nextActionIndex = (int)(Math.random()*this.nextActionSet.length+0.5);
    }


    @Override
    public void newTurn() {
        super.newTurn();

        //血量50%以下增加5点力量
        if(this.getHP() == this.getTotalHP()/2 && canIncreaseStrength){
            this.setStrength(this.getStrength()+5);
        }

        //TODO 判断能不能召唤狼人
    }

    private void $(){
        //TODO 这里写召唤狼人等逻辑处理
    }

    @Override
    protected void attack() {
        int demage = (int) (5 * this.getMultiplyingDealDamage());
        for(int i = 0;i<3;i++) {
            this.target.getDamage(demage);

            this.treat(demage/4);
        }
        //TODO 减少玩家 1 点MP
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
        //TODO 玩家下一张卡牌消耗翻倍
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
            case 6:
                $();
                break;
            default:
                return;
        }
    }

}
