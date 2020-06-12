/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Laundry;
import java.awt.GraphicsEnvironment;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Praise13
 */
public class pengguna extends javax.swing.JFrame {
    private DefaultTableModel model;
    public static int statusSearching = 0;
    
    Connection con;
    Statement st;
    
    int xy;
    int xx;


    public pengguna() {
        initComponents();
        db_koneksi data = new db_koneksi();
        data.config();
        con = data.con;
        st = data.st;
        model = (DefaultTableModel)tabel_peng.getModel();
    }

    
    private void tampil_tabel_peng() {
        try {
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM pengguna");
                ResultSetMetaData rsmd = rs.getMetaData();
                    int columnCount = rsmd.getColumnCount();
                DefaultTableModel tm = (DefaultTableModel) tabel_peng.getModel();
                tm.setColumnCount(5);
                    for (int i = 6; i <= columnCount; i++ ) {
                    tm.addColumn(rsmd.getColumnName(i));
                }
                    tm.setRowCount(0);
                    while (rs.next()) {
                    String[] a = new String[columnCount];
                        for(int i = 0; i < columnCount; i++) {
                        a[i] = rs.getString(i+1);
                    }
                    tm.addRow(a);
                }
                tm.fireTableDataChanged();
                rs.close();
                st.close();
                } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex, ex.getMessage(), WIDTH, null);
                }
       }
    
    private void simpan_peng() {
        String UserPeng = username_peng.getText();
        String PassPeng = password_peng.getText();
        String NamaPeng = nama_peng.getText();

        try {
            st = con.createStatement();
            String sqlSimpan = "insert into pengguna values (NULL,'"+UserPeng+"','"+PassPeng+"','"+hakakses.getSelectedItem()+"','"+NamaPeng+"') ";
            st.executeUpdate(sqlSimpan);
            JOptionPane.showMessageDialog(null, "Data Berhasil Masuk!!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Tak Masuk " + e.getMessage());
        }
    }
    
    private void klik_tabel_peng() {
        String PengID = tabel_peng.getValueAt(tabel_peng.getSelectedRow(), 0).toString();
        String PengUser = tabel_peng.getValueAt(tabel_peng.getSelectedRow(), 1).toString();
        String PengPass = tabel_peng.getValueAt(tabel_peng.getSelectedRow(), 2).toString();
        String PengHak = tabel_peng.getValueAt(tabel_peng.getSelectedRow(), 3).toString();
        String PengNama = tabel_peng.getValueAt(tabel_peng.getSelectedRow(), 4).toString();
        
        id_peng.setText(PengID);
        username_peng.setText(PengUser);
        password_peng.setText(PengPass);
        nama_peng.setText(PengNama);
    }
    
    private void ubah_data() {
        int index = tabel_peng.getSelectedRow();
        String id = tabel_peng.getValueAt(index, 0).toString();
        
        String IDPENG = id_peng.getText();
        String User = username_peng.getText();
        String Pass = password_peng.getText();
        String Nama = nama_peng.getText();
        
        
        try {

            st = con.createStatement();
            String sqlUpdate = "UPDATE pengguna SET id_pengguna='"+IDPENG+"',username='"+User+"',password='"+Pass+"',nama_pengguna='"+Nama+"' WHERE id_pengguna='"+id+"' ";
            st.executeUpdate(sqlUpdate);

        JOptionPane.showMessageDialog(null, "Data Berhasil di Ubah");
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Data Gagal di Ubah " + e.getMessage());
            }
        }
    
    private void hapus_data() {
            String[] options = {"Yes", "No"};
            JOptionPane.showOptionDialog(null, "Yakin Hapus Data Ini??", "Delete Confirm", 
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

            String id = id_peng.getText();

            try {

            st = con.createStatement();
            String sqlUpdate = "DELETE FROM pengguna WHERE id_pengguna='"+id+"'";
            st.executeUpdate(sqlUpdate);
            
            
            username_peng.setText("");
            password_peng.setText("");
        
            nama_peng.setText("");
            id_peng.setText("Auto Generate");
            

        JOptionPane.showMessageDialog(null, "Data Dihapus dari Database");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus " + e.getMessage());
            }
        
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
        tabel_peng = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        id_peng = new javax.swing.JTextField();
        username_peng = new javax.swing.JTextField();
        password_peng = new javax.swing.JTextField();
        nama_peng = new javax.swing.JTextField();
        hakakses = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        tambah_peng = new javax.swing.JButton();
        simpan_peng = new javax.swing.JButton();
        hapus_peng = new javax.swing.JButton();
        ubah_peng = new javax.swing.JButton();
        tutup_peng = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 204, 204));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 204));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PENGGUNA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
        );

        tabel_peng.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Pengguna", "Username", "Password", "Hak Akses", "Nama Lengkap"
            }
        ));
        tabel_peng.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_pengMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_peng);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));

        jLabel2.setText("User ID");

        jLabel3.setText("Username");

        jLabel4.setText("Password");

        jLabel5.setText("Nama Lengkap");

        hakakses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user" }));
        hakakses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hakaksesActionPerformed(evt);
            }
        });

        jLabel6.setText("Hak Akses");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6))
                .addGap(55, 55, 55)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(id_peng)
                        .addComponent(username_peng)
                        .addComponent(password_peng)
                        .addComponent(nama_peng, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
                    .addComponent(hakakses, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(id_peng, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3))
                            .addComponent(username_peng, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4))
                    .addComponent(password_peng, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hakakses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nama_peng, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        tambah_peng.setBackground(new java.awt.Color(0, 204, 204));
        tambah_peng.setText("Tambah");
        tambah_peng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambah_pengActionPerformed(evt);
            }
        });

        simpan_peng.setBackground(new java.awt.Color(0, 204, 204));
        simpan_peng.setText("Simpan");
        simpan_peng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpan_pengActionPerformed(evt);
            }
        });

        hapus_peng.setBackground(new java.awt.Color(0, 204, 204));
        hapus_peng.setText("Hapus");
        hapus_peng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapus_pengActionPerformed(evt);
            }
        });

        ubah_peng.setBackground(new java.awt.Color(0, 204, 204));
        ubah_peng.setText("Ubah");
        ubah_peng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubah_pengActionPerformed(evt);
            }
        });

        tutup_peng.setBackground(new java.awt.Color(0, 204, 204));
        tutup_peng.setText("Tutup");
        tutup_peng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tutup_pengActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tambah_peng)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(simpan_peng)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(hapus_peng)
                .addGap(29, 29, 29)
                .addComponent(ubah_peng)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(tutup_peng))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tambah_peng)
                            .addComponent(simpan_peng)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(hapus_peng))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ubah_peng)
                            .addComponent(tutup_peng))))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tambah_pengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambah_pengActionPerformed
        username_peng.setText("");
        password_peng.setText("");
        
        nama_peng.setText("");
        
        try {
            st = con.createStatement();
            String sql = "SELECT * FROM pengguna ORDER BY id_pengguna DESC";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                int hmmmm = Integer.parseInt(rs.getString("id_pengguna"))+1;
                id_peng.setText(Integer.toString(hmmmm));
            } else {
                id_peng.setText("0001");//sesuaikan dengan variable namenya
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }   
    }//GEN-LAST:event_tambah_pengActionPerformed

    private void simpan_pengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpan_pengActionPerformed
        simpan_peng();
        tampil_tabel_peng();
    }//GEN-LAST:event_simpan_pengActionPerformed

    private void hapus_pengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapus_pengActionPerformed
       hapus_data();
       tampil_tabel_peng();
    }//GEN-LAST:event_hapus_pengActionPerformed

    private void ubah_pengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubah_pengActionPerformed
        ubah_data();
        tampil_tabel_peng();
    }//GEN-LAST:event_ubah_pengActionPerformed

    private void tutup_pengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tutup_pengActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_tutup_pengActionPerformed

    private void tabel_pengMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_pengMouseClicked
         klik_tabel_peng();
    }//GEN-LAST:event_tabel_pengMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil_tabel_peng();
    }//GEN-LAST:event_formWindowOpened

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x-xx,y-xy);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void hakaksesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hakaksesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hakaksesActionPerformed

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
            java.util.logging.Logger.getLogger(pengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pengguna().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> hakakses;
    private javax.swing.JButton hapus_peng;
    private javax.swing.JTextField id_peng;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nama_peng;
    private javax.swing.JTextField password_peng;
    private javax.swing.JButton simpan_peng;
    private javax.swing.JTable tabel_peng;
    private javax.swing.JButton tambah_peng;
    private javax.swing.JButton tutup_peng;
    private javax.swing.JButton ubah_peng;
    private javax.swing.JTextField username_peng;
    // End of variables declaration//GEN-END:variables
}
