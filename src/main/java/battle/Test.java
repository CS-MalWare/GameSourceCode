package battle;

import appState.*;
import character.MainRole;
import com.jme3.app.DebugKeysAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioListenerState;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.*;
import com.jme3.post.FilterPostProcessor;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.shadow.BasicShadowRenderer;
import com.jme3.shadow.DirectionalLightShadowFilter;
import com.jme3.shadow.DirectionalLightShadowRenderer;
import com.jme3.system.AppSettings;
import com.jme3.ui.Picture;
import equipment.CreateEquipment;
import equipment.epic.ArmstrongGun;
import equipment.legendary.BalancedLibra;
import equipment.legendary.MerlinGown;
import equipment.legendary.MerlinWand;

public class Test extends SimpleApplication {

    //Picture last = new Picture("null");

    public Test() {
        super(
                new StatsAppState()
                , new AudioListenerState()
                , new DebugKeysAppState()
                , new EnemyState()
                , new HandCardsState()
                , new DecksState()
                , new LeadingActorState()
                , new BattleBackGroundState()
//                , new GetCardState()
//                new GetEquipmentState()
        );
    }

    @Override
    public void simpleInitApp() {
        character.MainRole mainRole = MainRole.getInstance();
        mainRole.bindApp(this);
        mainRole.getEquipment(new BalancedLibra());
        mainRole.getEquipment(new MerlinGown());
        mainRole.getEquipment(new MerlinWand());
        mainRole.getEquipment(new ArmstrongGun());
        mainRole.getEquipment(CreateEquipment.getRandomEquipment());
        addLight();
    }

    public void addLight() {
        // 定向光
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-1, -2, -3));

        //viewPort.setBackgroundColor(ColorRGBA.LightGray);
        // 环境光
        AmbientLight ambient = new AmbientLight();

        // 调整光照亮度
        ColorRGBA lightColor = new ColorRGBA();
        sun.setColor(lightColor.mult(1.3f));
        ambient.setColor(lightColor.mult(1.3f));

        rootNode.addLight(sun);
        rootNode.addLight(ambient);

        DirectionalLightShadowFilter su = new DirectionalLightShadowFilter(assetManager, 1024, 3);
        su.setLight(sun);
        su.setEnabled(true);
        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        fpp.addFilter(su);
        viewPort.addProcessor(fpp);
    }



    public static void main(String[] args) {
        // 配置参数
        AppSettings settings = new AppSettings(true);
        settings.setTitle("protect yuetao king");// 标题
        settings.setResolution(1600, 900);// 分辨率
        settings.setFrameRate(100);//限制fps
        Test app = new Test();
        app.setSettings(settings);
        app.setShowSettings(false);

        app.start();
    }
}
