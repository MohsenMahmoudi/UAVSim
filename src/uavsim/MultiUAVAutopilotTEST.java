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
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class MultiUAVAutopilotTEST extends javax.swing.JFrame {

    private final KMLProvider tst;
    private ArrayList<Trackpoint> waypointlst;

    private ArrayList<MQ1_WaypointTest> uavs;

    private Timer tmp;

    private boolean followUAV;
    private int selectedUAV;

    private int lastUAVID = 0;

    /**
     * Creates new form MultiUAVAutopilotTEST
     */
    public MultiUAVAutopilotTEST() {
        initComponents();
        this.setResizable(false);

        followUAV = false;

        waypointlst = new ArrayList<>();
        uavs = new ArrayList();

        tst = new KMLProvider(UAVComProvider.lst, waypointlst);

        init();
        
        tmp = new Timer();
        tmp.schedule(new TimerTask() {

            @Override
            public void run() {
                UAVComProvider.refreshUAVsNearList();
                if (selectedUAV >= 0 && selectedUAV < uavs.size()) {
                    SpeedValueLBL.setText(String.valueOf(((MQ1PhysicsProvider) (uavs.get(selectedUAV).getPhysicEng())).getSpeed()));
                    if (uavs.get(selectedUAV).getBottomCamera().getImage() != null) {
                        BottomCameraLBL.setIcon(new ImageIcon(uavs.get(selectedUAV).getBottomCamera().getImage()));
                    }
                    TemperatureValueLBL.setText(String.valueOf(uavs.get(selectedUAV).getTempSensor().getTemperature()));
                }
            }
        }, 0, 1 * 100);
    }

    private void init() {
        MQ1_WaypointTest uav1;
        MQ1_WaypointTest uav2;
//        MQ1_WaypointTest uav3;
//        MQ1_WaypointTest uav4;
//        MQ1_WaypointTest uav5;
//        MQ1_WaypointTest uav6;
//        
        
        uav1 = new MQ1_WaypointTest(UAV_1(), UAV_1().get(0),1, "B88AE6");
        uav2 = new MQ1_WaypointTest(UAV_2(), UAV_2().get(0),2, "da536e");
//        uav3 = new MQ1_WaypointTest(UAV_3(), UAV_3().get(0),3, "6e4a55");
//        uav4 = new MQ1_WaypointTest(UAV_4(), UAV_4().get(0),4, "00a3a7");
//        uav5 = new MQ1_WaypointTest(UAV_5(), UAV_5().get(0),5, "56256e");
//        uav6 = new MQ1_WaypointTest(UAV_6(), UAV_6().get(0),6, "edde7b");
        
        uav1.getBottomCamera().setEnable(false);
        uav2.getBottomCamera().setEnable(false);
//        uav3.getBottomCamera().setEnable(false);
//        uav4.getBottomCamera().setEnable(false);
//        uav5.getBottomCamera().setEnable(false);
//        uav6.getBottomCamera().setEnable(false);
        
        uavs.add(uav1);
        uavs.add(uav2);
//        uavs.add(uav3);
//        uavs.add(uav4);
//        uavs.add(uav5);
//        uavs.add(uav6);
        
        UAVComProvider.lst.add(uav1);
        UAVComProvider.lst.add(uav2);
//        UAVComProvider.lst.add(uav3);
//        UAVComProvider.lst.add(uav4);
//        UAVComProvider.lst.add(uav5);
//        UAVComProvider.lst.add(uav6);
        
        waypointlst.addAll(UAV_1());
        waypointlst.addAll(UAV_2());
//        waypointlst.addAll(UAV_3());
//        waypointlst.addAll(UAV_4());
//        waypointlst.addAll(UAV_5());
//        waypointlst.addAll(UAV_6());
//        
        jList2.setModel(new javax.swing.AbstractListModel() {
                @Override
                public int getSize() {
                    return uavs.size();
                }

                @Override
                public Object getElementAt(int i) {
                    return uavs.get(i).getUAV_ID();
                }
            });
        
    }
   static ArrayList<Trackpoint> UAV_1() {
        ArrayList<Trackpoint> targetWaypoint = new ArrayList<>();

        targetWaypoint.add(new Trackpoint(50, new Latitude(35.683052), new Longitude(51.337183), new Date(), "UAV #1 - 1"));
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

    private void addUAV(Trackpoint startPos) {
        if (startPos != null) {
            Random rand = new Random();
            int myRandomNumber = rand.nextInt(0x10) + 0x10;

            MQ1_WaypointTest uav_tmp = new MQ1_WaypointTest(new ArrayList<Trackpoint>(), startPos, lastUAVID++, Integer.toHexString(myRandomNumber));
            UAVComProvider.lst.add(uav_tmp);
            jList2.setModel(new javax.swing.AbstractListModel() {
                @Override
                public int getSize() {
                    return uavs.size();
                }

                @Override
                public Object getElementAt(int i) {
                    return uavs.get(i).getUAV_ID();
                }
            });
        }
    }

    private void addWaypoint(Trackpoint waypoint) {
        if (waypoint != null) {
            uavs.get(selectedUAV).getTargetWaypointList().add(waypoint);
        }
        updateMonitorPanel();
    }

    private void updateMonitorPanel() {
        jList1.setModel(new javax.swing.AbstractListModel() {
                @Override
                public int getSize() {
                    return uavs.get(selectedUAV).getTargetWaypointList().size();
                }

                @Override
                public Object getElementAt(int i) {
                    return uavs.get(selectedUAV).getTargetWaypointList().get(i).toString();
                }
            });
    }

    private Trackpoint Convert2TrackPoint(String alt, String lat, String lon) {
        double altValue = 0.0, latValue = 0.0, lonValue = 0.0;

        try {
            altValue = Double.parseDouble(alt);
            latValue = Double.parseDouble(lat);
            lonValue = Double.parseDouble(lon);
            return new Trackpoint(altValue, new Latitude(latValue), new Longitude(lonValue), new Date());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Error!\n" + ex.getMessage());
            return null;
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

        UAVSetupPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jLabel6 = new javax.swing.JLabel();
        addUAVBTN = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        UAV_StartPOS_LonTF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        UAV_StartPOS_Alt_TF = new javax.swing.JTextField();
        UAV_Start_POS_LatTF = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        UAVMonitorPanel = new javax.swing.JPanel();
        BottomCameraLBL = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        TemperatureValueLBL = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        altitudeTF = new javax.swing.JTextField();
        latitudeTF = new javax.swing.JTextField();
        longitudeTF = new javax.swing.JTextField();
        addWaypointBTN = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        SpeedValueLBL = new javax.swing.JLabel();
        DrawPathLineCB = new javax.swing.JCheckBox();
        DrawWaypointPinCB = new javax.swing.JCheckBox();
        FollowUAVCB = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        UAVSetupPanel.setBackground(new java.awt.Color(204, 204, 255));

        jList2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jList2.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList2ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList2);

        jLabel6.setText("UAV(s):");

        addUAVBTN.setText("Add UAV");
        addUAVBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUAVBTNActionPerformed(evt);
            }
        });

        jLabel9.setText("longitude:");

        jLabel8.setText("altitude:");

        jLabel10.setText("latitude:");

        javax.swing.GroupLayout UAVSetupPanelLayout = new javax.swing.GroupLayout(UAVSetupPanel);
        UAVSetupPanel.setLayout(UAVSetupPanelLayout);
        UAVSetupPanelLayout.setHorizontalGroup(
            UAVSetupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
            .addGroup(UAVSetupPanelLayout.createSequentialGroup()
                .addGroup(UAVSetupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(UAVSetupPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)
                        .addGap(8, 8, 8)
                        .addComponent(UAV_StartPOS_Alt_TF, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(UAVSetupPanelLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(UAV_Start_POS_LatTF, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UAVSetupPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(UAV_StartPOS_LonTF, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(UAVSetupPanelLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(addUAVBTN)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        UAVSetupPanelLayout.setVerticalGroup(
            UAVSetupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UAVSetupPanelLayout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(UAVSetupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(UAV_StartPOS_Alt_TF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(UAVSetupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UAV_Start_POS_LatTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(34, 34, 34)
                .addGroup(UAVSetupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UAV_StartPOS_LonTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addUAVBTN)
                .addContainerGap())
        );

        UAVMonitorPanel.setBackground(new java.awt.Color(255, 204, 204));

        BottomCameraLBL.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Temperature:");

        jScrollPane1.setViewportView(jList1);

        jLabel2.setText("Waypoint(s):");

        jLabel3.setText("altitude:");

        jLabel4.setText("latitude:");

        jLabel5.setText("longitude:");

        addWaypointBTN.setText("Add Waypoint");
        addWaypointBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addWaypointBTNActionPerformed(evt);
            }
        });

        jLabel7.setText("Speed:");

        javax.swing.GroupLayout UAVMonitorPanelLayout = new javax.swing.GroupLayout(UAVMonitorPanel);
        UAVMonitorPanel.setLayout(UAVMonitorPanelLayout);
        UAVMonitorPanelLayout.setHorizontalGroup(
            UAVMonitorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UAVMonitorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(UAVMonitorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(UAVMonitorPanelLayout.createSequentialGroup()
                        .addGroup(UAVMonitorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(UAVMonitorPanelLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(altitudeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(UAVMonitorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addWaypointBTN)
                                    .addGroup(UAVMonitorPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(latitudeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(longitudeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel2))
                        .addContainerGap(13, Short.MAX_VALUE))
                    .addGroup(UAVMonitorPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(31, 31, 31)
                        .addComponent(TemperatureValueLBL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(35, 35, 35)
                        .addComponent(SpeedValueLBL)
                        .addGap(52, 52, 52))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UAVMonitorPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BottomCameraLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        UAVMonitorPanelLayout.setVerticalGroup(
            UAVMonitorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UAVMonitorPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(BottomCameraLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(UAVMonitorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TemperatureValueLBL)
                    .addComponent(jLabel7)
                    .addComponent(SpeedValueLBL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(UAVMonitorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(altitudeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(latitudeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(longitudeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addWaypointBTN)
                .addGap(6, 6, 6))
        );

        DrawPathLineCB.setSelected(true);
        DrawPathLineCB.setText("Draw path line");
        DrawPathLineCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DrawPathLineCBActionPerformed(evt);
            }
        });

        DrawWaypointPinCB.setSelected(true);
        DrawWaypointPinCB.setText("Draw Waypoints pin");
        DrawWaypointPinCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DrawWaypointPinCBActionPerformed(evt);
            }
        });

        FollowUAVCB.setText("Follow UAV");
        FollowUAVCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FollowUAVCBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DrawPathLineCB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DrawWaypointPinCB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FollowUAVCB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(UAVSetupPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UAVMonitorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(UAVSetupPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(DrawPathLineCB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(DrawWaypointPinCB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(FollowUAVCB))
            .addComponent(UAVMonitorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DrawPathLineCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DrawPathLineCBActionPerformed
        tst.setWriteTrack(this.DrawPathLineCB.isSelected());
    }//GEN-LAST:event_DrawPathLineCBActionPerformed

    private void DrawWaypointPinCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DrawWaypointPinCBActionPerformed
        tst.setWritePlacemaks(this.DrawWaypointPinCB.isSelected());
    }//GEN-LAST:event_DrawWaypointPinCBActionPerformed

    private void FollowUAVCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FollowUAVCBActionPerformed
        followUAV = FollowUAVCB.isSelected();
        if(followUAV)
            tst.setFollowingUAVID(selectedUAV+1);
        else
            tst.setFollowingUAVID(-1);
    }//GEN-LAST:event_FollowUAVCBActionPerformed

    private void addUAVBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUAVBTNActionPerformed
        addUAV(Convert2TrackPoint(UAV_StartPOS_Alt_TF.getText(), UAV_Start_POS_LatTF.getText(), UAV_StartPOS_LonTF.getText()));
    }//GEN-LAST:event_addUAVBTNActionPerformed

    private void addWaypointBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addWaypointBTNActionPerformed
        addWaypoint(Convert2TrackPoint(altitudeTF.getText(), latitudeTF.getText(), longitudeTF.getText()));
    }//GEN-LAST:event_addWaypointBTNActionPerformed

    private void jList2ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList2ValueChanged
        uavs.get(selectedUAV).getBottomCamera().setEnable(false);
        selectedUAV = Integer.parseInt(jList2.getSelectedValue().toString())-1;
        System.out.println(selectedUAV);
        uavs.get(selectedUAV).getBottomCamera().setEnable(true);
        updateMonitorPanel();
        if (followUAV) {
            tst.setFollowingUAVID(selectedUAV+1);
        }
        else
            tst.setFollowingUAVID(-1);
    }//GEN-LAST:event_jList2ValueChanged

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
            java.util.logging.Logger.getLogger(MultiUAVAutopilotTEST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MultiUAVAutopilotTEST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MultiUAVAutopilotTEST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MultiUAVAutopilotTEST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MultiUAVAutopilotTEST().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BottomCameraLBL;
    private javax.swing.JCheckBox DrawPathLineCB;
    private javax.swing.JCheckBox DrawWaypointPinCB;
    private javax.swing.JCheckBox FollowUAVCB;
    private javax.swing.JLabel SpeedValueLBL;
    private javax.swing.JLabel TemperatureValueLBL;
    private javax.swing.JPanel UAVMonitorPanel;
    private javax.swing.JPanel UAVSetupPanel;
    private javax.swing.JTextField UAV_StartPOS_Alt_TF;
    private javax.swing.JTextField UAV_StartPOS_LonTF;
    private javax.swing.JTextField UAV_Start_POS_LatTF;
    private javax.swing.JButton addUAVBTN;
    private javax.swing.JButton addWaypointBTN;
    private javax.swing.JTextField altitudeTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField latitudeTF;
    private javax.swing.JTextField longitudeTF;
    // End of variables declaration//GEN-END:variables
}
