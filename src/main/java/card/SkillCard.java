package card;

public class SkillCard extends Card {
    private int block;

    public SkillCard(OCCUPATION occupation, String name, int cost, RARITY rarity, String description) {
        super(occupation, name, cost, TYPE.SKILL, rarity, description);
    }
}
