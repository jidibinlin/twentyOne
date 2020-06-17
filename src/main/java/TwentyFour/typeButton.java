package TwentyFour;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.event.*;

public class typeButton extends JButton {

    public typeButton(String text) {
        super(text);
        Dimension preferredSize = new Dimension(100, 30);
        this.setPreferredSize(preferredSize);
        this.setEnabled(false);
    }

    // public void reqSendLisen(final Server server) {
    //     this.addActionListener(new ActionListener() {
    //         public void actionPerformed(ActionEvent e) {
    //             String toSend = ClientDate.genRequestCard("requestCard", "client1");
    //             try {
    //                 server.writer.write(toSend + "\n");
    //             } catch (IOException e1) {
    //                 // TODO Auto-generated catch block
    //                 e1.printStackTrace();
    //             }
    //         }
    //     });

    // }

    // public void stopReqSendLisen(final Server server){
    //     this.addActionListener(new ActionListener(){
    //         public void actionPerformed(ActionEvent e){
    //             String toSend = ClientDate.genRequestCard("stopRequestCard", "client1");

    //             try {
    //                 server.writer.write(toSend + "\n");
    //             } catch (IOException e1) {
    //                 // TODO Auto-generated catch block
    //                 e1.printStackTrace();
    //             }
    //         }

    //     });
    // }

}