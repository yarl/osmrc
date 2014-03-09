package org.openstreetmap.osmrc;

import java.awt.event.WindowAdapter;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.osmrc.Configuration.Profile;
import org.openstreetmap.osmrc.DataContainer.Node;

public class Main extends javax.swing.JFrame implements ZMapWidgetListener, ConfigurationListener {
  private static final long serialVersionUID = -2976479683829295126L;
  public static Main instance;
    
  private ZMapWidget map;
  private Timer refetchTimer;
  private int seqNum;
  private int firstSeq = Integer.MAX_VALUE;

  public DataContainer dc;
  private Configuration conf = new Configuration();
  
  boolean setBox;

  public Main() {

    dc = new DataContainer();
    this.setTitle("OSMZmiany");
    this.addWindowListener(new windowHandler());

    conf = Configuration.loadFromFile();
    conf.addConfigurationListener(this);
    //MAP
    map = new ZMapWidget(dc);
    Profile p = conf.getSelectedProfile();
    map.addZMapWidgetListener(this);
    initComponents();

    initChangeStream();

    //show
    setLocationRelativeTo(null);
    setVisible(true);
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jPanel1 = new javax.swing.JPanel();
    pMap = map;
    jPanel2 = new javax.swing.JPanel();
    bLiveEdit = new javax.swing.JToggleButton();
    jLabel1 = new javax.swing.JLabel();
    tLiveEditStatus = new javax.swing.JLabel();
    jPanel3 = new javax.swing.JPanel();
    jLabel3 = new javax.swing.JLabel();
    btNode = new javax.swing.JButton();
    btChangeset = new javax.swing.JButton();
    btUser = new javax.swing.JButton();
    bGetLast = new javax.swing.JButton();
    tGetLast = new javax.swing.JTextField();
    jMenuBar1 = new javax.swing.JMenuBar();
    jMenu1 = new javax.swing.JMenu();
    jMenu2 = new javax.swing.JMenu();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setBounds(new java.awt.Rectangle(0, 0, 0, 0));

    jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Map"));

    pMap.setMinimumSize(new java.awt.Dimension(650, 100));

    javax.swing.GroupLayout pMapLayout = new javax.swing.GroupLayout(pMap);
    pMap.setLayout(pMapLayout);
    pMapLayout.setHorizontalGroup(
      pMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 0, Short.MAX_VALUE)
    );
    pMapLayout.setVerticalGroup(
      pMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 0, Short.MAX_VALUE)
    );

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(pMap, javax.swing.GroupLayout.PREFERRED_SIZE, 638, Short.MAX_VALUE)
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addComponent(pMap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap())
    );

    jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Options"));

    bLiveEdit.setText("Live Edit");
    bLiveEdit.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        bLiveEditActionPerformed(evt);
      }
    });

    jLabel1.setText("Status:");

    tLiveEditStatus.setText("jLabel2");

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(bLiveEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(jPanel2Layout.createSequentialGroup()
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(tLiveEditStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)))
        .addContainerGap())
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(bLiveEdit)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(tLiveEditStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(176, Short.MAX_VALUE))
    );

    jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Info"));

    jLabel3.setText("jLabel3");

    btNode.setText("jButton1");

    btChangeset.setText("jButton2");

    btUser.setText("jButton3");

    bGetLast.setText("jButton1");
    bGetLast.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        bGetLastActionPerformed(evt);
      }
    });

    tGetLast.setText("2");

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
      jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel3Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(btNode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(btChangeset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(btUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(tGetLast, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(bGetLast, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap())
    );
    jPanel3Layout.setVerticalGroup(
      jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel3Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel3)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(btNode)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(btChangeset)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(btUser)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(bGetLast)
          .addComponent(tGetLast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap())
    );

    jMenu1.setText("File");
    jMenuBar1.add(jMenu1);

    jMenu2.setText("Edit");
    jMenuBar1.add(jMenu2);

    setJMenuBar(jMenuBar1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents


  private void bLiveEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLiveEditActionPerformed
  }//GEN-LAST:event_bLiveEditActionPerformed

  private void bGetLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGetLastActionPerformed
    if (firstSeq == Integer.MAX_VALUE) {
      initChangeStream();
    }

    Thread t = new Thread() {
      @Override
      public void run() {
        final int values = 60 * Integer.parseInt(tGetLast.getText());
        int sq = firstSeq;
        //btnGetLast.setEnabled(false);
        while (firstSeq - sq < values && sq > 0) {
          getData(sq);
          sq--;
          //postep.setText("" + (firstSeq - sq) + "/" + values);
        }
        //postep.setText("Complete");
        firstSeq = sq;
        //btnGetLast.setEnabled(true);
      }
    };
    t.start();
  }//GEN-LAST:event_bGetLastActionPerformed

  private void btSChangeset() {
    if (map.drawStyle.getSelectedChangeset() == null
            && map.drawStyle.getSelectedNode() != null) {
      //btSelectChangeset.setEnabled(true);
    } else {
      //btSelectChangeset.setEnabled(false);
    }
  }

  
  private void initChangeStream() {
    try {
      BufferedReader br;
      br = new BufferedReader(
              new InputStreamReader(
                      new BufferedInputStream(
                              new URL(
                                      conf.getDiffBaseUrl() + "state.txt")
                              .openStream())));
      br.readLine();
      String seqNumStr = br.readLine();

      seqNum = Integer.parseInt(seqNumStr.substring(seqNumStr
              .indexOf("=") + 1));

      if (seqNum < firstSeq) {
        firstSeq = seqNum;
      }

      br.readLine();
      br.close();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getData() {
    getData(seqNum);
    seqNum++;
  }

  public void getData(int seqNum) {
    DecimalFormat myFormat = new DecimalFormat("000");
    String url = conf.getDiffBaseUrl()
            + myFormat.format(seqNum / 1000000) + "/"
            + myFormat.format((seqNum / 1000) % 1000) + "/"
            + myFormat.format(seqNum % 1000) + ".osc.gz";
    getData(url);
  }

  public void getData(String url) {
    try {
      BufferedInputStream bis;
      bis = new BufferedInputStream(
              new GZIPInputStream(new URL(url).openStream()));
      System.out.println("Download: " + url);
      dc.addData(bis);
    } catch (MalformedURLException e) {
      System.err.println("");
    } catch (IOException e) {
      System.err.println("");
    }
  }

  public void reloadChangesets() {
    /*model.clear();
    Iterator<Changeset> iterator = dc.getChangesets().iterator();
    while (iterator.hasNext()) {
      Changeset ch = iterator.next();
      model.add(0, ch.id + ":" + dc.getUsers().get(ch.userId).name);
    }*/
  }

  public static void openURL(String url) {
    if (!java.awt.Desktop.isDesktopSupported()) {
      System.err.println("Desktop is not supported (fatal)");
    }
    java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
    if (!desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
      System.err.println("Desktop doesn't support the browse action (fatal)");
    }
    try {
      java.net.URI uri = new java.net.URI(url);
      desktop.browse(uri);
    } catch (Exception e) {
    }
  }

  public void nodeClicked(Node node) {
    btNode.setText(Long.toString(node.id));
    btChangeset.setText(Long.toString(node.changesetId));
    btUser.setText(dc.getUsers().get(dc.getChangesets().get(dc.getChangesetsIndex().get(node.changesetId)).userId).name);
    //this.tabbedPane.setSelectedIndex(3);
    map.drawStyle.setSelection(node);
    btSChangeset();
  }

  public void profileChanged(Profile p) {
    this.reloadUsersList();
    map.refrashOverlay(true);
  }

  public void reloadProfiles() {/*
    profilesModel.removeAllElements();
    Profile[] p = conf.getProfiles();
    Profile s = conf.getSelectedProfile();
    int tmpInt = 0;
    for (int i = 0; i < p.length; i++) {
      if (p[i] == s) {
        tmpInt = i;
      }
      profilesModel.addElement(p[i].getName());
    }
    this.cbProfiles.setSelectedIndex(tmpInt);*/
  }

  public void reloadUserType() {/*
    Profile p = conf.getSelectedProfile();
    //Combo type	
    usersListType.setSelectedIndex(p.getListType());*/
  }

  public void reloadUsersList() {/*
    Profile p = conf.getSelectedProfile();
    //Users
    modelUsers.removeAllElements();
    User[] users = p.getUsers();
    for (int i = 0; i < users.length; i++) {
      modelUsers.addElement(users[i].id + ":" + users[i].name);
    }*/
  }

  private class windowHandler extends WindowAdapter {

    public void windowClosing(java.awt.event.WindowEvent event) {
      System.out.println("EXIT");
      conf.setWindowSize(getSize());
      conf.saveToFile();
    }
  }

  public void boxDrawed(Coordinate c1, Coordinate c2) {
    if (setBox) {
      double d1 = Math.round(c1.getLat() * 100);
      d1 /= 100;
      double d2 = Math.round(c1.getLon() * 100);
      d2 /= 100;
      double e1 = Math.round(c2.getLat() * 100);
      e1 /= 100;
      double e2 = Math.round(c2.getLon() * 100);
      e2 /= 100;

      //lblBoxB1.setText("LAT:" + d1 + ";LON:" + d2);
      //lblBoxB2.setText("LAT:" + e1 + ";LON:" + e2);
      MapFilter mf = new BoundaryMapFilter(c1.getLat(), c1.getLon(), c2.getLat(), c2.getLon());
      dc.removeData(mf);
      conf.getSelectedProfile().setMapFilter(mf);
      map.refrashOverlay(true);
      setBox = false;
    }
  }

  private static String newStringDialog(JFrame frame, String name, String text) {
    JPanel panel = new JPanel();
    panel.add(new JLabel(text));
    final JTextField fooField = new JTextField(10);
    panel.add(fooField);
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        fooField.requestFocusInWindow();
      }
    });
    int choice = JOptionPane.showConfirmDialog(frame, panel,
            name, JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE);

    switch (choice) {
      case JOptionPane.OK_OPTION:
        return fooField.getText();
    }
    return null;
  }

  private static int findZoom(double lat, double lon) {
    int zoom = 1;
    double p = 360;
    double d = 180;
    for (; p > lat && d > lon; zoom++) {
      p /= 2;
      d /= 2;
    }

    return zoom;
  }

  public static void main(String args[]) {
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code ">
    try {
      try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (ClassNotFoundException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
      } catch (InstantiationException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
      }
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        Main main = new Main();
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton bGetLast;
  private javax.swing.JToggleButton bLiveEdit;
  private javax.swing.JButton btChangeset;
  private javax.swing.JButton btNode;
  private javax.swing.JButton btUser;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JMenu jMenu1;
  private javax.swing.JMenu jMenu2;
  private javax.swing.JMenuBar jMenuBar1;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JPanel jPanel3;
  private javax.swing.JPanel pMap;
  private javax.swing.JTextField tGetLast;
  private javax.swing.JLabel tLiveEditStatus;
  // End of variables declaration//GEN-END:variables
}
