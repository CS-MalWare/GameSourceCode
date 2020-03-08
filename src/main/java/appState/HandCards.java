package appState;

import card.Card;
import card.CreateCard;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.collision.CollisionResults;
import com.jme3.input.KeyInput;
import com.jme3.input.RawInputListener;
import com.jme3.input.event.*;
import com.jme3.material.Material;
import com.jme3.math.*;
import com.jme3.renderer.Camera;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Curve;
import com.jme3.ui.Picture;

import java.util.ArrayList;
import java.util.List;

import control.CardMotionControl;

public class HandCards extends BaseAppState {
    private SimpleApplication app;
    //    private double height = app.getCamera().getHeight();
//    private double width = app.getCamera().getWidth();
    private double height = 900; //屏幕高度
    private double width = 1600;  // 屏幕宽度


    private float ratio = (float) (width / 1600.0);  //缩放比例

    private double top = -20 * ratio;  //第一张牌距离屏幕高度
    private double verticalDiff_left = 22 * ratio;  //竖向位移
    private double verticalDiff_right = 15 * ratio;
    private double horizontalDiff = 120 * ratio; //横向位移

    public double cardWidth = 200 * ratio;  //卡片宽度
    public double cardHeight = 260 * ratio;  //卡片高度

    private float rotateRate_left = FastMath.PI / 180 * 3;   //旋转速率
    private float rotateRate_right = -FastMath.PI / 180 * 4;

    private Node rootNode = new Node("HandCards");  //主节点

    private double[][][] positions = new double[20][20][3]; //存放每张牌的位置

    private List<Picture> cards;

    private MyRawInputListener cardListener;

    private Picture chosen;

    private Geometry arrow;

    private Vector3f startPoint;

    private Spline spline;

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
            result[center1] = new double[]{centerPosition - horizontalDiff / 2, top - 20, 0};
            result[center2] = new double[]{centerPosition + horizontalDiff / 2, top - 20, 0};
            for (int i = 0; i <= center1; i++) {
                result[center1 - i] = new double[]{centerPosition - horizontalDiff / 2 - horizontalDiff * i, top - i * verticalDiff_left, rotateRate_left * i};
                result[center2 + i] = new double[]{centerPosition + horizontalDiff / 2 + horizontalDiff * i, top - i * verticalDiff_right, rotateRate_right * i};
            }
        } else {
            int center = num / 2;
            result[center] = new double[]{centerPosition, top - 20, 0};
            for (int i = 0; i <= center; i++) {
                result[center - i] = new double[]{centerPosition - i * horizontalDiff, top - i * verticalDiff_left, rotateRate_left * i};
                result[center + i] = new double[]{centerPosition + i * horizontalDiff, top - i * verticalDiff_right, rotateRate_right * i};
            }
        }
        return result;
    }

    // 初始化卡片
    protected Card newCard(String path) {
        System.out.println(path);
        String[] paths = path.split("/");//将卡牌路径拆开
        String name = paths[paths.length - 1];//获取卡牌名称
        System.out.println(name);
//        Card card = CreateCard.createCard(name, Card.TYPE.ATTACK);//创建卡牌
        Card card = new Card(path);
        card.setImage(app.getAssetManager(), path, true);
        card.setHeight((float) cardHeight);//设置宽高

        card.setWidth((float) cardWidth);//设置宽高
        card.setImage(app.getAssetManager(), path, true);//将卡牌添加进Assetmanager
        return card;
    }

    @Override
    protected void initialize(Application app) {
        this.app = (SimpleApplication) getApplication();
        this.cardListener = new MyRawInputListener();
        for (int i = 0; i < 20; i++) this.positions[i] = this.computePosition(i);

        cards = new ArrayList<Picture>();
        cards.add(newCard("Cards/caster/attack/星陨.png"));
        cards.add(newCard("Cards/caster/attack/充钱.png"));
        cards.add(newCard("Cards/caster/attack/充钱.png"));
        cards.add(newCard("Cards/caster/attack/充钱.png"));
        cards.add(newCard("Cards/caster/attack/充钱.png"));
        cards.add(newCard("Cards/caster/attack/充钱.png"));

//        cards.add(newCard("Cards/caster/attack/双龙炼狱(+).png"));
//        cards.add(newCard("Cards/caster/attack/奥数冲击.png"));
//        cards.add(newCard("Cards/caster/attack/流星雨(+).png"));
//        cards.add(newCard("Cards/caster/attack/无限真空刃(+).png"));
//        cards.add(newCard("Cards/caster/attack/爆破(+).png"));
//        cards.add(newCard("Cards/caster/skill/恶魔契约(+).png"));
        int i = 0;
        int length = cards.size();
        for (Picture card : cards) {
            double x = this.positions[length][i][0];
            double y = this.positions[length][i][1];
            double angle = this.positions[length][i][2];
            card.setPosition((float) x, (float) y);
            card.rotate(0, 0, (float) angle);

//            System.out.println(angle);
//            card.rotateUpTo(new Vector3f(0, 0, (float) angle));
//            Quaternion rotate = card.getLocalRotation();
//            System.out.printf("z: %f \n",rotate.getZ());
            rootNode.attachChild(card);
            i++;
        }


    }

    @Override
    protected void cleanup(Application app) {
        cards.clear();
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


    private void drawCards(int num) {
        int size0 = cards.size();//获取当前还没有抽卡的手牌数量
        cards.add(newCard("Cards/caster/attack/充钱.png"));
//        cards.add(newCard("Cards/caster/power/时空裂隙(+).png"));

        int size = cards.size();//获得新手牌数量
        //放置新卡牌
        for (int i = size0; i < size; i++) {
            Picture card = cards.get(i);
            card.setPosition(1400, -25);
            rootNode.attachChild(card);
        }

        adjustAllCardsPosition(size, size0);


    }

    //多张卡变动时调整卡牌位置，如果传入的size0是-1，那么就是一张卡牌的调节
    private void adjustAllCardsPosition(int size, int size0) {
        for (int i = 0; i < size; i++) {
            Picture card = cards.get(i);
//            System.out.printf("原来 x: %f, y: %f, angle: %f\n",card.getLocalTranslation().x,card.getLocalTranslation().y,card.getLocalRotation().getZ()*2);
            CardMotionControl control = new CardMotionControl();
            control.setSpatial(card);

            //size0为-1说明是单张卡牌当操作，不需要调整移动速度
            if (size0 != -1)
                if (i >= size0)
                    control.setWalkSpeed(1200f);


            double[] position = positions[size][i];
            double x = position[0];
            double y = position[1];
            double angle = position[2];
//            System.out.printf("新位置 x: %f, y: %f, angle: %f\n",x,y,angle);
            control.setTarget(new Vector2f((float) x, (float) y), (float) angle);
            card.addControl(control);
        }
    }

    //卡牌打出时候的操作
    private void useCards(Picture picture) {
        cards.remove(picture);
        rootNode.detachChild(picture);
        int size = cards.size();
        adjustAllCardsPosition(size, -1);

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

        Ray ray = new Ray(new Vector3f(x, y, 100), dir);
        CollisionResults results = new CollisionResults();
        rootNode.collideWith(ray, results);//检测guinode 中所有图形对象 和 ray 的碰撞

        return results;
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

        Ray ray = new Ray(new Vector3f(x, y, 100), dir);
        CollisionResults results = new CollisionResults();
        rootNode.collideWith(ray, results);//检测guinode 中所有图形对象 和 ray 的碰撞

        return results;
    }

    private void enlargeCard(Card img, Card closest) {
        closest.setWidth((float) (cardWidth * 1.25));
        closest.setHeight((float) (cardHeight * 1.25));
        Vector3f location = closest.getLocalTranslation();
        closest.setLocalTranslation(location.x, location.y, 1);//通过竖坐标增加来使得图片在前显示
        //放大这个离鼠标最近的图片

        if(img!=null) {
            img.setWidth((float) cardWidth);
            img.setHeight((float) cardHeight);
            location = img.getLocalTranslation();
            img.setLocalTranslation(location.x, location.y, 0);//图片还原
        }
    }

    private void recoverCard(Card img) {
        img.setWidth((float) cardWidth);
        img.setHeight((float) cardHeight);
        Vector3f location = img.getLocalTranslation();
        img.setLocalTranslation(location.x, location.y, 0);
    }

    private Card putCardCenter(Card closest) {
        Card center = newCard(closest.getName());
        center.setPosition((float) ((width - cardWidth * 1.5) / 2.0), (float) ((height - cardHeight) / 2.0));
        center.setWidth((float) (cardWidth * 1.5));
        center.setHeight((float) (cardHeight * 1.5));
        return center;
    }

    // 创建曲线
    protected void createArrow(MouseButtonEvent evt) {
        Material mat = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", new ColorRGBA(255, 0, 0, 0.6f));
        mat.getAdditionalRenderState().setLineWidth(20f);
        mat.getAdditionalRenderState().setWireframe(true);

        // 创建几何物体，应用箭头网格。
        float startX = (float) (chosen.getLocalTranslation().getX() + cardWidth / 2);
        float startY = (float) (chosen.getLocalTranslation().getY() + cardHeight / 2);
        float endX = evt.getX();
        float endY = evt.getY();
        this.startPoint = new Vector3f(startX, startY, -1);
        Vector3f endPoint = new Vector3f(endX, endY, -1);
        //创建两个向量,一个指向卡牌中心,一个指向当前鼠标位置

        float distance = startPoint.distance(endPoint);
        //计算起点到中点的距离

        Vector3f k1 = new Vector3f(endPoint.subtract(startPoint).getX(), endPoint.subtract(startPoint).getY(), 0).normalize();
        //计算这个方向的单位向量

        Vector3f k2 = new Vector3f(-k1.getY(), k1.getX(), 0);
        //计算上面那个向量的正交向量

        Vector3f centerPoint = new Vector3f((startX + endX) / 2, (startY + endY) / 2, -1).add(k2.mult(distance / 8));

        Vector3f[] points = new Vector3f[]{startPoint, centerPoint, endPoint};

        Spline spline = new Spline(Spline.SplineType.CatmullRom, points, 0.7f, false);
        this.arrow = new Geometry("arrow", new Curve(spline, 100));
        arrow.setMaterial(mat);
        arrow.setShadowMode(RenderQueue.ShadowMode.Off);
        rootNode.attachChild(arrow);

    }

    // 鼠标移动的时候改动曲线
    protected void moveArrow(MouseMotionEvent evt) {
        Material mat = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", new ColorRGBA(255, 0, 0, 0.6f));
        mat.getAdditionalRenderState().setLineWidth(20f);
        mat.getAdditionalRenderState().setWireframe(true);

        // 创建几何物体，应用箭头网格。
        float startX = startPoint.getX();
        float startY = startPoint.getY();
        float endX = evt.getX();
        float endY = evt.getY();
        this.startPoint = new Vector3f(startX, startY, -1);
        Vector3f endPoint = new Vector3f(endX, endY, -1);
        float distance = startPoint.distance(endPoint);
        Vector3f k1 = new Vector3f(endPoint.subtract(startPoint).getX(), endPoint.subtract(startPoint).getY(), 0).normalize();
        Vector3f k2 = new Vector3f(-k1.getY(), k1.getX(), 0);
        Vector3f centerPoint = new Vector3f((startX + endX) / 2, (startY + endY) / 2, 0).add(k2.mult(distance / 9));

        Vector3f[] points = new Vector3f[]{startPoint, centerPoint, endPoint};

        Spline spline = new Spline(Spline.SplineType.CatmullRom, points, 0.7f, false);
        if (this.arrow != null) {
            this.arrow.removeFromParent();
        }
        this.arrow = new Geometry("arrow", new Curve(spline, 1000));
        arrow.setMaterial(mat);
        arrow.setShadowMode(RenderQueue.ShadowMode.Off);
        rootNode.attachChild(arrow);
    }


    class MyRawInputListener implements RawInputListener {
        Card last = CreateCard.createCard("null", Card.TYPE.ATTACK);//上次划过的图片
        Card center = CreateCard.createCard("null", Card.TYPE.ATTACK);//屏幕中心的图片

        /**
         * 键盘输入事件
         */
        @Override
        public void onKeyEvent(KeyInputEvent evt) {
            int keyCode = evt.getKeyCode();
            boolean isPressed = evt.isPressed();
            if (keyCode == KeyInput.KEY_L && isPressed) {
                drawCards(1);
            }

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
//                    for (int i = 0; i < results.size();i++){
//                        res =results.getCollision(i).getGeometry();
//                        System.out.println(res.getName());
//                    }
                    return;
                }

                // 使鼠标放上去的卡牌放大(除了正中间那一张)
                if (last != closest && closest != center) {
                    enlargeCard(last, closest);//放大选中图片
                    last = closest;
//                    center = putCardCenter(center,closest);//将图片放置在中央
//                    Node guiNode = app.getGuiNode();//GUInode 包含了所有图形对象
//                    guiNode.attachChild(center);
                }
            } else {
                // 使卡牌恢复原状
                if (last != null) {
                    recoverCard(last);
                    last = CreateCard.createCard("null", Card.TYPE.ATTACK);
                }
//                center.removeFromParent();

            }

            if (chosen != null) {
                moveArrow(evt);

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
                    Card closest;

                    //如果选中的是卡牌,并且不是能力卡牌（因为power卡不需要指定目标），就获得它
                    if (res instanceof Card && ((Card)res).getType() != Card.TYPE.POWER) {
                        closest = (Card) res;
                        chosen = closest;
                        center = putCardCenter(closest);//将图片放置在中央
//                        Node guiNode = app.getGuiNode();//GUInode 包含了所有图形对象
                        rootNode.attachChild(center);
                        
                        if (arrow != null) {
                            arrow.removeFromParent();
                        }
                        createArrow(evt);
                    }
                } else {
                    if(center!=null)
                        center.removeFromParent();

                    chosen = null;//点击其他区域（不是敌人或者卡牌的时候，取消所有选择）

                    if(arrow!=null)
                        arrow.removeFromParent();//鼠标释放的时候移除箭头（不论是否选中敌人）

                }


            } else if (evt.isReleased()) {
                //这里处理的是拖动导致的释放卡牌
                Geometry enemyChosen = app.getStateManager().getState(EnemyState.class).getChosen();
                if (chosen != null && enemyChosen != null) {
                    useCards(chosen);
                }

                if(center!=null)
                    center.removeFromParent();

                chosen = null;//点击其他区域（不是敌人或者卡牌的时候，取消所有选择）

                if(arrow!=null)
                    arrow.removeFromParent();//鼠标释放的时候移除箭头（不论是否选中敌人）

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

    public static void main(String[] args) {
//        HandCards a = new HandCards();
//        double[][] result = a.computePosition(5);
//        for (double[] x : result) {
//            for (double y : x) {
//                System.out.print(y + "  ");
//            }
//            System.out.println();
//        }
        System.out.println(FastMath.PI / 180 * 3);
    }
}
