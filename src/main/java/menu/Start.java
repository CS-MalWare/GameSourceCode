package menu;
import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;

public class Start extends SimpleApplication {

    public void simpleInitApp() {
        // #1 创建一个方块形状的网格
        Mesh box = new Box(1, 1, 1);

        // #2 加载一个感光材质
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");

        // #3 创建一个几何体，应用刚才和网格和材质。
        Geometry geom = new Geometry("Box");
        geom.setMesh(box);
        geom.setMaterial(mat);

        // #4 创建一束定向光，并让它斜向下照射，好使我们能够看清那个方块。
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-1, -2, -3));

        // #5 将方块和光源都添加到场景图中
        rootNode.attachChild(geom);
        rootNode.addLight(sun);
    }

    public static void main(String[] args) {
        AppSettings settings = new AppSettings(true);
        settings.setTitle("cs-malware");
        settings.setMinResolution(1600,900);
        settings.setVSync(false);

        Start app = new Start();
        app.setSettings(settings);
        app.showSettings=false;
        app.start();
    }

}
