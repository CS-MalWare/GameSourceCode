package appState;

import character.Enemy;
import character.enemy.dragonWat.DarkDragon;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.collision.CollisionResults;
import com.jme3.input.InputManager;
import com.jme3.input.RawInputListener;
import com.jme3.input.event.*;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import org.lwjgl.Sys;
import truetypefont.TrueTypeFont;
import truetypefont.TrueTypeKey;
import truetypefont.TrueTypeLoader;

import java.util.ArrayList;

public class EnemyState extends BaseAppState {
    private SimpleApplication app;
    private Node rootNode = new Node("EnemyState");  //主节点
    private ArrayList<Enemy> enemies;
    private ArrayList<Geometry> hpHints;
    private ArrayList<Geometry> blockHints;
    private MyRawInputListener myRawInputListener;
    private Geometry chosen;  // 选中的模型
    private Enemy target;   // 选中模型对应的enemy类
    private int targetID; // 选中的enemy类在 数组中的位置,主要用于更新hp和block
    private static EnemyState instance = null;
    TrueTypeFont font;
    private SimpleApplication simpleApp;
    private AssetManager assetManager;
    private AppStateManager stateManager;
    private InputManager inputManager;
    private ViewPort viewPort;
    private Camera camera;

    @Override
    protected void initialize(Application application) {
        this.app = (SimpleApplication) getApplication();
        this.assetManager = app.getAssetManager();
        this.stateManager = app.getStateManager();
        this.inputManager = app.getInputManager();
        this.viewPort     = app.getViewPort();
        this.camera       = app.getCamera();
        this.myRawInputListener = new MyRawInputListener();
        enemies = new ArrayList<Enemy>();
        hpHints = new ArrayList<Geometry>();
        blockHints = new ArrayList<Geometry>();
        this.assetManager.registerLoader(TrueTypeLoader.class, "ttf");
        TrueTypeKey ttk = new TrueTypeKey("Util/font.ttf", // 字体
                1, // 字形：0 普通、1 粗体、2 斜体
                20);// 字号
        font = (TrueTypeFont) this.assetManager.loadAsset(ttk);
        addEnemies(
                new DarkDragon(85, "Dragon/dragon.obj", 0, 0, 0, 0, 0, 0, 0, 0),
                new DarkDragon(85, "Dragon/dragon.obj", 0, 0, 0, 0, 0, 0, 0, 0),
                new DarkDragon(85, "Dragon/dragon.obj", 0, 0, 0, 0, 0, 0, 0, 0)
        );
        initializeHints();
        this.inputManager.addRawInputListener(myRawInputListener);
        instance = this;
        chosen = null;
        target = null;
        targetID = -1;
    }

    //加载的时候渲染护甲和血量的值
    public void initializeHints() {
        int index = 0;
        //为每个敌人添加血量
        for (Enemy enemy : enemies) {
            Geometry hpHint = font.getBitmapGeom(String.format("%s/%s", enemy.getHP(), enemy.getTotalHP()), 0, ColorRGBA.Red);
            hpHint.scale(0.015f);
            hpHint.setLocalTranslation(4 + 1.85f*(index==0?0:(float)Math.pow(-1,index)), 1.5f, 0);
            hpHints.add(hpHint);
            rootNode.attachChild(hpHint);
            Geometry blockHint = font.getBitmapGeom(String.format("%s", enemy.getBlock()), 0, ColorRGBA.Blue);
            blockHint.scale(0.015f);
            blockHint.setLocalTranslation(4.5f + 1.85f*(index==0?0:(float)Math.pow(-1,index)), -1.5f, 0);
            blockHints.add(blockHint);
            rootNode.attachChild(blockHint);
            index += 1;
        }
    }

    public void updateHints(boolean isAOE) {
        int index = 0;
        if (isAOE) {
            System.out.println("更新多个");
            for (Enemy enemy : enemies) {
                hpHints.get(index).removeFromParent();
                Geometry hpHint = font.getBitmapGeom(String.format("%s/%s", enemy.getHP(), enemy.getTotalHP()), 0, ColorRGBA.Red);
                hpHint.scale(0.015f);
                hpHint.setLocalTranslation(4 + 1.85f * (index == 0 ? 0 : (float) Math.pow(-1, index)), 1.5f, 0);
                rootNode.attachChild(hpHint);
                hpHints.set(index, hpHint);

                blockHints.get(index).removeFromParent();
                Geometry blockHint = font.getBitmapGeom(String.format("%s", enemy.getBlock()), 0, ColorRGBA.Blue);
                blockHint.scale(0.015f);
                blockHint.setLocalTranslation(4.5f + 1.85f*(index==0?0:(float)Math.pow(-1,index)), -1.5f, 0);
                rootNode.attachChild(blockHint);
                blockHints.set(index, blockHint);
                index += 1;
            }
        }
        else {
            if (targetID != -1) {
                System.out.println("更新单个");
                hpHints.get(targetID).removeFromParent();

                Geometry hpHint = font.getBitmapGeom(String.format("%s/%s", enemies.get(targetID).getHP(), enemies.get(targetID).getTotalHP()), 0, ColorRGBA.Red);
                hpHint.scale(0.015f);
                hpHint.setLocalTranslation(4 + 1.85f*(index==0?0:(float)Math.pow(-1,index)), 1.5f, 0);
                rootNode.attachChild(hpHint);
                hpHints.set(targetID, hpHint);

                blockHints.get(targetID).removeFromParent();
                Geometry blockHint = font.getBitmapGeom(String.format("%s", enemies.get(targetID).getBlock()), 0, ColorRGBA.Blue);
                blockHint.scale(0.015f);
                blockHint.setLocalTranslation(4.5f + 1.85f*(index==0?0:(float)Math.pow(-1,index)), -1.5f, 0);
                rootNode.attachChild(blockHint);
                blockHints.set(targetID, blockHint);
            }
        }
    }


    public void addEnemies(Enemy... enemies) {
        for (int i = 0; i < enemies.length; i++) {
            this.enemies.add(enemies[i]);
            String src = enemies[i].getSrc();
            Spatial model = this.assetManager.loadModel(src);
            model.setName(src);
            model.scale(0.03f);// 按比例缩小
            model.center();// 将模型的中心移到原点
            model.move(6 + 2*(i==0?0:(float)Math.pow(-1,i)), 0, -1);
            model.rotate(0, -0.9f, 0);//调整y角度可以设置怪物脸的朝向，0为正对屏幕，负数为向左看，正数为向右看
            rootNode.attachChild(model);
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


    public static EnemyState getInstance() {
        return instance;
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

        @Override
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
                    target = null;
                    targetID = -1;
                }

            } else if (evt.isReleased()) {
                CollisionResults guiResults = getRootCollision(evt);
                if (guiResults.size() > 0) {
                    // 获得离射线原点最近的交点所在的图片
                    Geometry res = guiResults.getClosestCollision().getGeometry();
                    chosen = res;
                    targetID = 0;
                    for (Enemy x : enemies) {
                        if (x.getSrc().equals(res.getName())) {
                            target = x;
                            break;
                        }
                        targetID++;
                    }
                } else {
                    chosen = null;
                    target = null;
                    targetID = -1;
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


    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public Enemy getTarget() {
        return target;
    }

    public Geometry getChosen() {
        return chosen;
    }

    @Override
    public void update(float tpf) {
        super.update(tpf);
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
        this.enemies.clear();
        this.target = null;
        this.chosen = null;
    }
}