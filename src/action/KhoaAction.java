package action;

import connection.DatabaseConnector;
import model.Khoa;
import model.MonHoc;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class KhoaAction {
    // them khoa
    public static void addKhoa() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập mã khoa:");
        String maKhoa = sc.nextLine();
        System.out.println("Nhập tên khoa: ");
        String tenKhoa = sc.nextLine();
        System.out.println("Nhập số tiền tín chỉ:");
        String soTienTinChi = sc.nextLine();
        Connection con = DatabaseConnector.getConnection();
        Statement statement = con.createStatement();
        String sql = "INSERT INTO khoa (maKhoa, ten_khoa, hoc_phi) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, maKhoa);
        preparedStatement.setString(2, tenKhoa);
        preparedStatement.setInt(3, Integer.parseInt(soTienTinChi));

        int row = preparedStatement.executeUpdate();
        try {
            if(row > 0){
                System.out.println("Thêm khoa thành công");
            }
        }
        catch (Exception e){

        }
    }

    // show danh sách khoa
    public static void showDanhSachKhoa() throws SQLException{
        Connection con = DatabaseConnector.getConnection();
        Statement statement = con.createStatement();
        String sql = "select * from khoa";
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            System.out.println(new Khoa(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3)));
        }
    }

    // xóa khoa
    public static void deleteKhoaByMaKhoa() throws SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Hãy nhập mã khoa của khoa muốn xóa:");
        String maKhoa = sc.nextLine();
        Connection con = DatabaseConnector.getConnection();
        Statement statement = con.createStatement();
        String sql = "DELETE FROM khoa WHERE maKhoa = '" + maKhoa + "'";
        try {
            int update = statement.executeUpdate(sql);
            if(update > 0){
                System.out.println("delete complete");
            }
        }
        catch (Exception e){

        }
    }
}
