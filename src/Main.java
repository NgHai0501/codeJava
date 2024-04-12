import action.AccountAction;
import action.KhoaAction;
import action.MonHocAction;
import action.StudentService;
import model.Khoa;
import model.Student;

import java.sql.*;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static boolean checkLogin = false;
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Hello and welcome");
            System.out.println("Menu");
            System.out.println("1. Đăng nhập");
            System.out.println("2. Kết thúc chương trình");
            System.out.println("Nhập tùy chọn: ");
            int tuychon = sc.nextInt();
            switch (tuychon){
                case 1:
                    if(!checkLogin){
                        pressAnyKey();
                        batdau();
                        checkLogin = true;
                    }
                    else{
                        checkLogin = false;
                    }
                    break;
                case 2:
                    System.exit(0);
                default:
                    System.out.println("tùy chọn không có trong danh sách, hãy chọn lại!");
                    pressAnyKey();
                    break;
            }
        }
    }

    public static void batdau() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("hello to my website");
        System.out.println("Nhap tai khoan:");
        String taikhoan = sc.nextLine();
        System.out.println("Nhap mat khau:");
        String matkhau = sc.nextLine();
        int check = AccountAction.dangNhap(taikhoan, matkhau);
        if(check!=0){
            checkLogin = true;
            if(check == 2){
                adminAction(taikhoan);
            }
            else if(check == 1){
                userAction(taikhoan);
            }
        }
        else{
            System.out.println("Tai khoan va mat khau khong chinh xac!");
            pressAnyKey();
        }
    }

    public static void adminAction(String name) throws SQLException {
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        while(check){
            System.out.println("hello "+ name);
            System.out.println("Menu");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Xóa sinh viên");
            System.out.println("3. Sửa thông tin sinh viên");
            System.out.println("4. Show danh sách sinh viên");
            System.out.println("5. Thêm khoa");
            System.out.println("6. Xóa khoa");
            System.out.println("7. Show danh sách khoa");
            System.out.println("8. Thêm môn học");
            System.out.println("9. Xóa môn học");
            System.out.println("10. Show danh sách môn học");
            System.out.println("11. Chỉnh sửa học phí");
            System.out.println("12. Đăng kí tài khoản cho sinh viên");
            System.out.println("13. Đăng xuất");
            System.out.println("Nhập tùy chọn:");
            int tuychon = sc.nextInt();
            switch (tuychon){
                case 1: // them sinh vien
                    StudentService.addStudent();
                    pressAnyKey();
                    break;
                case 2: // xoa sinh vien
                    StudentService.deleteByMaSV();
                    pressAnyKey();
                    break;
                case 3: // sửa thông tin sinh viên
                    StudentService.updateDataStudent();
                    pressAnyKey();
                    break;
                case 4: // show danh sách sinh viên
                    StudentService.showDanhSachSinhVien();
                    pressAnyKey();
                    break;
                case 5: // thêm khoa
                    KhoaAction.addKhoa();
                    pressAnyKey();
                    break;
                case 6: // xóa khoa
                    KhoaAction.deleteKhoaByMaKhoa();
                    pressAnyKey();
                    break;
                case 7: // show danh sach khoa
                    KhoaAction.showDanhSachKhoa();
                    pressAnyKey();
                    break;
                case 8: // thêm môn học
                    MonHocAction.addMonHoc();
                    pressAnyKey();
                    break;
                case 9: // Xóa môn học
                    MonHocAction.deleteMonHocByMaMH();
                    pressAnyKey();
                    break;
                case 10: // show mon hoc
                    MonHocAction.showDanhSachMonHoc();
                    pressAnyKey();
                    break;
                case 11: // chỉnh học phí
                    StudentService.chinhHocPhi();
                    pressAnyKey();
                    break;
                case 12: // đãng kí tài khoản sinh viên
                    AccountAction.dangKi();
                    pressAnyKey();
                    break;
                case 13: // đăng xuất
                    checkLogin = false;
                    check = false;
                    System.out.println("Tiến hành đăng xuất...");
                    pressAnyKey();
                    break;
                default:
                    System.out.println("Nhập sai tùy chọn, vui lòng nhập lại");
                    pressAnyKey();
                    break;
            }
        }
    }

    public static void userAction(String taikhoan) throws SQLException {
        Scanner sc = new Scanner(System.in);
        Student student = StudentService.selectStudent(taikhoan);
        boolean check = true;
        while (check){
            System.out.println("hello "+ taikhoan);
            System.out.println("Menu");
            System.out.println("1. Thông tin sinh viên");
            System.out.println("2. Chương trình đào tạo");
            System.out.println("3. Môn học trong kì này");
            System.out.println("4. Xem học phí");
            System.out.println("5. Đăng xuất");
            System.out.println("Nhập tùy chọn:");
            int tuychon = sc.nextInt();

            switch (tuychon){
                case 1: // thông tin sinh vien
                    StudentService.findById(taikhoan);
                    pressAnyKey();
                    break;
                case 2: // chương trình đào tạo
                    MonHocAction.showChuongTrinhDaoTao(student.getKhoa());
                    pressAnyKey();
                    break;
                case 3: // môn học trong kì này
                    MonHocAction.showMonHocTrongKi(student.getMaSV(), student.getKhoa());
                    pressAnyKey();
                    break;
                case 4: // xem hoc phi
                    StudentService.showHocPhi(student.getMaSV(), student.getKhoa());
                    pressAnyKey();
                    break;
                case 5:
                    checkLogin = false;
                    check = false;
                    System.out.println("Tiến hành đăng xuất...");
                    pressAnyKey();
                    break;
                default:
                    System.out.println("Lựa chọn không phù hợp, vui lòng chọn lại!");
                    pressAnyKey();
                    break;
            }
        }
    }

    public static void pressAnyKey() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\nNhấn phím bất kỳ để tiếp tục...");
        scanner.nextLine(); // Đọc một dòng từ bàn phím, chờ người dùng nhấn Enter
        clearScreen();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J"); // Xóa màn hình
        System.out.flush(); // Flush để đảm bảo màn hình được xóa ngay lập tức
    }
}