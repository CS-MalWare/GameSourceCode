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
import appState.HandCards;

public class Test extends SimpleApplication {


    Picture last = new Picture("null");

    public Test() {
        super(new StatsAppState(), new AudioListenerState(), new DebugKeysAppState(),
               new HandCards());
    }

    @Override
    public void simpleInitApp() {


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
