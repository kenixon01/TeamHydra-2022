public class Item {

    // define variables
    private int _itemNumber;
    private String _itemName;
    private String _itemDescription;
    private int _itemRoom;
    private int _damageValue;
    private int _healValue;
    private String _itemType;
    private int _totalHpModifier;
    private int _criticalHitChance;

    public Item(int itemNum, String itemName, String itemDesc, int itemRoom, int damageValue, int healValue, String itemType, int totalHpModifier, int criticalHitChance) {
        this._itemNumber = itemNum;
        this._itemName = itemName;
        this._itemDescription = itemDesc;
        this._itemRoom = itemRoom;
        this._damageValue = damageValue;
        this._healValue = healValue;
        this._itemType = itemType;
        this._totalHpModifier = totalHpModifier;
        this._criticalHitChance = criticalHitChance;
    }

    public int get_criticalHitChance() {
        return _criticalHitChance;
    }

    public int get_totalHpModifier() {
        return _totalHpModifier;
    }

    public String get_itemType() {
        return _itemType;
    }

    public int get_itemNumber() {
        return _itemNumber;
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

    public int get_itemDamageValue() {
        return this._damageValue;
    }

    public int get_itemHealValue() {
        return this._healValue;
    }

    public int get_itemRoom() {
        return _itemRoom;
    }

    public void set_itemRoom(int _itemRoom) {
        this._itemRoom = _itemRoom;
    }



}