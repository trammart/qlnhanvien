/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author NgocTram
 */
public class User {
    private String tenDangNhap, matKhau, ten, vaiTro;
    private int lockout;

    public User() {
    }

    public User(String tenDangNhap, String matKhau, String ten, String vaiTro, int lockout) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.ten = ten;
        this.vaiTro = vaiTro;
        this.lockout = lockout;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
    
    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }
    
    public int getLockout() {
        return lockout;
    }

    public void setLockout(int lockout) {
        this.lockout = lockout;
    }
    
}

