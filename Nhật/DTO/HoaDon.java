/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package DTO;
import java.util.*;
/**
 *
 * @author Nhat
 */
public class HoaDon {
    public static Scanner sc = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    private String MaHD;
    private String MaKHTour;
    private String MaKHDat;
    private String MaNV;
    private float TongTien;

    public HoaDon(String MaHD, String MaKHTour, String MaKHDat, String MaNV, float TongTien) {
        this.MaHD = MaHD;
        this.MaKHTour = MaKHTour;
        this.MaKHDat = MaKHDat;
        this.MaNV = MaNV;
        this.TongTien = TongTien;
    }

    public String getMaHD() {
        return MaHD;
    }

    public String getMaKHTour() {
        return MaKHTour;
    }

    public String getMaKHDat() {
        return MaKHDat;
    }

    public String getMaNV() {
        return MaNV;
    }

    public float getTongTien() {
        return TongTien;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public void setMaKHTour(String MaKHTour) {
        this.MaKHTour = MaKHTour;
    }

    public void setMaKHDat(String MaKHDat) {
        this.MaKHDat = MaKHDat;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public void setTongTien(float TongTien) {
        this.TongTien = TongTien;
    }
    
    public void TinhTien(){
        
    }
    public void Nhap(){
        while(true){
           System.out.println("Nhap ma hoa don : ");
           MaHD=sc.nextLine();
           if(MaHD.matches("^HD\\d{3}$")){
               break;
           }
        }
        
        while(true){
           System.out.println("Nhap ma ke hoach tour : ");
           MaKHTour=sc.nextLine();
           if(MaKHTour.matches("^KHT\\d{3}$")){
               break;
           }
        }
        
        while(true){
           System.out.println("Nhap ma khach hang dat : ");
           MaKHDat=sc.nextLine();
           if(MaKHDat.matches("^KH\\d{3}$")){
               break;
           }
        }
        
        while(true){
           System.out.println("Nhap ma nhan vien : ");
           MaNV=sc.nextLine();
           if(MaNV.matches("^NV\\d{3}$")){
               break;
           }
        }
    }
    public void Xuat(){
        System.out.printf("%-10s %-10s %-10s %-10s %-10s\n","MaHD" ,"MaKHTour","MaKHDat","MaNV","TongTien");
        System.out.printf("%-10s %-10s %-10s %-10s %,15.0f\n",MaHD,MaKHTour,MaKHDat,MaNV,TongTien);
    }
   
}
