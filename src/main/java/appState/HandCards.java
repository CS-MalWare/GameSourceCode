package appState;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.collision.CollisionResults;
import com.jme3.input.RawInputListener;
import com.jme3.input.event.*;
import com.jme3.math.FastMath;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.ui.Picture;

import java.util.ArrayList;
import java.util.List;

public class HandCards extends BaseAppState {
    private SimpleApplication app;
    //    private double height = app.getCamera().getHeight();
//    private double width = app.getCamera().getWidth();
    private double height = 900; //屏幕高度
    private double width = 1600;  // 屏幕宽度


    private float ratio = (float) (width / 1600.0);  //缩放比例

    private double top = -25 * ratio;  //第一张牌距离屏幕高度
    private double verticalDiff_left = 22 * ratio;  //竖向位移
    private double verticalDiff_right = 15 * ratio;
    private double horizontalDiff= 120 * ratio; //横向位移

    public double cardWidth = 200 * ratio;  //卡片宽度
    public double cardHeight = 260 * ratio;  //卡片高度

    private float rotateRate_left = FastMath.PI / 180 * 3;   //旋转速率
    private float rotateRate_right = -FastMath.PI / 180 * 4;

    private Node rootNode = new Node("HandCards");  //主节点

    private double[][][] positions = new double[20][20][3]; //存放每张牌的位置


    private MyRawInputListener cardListener;


    // 事先计算每张牌的位置
    protected double[][] computePosition(int num) {
        if (num == 0) return null;
        double[][] result = new double[num][3];
        double horizontalDiff = num < 10 ? this.horizontalDiff : this.horizontalDiff * 10.0 / num;
        double verticalDiff_right = num < 10 ? this.verticalDiff_right : this.verticalDiff_right * 10.0 / num;
        double verticalDiff_left = num < 10 ? this.verticalDiff_left : this.verticalDiff_left * 10.0 / num;
        double rotateRate_left = num < 10 ? this.rotateRate_left : this.rotateRate_left * 10.0 / num;
        double rotateRate_right = num < 10 ? this.rotateRate_right : this.rotateRate_right * 10.0 / num;
        double centerPosition = (width - cardWidth) / 2.0;
        if (num % 2 == 0) {
            int center1 = num / 2 - 1;
            int center2 = num / 2;
            result[center1] = new double[]{centerPosition - horizontalDiff / 2, top, 0};
            result[center2] = new double[]{centerPosition + horizontalDiff / 2, top, 0};
            for (int i = 0; i <= center1; i++) {
                result[center1 - i] = new double[]{centerPosition - horizontalDiff / 2 - horizontalDiff * i, top - i * verticalDiff_left, rotateRate_left * i};
                result[center2 + i] = new double[]{centerPosition + horizontalDiff / 2 + horizontalDiff * i, top - i * verticalDiff_right, rotateRate_right * i};
            }
        } else {
            int center = num / 2;
            result[center] = new double[]{centerPosition, top,  0};
            for (int i = 0; i <= center; i++) {
                result[center - i] = new double[]{centerPosition - i * horizontalDiff, top - i * verticalDiff_left, rotateRate_left * i};
                result[center + i] = new double[]{centerPosition + i * horizontalDiff, top - i * verticalDiff_right, rotateRate_right * i};
            }
        }
        return result;
    }

    // 初始化卡片
    protected Picture newCard(@org.jetbrains.annotations.NotNull String path) {
        String[] paths = path.split("/");
        String name = paths[paths.length - 1];
        Picture card = new Picture(path);
        card.setHeight((float) cardHeight);
        card.setWidth((float) cardWidth);
        card.setImage(app.getAssetManager(), path, true);
        return card;
    }

    @Override
    protected void initialize(Application app) {
        this.app = (SimpleApplication) getApplication();
        this.cardListener = new MyRawInputListener();
        for (int i = 0; i < 20; i++) this.positions[i] = this.computePosition(i);

        List<Picture> cards = new ArrayList<Picture>();
        cards.add(newCard("Cards/caster/attack/冰雷破(+).png"));
        cards.add(newCard("Cards/caster/attack/双龙炼狱.png"));
        cards.add(newCard("Cards/caster/attack/奥数冲击(+).png"));
        cards.add(newCard("Cards/caster/power/思维窃取(+).png"));


        int i = 0;
        int length = cards.size();
        for (Picture card : cards) {
            double x = this.positions[length][i][0];
            double y = this.positions[length][i][1];
            double angle = this.positions[length][i][2];
            card.setPosition((float) x, (float) y);
            card.rotate(0, 0, (float) angle);
            rootNode.attachChild(card);
            i++;
        }


    }


    @Override
    protected void cleanup(Application app) {

    }

    @Override
    protected void onEnable() {
        app.getGuiNode().attachChild(this.rootNode);
        app.getInputManager().addRawInputListener(cardListener);
    }

    @Override
    protected void onDisable() {
        this.rootNode.removeFromParent();
        app.getInputManager().removeRawInputListener(cardListener);
    }


    class MyRawInputListener implements RawInputListener {
        Picture last = new Picture("null");
        Picture center = new Picture("null");
        /**
         * 键盘输入事件
         */
        @Override
        public void onKeyEvent(KeyInputEvent evt) {
            int keyCode = evt.getKeyCode();
            boolean isPressed = evt.isPressed();

        }

        /**
         * 鼠标输入事件
         */
        @Override
        public void onMouseMotionEvent(MouseMotionEvent evt) {
            int x = evt.getX();
            int y = evt.getY();
            Camera cam = app.getCamera();
            Vector2f screenCoord = new Vector2f(x, y);
            Vector3f worldCoord = cam.getWorldCoordinates(screenCoord, 1f);
            Vector3f worldCoord2 = cam.getWorldCoordinates(screenCoord, 0f);

// 然后计算视线方向
            Vector3f dir = worldCoord.subtract(worldCoord2);
            dir.normalizeLocal();

// 生成射线
            Node guiNode = app.getGuiNode();

            Ray ray = new Ray(new Vector3f(x, y, 100), dir);
            CollisionResults results = new CollisionResults();
            guiNode.collideWith(ray, results);

            if (results.size() > 0) {
                // 获得离射线原点最近的交点
                Picture closest = (Picture) (results.getClosestCollision().getGeometry());

                // 使鼠标放上去的卡牌放大,并且放置在屏幕中央
                if (last != closest) {
                    closest.setWidth((float) (cardWidth *1.25));
                    closest.setHeight((float) (cardHeight *1.25));
                    Vector3f location = closest.getLocalTranslation();
                    closest.setLocalTranslation(location.x,location.y,1);

                    last.setWidth((float) cardWidth);
                    last.setHeight((float) cardHeight);
                    location =last.getLocalTranslation();
                    last.setLocalTranslation(location.x,location.y,0);
                    last = closest;
                    center.removeFromParent();
                    center =newCard(closest.getName());
                    center.setPosition((float) ((width-cardWidth *1.5)/2.0),  (float) ((height-cardHeight)/2.0));
                    center.setWidth((float) (cardWidth *1.5));
                    center.setHeight((float) (cardHeight *1.5));

                    guiNode.attachChild(center);
                }
            } else {
                // 使卡牌恢复原状
                last.setWidth((float) cardWidth);
                last.setHeight((float) cardHeight);
                Vector3f location = last.getLocalTranslation();
                last.setLocalTranslation(location.x,location.y,0);
                last = new Picture("null");
                center.removeFromParent();
            }


        }

        public void onMouseButtonEvent(MouseButtonEvent evt) {
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

    public static void main(String[] args) {
        HandCards a = new HandCards();
        double[][] result = a.computePosition(5);
        for (double[] x : result) {
            for (double y : x) {
                System.out.print(y + "  ");
            }
            System.out.println();
        }
    }
}
