package session13.baitap04;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class Main {
    // DTO cho bệnh nhân
    public static class BenhNhanDTO {
        private int maBenhNhan;
        private String ten;
        private Date ngayNhapVien;
        private List<DichVu> dsDichVu;

        // Getter/Setter
        public int getMaBenhNhan() { return maBenhNhan; }
        public void setMaBenhNhan(int maBenhNhan) { this.maBenhNhan = maBenhNhan; }

        public String getTen() { return ten; }
        public void setTen(String ten) { this.ten = ten; }

        public Date getNgayNhapVien() { return ngayNhapVien; }
        public void setNgayNhapVien(Date ngayNhapVien) { this.ngayNhapVien = ngayNhapVien; }

        public List<DichVu> getDsDichVu() { return dsDichVu; }
        public void setDsDichVu(List<DichVu> dsDichVu) { this.dsDichVu = dsDichVu; }
    }

    // DTO cho dịch vụ
    public static class DichVu {
        private int maDichVu;
        private String tenDichVu;
        private Timestamp thoiGianSuDung;

        // Getter/Setter
        public int getMaDichVu() { return maDichVu; }
        public void setMaDichVu(int maDichVu) { this.maDichVu = maDichVu; }

        public String getTenDichVu() { return tenDichVu; }
        public void setTenDichVu(String tenDichVu) { this.tenDichVu = tenDichVu; }

        public Timestamp getThoiGianSuDung() { return thoiGianSuDung; }
        public void setThoiGianSuDung(Timestamp thoiGianSuDung) { this.thoiGianSuDung = thoiGianSuDung; }
    }

    // Hàm lấy dữ liệu Dashboard
    public List<BenhNhanDTO> layDanhSachBenhNhan(Connection conn) throws SQLException {
        String sql = "SELECT b.maBenhNhan, b.ten, b.ngayNhapVien, " +
                "d.maDichVu, d.tenDichVu, d.thoiGianSuDung " +
                "FROM BenhNhan b " +
                "LEFT JOIN DichVuSuDung d ON b.maBenhNhan = d.maBenhNhan " +
                "WHERE b.khoa = 'CapCuu' AND b.ngayNhapVien = CURRENT_DATE";

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        Map<Integer, BenhNhanDTO> map = new HashMap<>();

        while (rs.next()) {
            int maBN = rs.getInt("maBenhNhan");
            BenhNhanDTO bn = map.get(maBN);

            if (bn == null) {
                bn = new BenhNhanDTO();
                bn.setMaBenhNhan(maBN);
                bn.setTen(rs.getString("ten"));
                bn.setNgayNhapVien(rs.getDate("ngayNhapVien"));
                bn.setDsDichVu(new ArrayList<>());
                map.put(maBN, bn);
            }

            // Bẫy 2: Nếu bệnh nhân chưa có dịch vụ, cột maDichVu sẽ null
            int maDV = rs.getInt("maDichVu");
            if (!rs.wasNull()) {
                DichVu dv = new DichVu();
                dv.setMaDichVu(maDV);
                dv.setTenDichVu(rs.getString("tenDichVu"));
                dv.setThoiGianSuDung(rs.getTimestamp("thoiGianSuDung"));
                bn.getDsDichVu().add(dv);
            }
        }

        rs.close();
        ps.close();

        return new ArrayList<>(map.values());
    }
}
