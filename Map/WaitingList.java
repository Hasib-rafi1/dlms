package Map;

public class WaitingList {

    private String userName;

    public WaitingList(String userName ) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Override
    public String toString() {
        return "{" +
                "userName='" + userName + '\'' +
                '}';
    }
    
}
