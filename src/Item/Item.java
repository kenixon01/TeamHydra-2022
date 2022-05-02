package Item;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Author: Jayson Dasher
 */
public class Item implements Serializable {

    // define variables
    private int _itemNumber;
    private String _itemName;
    private String _itemDescription;
    private String _itemRoom;
    private int _damageValue;
    private int _healValue;
    private String _itemType;
    private int _totalHpModifier;
    private float _criticalHitChance;
    private Boolean isEquipped;

    private boolean wearable; // Author: Brian Smithers
    private Boolean restoreHealthOnPickUp; // Author: Brian Smithers

    private Boolean itemUnlocked;

    private static LinkedList<Item> itemList = new LinkedList<>();

    public Item(int itemNum, String itemName, String itemDesc, String itemRoom, int damageValue,
                int healValue, String itemType, int totalHpModifier, float criticalHitChance,
                boolean wearable, boolean restoreHealthOnPickUp, Boolean itemUnlocked) {
        this._itemNumber = itemNum;
        this._itemName = itemName;
        this._itemDescription = itemDesc;
        this._itemRoom = itemRoom;
        this._damageValue = damageValue;
        this._healValue = healValue;
        this._itemType = itemType;
        this._totalHpModifier = totalHpModifier;
        this._criticalHitChance = criticalHitChance;
        //this.isEquipped = false;
        this.wearable = wearable;
        this.restoreHealthOnPickUp = restoreHealthOnPickUp;
        this.itemUnlocked = itemUnlocked;
    }

    public static LinkedList<Item> getItemList() {
        return itemList;
    }

    public String get_itemName() {
        return _itemName;
    }

    public String get_itemDescription() {
        return _itemDescription;
    }

    public String get_itemRoom() {
        return _itemRoom;
    }

    public int get_damageValue() {
        return _damageValue;
    }

    public int get_healValue() {
        return _healValue;
    }

    public String get_itemType() {
        return _itemType;
    }

    public int get_totalHpModifier() {
        return _totalHpModifier;
    }

    public Boolean getEquipped() {
        return isEquipped;
    }

    public void setEquipped(Boolean equipped) {
        isEquipped = equipped;
    }

    public boolean isRestoreHealthOnPickUp() {
        return restoreHealthOnPickUp;
    }

    // Author: Brian Smithers
    // If the player picks up an item that restores health
    // it cannot restore health again. This will be called
    // to remove if its healing affects.
    public void setRestoreHealthOnPickUp(boolean status) {
        restoreHealthOnPickUp = status;
    }

    public Boolean getItemUnlocked() {
        return itemUnlocked;
    }

    public void setItemUnlocked(Boolean itemUnlocked) {
        this.itemUnlocked = itemUnlocked;
    }
}