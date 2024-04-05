package Services;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServiceReservation implements Runnable {
    private Socket socket;

    public ServiceReservation(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
        ) {
            String command = (String) in.readObject();
            MediathequeParseResult parseResult = MediathequeService.parseCommand(command);
            // Logic to handle reservation command using parseResult
        } catch (IOException | ClassNotFoundException | MediathequeCommandParsingException e) {
            e.printStackTrace();
        }
    }
}
