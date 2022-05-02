package Monster;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author Khamilah Nixon
 */
public class MonsterController implements Serializable {
    private MonsterView view;
    private HashMap<Integer, Monster> model;

    public MonsterController(HashMap<Integer, Monster> model, MonsterView view) {
        this.view = view;
        this.model = model;
    }

    public void monsterInfo(int key) {
        view.monsterInfo(model.get(key));
    }

    public HashMap<Integer, Monster> getModel() {
        return model;
    }

}
