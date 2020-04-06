package character.enemy.boss;

import character.Enemy;
import utils.buffs.limitBuffs.Vulnerable;
import utils.buffs.limitBuffs.Weak;

public class Faker extends Enemy {
    private int lastDamageSum = 1;
    public Faker(int HP, String src, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {
        super(HP, src, block, strength, dexterity, dodge, artifact, shield, disarm, silence);
        this.nextActionSet =  new String[]
                {
                        "this enemy will deal 40 damages to you",
                        "this enemy will deal 10 damages to you and gain 20 blocks",
                        "this enemy will deal ? damages to you",
                        "this enemy will inflict debuffs on you",
                        "this enemy will deal 5*5 damages to you",
                        "???"
                };
        this.nextActionIndex = (int)(Math.random()*this.nextActionSet.length);
    }

    @Override
    public void startTurn() {
        super.startTurn();
        if (stun.getDuration() > 0) {
            return;
        }
        this.nextActionSet = new String[]{
                String.format(hints[0], computeDamage(40)),
                String.format(hints[4],computeDamage(10),computeBlock(20)),
                String.format(hints[0],computeDamage(lastDamageSum)),
                hints[1],
                String.format(hints[7],computeDamage(5),5),
                "???"
        };
        newTurn();
    }

    @Override
    public void enemyAction() {
        switch (this.nextActionIndex){
            case 0:
                this.attack();
                break;
            case 1:
                this.getBlockAndAttack();
                break;
            case 2:
                this.attack2();
                break;
            case 3:
                this.releaseDebuff();
                break;
            case 4:
                this.attack3();
                break;
            case 5:
                this.$();
                break;
            default:
                break;

        }
    }

    @Override
    public void newTurn() {
        super.newTurn();
        this.target.decMP(1);
    }

    //当faker受到一次减少10点以上hp的伤害时候,会对玩家造成5点伤害
    //玩家对该BOSS造成伤害时候,BOSS会改变行动
    @Override
    public int getDamage(int damage) {
        if(damage>10){
            this.target.getDamage(computeDamage(5));
            lastDamageSum = computeDamage(5);
        }
        this.nextActionIndex = (int)(Math.random()*this.nextActionSet.length);
        return super.getDamage(damage);
    }

    @Override
    protected void attack() {
        this.target.getDamage(computeDamage(40));
        lastDamageSum = computeDamage(40);
    }

    protected void attack2(){
        this.target.getDamage(computeDamage(lastDamageSum));
        lastDamageSum = computeDamage(lastDamageSum);
    }

    protected void attack3(){
        int temp = 0;
        for(int i = 0;i<5;i++){
            this.target.getDamage(computeDamage(5));
            temp += computeDamage(5);
        }
        lastDamageSum = computeDamage(temp);
    }

    protected void $(){
        int temp = (10+this.getStrength())*(this.getTotalHP()-this.getHP())/10;//回复10%的已损失的生命值
        this.treat(temp);
        lastDamageSum = temp;
    }

    @Override
    protected void releaseDebuff() {
        this.target.getBuff(new Weak(this.target,3),new Vulnerable(this.target,3));
        this.target.setStrength(this.getStrength()-1);
        this.target.setDexterity(this.getDexterity()-1);
    }

    @Override
    protected void releaseCurses() {

    }

    @Override
    protected void getBlocks() {

    }

    @Override
    protected void getBlockAndAttack() {
        this.target.getDamage(computeDamage(10));
        this.setBlock(this.getBlock()+computeBlock(20));
        lastDamageSum = computeDamage(10);
    }

    @Override
    protected void releaseBuff() {

    }

    @Override
    protected void getBlessing() {

    }
}
