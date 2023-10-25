/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manage;

import java.sql.CallableStatement;
import helper.DB_Helper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.NhanVien;

/**
 *
 * @author NgocTram
 */
public class NhanVienManage {

    public boolean insert(NhanVien nv) throws Exception {
        String sql = "{call ThemNV(?,?,?,?,?,?,?)}";
        try (
                Connection con = DB_Helper.openConnection(); 
                CallableStatement cstmt = con.prepareCall(sql);) {
            cstmt.setString(1, nv.getMaNV());
            cstmt.setString(2, nv.getHoten());
            cstmt.setString(3, nv.getLuong());
            cstmt.setString(4, nv.getEmail());
            cstmt.setString(5, nv.getSdt());
            cstmt.setString(6, nv.getGioiTinh());
            cstmt.setString(7, nv.getDiachi());

            return cstmt.executeUpdate() > 0;
        }
    }

    //==========================================================================
    public boolean update(NhanVien nv) throws Exception {
        String sql = "{call UpdateNhanVien (?,?,?,?,?,?,?)}";
        try (
                Connection con = DB_Helper.openConnection();
                CallableStatement cstmt = con.prepareCall(sql);)  {
            cstmt.setString(1, nv.getMaNV());
            cstmt.setString(2, nv.getHoten());
            cstmt.setString(3, nv.getLuong());
            cstmt.setString(4, nv.getEmail());
            cstmt.setString(5, nv.getSdt());
            cstmt.setString(6, nv.getGioiTinh());
            cstmt.setString(7, nv.getDiachi());
            return cstmt.executeUpdate() > 0;
        }
    }

    //==============================================================
    public boolean delete(String maNV) throws Exception {
        String sql = "{call DeleteNhanVien (?)}";
        try (
                Connection con = DB_Helper.openConnection(); 
                CallableStatement cstmt = con.prepareCall(sql);)  {
            cstmt.setString(1, maNV);
            return cstmt.executeUpdate() > 0;
        }
    }

    //==========================================================
    public NhanVien findByMaNV(String maNV) throws Exception {
        String sql = "select * from nhanvien "
                + "where maNhanVien=?";
        try (
                Connection con = DB_Helper.openConnection(); PreparedStatement ptmt = con.prepareStatement(sql);) {
            ptmt.setString(1, maNV);
            try (ResultSet rs = ptmt.executeQuery();) {
                if (rs.next()) {
                    NhanVien nv = new NhanVien();
                    nv.setMaNV(rs.getString(1));
                    nv.setHoten(rs.getString(2));
                    nv.setLuong(rs.getString(3));
                    nv.setEmail(rs.getString(4));
                    nv.setSdt(rs.getString(5));
                    nv.setGioiTinh(rs.getString(6));
                    nv.setDiachi(rs.getString(7));
                    return nv;
                }
            }
            return null;
        }
    }
    //=========================================================

    public List<NhanVien> findAll() throws Exception {
        String sql = "select * from nhanvien;";
        try (
                Connection con = DB_Helper.openConnection(); PreparedStatement ptmt = con.prepareStatement(sql);) {
            try (ResultSet rs = ptmt.executeQuery();) {
                List<NhanVien> list = new ArrayList<>();
                while (rs.next()) {
                    NhanVien nv = new NhanVien();
                    nv.setMaNV(rs.getString(1));
                    nv.setHoten(rs.getString(2));
                    nv.setLuong(rs.getString(3));
                    nv.setEmail(rs.getString(4));
                    nv.setSdt(rs.getString(5));
                    nv.setGioiTinh(rs.getString(6));
                    nv.setDiachi(rs.getString(7));
                    list.add(nv);
                }
                return list;
            }
        }
    }
}
