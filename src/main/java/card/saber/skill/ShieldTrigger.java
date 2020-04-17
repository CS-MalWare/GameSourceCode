package card.saber.skill;

import appState.EnemyState;
import card.SkillCard;
import character.Enemy;
import character.MainRole;
import character.Role;
import org.codehaus.groovy.tools.shell.Main;

import java.util.ArrayList;

public class ShieldTrigger extends SkillCard {
    public ShieldTrigger() {
        super(OCCUPATION.SABER, "盾牌激发", 1, RARITY.RARE, "gain 8 block. if any enemy is weak, gain 8 extra block in the next round");

    }

    public ShieldTrigger(boolean upgraded) {
        super(OCCUPATION.SABER, "盾牌激发+", 1, RARITY.RARE, "gain 12 block. if any enemy is weak, gain 8 extra block in the next round");
        this.upgraded = true;
    }


    @Override
    public boolean upgrade() {
        if (this.upgraded) {
            return false;
        } else {
            this.upgraded = true;
            this.setCardName("盾牌激发+");
            this.setDescription("gain 12 block. if any enemy is weak, gain 8 extra block in the next round");
        }
        return true;
    }


    @Override
    public boolean use(Role target) {
        if (!upgraded) MainRole.getInstance().gainBlock(8);
        else MainRole.getInstance().gainBlock(12);
        ArrayList<Enemy> enemies = EnemyState.getInstance().getEnemies();
        boolean flag = false;
        for (Enemy enemy : enemies) {
            if (enemy.getWeak().getDuration() > 0) {
                flag = true;
                break;
            }
        }
        if (flag) {
            MainRole.getInstance().addCardEffect(this.getCardName());
        }

        return true;
    }

}
