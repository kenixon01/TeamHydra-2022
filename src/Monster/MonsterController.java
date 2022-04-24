package Monster;

public class MonsterController {
    private MonsterView view = new MonsterView();
    private Monster model = new Monster();

    public MonsterController(Monster model, MonsterView view) {
        this.view = view;
        this.model = model;
    }

    public void monsterDescription() {
        view.monsterDescription(model);
    }

    public void die() {
        view.itemDrop(model);
        model = null;
    }
}
