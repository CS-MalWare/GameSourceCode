package equipment.epic;

import appState.HandCardsState;
import card.Card;
import character.MainRole;
import equipment.Equipment;

import java.util.ArrayList;

public class WalletOfGSW extends Equipment {
    private boolean work;

    public WalletOfGSW() {
        super("Wallet Of GSW", "高士味的钱包", "You can get 20 more gold coins in each battle, and at most 300 gold coins in total. This effect will not work after entering the store", EquipmentDegree.EPIC, Opportunity.ENDB);
        work = true;
    }

    @Override
    public void fun() {
        if (work)
            MainRole.getInstance().getGold(20);

    }
}
