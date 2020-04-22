package appState;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.scene.Node;

public class GetCardState extends BaseAppState {
    private SimpleApplication app;
    private Node rootNode = new Node("GetCardState");
    // 获得普通卡的概率为70%
    private final float getCommonCard = 0.55f;
    // 获得稀有卡的概率为20%
    private final float getRareCard = 0.20f;
    // 获得史诗卡的概率为15%
    private final float getEpicCard = 0.15f;
    // 获得传说卡的概率为10%
    private final float getLegendaryCard = 0.1f;
    @Override
    protected void initialize(Application application) {

    }

    @Override
    protected void cleanup(Application application) {

    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
