package appState;

import character.Role;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;

import java.util.List;

public class EnemyState extends BaseAppState {
    private SimpleApplication app;
    private Node rootNode = new Node("EnemyState");  //主节点
    private List<Role> enemies;
    @Override
    protected void initialize(Application application) {
        this.app = (SimpleApplication) getApplication();
        Geometry grid = new Geometry("sphere",new Sphere(20,40,3));
        Material mat = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.DarkGray);
        grid.setMaterial(mat);
        grid.setShadowMode(RenderQueue.ShadowMode.Off);
        grid.move(4, 3, 0);
        rootNode.attachChild(grid);

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
