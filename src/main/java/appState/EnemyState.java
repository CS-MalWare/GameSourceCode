package appState;

import character.Role;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

import java.util.List;

public class EnemyState extends BaseAppState {
    private SimpleApplication app;
    private Node rootNode = new Node("EnemyState");  //主节点
    private List<Role> enemies;
    @Override
    protected void initialize(Application application) {
        this.app = (SimpleApplication) getApplication();
        Spatial model = application.getAssetManager().loadModel("dragon2/dragon.obj");
        model.scale(0.03f);// 按比例缩小
        model.center();// 将模型的中心移到原点
        model.move(7,0,0);
        model.rotate(0,-1f,0);
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(2,1, -3));

        // 环境光
        AmbientLight ambient = new AmbientLight();

        // 调整光照亮度
        ColorRGBA lightColor = new ColorRGBA();
        sun.setColor(lightColor.mult(0.8f));
        ambient.setColor(lightColor.mult(1));

        // #3 将模型和光源添加到场景图中
        rootNode.addLight(sun);
        rootNode.addLight(ambient);
        rootNode.attachChild(model);

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
    }
}
