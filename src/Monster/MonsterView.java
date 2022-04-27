package Monster;

/**
 * Prints all related information related to a Monster to the console
 * @author Khamilah Nixon
 */
public final class MonsterView {
    public void print(String message) {
        System.out.println(message);
    }


    public void examine(Monster monster) {
        System.out.println(monster.getName() + ":");
        System.out.println("Health: " + monster.getHp() + " HP");
        System.out.println("Damage: " + monster.getDamage() + " DMG");
        System.out.println(monster.getDescription());
    }


    public void monsterUnavailable() {
        System.out.println("There is no monster.");
    }

    public void died(Monster monster) {
        System.out.println(monster.getName() + " died.");
    }
    public void itemDrop(Monster monster) {
        System.out.println(monster.getName() + " dropped an item");
    }
}
