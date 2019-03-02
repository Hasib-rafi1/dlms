package Map;

public class BorrowedItem {

	private Integer numberOfDays;
    private String itemID;

    public BorrowedItem(String itemID, Integer numberOfDays ) {
        this.numberOfDays = numberOfDays;
        this.itemID = itemID;
    }

    public String getItemId() {
        return itemID;
    }

    public void setItemId(String itemId) {
        this.itemID = itemId;
    }

   
    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    @Override
    public String toString() {
        return "{" +
                "itemID='" + itemID + '\'' +
                ", numberOfDays='" + numberOfDays + '\'' +
                '}';
    }
    
}
