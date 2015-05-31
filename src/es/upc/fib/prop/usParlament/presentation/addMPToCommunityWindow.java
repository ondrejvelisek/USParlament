/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upc.fib.prop.usParlament.presentation;

import es.upc.fib.prop.usParlament.misc.*;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author miquel
 */

//TODO: Testing (not possible until the tables show the info).
public class addMPToCommunityWindow extends javax.swing.JFrame {

    private PresentationController pc;
    private Integer cNumb;
    private MainView pops;
    private int fila;
    /**
     * Creates new form addMPToCommunityWindow
     * @param pc
     */
    public addMPToCommunityWindow(PresentationController pc, Integer cNumb, MainView mv, int fila) {
        initComponents();
        this.pc = pc;
        this.cNumb = cNumb;
        pops = mv;
        this.fila = fila;
        inicialitza();

    }

    public void inicialitza()
    {
        System.out.println("INICIALITZAT");

        JSONObject j = pc.getShortMPList();
        JSONArray ja = (JSONArray)j.getJSONByKey("MPList");

        Set<State> estats = new LinkedHashSet<>();
        for(JSON json:ja.getArray()){
            Map<String,String> mss = ((JSONObject)json).basicJSONObjectGetInfo();
            estats.add(State.valueOf(mss.get("State")));
        }





        for(State s:estats) stateComboBox.addItem(s);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox2 = new javax.swing.JComboBox();
        AddMPLabel = new javax.swing.JLabel();
        stateComboBox = new javax.swing.JComboBox();
        districtComboBox = new javax.swing.JComboBox();
        addMPButton = new javax.swing.JButton();
        StateLabel = new javax.swing.JLabel();
        DistrictLabel = new javax.swing.JLabel();

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        AddMPLabel.setText("Add MP to selected Community");

        stateComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stateComboBoxActionPerformed(evt);
            }
        });

        districtComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                districtComboBoxActionPerformed(evt);
            }
        });

        addMPButton.setText("Add MP");
        addMPButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMPButtonActionPerformed(evt);
            }
        });

        StateLabel.setText("State");

        DistrictLabel.setText("District");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(addMPButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(StateLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(stateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(AddMPLabel)
                                .addGap(0, 13, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(DistrictLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(districtComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AddMPLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(districtComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DistrictLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addMPButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void stateComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stateComboBoxActionPerformed
        // TODO add your handling code here:
        updateBoxes();
    }//GEN-LAST:event_stateComboBoxActionPerformed


    private void updateBoxes()
    {
        districtComboBox.removeAllItems();

        if(stateComboBox.getSelectedItem()== null){
            System.out.println("NO STATE");
            return;
        }
        State st = (State) stateComboBox.getSelectedItem();
        Set<String> districts = new LinkedHashSet();
        System.out.println("Districts:"+districts);
        Set<JSONObject> cpmp = pc.getMPsCurrentPartition(cNumb);
        System.out.println("cpmp:"+cpmp);
        for(JSONObject mpc:cpmp){

            if(st.equals(State.valueOf(mpc.basicJSONObjectGetInfo().get("State")))){
                districts.add(mpc.basicJSONObjectGetInfo().get("District"));
            }
        }


        JSONObject jList = pc.getShortMPList();
        JSONArray ja = (JSONArray) jList.getJSONByKey("MPList");
        for (JSON jn : ja.getArray()) {
            JSONObject jo = (JSONObject) jn;
            Map<String,String> ms = jo.basicJSONObjectGetInfo();
            if (State.valueOf(ms.get("State")).equals(st)) {
                if(!districts.contains(ms.get("District"))) districtComboBox.addItem(ms.get("District"));
            }
        }
    }



    private void districtComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_districtComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_districtComboBoxActionPerformed

    private void addMPButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMPButtonActionPerformed
        State st = (State)stateComboBox.getSelectedItem();
        Integer dt = Integer.parseInt((String)districtComboBox.getSelectedItem());
        pc.addMPToCommunity(cNumb, st, dt);
        pops.updateMPsInCommunityTable();
        updateBoxes();
    }//GEN-LAST:event_addMPButtonActionPerformed

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
            java.util.logging.Logger.getLogger(addMPToCommunityWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addMPToCommunityWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addMPToCommunityWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addMPToCommunityWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               //new addMPToCommunityWindow(pc).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AddMPLabel;
    private javax.swing.JLabel DistrictLabel;
    private javax.swing.JLabel StateLabel;
    private javax.swing.JButton addMPButton;
    private javax.swing.JComboBox districtComboBox;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox stateComboBox;
    // End of variables declaration//GEN-END:variables
}