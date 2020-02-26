package battle;

import com.jme3.app.DebugKeysAppState;
import com.jme3.app.FlyCamAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.audio.AudioListenerState;
import com.jme3.collision.CollisionResults;
import com.jme3.input.KeyInput;
import com.jme3.input.RawInputListener;
import com.jme3.input.event.*;
import com.jme3.math.FastMath;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.system.AppSettings;
import com.jme3.ui.Picture;

public class Test extends SimpleApplication {


    Picture last = new Picture("null");
    public Test() {
        super(new StatsAppState(), new AudioListenerState(), new DebugKeysAppState(),
                new HandCards());
    }

    @Override
    public void simpleInitApp() {
        inputManager.addRawInputListener(new MyRawInputListener());

    }

    // 原始输入监听器
    class MyRawInputListener implements RawInputListener {

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

            Vector2f screenCoord = new Vector2f(x,y);
            Vector3f worldCoord = cam.getWorldCoordinates(screenCoord, 1f);
            Vector3f worldCoord2 = cam.getWorldCoordinates(screenCoord, 0f);

// 然后计算视线方向
            Vector3f dir = worldCoord.subtract(worldCoord2);
            dir.normalizeLocal();

// 生成射线
            Ray ray = new Ray(new Vector3f(x,y,0), dir);
            CollisionResults results = new CollisionResults();
            guiNode.collideWith(ray, results);

            if (results.size() > 0) {
                // 获得离射线原点最近的交点
                Picture closest = (Picture)(results.getClosestCollision().getGeometry());
                System.out.println(closest.getName());
                if (last!=closest){
                    closest.setWidth(400);
                    closest.setHeight(400);
                    closest.move(0,0,1);
                    if (!last.getName().equals("null")){
                        last.setWidth(300);
                        last.setHeight(300);
                        last.move(0,0,0);
                    }
                    last = closest;
                }

            }else{
                if (!last.getName().equals("null")){
                    last.setWidth(300);
                    last.setHeight(300);
                    last.move(0,0,0);
                    last.setName("null");
                }
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
        // 配置参数
        AppSettings settings = new AppSettings(true);
        settings.setTitle("守护陈岳涛殿下");// 标题
        settings.setResolution(1600, 900);// 分辨率

        Test app = new Test();
        app.setSettings(settings);
        app.setShowSettings(false);

        app.start();
    }
}
