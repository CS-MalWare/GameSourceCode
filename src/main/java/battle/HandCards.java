package battle;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.ui.Picture;

import java.util.ArrayList;
import java.util.List;

public class HandCards extends BaseAppState {
    private SimpleApplication app;
    //    private double height = app.getCamera().getHeight();
//    private double width = app.getCamera().getWidth();
    private double height = 900;
    private double width = 1600;
    private double left = width / 6.0;
    private double right = width / 6.0 * 5;

    private double deckWidth = width / 3.0 * 2;
    private float ratio = (float) (width / 1600.0);

    private double top = -50 * ratio;
    private double verticalDiff_left = 22 * ratio;
    private double verticalDiff_right = 15 * ratio;
    private double horizontalDiff= 120 * ratio;

    public double cardWidth = 200 * ratio;
    public double cardHeight = 290 * ratio;

    private float rotateRate_left = FastMath.PI / 180 * 4;
    private float rotateRate_right = -FastMath.PI / 180 * 3;

    private Node rootNode = new Node("HandCards");

    private double[][][] positions = new double[20][20][3];

    protected double[][] computePosition(int num) {
        if (num == 0) return null;
        double[][] result = new double[num][3];
        double horizontalDiff = num < 10 ? this.horizontalDiff : this.horizontalDiff * 10.0 / num;
        double verticalDiff_right = num < 10 ? this.verticalDiff_right : this.verticalDiff_right * 10.0 / num;
        double verticalDiff_left = num < 10 ? this.verticalDiff_left : this.verticalDiff_left * 10.0 / num;
        double rotateRate_left = num < 10 ? this.rotateRate_left : this.rotateRate_left * 10.0 / num;
        double rotateRate_right = num < 10 ? this.rotateRate_right : this.rotateRate_right * 10.0 / num;
        double centerPosition = (width - 300*ratio) / 2.0;
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


    protected Picture newCard(@org.jetbrains.annotations.NotNull String path) {
        String[] paths = path.split("/");
        String name = paths[paths.length - 1];
        Picture card = new Picture(path);
        card.setHeight(ratio * 300);
        card.setWidth(ratio * 300);
        card.setImage(app.getAssetManager(), path, true);
        return card;
    }

    @Override
    protected void initialize(Application app) {
        this.app = (SimpleApplication) getApplication();
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
    }

    @Override
    protected void onDisable() {
        this.rootNode.removeFromParent();
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
