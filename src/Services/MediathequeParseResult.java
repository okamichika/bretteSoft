package Services;

public class MediathequeParseResult {
    private String command;
    private int abonneNumero;
    private int documentNumero;

    public MediathequeParseResult(String command, int abonneNumero, int documentNumero) {
        this.command = command;
        this.abonneNumero = abonneNumero;
        this.documentNumero = documentNumero;
    }

    public String getCommand() {
        return command;
    }

    public int getAbonneNumero() {
        return abonneNumero;
    }

    public int getDocumentNumero() {
        return documentNumero;
    }
}
