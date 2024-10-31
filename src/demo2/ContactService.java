package demo2;

import java.util.List;
public interface ContactService {
    void addContact(Contact contact);
    boolean deleteContact(int id);
    boolean updateContact(Contact contact);
    List<Contact> getAllContacts();
}