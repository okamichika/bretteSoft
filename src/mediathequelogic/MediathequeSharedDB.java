package mediathequelogic;

import java.util.HashMap;
import java.util.Map;

public class MediathequeSharedDB {
    private static final Map<Integer, Document> documents = new HashMap<>();
    private static final Map<Integer, Abonne> abonnes = new HashMap<>();

    public static void addDocument(Document document) {
        documents.put(document.getNumero(), document);
    }

    public static Document getDocument(int numero) {
        return documents.get(numero);
    }

    public static void addAbonne(Abonne abonne) {
        abonnes.put(abonne.getNumero(), abonne);
    }

    public static Abonne getAbonne(int numero) {
        return abonnes.get(numero);
    }
}
