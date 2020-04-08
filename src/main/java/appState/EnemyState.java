package appState;

import character.Enemy;
import character.enemy.dragonWat.DarkDragon;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
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
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Quad;

import java.util.ArrayList;

public class EnemyState extends BaseAppState {
    private SimpleApplication app;
    private Node rootNode = new Node("EnemyState");  //主节点
    private ArrayList<Enemy> enemies;
    private ArrayList<Spatial> enemiesModel;
    private ArrayList<BitmapText> hpHints;
    private ArrayList<BitmapText> blockHints;
    private MyRawInputListener myRawInputListener;
    private Geometry chosen;  // 选中的模型
    private Enemy target;   // 选中模型对应的enemy类
    private int targetID; // 选中的enemy类在 数组中的位置,主要用于更新hp和block
    private static EnemyState instance = null;
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
        this.viewPort = app.getViewPort();
        this.camera = app.getCamera();
        this.myRawInputListener = new MyRawInputListener();
        enemies = new ArrayList<Enemy>();
        hpHints = new ArrayList<BitmapText>();
        blockHints = new ArrayList<BitmapText>();
        enemiesModel = new ArrayList<Spatial>();
        addEnemies(
                new DarkDragon(85, "Dragon/dragon.obj", 0, 0, 0, 0, 0, 0, 0, 0),
                new DarkDragon(85, "Dragon2/dragon.obj", 0, 0, 0, 0, 0, 0, 0, 0),
                new DarkDragon(85, "Dragon3/dragon.obj", 0, 0, 0, 0, 0, 0, 0, 0)
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
        BitmapFont fnt = assetManager.loadFont("Interface/Fonts/Default.fnt");
        for (Enemy enemy : enemies) {
            BitmapText hpHint = new BitmapText(fnt, false);
            hpHint.setBox(new Rectangle(3.9f + 2 * (index == 0 ? 0 : (float) Math.pow(-1, index)), 1.5f, 6, 3));
            hpHint.setQueueBucket(RenderQueue.Bucket.Transparent);
            hpHint.setSize(0.3f);
            hpHint.setColor(ColorRGBA.Red);
            hpHint.setText(String.format("HP: %s/%s", enemy.getHP(), enemy.getTotalHP()));
            rootNode.attachChild(hpHint);
            hpHints.add(hpHint);

            BitmapText blockHint = new BitmapText(fnt, false);
            blockHint.setBox(new Rectangle(3.9f + 2 * (index == 0 ? 0 : (float) Math.pow(-1, index)), -1f, 6, 3));
            blockHint.setQueueBucket(RenderQueue.Bucket.Transparent);
            blockHint.setSize(0.3f);
            blockHint.setColor(ColorRGBA.Blue);
            blockHint.setText(String.format("Blocks: %s", enemy.getBlock()));
            rootNode.attachChild(blockHint);
            blockHints.add(blockHint);
            index += 1;
        }
    }

    public void updateHints(boolean isAOE) {
        int index = 0;
        BitmapFont fnt = assetManager.loadFont("Interface/Fonts/Default.fnt");
        if (isAOE) {
            for (Enemy enemy : enemies) {
                hpHints.get(index).removeFromParent();
                BitmapText hpHint = new BitmapText(fnt, false);
                hpHint.setBox(new Rectangle(4 + 2 * (index == 0 ? 0 : (float) Math.pow(-1, index)), 1.5f, 6, 3));
                hpHint.setQueueBucket(RenderQueue.Bucket.Transparent);
                hpHint.setSize(0.3f);
                hpHint.setColor(ColorRGBA.Red);
                hpHint.setText(String.format("HP: %s/%s", enemy.getHP(), enemy.getTotalHP()));
                rootNode.attachChild(hpHint);
                hpHints.set(index, hpHint);

                blockHints.get(index).removeFromParent();
                BitmapText blockHint = new BitmapText(fnt, false);
                blockHint.setBox(new Rectangle(4f + 2 * (index == 0 ? 0 : (float) Math.pow(-1, index)), -1f, 6, 3));
                blockHint.setQueueBucket(RenderQueue.Bucket.Transparent);
                blockHint.setSize(0.3f);
                blockHint.setColor(ColorRGBA.Blue);
                blockHint.setText(String.format("Blocks: %s", enemies.get(index).getBlock()));
                rootNode.attachChild(blockHint);
                blockHints.set(index, blockHint);
                index += 1;
            }
        } else {
            if (targetID != -1) {
                System.out.println("更新单个");
                hpHints.get(targetID).removeFromParent();
                BitmapText hpHint = new BitmapText(fnt, false);
                hpHint.setBox(new Rectangle(4 + 2 * (targetID == 0 ? 0 : (float) Math.pow(-1, targetID)), 1.5f, 6, 3));
                hpHint.setQueueBucket(RenderQueue.Bucket.Transparent);
                hpHint.setSize(0.3f);
                hpHint.setColor(ColorRGBA.Red);
                hpHint.setText(String.format("HP: %s/%s", enemies.get(targetID).getHP(), enemies.get(targetID).getTotalHP()));
                rootNode.attachChild(hpHint);
                hpHints.set(targetID, hpHint);

                blockHints.get(targetID).removeFromParent();
                BitmapText blockHint = new BitmapText(fnt, false);
                blockHint.setBox(new Rectangle(4f + 2 * (targetID == 0 ? 0 : (float) Math.pow(-1, targetID)), -1f, 6, 3));
                blockHint.setQueueBucket(RenderQueue.Bucket.Transparent);
                blockHint.setSize(0.3f);
                blockHint.setColor(ColorRGBA.Blue);
                blockHint.setText(String.format("Blocks: %s", enemies.get(targetID).getBlock()));
                rootNode.attachChild(blockHint);
                blockHints.set(targetID, blockHint);
            }
        }
    }


    public void addEnemies(Enemy... enemies) {
        for (int i = 0; i < enemies.length; i++) {
            this.enemies.add(enemies[i]);
            enemies[i].bindApp(app);
            String src = enemies[i].getSrc();
            Spatial model = this.assetManager.loadModel(src);
            model.setName(src);
            model.scale(0.03f);// 按比例缩小
            model.center();// 将模型的中心移到原点
            model.move(6 + 2 * (i == 0 ? 0 : (float) Math.pow(-1, i)), 0, -1);
            model.rotate(0, -0.9f, 0);//调整y角度可以设置怪物脸的朝向，0为正对屏幕，负数为向左看，正数为向右看
            model.setModelBound(new BoundingSphere());// 使用包围球
            model.updateModelBound();// 更新包围球
            this.enemiesModel.add(model);
            rootNode.attachChild(model);
        }
    }

    private CollisionResults getRootCollision(MouseButtonEvent evt) {
        Vector2f screenCoord = this.inputManager.getCursorPosition();
        Vector3f worldCoord = this.camera.getWorldCoordinates(screenCoord, 1f);

        // 计算方向
        Vector3f dir = worldCoord.subtract(this.camera.getLocation());
        dir.normalizeLocal();
        Ray ray = new Ray();
        ray.setOrigin(this.camera.getLocation());
        ray.setDirection(dir);
        CollisionResults results = new CollisionResults();
        rootNode.collideWith(ray, results);//检测guinode 中所有图形对象 和 ray 的碰撞

        return results;
    }

    private CollisionResults getGuiCollision(MouseMotionEvent evt) {
        Vector2f screenCoord = this.inputManager.getCursorPosition();
        Vector3f worldCoord = this.camera.getWorldCoordinates(screenCoord, 1f);

        // 计算方向
        Vector3f dir = worldCoord.subtract(this.camera.getLocation());
        dir.normalizeLocal();
        Ray ray = new Ray();
        ray.setOrigin(this.camera.getLocation());
        ray.setDirection(dir);
        CollisionResults results = new CollisionResults();
        rootNode.collideWith(ray, results);//检测guinode 中所有图形对象 和 ray 的碰撞

        return results;
    }

    public static EnemyState getInstance() {
        return instance;
    }

    class MyRawInputListener implements RawInputListener {
        Quad q = new Quad(6, 3);
        BitmapFont fnt = assetManager.loadFont("Interface/Fonts/Default.fnt");
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
                Geometry res = results.getClosestCollision().getGeometry();
                if (!res.getName().equals("BitmapFont")) {
                    //遍历敌人数组，确定鼠标选中的是哪一个敌人
                    Enemy targetEnemy = null;
                    for (Enemy enemy : enemies) {
                        if (enemy.getSrc().equals(res.getName())) {
                            targetEnemy = enemy;
                            break;
                        }
                    }
                    buffDisplayBoard.setLocalTranslation(2, 2, -1);
                    Material mt = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                    mt.setColor("Color", ColorRGBA.Red);
                    mt.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
                    buffDisplayBoard.setQueueBucket(RenderQueue.Bucket.Transparent);
                    buffDisplayBoard.setMaterial(mt);
                    rootNode.attachChild(buffDisplayBoard);
                    String txtB = "This character's buff:\n\n";
                    if (targetEnemy != null) {
                        txtB += String.format("   Bleeding: %d      ", targetEnemy.getBleeding().getDuration());
                        txtB += String.format("     Disarm: %d      ", targetEnemy.getDisarm().getDuration());
                        txtB += String.format("      Erode: %d       \n", targetEnemy.getErode().getDuration());
                        txtB += String.format("     Excite: %d      ", targetEnemy.getExcite().getDuration());
                        txtB += String.format(" Intangible: %d      ", targetEnemy.getIntangible().getDuration());
                        txtB += String.format("     Posion: %d       \n", targetEnemy.getPosion().getDuration());
                        txtB += String.format("     Sheild: %d      ", targetEnemy.getSheild().getDuration());
                        txtB += String.format("    Silence: %d      ", targetEnemy.getSilence().getDuration());
                        txtB += String.format("       Stun: %d       \n", targetEnemy.getStun().getDuration());
                        txtB += String.format("Vunlnerable: %d      ", targetEnemy.getVulnerable().getDuration());
                        txtB += String.format("       Weak: %d      ", targetEnemy.getWeak().getDuration());
                        txtB += String.format("   Artifact: %d       \n", targetEnemy.getArtifact().getTimes());
                        txtB += String.format("  Dexterity: %d      ", targetEnemy.getDexterity());
                        txtB += String.format("      Dodge: %d      ", targetEnemy.getDodge().getTimes());
                        txtB += String.format("     Excite: %d      ", targetEnemy.getStrength());

                    }
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
        for(int i = 0 ;i<enemies.size();i++){
            if(enemies.get(i).getHP()<=0){
                enemiesModel.get(i).removeFromParent();
                hpHints.get(i).removeFromParent();
                blockHints.get(i).removeFromParent();
                blockHints.remove(i);
                hpHints.remove(i);
                enemies.remove(i);
                enemiesModel.remove(i);
            }
        }
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