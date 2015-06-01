/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upc.fib.prop.usParlament.presentation;

import es.upc.fib.prop.usParlament.misc.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author miquel
 */
public class ModifyMPWindow extends javax.swing.JFrame {

    /**
     * Creates new form ModifyMPWindow
     */
    private PresentationController pc;
    private MainView pops;
    private State state;
    private int district;
    private JSONObject jmp;

    public ModifyMPWindow(PresentationController pece,MainView father,State estat,int dist) {
        pops = father;
        pc = pece;
        state = estat;
        district = dist;
        jmp = new JSONObject();
        jmp.addPair(new JSONString("State"),new JSONString(state.toString()));
        jmp.addPair(new JSONString("District"),new JSONString(Integer.toString(dist)));

        initComponents();
        updateTableAndCombo();
        titleLabel.setText("Modifying MP:"+estat.toString()+" "+Integer.toString(dist));
    }

    public void updateTableAndCombo()
    {

        ValueTextField.setText("");

        DefaultTableModel adtm = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        adtm.addColumn("AttrDef");
        adtm.addColumn("Value");
        JSONObject jo = pc.getMPInfo(state,district);
        //System.out.println(jo);
        JSONArray ja = (JSONArray)jo.getJSONByKey("Attributes");
        Set<String> attrdefsin = new LinkedHashSet<>();
        Set<String> attrdefs = new LinkedHashSet<>();
        for(JSON el:ja.getArray()){

            Map<String,String> ms = ((JSONObject)el).basicJSONObjectGetInfo();
            Vector<String> row = new Vector<String>();
            for(int pos = 0;pos<ja.getArray().size();pos++){
                row.add(ms.get("AttrDefName"));
                attrdefsin.add(ms.get("AttrDefName"));
                row.add(ms.get("AttrValue"));
            }

            adtm.addRow(row);

        }

        //Get all attr defs to make intersection
        JSONObject jotd = pc.getAttrDefs();
        JSONArray jatd = ((JSONArray)jotd.getJSONByKey("Attribute Definitions"));

        AttrDefComboBox.removeAllItems();

        for(JSON element:jatd.getArray()){
            Map<String,String> ms = ((JSONObject)element).basicJSONObjectGetInfo();
            attrdefs.add(ms.get("AttrDefName"));
        }
        attrdefs.removeAll(attrdefsin);
        for(String s:attrdefs) AttrDefComboBox.addItem(s);



        attributesTable.setModel(adtm);
        attributesTable.getTableHeader().setReorderingAllowed(false);


        pops.updateMPManagementMPTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        attTablePanel = new javax.swing.JScrollPane();
        attributesTable = new javax.swing.JTable();
        titleLabel = new javax.swing.JLabel();
        addAttributeButton = new javax.swing.JButton();
        deleteAttributeButton = new javax.swing.JButton();
        modifyAttributeButton = new javax.swing.JButton();
        AttrDefComboBox = new javax.swing.JComboBox();
        ValueTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        attributesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "AttrDef", "Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        attTablePanel.setViewportView(attributesTable);

        titleLabel.setText("Modifying MP:");

        addAttributeButton.setText("Add Attribute");
        addAttributeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAttributeButtonActionPerformed(evt);
            }
        });

        deleteAttributeButton.setText("Delete Attribute");
        deleteAttributeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAttributeButtonActionPerformed(evt);
            }
        });

        modifyAttributeButton.setText("Modify Attribute");
        modifyAttributeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyAttributeButtonActionPerformed(evt);
            }
        });

        AttrDefComboBox.setModel(new javax.swing.DefaultComboBoxModel());

        ValueTextField.setText("jTextField1");

        jLabel1.setText("AttrDef.");

        jLabel2.setText("Value");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(titleLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(attTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addAttributeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteAttributeButton, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                            .addComponent(modifyAttributeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(AttrDefComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(ValueTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AttrDefComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ValueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addComponent(addAttributeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modifyAttributeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteAttributeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(attTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addAttributeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAttributeButtonActionPerformed
        // TODO add your handling code here:
        String value = ValueTextField.getText();
        String attrdef = (String)AttrDefComboBox.getSelectedItem();

        if(attrdef == null){
            JOptionPane.showMessageDialog(new JFrame(), "No attributes left to add");
            return;
        }

        if(value.equals("")){
            JOptionPane.showMessageDialog(new JFrame(), "Please add a valid input to value");
            return;
        }


        JSONArray ja = new JSONArray();
        JSONObject jo = new JSONObject();
        jo.addPair(new JSONString("AttrDefName"),new JSONString(attrdef));
        jo.addPair(new JSONString("AttrValue"), new JSONString(value));
        ja.addElement(jo);
        pc.addAttributes(jmp, ja);
        //System.out.println("ATTRS:"+ja);
        updateTableAndCombo();



    }//GEN-LAST:event_addAttributeButtonActionPerformed

    private void modifyAttributeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyAttributeButtonActionPerformed
        // TODO add your handling code here:
        int fila = attributesTable.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(new JFrame(), "No row selected");
            return;
        }
        //columna 0 estat
        //columna 1 districte
        String attrdef = (String)attributesTable.getValueAt(fila,0);
        String value = JOptionPane.showInputDialog("Introduce the new value");

        JSONObject jo = new JSONObject();
        jo.addPair(new JSONString("AttrDefName"),new JSONString(attrdef));
        jo.addPair(new JSONString("AttrValue"),new JSONString(value));
        JSONArray ja = new JSONArray();
        ja.addElement(jo);
        pc.addAttributes(jmp, ja);
        updateTableAndCombo();
        pops.updateMPManagementMPTable();
    }//GEN-LAST:event_modifyAttributeButtonActionPerformed

    private void deleteAttributeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAttributeButtonActionPerformed
        // TODO add your handling code here:

        int fila = attributesTable.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(new JFrame(), "No row selected");
            return;
        }
        //columna 0 estat
        //columna 1 districte
        String attrdef = (String)attributesTable.getValueAt(fila,0);
        pc.removeAttribute(jmp, attrdef);

        updateTableAndCombo();
    }//GEN-LAST:event_deleteAttributeButtonActionPerformed

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
            java.util.logging.Logger.getLogger(ModifyMPWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModifyMPWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModifyMPWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModifyMPWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModifyMPWindow(null,null,null,0).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox AttrDefComboBox;
    private javax.swing.JTextField ValueTextField;
    private javax.swing.JButton addAttributeButton;
    private javax.swing.JScrollPane attTablePanel;
    private javax.swing.JTable attributesTable;
    private javax.swing.JButton deleteAttributeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton modifyAttributeButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
