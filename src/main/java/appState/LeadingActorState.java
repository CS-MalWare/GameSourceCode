package appState;

import character.MainRole;
import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.collision.CollisionResults;
import com.jme3.input.InputManager;
import com.jme3.input.RawInputListener;
import com.jme3.input.event.*;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

import java.util.ArrayList;

public class LeadingActorState extends BaseAppState {
    private AnimControl animControl;
    private AnimChannel animChannel;
    private SimpleApplication app;
    private Node rootNode = new Node("LeadingActorState");
    private ArrayList<MainRole> actors;
    private RawInputListener myRawInputListener;
    private Geometry chosen;
    private MainRole target;

    private Spatial model1;


    protected void initialize(Application application) {
        this.app = (SimpleApplication) getApplication();
        this.myRawInputListener = new MyRawInputListener();
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

        actors = new ArrayList<>();
        app.getInputManager().addRawInputListener(myRawInputListener);
        rootNode.attachChild(model1);
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

    class MyRawInputListener implements RawInputListener {


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


        }

        public void onMouseButtonEvent(MouseButtonEvent evt) {
            //如果是鼠标按下去
            if (evt.isPressed()) {

                //获得当前鼠标选中的位置
                CollisionResults guiResults = getRootCollision(evt);
                if (guiResults.size() > 0) {
                    // 获得离射线原点最近的交点所在的图片
                    Geometry res = guiResults.getClosestCollision().getGeometry();
                    System.out.println(res.getName());
                } else {
                    chosen = null;
                }

            } else if (evt.isReleased()) {
                CollisionResults guiResults = getRootCollision(evt);
                if (guiResults.size() > 0) {
                    // 获得离射线原点最近的交点所在的图片
                    Geometry res = guiResults.getClosestCollision().getGeometry();
                    chosen = res;
                    for (MainRole x : actors) {
                        if (x.getSrc().equals(res.getName())) {
                            target = x;
                        }
                    }
                } else {
                    chosen = null;
                    target = null;
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


    public Geometry getChosen() {
        return chosen;
    }

    @Override
    protected void cleanup(Application application) {

    }

    @Override
    protected void onEnable() {
        app.getRootNode().attachChild(this.rootNode);

    }

    @Override
    protected void onDisable() {
        this.rootNode.removeFromParent();
        this.actors.clear();
        this.target = null;
        this.chosen = null;
    }
}
