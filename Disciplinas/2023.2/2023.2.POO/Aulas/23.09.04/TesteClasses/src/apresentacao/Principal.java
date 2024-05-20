package apresentacao;

import dados.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Principal {

    public static void main(String[] args) {
        Classe2 classe2a = new Classe2(0, null);
        Classe2 classe2b = new Classe2(2, classe2a);
        Classe1 classe1a = new Classe1(1, classe2b);

        String a = "a";
        String b = "aaaaab";

        System.out.println(a.compareToIgnoreCase(b));

//        classe2a.setNum(3);
//
//        System.out.println(classe1a.getNum());
//        System.out.println(classe1a.getTeste().getNum());
//        System.out.println(classe1a.getTeste().getTeste().getNum());
//
//        classe2a.setTeste(classe2a);
//
//        System.out.println(classe2a.getNum());
//        System.out.println(classe2a.getTeste().getNum());
//        System.out.println(classe2a.getTeste().getTeste().getNum());
//
//        System.out.println(LocalDateTime.now());
//        System.out.println(LocalDateTime.now().getYear());
//
//        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//
//        System.out.println(LocalDateTime.now().format(myFormatObj));



    }
}
