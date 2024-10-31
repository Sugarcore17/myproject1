package demo2;

import java.util.List;
import java.util.Scanner;

public class AddressBookUI {
    private Scanner scanner;
    private ContactService contactService;

    public AddressBookUI(ContactService contactService) {
        this.contactService = contactService;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("\n个人通讯录");
            System.out.println("----------------");
            System.out.println("1. 查看联系人");
            System.out.println("2. 添加联系人");
            System.out.println("3. 修改联系人");
            System.out.println("4. 删除联系人");
            System.out.println("5. 退出通讯录");
            System.out.print("选择一个选项: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewContacts();
                    break;
                case 2:
                    addContact();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    System.out.println("退出中...");
                    return;
                default:
                    System.out.println("无效选项，请再次尝试");
            }
        }
    }

    private void addContact() {
        System.out.print("输入姓名: ");
        String name = scanner.nextLine();
        System.out.print("输入地址: ");
        String address = scanner.nextLine();
        System.out.print("输入电话号码: ");
        String phone = scanner.nextLine();

        Contact contact = new Contact(name, address, phone);
        contactService.addContact(contact);
        System.out.println("联系人添加成功！");
    }

    private void deleteContact() {
        System.out.print("输入要删除的ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean result = contactService.deleteContact(id);
        if (result) {
            System.out.println("联系人删除成功！");
        } else {
            System.out.println("找不到联系人，请重试");
        }
    }

    private void updateContact() {
        System.out.print("输入要更新的ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("输入新姓名: ");
        String name = scanner.nextLine();
        System.out.print("输入新地址: ");
        String address = scanner.nextLine();
        System.out.print("输入新电话号码: ");
        String phone = scanner.nextLine();

        Contact contact = new Contact(name, address, phone);
        contact.setId(id);

        boolean result = contactService.updateContact(contact);
        if (result) {
            System.out.println("联系人更新成功！");
        } else {
            System.out.println("找不到联系人，请重试");
        }
    }

    private void viewContacts() {
        List<Contact> contacts = contactService.getAllContacts();
        if (contacts.isEmpty()) {
            System.out.println("没有联系人");
        } else {
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }

    public static void main(String[] args) {
        ContactService contactService = new ContactServiceImpl(new ContactDAOImpl());
        AddressBookUI ui = new AddressBookUI(contactService);
        ui.run();
    }
}
