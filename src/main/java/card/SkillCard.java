package card;

public class SkillCard extends Card {
    private int block;


    public SkillCard(String path,String name, int cost, TYPE type, RARITY rarity,String description) {
        super(path,name, cost, type, rarity, description);
    }
}
