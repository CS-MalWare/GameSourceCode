package card;

import card.caster.attack.MagicImpulse;

public class CreateCard {
    private static String path = "Cards/caster/";//所有卡牌的基础路径

    //传入卡牌名称和卡牌类型
    public static Card createCard(String cardName, Card.TYPE type) {
        switch (type) {
            case ATTACK://
                switch (cardName) {
                    case "充钱.png":
                        return new Card(Card.OCCUPATION.SABER, cardName, 1, type, Card.RARITY.COMMON, "deal 8 damage and keep one turn of cards");
                    case "奥数冲击.png":
                        return new MagicImpulse();
                    default:
                        return null;
                }
            case SKILL:
                switch (cardName) {
                    case "星陨.png":
                        return new Card(Card.OCCUPATION.SABER, cardName, 1, type, Card.RARITY.RARE, "deal 8 damage and keep one turn of cards");
                    default:
                        return null;
                }
            case POWER:
                switch (cardName) {
                    case "奥数冲击.png":
                        return new Card(Card.OCCUPATION.SABER, cardName, 1, type, Card.RARITY.RARE, "deal 8 damage and keep one turn of cards");
                    default:
                        return null;
                }
            case CURSE:
                switch (cardName) {
                    case "蛇皮操作.png":
                        return new Card(Card.OCCUPATION.SABER, cardName, 1, type, Card.RARITY.RARE, "deal 8 damage and keep one turn of cards");
                    default:
                        return null;
                }
            default:
                return null;
        }
    }

}
