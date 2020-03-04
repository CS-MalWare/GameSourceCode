package card;

import com.jme3.ui.Picture;


public class Card extends Picture {
    public enum RARITY {COMMON, RARE, EPIC, LEGENDARY} //公有：卡牌的稀有度

    public enum TYPE {ATTACK, SKILL, POWER, CURSE}  //公有：卡牌的类型

    private String name; //卡牌的名称
    private int cost;  //卡牌的费用
    private TYPE type; //卡牌的类型
    private RARITY rarity; //卡牌的稀有度
    private String description; //卡牌的效果描述
    private String path;

    public enum OCCUPATION {SABER, CASTER, NEUTRAL}

    private OCCUPATION occupation;

    public Card(OCCUPATION occupation, String name, int cost, TYPE type, RARITY rarity, String description) {
        super(name);
        this.occupation = occupation;
        switch (this.occupation) {
            case SABER:
                this.path = "Cards/saber";
                break;
            case CASTER:
                this.path = "Cards/caster";
                break;
            case NEUTRAL:
                this.path = "Cards/neutral";
                break;
            default:
                return;
        }
        switch (this.type) {
            case ATTACK:
                this.path += "/attack";
                break;
            case SKILL:
                this.path += "/skill";
                break;
            case POWER:
                this.path += "/power";
                break;
            default:
                return;
        }
        this.name = name;
        this.cost = cost;
        this.type = type;
        this.rarity = rarity;
        this.description = description;
    }

    public String getCardName() {
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

    public void setCardName(String name) {
        this.name = name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
