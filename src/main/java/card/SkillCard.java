package card;

public class SkillCard extends Card {
    private int block;


    public SkillCard(String name, int cost, TYPE type, RARITY rarity,String description) {
        super(name, cost, type, rarity, description);
    }
}
