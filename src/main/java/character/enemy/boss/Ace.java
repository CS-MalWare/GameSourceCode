package character.enemy.boss;

import character.Enemy;
import utils.buffs.foreverBuffs.Dodge;
import utils.buffs.limitBuffs.Intangible;
import utils.buffs.limitBuffs.Poison;


public class Ace extends Enemy {

    private int turnCount = 1;//用于计算战斗回合数，触发每3回合给玩家2层中毒

    public Ace(int HP, String src, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {
        super(HP, src, block, strength, dexterity, dodge, artifact, shield, disarm, silence);


        this.nextActionSet = new String[]{
                String.format(hints[0], computeDamage(25)),
                hints[5],
                hints[1],
                String.format(hints[3], computeBlock(20)),
                hints[6],
                String.format(hints[7],computeDamage(4),4),
                String.format(hints[4],computeDamage(10),computeBlock(10)),
        };
        this.nextActionIndex = (int)(Math.random()*this.nextActionSet.length);
    }

    @Override
    public void startTurn() {
        super.startTurn();
        if (stun.getDuration() > 0) {
            return;
        }
        if(turnCount%3==0){
            this.target.getBuff(new Poison(this.target, 2));
        }
    }


    //重写受伤事件，因为有一定几率闪避, 受伤会增加格挡数
    @Override
    public int getDamage(int damage) {
        if(Math.random()*3<1 && this.getHP()*5<this.getTotalHP()) {
            this.target.getBuff(new Poison(this.target, 1));
            return super.getDamage(1);
        }
        else {
            int tempDamage =  super.getDamage(damage);
            this.setBlock(this.getBlock()+(int)(tempDamage*0.5));
            return tempDamage;
        }
    }

    @Override
    public String enemyAction() {
        switch (this.nextActionIndex){
            case 0:
                this.attack();
                this.newAction();
                return "boss attack";
            case 1:
                this.releaseBuff();
                this.newAction();
                return "boss skill";
            case 2:
                this.releaseDebuff();
                this.newAction();
                return "boss skill";
            case 3:
                this.getBlocks();
                this.newAction();
                return "boss skill";
            case 4:
                this.getBlessing();
                this.newAction();
                return "boss skill";
            case 5:
                this.attack2();
                this.newAction();
                return "boss attack";
            case 6:
                this.getBlockAndAttack();
                this.newAction();
                return "boss attack";
            default:
                return "";


        }
    }

    @Override
    protected void attack() {
        this.target.getDamage(computeDamage(25));
        this.target.getBuff(new Poison(this.target, 1));

    }

    protected void attack2(){
        for(int i = 0;i<4;i++){
            this.target.getDamage(computeDamage(4));
        }

        this.target.getBuff(new Poison(this.target, 2));
    }

    @Override
    protected void releaseDebuff() {
        this.target.getBuff(new Poison(this.target, 5));

    }

    @Override
    protected void releaseCurses() {

    }

    @Override
    protected void getBlocks() {
        this.setBlock(this.getBlock()+computeBlock(20));
        this.getBuff(new Intangible(this,1));

    }

    @Override
    protected void getBlockAndAttack() {
        this.target.getDamage(computeDamage(10));
        this.setBlock(this.getBlock() + computeBlock(10));
        this.target.getBuff(new Poison(this.target, 1));
    }

    @Override
    protected void releaseBuff() {
        this.getBuff(new Dodge(this,1));
    }

    @Override
    protected void getBlessing() {
        this.setStrength(this.getStrength()+3);
        this.getBuff(new Dodge(this,1));
    }
}
