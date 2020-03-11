package character;

import appState.DecksState;
import appState.HandCards;
import card.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;


public class MainRole extends Role {
    //  原本固有的属性
    private int strength_;//力量，提升基础伤害
    private int dexterity_;//灵巧，提升基础获得护甲值

    private int draw_;//抽牌数量


    //玩家的独特属性
    private int MP;
    private int attack;
    private int draw;
    private int potionBag;
    private int gold;

    public ArrayList<Card> deck_;// 原卡组（有序排列）
    public ArrayList<Card> deck;// 战斗中卡组（有序排列）


    public MainRole(int HP, String src) {
        super(HP, src, ROLE.MAINROLE);
        this.strength_ = 0;
        this.dexterity_ = 0;
        this.draw_ = 6;

        this.potionBag = 3;
        this.gold = 0;
        this.deck = new ArrayList<Card>();

    }


    public void startBattle() {
        this.draw = this.draw_;
        this.setStrength(this.strength_);

        this.attack = 0;
        this.setDexterity(this.dexterity_);

        Collections.copy(deck, deck_);
        Collections.shuffle(deck);
        app.getStateManager().getState(DecksState.class).addToDraw(this.deck);


    }

    public void getCard(Card... cards) {
        deck_.addAll(Arrays.asList(cards));
    }

    //每回合开始时候的抽牌
    public void startTurn() {
        app.getStateManager().getState(HandCards.class).drawCards(this.draw);
        if (this.excite.getDuration() > 0) {
            app.getStateManager().getState(HandCards.class).drawCards(1);
        }
    }


    @Override
    public void endTurn() {
        super.endTurn();
    }

    public void drawCards(int num) {
        app.getStateManager().getState(HandCards.class).drawCards(num);
    }


}
