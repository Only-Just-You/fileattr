package org.example.fileattr;

import java.awt.*;

public class FileAttrView {
    public FileAttrView(){
        FileAttrFrame frame = new FileAttrFrame();
        frame.validate();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if(frameSize.height > screenSize.height){
            frameSize.height = screenSize.height;
        }
        if(frameSize.width > screenSize.width){
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new FileAttrView();
    }
}
