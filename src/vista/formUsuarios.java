/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import DAO.UsuarioDAO;
import Modelo.Usuario;
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
public class formUsuarios extends javax.swing.JPanel {
    
    Usuario sysUser;
    
    /**
     * Creates new form Usuarios
     */
    public formUsuarios() {
        initComponents();
        
    }
    
    public formUsuarios(Usuario user) {
        initComponents();
        this.sysUser = user;
        getUsuarios();
        
    }
    
    public static void getUsuarios(){
        UsuarioDAO cliDAO = new UsuarioDAO();
        List<Usuario> clientes = cliDAO.listar();

        DefaultTableModel model = (DefaultTableModel) tabListaUsuarios.getModel();
        tabListaUsuarios.removeAll();
        model.setRowCount(0);
        
        for(Usuario cli :clientes){
            
            model.addRow(new String[]{String.valueOf(cli.getIdUsuario()), cli.getUsername(), cli.getCedula(), cli.getNombreCompleto(), cli.getDepartamento(), 
                cli.getCargo(), cli.getFechaNacimiento().toString(), cli.getCodigoEmpleado()
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

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabListaUsuarios = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();

        setBackground(new java.awt.Color(224, 224, 224));
        setPreferredSize(new java.awt.Dimension(900, 500));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(11, 58, 82));
        jLabel2.setText("USUARIOS");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        tabListaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Username", "Cédula", "Nombres y Apellidos", "Departamento", "Cargo", "Fecha de nacimiento", "Cod. Empleado"
            }
        ));
        tabListaUsuarios.setMinimumSize(new java.awt.Dimension(0, 0));
        tabListaUsuarios.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabListaUsuarios);

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
        
        int fila = tabListaUsuarios.getSelectedRow();
        Usuario client = new Usuario();
        UsuarioDAO cliDAO = new UsuarioDAO();

        if(fila >= 0){
            
            String id = (String) tabListaUsuarios.getValueAt(fila, 0);
            int res = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el registro?");
            
            if(res == 0){
                client.setIdUsuario(Integer.valueOf(id));
                client.setEstado(0);
                client.setFechaElimina(LocalDateTime.now());
                client.setUsuarioElimina(sysUser.getUsername());
                cliDAO.eliminar(client);
                getUsuarios();
            }            

        }else{
            JOptionPane.showMessageDialog(null, "Por favor, debe seleccionar un registro.");
            
        }
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        
        formUsuarioAdd regClient = new formUsuarioAdd(sysUser);
        regClient.setVisible(true);
        
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        
        int fila = tabListaUsuarios.getSelectedRow();
        if(fila >= 0){
            String id = (String) tabListaUsuarios.getValueAt(fila, 0);
            formUsuarioEdit editClient = new formUsuarioEdit(id, sysUser);
            editClient.setVisible(true);
            
        }else{

            JOptionPane.showMessageDialog(null, "Por favor, debe seleccionar un registro.");
        }
        
    }//GEN-LAST:event_btnModificarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tabListaUsuarios;
    // End of variables declaration//GEN-END:variables
}
