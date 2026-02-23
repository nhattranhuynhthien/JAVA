/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DsCTietHD;
import java.util.ArrayList;
import DTO.CTietHD;
/**
 *
 * @author Nhat
 */
public class CTHoaDonBus {
    public static ArrayList<CTietHD> ds;
    public static DsCTietHD dao;
    
    public CTHoaDonBus(){
    if(ds==null){
        dao=new DsCTietHD();
        ds=dao.getDs();
    }
}
    
    public void docDs(){
        ds=dao.getDs();
    }
    
    public static ArrayList<CTietHD> getDs(){
        return ds;
    }
    
    public boolean timCtiethd(CTietHD ct){
        for(CTietHD cthd:ds){
            if(ct.getMaHD().equals(cthd.getMaHD()) && ct.getMaKHDi().equals(cthd.getMaKHDi()))
                return true;
                }
        return false;
    }
    
    public CTietHD timCt(String mact,String makh){
        for(CTietHD cthd:ds){
            if(cthd.getMaHD().equals(mact) && cthd.getMaKHDi().equals(makh))
                return cthd;
        }
        return null;
    }
        
    public boolean themCTietHd(CTietHD ct){
        ds.add(ct);
        dao.themCtietHD(ct);
        return true;
    }
    
    public boolean xoaCtietHd(CTietHD mact){
        boolean flag=false;
        if(timCtiethd(mact)==false){
            flag=false;
        } else{
            ds.remove(mact);
            flag=true;
        }
        if(dao.TimHD(mact.getMaHD())==null){
            flag=false;
        }else {
            dao.themCtietHD(mact);
            flag=true;
        }
        return flag;
    }
    
    public boolean suaCtiethd(CTietHD ct){
        boolean flag=false;
        if(timCtiethd(ct)!=true){
            flag=false;
        }else {
            flag=true;
            for(int i=0;i<ds.size();i++){
                if(ds.get(i).getMaHD().equals(ct.getMaHD()) && ds.get(i).getMaKHDi().equals(ct.getMaKHDi())){
                    ds.set(i,ct);
                    flag=true;
                }
            }
        }
        if(dao.TimHD(ct.getMaHD())==null){
            flag=false;
        }else {
            dao.suaCthd(ct);
        }
        return flag; 
   }
    public ArrayList<DTO.CTietHD> getDstheoma(String mahd){
        DAO.DsCTietHD dao=new DAO.DsCTietHD();
        return dao.getDstheoma(mahd);
    }
}
