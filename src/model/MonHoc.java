package model;

public class MonHoc {
    private int id;
    private String maMonHoc;
    private String tenMonHoc;
    private String maKhoa;
    private int soTinChi;
    private String kiHoc;

    public MonHoc(int id, String maMonHoc, String tenMonHoc, String maKhoa, int soTinChi, String kiHoc) {
        this.id = id ;
        this.maMonHoc = maMonHoc;
        this.tenMonHoc = tenMonHoc;
        this.maKhoa = maKhoa;
        this.kiHoc = kiHoc;
        this.soTinChi = soTinChi;
    }

    public String getMaMonHoc() {
        return maMonHoc;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public String getKiHoc() {
        return kiHoc;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    @Override
    public String toString() {
        return "MonHoc{" +
                "id=" + id +
                ", maMonHoc='" + maMonHoc + '\'' +
                ", tenMonHoc='" + tenMonHoc + '\'' +
                ", maKhoa='" + maKhoa + '\'' +
                ", soTinChi=" + soTinChi +
                ", kiHoc='" + kiHoc + '\'' +
                '}';
    }
}
