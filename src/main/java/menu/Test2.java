package menu;

import com.jme3.app.SimpleApplication;
import com.simsilica.lemur.*;
import com.simsilica.lemur.style.BaseStyles;

/**
 * 加载模型
 * @author yanmaoyuan
 *
 */
public class Test2 extends SimpleApplication {


    public void simpleInitApp() {
        GuiGlobals.initialize(this);
        BaseStyles.loadGlassStyle();
        GuiGlobals.getInstance().getStyles().setDefaultStyle("glass");
        Container myWindow = new Container();
        guiNode.attachChild(myWindow);

        myWindow.setLocalTranslation(300,300,0);
        myWindow.addChild(new Label("Hello, World."));
        Button clickMe = myWindow.addChild(new Button("Click Me"));
        clickMe.addClickCommands(new Command<Button>() {
            @Override
            public void execute(Button source) {
                System.out.println("The world is yours.");
            }
        });
    }

    public static void main(String[] args) {
        // 启动程序
        Test2 app = new Test2();
        app.start();
    }

}
