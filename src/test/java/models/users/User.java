package models.users;

import java.util.Objects;

public class User {

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public Company getCompany() {
        return company;
    }

    public User(int id, String name, String username, String email, Address address, String phone, String website, Company company) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;
    }

    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && name.equals(user.name) && username.equals(user.username) && email.equals(user.email) && address.equals(user.address) && phone.equals(user.phone) && website.equals(user.website) && company.equals(user.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, email, address, phone, website, company);
    }

    public static User createExpectedUser (){
    Geo expectedGeo = new Geo("-31.8129", "62.5342");
    Address expectedAddress = new Address("Skiles Walks", "Suite 351", "Roscoeview", "33263", expectedGeo);
    Company expectedCompany = new Company("Keebler LLC", "User-centric fault-tolerant solution", "revolutionize end-to-end systems");
        return new User(5, "Chelsey Dietrich", "Kamren", "Lucio_Hettinger@annie.ca",
            expectedAddress, "(254)954-1289", "demarco.info", expectedCompany);

    }
}

