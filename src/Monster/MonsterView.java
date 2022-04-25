package Monster;

/**
 * Prints all related information related to a Monster to the console
 * @author Khamilah Nixon
 */
public final class MonsterView {
    public void print(String message) {
        System.out.println(message);
    }

    public void monsterDescription(Monster monster) {
        System.out.println(monster.getDescription());
    }

    public void itemDrop(Monster monster) {
        System.out.println(monster.getName() + " dropped an item");
    }
}
