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
        System.out.println(monster.getDESCRIPTION());
    }

    public void itemDrop(Monster monster) {
        System.out.println(monster.getNAME() + " dropped an item");
    }
}
