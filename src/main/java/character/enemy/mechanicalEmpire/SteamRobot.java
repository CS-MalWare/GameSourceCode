package character.enemy.mechanicalEmpire;

import character.Enemy;
import character.MainRole;

public class SteamRobot extends Enemy {
    //TODO 固化HP和SRC等属性
    public SteamRobot(int HP, String src, MainRole target, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {
        super(HP, src, target, block, strength, dexterity, dodge, artifact, shield, disarm, silence);
        //TODO 永久 10层 护盾 玩家每打出一张能力牌，自己获得一层 荆棘 一点 灵巧 每四个回合，随机使玩家一件装备在本场战斗中无效
        this.nextActionSet = new String[]
                {
                        "this enemy will gain 20 blocks",
                        "this enemy will inflict debuffs on you",
                        "this enemy will gain some buff",
                        "???",//召唤两个小机器人
                        "this enemy will deal 45 damages to you",
                        "this enemy will inflict strong curses on you",
                        "this enemy will deal some damages to you and gain some blocks",
                        "this enemy will deal 10*3 damages to you",
                        "this enemy will deal 15 damages to you"
                };
        this.nextActionIndex = (int)(Math.random()*this.nextActionSet.length);
    }

    @Override
    public void enemyAction() {
        switch (this.nextActionIndex){
            case 0:
                this.getBlocks();
                break;
            case 1:
                this.releaseDebuff();
                break;
            case 2:
                this.releaseBuff();
                break;
            case 3:
                this.$();
                break;
            case 4:
                this.attack();
                break;
            case 5:
                this.releaseCurses();
                break;
            case 6:
                this.getBlockAndAttack();
                break;
            case 7:
                this.attack2();
                break;
            case 8:
                this.attack3();
                break;
            default:
                break;
        }
    }

    protected void $(){
        //TODO 召唤两个小机器人
    }

    @Override
    protected void attack() {
        this.target.getDamage((int)(45*this.getMultiplyingDealDamage()));
        //todo 眩晕自己1层
    }

    protected void attack2(){
        for(int i = 0;i<3;i++)
            this.target.getDamage((int)(10*this.getMultiplyingDealDamage()));
    }

    protected void attack3(){
        this.target.getDamage((int)(15*this.getMultiplyingDealDamage()));
        //TODO 2层沉默
    }

    @Override
    protected void releaseDebuff() {
        //TODO 5层虚弱，5层脆弱

    }

    @Override
    protected void releaseCurses() {
        //TODO 4张灼烧塞入玩家的卡组（回合末，受到2点伤害，无法打出）
    }

    @Override
    protected void getBlocks() {
        this.setBlock(this.getBlock()+20);
        //暂时 消耗 一张玩家卡组中稀有度最高的卡。 当扣除50点HP时候归还
    }

    @Override
    protected void getBlockAndAttack() {
        this.setBlock(this.getBlock()+8);
        //TODO 玩家卡组中每有一张诅咒牌，便造成2点伤害
    }

    @Override
    protected void releaseBuff() {
        //TODO 1层闪避
    }

    @Override
    protected void getBlessing() {

    }
}
