package action;

import connection.DatabaseConnector;
import model.MonHoc;

import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;

public class MonHocAction {

    //getdatafromdatabase

    // them mon hoc
    public static void addMonHoc() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập mã môn học:");
        String maMonHoc = sc.nextLine();
        System.out.println("Nhập tên môn học:");
        String tenMonHoc = sc.nextLine();
        System.out.println("Nhập mã khoa:");
        String maKhoa = sc.nextLine();
        System.out.println("Nhập kì học:");
        String kiHoc = sc.nextLine();
        System.out.println("Nhập số tín chỉ:");
        String soTinChi = sc.nextLine();


        Connection con = DatabaseConnector.getConnection();
        Statement statement = con.createStatement();
        String sql = "INSERT INTO monhoc (maMH, TenMH, Khoa, tin_chi, nam_hoc) " +
                "VALUES ('" + maMonHoc + "', '" + tenMonHoc + "', '" + maKhoa + "', " + Integer.parseInt(soTinChi) + ", '" + kiHoc + "')";
        try {
            int update = statement.executeUpdate(sql);
            if(update > 0){
                System.out.println("them thanh cong");
            }
        }
        catch (Exception e){

        }
    }

    // xem danh sach mon học
    public static void showDanhSachMonHoc() throws SQLException{
        Connection con = DatabaseConnector.getConnection();
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from monhoc");
        while (resultSet.next()){
            System.out.println(new MonHoc(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6)));
        }
    }

    // delete monhoc by Khoa

    public static void deleteMonHocByMaMH() throws SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.println("nhập mã môn học muốn xóa:");
        String maMonHoc = sc.nextLine();


        Connection connection = DatabaseConnector.getConnection();
        Statement statement = connection.createStatement();
        String sql = "DELETE FROM monhoc WHERE maMH = '" + maMonHoc + "'";
        try {
            int update = statement.executeUpdate(sql);
            if(update > 0){
                System.out.println("Xoa thanh cong");
            }
        }
        catch (Exception e){

        }
    }

    public static void showChuongTrinhDaoTao(String maKhoa) throws SQLException{
        Connection con = DatabaseConnector.getConnection();
        Statement statement = con.createStatement();
        String sql = "SELECT * FROM monhoc WHERE Khoa = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, maKhoa);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println(new MonHoc(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6)));
        }
    }

    public static void showMonHocTrongKi(String maSV, String maKhoa) throws SQLException {
        Connection con = DatabaseConnector.getConnection();
        Statement statement = con.createStatement();

        String first = maSV.substring(1,3);
        int ki = 24 - Integer.parseInt(first);
        String sql = "SELECT * FROM monhoc WHERE (nam_hoc = ? AND Khoa = ?)";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, String.format("kì %d", ki));
        preparedStatement.setString(2, maKhoa);

        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            System.out.println(new MonHoc(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6) ));
        }
        con.close();
        preparedStatement.close();

    }
}
