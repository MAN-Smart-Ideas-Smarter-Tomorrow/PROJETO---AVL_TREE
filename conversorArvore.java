public class conversorArvore {
    private String[] tokens;
    private int pos;

    public conversorArvore(String expressao) {
        expressao = expressao.replace("(", " ( ").replace(")", " ) ").trim();
        tokens = expressao.split("\\s+");
        pos = 0;
    }

    public No converter() {
        if (pos >= tokens.length) return null;

        String token = tokens[pos++];

        if (token.equals("*")) return null;
        if (token.equals("(")) {
            int valor = Integer.parseInt(tokens[pos++]);

            No esquerda = converter();
            No direita  = converter();

            pos++; // pula o ")"

            return new No(valor, esquerda, direita);
        }
        return null;
    }
}