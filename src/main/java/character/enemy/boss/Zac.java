package character.enemy.boss;

import character.Enemy;
import utils.buffs.limitBuffs.Intangible;
import utils.buffs.limitBuffs.Weak;

public class Zac extends Enemy {

    private boolean canImprove = true;//是否能够触发生命值在50%下增加能力
    public Zac(int HP, String src, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {
        super(HP, src, block, strength, dexterity, dodge, artifact, shield, disarm, silence);
        this.nextActionSet =  new String[]
                {
                        "this enemy will deal 25 damages to you",
                        "this enemy will deal 4*8 damages to you",
                        "this enemy will inflict debuffs on you",
                        "this enemy will gain 50 blocks",
                        "this enemy will inflict strong curses on you",
                        "this enemy will gain some buff",
                };
        this.nextActionIndex = (int)(Math.random()*this.nextActionSet.length);
        this.getBuff(new Intangible(this,1));
    }

    @Override
    public void newTurn() {
        super.newTurn();

        //每回合开始重置受伤倍数
        this.setMultiplyingGetDamage(1.0);

        //当生命值小于50%增加能力
        if(canImprove&&this.getHP()*2<=this.getTotalHP()){
            this.canImprove = false;
            this.setStrength(this.getStrength()+5);
            this.setDexterity(this.getDexterity()+5);
        }
    }

    //重写获得伤害的方法，用于每次受伤增加下次收到伤害
    @Override
    public int getDamage(int damage) {
        int tempDamage =  super.getDamage(damage);
        this.setMultiplyingGetDamage(this.getMultiplyingGetDamage()+0.1);
        return tempDamage;
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
                this.releaseDebuff();
                break;
            case 3:
                this.getBlocks();
                break;
            case 4:
                this.releaseCurses();
                break;
            case 5:
                this.releaseBuff();
                break;
            default:
                break;
        }
    }

    @Override
    protected void attack() {
        this.target.getDamage(computeDamage(25));
    }

    protected void attack2() {
        for(int i =0;i<8;i++){
            this.target.getDamage(computeDamage(4));
        }
    }

    @Override
    protected void releaseDebuff() {
        this.target.setStrength(this.target.getStrength()-1);
        this.target.getBuff(new Weak(this.target,3));
    }

    @Override
    protected void releaseCurses() {
        this.target.setStrength(this.target.getStrength()+1);
        this.target.setDexterity(this.target.getDexterity()-4);
        if(this.target.getDexterity()<=-1){
            this.target.getTrueDamage((int)(this.target.getTotalHP()*0.1));//造成最大生命值10%的真实伤害
            this.target.setStrength(0);
            this.target.setDexterity(0);
        }

    }

    @Override
    protected void getBlocks() {
        this.setBlock(this.getBlock()+computeBlock(50));
    }

    @Override
    protected void getBlockAndAttack() {

    }

    @Override
    protected void releaseBuff() {
        this.setStrength(this.getStrength()+1);
        this.getBuff(new Intangible(this,1));
    }

    @Override
    protected void getBlessing() {

    }
}
