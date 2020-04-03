package card;

import character.Role;

public abstract class SkillCard extends Card {
    private int block;

    public SkillCard(OCCUPATION occupation, String name, int cost, RARITY rarity, String description) {
        super(occupation, name, cost, TYPE.SKILL, rarity, description);
    }


    @Override
    public boolean use(Role target) {
        return super.use(target);
    }

    @Override
    public boolean use(Role... target) {
        return super.use(target);
    }

    @Override
    public boolean use() {
        return super.use();
    }
}
