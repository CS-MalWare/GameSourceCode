package appState;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.ui.Picture;
import truetypefont.TrueTypeFont;
import truetypefont.TrueTypeKey;
import truetypefont.TrueTypeLoader;


public class DecksState extends BaseAppState {
    private Picture drawDeck;
    private Picture exhaustDeck;
    private Picture dropDeck;
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

    private int[] positionX = new int[]{95, 1430, 1472}; //抽牌,弃牌,消耗堆的顺序
    private int[] positionY = new int[]{35, 35, 160};


    TrueTypeFont font;

    @Override
    protected void initialize(Application app) {
        rootNode = new Node("DeckNode");
        this.app = (SimpleApplication) getApplication();
        drawDeck = new Picture("抽牌堆");
        drawDeck.setImage(app.getAssetManager(), "Util/抽牌堆.png", true);
        exhaustDeck = new Picture("消耗堆");
        exhaustDeck.setImage(app.getAssetManager(), "Util/消耗堆.png", true);
        dropDeck = new Picture("弃牌堆");
        dropDeck.setImage(app.getAssetManager(), "Util/弃牌堆.png", true);


        drawDeck.setPosition(30, 20);
        dropDeck.setPosition(1430, 20);
        exhaustDeck.setPosition(1430, 130);
        drawDeck.setHeight(100);
        drawDeck.setWidth(100);
        dropDeck.setHeight(100);
        dropDeck.setWidth(100);
        exhaustDeck.setHeight(100);
        exhaustDeck.setWidth(100);
        rootNode.attachChild(drawDeck);
        rootNode.attachChild(dropDeck);
        rootNode.attachChild(exhaustDeck);


        this.app.getAssetManager().registerLoader(TrueTypeLoader.class, "ttf");

        // 创建字体 (例如：楷书)
        TrueTypeKey ttk = new TrueTypeKey("Util/font.ttf", // 字体
                BOLD, // 字形：0 普通、1 粗体、2 斜体
                FONT_SIZE);// 字号

        font = (TrueTypeFont) this.app.getAssetManager().loadAsset(ttk);


        // 创建文字
        Geometry drawText = font.getBitmapGeom(drawNum + "", 0, ColorRGBA.White);
        drawText.setLocalTranslation(positionX[0], positionY[0], 10);
        rootNode.attachChild(drawText);

        Geometry dropText = font.getBitmapGeom(dropNum + "", 0, ColorRGBA.White);
        dropText.setLocalTranslation(positionX[1], positionY[1], 10);
        rootNode.attachChild(dropText);

        Geometry exhaustText = font.getBitmapGeom(exhaustNum + "", 0, ColorRGBA.White);
        exhaustText.setLocalTranslation(positionX[2], positionY[2], 10);
        rootNode.attachChild(exhaustText);
    }

    @Override
    protected void cleanup(Application app) {

    }

    @Override
    protected void onEnable() {

        app.getGuiNode().attachChild(this.rootNode);
    }

    @Override
    protected void onDisable() {
        this.rootNode.removeFromParent();
    }


    public int getDrawNum() {
        return drawNum;
    }

    public void setDrawNum(int drawNum) {
        this.drawNum = drawNum;
        this.drawText = font.getBitmapGeom(drawNum + "", 0, ColorRGBA.White);
    }

    public int getDropNum() {
        return dropNum;

    }

    public void setDropNum(int dropNum) {
        this.dropNum = dropNum;
        this.drawText = font.getBitmapGeom(dropNum + "", 0, ColorRGBA.White);
    }

    public int getExhaustNum() {
        return exhaustNum;
    }

    public void setExhaustNum(int exhaustNum) {
        this.exhaustNum = exhaustNum;
        this.drawText = font.getBitmapGeom(drawNum + "", 0, ColorRGBA.White);
    }
}
