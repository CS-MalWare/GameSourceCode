package card;

import card.Card;
import character.MainRole;
import character.Role;


public abstract class AttackCard extends Card {
    private int damage;
    private int times;

    public AttackCard(OCCUPATION occupation, String name, int cost, RARITY rarity, String description, int damage, int times) {
        super(occupation, name, cost, TYPE.ATTACK, rarity, description);
        this.damage = damage;
        this.times = times;

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

    @Override
    public boolean use(Role target) {
        if (target == MainRole.getInstance()) {
            return false;
        }
        for (int i = 0; i < this.times; i++) {
            target.getDamage(target.computeDamage(this.damage));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
