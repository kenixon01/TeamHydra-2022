//package Entry;
//
//
//import Console.Console;
//import Console.ConsoleController;
//import Console.ConsoleView;
//import Battle.*;
//import Character.Character;
//import Item.*;
//import Room.*;
//import Character.*;
//import Monster.*;
//import Puzzle.*;
//
//import java.util.InputMismatchException;
//
///**
// * Author: Brian Smithers
// */
//public class Entry {
//
//    //Brian Smithers and Khamilah Nixon
//    public static void main(String[] args) {
//        Character character = new Character();
//        CharacterView characterView = new CharacterView();
//        CharacterController characterController;
//
//        Item item = new Item();
//        ItemView itemView = new ItemView();
//        ItemController itemController = new ItemController();
//
//        Monster monster = new Monster();
//        MonsterView monsterView = new MonsterView();
//        MonsterController monsterController = new MonsterController(monster, monsterView);
//
//        Puzzle puzzle = new Puzzle();
//        PuzzleView puzzleView = new PuzzleView();
//        PuzzleController puzzleController = new PuzzleController();
//
//        Room room = new Room();
//        RoomView roomView = new RoomView();
//        RoomController roomController = new RoomController(room, roomView);
//
//        Battle battle = new Battle(character,monster);
//        BattleView battleView = new BattleView();
//        BattleController battleController = new BattleController(battle,battleView);
//
//        Console console = new Console();
//        ConsoleView consoleView = new ConsoleView();
//        ConsoleController consoleController = new ConsoleController(
//                battleController,battle,battleView,
//                characterController, character, characterView,
//                itemController,item,itemView,
//                monsterController, monster,monsterView,
//                puzzleController, puzzle, puzzleView,
//                roomController, room, roomView, console, consoleView);
//
//
//        try {
//            consoleController.startGame();
//
//            //main menu displays on console
//            //enter start, continue, or exit on main menu
//            consoleController.mainMenu();
//
//            //character select menu displays on console
//            //player can select and verify their character
//            consoleController.characterSelect();
//
//
//
//            /*
//              allows player to enter commands once the game
//              environment and first room loads
//            */
//            consoleController.enterCommand();
//        }
//        catch (InputMismatchException e) {
//            consoleController.invalidCommand();
//        }
//    }
//}
