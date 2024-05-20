package dados;

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
    public void setIdEmail(int idEmail) {
        this.idEmail = idEmail;
    }
    public String getRemetente() {
        return remetente;
    }
    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }
    public String getDestinatario() {
        return destinatario;
    }
    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }
    public String getAssunto() {
        return assunto;
    }
    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public void setDataHora() {
        this.dataHora = LocalDateTime.now();
    }
    public Email getEmailAnterior() {
        return emailAnterior;
    }
    public void setEmailAnterior(Email emailAnterior) {
        this.emailAnterior = emailAnterior;
    }

    public Email() {
        this.emailAnterior = null;
    }

    @Override
    public String toString() {
        cifrar(1);

        String formatted = """
                Assunto: %s
                De: %s
                Para: %s
                Data: %s
                                
                %s
                ------------------------------------
                %s
                """.formatted(this.assunto,
                this.destinatario,
                this.remetente,
                formatarDataTime(this.dataHora),
                this.mensagem,
                this.emailAnterior == null ? "" : this.emailAnterior.toString());

        cifrar(0);

        return formatted;
    }

    public String toStringResumido() {
        return """
                [%s] %s | %s""".formatted(this.idEmail, formatarDataTime(this.dataHora), this.assunto);
    }

    public String formatarDataTime(LocalDateTime d){
        return d.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
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
}
