package ua.lardi.phoneBook.model;

public class ContactBuilder {
    private Contact contact;

    public ContactBuilder() {
        contact = new Contact();
    }

    public ContactBuilder setId(Long id) {
        contact.setId(id);
        return this;
    }

    public ContactBuilder setLastName(String lastName) {
        contact.setLastName(lastName);
        return this;
    }

    public ContactBuilder setFirstName(String firstName) {
        contact.setFirstName(firstName);
        return this;
    }

    public ContactBuilder setMiddleName(String middleName) {
        contact.setMiddleName(middleName);
        return this;
    }

    public ContactBuilder setMobilePhone(String mobilePhone) {
        contact.setMobilePhone(mobilePhone);
        return this;
    }

    public ContactBuilder setHomePhone(String homePhone) {
        contact.setHomePhone(homePhone);
        return this;
    }

    public ContactBuilder setAddress(String address) {
        contact.setAddress(address);
        return this;
    }

    public ContactBuilder setEmail(String email) {
        contact.setEmail(email);
        return this;
    }

    public Contact build() {
        return contact;
    }
}
