package Item;

import java.util.LinkedList;

/**
 * Author: Jayson Dasher
 */
public class Item {

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

    public int get_itemNumber() {
        return _itemNumber;
    }

    public static LinkedList<Item> getItemList() {
        return itemList;
    }

    public void set_itemNumber(int _itemNumber) {
        this._itemNumber = _itemNumber;
    }

    public String get_itemName() {
        return _itemName;
    }

    public void set_itemName(String _itemName) {
        this._itemName = _itemName;
    }

    public String get_itemDescription() {
        return _itemDescription;
    }

    public void set_itemDescription(String _itemDescription) {
        this._itemDescription = _itemDescription;
    }

    public String get_itemRoom() {
        return _itemRoom;
    }

    public void set_itemRoom(String _itemRoom) {
        this._itemRoom = _itemRoom;
    }

    public int get_damageValue() {
        return _damageValue;
    }

    public void set_damageValue(int _damageValue) {
        this._damageValue = _damageValue;
    }

    public int get_healValue() {
        return _healValue;
    }

    public void set_healValue(int _healValue) {
        this._healValue = _healValue;
    }

    public String get_itemType() {
        return _itemType;
    }

    public void set_itemType(String _itemType) {
        this._itemType = _itemType;
    }

    public int get_totalHpModifier() {
        return _totalHpModifier;
    }

    public void set_totalHpModifier(int _totalHpModifier) {
        this._totalHpModifier = _totalHpModifier;
    }

    public float get_criticalHitChance() {
        return _criticalHitChance;
    }

    public void set_criticalHitChance(int _criticalHitChance) {
        this._criticalHitChance = _criticalHitChance;
    }

    public Boolean getEquipped() {
        return isEquipped;
    }

    public void setEquipped(Boolean equipped) {
        isEquipped = equipped;
    }

    public boolean isWearable() {
        return wearable;
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