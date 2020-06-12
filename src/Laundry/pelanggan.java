/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Laundry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Praise13
 */
public class pelanggan extends javax.swing.JFrame {
    private DefaultTableModel model;
    public static int statusSearching = 0;
    
    Connection con;
    Statement st;
    private int xx;
    private int xy;
    /**
     * Creates new form pelanggan
     */
    public pelanggan() {
        initComponents();
        db_koneksi data = new db_koneksi();
        data.config();
        con = data.con;
        st = data.st;
        model = (DefaultTableModel)tabel_pel.getModel();
    }

    private void tampil_tabel() {
        try {
                st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM pelanggan");
                ResultSetMetaData rsmd = rs.getMetaData();
                    int columnCount = rsmd.getColumnCount();
                DefaultTableModel tm = (DefaultTableModel) tabel_pel.getModel();
                tm.setColumnCount(4);
                    for (int i = 5; i <= columnCount; i++ ) {
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

    private void simpan() {
        String NamaPel = nama_pel.getText();
        String AlamatPel = alamat_pel.getText();
        String TelpPel = telp_pel.getText();

        try {
            st = con.createStatement();
            String sqlSimpan = "insert into pelanggan values (NULL,'"+NamaPel+"','"+AlamatPel+"','"+TelpPel+"') ";
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

            String id = id_pel.getText();

            try {

            st = con.createStatement();
            String sqlUpdate = "DELETE FROM pelanggan WHERE id_pelanggan='"+id+"'";
            st.executeUpdate(sqlUpdate);
           
            id_pel.setText("Auto Generate");
            nama_pel.setText("");
            alamat_pel.setText("");
            telp_pel.setText("");
            

        JOptionPane.showMessageDialog(null, "Data Dihapus dari Database");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus " + e.getMessage());
            }
        
        
    }
    
    private void click_data() {
        String PelID = tabel_pel.getValueAt(tabel_pel.getSelectedRow(), 0).toString();
        String PelNama = tabel_pel.getValueAt(tabel_pel.getSelectedRow(), 1).toString();
        String PelAlamat = tabel_pel.getValueAt(tabel_pel.getSelectedRow(), 2).toString();
        String PelTelp = tabel_pel.getValueAt(tabel_pel.getSelectedRow(), 3).toString();
        
        id_pel.setText(PelID);
        nama_pel.setText(PelNama);
        alamat_pel.setText(PelAlamat);
        telp_pel.setText(PelTelp);
    }
    
    private void ubah_data() {
        int index = tabel_pel.getSelectedRow();
        String id = tabel_pel.getValueAt(index, 0).toString();
        
        String IDPel = id_pel.getText();
        String NamaPel = nama_pel.getText();
        String AlamatPel = alamat_pel.getText();
        String TelpPel = telp_pel.getText();
        
        try {

            st = con.createStatement();
            String sqlUpdate = "UPDATE pelanggan SET id_pelanggan='"+IDPel+"',nama_pelanggan='"+NamaPel+"',alamat='"+AlamatPel+"',telp='"+TelpPel+"' WHERE id_pelanggan='"+id+"' ";
            st.executeUpdate(sqlUpdate);

        JOptionPane.showMessageDialog(null, "Data Berhasil di Ubah");
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Data Gagal di Ubah " + e.getMessage());
            }
        }
    
    private void tambah_data() {
        try {
            st = con.createStatement();
            String sql = "SELECT * FROM pelanggan ORDER BY id_pelanggan DESC";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                int hmmmm = Integer.parseInt(rs.getString("id_pelanggan"))+1;
                id_pel.setText(Integer.toString(hmmmm));
            } else {
                id_pel.setText("0001");//sesuaikan dengan variable namenya
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();//penanganan masalah
        }
        
        nama_pel.setText("");
        alamat_pel.setText("");
        telp_pel.setText("");
    }
    
    private void cari_data_pel() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        statusSearching = 1;
        if(cari_btn_pel.getText().isEmpty())
        { statusSearching = 0; }
        else if(statusSearching==1){
           
        
        String nyari = cari_pel.getText();
        
        try{
            String sql = "Select * From pelanggan Where id_pelanggan LIKE '"+nyari+"'"
                            + "OR nama_pelanggan LIKE '"+nyari+"' OR alamat LIKE '"+nyari+"'"
                            + "OR telp LIKE '"+nyari+"' ORDER BY id_pelanggan";
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery(sql);
        while(rs.next()){
            model.addRow(new Object[]{
               rs.getString(1),
               rs.getString(2),
               rs.getString(3),
               rs.getString(4)
            
            });
        }
        
        tabel_pel.setModel(model);
                
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(rootPane,"Data yang dicari tidak di temukan!");
                }
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
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        id_pel = new javax.swing.JTextField();
        nama_pel = new javax.swing.JTextField();
        alamat_pel = new javax.swing.JTextField();
        telp_pel = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_pel = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        cari_pel = new javax.swing.JTextField();
        cari_btn_pel = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        tambah_pel = new javax.swing.JButton();
        simpan_pel = new javax.swing.JButton();
        hapus_pel = new javax.swing.JButton();
        ubah_pel = new javax.swing.JButton();
        tutup_pel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
        jLabel1.setForeground(new java.awt.Color(255, 255, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PELANGGAN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 131, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(0, 153, 204));

        jLabel2.setText("ID");

        jLabel3.setText("Nama");

        jLabel4.setText("Alamat");

        jLabel5.setText("No Telp");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(66, 66, 66)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(id_pel)
                    .addComponent(nama_pel)
                    .addComponent(alamat_pel)
                    .addComponent(telp_pel, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(id_pel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nama_pel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(alamat_pel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(telp_pel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        tabel_pel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Pelanggan", "Nama", "Alamat", "NoTelp"
            }
        ));
        tabel_pel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_pelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_pel);

        jPanel5.setBackground(new java.awt.Color(0, 102, 204));

        cari_btn_pel.setText("Cari");
        cari_btn_pel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cari_btn_pelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cari_pel, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cari_btn_pel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cari_pel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cari_btn_pel))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(0, 204, 204));

        tambah_pel.setText("Tambah");
        tambah_pel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambah_pelActionPerformed(evt);
            }
        });

        simpan_pel.setText("Simpan");
        simpan_pel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpan_pelActionPerformed(evt);
            }
        });

        hapus_pel.setText("Hapus");
        hapus_pel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapus_pelActionPerformed(evt);
            }
        });

        ubah_pel.setText("Ubah");
        ubah_pel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubah_pelActionPerformed(evt);
            }
        });

        tutup_pel.setText("Tutup");
        tutup_pel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tutup_pelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tambah_pel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(simpan_pel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(hapus_pel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ubah_pel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tutup_pel)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tambah_pel)
                    .addComponent(simpan_pel)
                    .addComponent(hapus_pel)
                    .addComponent(ubah_pel)
                    .addComponent(tutup_pel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 554, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void tambah_pelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambah_pelActionPerformed
       tambah_data();
    }//GEN-LAST:event_tambah_pelActionPerformed

    private void simpan_pelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpan_pelActionPerformed
        simpan();
        tampil_tabel();
    }//GEN-LAST:event_simpan_pelActionPerformed

    private void hapus_pelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapus_pelActionPerformed
        hapus_data();
        tampil_tabel();
    }//GEN-LAST:event_hapus_pelActionPerformed

    private void ubah_pelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubah_pelActionPerformed
        ubah_data();
        tampil_tabel();
    }//GEN-LAST:event_ubah_pelActionPerformed

    private void tutup_pelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tutup_pelActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_tutup_pelActionPerformed

    private void cari_btn_pelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cari_btn_pelActionPerformed
        cari_data_pel();
    }//GEN-LAST:event_cari_btn_pelActionPerformed

    private void tabel_pelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_pelMouseClicked
       click_data();
    }//GEN-LAST:event_tabel_pelMouseClicked

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x-xx,y-xy);
    }//GEN-LAST:event_jPanel1MouseDragged

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
            java.util.logging.Logger.getLogger(pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pelanggan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField alamat_pel;
    private javax.swing.JButton cari_btn_pel;
    private javax.swing.JTextField cari_pel;
    private javax.swing.JButton hapus_pel;
    private javax.swing.JTextField id_pel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nama_pel;
    private javax.swing.JButton simpan_pel;
    private javax.swing.JTable tabel_pel;
    private javax.swing.JButton tambah_pel;
    private javax.swing.JTextField telp_pel;
    private javax.swing.JButton tutup_pel;
    private javax.swing.JButton ubah_pel;
    // End of variables declaration//GEN-END:variables
}
