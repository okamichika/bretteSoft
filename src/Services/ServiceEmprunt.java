package Services;

import mediathequelogic.Document;
import mediathequelogic.EmpruntException;
import mediathequelogic.Livre;
import mediathequelogic.MediathequeSharedDB;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServiceEmprunt implements Runnable {
    private Socket socket;

    public ServiceEmprunt(Socket socket) {
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
            int abonneNumero = parseResult.getAbonneNumero();
            int documentNumero = parseResult.getDocumentNumero();

            // Get the Document object from the MediathequeSharedDB
            Document document = MediathequeSharedDB.getDocument(documentNumero);

            // Check if the Document is a Livre
            if (document instanceof Livre) {
                Livre livre = (Livre) document;
                // Check if the Livre is available and not reserved
                if (!livre.isReserved() && !livre.isBorrowed()) {
                    // Perform the borrow operation
                    livre.borrowBy(MediathequeSharedDB.getAbonne(abonneNumero));
                    out.writeObject("Borrow successful");
                } else {
                    out.writeObject("Borrow unsuccessful: Livre is not available for borrowing");
                }
            } else {
                out.writeObject("Borrow unsuccessful: Document is not a Livre");
            }
        } catch (IOException | ClassNotFoundException | MediathequeCommandParsingException | EmpruntException e) {
            e.printStackTrace();
        }
    }
}
