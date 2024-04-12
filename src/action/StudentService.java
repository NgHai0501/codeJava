package action;

import connection.DatabaseConnector;
import model.MonHoc;
import model.Student;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentService {
    public static List<Student> getDataFromDatabase() throws SQLException {
        List<Student> ds = new ArrayList<Student>();
        Connection con = DatabaseConnector.getConnection();
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from sinhvien");
        while (resultSet.next()){
            Student student = new Student(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6));
            ds.add(student);
        }
        return ds;
    }


    // show data
    public static void showDanhSachSinhVien() throws SQLException {
        Connection con = DatabaseConnector.getConnection();
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from sinhvien");
        while (resultSet.next()){
            System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " "
                    + resultSet.getDate(4) + " " + resultSet.getString(5));
        }
        con.close();
    }



    // findById
    public static void findById(String maSV) throws SQLException {
        Connection con = DatabaseConnector.getConnection();
        Statement statement = con.createStatement();
        String sql = "SELECT * FROM sinhvien WHERE maSV = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, maSV);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " "
                    + resultSet.getDate(4) + " " + resultSet.getString(5) + " " + resultSet.getInt(6));
        }
        con.close();
    }

    // lấy thông tin 1 sinh viên
    public static Student selectStudent(String maSV) throws SQLException {

        Connection con = DatabaseConnector.getConnection();
        Statement statement = con.createStatement();
        String sql = "SELECT * FROM sinhvien WHERE maSV = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, maSV);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            return new Student(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6));
        }
        return null;
    }


    // thêm sinh viên
    public static void addStudent() throws SQLException {
        // nhập thông tin sinh viên
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập mã sinh viên: ");
        String maSV = sc.nextLine();
        System.out.println("Nhập tên: ");
        String Ten = sc.nextLine();
        System.out.println("Nhập ngày sinh theo định dạng yyyy-mm-dd:"); // year-month-date
        String Ngaysinh = sc.nextLine();
        System.out.println("Nhập mã khoa: ");
        String maKhoa = sc.nextLine();
        System.out.println();

        String sql = "INSERT INTO sinhvien (maSV, Ten, NgaySinh, maKhoa, hocPhiDaNop) VALUES (?, ?, ?, ?, ?)" ;
        Connection con = DatabaseConnector.getConnection();
        Statement statement = con.createStatement();
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, maSV);
        preparedStatement.setString(2, Ten);
        preparedStatement.setString(3, Ngaysinh);
        preparedStatement.setString(4, maKhoa);
        preparedStatement.setInt(5, 0);

        int update = preparedStatement.executeUpdate();
        if(update > 0){
            System.out.println("add complete");
        }
        else System.out.println("add failure");
    }

    // updateDataById
    public static void updateDataStudent() throws SQLException {
        // nhập thông tin sinh viên
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập mã sinh viên của sinh viên muốn thay đổi thông tin: ");
        String maSV = sc.nextLine();
        System.out.println("Nhập tên: ");
        String Ten = sc.nextLine();
        System.out.println("Nhập ngày sinh theo định dạng yyyy-mm-dd:"); // year-month-date
        String Ngaysinh = sc.nextLine();
        System.out.println("Nhập mã khoa: ");
        String maKhoa = sc.nextLine();

        String sql = "UPDATE sinhvien SET Ten = ?, NgaySinh = ?, maKhoa = ? WHERE maSV = ?" ;
        Connection con = DatabaseConnector.getConnection();
        Statement statement = con.createStatement();
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, Ten);
        preparedStatement.setString(2, Ngaysinh);
        preparedStatement.setString(3, maKhoa);
        preparedStatement.setString(4, maSV);

        int update = preparedStatement.executeUpdate();
        if(update > 0){
            System.out.println("add complete");
        }
        else System.out.println("add failure");

    }


    // deleleById
    public static void deleteByMaSV() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập mã SV sinh viên muốn xóa:");
        String maSV = sc.nextLine();
        String sql = "DELETE FROM sinhvien WHERE maSV = ?";
        Connection con = DatabaseConnector.getConnection();
        Statement statement = con.createStatement();
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, maSV);

        int update = preparedStatement.executeUpdate();
        if(update > 0){
            System.out.println("delete thanh cong");
        }
        else{
            System.out.println("delete failure");
        }
    }

    // show học phí
    public static void showHocPhi(String maSV, String maKhoa) throws SQLException {
        Connection con = DatabaseConnector.getConnection();
        Statement statement = con.createStatement();

        // so hoc phi tromg ki
        String first = maSV.substring(1,3);
        int ki = 24 - Integer.parseInt(first);
        int sum = 0;
        int hoc_phi = 0;
        String sql = "SELECT * FROM monhoc WHERE (nam_hoc = ? AND Khoa = ?)";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, String.format("kì %d", ki));
        preparedStatement.setString(2, maKhoa);

        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            sum += resultSet.getInt(5);
        }
        String sql1 = "SELECT * FROM khoa WHERE maKhoa = ?";
        PreparedStatement preparedStatement1 = con.prepareStatement(sql1);
        preparedStatement1.setString(1, maKhoa);
        ResultSet resultSet1 = preparedStatement1.executeQuery();
        while(resultSet1.next()){
            hoc_phi = resultSet1.getInt(3);
        }
        DecimalFormat decimalFormat = new DecimalFormat("#,###,###,###.00");
        System.out.println("Số học phí trả trong kì này là: " + decimalFormat.format(sum * hoc_phi ));

        // so hoc phi da nop
        String sql2 = "SELECT * FROM sinhvien WHERE maSV = ?";
        PreparedStatement preparedStatement2 = con.prepareStatement(sql2);
        preparedStatement2.setString(1, maSV);
        ResultSet resultSet2 = preparedStatement2.executeQuery();
        while(resultSet2.next()){
            System.out.println("Số học phí đã nộp là: " + decimalFormat.format(resultSet2.getInt(6)));
        }
    }

    public static void chinhHocPhi() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection con = DatabaseConnector.getConnection();
        Statement statement = con.createStatement();

        System.out.println("Nhập khoa muốn  thay đổi");
        String maKhoa = sc.nextLine();
        System.out.println("Nhập số tiền muốn thay đổi:");
        String tien = sc.nextLine();

        String sql = "UPDATE khoa SET hoc_phi = ? WHERE maKhoa = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, Integer.parseInt(tien));
        preparedStatement.setString(2, maKhoa);
        try{
            int update = preparedStatement.executeUpdate();
            if(update > 0){
                System.out.println("chỉnh tiền thành công");
            }
        } catch (Exception e){

        }

    }
}
