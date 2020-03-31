package card.caster.attack;

import card.AttackCard;
import card.Card;

public class MagicImpulse extends AttackCard {
    private boolean upgraded = false; //卡牌是否升级
    private int damage; //卡牌的伤害
    private int times; //卡牌的？
    private String src; //卡牌的路径


    public MagicImpulse(boolean upgraded) {
        super(OCCUPATION.CASTER, "Magic impulse(+)", 1, RARITY.RARE, "deal 11 damage and keep one turn of cards", 11, 1);
        this.upgraded = true;
        this.damage = 8;
        this.times = 1;
        this.src = "Cards/caster/attack/奥数冲击(+).png";
    }

    public MagicImpulse() {
        super(OCCUPATION.CASTER, "Magic impulse", 1, RARITY.RARE, "deal 8 damage and keep one turn of cards", 8, 1);
        this.upgraded = false;
        this.damage = 8;
        this.times = 1;
        this.src = "Cards/caster/attack/奥数冲击.png";
    }

    public boolean isUpgraded() {
        return upgraded;
    }

    public int getDamage() {
        return damage;
    }

    public int getTimes() {
        return times;
    }

    public String getSrc() {
        return src;
    }

    private void setUpgraded(boolean upgraded) {
        this.upgraded = upgraded;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    @Override
    public void func() {

    }

    public void upgrade() {
        this.setName("Magic impulse(+)");
        this.setDamage(11);
        this.setUpgraded(true);

    }

}
