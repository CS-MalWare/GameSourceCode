package character;

import utils.buffs.foreverBuffs.Artifact;
import utils.buffs.foreverBuffs.Dodge;
import utils.buffs.limitBuffs.Disarm;
import utils.buffs.limitBuffs.Sheild;
import utils.buffs.limitBuffs.Silence;

public abstract class Enemy extends Role {
    protected MainRole target;//攻击目标
    protected String[] nextActionSet;
    protected String[] hints = new String[]
            {
                    "this enemy will deal %d damages to you",
                    "this enemy will inflict debuffs on you",
                    "this enemy will inflict strong curses on you",
                    "this enemy will gain %d blocks",
                    "this enemy will deal %d damages to you and gain %d blocks",
                    "this enemy will gain some buff",
                    "this enemy will exert strong blessing on itself",
                    "this enemy will deal %d * %d damages to you",
            };

    /*
    *
            {
                "this enemy will deal x damages to you",
                "this enemy will inflict debuffs on you",
                "this enemy will inflict strong curses on you",
                "this enemy will gain some block",
                "this enemy will deal x damages to you and gain some block",
                "this enemy will gain some buff",
                "this enemy will exert strong blessing on itself"
            };
    * */
    protected int nextActionIndex;//下回合行动在行动集合🀄️的索引
    protected String specialStatus;//一些特殊状态

    public Enemy(int HP, String src, MainRole target, int block, int strength, int dexterity, int dodge, int artifact, int shield, int disarm, int silence) {
        super(HP, src, ROLE.ENEMY);
        this.target = target;
        this.setBlock(block);
        this.setStrength(strength);
        this.setDexterity(dexterity);

        // 更新buff状态
        this.getBuff(new Dodge(this, dodge), new Artifact(this, artifact),
                new Sheild(this, shield), new Disarm(this, disarm), new Silence(this, silence));


    }


    public Enemy(int HP, String src, MainRole target) {
        super(HP, src, ROLE.ENEMY);
        this.target = target;
    }

    public String getNextActionDescription() {
        return this.nextActionSet[this.nextActionIndex];
    }

    //新回合，重随下回合随机事件
    public void newTurn() {
        this.nextActionIndex = (int) (Math.random() * this.nextActionSet.length + 0.5);
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
