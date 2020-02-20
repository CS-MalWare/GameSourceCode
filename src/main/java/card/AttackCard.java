package card;
import  card.Card;
public class AttackCard extends Card {
    private int damage;
    private int times;

    public AttackCard(String name, int cost, TYPE type, RARITY rarity,  String description, int damage,int times) {
        super(name, cost, type, rarity, description);
        this.damage =damage;
        this.times= times;

    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
