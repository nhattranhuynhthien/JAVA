```java
package GUI.dialog;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.CardLayout;

public class CTrinhKMDialog extends javax.swing.JDialog {

    private BUS.CTrinhKMBUS bus = new BUS.CTrinhKMBUS();

    private CardLayout card;
    private JPanel pnlSwitch;

    private JPanel pnlHoaDon;
    private JPanel pnlTour;

    private JTextField txtGiaApDung;

    private JTable tblTour;
    private DefaultTableModel tourModel;

    private ButtonGroup group;

    public CTrinhKMDialog() {
        initComponents();
        setTitle("Chương trình khuyến mãi");
        setLocationRelativeTo(null);

        group = new ButtonGroup();
        group.add(rdoHd);
        group.add(rdoTour);

        initPanelSwitch();
    }

    private void initPanelSwitch(){

        card = new CardLayout();
        pnlSwitch = new JPanel(card);

        /* PANEL HÓA ĐƠN */

        pnlHoaDon = new JPanel();
        pnlHoaDon.setBorder(BorderFactory.createTitledBorder("Điều kiện hóa đơn"));

        JLabel lblGia = new JLabel("Giá áp dụng:");
        txtGiaApDung = new JTextField(15);

        pnlHoaDon.add(lblGia);
        pnlHoaDon.add(txtGiaApDung);


        /* PANEL TOUR */

        pnlTour = new JPanel(new java.awt.BorderLayout());
        pnlTour.setBorder(BorderFactory.createTitledBorder("Danh sách tour áp dụng"));

        String[] col = {"Chọn","Mã Tour","Tên Tour"};

        tourModel = new DefaultTableModel(col,0){
            public Class getColumnClass(int column){
                return column==0 ? Boolean.class : String.class;
            }
        };

        tblTour = new JTable(tourModel);

        pnlTour.add(new JScrollPane(tblTour));

        pnlSwitch.add(pnlHoaDon,"HOADON");
        pnlSwitch.add(pnlTour,"TOUR");

        getContentPane().add(pnlSwitch);

        loadTour();
    }

    private void loadTour(){

        tourModel.setRowCount(0);

        try{

            DAO.TourDAO daoTour = new DAO.TourDAO();
            ArrayList<DTO.Tour> ds = daoTour.getDsTour();

            for(DTO.Tour t : ds){

                tourModel.addRow(new Object[]{
                    false,
                    t.getMaTour(),
                    t.getTenTour()
                });

            }

        }catch(Exception e){

            System.out.println("Lỗi load tour");

        }
    }

    private void resetForm(){

        txtMaCTKM.setText("");
        txtTenctkm.setText("");
        txtNgayBD.setText("");
        txtNgayKT.setText("");
        txtChietkhau.setText("");
        txtGhichu.setText("");
        txtGiaApDung.setText("");

        rdoHd.setSelected(true);
        card.show(pnlSwitch,"HOADON");

    }

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {

        try {

            String ma = txtMaCTKM.getText().trim();
            String ten = txtTenctkm.getText().trim();
            String ngaybd = txtNgayBD.getText().trim();
            String ngaykt = txtNgayKT.getText().trim();

            boolean hinhthuc = rdoHd.isSelected();

            float chietkhau = Float.parseFloat(txtChietkhau.getText().trim());
            String ghichu = txtGhichu.getText().trim();

            float giaApDung = 0;

            ArrayList<String> dsTour = new ArrayList<>();

            if(hinhthuc){

                giaApDung = Float.parseFloat(txtGiaApDung.getText());

            }else{

                for(int i=0;i<tblTour.getRowCount();i++){

                    boolean chon = (boolean) tblTour.getValueAt(i,0);

                    if(chon){

                        dsTour.add(tblTour.getValueAt(i,1).toString());

                    }

                }
            }

            DTO.CTrinhKM ct = new DTO.CTrinhKM(
                    ma,ten,ngaybd,ngaykt,
                    hinhthuc,
                    chietkhau,
                    ghichu
            );

            ct.setGiaApDung(giaApDung);
            ct.setDsTour(dsTour);

            if(bus.timCTrinhKM(ma)==null){

                bus.themCTrinhKM(ct);

                JOptionPane.showMessageDialog(this,
                        "Thêm chương trình khuyến mãi thành công");

                resetForm();

            }else{

                bus.suaCTrinhKM(ct);

                JOptionPane.showMessageDialog(this,
                        "Sửa chương trình khuyến mãi thành công");

                dispose();
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    "Dữ liệu nhập không hợp lệ");

        }

    }

    private void rdoHdActionPerformed(java.awt.event.ActionEvent evt) {

        card.show(pnlSwitch,"HOADON");

    }

    private void rdoTourActionPerformed(java.awt.event.ActionEvent evt) {

        card.show(pnlSwitch,"TOUR");

    }

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {

        resetForm();

    }

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {

        dispose();

    }

    private void initComponents() {

        jLabel1 = new JLabel("Mã chương trình khuyến mãi");
        txtMaCTKM = new JTextField(15);

        jLabel2 = new JLabel("Tên chương trình khuyến mãi");
        txtTenctkm = new JTextField(15);

        jLabel3 = new JLabel("Ngày bắt đầu");
        txtNgayBD = new JTextField(10);

        jLabel4 = new JLabel("Ngày kết thúc");
        txtNgayKT = new JTextField(10);

        jLabel5 = new JLabel("Hình thức khuyến mãi");

        rdoHd = new JRadioButton("Khuyến mãi hóa đơn");
        rdoTour = new JRadioButton("Khuyến mãi tour");

        rdoHd.addActionListener(this::rdoHdActionPerformed);
        rdoTour.addActionListener(this::rdoTourActionPerformed);

        jLabel6 = new JLabel("Chiết khấu");
        txtChietkhau = new JTextField(10);

        jLabel7 = new JLabel("Ghi chú");
        txtGhichu = new JTextField(15);

        btnLuu = new JButton("Lưu");
        btnReset = new JButton("Reset");
        btnHuy = new JButton("Hủy");

        btnLuu.addActionListener(this::btnLuuActionPerformed);
        btnReset.addActionListener(this::btnResetActionPerformed);
        btnHuy.addActionListener(this::btnHuyActionPerformed);

        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

        add(jLabel1);
        add(txtMaCTKM);

        add(jLabel2);
        add(txtTenctkm);

        add(jLabel3);
        add(txtNgayBD);

        add(jLabel4);
        add(txtNgayKT);

        add(jLabel5);

        add(rdoHd);
        add(rdoTour);

        add(jLabel6);
        add(txtChietkhau);

        add(jLabel7);
        add(txtGhichu);

        setSize(500,600);
    }

    private JLabel jLabel1,jLabel2,jLabel3,jLabel4,jLabel5,jLabel6,jLabel7;
    private JTextField txtMaCTKM,txtTenctkm,txtNgayBD,txtNgayKT,txtChietkhau,txtGhichu;
    private JRadioButton rdoHd,rdoTour;
    private JButton btnLuu,btnReset,btnHuy;
}
```
