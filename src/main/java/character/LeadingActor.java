package character;

import utils.buffs.foreverBuffs.Artifact;
import utils.buffs.foreverBuffs.Dodge;
import utils.buffs.limitBuffs.Disarm;
import utils.buffs.limitBuffs.Sheild;
import utils.buffs.limitBuffs.Silence;

public abstract class LeadingActor extends Role {
    protected String[] nextActionSet;
    protected String[] hints = new String[]
            {

            };
    protected int nextActionIndex;
    protected String specialStatus;

    public LeadingActor(int HP, String src, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {
        super(HP, src, ROLE.ENEMY);
        this.setBlock(block);
        this.setStrength(strength);
        this.setDexterity(dexterity);

        this.getBuff(new Dodge(this, dodge), new Artifact(this, artifact),
                new Sheild(this, shield), new Disarm(this, disarm), new Silence(this, silence));
    }

    public LeadingActor(int HP, String src, MainRole target) {
        super(HP, src, ROLE.ENEMY);
    }

    public String getNextActionDescription() {
        return this.nextActionSet[this.nextActionIndex];
    }

    //新回合，重随下回合随机事件
    public void newTurn() {
        this.nextActionIndex = (int) (Math.random() * this.nextActionSet.length);
    }

    //敌人行动
    public abstract void enemyAction();

    //敌人攻击
    protected abstract void attack();

    //敌人释放减益
    protected abstract void releaseDebuff();

    //敌人释放强大的诅咒
    protected abstract void releaseCurses();

    //敌人获得护甲
    //注意，这里是getblocks，因为getblock已经在基类Role中用于访问block属性了
    protected abstract void getBlocks();

    //敌人造成伤害和获得护甲
    protected abstract void getBlockAndAttack();

    //敌人获得增益
    protected abstract void releaseBuff();

    //敌人获得强大效果
    protected abstract void getBlessing();

    //适用于吸血效果或者治疗效果
    protected void treat(int number) {
        if (this.getHP() + number >= this.getTotalHP()) {
            this.setHP(this.getTotalHP());
        } else {
            this.setHP(this.getHP() + number);
        }
    }
}
