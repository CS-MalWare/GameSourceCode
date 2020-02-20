package card.caster.attack;

import card.Card;

public class MagicImpulse extends Card {
    private boolean upgraded = false;
    private int damage;
    private int times;
    private String src;


    public MagicImpulse(boolean upgraded) {
        super("Magic impulse(+)", 1, TYPE.ATTACK, RARITY.RARE, "deal 11 damage and keep one turn of cards");
        this.upgraded = true;
        this.damage = 8;
        this.times = 1;
        this.src = "Cards/caster/attack/奥数冲击(+).png";
    }

    public MagicImpulse() {
        super("Magic impulse", 1, TYPE.ATTACK, RARITY.RARE, "deal 8 damage and keep one turn of cards");
        this.upgraded=false;
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

    public void Upgrade(){
        this.setName("Magic impulse(+)");
        this.setDamage(11);
        this.setUpgraded(true);

    }

}
