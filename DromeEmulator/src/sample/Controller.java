package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
//import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.fxml.FXML;
import java.util.ArrayList;
import java.awt.*;
import java.io.IOException;
import java.net.*;

public class Controller {
    private GraphicsContext pen;
    private UdpPackageReceiver receiver;
    private DroneFill drone = new DroneFill();
    @FXML
    private Canvas canvas;
    //@FXML
    //private Label controllerCommand;

    public void initialize() throws UnknownHostException {
        pen = canvas.getGraphicsContext2D();
        pen.setStroke(Color.RED);
        DrawDrone.draw(pen, drone);

        //add udp server/reeciver
        var newList = new ArrayList<>();
        receiver = new UdpPackageReceiver(newList, 41516, this);
        new Thread(receiver).start();
    }

    //Method to control drone movement when recieving messages
    public void droneMovement(String message) {
        System.out.println(message);
        //controllerCommand.setText(message);

        if (message.startsWith("RIGHT")) DroneFill.rectX += 5;
        {
            DrawDrone.draw(canvas.getGraphicsContext2D(), drone);
        }
        if (message.startsWith("LEFT")) DroneFill.rectX -= 5;
        {
            DrawDrone.draw(canvas.getGraphicsContext2D(), drone);
        }
        if (message.startsWith("DOWN")) DroneFill.rectY += 5;
        {
            DrawDrone.draw(canvas.getGraphicsContext2D(), drone);
        }
        if (message.startsWith("UP")) DroneFill.rectY -= 5;
        {
            DrawDrone.draw(canvas.getGraphicsContext2D(), drone);
        }
        //Reset drone location to 100 by 100 pixels on canvas
        if (message.startsWith("Init")) {
            DroneFill.rectX = 100;
            DroneFill.rectY = 100;
        }
    }
}