package Map;

public class Item {

	private Integer itemQty;
    private String itemId;
    private String itemName;

    public Item(String itemId, String itemName, Integer itemQty ) {
        this.itemQty = itemQty;
        this.itemId = itemId;
        this.itemName = itemName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getitemName() {
        return itemName;
    }

    public void setitemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getitemQty() {
        return itemQty;
    }

    public void setitemQty(Integer itemQty) {
        this.itemQty = itemQty;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemName='" + itemName + '\'' +
                ", itemQty='" + itemQty + '\'' +
                '}';
    }
    
}
