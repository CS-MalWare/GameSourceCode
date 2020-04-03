package card.neutral.skill;

import card.SkillCard;
import character.MainRole;
import character.Role;
import org.codehaus.groovy.tools.shell.Main;
import utils.buffs.Buff;
import utils.buffs.foreverBuffs.Artifact;

public class Tenacity extends SkillCard {
    public Tenacity() {
        super(OCCUPATION.NEUTRAL, "坚定不移", 1, RARITY.RARE, "gian 7 blocks and 1 artifact");
    }

    public Tenacity(boolean upgraded) {
        super(OCCUPATION.NEUTRAL, "坚定不移+", 1, RARITY.RARE, "gian 10 blocks and 1 artifact");
        this.upgraded = true;
    }


    @Override
    public boolean upgrade() {
        if (this.upgraded) {
            return false;
        } else {
            this.upgraded = true;
            this.setCardName("坚定不移+");
            this.setDescription("gian 10 blocks and 1 artifact");

        }
        return true;
    }

    @Override
    public boolean use(Role target) {
        MainRole.getInstance().getBuff(new Artifact(MainRole.getInstance(), 1));
        if (!upgraded)
            MainRole.getInstance().gainBlock(7);
        else
            MainRole.getInstance().gainBlock(10);
        return true;
    }

}
