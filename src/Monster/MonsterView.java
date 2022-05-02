package Monster;

import java.io.Serializable;
import Utilities.ConsoleColors;

/**
 * Prints all related information related to a Monster to the console
 * @author Khamilah Nixon
 */
public final class MonsterView implements Serializable {
    private ConsoleColors colors = new ConsoleColors();

    public MonsterView() {
        colors.setTextColor("red");
    }

    public void monsterInfo(Monster monster) {
        System.out.println(
                colors.textColor(monster.getName()));
        System.out.println(
                colors.textColor("Health: ") + monster.getHp() + " HP");
        System.out.println(
                colors.textColor("Damage: ") + monster.getDamage() + " DMG");
        System.out.println(
                colors.textColor(monster.getDescription(), "b/red"));
    }
}
