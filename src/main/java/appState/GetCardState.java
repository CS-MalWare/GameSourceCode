package appState;

import card.Card;
import card.CreateCard;
import character.MainRole;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.asset.AssetNotFoundException;
import com.jme3.collision.CollisionResults;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.input.InputManager;
import com.jme3.input.RawInputListener;
import com.jme3.input.event.*;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;

import java.util.ArrayList;

import static card.Card.OCCUPATION.NEUTRAL;
import static card.Card.OCCUPATION.SABER;

public class GetCardState extends BaseAppState {
    private SimpleApplication app;
    private Node rootNode = new Node("GetCardState");
    private AssetManager assetManager;
    private AppStateManager stateManager;
    private InputManager inputManager;
    private ViewPort viewPort;
    private Camera camera;
    private ArrayList<Card> card;
    private MyRawInputListener mril;

    @Override
    protected void initialize(Application application) {
        this.app = (SimpleApplication) getApplication();
        this.assetManager = app.getAssetManager();
        this.stateManager = app.getStateManager();
        this.inputManager = app.getInputManager();
        this.viewPort = app.getViewPort();
        this.camera = app.getCamera();
        mril = new MyRawInputListener();
        this.card = new ArrayList<Card>() {{
            add(CreateCard.getRandomCard(Math.random()<0.7?SABER:NEUTRAL));
            add(CreateCard.getRandomCard(Math.random()<0.7?SABER:NEUTRAL));
            add(CreateCard.getRandomCard(Math.random()<0.7?SABER:NEUTRAL));
        }};
        BitmapFont fnt = app.getAssetManager().loadFont("Interface/Fonts/Default.fnt");
        BitmapText word = new BitmapText(fnt, false);//显示的文字
        word.setText("Please choose one card. You can use the card in continue battles");
        word.setSize(0.2f);
//        word.setColor(ColorRGBA.Black);
        word.setLocalTranslation(-2.5f, -2.5f, 0);
        Material mat = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", new ColorRGBA(1f, 1f, 1f, 0.1f));// 镜面反射时，高光的颜色。

        // 应用材质。
        Geometry geom = new Geometry("选卡界面", new Quad(1600, 900));
        geom.setMaterial(mat);

        // 使物体看起来透明
        mat.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
        geom.setQueueBucket(RenderQueue.Bucket.Transparent);

        geom.center();

        try {// 因为懒惰的逸润巨佬卡牌没有做完会爆错，所以这里有个TRY-CATCH

            int count = 0;
            for (Card card : card) {
                // 每个卡都有0.1的概率直接获得升级版本
                if(Math.random()<0.1)
                    card.upgrade();
                card.setImage(app.getAssetManager());//将卡牌添加进Assetmanager
                card.setLocalTranslation(400 + (count++) * 300f, (float) (HandCardsState.height - HandCardsState.cardHeight) / 2, 1);
                rootNode.attachChild(card);

            }
        } catch (AssetNotFoundException e) {
            System.out.println("逸润巨佬快加上这个卡：" + e.getMessage());
        }
        rootNode.attachChild(word);

        rootNode.attachChild(geom);


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

    private void enlargeCard(Card img, Card closest) {
        closest.setWidth((float) (HandCardsState.cardWidth * 1.25));
        closest.setHeight((float) (HandCardsState.cardHeight * 1.25));
        Vector3f location = closest.getLocalTranslation();
        closest.setLocalTranslation(location.x, location.y, 1);//通过竖坐标增加来使得图片在前显示
        //放大这个离鼠标最近的图片

        if (img != null) {
            img.setWidth((float) HandCardsState.cardWidth);
            img.setHeight((float) HandCardsState.cardHeight);
            location = img.getLocalTranslation();
            img.setLocalTranslation(location.x, location.y, 0);//图片还原
        }
    }

    private void recoverCard(Card img) {
        img.setWidth((float) HandCardsState.cardWidth);
        img.setHeight((float) HandCardsState.cardHeight);
        Vector3f location = img.getLocalTranslation();
        img.setLocalTranslation(location.x, location.y, 0);
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


        Ray ray = new Ray(new Vector3f(x, y, 10), dir);
        CollisionResults results = new CollisionResults();
        rootNode.collideWith(ray, results);//检测guinode 中所有图形对象 和 ray 的碰撞

        return results;
    }

    class MyRawInputListener implements RawInputListener {
        Card last = CreateCard.createCard("null", Card.TYPE.ATTACK);//上次划过的图片
        Card center = CreateCard.createCard("null", Card.TYPE.ATTACK);//屏幕中心的图片

        /**
         * 键盘输入事件
         */
        @Override
        public void onKeyEvent(KeyInputEvent evt) {
        }

        /**
         * 鼠标输入事件
         */
        @Override
        public void onMouseMotionEvent(MouseMotionEvent evt) {
            CollisionResults results = getGuiCollision(evt);

            if (results.size() > 0) {
                // 获得离射线原点最近的交点所在的图片
                Geometry res = results.getClosestCollision().getGeometry();
                Card closest;

                if (res instanceof Card) {
                    closest = (Card) res;
                } else {
                    return;
                }

                if (last != closest) {
                    enlargeCard(last, closest);//放大选中图片
                    last = closest;
                }
            } else {
                // 使卡牌恢复原状
                if (last != null) {
                    recoverCard(last);
                    last = CreateCard.createCard("null", Card.TYPE.ATTACK);
                }
            }

        }

        public void onMouseButtonEvent(MouseButtonEvent evt) {
            //如果是鼠标按下去

            if (evt.isPressed()) {
//                System.out.println(evt.getX());
//                System.out.println(evt.getY());
                //获得当前鼠标选中的位置
                CollisionResults guiResults = getGuiCollision(evt);
                if (guiResults.size() > 0) {
                    // 获得离射线原点最近的交点所在的图片
                    Geometry res = guiResults.getClosestCollision().getGeometry();

                    if (res instanceof Card) {
//                        System.out.println((Card)res);
                        MainRole.getInstance().getCard((Card) res);
                        res.removeFromParent();
                        // 完成操作，删除这个 state
                        finish();
                    }

                }
            }
        }

        @Override
        public void beginInput() {
        }

        @Override
        public void endInput() {
        }


        public void onJoyAxisEvent(JoyAxisEvent evt) {
        }

        public void onJoyButtonEvent(JoyButtonEvent evt) {
        }

        public void onTouchEvent(TouchEvent evt) {
        }
    }

    // 完成卡牌选择
    public void finish() {
        this.onDisable();
    }

    @Override
    protected void cleanup(Application application) {

    }

    @Override
    protected void onEnable() {
        app.getRootNode().attachChild(this.rootNode);
        app.getInputManager().addRawInputListener(mril);
    }

    @Override
    protected void onDisable() {
        this.rootNode.removeFromParent();
        app.getInputManager().removeRawInputListener(mril);
    }
}
