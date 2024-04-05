package serveur;

import Services.ServiceEmprunt;
import Services.ServiceReservation;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Serveur {
    private static final int PORT_RESERVATION = 3000;
    private static final int PORT_EMPRUNT_RETOUR = 4000;

    public static void main(String[] args) {
        try (ServerSocket reservationServerSocket = new ServerSocket(PORT_RESERVATION);
             ServerSocket empruntRetourServerSocket = new ServerSocket(PORT_EMPRUNT_RETOUR)) {

            ExecutorService reservationExecutor = Executors.newFixedThreadPool(10);
            ExecutorService empruntRetourExecutor = Executors.newFixedThreadPool(10);

            System.out.println("Reservation server started on port " + PORT_RESERVATION);
            System.out.println("Emprunt/Retour server started on port " + PORT_EMPRUNT_RETOUR);

            while (true) {
                reservationExecutor.submit(new ServiceReservation(reservationServerSocket.accept()));
                empruntRetourExecutor.submit(new ServiceEmprunt(empruntRetourServerSocket.accept()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

