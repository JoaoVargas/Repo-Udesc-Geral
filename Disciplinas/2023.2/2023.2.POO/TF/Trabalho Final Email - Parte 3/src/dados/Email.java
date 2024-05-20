package dados;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Email {
    private int idEmail;
    private String remetente;
    private String destinatario;
    private String assunto;
    private String mensagem;
    private LocalDateTime dataHora;
    private Email emailAnterior;

    public int getIdEmail() {
        return idEmail;
    }
    public String getRemetente() {
        return remetente;
    }
    public String getDestinatario() {
        return destinatario;
    }
    public String getAssunto() {
        return assunto;
    }
    public String getMensagem() {
        return mensagem;
    }
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public Email getEmailAnterior() {
        return emailAnterior;
    }
    public void setEmailAnterior(Email emailAnterior) {
        this.emailAnterior = emailAnterior;
    }

    public Email(int idEmail, String remetente, String destinatario, String assunto, String mensagem) {
        this.idEmail = idEmail;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.assunto = assunto;
        this.mensagem = mensagem;
        this.dataHora = LocalDateTime.now();
        this.emailAnterior = null;

        cifrar(0);
    }
    public Email(int idEmail, String remetente, String destinatario, String assunto, String mensagem, Email emailAnterior) {
        this.idEmail = idEmail;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.assunto = assunto;
        this.mensagem = mensagem;
        this.dataHora = LocalDateTime.now();
        this.emailAnterior = emailAnterior;

        cifrar(0);
    }
    public Email(int idEmail, String remetente, String destinatario, String assunto, String mensagem, Timestamp timestamp, Email emailAnterior) {
        this.idEmail = idEmail;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.assunto = assunto;
        this.mensagem = mensagem;
        this.dataHora = timestamp.toLocalDateTime();
        this.emailAnterior = emailAnterior;

        cifrar(0);
    }

    public String formatarDataTime(LocalDateTime d){
        return d.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
    public Timestamp formatarDataTimeTimestamp(LocalDateTime d){
        return Timestamp.valueOf(d);
    }

    public void cifrar(int cond){
        char[] maiusculas = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char[] minusculas = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        String cifrado = "";
        String id = String.valueOf(idEmail);
        String dia = this.dataHora.format(DateTimeFormatter.ofPattern("dd"));
        int unidadeId = Integer.parseInt(id.substring(id.length() - 1));
        int diaData = Integer.parseInt(dia.substring(dia.length() - 1));

        if (cond == 0){
            int chave = unidadeId + diaData;

            for (char letra : this.mensagem.toCharArray()) {
                if (letra >= 65 && letra <= 90){
                    int novaLetra = (letra - 65 + chave) % 26;
                    cifrado += maiusculas[novaLetra];
                } else if (letra >= 97 && letra <= 122) {
                    int novaLetra = (letra - 97 + chave) % 26;
                    cifrado += minusculas[novaLetra];
                } else {
                    cifrado += letra;
                }
            }

            this.mensagem = cifrado;
        } else if (cond == 1) {
            int chave = 26 - (unidadeId + diaData);

            for (char letra : this.mensagem.toCharArray()) {
                if (letra >= 65 && letra <= 90){
                    int novaLetra = (letra - 65 + chave) % 26;
                    cifrado += maiusculas[novaLetra];
                } else if (letra >= 97 && letra <= 122) {
                    int novaLetra = (letra - 97 + chave) % 26;
                    cifrado += minusculas[novaLetra];
                } else {
                    cifrado += letra;
                }
            }

            this.mensagem = cifrado;
        }
    }

    public String toStringBloco() {
        cifrar(1);

        String s = "<html><body><h2>%s</h2><br><h5>De: </h5>%s<br><h5>Para: </h5>%s<br><h5>Data: </h5>%s<br><br>%s<br>-----------------------------------------------------<br>%s</body></html>".formatted(
                    this.assunto,
                    this.destinatario,
                    this.remetente,
                    formatarDataTime(this.dataHora),
                    this.mensagem,
                    (this.emailAnterior == null ? "" : this.emailAnterior.toStringBloco())
        );

        cifrar(0);

        return s;
    }

    public String toStringLinha() {
        String s = """
                [%s] %s | %s""".formatted(
                    this.idEmail,
                    formatarDataTime(this.dataHora),
                    this.assunto
        );


        return s;
    }
}
