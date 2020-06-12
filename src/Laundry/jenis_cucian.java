/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Laundry;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Praise13
 */
public class jenis_cucian extends javax.swing.JFrame {

    private DefaultTableModel model;
    public static int statusSearching = 0;
    
    Connection con;
    Statement st;
    
    int xy;
    int xx;
    /**
     * Creates new form jenis_cucian
     */
    public jenis_cucian() {
        initComponents();
        db_koneksi data = new db_koneksi();
        data.config();
        con = data.con;
        st = data.st;
        model = (DefaultTableModel)tabel_jc.getModel();
    }   
    private void tambah_data() {
        jenis_jc.setText("");
        harga_jc.setText("");
        
        try {
            st = con.createStatement();
            String sql = "SELECT * FROM jenis_cucian ORDER BY kd_jenis DESC";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                int hmmmm = Integer.parseInt(rs.getString("kd_jenis"))+1;
                id_jc.setText(Integer.toString(hmmmm));
            } else {
                id_jc.setText("0001");//sesuaikan dengan variable namenya
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
    }
    
    private void simpan() {
        String JenisJC = jenis_jc.getText();
        String HargaJC = harga_jc.getText();

        try {
            st = con.createStatement();
            String sqlSimpan = "insert into jenis_cucian values (NULL,'"+JenisJC+"','"+HargaJC+"') ";
            st.executeUpdate(sqlSimpan);
            JOptionPane.showMessageDialog(null, "Data Berhasil Masuk!!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Tak Masuk " + e.getMessage());
        }
    }
    
    private void hapus_data() {
            String[] options = {"Yes", "No"};
            JOptionPane.showOptionDialog(null, "Yakin Hapus Data Ini??", "Delete Confirm", 
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

            String id = id_jc.getText();

            try {

            st = con.createStatement();
            String sqlUpdate = "DELETE FROM jenis_cucian WHERE kd_jenis='"+id+"'";
            st.executeUpdate(sqlUpdate);
           
            id_jc.setText("Auto Generate");
            jenis_jc.setText("");
            harga_jc.setText("");
            

        JOptionPane.showMessageDialog(null, "Data Dihapus dari Database");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus " + e.getMessage());
            }
        
    }
    
    private void click_data() {
        String JenisID = tabel_jc.getValueAt(tabel_jc.getSelectedRow(), 0).toString();
        String JenisJC = tabel_jc.getValueAt(tabel_jc.getSelectedRow(), 1).toString();
        String HargaJC = tabel_jc.getValueAt(tabel_jc.getSelectedRow(), 2).toString();
        
        id_jc.setText(JenisID);
        jenis_jc.setText(JenisJC);
        harga_jc.setText(HargaJC);
    }
    
    private void ubah_data() {
        int index = tabel_jc.getSelectedRow();
        String id = tabel_jc.getValueAt(index, 0).toString();
        
        String IDJC = id_jc.getText();
        String JenisJC = jenis_jc.getText();
        String HargaJC = harga_jc.getText();
        
        try {

            st = con.createStatement();
            String sqlUpdate = "UPDATE jenis_cucian SET kd_jenis='"+IDJC+"',jenis_cucian='"+JenisJC+"',harga='"+HargaJC+"' WHERE kd_jenis='"+id+"' ";
            st.executeUpdate(sqlUpdate);

        JOptionPane.showMessageDialog(null, "Data Berhasil di Ubah");
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Data Gagal di Ubah " + e.getMessage());
            }
        }
    
    private void tampil_tabel() {
        try {
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM jenis_cucian");
                ResultSetMetaData rsmd = rs.getMetaData();
                    int columnCount = rsmd.getColumnCount();
                DefaultTableModel tm = (DefaultTableModel) tabel_jc.getModel();
                tm.setColumnCount(3);
                    for (int i = 4; i <= columnCount; i++ ) {
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
        tabel_jc = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        id_jc = new javax.swing.JTextField();
        jenis_jc = new javax.swing.JTextField();
        harga_jc = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        tambah_jc = new javax.swing.JButton();
        simpan_jc = new javax.swing.JButton();
        hapus_jc = new javax.swing.JButton();
        ubah_jc = new javax.swing.JButton();
        tutup_jc = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 204));
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
        jLabel1.setForeground(new java.awt.Color(51, 255, 51));
        jLabel1.setText("JENIS CUCIAN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
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

        tabel_jc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "KD Jenis", "Jenis Cucian", "Harga"
            }
        ));
        tabel_jc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_jcMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_jc);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(0, 153, 204));

        jLabel2.setText("ID");

        jLabel3.setText("Jenis Cucian");

        jLabel4.setText("Harga");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(59, 59, 59)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(id_jc)
                    .addComponent(jenis_jc)
                    .addComponent(harga_jc, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(id_jc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jenis_jc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(harga_jc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 255, 255));

        tambah_jc.setText("Tambah");
        tambah_jc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambah_jcActionPerformed(evt);
            }
        });

        simpan_jc.setText("Simpan");
        simpan_jc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpan_jcActionPerformed(evt);
            }
        });

        hapus_jc.setText("Hapus");
        hapus_jc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapus_jcActionPerformed(evt);
            }
        });

        ubah_jc.setText("Ubah");
        ubah_jc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubah_jcActionPerformed(evt);
            }
        });

        tutup_jc.setText("Tutup");
        tutup_jc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tutup_jcActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tambah_jc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(simpan_jc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hapus_jc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ubah_jc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tutup_jc))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tambah_jc)
                    .addComponent(simpan_jc)
                    .addComponent(hapus_jc)
                    .addComponent(ubah_jc)
                    .addComponent(tutup_jc))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabel_jcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_jcMouseClicked
         click_data();
    }//GEN-LAST:event_tabel_jcMouseClicked

    private void tambah_jcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambah_jcActionPerformed
        tambah_data();
    }//GEN-LAST:event_tambah_jcActionPerformed

    private void simpan_jcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpan_jcActionPerformed
        simpan();
        tampil_tabel();
    }//GEN-LAST:event_simpan_jcActionPerformed

    private void hapus_jcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapus_jcActionPerformed
        hapus_data();
        tampil_tabel();
    }//GEN-LAST:event_hapus_jcActionPerformed

    private void ubah_jcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubah_jcActionPerformed
        ubah_data();
        tampil_tabel();
    }//GEN-LAST:event_ubah_jcActionPerformed

    private void tutup_jcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tutup_jcActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_tutup_jcActionPerformed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
       int x = evt.getXOnScreen();
       int y = evt.getYOnScreen();
       this.setLocation(x-xx,y-xy);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil_tabel();
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(jenis_cucian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jenis_cucian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jenis_cucian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jenis_cucian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jenis_cucian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton hapus_jc;
    private javax.swing.JTextField harga_jc;
    private javax.swing.JTextField id_jc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jenis_jc;
    private javax.swing.JButton simpan_jc;
    private javax.swing.JTable tabel_jc;
    private javax.swing.JButton tambah_jc;
    private javax.swing.JButton tutup_jc;
    private javax.swing.JButton ubah_jc;
    // End of variables declaration//GEN-END:variables
}
