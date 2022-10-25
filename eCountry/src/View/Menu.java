/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.CountryModel;
import Model.EmployeeModel;
import Model.TypeCountryModel;
import Service.CountryService;
import Service.EmployeeService;
import Service.TypeCountryService;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class Menu extends javax.swing.JFrame {

    private TypeCountryService typeCountryService = new TypeCountryService();
    private CountryService countryService = new CountryService();
    private EmployeeService employeeService = new EmployeeService();

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
        LoadTableLoaiNuoc();
        setLocationRelativeTo(null);
        GetComboboxLoaiNuoc();
        LoadTableCountry();
        LoadTableEmployee();
    }
    String strhinhanh;

    //-----------------------------------------------------------LoaiContry---------------------------------------------------------------------------
    private void LoadTableLoaiNuoc() {
        DefaultTableModel bang = (DefaultTableModel) tableLoaiNuoc.getModel();
        typeCountryService.all();
        bang.setRowCount(0);
        for (TypeCountryModel typeCountryModel : typeCountryService.all()) {
            bang.addRow(new Object[]{typeCountryModel.getIdLoaiNuoc(),
                typeCountryModel.getTenLoaiNuoc()});
        }
    }

    private TypeCountryModel getFormData() {
        String idLoai = txtidloainuoc.getText();
        String tenLoai = txttenloainuoc.getText();

        if (idLoai.length() == 0
                || tenLoai.length() == 0) {
            JOptionPane.showMessageDialog(this, "Thông tin không được để trống");
            return null;
        }

        return new TypeCountryModel(idLoai, tenLoai);
    }

    private void ClearForm() {
        txtidloainuoc.setText("");
        txttenloainuoc.setText("");
        txtidloainuoc.setEnabled(true);
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------Nước-------------------------------------------------------------------
    private void GetComboboxLoaiNuoc() {
        cbbLoaiNuoc.removeAllItems();
        for (CountryModel countryModel : countryService.combobox()) {
            cbbLoaiNuoc.addItem(countryModel);
        }
    }

    private void LoadTableCountry() {
        DefaultTableModel bang = (DefaultTableModel) tableNuoc.getModel();
        bang.setRowCount(0);
        for (CountryModel countryModel : countryService.all()) {
            bang.addRow(new Object[]{countryModel.getIdnuoc(), countryModel.getTennuoc(), countryModel.getLoainuoc(), countryModel.getGiatien()});
        }
    }

    private String getIDtenNuoc(String Ten) {
        for (CountryModel countryModel : countryService.combobox()) {
            if (countryModel.getLoainuoc().toString().equals(Ten)) {
                return countryModel.getIdnuoc();
            }
        }
        return null;
    }

    private String getCountryMounClick(String id) {
        for (CountryModel countryModel : countryService.combobox()) {
            if (countryModel.getIdnuoc().toString().equals(id)) {
                return countryModel.getLoainuoc();
            }
        }
        return null;
    }

    private CountryModel getFormdata() {
        String idNuoc = txtidnuoc.getText();
        String tenNuoc = txttennuoc.getText();
        String giatien = txtgianuoc.getText();
        String Loainuoc = cbbLoaiNuoc.getSelectedItem().toString();
        String idCountry = getIDtenNuoc(Loainuoc);

        if (tenNuoc.length() == 0
                || giatien.length() == 0
                || idNuoc.length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống thông tin.");
            return null;
        }
        double Giatien = -1;
        try {
            Giatien = Double.parseDouble(giatien);
            if (Giatien < 0) {
                JOptionPane.showMessageDialog(this, "Giá tiền không được âm.");
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Giá tiền phải là số.");
        }

        return new CountryModel(idNuoc, tenNuoc, idCountry, Giatien);
    }

    private void ThemmoiCountry() {
        txtidnuoc.setText("");
        txttennuoc.setText("");
        txtgianuoc.setText("");
        cbbLoaiNuoc.setSelectedIndex(0);
        txtidnuoc.setEnabled(true);
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------Employee----------------------------------------------------------------------
    private void LoadTableEmployee() {
        DefaultTableModel bang = (DefaultTableModel) TableEmployee.getModel();
        bang.setRowCount(0);
        for (EmployeeModel em : employeeService.all()) {
            bang.addRow(new Object[]{
                em.getTaiKhoan(),
                em.getHoTen(),
                em.getGioiTinh(),
                em.getNgaySinh(),
                em.getSoDienThoai(),
                em.getEmail(),
                em.getDiaChi(),
                em.getHinh()
            });
        }
    }

    private EmployeeModel getFormDataEmployee() {
        String taiKhoan = txtTaiKhoan.getText();
        String matKhau = txtMatKhau.getText();
        String hoTen = txtHoTen.getText();
        String gioiTinh;
        if (raNam.isSelected()) {
            gioiTinh = "Nam";
        } else {
            gioiTinh = "Nữ";
        }
        String ngaySinh = txtNgaySinh.getText();
        String soDienThoai = txtSoDienThoai.getText();
        String email = txtEmail.getText();
        String diaChi = txtDiaChi.getText();
        String hinh = strhinhanh;
        String xnmatKhau = txtXNMatKhau.getText();

        if (taiKhoan.length() == 0
                || hoTen.length() == 0
                || ngaySinh.length() == 0
                || soDienThoai.length() == 0
                || email.length() == 0
                || diaChi.length() == 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập đủ thông tin.");
            return null;
        }

        if (!xnmatKhau.equals(matKhau)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không trùng khớp.");
            return null;
        }
        return new EmployeeModel(taiKhoan, matKhau, hoTen, gioiTinh, ngaySinh, soDienThoai, email, diaChi, hinh);
    }

    private void ClearFormEmployee() {
        txtTaiKhoan.setText("");
        txtMatKhau.setText("");
        txtXNMatKhau.setText("");
        txtHoTen.setText("");
        txtDiaChi.setText("");
        txtEmail.setText("");
        txtNgaySinh.setText("");
        txtSoDienThoai.setText("");

        txtTaiKhoan.setEnabled(true);
        txtMatKhau.setEnabled(true);
        txtXNMatKhau.setEnabled(true);
        lblhinhanh.setIcon(null);
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtidloainuoc = new javax.swing.JTextField();
        txttenloainuoc = new javax.swing.JTextField();
        btnsualoainuoc = new javax.swing.JButton();
        btnxoaloainuoc = new javax.swing.JButton();
        btnthemloainuoc = new javax.swing.JButton();
        btnlammoiloainuoc = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableLoaiNuoc = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtidnuoc = new javax.swing.JTextField();
        txttennuoc = new javax.swing.JTextField();
        btnthemnuoc = new javax.swing.JButton();
        btnsuanuoc = new javax.swing.JButton();
        btnxoanuoc = new javax.swing.JButton();
        btnlammoinuoc = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableNuoc = new javax.swing.JTable();
        cbbLoaiNuoc = new javax.swing.JComboBox<CountryModel>();
        txtgianuoc = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableEmployee = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtTaiKhoan = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        jPanel12 = new javax.swing.JPanel();
        btnSua = new javax.swing.JButton();
        btnThemMoi = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        lblhinhanh = new javax.swing.JLabel();
        raNu = new javax.swing.JRadioButton();
        raNam = new javax.swing.JRadioButton();
        txtMatKhau = new javax.swing.JPasswordField();
        txtXNMatKhau = new javax.swing.JPasswordField();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jLabel1.setText("ID Loại Nước :");

        jLabel2.setText("Tên Loại Nước :");

        btnsualoainuoc.setText("Sửa");
        btnsualoainuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsualoainuocActionPerformed(evt);
            }
        });

        btnxoaloainuoc.setText("Xóa");
        btnxoaloainuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaloainuocActionPerformed(evt);
            }
        });

        btnthemloainuoc.setText("Thêm");
        btnthemloainuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemloainuocActionPerformed(evt);
            }
        });

        btnlammoiloainuoc.setText("Làm mới");
        btnlammoiloainuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlammoiloainuocActionPerformed(evt);
            }
        });

        tableLoaiNuoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Loại Nước", "Tên Loại Nước"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableLoaiNuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableLoaiNuocMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableLoaiNuoc);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(79, 79, 79)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttenloainuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtidloainuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(btnthemloainuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnsualoainuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnxoaloainuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnlammoiloainuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(210, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtidloainuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txttenloainuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsualoainuoc)
                    .addComponent(btnxoaloainuoc)
                    .addComponent(btnlammoiloainuoc)
                    .addComponent(btnthemloainuoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        jTabbedPane2.addTab("Loại nước", jPanel5);

        jLabel3.setText("ID Nước :");

        jLabel4.setText("Tên Nước :");

        jLabel5.setText("Loại Nước :");

        jLabel6.setText("Giá Tiền :");

        btnthemnuoc.setText("Thêm");
        btnthemnuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemnuocActionPerformed(evt);
            }
        });

        btnsuanuoc.setText("Sửa");
        btnsuanuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuanuocActionPerformed(evt);
            }
        });

        btnxoanuoc.setText("Xóa");
        btnxoanuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoanuocActionPerformed(evt);
            }
        });

        btnlammoinuoc.setText("Làm mới");
        btnlammoinuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlammoinuocActionPerformed(evt);
            }
        });

        tableNuoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Nước", "Tên Nước", "Loại Nước", "Giá Tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableNuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNuocMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableNuoc);

        cbbLoaiNuoc.setModel(new javax.swing.DefaultComboBoxModel<CountryModel>());

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnthemnuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btnsuanuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btnxoanuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(btnlammoinuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtgianuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(48, 48, 48)
                            .addComponent(txtidnuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txttennuoc, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                                .addComponent(cbbLoaiNuoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtidnuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txttennuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbbLoaiNuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtgianuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthemnuoc)
                    .addComponent(btnsuanuoc)
                    .addComponent(btnxoanuoc)
                    .addComponent(btnlammoinuoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Nước", jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(196, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản Phẩm", jPanel1);

        jLabel7.setText("jLabel7");

        TableEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tài khoản", "Họ và tên", "Giới tính", "Ngày sinh", "Số điện thoại", "Email", "Địa chỉ ", "Hình ảnh"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TableEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableEmployeeMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TableEmployee);

        jLabel9.setText("Tài khoản :");

        jLabel10.setText("Mật khẩu :");

        jLabel11.setText("Xác nhận mật khẩu :");

        jLabel12.setText("Email :");

        jLabel13.setText("Địa chỉ :");

        txtDiaChi.setColumns(20);
        txtDiaChi.setRows(5);
        jScrollPane5.setViewportView(txtDiaChi);

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnThemMoi.setText("Thêm mới");
        btnThemMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMoiActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua)
                    .addComponent(btnThemMoi)
                    .addComponent(btnXoa)
                    .addComponent(btnLamMoi))
                .addGap(14, 14, 14))
        );

        jLabel16.setText("Ngày sinh :");

        jLabel17.setText("Số điện thoại :");

        jLabel14.setText("Họ tên :");

        jLabel15.setText("Giới tính :");

        lblhinhanh.setText("Thêm ảnh");
        lblhinhanh.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblhinhanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblhinhanhMouseClicked(evt);
            }
        });

        buttonGroup1.add(raNu);
        raNu.setText("Nữ");

        buttonGroup1.add(raNam);
        raNam.setText("Nam");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(68, 68, 68)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(68, 68, 68))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(39, 39, 39)))
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                            .addComponent(txtMatKhau)
                                            .addComponent(txtXNMatKhau))))
                                .addGap(42, 42, 42)
                                .addComponent(lblhinhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(raNam, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(raNu, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtXNMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(raNu)
                                    .addComponent(raNam))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblhinhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 819, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel7))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nhân Viên", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 934, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 547, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Doanh số", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 934, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 547, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Lịch sử ", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1013, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnthemloainuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemloainuocActionPerformed
        // TODO add your handling code here:
        TypeCountryModel typeCountryModel = getFormData();
        if (typeCountryModel == null) {
            return;
        }
        String idLoai = txtidloainuoc.getText();
        for (TypeCountryModel tCountryModel : typeCountryService.all()) {
            if (idLoai.equals(tCountryModel.getIdLoaiNuoc())) {
                JOptionPane.showMessageDialog(this, "Id đã tồn tại");
                return;
            }
        }
        typeCountryService.insert(typeCountryModel);
        LoadTableLoaiNuoc();
        ClearForm();
        JOptionPane.showMessageDialog(this, "Thêm thành công.");
    }//GEN-LAST:event_btnthemloainuocActionPerformed

    private void btnsualoainuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsualoainuocActionPerformed
        // TODO add your handling code here:
        TypeCountryModel typeCountryModel = getFormData();
        if (typeCountryModel == null) {
            return;
        }
        typeCountryService.update(typeCountryModel.getIdLoaiNuoc(), typeCountryModel);
        LoadTableLoaiNuoc();
        ClearForm();
        JOptionPane.showMessageDialog(this, "Sửa thành công.");
    }//GEN-LAST:event_btnsualoainuocActionPerformed

    private void btnxoaloainuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaloainuocActionPerformed
        // TODO add your handling code here:
        int row = tableLoaiNuoc.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng cần xóa.");
        }
        String xoa = (String) tableLoaiNuoc.getValueAt(row, 0);
        typeCountryService.delete(xoa);
        LoadTableLoaiNuoc();
        ClearForm();
        JOptionPane.showMessageDialog(this, "Xóa thành công.");
    }//GEN-LAST:event_btnxoaloainuocActionPerformed

    private void tableLoaiNuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLoaiNuocMouseClicked
        // TODO add your handling code here:
        int row = tableLoaiNuoc.getSelectedRow();
        txtidloainuoc.setText((String) tableLoaiNuoc.getValueAt(row, 0));
        txttenloainuoc.setText((String) tableLoaiNuoc.getValueAt(row, 1));
        txtidloainuoc.setEnabled(false);
    }//GEN-LAST:event_tableLoaiNuocMouseClicked

    private void btnlammoiloainuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlammoiloainuocActionPerformed
        // TODO add your handling code here:
        ClearForm();
    }//GEN-LAST:event_btnlammoiloainuocActionPerformed

    private void btnthemnuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemnuocActionPerformed
        // TODO add your handling code here:
        CountryModel countryModel = this.getFormdata();
        if (countryModel == null) {
            return;
        }
        String id = txtidnuoc.getText();
        for (CountryModel countrymodel : countryService.all()) {
            if (countrymodel.getIdnuoc().equals(id)) {
                JOptionPane.showMessageDialog(this, "Id đã tồn tại.");
                return;
            }
        }
        countryService.insert(countryModel);
        LoadTableCountry();
        ThemmoiCountry();
        JOptionPane.showMessageDialog(this, "Thêm thành công.");
    }//GEN-LAST:event_btnthemnuocActionPerformed

    private void btnsuanuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuanuocActionPerformed
        // TODO add your handling code here:
        CountryModel countryModel = this.getFormdata();
        if (countryModel == null) {
            return;
        }
        countryService.update(countryModel.getIdnuoc(), countryModel);
        LoadTableCountry();
        ThemmoiCountry();
        JOptionPane.showMessageDialog(this, "Sửa thành công.");
    }//GEN-LAST:event_btnsuanuocActionPerformed

    private void btnxoanuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoanuocActionPerformed
        // TODO add your handling code here:
        int row = tableNuoc.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng cần xóa.");
            return;
        }
        String xoa = (String) tableLoaiNuoc.getValueAt(row, 0);
        countryService.delete(xoa);
        LoadTableCountry();
        ThemmoiCountry();
        JOptionPane.showMessageDialog(this, "Xóa thành công.");
    }//GEN-LAST:event_btnxoanuocActionPerformed

    private void btnlammoinuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlammoinuocActionPerformed
        // TODO add your handling code here:
        ThemmoiCountry();
    }//GEN-LAST:event_btnlammoinuocActionPerformed

    private void tableNuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNuocMouseClicked
        // TODO add your handling code here:
        int row = tableNuoc.getSelectedRow();
        String ten = getCountryMounClick((String) tableNuoc.getValueAt(row, 2));
        for (int i = 0; i < cbbLoaiNuoc.getItemCount(); i++) {
            if (cbbLoaiNuoc.getItemAt(i).toString().equals(ten)) {
                cbbLoaiNuoc.setSelectedIndex(i);
            }
        }

        txtidnuoc.setText((String) tableNuoc.getValueAt(row, 0));
        txttennuoc.setText((String) tableNuoc.getValueAt(row, 1));
        txtgianuoc.setText(tableNuoc.getValueAt(row, 3).toString());
        txtidnuoc.setEnabled(false);
    }//GEN-LAST:event_tableNuocMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        EmployeeModel employeeModel = getFormDataEmployee();
        if (employeeModel == null) {
            return;
        }
        employeeService.update(employeeModel.getTaiKhoan(), employeeModel);
        LoadTableEmployee();
        ClearFormEmployee();
        JOptionPane.showMessageDialog(this, "Bạn đã cập nhật thành công");
    }//GEN-LAST:event_btnSuaActionPerformed

    private void lblhinhanhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblhinhanhMouseClicked
        // TODO add your handling code here:
        JFileChooser jchooser = new JFileChooser("C://Users//Admin//OneDrive//Pictures//Cuộn phim//");
        int re = jchooser.showOpenDialog(this);
        if (re == JFileChooser.APPROVE_OPTION) {
            strhinhanh = jchooser.getSelectedFile().getName();
            upImages(strhinhanh);
        }
    }//GEN-LAST:event_lblhinhanhMouseClicked

    private void btnThemMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMoiActionPerformed
        // TODO add your handling code here:
        EmployeeModel employeeModel = getFormDataEmployee();
        if (employeeModel == null) {
            return;
        }
        String taiKhoan = txtTaiKhoan.getText();
        for (EmployeeModel em : employeeService.all()) {
            if (em.getTaiKhoan().equals(taiKhoan)) {
                JOptionPane.showMessageDialog(this, "Tên tài khoản đã tồn tại");
                return;
            }
        }
        employeeService.insert(employeeModel);
        ClearFormEmployee();
        LoadTableEmployee();
        JOptionPane.showMessageDialog(this, "Bạn đã thêm thành công.");
    }//GEN-LAST:event_btnThemMoiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int row = TableEmployee.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng cần xóa.");
            return;
        }
        String xoa = TableEmployee.getValueAt(row, 0).toString();
        employeeService.delete(xoa);
        LoadTableEmployee();
        ClearFormEmployee();
        JOptionPane.showMessageDialog(this, "Bạn đã xóa thành công.");
    }//GEN-LAST:event_btnXoaActionPerformed

    private void TableEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableEmployeeMouseClicked
        // TODO add your handling code here:
        int row = TableEmployee.getSelectedRow();
        txtTaiKhoan.setText(TableEmployee.getValueAt(row, 0).toString());
        txtHoTen.setText(TableEmployee.getValueAt(row, 1).toString());
        if (TableEmployee.getValueAt(row, 2).toString().equals("Nam")) {
            raNam.setSelected(true);
        } else {
            raNu.setSelected(true);
        }
        txtNgaySinh.setText(TableEmployee.getValueAt(row, 3).toString());
        txtSoDienThoai.setText(TableEmployee.getValueAt(row, 4).toString());
        txtEmail.setText(TableEmployee.getValueAt(row, 5).toString());
        txtDiaChi.setText(TableEmployee.getValueAt(row, 6).toString());
        ArrayList<EmployeeModel> list = employeeService.all();
        this.upImages(list.get(row).getHinh());
        txtMatKhau.setEnabled(false);
        txtXNMatKhau.setEnabled(false);
        txtTaiKhoan.setEnabled(false);
    }//GEN-LAST:event_TableEmployeeMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        ClearFormEmployee();
    }//GEN-LAST:event_btnLamMoiActionPerformed
    private void upImages(String strhinhanh) {
        ImageIcon icon = new ImageIcon("C://Users//Admin//OneDrive//Pictures//Cuộn phim//" + strhinhanh);
        Image img = icon.getImage();
        ImageIcon icon1 = new ImageIcon(img.getScaledInstance(lblhinhanh.getWidth(), lblhinhanh.getHeight(), img.SCALE_SMOOTH));
        lblhinhanh.setIcon(icon1);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableEmployee;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThemMoi;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnlammoiloainuoc;
    private javax.swing.JButton btnlammoinuoc;
    private javax.swing.JButton btnsualoainuoc;
    private javax.swing.JButton btnsuanuoc;
    private javax.swing.JButton btnthemloainuoc;
    private javax.swing.JButton btnthemnuoc;
    private javax.swing.JButton btnxoaloainuoc;
    private javax.swing.JButton btnxoanuoc;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<CountryModel> cbbLoaiNuoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lblhinhanh;
    private javax.swing.JRadioButton raNam;
    private javax.swing.JRadioButton raNu;
    private javax.swing.JTable tableLoaiNuoc;
    private javax.swing.JTable tableNuoc;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTaiKhoan;
    private javax.swing.JPasswordField txtXNMatKhau;
    private javax.swing.JTextField txtgianuoc;
    private javax.swing.JTextField txtidloainuoc;
    private javax.swing.JTextField txtidnuoc;
    private javax.swing.JTextField txttenloainuoc;
    private javax.swing.JTextField txttennuoc;
    // End of variables declaration//GEN-END:variables
}
