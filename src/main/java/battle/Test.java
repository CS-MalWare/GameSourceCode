package battle;

import appState.DecksState;
import appState.EnemyState;
import character.MainRole;
import com.jme3.app.DebugKeysAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.audio.AudioListenerState;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.*;
import com.jme3.system.AppSettings;
import com.jme3.ui.Picture;
import appState.HandCardsState;

public class Test extends SimpleApplication {


    Picture last = new Picture("null");

    public Test() {
        super(new StatsAppState(), new AudioListenerState(), new DebugKeysAppState(),
                new EnemyState(), new HandCardsState(), new DecksState());
    }

    @Override
    public void simpleInitApp() {
        character.MainRole mainRole = MainRole.getInstance();
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
        settings.setFrameRate(100);//限制fps
        Test app = new Test();
        app.setSettings(settings);
        app.setShowSettings(false);

        app.start();
    }
}
