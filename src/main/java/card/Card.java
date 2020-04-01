package card;

import character.Role;
import com.jme3.asset.AssetManager;
import com.jme3.math.Quaternion;
import com.jme3.scene.Geometry;
import com.jme3.ui.Picture;


public class Card extends Picture {
    public enum RARITY {COMMON, RARE, EPIC, LEGENDARY} //公有：卡牌的稀有度

    public enum TYPE {ATTACK, SKILL, POWER, CURSE}  //公有：卡牌的类型

    private String name; //卡牌的名称
    private int cost;  //卡牌的费用
    private TYPE type; //卡牌的类型
    private RARITY rarity; //卡牌的稀有度
    private String description; //卡牌的效果描述
    private String path;   //图片路径

    protected boolean upgraded; // 是否已经升级

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
        this.path += "/" + name + ".png";
        this.cost = cost;
        this.type = type;
        this.rarity = rarity;
        this.description = description;
        this.setHeight(260);
        this.setWidth(200);
    }

    public Card(String name) {
        super(name);
        this.path = name;
        this.setHeight(260);
        this.setWidth(200);
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

    public String getPath() {
        return path;
    }

    public OCCUPATION getOccupation() {
        return occupation;
    }


    public void setImage(AssetManager assetManager) {
        super.setImage(assetManager, this.path, true);
        this.setHeight(260);
        this.setWidth(200);
    }


    public void reset() {
        this.setLocalRotation(new Quaternion());
    }

    // 子类需要重写该方法
    public boolean upgrade() {
        return false;
        // 记得调用该函数后, 要在别的地方调用setImage,来改变实际的图片
    }


    // 子类需要重写该方法
    public boolean use(Role target) {
        return false;
    }

    @Override
    public Card clone(boolean cloneMaterial) {
        return (Card) super.clone(cloneMaterial);
    }

    @Override
    public Card clone() {
        return (Card) super.clone();
    }
}
