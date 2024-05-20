public class ModificadorDeString {
    public static String removerVogais(String s){
        return s.replaceAll("[aeiouAEIOU]", "");
    }

    public static String removerConsoantes(String s){
        return s.replaceAll("[bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ]", "");
    }
}
