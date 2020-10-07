package chaplinskiy.crud.model;

import java.util.Set;

public class Customer {
    private Long id;
    private String name;
    private String surname;
    private Set<Specialty> specialties;
    private Account account;

    public Customer() {
    }

    public Customer(Long id, String name, String surname, Set<Specialty> specialties, Account account) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.specialties = specialties;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", specialties=" + specialties +
                ", account=" + account +
                '}';
    }
}
