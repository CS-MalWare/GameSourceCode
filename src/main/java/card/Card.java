package card;

public class Card {
    public enum RARITY {COMMON,RARE,EPIC,LEGENDARY}
    public enum TYPE {ATTACK,SKILL,POWER,CURSE}
    private String name;
    private int cost;
    private TYPE type;
    private RARITY rarity;
    private String description;

    public Card(String name, int cost, TYPE type, RARITY rarity, String description) {
        this.name = name;
        this.cost = cost;
        this.type = type;
        this.rarity = rarity;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public TYPE getType() {
        return type;
    }

    public RARITY getRarity() {
        return rarity;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
