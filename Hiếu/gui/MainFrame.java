package org.example.gui;

import org.example.gui.panel.*;

import javax.swing.*;
import java.awt.*;

public class _MainFrame extends JFrame {

    //layout
    private CardLayout cardLayout;
    private JPanel contentArea;
    private JButton activeButton;

    public _MainFrame() {
        setTitle("Quản lý Tour du lịch");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(buildSideBar(), BorderLayout.WEST);
        add(buildContentArea(), BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel buildSideBar(){
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(30, 90, 160));
        sidebar.setPreferredSize(new Dimension(190, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        // Logo
//        sidebar.add(buildLogo());

        //menu items: label, cardName
        String[][] menus = {
                {"Tour", "Tour"},
                {"Loại Tour", "LoaiTour"},
                {"Kế Hoạch Tour", "KeHoachTour"},
                {"Hóa đơn", "HoaDon"},
                {"Khách hàng", "KhachHang"},
                {"Nhân viên", "NhanVien"},
                {"Địa điểm", "DiaDiem"},
                {"Thống kê", "ThongKe"}
        };

        for(String[] m : menus){
            JButton btn = createMenuButton(m[0], m[1]);
            sidebar.add(btn);
            sidebar.add(Box.createRigidArea(new Dimension(0, 2)));
        }
        sidebar.add(Box.createVerticalGlue());

        return sidebar;
    }

    private JPanel buildContentArea(){
        cardLayout = new CardLayout();
        contentArea = new JPanel(cardLayout);
        contentArea.setBackground(Color.cyan);

        contentArea.add(new _TourPanel(), "Tour");
        contentArea.add(new _LoaiTourPanel(), "LoaiTour");
        contentArea.add(new _KeHoachTourPanel(), "KeHoachTour");
        contentArea.add(new HoaDonPanel(), "HoaDon");
        contentArea.add(new DiaDiemPanel(), "DiaDiem");
        contentArea.add(new KhachHangPanel(), "KhachHang");
        contentArea.add(new NhanVienPanel(), "NhanVien");
        contentArea.add(new DiaDiemPanel(), "DiaDiem");
        contentArea.add(new _StatisticsPanel(), "ThongKe");

        return contentArea;
    }

    private JButton createMenuButton(String text, String card){
        JButton btn = new JButton(text) {
            @Override protected void paintComponent(Graphics g) {
                if (this == activeButton) {
                    g.setColor(new Color(255, 255, 255, 30));
                    g.fillRoundRect(8, 4, getWidth() - 16, getHeight() - 8, 10, 10);
                } else if (getModel().isRollover()) {
                    g.setColor(Color.blue);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
                super.paintComponent(g);
            }
        };
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btn.setForeground(Color.WHITE);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setMaximumSize(new Dimension(190, 48));
        btn.setPreferredSize(new Dimension(190, 48));
        btn.setBorder(BorderFactory.createEmptyBorder(0, 24, 0, 0));

        btn.addActionListener(e -> {

            if(activeButton != null) // fix color painted
                activeButton.repaint();

            activeButton = btn;
            btn.repaint();
            cardLayout.show(contentArea, card);
        });
        return btn;
    }
}
