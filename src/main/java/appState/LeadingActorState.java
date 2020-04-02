package appState;

import character.Enemy;
import character.LeadingActor;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.collision.CollisionResults;
import com.jme3.input.InputManager;
import com.jme3.input.RawInputListener;
import com.jme3.input.event.*;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
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
    private SimpleApplication app;
    private Node rootNode = new Node("LeadingActorState");
    private ArrayList<LeadingActor> actors;
    private RawInputListener myRawInputListener;
    private Geometry chosen;
    private LeadingActor target;


    protected void initialize(Application application) {
        this.app = (SimpleApplication) getApplication();
        this.myRawInputListener = new MyRawInputListener();
        Spatial model1 = application.getAssetManager().loadModel("LeadingActor/untitled.obj");
        System.out.println(model1.getName());
        model1.setName("LeadingActor/untitled.obj");
        model1.scale(0.03f);// 按比例缩小
        model1.center();// 将模型的中心移到原点
        model1.move(-7, 0, -3);
        model1.rotate(0, 0.8f, 0);

        model1.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);

        actors = new ArrayList<>();



        /*DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(2, 1, -3));

        // 环境光
        AmbientLight ambient = new AmbientLight();

        // 调整光照亮度
        ColorRGBA lightColor = new ColorRGBA();
        sun.setColor(lightColor.mult(0.8f));
        ambient.setColor(lightColor.mult(1));
        app.getInputManager().addRawInputListener(myRawInputListener);
        // #3 将模型和光源添加到场景图中
        rootNode.addLight(sun);
        rootNode.addLight(ambient);*/
        rootNode.attachChild(model1);
    }

    public void addActor(LeadingActor... actors) {
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
                    for (LeadingActor x : actors) {
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
