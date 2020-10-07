package chaplinskiy.crud.model;

public class Account {
    private Long id;
    private AccountStatus status;

    public Account(AccountStatus status) {
        this.status = status;
    }

    public Account(Long id, AccountStatus status) {
        this.id = id;
        this.status = status;
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}
