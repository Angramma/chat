package ru.levelp.java.junior.Chat2;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(
                name = User.FindByLogin,
                query = "select u from User u where u.login = :login"
        ),
        @NamedQuery(
                name = User.FindByNickname,
                query = "select u from User u where u.nickname like :nickname"
        )
})
@Table(name = "users")
public class User {
    public static final String RootUserName = "root";
    public static final String FindByLogin = "Users.findByLogin";
    public static final String FindByNickname = "Users.findByNickname";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id", updatable = false, insertable = false)
    private int userId;

    @Column(unique = true)
    private String login;

    @Column (unique = true)
    private String nickname;

    @Column
    private String password;

    @Column(name = "del_date")
    private Date delDate;

    @Column(name = "del_user")
    private String delUser;

    @Column(name = "navi_date")
    private Timestamp naviDate;


    // @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
   // private List<Message> messages;

    public User() {


    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDelDate() {
        return delDate;
    }

    public void setDelDate(Date delDate) {
        this.delDate = delDate;
    }

    public String getDelUser() {
        return delUser;
    }

    public void setDelUser(String delUser) {
        this.delUser = delUser;
    }

    public Timestamp getNaviDate() {
        return naviDate;
    }

    public void setNaviDate(Timestamp naviDate) {
        this.naviDate = naviDate;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

   /* public double getBalance() {
        return balance;
    }*/

  /*  public void setBalance(double balance) {
        this.balance = balance;
    }*/

  /*  public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }*/
}
