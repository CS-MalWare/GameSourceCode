package character.enemy.boss;

import character.Enemy;
import utils.buffs.foreverBuffs.Dodge;
import utils.buffs.limitBuffs.*;

public class Tuxnet extends Enemy {
    private int lastDamageSum = 15;
    public Tuxnet(int HP, String src, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {
        super(HP, src, block, strength, dexterity, dodge, artifact, shield, disarm, silence);
        this.nextActionSet =  new String[]
                {
                        "this enemy will deal ? damages to you",
                        "this enemy will deal 1*8 damages to you",
                        "this enemy will gain 4*4 blocks",
                        "this enemy will deal 30 damages to you and gain 15 blocks",
                        "this enemy will gain some buff",
                        "this enemy will inflict strong curses on you"
                };
        this.nextActionIndex = (int)(Math.random()*this.nextActionSet.length);
    }

    @Override
    public void enemyAction() {
        switch (this.nextActionIndex){
            case 0:
                this.attack();
                break;
            case 1:
                this.attack2();
                break;
            case 2:
                this.getBlocks();
                break;
            case 3:
                this.getBlockAndAttack();
                break;
            case 4:
                this.releaseBuff();
                break;
            case 5:
                this.releaseCurses();
                break;
            default:
                break;
        }
    }

    @Override
    public void newTurn() {
        super.newTurn();
        if(this.getHP()<100){
            this.target.decMP(1);
        }

        //40生命值的时候锁血
        if(this.getHP()<40){
            this.setHP(40);
        }

        //力量和灵巧恒为已损失生命值/100
        this.setStrength((this.getTotalHP()-this.getHP())/100);
        this.setDexterity((this.getTotalHP()-this.getHP())/100);

        //2层 中毒, 2层 流血, 1层 虚弱, 1层 脆弱
        int random = (int)(4*Math.random());
        switch (random){
            case 0:
                this.target.getBuff(new Posion(this.target,2));
                break;
            case 1:
                this.target.getBuff(new Bleeding(this.target,2));
                break;
            case 2:
                this.target.getBuff(new Weak(this.target,1));
                break;
            case 3:
                this.target.getBuff(new Vulnerable(this.target,1));
                break;
            default:
                break;
        }
    }

    @Override
    public int getDamage(int damage) {
        int temp = super.getDamage(damage);
        this.treat(temp-100>0?temp-100:0);//受到单次伤害的上限为100
        this.target.getDamage(computeDamage(1));
        this.lastDamageSum = computeDamage(1);
        return temp;
    }

    @Override
    protected void attack() {
        this.target.getDamage(computeDamage(lastDamageSum));
        this.lastDamageSum = computeDamage(lastDamageSum);
    }

    protected void attack2(){
        int temp = 0;
        for(int i = 0;i<8;i++){
            this.target.getDamage(computeDamage(1));
            temp += computeDamage(1);
        }
        this.lastDamageSum = temp;
    }

    @Override
    protected void releaseDebuff() {

    }

    @Override
    protected void releaseCurses() {
        this.target.setStrength(this.target.getStrength()-1);
        this.target.setDexterity(this.target.getDexterity()-1);
    }

    @Override
    protected void getBlocks() {
        for(int i =0;i<4;i++){
            this.setBlock(this.getBlock()+computeBlock(4));
        }
    }

    @Override
    protected void getBlockAndAttack() {
        this.target.getDamage(computeDamage(30));
        lastDamageSum = computeDamage(30);
        this.setBlock(this.getBlock()+computeBlock(15));
    }

    @Override
    protected void releaseBuff() {
        this.getBuff(new Intangible(this,3),new Dodge(this,1));
        this.treat((10+this.getStrength())*(this.getTotalHP()-this.getHP())/10);
    }

    @Override
    protected void getBlessing() {

    }
}
