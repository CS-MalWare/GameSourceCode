package equipment.legendary;

import card.Card;
import character.MainRole;
import equipment.Equipment;

public class TheMythOfPride extends Equipment {

    public TheMythOfPride() {
        super("The myth of pride", "高傲的神话", "Upgrade all cards, any healing effect recover 1 HP at most in the rest of this game", EquipmentDegree.LEGENDARY, Opportunity.GET);

    }

    @Override
    public void fun() {
        for (Card card : MainRole.getInstance().getDeck_()) {
            card.upgrade();
            card.setImage(MainRole.getInstance().getApp().getAssetManager());
        }
        MainRole.getInstance().setUntreatable(true);
    }
}
