/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uavsim;

import Controller.Communication.UAVComProvider;
import Controller.Map.GPS.Latitude;
import Controller.Map.GPS.Longitude;
import Controller.Map.GPS.Trackpoint;
import Controller.Map.KMLProvider;
import Model.Component.PhysicsProvider.MQ1PhysicsProvider;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;

/**
 *
 * @author Administrator
 */
public class WaypointTestFrame extends javax.swing.JFrame {

    KMLProvider tst;
    MQ1_WaypointTest uav1;
    MQ1_WaypointTest uav2;
    MQ1_WaypointTest uav3;
    MQ1_WaypointTest uav4;
    MQ1_WaypointTest uav5;
    MQ1_WaypointTest uav6;

    Timer tmp;

    /**
     * Creates new form WaypointTestFrame
     */
    static ArrayList<Trackpoint> UAV_1() {
        ArrayList<Trackpoint> targetWaypoint = new ArrayList<>();

        targetWaypoint.add(new Trackpoint(50, new Latitude(35.683052), new Longitude(51.337183), new Date(), "UAV #1"));
        //35.669177, 51.393274
        targetWaypoint.add(new Trackpoint(100, new Latitude(35.669177), new Longitude(51.393274), new Date(), "UAV #1"));

        //35.629701, 51.469320
        targetWaypoint.add(new Trackpoint(150, new Latitude(35.629701), new Longitude(51.469320), new Date(), "UAV #1"));

        //35.569333, 51.450952
        targetWaypoint.add(new Trackpoint(120, new Latitude(35.569333), new Longitude(51.450952), new Date(), "UAV #1"));

        //35.535944, 51.301863
        targetWaypoint.add(new Trackpoint(50, new Latitude(35.535944), new Longitude(51.301863), new Date(), "UAV #1"));

        //35.690013, 51.301176
        targetWaypoint.add(new Trackpoint(10, new Latitude(35.690013), new Longitude(51.301176), new Date(), "UAV #1"));

        return targetWaypoint;
    }

    static ArrayList<Trackpoint> UAV_2() {
        ArrayList<Trackpoint> targetWaypoint = new ArrayList<>();
        targetWaypoint.add(new Trackpoint(50, new Latitude(35.694160), new Longitude(51.294510), new Date(), "UAV #2 - 1"));
        targetWaypoint.add(new Trackpoint(50, new Latitude(35.708414), new Longitude(51.334635), new Date(), "UAV #2 - 2"));
        targetWaypoint.add(new Trackpoint(50, new Latitude(35.708065), new Longitude(51.351244), new Date(), "UAV #2 - 3"));
        targetWaypoint.add(new Trackpoint(50, new Latitude(35.721132), new Longitude(51.345815), new Date(), "UAV #2 - 4"));
        targetWaypoint.add(new Trackpoint(50, new Latitude(35.692383), new Longitude(51.337304), new Date(), "UAV #2 - 5"));
        return targetWaypoint;
    }

    static ArrayList<Trackpoint> UAV_3() {
        ArrayList<Trackpoint> targetWaypoint = new ArrayList<>();
        targetWaypoint.add(new Trackpoint(50, new Latitude(35.694160), new Longitude(51.294510), new Date(), "UAV #3 - 1"));
        targetWaypoint.add(new Trackpoint(50, new Latitude(35.667521), new Longitude(51.360706), new Date(), "UAV #3 - 2"));
        targetWaypoint.add(new Trackpoint(50, new Latitude(35.699276), new Longitude(51.350192), new Date(), "UAV #3 - 3"));
        targetWaypoint.add(new Trackpoint(50, new Latitude(35.689483), new Longitude(51.390060), new Date(), "UAV #3 - 4"));
        targetWaypoint.add(new Trackpoint(50, new Latitude(35.667491), new Longitude(51.360127), new Date(), "UAV #3 - 5"));
        return targetWaypoint;
    }

    static ArrayList<Trackpoint> UAV_4() {
        ArrayList<Trackpoint> targetWaypoint = new ArrayList<>();
        targetWaypoint.add(new Trackpoint(50, new Latitude(35.408809), new Longitude(51.155108), new Date(), "UAV #4 - 1"));
        targetWaypoint.add(new Trackpoint(50, new Latitude(35.372791), new Longitude(51.381379), new Date(), "UAV #4 - 2"));
        targetWaypoint.add(new Trackpoint(50, new Latitude(35.485251), new Longitude(51.289712), new Date(), "UAV #4 - 3"));
        targetWaypoint.add(new Trackpoint(50, new Latitude(35.285401), new Longitude(51.284905), new Date(), "UAV #4 - 4"));
        targetWaypoint.add(new Trackpoint(50, new Latitude(35.437433), new Longitude(51.617585), new Date(), "UAV #4 - 5"));
        return targetWaypoint;
    }

    static ArrayList<Trackpoint> UAV_5() {
        ArrayList<Trackpoint> targetWaypoint = new ArrayList<>();
        targetWaypoint.add(new Trackpoint(50, new Latitude(35.413933), new Longitude(51.142426), new Date(), "UAV #5 - 1"));
        targetWaypoint.add(new Trackpoint(50, new Latitude(35.534438), new Longitude(50.982781), new Date(), "UAV #5 - 2"));
        targetWaypoint.add(new Trackpoint(50, new Latitude(35.713045), new Longitude(51.300355), new Date(), "UAV #5 - 3"));
        targetWaypoint.add(new Trackpoint(50, new Latitude(35.600346), new Longitude(51.493302), new Date(), "UAV #5 - 4"));
        targetWaypoint.add(new Trackpoint(50, new Latitude(35.457291), new Longitude(51.592522), new Date(), "UAV #5 - 5"));
        return targetWaypoint;
    }

    static ArrayList<Trackpoint> UAV_6() {
        ArrayList<Trackpoint> targetWaypoint = new ArrayList<>();
        targetWaypoint.add(new Trackpoint(50, new Latitude(35.645540), new Longitude(51.383414), new Date(), "UAV #6 - 1"));
        targetWaypoint.add(new Trackpoint(50, new Latitude(35.643181), new Longitude(51.437674), new Date(), "UAV #6 - 2"));
        targetWaypoint.add(new Trackpoint(50, new Latitude(35.674354), new Longitude(51.398192), new Date(), "UAV #6 - 3"));
        targetWaypoint.add(new Trackpoint(50, new Latitude(35.705096), new Longitude(51.301632), new Date(), "UAV #6 - 4"));
        targetWaypoint.add(new Trackpoint(50, new Latitude(35.653023), new Longitude(51.270738), new Date(), "UAV #6 - 5"));
        return targetWaypoint;
    }

    public WaypointTestFrame() {
        initComponents();
        uav1 = new MQ1_WaypointTest(UAV_1(), UAV_1().get(0), 1, "B88AE6");
        uav2 = new MQ1_WaypointTest(UAV_2(), UAV_2().get(0), 2, "da536e");
        uav3 = new MQ1_WaypointTest(UAV_3(), UAV_3().get(0), 3, "6e4a55");
        uav4 = new MQ1_WaypointTest(UAV_4(), UAV_4().get(0), 4, "00a3a7");
        uav5 = new MQ1_WaypointTest(UAV_5(), UAV_5().get(0), 5, "56256e");
        uav6 = new MQ1_WaypointTest(UAV_6(), UAV_6().get(0), 6, "edde7b");

        UAVComProvider.lst.add(uav1);
        UAVComProvider.lst.add(uav2);
        UAVComProvider.lst.add(uav3);
        UAVComProvider.lst.add(uav4);
        UAVComProvider.lst.add(uav5);
        UAVComProvider.lst.add(uav6);
//        

        ArrayList<Trackpoint> waypointlst = new ArrayList<>();

        waypointlst.addAll(UAV_1());
        waypointlst.addAll(UAV_2());
        waypointlst.addAll(UAV_3());
        waypointlst.addAll(UAV_4());
        waypointlst.addAll(UAV_5());
        waypointlst.addAll(UAV_6());

        tst = new KMLProvider(UAVComProvider.lst, waypointlst);

        tmp = new Timer();
        tmp.schedule(new TimerTask() {

            @Override
            public void run() {
                UAVComProvider.refreshUAVsNearList();
                speedvalueLBL.setText(String.valueOf(((MQ1PhysicsProvider) (uav1.getPhysicEng())).getSpeed()));
                if (uav1.getBottomCamera().getImage() != null) {
                    BottomCameraImageLBL.setIcon(new ImageIcon(uav1.getBottomCamera().getImage()));
                }
                TempvalueLBL.setText(String.valueOf(uav1.getTempSensor().getTemperature()));
            }
        }, 0, 1 * 100);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BottomCameraImageLBL = new javax.swing.JLabel();
        LatTF = new javax.swing.JTextField();
        LonTF = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        GOBTN = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        speedvalueLBL = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TempvalueLBL = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        AltitudeCB = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LatTF.setText("35.72087288");

        LonTF.setText("51.4352417");

        jLabel1.setText("Waypoint Latitude");

        jLabel2.setText("Waypoint Longitude");

        GOBTN.setText("GO");
        GOBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GOBTNActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Speed");

        speedvalueLBL.setText("speedvalueLBL");

        jLabel5.setText("Temperatur");

        TempvalueLBL.setText("TempvalueLBL");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(50, 50, 50)
                        .addComponent(speedvalueLBL))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TempvalueLBL)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(speedvalueLBL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TempvalueLBL))
                .addContainerGap(182, Short.MAX_VALUE))
        );

        jLabel4.setText("Travel Altitude");

        AltitudeCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Low Profile (below 100 meters)", "Normal Patrol(in 100 to 1000 meters range)", "High Altitude(over 1000 meters)" }));
        AltitudeCB.setSelectedIndex(1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(BottomCameraImageLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(GOBTN)
                        .addGap(83, 83, 83))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LatTF)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LonTF)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AltitudeCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(BottomCameraImageLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LatTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LonTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AltitudeCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(GOBTN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GOBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GOBTNActionPerformed
        int alt = 0;

        switch (AltitudeCB.getSelectedIndex()) {
            case 0:
                alt = new Random().nextInt(50) + 50;
                break;
            case 1:
                alt = (new Random().nextInt(900)) + 100;
                break;
            case 2:
                alt = new Random().nextInt(4000) + 1000;
                break;
        }

        try {
            double lat, lon;
            lat = Double.valueOf(LatTF.getText());
            lon = Double.valueOf(LonTF.getText());
            System.out.println(alt);
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_GOBTNActionPerformed

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
            java.util.logging.Logger.getLogger(WaypointTestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WaypointTestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WaypointTestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WaypointTestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WaypointTestFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox AltitudeCB;
    private javax.swing.JLabel BottomCameraImageLBL;
    private javax.swing.JButton GOBTN;
    private javax.swing.JTextField LatTF;
    private javax.swing.JTextField LonTF;
    private javax.swing.JLabel TempvalueLBL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel speedvalueLBL;
    // End of variables declaration//GEN-END:variables
}
