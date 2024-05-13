package bookMarket.model;

public abstract class User {
    // 사용자 아이디
    private String userId;
    // 사용자 이름
    private String userName;
    // 사용자 이메일 주소
    private String email;

    // 생성자
    public User(String userId, String userName, String email) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }

    // 게터 및 세터 메서드
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
