package model;

public class Student {
    private int id;
    private String maSV;
    private String name;
    private String date;
    private String khoa;
    private int soTienHocDaDong;

    public Student(int id, String maSV, String name, String date, String khoa, int soTienDaDong) {
        this.id = id;
        this.maSV = maSV;
        this.name = name;
        this.date = date;
        this.khoa = khoa;
        this.soTienHocDaDong = soTienDaDong;
    }


    public int getId() {
        return id;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public int getSoTienHocDaDong() {
        return soTienHocDaDong;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", maSV='" + maSV + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", khoa='" + khoa + '\'' +
                '}';
    }
}
