import ReverseModule.*;

class ReverseImpl extends ReversePOA {
    public String reverse_string(String name) {
        String reversed = "";
        for (int i = name.length() - 1; i >= 0; i--) {
            reversed += name.charAt(i);
        }
        return reversed;
    }
}
