public class Inversor {
    public static String inverterString(String texto) {
//        if (texto == null){
//            return null;
//        }

        char []letras = texto.toCharArray();
        char []invertido = new char[letras.length];

        int index = letras.length - 1;
        for(char letra : letras) {
            invertido[index] = letra;
            index--;
        }
        return new String(invertido);
    }
}