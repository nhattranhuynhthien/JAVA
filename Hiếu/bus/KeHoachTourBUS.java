package org.example.bus;

import org.example.dao.KeHoachTourDAO;
import org.example.dto.KeHoachTourDTO;
import org.example.dto.LoaiTourDTO;
import org.example.dto.TourDTO;

import javax.swing.*;
import java.util.ArrayList;

public class KeHoachTourBUS {
    private ArrayList<KeHoachTourDTO> lsKeHoachTour;
    KeHoachTourDAO keHoachTourDAO;

    public KeHoachTourBUS(){
        lsKeHoachTour = new ArrayList<>();
        keHoachTourDAO = new KeHoachTourDAO();
    }

    public ArrayList<KeHoachTourDTO> getAllKeHoachTours(){
        lsKeHoachTour = keHoachTourDAO.getAllKeHoachTours();
        return lsKeHoachTour;
    }

    public ArrayList<KeHoachTourDTO> getAllKeHoachToursByID(String maTour){
        lsKeHoachTour = keHoachTourDAO.getAllKeHoachTours();
        ArrayList<KeHoachTourDTO> lsKeHoachToursID = new ArrayList<>();

        for(KeHoachTourDTO kt : lsKeHoachTour){
            if(kt.getMaTour().trim().equalsIgnoreCase(maTour)){
                lsKeHoachToursID.add(kt);
            }
        }

        return lsKeHoachToursID;
    }

    public boolean addKeHoachTour(KeHoachTourDTO t){
        if(t == null) return false;

        boolean success = keHoachTourDAO.addKeHoachTour(t);
        if(success) lsKeHoachTour.add(t);
        return success;
    }

    public void editKeHoachTour(KeHoachTourDTO t){
        keHoachTourDAO.editKeHoachTour(t);
    }

    public boolean removeKeHoachTour(String matour){
        boolean success = keHoachTourDAO.removeKeHoachTour(matour);
        return success;
    }

    public KeHoachTourDTO getById(String maKHTour){
        KeHoachTourDTO result = new KeHoachTourDTO();
        for (KeHoachTourDTO kt : lsKeHoachTour){
            if(kt.getMaKHTour().trim().equalsIgnoreCase(maKHTour)) {
                result = kt;
                break;
            }
        }
        return result;
    }

    public boolean existedKeHoachTourWithID(String maKHTour){
        for (KeHoachTourDTO kt : lsKeHoachTour){
            if(kt.getMaTour().trim().equalsIgnoreCase(maKHTour))
                return true;
        }
        return false;
    }
}
