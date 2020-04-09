package character;

import appState.DecksState;
import appState.HandCardsState;
import card.Card;
import card.neutral.attack.HookBoxing;
import card.neutral.attack.KickDown;
import card.neutral.attack.SoulSacrifice;
import card.neutral.attack.Whirlpool;
import card.neutral.skill.Crouch;
import card.neutral.skill.Intelligent;
import card.neutral.skill.Winding;
import card.saber.attack.FireSlash;
import card.saber.attack.IceSlash;
import card.saber.power.ManaBoost;
import card.saber.skill.GrindingSword;
import card.saber.skill.IceMagicShield;
import card.saber.skill.RaiseShield;
import card.saber.skill.WhirlingShield;
import org.codehaus.groovy.tools.shell.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class MainRole extends Role {
    private static MainRole instance = null;


    //  原本固有的属性
    private int strength_;//力量，提升基础伤害
    private int dexterity_;//灵巧，提升基础获得护甲值

    private int draw_;// 原本抽牌数量

    private boolean keepCard = false;

    //玩家的独特属性
    private int MP_max;
    private int MP_current;

    private int draw; // 战斗中的抽牌数量
    private int potionBag;
    private int gold;

    private ArrayList<Card> deck_ = new ArrayList<Card>();// 原卡组（有序排列）
    public ArrayList<Card> deck;// 战斗中卡组（无序排列）


    public static MainRole getInstance() {
        if (instance == null) {
            System.out.println("初始化主角类");
            instance = new MainRole(85, "LeadingActor/MajorActor4.j3o");
        }
        return instance;
    }


    public MainRole(int HP, String src) {
        super(HP, src, ROLE.MAINROLE);

        this.strength_ = 0;
        this.dexterity_ = 0;
        this.draw_ = 5;
        this.potionBag = 3;
        this.gold = 0;
        this.deck = new ArrayList<Card>();
        this.MP_max = 6;
        this.MP_current = 6;

        this.deck_.add(new FireSlash());
        this.deck_.add(new Intelligent());
        this.deck_.add(new IceSlash());
        this.deck_.add(new GrindingSword());
        this.deck_.add(new RaiseShield());
        this.deck_.add(new IceMagicShield());
        this.deck_.add(new WhirlingShield());
        this.deck_.add(new Whirlpool());
        this.deck_.add(new ManaBoost());
        this.deck_.add(new KickDown());
        this.deck_.add(new SoulSacrifice());
        this.deck_.add(new Winding());
        this.deck_.add(new Crouch());
        this.deck_.add(new HookBoxing());
    }


    public void startBattle() {

        this.setStrength(this.strength_);

        this.setDexterity(this.dexterity_);

        // 将卡组复制一份,因为战斗中可能会修改卡组
        deck = new ArrayList<Card>(Arrays.asList(new Card[deck_.size()]));
        Collections.copy(deck, deck_);
        Collections.shuffle(deck);
        //将卡组加入抽牌堆
        app.getStateManager().getState(DecksState.class).addToDraw(this.deck);
        this.draw = this.draw_;
        this.MP_current = this.MP_max;
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
        if (this.excite.getDuration() > 0) {
            this.drawCards(1 + this.draw, true);
        } else {
            this.drawCards(this.draw, true);
        }
        this.MP_current = this.MP_max;

    }





    public int getMP_current() {
        return MP_current;
    }

    public void setMP_current(int MP_current) {
        this.MP_current = MP_current;
    }

    public void gainMP(int num) {
        this.MP_current = Math.min(this.MP_current + num, this.MP_max);
    }

    public boolean decMP(int num) {
        if (num > MP_current) {
            return false;
        } else {
            this.MP_current -= num;
            return true;
        }
    }


    @Override
    public void endTurn() {
        super.endTurn();
        if (this.keepCard) {
            return;
        } else {
            DecksState deckState = app.getStateManager().getState(DecksState.class);
            HandCardsState handCardsState = app.getStateManager().getState(HandCardsState.class);
            deckState.addToDrop(handCardsState.getHandCards().toArray(new Card[0]));
            handCardsState.getHandCards().clear();
        }

    }


    public int getMP_max() {
        return MP_max;
    }

    public int getGold() {
        return gold;
    }

    public void getGold(int num) {
        this.gold += num;
    }

    public void drawCards(int num) {

        this.app.getStateManager().getState(HandCardsState.class).drawCards(num, false);
    }

    public void drawCards(int num, boolean withAdjust) {
        this.app.getStateManager().getState(HandCardsState.class).drawCards(num, withAdjust);
    }


    public void setKeepCard(boolean keepCard) {
        this.keepCard = keepCard;
    }
}
