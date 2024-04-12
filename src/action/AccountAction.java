package action;

import connection.DatabaseConnector;

import java.sql.*;
import java.util.Scanner;

public class AccountAction {

    // check tai khoan va mat khau chinh xac
    public static int dangNhap(String username, String password) throws SQLException {
        Connection con = DatabaseConnector.getConnection();
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from taikhoan");
        while (resultSet.next()) {
            if (username.equals(resultSet.getString(2)) && password.equals(resultSet.getString(3))
                    && resultSet.getString(4).equals("user")) {
                System.out.println("dang nhap thanh cong");
                return 1;
            } else if (username.equals(resultSet.getString(2)) && password.equals(resultSet.getString(3))
                    && resultSet.getString(4).equals("admin")) {
                System.out.println("dang nhap thanh cong");
                return 2;
            }
        }
        return 0;
    }

    public static void dangKi() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập tên tài khoản muốn đăng kí:");
        String taikhoan = sc.nextLine();
        System.out.println("Nhập mật khẩu muốn đăng kí:");
        String matKhau = sc.nextLine();
        System.out.println("Nhập quyền muốn đăng kí (user/admin):");
        String quyen = sc.nextLine();

        Connection con = DatabaseConnector.getConnection();
        Statement statement = con.createStatement();
        String sql = "insert into taikhoan (maSV, matKhau, Quyen) values (?,?,?)";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1,taikhoan);
        preparedStatement.setString(2,matKhau);
        preparedStatement.setString(3,quyen);

        int update = preparedStatement.executeUpdate();
        if(update > 0){
            System.out.println("Dang ki thanh cong");
        }
        else System.out.println("Dang ki khong thanh cong");

    }

}
