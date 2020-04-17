package equipment.common;

import card.Card;
import character.MainRole;
import equipment.Equipment;

public class KnifeStone extends Equipment {
    public KnifeStone() {
        super("knife stone", "磨刀石", "After picking up this, upgrade three attack cards randomly", EquipmentDegree.COMMON, Opportunity.GET);
    }

    @Override
    public void fun() {
        int i = 0;
        int size = MainRole.getInstance().getDeck_().size();
        while (i < 3) {
            Card card = MainRole.getInstance().getDeck_().get((int) (size * Math.random()));
            if (
                    card.getType() == Card.TYPE.ATTACK
                            && !card.isUpgraded()
            ) {
                card.upgrade();
                i++;
            }
        }

    }
}
