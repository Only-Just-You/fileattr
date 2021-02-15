package org.example.fileattr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class FileAttrFrame extends JFrame {
    private final BorderLayout borderLayout1 = new BorderLayout();
    private final JTextField jTextField1 = new JTextField();
    private final JScrollPane jScrollPane1 = new JScrollPane();
    private final JButton jButton1 = new JButton();
    File file;

    public FileAttrFrame(){
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit(){
        JPanel contentPane = (JPanel) this.getContentPane();
        jTextField1.setText("文件的地址");
        jTextField1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jTextField1.setText("");
            }
        });
        contentPane.setLayout(borderLayout1);
        this.setSize(new Dimension(750,164));
        this.setTitle("文件属性查看器");
        jScrollPane1.setAutoscrolls(true);
        jButton1.setText("查看");

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton1_actionPerformed();
            }
        });
        contentPane.add(jButton1, BorderLayout.SOUTH);
        contentPane.add(jTextField1, BorderLayout.NORTH);
        contentPane.add(jScrollPane1, BorderLayout.CENTER);
    }
    protected void processWindowEvent(WindowEvent e){
        super.processWindowEvent(e);
        if(e.getID() == WindowEvent.WINDOW_CLOSING){
            System.exit(0);
        }
    }

    private void jButton1_actionPerformed() {
        file = new File(this.jTextField1.getText());
        JTable jTable1 = new JTable(this.getFileInfo(file), this.getFileInfoType());
        jScrollPane1.getViewport().add(jTable1,null);
    }

    private String[][] getFileInfo(File file) {
        String[][] data = new String[1][7];
//        if(theFile.exists()) return data;
        data[0][0] = file.getName();
        data[0][1] = String.valueOf(file.isFile());
        if(file.isDirectory()) data[0][2] = "";
        else data[0][2] = file.length() + " KB ("+
                file.length() / 1024 / 1024 +" MB)";
        data[0][3] = String.valueOf(file.canRead());
        data[0][4] = String.valueOf(file.canWrite());
        data[0][5] = getDateString(file.lastModified());
        data[0][6] = String.valueOf(file.isHidden());
        return data;
    }

    public static String getDateString(long lastModified) {
        if(lastModified < 0) return "";
        Date date = new Date(lastModified);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        int year = rightNow.get(Calendar.YEAR);
        int month = rightNow.get(Calendar.MONTH);
        int day = rightNow.get(Calendar.DAY_OF_MONTH);
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        int min = rightNow.get(Calendar.MINUTE);
        return year + "-" + (month < 10 ? "0" + month : "" + month) + "-"
                + (day < 10 ? "0" + day : "" + day) + " "
                + (hour < 10 ? "0" + hour : "" + hour) + ":"
                + (min < 10 ? "0" + min : "" + min);
    }

    private Object[] getFileInfoType() {
        return new Object[] {"Name","isFile","length","canRead","canWrite","lastMotified","isHidden"};
    }
}
