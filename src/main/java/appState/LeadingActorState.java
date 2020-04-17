package appState;

import character.Enemy;
import character.MainRole;
import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.bounding.BoundingSphere;
import com.jme3.collision.CollisionResults;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.font.Rectangle;
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
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Quad;
import com.jme3.ui.Picture;
import org.ejml.alg.generic.GenericMatrixOps;
import org.lwjgl.Sys;
import truetypefont.TrueTypeFont;
import truetypefont.TrueTypeKey;

import java.util.ArrayList;

public class LeadingActorState extends BaseAppState {
    private AnimControl animControl;
    private AnimChannel animChannel;
    private SimpleApplication app;
    private Node rootNode = new Node("LeadingActorState");
    private Node guiNode = new Node("MP");
    private ArrayList<MainRole> actors;
    private RawInputListener myRawInputListener;
    private Geometry chosen;
    private MainRole target;

    private Picture MpPic;
    private Geometry MpText;

    private Spatial model1;


    BitmapFont fnt;

    TrueTypeKey ttk;

    TrueTypeFont font;

    BitmapText hpHint; // 显示的hp

    BitmapText blHint;  // 显示的护甲

    protected void initialize(Application application) {
        this.app = (SimpleApplication) getApplication();
        this.myRawInputListener = new MyRawInputListener();
        this.target = MainRole.getInstance();
        model1 = app.getAssetManager().loadModel("LeadingActor/MajorActor4.j3o");
        //model1.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);

        Node scene = (Node) model1;

        Node bip001 = (Node) scene.getChild("Bip001");


        //spatial.getChild("AnimControl");


        animControl = bip001.getControl(AnimControl.class);

        //AnimControl control = (AnimControl)spatial.getControl(0);

        System.out.println(animControl.getAnimationNames() + "zzzzzzz");

        animChannel = animControl.createChannel();

        animChannel.setAnim("walk");
        System.out.println(model1.getName());
        model1.setName("LeadingActor/leader.j3o");
        model1.scale(0.03f);// 按比例缩小
        model1.center();// 将模型的中心移到原点
        model1.move(-6, 0.5f, -5);
        model1.rotate(-1.5f, 1.3f, 0);

        model1.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
        model1.setModelBound(new BoundingSphere());// 使用包围球
        model1.updateModelBound();// 更新包围球

        actors = new ArrayList<>();
        app.getInputManager().addRawInputListener(myRawInputListener);
        rootNode.attachChild(model1);

        fnt = app.getAssetManager().loadFont("Interface/Fonts/Default.fnt");

        ttk = new TrueTypeKey("Util/font.ttf", // 字体
                1, // 字形：0 普通、1 粗体、2 斜体
                30);// 字号

        font = (TrueTypeFont) this.app.getAssetManager().loadAsset(ttk);

        initializeHints();
    }

    public void initializeHints() {
        hpHint = new BitmapText(fnt, false);
        hpHint.setBox(new Rectangle(-3.9f, 2.5f, 6, 3));
        hpHint.setQueueBucket(RenderQueue.Bucket.Transparent);
        hpHint.setSize(0.3f);
        hpHint.setColor(ColorRGBA.Red);
        hpHint.setText(String.format("HP: %d/%d", this.target.getHP(), this.target.getTotalHP()));
        rootNode.attachChild(hpHint);

        blHint = new BitmapText(fnt, false);
        blHint.setBox(new Rectangle(-3.9f, 0, 6, 3));
        blHint.setQueueBucket(RenderQueue.Bucket.Transparent);
        blHint.setSize(0.3f);
        blHint.setColor(ColorRGBA.Blue);
        blHint.setText(String.format("Blocks: %d", this.target.getBlock()));
        rootNode.attachChild(blHint);

        // 添加MP显示的图片与文字
        MpPic = new Picture("MP");
        MpPic.setImage(app.getAssetManager(), "Util/MP.png", true);
        MpPic.setHeight(120);
        MpPic.setWidth(120);
        MpPic.setPosition(220, 220);
        guiNode.attachChild(MpPic);


        // 创建MP文字
        MpText = font.getBitmapGeom(String.format("%d/%d", MainRole.getInstance().getMP_current(), MainRole.getInstance().getMP_max()), 0, ColorRGBA.Red);
        MpText.setLocalTranslation(250, 270, 1);
        guiNode.attachChild(MpText);

    }

    public void updateHints() {
        hpHint.removeFromParent();
        hpHint = new BitmapText(fnt, false);
        hpHint.setBox(new Rectangle(-3.9f, 2.5f, 6, 3));
        hpHint.setQueueBucket(RenderQueue.Bucket.Transparent);
        hpHint.setSize(0.3f);
        hpHint.setColor(ColorRGBA.Red);
        hpHint.setText(String.format("HP: %d/%d", this.target.getHP(), this.target.getTotalHP()));
        rootNode.attachChild(hpHint);

        blHint.removeFromParent();
        blHint = new BitmapText(fnt, false);
        blHint.setBox(new Rectangle(-3.9f, 0, 6, 3));
        blHint.setQueueBucket(RenderQueue.Bucket.Transparent);
        blHint.setSize(0.3f);
        blHint.setColor(ColorRGBA.Blue);
        blHint.setText(String.format("Blocks: %d", this.target.getBlock()));
        rootNode.attachChild(blHint);

        MpText.removeFromParent();
        MpText = font.getBitmapGeom(String.format("%d/%d", MainRole.getInstance().getMP_current(), MainRole.getInstance().getMP_max()), 0, ColorRGBA.Red);
        MpText.setLocalTranslation(250, 270, 1);
        guiNode.attachChild(MpText);

    }

    public void addActor(MainRole... actors) {
        for (int i = 0; i < actors.length; i++) {
            this.actors.add(actors[i]);
            String src = actors[i].getSrc();
            Spatial model = this.app.getAssetManager().loadModel(src);
            model.setName(src);
            model.scale(0.03f);// 按比例缩小
            model.center();// 将模型的中心移到原点
            model.move(7 + 3 * i, 5 * i, -3);
            model.rotate(0, -1f, 0);
        }
    }

    private CollisionResults getRootCollision(MouseButtonEvent evt) {
        int x = evt.getX();//得到鼠标的横坐标
        int y = evt.getY();//得到鼠标的纵坐标

        InputManager inputManager = app.getInputManager();
        Camera cam = app.getCamera();
        Vector2f screenCoord = inputManager.getCursorPosition();
        Vector3f worldCoord = cam.getWorldCoordinates(screenCoord, 1f);

        // 计算方向
        Vector3f dir = worldCoord.subtract(cam.getLocation());
        dir.normalizeLocal();
        Ray ray = new Ray();
        ray.setOrigin(cam.getLocation());
        ray.setDirection(dir);
        CollisionResults results = new CollisionResults();
        rootNode.collideWith(ray, results);//rootNode 中所有图形对象 和 ray 的碰撞

        return results;
    }

    private CollisionResults getGuiCollision(MouseMotionEvent evt) {
        Vector2f screenCoord = this.app.getInputManager().getCursorPosition();
        Vector3f worldCoord = this.app.getCamera().getWorldCoordinates(screenCoord, 1f);

        // 计算方向
        Vector3f dir = worldCoord.subtract(this.app.getCamera().getLocation());
        dir.normalizeLocal();
        Ray ray = new Ray();
        ray.setOrigin(this.app.getCamera().getLocation());
        ray.setDirection(dir);
        CollisionResults results = new CollisionResults();
        model1.getWorldBound().collideWith(ray, results);//检测guinode 中所有图形对象 和 ray 的碰撞
        return results;
    }

    class MyRawInputListener implements RawInputListener {
        Quad q = new Quad(6, 3);
        BitmapFont fnt = app.getAssetManager().loadFont("Interface/Fonts/Default.fnt");
        private BitmapText buffDisplay = new BitmapText(fnt, false);//显示的文字
        private Geometry buffDisplayBoard = new Geometry("quad", q);//文字后面的版


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
                if (target != null) {
                    buffDisplayBoard.setLocalTranslation(2, 2, -1);
                    Material mt = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
                    mt.setColor("Color", ColorRGBA.Red);
                    mt.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
                    buffDisplayBoard.setQueueBucket(RenderQueue.Bucket.Transparent);
                    buffDisplayBoard.setMaterial(mt);
                    rootNode.attachChild(buffDisplayBoard);
                    String txtB = "This character's buff:\n";
                    txtB += String.format("   Strength: %d      ", target.getStrength());
                    txtB += String.format("  Dexterity: %d       \n", target.getDexterity());
                    txtB += String.format("   Bleeding: %d      ", target.getBleeding().getDuration());
                    txtB += String.format("     Disarm: %d      ", target.getDisarm().getDuration());
                    txtB += String.format("      Erode: %d       \n", target.getErode().getDuration());
                    txtB += String.format("     Excite: %d      ", target.getExcite().getDuration());
                    txtB += String.format(" Intangible: %d      ", target.getIntangible().getDuration());
                    txtB += String.format("     Poison: %d       \n", target.getPoison().getDuration());
                    txtB += String.format("     Sheild: %d      ", target.getSheild().getDuration());
                    txtB += String.format("    Silence: %d      ", target.getSilence().getDuration());
                    txtB += String.format("       Stun: %d       \n", target.getStun().getDuration());
                    txtB += String.format("Vunlnerable: %d      ", target.getVulnerable().getDuration());
                    txtB += String.format("       Weak: %d      ", target.getWeak().getDuration());
                    txtB += String.format("   Artifact: %d       \n", target.getArtifact().getTimes());
                    txtB += String.format("  Dexterity: %d      ", target.getDexterity());
                    txtB += String.format("      Dodge: %d      ", target.getDodge().getTimes());
                    txtB += String.format("     Excite: %d      ", target.getStrength());
                    buffDisplay.setBox(new Rectangle(2, 4, 6, 3));
                    buffDisplay.setQueueBucket(RenderQueue.Bucket.Transparent);
                    buffDisplay.setSize(0.25f);
                    buffDisplay.setText(txtB);
                    rootNode.attachChild(buffDisplay);
                }
            } else {
//                if (buffDisplay != null)
                buffDisplay.removeFromParent();
//                if (buffDisplayBoard != null)
                buffDisplayBoard.removeFromParent();
            }


        }

        public void onMouseButtonEvent(MouseButtonEvent evt) {
            //如果是鼠标按下去

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


    public Geometry getChosen() {
        return chosen;
    }

    @Override
    protected void cleanup(Application application) {

    }

    @Override
    protected void onEnable() {
        app.getRootNode().attachChild(this.rootNode);
        app.getGuiNode().attachChild(this.guiNode);
    }

    @Override
    public void update(float tpf) {
        super.update(tpf);
        if (this.target.getHP() <= 0) {
            BitmapFont fnt = app.getAssetManager().loadFont("Interface/Fonts/Default.fnt");
            BitmapText word = new BitmapText(fnt, false);//显示的文字
            word.setText("Game Over");
            word.setSize(1);
            word.setLocalTranslation(-2.5f,0.5f,0);

            Material mat = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
            mat.setColor("Color", new ColorRGBA(1f, 1f, 1f, 0.01f));// 镜面反射时，高光的颜色。

            // 应用材质。
            Geometry geom = new Geometry("结束界面", new Quad(1600, 900));
            geom.setMaterial(mat);

            // 使物体看起来透明
            mat.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
            geom.setQueueBucket(RenderQueue.Bucket.Transparent);

            geom.center();
            rootNode.attachChild(word);
            rootNode.attachChild(geom);
        }

    }

    @Override
    protected void onDisable() {
        this.rootNode.removeFromParent();
        this.guiNode.removeFromParent();
        this.actors.clear();
        this.target = null;
        this.chosen = null;
    }
}
