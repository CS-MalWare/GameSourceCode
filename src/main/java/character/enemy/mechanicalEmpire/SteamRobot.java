package character.enemy.mechanicalEmpire;

import character.Enemy;
import character.MainRole;
import utils.buffs.foreverBuffs.Dodge;
import utils.buffs.limitBuffs.Silence;
import utils.buffs.limitBuffs.Stun;
import utils.buffs.limitBuffs.Vulnerable;
import utils.buffs.limitBuffs.Weak;

public class SteamRobot extends Enemy {
    //TODO 固化HP和SRC等属性
    public SteamRobot(int HP, String src, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {
        super(HP, src, block, strength, dexterity, dodge, artifact, shield, disarm, silence);

        this.nextActionSet = new String[]
                {
                        "this enemy will gain 20 blocks",
                        "this enemy will inflict debuffs on you",
                        "this enemy will gain some buff",
                        "this enemy will deal 45 damages to you",
                        "this enemy will inflict strong curses on you",
                        "this enemy will deal 15 damages to you and gain 15 blocks",
                        "this enemy will deal 10*3 damages to you",
                        "this enemy will deal 15 damages to you"
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
                String.format(hints[3], computeBlock(20)),
                hints[1],
                hints[5],
                String.format(hints[0], computeDamage(45)),
                hints[2],
                String.format(hints[4],computeDamage(15),computeBlock(15)),
                String.format(hints[7],computeBlock(10),3),
                String.format(hints[0],computeDamage(15)),
        };
        newTurn();
    }

    @Override
    public String enemyAction() {
        switch (this.nextActionIndex){
            case 0:
                this.getBlocks();
                return "robot skill";
            case 1:
                this.releaseDebuff();
                return "robot skill";
            case 2:
                this.releaseBuff();
                return "robot skill";
            case 3:
                this.attack();
                return "robot attack";
            case 4:
                this.releaseCurses();
                return "robot skill";
            case 5:
                this.getBlockAndAttack();
                return "robot attack";
            case 6:
                this.attack2();
                return "robot attack";
            case 7:
                this.attack3();
                return "robot attack";
            default:
                return "";
        }
    }

    @Override
    protected void attack() {
        this.target.getDamage(computeDamage(45));
        this.getBuff(new Stun(this,1));
    }

    protected void attack2(){
        for(int i = 0;i<3;i++)
            this.target.getDamage(computeDamage(15));
    }

    protected void attack3(){
        this.target.getDamage(computeDamage(15));
        this.target.getBuff(new Silence(this.target,2));
    }

    @Override
    protected void releaseDebuff() {
        this.target.getBuff(new Weak(this.target,2),new Vulnerable(this.target,2));

    }

    @Override
    protected void releaseCurses() {
        this.target.getBuff(new Weak(this.target,5),new Vulnerable(this.target,5));
    }

    @Override
    protected void getBlocks() {
        this.setBlock(this.getBlock()+computeDamage(20));
    }

    @Override
    protected void getBlockAndAttack() {
        this.target.getDamage(computeDamage(15));
        this.setBlock(this.getBlock()+computeBlock(15));
    }

    @Override
    protected void releaseBuff() {
        this.getBuff(new Dodge(this,1));
    }

    @Override
    protected void getBlessing() {

    }
}
