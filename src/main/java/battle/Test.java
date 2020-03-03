package battle;

import appState.EnemyState;
import com.jme3.app.DebugKeysAppState;
import com.jme3.app.FlyCamAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.audio.AudioListenerState;
import com.jme3.collision.CollisionResults;
import com.jme3.input.KeyInput;
import com.jme3.input.RawInputListener;
import com.jme3.input.event.*;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.*;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Curve;
import com.jme3.system.AppSettings;
import com.jme3.ui.Picture;
import appState.HandCards;
import appState.EnemyState;

public class Test extends SimpleApplication {


    Picture last = new Picture("null");

    public Test() {
        super(new StatsAppState(), new AudioListenerState(), new DebugKeysAppState(),
                new EnemyState(), new HandCards());
    }

    @Override
    public void simpleInitApp() {
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", new ColorRGBA(255, 0, 0, 0.8f));
        mat.getAdditionalRenderState().setLineWidth(40f);
        mat.getAdditionalRenderState().setWireframe(true);

        // 创建几何物体，应用箭头网格。
        Vector3f[] points = new Vector3f[]{new Vector3f(100, 500, 20), new Vector3f(600, 800, 20), new Vector3f(1100, 500, 20)};
        Spline spline = new Spline(Spline.SplineType.CatmullRom, points, 0.6f, true);
        Geometry arrow = new Geometry("arrow", new Curve(spline, 50));
        arrow.setMaterial(mat);
        arrow.setShadowMode(RenderQueue.ShadowMode.Off);
        guiNode.attachChild(arrow);
    }

    public void addLight() {
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
