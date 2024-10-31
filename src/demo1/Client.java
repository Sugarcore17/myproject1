package demo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            while (true) {
                System.out.println("\n个人通讯录");
                System.out.println("----------------");
                System.out.println("1. 查看联系人");
                System.out.println("2. 添加联系人");
                System.out.println("3. 修改联系人");
                System.out.println("4. 删除联系人");
                System.out.println("5. 退出通讯录");
                System.out.print("选择一个选项: ");

                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        out.println("VIEW");
                        System.out.println("联系人:");
                        String line;
                        while ((line = in.readLine()) != null) {
                            if (line.equals("END_OF_CONTACTS")) {
                                break;
                            }
                            System.out.println(line);
                        }
                        break;
                    case 2:
                        System.out.print("输入姓名: ");
                        String name = scanner.nextLine();
                        System.out.print("输入地址: ");
                        String address = scanner.nextLine();
                        System.out.print("输入电话号码: ");
                        String phone = scanner.nextLine();
                        out.println("ADD " + name + " " + address + " " + phone);
                        System.out.println(in.readLine());
                        break;
                    case 3:
                        System.out.print("输入要更新的ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("输入新姓名: ");
                        name = scanner.nextLine();
                        System.out.print("输入新地址: ");
                        address = scanner.nextLine();
                        System.out.print("输入新电话号码: ");
                        phone = scanner.nextLine();
                        out.println("UPDATE " + id + " " + name + " " + address + " " + phone);
                        System.out.println(in.readLine());
                        break;
                    case 4:
                        System.out.print("输入要删除的ID: ");
                        id = scanner.nextInt();
                        scanner.nextLine();
                        out.println("DELETE " + id);
                        System.out.println(in.readLine());
                        break;
                    case 5:
                        System.out.println("退出中...");
                        return;
                    default:
                        System.out.println("无效选项，请再次尝试");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}