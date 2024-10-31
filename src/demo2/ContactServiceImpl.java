package demo2;

import java.util.List;

public class ContactServiceImpl implements ContactService {
    private ContactDAO contactDAO;

    public ContactServiceImpl(ContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    @Override
    public void addContact(Contact contact) {
        contactDAO.addContact(contact);
    }

    @Override
    public boolean deleteContact(int id) {
        return contactDAO.deleteContact(id);
    }

    @Override
    public boolean updateContact(Contact contact) {
        return contactDAO.updateContact(contact);
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactDAO.getAllContacts();
    }
}