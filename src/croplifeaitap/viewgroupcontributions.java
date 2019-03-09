/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package croplifeaitap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author albert
 */
public class viewgroupcontributions extends javax.swing.JFrame {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form viewgroupcontributions
     */
    public viewgroupcontributions() {
        initComponents();
        con = connecttodb.Connectdb();
        update();
        totalsum();
        groupsum();
        PFCsum();
        REGsum();
        Asum();
        Bsum();
        Csum();
        Dsum();
    }

    public void totalsum() {
        try {
            String sql = "SELECT  sum(amount) FROM `membercontributions` ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                total.setText(rs.getString("sum(amount)"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
     public void PFCsum() {
        try {
            String sql = "SELECT  sum(amount) FROM `membercontributions` where contributiontype='PROJECT FUND CONTRIBUTION'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                pfc.setText(rs.getString("sum(amount)"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
      public void REGsum() {
        try {
            String sql = "SELECT  sum(amount) FROM `membercontributions`WHERE contributiontype='REGISTRATION' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                reg.setText(rs.getString("sum(amount)"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
public void Asum() {
        try {
            String sql = "SELECT  sum(amount) FROM `membercontributions` WHERE zone='A' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                za.setText(rs.getString("sum(amount)"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
public void Bsum() {
        try {
            String sql = "SELECT  sum(amount) FROM `membercontributions` inner join groups on (membercontributions.group=groups.groupname)WHERE groups.groupzone='B' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                zb.setText(rs.getString("sum(amount)"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
public void Csum() {
        try {
            String sql = "SELECT  sum(amount) FROM `membercontributions` WHERE zone='C' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                zc.setText(rs.getString("sum(amount)"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
public void Dsum() {
        try {
            String sql = "SELECT  sum(amount) FROM `membercontributions` inner join groups on (membercontributions.group=groups.groupname)WHERE groups.groupzone='D' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                zd.setText(rs.getString("sum(amount)"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    public void groupsum() {
        int sum = 0;
        for (int i = 0; i < table1.getRowCount(); i++) {
            sum = sum + Integer.parseInt(table1.getValueAt(i, 5).toString());
        }
        group.setText(Integer.toString(sum));
    }

    public void serching() {
        String cmb1 = grp_combo1.getSelectedItem().toString();
        String cmb2 = grp_combo2.getSelectedItem().toString();
        String cmb3 = grp_combo3.getSelectedItem().toString();
        if (cmb1.equalsIgnoreCase("GROUP NAME")) {

            try {
                String sql = "SELECT  `referencenumber` as 'Ref No', "
                        + "`group` as 'Group',"
                        + " `contributiontype` as'Type',"
                        + " `contributionfrequency` as 'Frequency', `fnumber`, "
                        + " (amount*10)as 'Amount', `date` as'Reg Date', `month` FROM `membercontributions` where `group` like'" + srch_txt1.getText() + "%' GROUP by date";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                table1.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        } else if (cmb1.equalsIgnoreCase("CONTRIBUTION TYPE")) {

            try {
                String sql = "SELECT  `referencenumber` as 'Ref No', "
                        + "`group` as 'Group',"
                        + " `contributiontype` as'Type',"
                        + " `contributionfrequency` as 'Frequency', `fnumber`, "
                        + " (amount*10)as 'Amount', `date` as'Reg Date', `month` FROM `membercontributions` where `contributiontype` ='" + srch_txt1.getText() + "' GROUP by date";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                table1.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
        groupsum();
    }

    public void serching2() {
        String cmb1 = grp_combo1.getSelectedItem().toString();
        String cmb2 = grp_combo2.getSelectedItem().toString();
        String cmb3 = grp_combo3.getSelectedItem().toString();
        if (cmb2.equalsIgnoreCase("GROUP NAME") && cmb3.equalsIgnoreCase("CONTRIBUTION TYPE")) {

            try {
                String sql = "SELECT  `referencenumber` as 'Ref No', "
                        + "`group` as 'Group',"
                        + " `contributiontype` as'Type',"
                        + " `contributionfrequency` as 'Frequency', `fnumber`, "
                        + " (amount*10)as 'Amount', `date` as'Reg Date', `month` FROM `membercontributions` where `group` ='" + srch_txt2.getText() + "' && `contributiontype` ='" + srch_txt3.getText() + "'GROUP by date";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                table1.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            groupsum();
        } else if (cmb2.equalsIgnoreCase("GROUP NAME") && cmb3.equalsIgnoreCase("DATE")) {

            try {
                String sql = "SELECT  `referencenumber` as 'Ref No', "
                        + "`group` as 'Group',"
                        + " `contributiontype` as'Type',"
                        + " `contributionfrequency` as 'Frequency', `fnumber`, "
                        + " (amount*10)as 'Amount', `date` as'Reg Date', `month` FROM `membercontributions` where `group` ='" + srch_txt2.getText() + "' && `date` ='" + srch_txt3.getText() + "'GROUP by date";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                table1.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            groupsum();
        }

    }

    public void update() {
        try {
            String sql = "SELECT  `referencenumber` as 'Ref No', "
                    + "`group` as 'Group',"
                    + " `contributiontype` as'Type',"
                    + " `contributionfrequency` as 'Frequency', `fnumber`, "
                    + " amount, `date` as'Reg Date', `month` FROM `membercontributions` ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        groupsum();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        srch_txt2 = new javax.swing.JTextField();
        grp_combo2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        grp_combo3 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        grp_combo1 = new javax.swing.JComboBox<>();
        srch_txt1 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        group = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        srch_txt3 = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        pfc = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        reg = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        pfc1 = new javax.swing.JLabel();
        za = new javax.swing.JLabel();
        zb = new javax.swing.JLabel();
        zc = new javax.swing.JLabel();
        zd = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("VIEW CONTRIBUTIONS FOR THE GROUP");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(274, 274, 274)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(table1);

        jLabel2.setText("SEARCH BY:");

        grp_combo2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        grp_combo2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GROUP NAME", "CONTRIBUTION TYPE", " ", " " }));

        jLabel3.setText("AND");

        grp_combo3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        grp_combo3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GROUP NAME", "CONTRIBUTION TYPE", "DATE", " ", " " }));
        grp_combo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_combo3ActionPerformed(evt);
            }
        });

        jLabel4.setText("SEARCH BY:");

        grp_combo1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        grp_combo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GROUP NAME", "CONTRIBUTION TYPE", " ", " " }));

        srch_txt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                srch_txt1KeyReleased(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/croplifeaitap/refresh.png"))); // NOI18N
        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("TOTAL CONTRIBUTION KSH");

        jLabel6.setText("GROUP CONTRIBUTION KSH");

        group.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        group.setForeground(new java.awt.Color(0, 204, 204));

        total.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        total.setForeground(new java.awt.Color(0, 204, 204));

        srch_txt3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                srch_txt3KeyReleased(evt);
            }
        });

        pfc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pfc.setForeground(new java.awt.Color(255, 0, 0));

        jLabel7.setText("PFC CONTRIBUTION  KSH");

        reg.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        reg.setForeground(new java.awt.Color(255, 0, 0));

        jLabel8.setText("REGISTRATION  KSH");

        pfc1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pfc1.setForeground(new java.awt.Color(255, 0, 0));

        za.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        za.setForeground(new java.awt.Color(0, 204, 204));

        zb.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        zb.setForeground(new java.awt.Color(0, 204, 204));

        zc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        zc.setForeground(new java.awt.Color(0, 204, 204));

        zd.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        zd.setForeground(new java.awt.Color(0, 204, 204));

        jLabel9.setText("ZONE A:");

        jLabel10.setText("ZONE B:");

        jLabel11.setText("ZONE C:");

        jLabel12.setText("ZONE D:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(20, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1085, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(grp_combo1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(srch_txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(grp_combo2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(srch_txt2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(grp_combo3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(srch_txt3, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(reg, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(pfc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(zc, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(za, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(56, 56, 56)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(zd, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(zb, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(group, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(289, 289, 289)))))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(170, 170, 170)
                    .addComponent(pfc1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(795, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)
                        .addComponent(group, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pfc, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(za, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(zb, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(reg, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(zc, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(zd, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(grp_combo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(srch_txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(srch_txt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grp_combo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(grp_combo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(srch_txt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(55, 55, 55)
                    .addComponent(pfc1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(488, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void srch_txt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_srch_txt1KeyReleased
        serching();
    }//GEN-LAST:event_srch_txt1KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        update();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void grp_combo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_combo3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grp_combo3ActionPerformed

    private void srch_txt3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_srch_txt3KeyReleased
        serching2();
    }//GEN-LAST:event_srch_txt3KeyReleased

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
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(viewgroupcontributions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewgroupcontributions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewgroupcontributions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewgroupcontributions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new viewgroupcontributions().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel group;
    private javax.swing.JComboBox<String> grp_combo1;
    private javax.swing.JComboBox<String> grp_combo2;
    private javax.swing.JComboBox<String> grp_combo3;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel pfc;
    private javax.swing.JLabel pfc1;
    private javax.swing.JLabel reg;
    private javax.swing.JTextField srch_txt1;
    private javax.swing.JTextField srch_txt2;
    private javax.swing.JTextField srch_txt3;
    private javax.swing.JTable table1;
    private javax.swing.JLabel total;
    private javax.swing.JLabel za;
    private javax.swing.JLabel zb;
    private javax.swing.JLabel zc;
    private javax.swing.JLabel zd;
    // End of variables declaration//GEN-END:variables
}
