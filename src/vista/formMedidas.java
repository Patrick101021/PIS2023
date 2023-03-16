/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import DAO.MedidaDAO;
import Modelo.Medida;
import Modelo.Usuario;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kevin Barzola
 */
public class formMedidas extends javax.swing.JPanel {
    
    Usuario sysUser;
    
    /**
     * Creates new form Medidas
     */
    public formMedidas() {
        initComponents();
        
    }
    
    public formMedidas(Usuario user) {
        initComponents();
        this.sysUser = user;
        getMedidas();
        
    }
    
    public static void getMedidas(){
        MedidaDAO cliDAO = new MedidaDAO();
        List<Medida> medidas = cliDAO.listar();

        DefaultTableModel model = (DefaultTableModel) tabListaMedidas.getModel();
        tabListaMedidas.removeAll();
        model.setRowCount(0);
        
        for(Medida med :medidas){
            
            model.addRow(new String[]{String.valueOf(med.getIdMedida()), 
                med.getDescripcion(), med.getAbreviatura()
            });
            
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

        labTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabListaMedidas = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();

        setBackground(new java.awt.Color(224, 224, 224));
        setPreferredSize(new java.awt.Dimension(900, 500));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labTitle.setForeground(new java.awt.Color(11, 58, 82));
        labTitle.setText("MEDIDAS");
        add(labTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        tabListaMedidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Descripción", "Abreviatura"
            }
        ));
        tabListaMedidas.setMinimumSize(new java.awt.Dimension(0, 0));
        tabListaMedidas.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabListaMedidas);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 860, 280));

        btnEliminar.setBackground(new java.awt.Color(255, 102, 102));
        btnEliminar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 100, 30));

        btnNuevo.setBackground(new java.awt.Color(30, 150, 210));
        btnNuevo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 100, 30));

        btnModificar.setBackground(new java.awt.Color(255, 153, 51));
        btnModificar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 100, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        
        int fila = tabListaMedidas.getSelectedRow();
        Medida cate = new Medida();
        MedidaDAO cliDAO = new MedidaDAO();

        if(fila >= 0){
            
            String id = (String) tabListaMedidas.getValueAt(fila, 0);
            int res = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el registro?");
            
            if(res == 0){
                cate.setIdMedida(Integer.valueOf(id));
                cate.setEstado(0);
                cate.setFechaElimina(LocalDateTime.now());
                cate.setUsuarioElimina(sysUser.getUsername());
                cliDAO.eliminar(cate);
                getMedidas();
            }            

        }else{
            JOptionPane.showMessageDialog(null, "Por favor, debe seleccionar un registro.");
            
        }
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        
        formMedidaAdd regCate = new formMedidaAdd(sysUser);
        regCate.setVisible(true);
        
        
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        
        int fila = tabListaMedidas.getSelectedRow();
        if(fila >= 0){
            String id = (String) tabListaMedidas.getValueAt(fila, 0);
            formMedidaEdit editClient = new formMedidaEdit(id, sysUser);
            editClient.setVisible(true);
            
        }else{

            JOptionPane.showMessageDialog(null, "Por favor, debe seleccionar un registro.");
        }
        
    }//GEN-LAST:event_btnModificarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labTitle;
    public static javax.swing.JTable tabListaMedidas;
    // End of variables declaration//GEN-END:variables
}
