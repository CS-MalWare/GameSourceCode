package appState;

import card.Card;
import character.Enemy;
import character.MainRole;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.audio.AudioData;
import com.jme3.audio.AudioNode;
import com.jme3.collision.CollisionResults;
import com.jme3.input.RawInputListener;
import com.jme3.input.event.*;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.ui.Picture;
import equipment.Equipment;
import truetypefont.TrueTypeFont;
import truetypefont.TrueTypeKey;
import truetypefont.TrueTypeLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class DecksState extends BaseAppState {
    private static DecksState instance = null;

    private Picture drawDeckPic;
    private Picture exhaustDeckPic;
    private Picture dropDeckPic;
    private SimpleApplication app;
    private Node rootNode;

    private ArrayList<AudioNode> audioNodes;
    private int drawNum = 10;
    private int dropNum = 10;
    private int exhaustNum = 1;
    private Geometry drawText;
    private Geometry dropText;
    private Geometry exhaustText;
    public final static int PLAIN = 0;// 普通
    public final static int BOLD = 1;// 粗体
    public final static int ITALIC = 2;// 斜体

    public final static int FONT_SIZE = 20;

    private int[] positionX = new int[]{95, 1431, 1471}; //抽牌,弃牌,消耗堆的顺序
    private int[] positionY = new int[]{35, 35, 160};


    private ArrayList<Card> drawDeck = new ArrayList<Card>(); //抽牌堆
    private ArrayList<Card> exhaustDeck = new ArrayList<Card>(); // 消耗堆
    private ArrayList<Card> dropDeck = new ArrayList<Card>();  // 弃牌堆

    private ArrayList<Card> showDeck = new ArrayList<Card>();

    TrueTypeFont font;

    private MyInputListener myActionListener;

    public final static String CLICK = "CLICK";

    private Picture endTurn;

//    BitmapFont fnt;

    public static DecksState getInstance() {
        return instance;
    }

    @Override
    protected void initialize(Application app) {
        rootNode = new Node("DeckNode");
        this.app = (SimpleApplication) getApplication();
        drawDeckPic = new Picture("抽牌堆");
        drawDeckPic.setImage(app.getAssetManager(), "Util/抽牌堆.png", true);
        exhaustDeckPic = new Picture("消耗堆");
        exhaustDeckPic.setImage(app.getAssetManager(), "Util/消耗堆.png", true);
        dropDeckPic = new Picture("弃牌堆");
        dropDeckPic.setImage(app.getAssetManager(), "Util/弃牌堆.png", true);
        endTurn = new Picture("结束回合");
        endTurn.setImage(app.getAssetManager(), "Util/结束.png", true);


        drawDeckPic.setPosition(30, 20);
        dropDeckPic.setPosition(1430, 20);
        exhaustDeckPic.setPosition(1430, 130);
        endTurn.setPosition(1220, 220);

        drawDeckPic.setHeight(100);
        drawDeckPic.setWidth(100);
        dropDeckPic.setHeight(100);
        dropDeckPic.setWidth(100);
        exhaustDeckPic.setHeight(100);
        exhaustDeckPic.setWidth(100);
        endTurn.setWidth(200);
        endTurn.setHeight(80);

        rootNode.attachChild(drawDeckPic);
        rootNode.attachChild(dropDeckPic);
        rootNode.attachChild(exhaustDeckPic);
        rootNode.attachChild(endTurn);

//        fnt = app.getAssetManager().loadFont("Interface/Fonts/Default.fnt");

        this.app.getAssetManager().registerLoader(TrueTypeLoader.class, "ttf");

        // 创建字体 (例如：楷书)
        TrueTypeKey ttk = new TrueTypeKey("Util/font.ttf", // 字体
                BOLD, // 字形：0 普通、1 粗体、2 斜体
                FONT_SIZE);// 字号

        font = (TrueTypeFont) this.app.getAssetManager().loadAsset(ttk);


        // 创建文字
        drawText = font.getBitmapGeom(drawNum + "", 0, ColorRGBA.White);
        drawText.setLocalTranslation(positionX[0], positionY[0], 1);
        rootNode.attachChild(drawText);

        dropText = font.getBitmapGeom(dropNum + "", 0, ColorRGBA.White);
        dropText.setLocalTranslation(positionX[1], positionY[1], 1);
        rootNode.attachChild(dropText);

        exhaustText = font.getBitmapGeom(exhaustNum + "", 0, ColorRGBA.White);
        exhaustText.setLocalTranslation(positionX[2], positionY[2], 1);
        rootNode.attachChild(exhaustText);

//        drawDeck.add(new ConeFlame(true));
//        drawDeck.add(new ConeGold());
//        drawDeck.add(new Whirlpool());
//        drawDeck.add(new TheKissOfDeath());
//
//        dropDeck.add(new Slash());
//        dropDeck.add(new SoilSlash());
//
//        exhaustDeck.add(new LightSlash());


        myActionListener = new MyInputListener();
        instance = this;

        // 设置音效
        audioNodes = new ArrayList<AudioNode>() {{
            add(new AudioNode(app.getAssetManager(), "Sound/Attack/史莱姆攻击.wav", AudioData.DataType.Buffer));
            add(new AudioNode(app.getAssetManager(), "Sound/Skill/史莱姆技能.wav", AudioData.DataType.Buffer));
            add(new AudioNode(app.getAssetManager(), "Sound/Dead/史莱姆死亡.wav", AudioData.DataType.Buffer));
            add(new AudioNode(app.getAssetManager(), "Sound/Attack/狼人攻击.wav", AudioData.DataType.Buffer));
            add(new AudioNode(app.getAssetManager(), "Sound/Skill/狼人技能.wav", AudioData.DataType.Buffer));
            add(new AudioNode(app.getAssetManager(), "Sound/Dead/狼人死亡.wav", AudioData.DataType.Buffer));
            add(new AudioNode(app.getAssetManager(), "Sound/Attack/机器人攻击.wav", AudioData.DataType.Buffer));
            add(new AudioNode(app.getAssetManager(), "Sound/Skill/机器人技能.wav", AudioData.DataType.Buffer));
            add(new AudioNode(app.getAssetManager(), "Sound/Dead/机器人死亡.wav", AudioData.DataType.Buffer));
            add(new AudioNode(app.getAssetManager(), "Sound/Attack/龙攻击.wav", AudioData.DataType.Buffer));
            add(new AudioNode(app.getAssetManager(), "Sound/Skill/龙技能.wav", AudioData.DataType.Buffer));
            add(new AudioNode(app.getAssetManager(), "Sound/Dead/龙死亡.wav", AudioData.DataType.Buffer));
        }};
        System.out.println(audioNodes.size());
        for (AudioNode an : audioNodes) {
            an.setLooping(false);
            an.setPositional(false);
            an.setVolume(2);
            rootNode.attachChild(an);
        }
    }

    public void setImages(SimpleApplication app) {
        for (Card card : drawDeck) {
            card.setImage(app.getAssetManager());
        }

        for (Card card : dropDeck) {
            card.setImage(app.getAssetManager());
        }
        for (Card card : exhaustDeck) {
            card.setImage(app.getAssetManager());
        }
        this.updateNum();
    }

    @Override
    protected void cleanup(Application app) {
        this.drawDeck.clear();
        this.dropDeck.clear();
        this.exhaustDeck.clear();
    }

    @Override
    protected void onEnable() {

        app.getGuiNode().attachChild(this.rootNode);
        app.getInputManager().addRawInputListener(myActionListener);
        MainRole.getInstance().startBattle();
        int count = 0;
        for (Equipment equipment : MainRole.getInstance().getEquipments()) {
            int x = count % 20;
            int y = count / 20;
            equipment.setPosition(70 + x * 70, 830 - y * 100);
            rootNode.attachChild(equipment);
            count++;
        }
        this.setImages(app);
        for (Enemy enemy : EnemyState.getInstance().getEnemies()) {
            enemy.updateHints();
        }
        MainRole.getInstance().startTurn();
    }

    @Override
    protected void onDisable() {
        this.rootNode.removeFromParent();

        for (Equipment equipment : MainRole.getInstance().getEquipments()) {
            equipment.removeFromParent();
        }
        app.getInputManager().removeRawInputListener(myActionListener);
    }


    public int getDrawNum() {
        return drawNum;
    }

    public void updateDrawNum() {
        this.drawNum = drawDeck.size();
        this.drawText.removeFromParent();
        this.drawText = font.getBitmapGeom(drawNum + "", 0, ColorRGBA.White);
        if (drawNum >= 10) {
            this.drawText.setLocalTranslation(positionX[0], positionY[0], 2);
        } else {
            this.drawText.setLocalTranslation(positionX[0] + 4, positionY[0], 2);
        }
        this.rootNode.attachChild(drawText);
    }

    public int getDropNum() {
        return dropNum;

    }

    public void updateDropNum() {
        this.dropNum = dropDeck.size();
        this.dropText.removeFromParent();
        this.dropText = font.getBitmapGeom(dropNum + "", 0, ColorRGBA.White);
        if (dropNum >= 10) {
            this.dropText.setLocalTranslation(positionX[1], positionY[1], 2);
        } else {
            this.dropText.setLocalTranslation(positionX[1] + 5, positionY[1], 2);
        }
        this.rootNode.attachChild(dropText);
    }

    public int getExhaustNum() {
        return exhaustNum;
    }

    public void updateExhaustNum() {
        this.exhaustNum = exhaustDeck.size();
        this.exhaustText.removeFromParent();
        this.exhaustText = font.getBitmapGeom(exhaustNum + "", 0, ColorRGBA.White);
        if (dropNum < 10) {
            this.exhaustText.setLocalTranslation(positionX[2], positionY[2], 2);
        } else {
            this.exhaustText.setLocalTranslation(positionX[2] - 3, positionY[2], 2);
        }
        this.rootNode.attachChild(exhaustText);
    }


    public void updateNum() {
        this.updateDrawNum();
        this.updateDropNum();
        this.updateExhaustNum();
    }

    // 抽卡
    public ArrayList<Card> drawCard(int num) {
        int drawNum = drawDeck.size();
        ArrayList<Card> draws = new ArrayList<Card>();
        // 抽牌堆中的牌还没抽光的时候
        if (drawNum >= num) {
            for (int i = 0; i < num; i++) {
                draws.add(drawDeck.remove(0));
            }
        } else { //先洗牌再抽
            // 先抽光手牌堆中的牌
            for (int i = 0; i < drawNum; i++) {
                draws.add(drawDeck.remove(0));
            }
            // 将弃牌堆中的牌加入抽牌堆

            drawDeck.addAll(dropDeck);
            dropDeck.clear();

//            for(int i=0;i<dropDeck.size();i++){
//                drawDeck.add(dropDeck.remove(0));
//            }
//            System.out.println(i);
            Collections.shuffle(drawDeck);
            for (int i = 0; i < num - drawNum; i++) {
                if (drawDeck.size() > 0)
                    draws.add(drawDeck.remove(0));
            }
        }
        this.updateNum();
        return draws;
    }

    // 随机加入抽牌堆
    public void addToDraw(Card... cards) {
        if (cards.length == 1) {
            Random random = new Random();
            int pos = random.nextInt(drawDeck.size());
            cards[0].reset();
            this.drawDeck.add(pos, cards[0]);
        } else {
            for (Card card : cards) {
                card.reset();
                drawDeck.add(card);
                card.removeFromParent();
            }
        }
        this.updateDrawNum();
    }


    public void addToDraw(ArrayList<Card> cards) {

        for (Card card : cards) {
            card.reset();
            drawDeck.add(card);
            card.removeFromParent();
        }

        this.updateDrawNum();
    }


    public void addToExhaust(Card... cards) {
        for (Card card : cards) {
            card.reset();
            exhaustDeck.add(card);
            card.removeFromParent();
        }
        this.updateExhaustNum();
    }

    public void addToDrop(Card... cards) {
        for (Card card : cards) {
            card.reset();
            dropDeck.add(card);
            card.removeFromParent();
        }
        this.updateDropNum();
    }


    public ArrayList<Card> getDrawDeck() {
        return drawDeck;
    }

    public ArrayList<Card> getExhaustDeck() {
        return exhaustDeck;
    }

    public ArrayList<Card> getDropDeck() {
        return dropDeck;
    }

    public void showCards(ArrayList<Card> cards) {
        this.hideCards();
        for (int index = 0; index < cards.size(); index++) {
            int i = index / 8 + 1; // 1-行号
            int j = index % 8 + 1; // 1-6
            int x = 100 + (j - 1) * 180;
            int y = 900 - i * 200;
            Card card = cards.get(index).clone(true);
            card.setImage(app.getAssetManager(), card.getPath(), true);
            card.setWidth(150);
            card.setHeight(200);
            card.setPosition(x, y);
            rootNode.attachChild(card);
            showDeck.add(card);
        }
    }

    public void hideCards() {
        for (Card card : this.showDeck) {
            card.removeFromParent();
        }
        this.showDeck.clear();
    }

    private CollisionResults getGuiCollision(MouseButtonEvent evt) {
        int x = evt.getX();//得到鼠标的横坐标
        int y = evt.getY();//得到鼠标的纵坐标
        Camera cam = app.getCamera();//获得摄像机引用
        Vector2f screenCoord = new Vector2f(x, y);
        Vector3f worldCoord = cam.getWorldCoordinates(screenCoord, 1f);
        Vector3f worldCoord2 = cam.getWorldCoordinates(screenCoord, 0f);
        //通过得到鼠标位置，生成一个二维向量，然后通过设定不同的竖坐标，获得应该的视线方向
// 然后计算视线方向
        Vector3f dir = worldCoord.subtract(worldCoord2);
        dir.normalizeLocal();//获得该方向单位向量

// 生成射线
//        Node guiNode = app.getGuiNode();//GUInode 包含了所有图形对象

        Ray ray = new Ray(new Vector3f(x, y, 10), dir);
        CollisionResults results = new CollisionResults();
        rootNode.collideWith(ray, results);//检测guinode 中所有图形对象 和 ray 的碰撞

        return results;
    }


    private CollisionResults getGuiCollision(MouseMotionEvent evt) {
        int x = evt.getX();//得到鼠标的横坐标
        int y = evt.getY();//得到鼠标的纵坐标
        Camera cam = app.getCamera();//获得摄像机引用
        Vector2f screenCoord = new Vector2f(x, y);
        Vector3f worldCoord = cam.getWorldCoordinates(screenCoord, 1f);
        Vector3f worldCoord2 = cam.getWorldCoordinates(screenCoord, 0f);
        //通过得到鼠标位置，生成一个二维向量，然后通过设定不同的竖坐标，获得应该的视线方向
// 然后计算视线方向
        Vector3f dir = worldCoord.subtract(worldCoord2);
        dir.normalizeLocal();//获得该方向单位向量

// 生成射线
//        Node guiNode = app.getGuiNode();//GUInode 包含了所有图形对象

        Ray ray = new Ray(new Vector3f(x, y, 10), dir);
        CollisionResults results = new CollisionResults();
        rootNode.collideWith(ray, results);//检测guinode 中所有图形对象 和 ray 的碰撞

        return results;
    }


    class MyInputListener implements RawInputListener {
        private Boolean isDrawDeckShow = false;
        private Boolean isDropDeckShow = false;
        private Boolean isExhuastDeckShow = false;
        private String enemyActionAudio = "";
        TrueTypeKey ttk = new TrueTypeKey("Util/font.ttf", // 字体
                PLAIN, // 字形：0 普通、1 粗体、2 斜体
                FONT_SIZE);// 字号
        TrueTypeFont font = (TrueTypeFont) app.getAssetManager().loadAsset(ttk);

        private Geometry text;

        @Override
        public void beginInput() {

        }

        @Override
        public void endInput() {

        }

        @Override
        public void onJoyAxisEvent(JoyAxisEvent evt) {

        }

        @Override
        public void onJoyButtonEvent(JoyButtonEvent evt) {

        }

        @Override
        public void onMouseMotionEvent(MouseMotionEvent evt) {
            CollisionResults guiResults = getGuiCollision(evt);
            if (guiResults.size() > 0) {
                Geometry res = guiResults.getClosestCollision().getGeometry();
                if (res instanceof Equipment) {
                    if (text != null) {
                        text.removeFromParent();
                    }
                    text = font.getBitmapGeom(String.format("%s", ((Equipment) res).getDescription()), 0, ColorRGBA.Red);
                    text.setLocalTranslation(50, 800, 1);
                    rootNode.attachChild(text);

                }
            } else {
                if (text != null)
                    text.removeFromParent();
            }
        }

        @Override
        public void onMouseButtonEvent(MouseButtonEvent evt) {
            if (evt.isPressed()) {
                CollisionResults guiResults = getGuiCollision(evt);
                if (guiResults.size() > 0) {
                    Geometry res = guiResults.getClosestCollision().getGeometry();

                    switch (res.getName()) {
                        case "抽牌堆":
                            if (!isDrawDeckShow) showCards(drawDeck);
                            else hideCards();
                            isDrawDeckShow = !isDrawDeckShow;
                            isDropDeckShow = false;
                            isExhuastDeckShow = false;
                            break;
                        case "弃牌堆":
                            if (!isDropDeckShow) showCards(dropDeck);
                            else hideCards();
                            isDropDeckShow = !isDropDeckShow;
                            isDrawDeckShow = false;
                            isExhuastDeckShow = false;
                            break;
                        case "消耗堆":
                            if (!isExhuastDeckShow) showCards(exhaustDeck);
                            else hideCards();
                            isExhuastDeckShow = !isExhuastDeckShow;
                            isDrawDeckShow = false;
                            isDropDeckShow = false;
                            break;
                        case "结束回合":

                            MainRole.getInstance().endTurn();
                            app.getStateManager().getState(LeadingActorState.class).updateHints();

                            ArrayList<Enemy> enemies = app.getStateManager().getState(EnemyState.class).getEnemies();
                            // 所有敌人开始行动
                            for (Enemy enemy : enemies) {
                                enemy.startTurn();
                                enemyActionAudio = enemy.enemyAction();
                                switch (enemyActionAudio){
                                    case "slime attack":
                                        audioNodes.get(0).playInstance();
                                        break;
                                    case "slime skill":
                                        audioNodes.get(1).playInstance();
                                        break;
                                    case "wolfman attack":
                                        audioNodes.get(3).playInstance();
                                        break;
                                    case "wolfman skill":
                                        audioNodes.get(4).playInstance();
                                        break;
                                    case "robot attack":
                                        audioNodes.get(6).playInstance();
                                        break;
                                    case "robot skill":
                                        audioNodes.get(7).playInstance();
                                        break;
                                    case "dragon attack":
                                        audioNodes.get(9).playInstance();
                                        break;
                                    case "dragon skill":
                                        audioNodes.get(10).playInstance();
                                        break;
                                    default:
                                        break;
                                }
                                app.getStateManager().getState(EnemyState.class).updateHints(true);
                                app.getStateManager().getState(LeadingActorState.class).updateHints();
                                try {
                                    Thread.sleep(300);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            // 停顿一下,假装在加载（逸润巨佬还是高明）
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            // 敌人行动完后,主角可以行动
                            MainRole.getInstance().startTurn();
                            // 将本回合内中已打出的牌清0
                            app.getStateManager().getState(HandCardsState.class).setCardUsedCount(0);
                            app.getStateManager().getState(LeadingActorState.class).updateHints();
                            break;
                        default:
                            isExhuastDeckShow = false;
                            isDrawDeckShow = false;
                            isDropDeckShow = false;
                            hideCards();
                    }
                    if (res == drawText) {
                        if (!isDrawDeckShow) showCards(drawDeck);
                        else hideCards();
                        isDrawDeckShow = !isDrawDeckShow;
                        isDropDeckShow = false;
                        isExhuastDeckShow = false;
                    } else if (res == dropText) {
                        if (!isDropDeckShow) showCards(dropDeck);
                        else hideCards();
                        isDropDeckShow = !isDropDeckShow;
                        isDrawDeckShow = false;
                        isExhuastDeckShow = false;
                    } else if (res == exhaustText) {
                        if (!isExhuastDeckShow) showCards(exhaustDeck);
                        else hideCards();
                        isExhuastDeckShow = !isExhuastDeckShow;
                        isDrawDeckShow = false;
                        isDropDeckShow = false;
                    }
                } else {
//                    System.out.println("nothing");
                    isExhuastDeckShow = false;
                    isDrawDeckShow = false;
                    isDropDeckShow = false;
                    hideCards();
                }
            }
        }

        @Override
        public void onKeyEvent(KeyInputEvent evt) {

        }

        @Override
        public void onTouchEvent(TouchEvent evt) {

        }
    }


}
