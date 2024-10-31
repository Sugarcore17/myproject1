package demo2;

import java.util.ArrayList;
import java.util.List;



public class ContactDAOImpl implements ContactDAO {
    private List<Contact> contacts;
    private int nextId = 1;

    public ContactDAOImpl() {
        contacts = new ArrayList<>();
    }

    @Override
    public void addContact(Contact contact) {
        contact.setId(nextId++);
        contacts.add(contact);
    }

    @Override
    public boolean deleteContact(int id) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId() == id) {
                contacts.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateContact(Contact contact) {
        for (Contact c : contacts) {
            if (c.getId() == contact.getId()) {
                c.setName(contact.getName());
                c.setAddress(contact.getAddress());
                c.setPhone(contact.getPhone());
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Contact> getAllContacts() {
        return contacts;
    }
}
