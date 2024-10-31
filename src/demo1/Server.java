package demo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static final int PORT = 1234;
    private static Map<Integer, Contact> contacts = new HashMap<>();
    private static int nextId = 1; // 自增ID计数器

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running on port " + PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ClientHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    String[] parts = inputLine.split(" ");
                    String command = parts[0];

                    switch (command) {
                        case "VIEW":
                            for (Contact contact : contacts.values()) {
                                out.println(contact.getId() + ". 姓名：" + contact.getName() + ", 地址：" + contact.getAddress() + ", 电话号码：" + contact.getPhone() );
                            }
                            out.println("END_OF_CONTACTS");
                            break;
                        case "ADD":
                            String name = parts[1];
                            String address = parts[2];
                            String phone = parts[3];
                            int id = nextId++; // 自动生成ID
                            contacts.put(id, new Contact(id, name, address, phone));
                            out.println("联系人添加成功！");
                            break;
                        case "UPDATE":
                            id = Integer.parseInt(parts[1]);
                            name = parts[2];
                            address = parts[3];
                            phone = parts[4];
                            Contact contact = contacts.get(id);
                            if (contact != null) {
                                contact.setName(name);
                                contact.setAddress(address);
                                contact.setPhone(phone);
                                out.println("联系人更新成功！");
                            } else {
                                out.println("找不到联系人，请重试");
                            }
                            break;
                        case "DELETE":
                            int idToDelete = Integer.parseInt(parts[1]);
                            if (contacts.remove(idToDelete) != null) {
                                out.println("联系人删除成功！");
                            } else {
                                out.println("找不到联系人，请重试");
                            }
                            break;
                        default:
                            out.println("无效选项");
                            break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static class Contact {
        private int id;
        private String name;
        private String address;
        private String phone;

        public Contact(int id, String name, String address, String phone) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.phone = phone;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}