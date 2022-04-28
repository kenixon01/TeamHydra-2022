package Monster;

import java.util.HashMap;

/**
 * @author Khamilah Nixon
 */
public class MonsterController {
    private MonsterView view;
    private HashMap<Integer, Monster> model;

    public MonsterController(HashMap<Integer, Monster> model, MonsterView view) {
        this.view = view;
        this.model = model;
    }

    public void monsterInfo(int key) {
        view.monsterInfo(model.get(key));
    }

    public MonsterView getView() {
        return view;
    }

    public HashMap<Integer, Monster> getModel() {
        return model;
    }

    public void die(int key) {
        view.died(model.get(key));
        view.itemDrop(model.get(key));
        model = null;
    }
}
