package model;

import java.util.ArrayList;

public class Khoa {
    private String maKhoa;
    private String tenKhoa;
    private ArrayList<MonHoc> listMonHoc;
    private int soTienTinChi;

    public Khoa(String maKhoa, String tenKhoa, int soTienTinChi) {
        this.maKhoa = maKhoa;
        this.tenKhoa = tenKhoa;
        this.soTienTinChi = soTienTinChi;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public ArrayList<MonHoc> getListMonHoc() {
        return listMonHoc;
    }

    public void setListMonHoc(ArrayList<MonHoc> listMonHoc) {
        this.listMonHoc = listMonHoc;
    }

    @Override
    public String toString() {
        return "Khoa{" +
                "maKhoa='" + maKhoa + '\'' +
                ", tenKhoa='" + tenKhoa + '\'' +
                ", soTienTinChi=" + soTienTinChi +
                '}';
    }
}
