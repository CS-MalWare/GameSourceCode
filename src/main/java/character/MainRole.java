package character;

import appState.DecksState;
import appState.HandCardsState;
import card.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class MainRole extends Role {
    private static MainRole instance = null;


    //  原本固有的属性
    private int strength_;//力量，提升基础伤害
    private int dexterity_;//灵巧，提升基础获得护甲值

    private int draw_;//抽牌数量

    private boolean keepCard = false;

    //玩家的独特属性
    private int MP_max;
    private int MP_current;
    private int attack;
    private int draw;
    private int potionBag;
    private int gold;

    public ArrayList<Card> deck_;// 原卡组（有序排列）
    public ArrayList<Card> deck;// 战斗中卡组（有序排列）


    public static MainRole getInstance() {
        return instance;
    }


    public MainRole(int HP, String src) {
        super(HP, src, ROLE.MAINROLE);
        this.strength_ = 0;
        this.dexterity_ = 0;
        this.draw_ = 6;

        this.potionBag = 3;
        this.gold = 0;
        this.deck = new ArrayList<Card>();
        instance = this;

    }


    public void startBattle() {

        this.setStrength(this.strength_);

        this.attack = 0;
        this.setDexterity(this.dexterity_);

        // 将卡组复制一份,因为战斗中可能会修改卡组
        Collections.copy(deck, deck_);
        Collections.shuffle(deck);
        //将卡组加入抽牌堆
        app.getStateManager().getState(DecksState.class).addToDraw(this.deck);


    }

    public void getCard(Card... cards) {
        deck_.addAll(Arrays.asList(cards));
    }

    //每回合开始时候的抽牌
    public void startTurn() {
        if (this.stun.getDuration() > 0) {
            this.endTurn();
            return;
        }
        this.keepCard = false;
        app.getStateManager().getState(HandCardsState.class).drawCards(this.draw);
        if (this.excite.getDuration() > 0) {
            app.getStateManager().getState(HandCardsState.class).drawCards(1);
        }
    }


    public boolean useCard(Card card, Enemy... enemy) {
        if (card.getCost() > this.MP_current) {
            return false;
        }

        if (!card.use(enemy)) return false;

        this.MP_current -= card.getCost();
        return true;
    }

    public void getMP(int num) {
        this.MP_current = Math.min(this.MP_current + num, this.MP_max);
    }


    @Override
    public void endTurn() {
        super.endTurn();
        if (this.keepCard) {
            return;
        } else {
            DecksState deckState = app.getStateManager().getState(DecksState.class);
            HandCardsState handCardsState = app.getStateManager().getState(HandCardsState.class);
            deckState.addToDraw(handCardsState.getHandCards());
        }

    }


    public void getGold(int num) {
        this.gold += num;
    }

    public void drawCards(int num) {
        app.getStateManager().getState(HandCardsState.class).drawCards(num);
    }


    public void setKeepCard(boolean keepCard) {
        this.keepCard = keepCard;
    }
}
