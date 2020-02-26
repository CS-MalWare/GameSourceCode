package menu;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

import java.util.List;

public class Listener extends SimpleApplication {
    public final  static String FIRE = "Fire";
    public final  static String LOAD = "Load";

    @Override
    public void simpleInitApp() {
        initInput();
        cam.setLocation(new Vector3f(0.41600543f, 3.2057908f, 6.6927643f));
        cam.setRotation(new Quaternion(-0.00414816f, 0.9817784f, -0.18875499f, -0.021575727f));

        flyCam.setMoveSpeed(10);
        viewPort.setBackgroundColor(ColorRGBA.LightGray);

        // 定向光
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-1, -2, -3));

        // 环境光
        AmbientLight ambient = new AmbientLight();

        // 调整光照亮度
        ColorRGBA lightColor = new ColorRGBA();
        sun.setColor(lightColor.mult(0.6f));
        ambient.setColor(lightColor.mult(0.4f));

        rootNode.addLight(sun);
        rootNode.addLight(ambient);
    }

    private void initInput(){
        inputManager.addMapping(FIRE, new KeyTrigger(KeyInput.KEY_SPACE), new MouseButtonTrigger(MouseInput.BUTTON_LEFT));

        inputManager.addMapping(LOAD,new KeyTrigger(KeyInput.KEY_L));
        MyActionListener l = new MyActionListener();

        inputManager.addListener(l, FIRE,LOAD);

        inputManager.removeListener(l);
    }

    class MyActionListener implements ActionListener{

        @Override
        public void onAction(String name, boolean isPressed, float tpf) {
            if(isPressed){
                if(FIRE.equals(name)){
                    System.out.println("bang!");
                } else if (LOAD.equals(name)) {
                    loadModel();

                }
            }
        }
    }

    private void loadModel(){
        new Thread () {
            public void run() {
                // 在子线程中导入模型
                final Spatial model = assetManager.loadModel("Models/test/test.obj");
                model.scale(0.03f);// 按比例缩小
                model.center();// 将模型的中心移到原点

                // 通知主线程，将模型添加到场景图中。
                enqueue(new Runnable() {
                    public void run() {
                        rootNode.attachChild(model);
                    }
                });
            }
        }.start();
    }


    public static void main(String[] args) {
        Listener app = new Listener();
        app.start();
    }
}
