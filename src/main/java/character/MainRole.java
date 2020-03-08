package character;

import card.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;


public class MainRole extends Role {
    //  原本固有的属性
    private int strength_;//力量，提升基础伤害
    private int dexterity_;//灵巧，提升基础获得护甲值
    private int dodge_;//闪避，将x次伤害变成1
    private int artifact_;//人工制品，抵挡下x次debuff
    private int draw_;//抽牌数量
    private int shield_;//回合末获得x层护甲

    //玩家的独特属性
    private int attack;
    private int draw;
    private int potionBag;
    private int gold;

    public List<Card> deck;//卡组（有序排列）

    public List<Card> handCards;//手牌

    public List<Card> drawPile;//抽牌堆（随机顺序）

    public List<Card> dropPile;//弃牌堆

    public List<Card> exhaustPile;//消耗掉的卡牌堆


    public MainRole(int HP, String src) {
        super(HP, src, ROLE.MAINROLE);
        this.strength_ = 0;
        this.dexterity_ = 0;
        this.dodge_ = 0;
        this.artifact_ = 0;
        this.draw_ = 6;
        this.shield_ = 0;
        this.potionBag = 3;
        this.gold = 0;
        this.deck = new ArrayList<Card>();
        this.handCards = new ArrayList<Card>();
        this.drawPile = new ArrayList<Card>();
        this.dropPile = new ArrayList<Card>();
        this.exhaustPile = new ArrayList<Card>();
    }


    public void startBattle() {
        this.draw = this.draw_;
        this.strength = this.strength_;
        this.dodge = this.dodge_;
        this.artifact = this.artifact_;
        this.attack = 0;
        this.dexterity = this.dexterity_;
        this.shield = this.shield_;

        Collections.copy(drawPile, deck);
        Collections.shuffle(drawPile);
    }

    public void getCard(Card... cards) {
        deck.addAll(Arrays.asList(cards));
    }


    public void incHP(int num) {
        this.totalHP += num;
    }


    public void drawCards() {

    }

    public void drawCards(int num) {

    }

    public int getDraw() {
        return draw;
    }


    public void setDraw(int num){
        this.draw = num;
    }
    public void incDraw(int num) {
        this.draw += num;
    }

    public void decDraw(int num) {
        this.draw -= num;
    }



}
