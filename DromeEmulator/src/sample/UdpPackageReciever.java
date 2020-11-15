package sample;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.List;

class UdpPackageReceiver implements Runnable{

    private boolean running = false;
    private DatagramSocket socket;
    private byte[] buf = new byte[256];
    private Controller controller;
    int port = 4000;

    List udpPackages;

    public UdpPackageReceiver(List newPackages, int port, Controller controller) {
        this.controller = controller;
        this.running = true;
        this.udpPackages = newPackages;
        this.port = port;
        try {
            socket = new DatagramSocket(this.port, InetAddress.getLocalHost());
        } catch (SocketException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (running) //loop to constantly check if packages are recieved
        {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet); //catch any packet sent
                String message = new String(packet.getData()); //convert data to string
                var msg = message.substring(0,packet.getLength()); // deletes unnecessary data
                controller.droneMovement(msg); //Send message to controller
            } catch (IOException e) {
                e.printStackTrace();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}