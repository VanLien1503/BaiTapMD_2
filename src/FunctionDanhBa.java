import javafx.scene.shape.Path;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FunctionDanhBa extends DanhBa {
    // Tạo 1 Arraylist:
    public ArrayList<DanhBa> arrayList = new ArrayList<>();

    // tao menu
    public void menu() {
        System.out.println("================");
        System.out.println("Menu:");
        System.out.println("Xem : 1");
        System.out.println("Thêm: 2");
        System.out.println("Sửa : 3");
        System.out.println("Xóa : 4");
        System.out.println("Tìm : 5");
        System.out.println("Đọc : 6");
        System.out.println("Ghi : 7");
        System.out.println("exit: 8");
        System.out.println("================");
        System.out.println("Lua tron Chuc Nang :");
        Scanner scanner = new Scanner(System.in);
        int value = scanner.nextInt();

        switch (value) {
            case 1:
                if (arrayList.isEmpty()) {
                    System.out.println(" danh ba trống:");
                    menu();
                    break;
                } else {
                    arrayList.forEach(k -> System.out.println(k));
                    menu();
                    break;
                }
            case 2:
                System.out.println("them 1 Danh ba moi");
                addDanhBa();
                System.out.println("----------------");
                menu();
                break;
            case 3:
                System.out.println("Cập Nhật...!");
                update(searchDanhBa(scanner.nextInt()));
                menu();
                break;
            case 4:
                System.out.println("xóa....!");
                delete(searchDanhBa(scanner.nextInt()));
                menu();
                break;
            case 5:
                System.out.println("Nhap SDT can tim : ");
                searchDanhBa(scanner.nextInt());
                System.out.println("----------------");
                menu();
                break;
            case 6:
                System.out.println("đọc File...!");
                read("fileDemo.txt");
                menu();
                break;
            case 7:
                System.out.println("ghi File...!");
                writerToFile_CSV(arrayList,"fileCSV.csv");
                writerToFile(arrayList, "fileDemo.txt");
                menu();
                break;
            case 8:
                System.exit(Integer.parseInt("kết thúc"));
            default:
                System.out.println("Chon Sai Moi Tron Lai...!");
                menu();
                break;
        }
    }

    // nhập dữ liệu từ bàn phím:
    private DanhBa inputFromKeyboard() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" AddPhone : ");
        int phone = scanner.nextInt();
        scanner.nextLine();

        System.out.println("AddTem : ");
        String tem = scanner.nextLine();

        System.out.println("AddName: ");
        String name = scanner.nextLine();

        System.out.println("AddGrender : ");
        String gender = scanner.nextLine();

        System.out.println("Address : ");
        String address = scanner.nextLine();

        System.out.println("addDateOfBirth : ");
        String dateOfBirth = scanner.nextLine();

        System.out.println("email : ");
        String email = scanner.nextLine();

        return new DanhBa(phone, tem, name, gender, address, dateOfBirth, email);
    }

    // Thêm vào danh bạ:

    public DanhBa addDanhBa() {
        DanhBa danhBa;
        danhBa = inputFromKeyboard();
        arrayList.add(danhBa);
        return danhBa;
    }

    // Tìm Kiếm
    public int searchDanhBa(int phone) {
        int index = 0;
        boolean check = false;
        DanhBa checkDanhBa = null;
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getPhone() == phone) {
                checkDanhBa = arrayList.get(i);
                check = true;
                index = i;
            } else {
                check = false;
            }
        }
        if (check) {
            System.out.println(" Tìm Thấy Số Phone : " + phone);

        } else {
            System.out.println("Không Tìm Thấy...!");
        }
        return index;

    }

    //Xửa
    public void update(int i) {
        System.out.println("Nhập Lại Thông Tin Muốn Sửa....!");
        arrayList.set(i, inputFromKeyboard());
        System.out.println(" Thông Tin sau Khi Xửa : ");
        for (DanhBa list : arrayList) {
            System.out.println(list);
            menu();
        }
    }

    // Xóa
    public void delete(int i) {
        System.out.println(" Xóa Danh Bạ: ");
        arrayList.remove(i);
        menu();

    }


    // ghi du lieu vao file
    public void writerToFile(ArrayList<DanhBa> arrayList, String path) {
        FileOutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            outputStream = new FileOutputStream(path);
            objectOutputStream = new ObjectOutputStream(outputStream);

            System.out.println("Writing....!");
            for (DanhBa danhBa : arrayList) {
                objectOutputStream.writeObject(danhBa);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //ghi file csv
    public void writerToFile_CSV(ArrayList<DanhBa> arrayList, String path) {
        File file = new File(path);
        FileWriter fileWriter=null;
        try {
           fileWriter = new FileWriter(file);
            fileWriter.append("phone,Tem,Name,Gender,address,BirthDay,Email");
            fileWriter.append("\n");
            for (DanhBa danhBa : arrayList) {
                fileWriter.append(String.valueOf(danhBa.getPhone()));
                fileWriter.append(",");

                fileWriter.append(danhBa.getTem());
                fileWriter.append(",");

                fileWriter.append(danhBa.getName());
                fileWriter.append(",");

                fileWriter.append(danhBa.getGender());
                fileWriter.append(",");

                fileWriter.append(danhBa.getAddress());
                fileWriter.append(",");

                fileWriter.append(danhBa.getDateOfBirth());
                fileWriter.append(",");

                fileWriter.append(danhBa.getEmail());
                fileWriter.append(",");
                fileWriter.append("\n");
            }
            System.out.println("CSV file was created successfully !!!");

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Doc File text

    public List<DanhBa> read(String path) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream;
        List<DanhBa> list = new ArrayList<>();
        try {
            fileInputStream = new FileInputStream(path);
            objectInputStream = new ObjectInputStream(fileInputStream);

            DanhBa danhBa = null;
            while ((danhBa = (DanhBa) objectInputStream.readObject()) != null) {
                list.add(danhBa);
//                arrayList.add(danhBa);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e) {
            System.out.println("het file");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

}