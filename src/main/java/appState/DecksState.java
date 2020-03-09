package appState;

import card.Card;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.input.MouseInput;
import com.jme3.input.RawInputListener;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.InputListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.input.event.*;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.ui.Picture;
import org.lwjgl.Sys;
import truetypefont.TrueTypeFont;
import truetypefont.TrueTypeKey;
import truetypefont.TrueTypeLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class DecksState extends BaseAppState {
    private Picture drawDeckPic;
    private Picture exhaustDeckPic;
    private Picture dropDeckPic;
    private SimpleApplication app;
    private Node rootNode;

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


        drawDeckPic.setPosition(30, 20);
        dropDeckPic.setPosition(1430, 20);
        exhaustDeckPic.setPosition(1430, 130);
        drawDeckPic.setHeight(100);
        drawDeckPic.setWidth(100);
        dropDeckPic.setHeight(100);
        dropDeckPic.setWidth(100);
        exhaustDeckPic.setHeight(100);
        exhaustDeckPic.setWidth(100);
        rootNode.attachChild(drawDeckPic);
        rootNode.attachChild(dropDeckPic);
        rootNode.attachChild(exhaustDeckPic);


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

        drawDeck.add(new Card("Cards/caster/attack/流星雨.png"));
        drawDeck.add(new Card("Cards/caster/attack/炎爆.png"));
        drawDeck.add(new Card("Cards/caster/attack/破碎虚空(+).png"));
        drawDeck.add(new Card("Cards/caster/attack/魔法吸血(+).png"));

        dropDeck.add(new Card("Cards/caster/power/思维窃取(+).png"));
        dropDeck.add(new Card("Cards/caster/power/超杀回复(+).png"));

        exhaustDeck.add(new Card("Cards/caster/skill/寒冰护甲(+).png"));

        this.updateNum();
        myActionListener = new MyInputListener();

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
    }

    @Override
    protected void onDisable() {
        this.rootNode.removeFromParent();
        app.getInputManager().removeRawInputListener(myActionListener);
    }


    public int getDrawNum() {
        return drawNum;
    }

    public void setDrawNum() {
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

    public void setDropNum() {
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

    public void setExhaustNum() {
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
        this.setDrawNum();
        this.setDropNum();
        this.setExhaustNum();
    }

    // 抽卡
    public ArrayList<Card> drawCard(int num) {
        int drawNum = drawDeck.size();
        ArrayList<Card> draws = new ArrayList<Card>();
        if (drawNum >= num) {
            for (int i = 0; i < num; i++) {
                draws.add(drawDeck.remove(0));
            }
        } else { //先洗牌再抽
            for (int i = 0; i < drawNum; i++) {
                draws.add(drawDeck.remove(0));
            }
            for (int i = 0; i < dropDeck.size(); i++) {
                drawDeck.add(dropDeck.remove(0));
            }
            Collections.shuffle(drawDeck);
            for (int i = 0; i < num - drawNum; i++) {
                draws.add(drawDeck.remove(0));
            }
        }
        this.updateNum();
        return draws;
    }

    // 随机加入抽牌堆
    public void addToDraw(Card card) {
        Random random = new Random();
        int pos = random.nextInt(drawDeck.size());
        this.drawDeck.add(pos, card);
    }

    public void addToExhaust(Card card) {
        this.exhaustDeck.add(card);
    }

    public void addToDrop(Card card) {
        this.dropDeck.add(card);
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

    class MyInputListener implements RawInputListener {


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

        }

        @Override
        public void onMouseButtonEvent(MouseButtonEvent evt) {
            if (evt.isPressed()) {
                CollisionResults guiResults = getGuiCollision(evt);
                if (guiResults.size() > 0) {
                    Geometry res = guiResults.getClosestCollision().getGeometry();
                    System.out.println(res.getName());
                    switch (res.getName()) {
                        case "抽牌堆":
                            showCards(drawDeck);
                            break;
                        case "弃牌堆":
                            showCards(dropDeck);
                            break;
                        case "消耗堆":
                            showCards(exhaustDeck);
                            break;
                        default:
                    }
                } else {
                    System.out.println("nothing");
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
