/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manage;

import helper.DB_Helper;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.NhanVien;
import model.User;

/**
 *
 * @author NgocTram
 */
public class UserManage {

    public User checkLogin(String tenDangNhap, String matKhau) throws Exception {
        String sql = "{call CheckLogin (?,?)}";
        try (
                Connection con = DB_Helper.openConnection(); CallableStatement cstmt = con.prepareCall(sql);) {
            cstmt.setString(1, tenDangNhap);
            cstmt.setString(2, matKhau);
            try (ResultSet rs = cstmt.executeQuery();) {
                if (rs.next()) {
                    User nd = new User();
                    nd.setTenDangNhap(tenDangNhap);
                    nd.setTen(rs.getString("ten"));
                    nd.setVaiTro(rs.getString("vaitro"));
                    nd.setMatKhau(matKhau);
                    nd.setLockout(rs.getInt("lockout"));
                    return nd;
                }
            }
        }
        return null;
    }

    //==========================================================
    public User findByMaND(String maND) throws Exception {
        String sql = "{call FindByMaND (?)}";
        try (
                Connection con = DB_Helper.openConnection(); CallableStatement cstmt = con.prepareCall(sql);) {
            cstmt.setString(1, maND);
            try (ResultSet rs = cstmt.executeQuery();) {
                if (rs.next()) {
                    User nd = new User();
                    nd.setTenDangNhap(rs.getString(1));
                    nd.setMatKhau(rs.getString(2));
                    nd.setTen(rs.getString(3));
                    nd.setVaiTro(rs.getString(4));
                    nd.setLockout(rs.getInt(5));
                    return nd;
                }
            }
            return null;
        }
    }

    public List<User> findAll() throws Exception {
        String sql = "{call FindAllUser()}";
        try (
                Connection con = DB_Helper.openConnection(); CallableStatement cstmt = con.prepareCall(sql);) {
            try (ResultSet rs = cstmt.executeQuery();) {
                List<User> list = new ArrayList<>();
                while (rs.next()) {
                    User nd = new User();
                    nd.setTenDangNhap(rs.getString(1));
                    nd.setTen(rs.getString(2));
                    nd.setVaiTro(rs.getString(3));
                    nd.setLockout(rs.getInt(4));
                    list.add(nd);
                }
                return list;
            }
        }
    }

    public boolean insert(User nd) throws Exception {
        String sql = "{call ThemND(?,?,?,?,?)}";
        try (
                Connection con = DB_Helper.openConnection(); CallableStatement cstmt = con.prepareCall(sql);) {
            cstmt.setString(1, nd.getTenDangNhap());
            cstmt.setString(3, nd.getTen());
            cstmt.setString(2, nd.getMatKhau());
            cstmt.setString(4, nd.getVaiTro());
            cstmt.setInt(5, nd.getLockout());
            
            return cstmt.executeUpdate() > 0;
        }
    }

    public boolean update(User nd) throws Exception {
        String sql = "{call UpdateUser (?,?,?)}";
        try (
                Connection con = DB_Helper.openConnection(); CallableStatement cstmt = con.prepareCall(sql);) {
            cstmt.setString(1, nd.getTenDangNhap());
            cstmt.setString(2, nd.getTen());
            cstmt.setString(3, nd.getVaiTro());
            return cstmt.executeUpdate() > 0;
        }
    }
    
    public boolean personalUpdate(User nd, String tdn) throws Exception {
        String sql = "{call PersonalUpdate (?,?,?)}";
        try (
                Connection con = DB_Helper.openConnection(); CallableStatement cstmt = con.prepareCall(sql);) {
            cstmt.setString(1, nd.getTenDangNhap());
            cstmt.setString(2, nd.getTen());
            cstmt.setString(3, tdn);
            return cstmt.executeUpdate() > 0;
        }
    }

    public boolean updateMK(String tdn, String mkm) throws Exception {
        String sql = "update nguoidung set matkhau=? "
                + "where tenDangNhap=?";
        try (
                Connection con = DB_Helper.openConnection(); PreparedStatement ptmt = con.prepareStatement(sql);) {
            ptmt.setString(1, mkm);
            ptmt.setString(2, tdn);
            return ptmt.executeUpdate() > 0;
        }
    }
    //==============================================================

    public boolean lock(String maND) throws Exception {
        String sql = "{call LockUser (?)}";
        try (
                Connection con = DB_Helper.openConnection(); CallableStatement cstmt = con.prepareCall(sql);) {
            cstmt.setString(1, maND);
            return cstmt.executeUpdate() > 0;
        }
    }
    //==============================================================
    public boolean unlock(String maND) throws Exception {
        String sql = "{call UnLockUser (?)}";
        try (
                Connection con = DB_Helper.openConnection(); CallableStatement cstmt = con.prepareCall(sql);) {
            cstmt.setString(1, maND);
            return cstmt.executeUpdate() > 0;
        }
    }
}
