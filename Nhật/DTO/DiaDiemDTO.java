/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.dto;

import java.time.LocalDate;

/**
 *
 * @author Nhat
 */
public class DiaDiemDTO {
    public String TenDiaDiem;
    public LocalDate NgayThucHien; 
    public float TongChi;

    public DiaDiemDTO(String TenDiaDiem, LocalDate NgayThucHien, float TongChi) {
        this.TenDiaDiem = TenDiaDiem;
        this.NgayThucHien = NgayThucHien;
        this.TongChi = TongChi;
    }
    
    public DiaDiemDTO() {
        this.TenDiaDiem = TenDiaDiem;
        this.NgayThucHien = NgayThucHien;
        this.TongChi = TongChi;
    }
    
    public String getTenDiaDiem() {
        return TenDiaDiem;
    }

    public LocalDate getNgayThucHien() {
        return NgayThucHien;
    }

    public float getTongChi() {
        return TongChi;
    }

    public void setTenDiaDiem(String TenDiaDiem) {
        this.TenDiaDiem = TenDiaDiem;
    }

    public void setNgayThucHien(LocalDate NgayThucHien) {
        this.NgayThucHien = NgayThucHien;
    }

    public void setTongChi(float TongChi) {
        this.TongChi = TongChi;
    }
    
    
}
