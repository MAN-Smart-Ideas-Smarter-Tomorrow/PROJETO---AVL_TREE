import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Arvore[] arvs = get_file("entrada.txt");
        Scanner sc = new Scanner(System.in);
        
        for (int i = 0; i < arvs.length; i++) {
            System.out.printf("===== Arvore %d =====\n", i+1);
            stats_arvore(arvs[i]);

            boolean op = (i != arvs.length-1) ? opcao(sc) : false;
            
            if (!op) {
                System.out.println("Finalizando...");
                return;
            }
        }

        sc.close();
    }

    private static boolean opcao(Scanner sc) {
        System.out.println();

        while (true) {
            System.out.print("Digite 'S' para continuar ou 'N' para finalizar: ");
            String op = sc.nextLine().trim().toLowerCase();

            if (op.equals("n")) {
                System.out.println();
                return false;
            }
            if (op.equals("s")) {
                System.out.println();
                return true;
            }
            System.out.println("\nOpção inválida! Digite apenas 'S' ou 'N'.");
        }
    }

    private static Arvore nova_arvore(String input, boolean ehArvore) {
        No raiz = null;

        if (ehArvore) raiz = new conversorArvore(input).converter();

        return new Arvore(raiz, input, ehArvore);
    }

    private static void stats_arvore(Arvore arv) {
        if (arv.ehArvore()) {
            System.out.println("Entrada: " + arv.get_expressao());

            System.out.println("\nArvore:");
            arv.exibirArvore();

            System.out.println(
                "\nAltura: " + arv.alturaArvore() +
                "\nArvore: " + arv.ehArvore() +
                "\n   BST: " + arv.ehBST() +
                "\n   ALV: " + arv.ehAVL()
            );

            System.out.println("\nArvore em preOrdem:");
            arv.preOrdem();
        }
        else System.out.printf("A entrada '%s' nao é valida!\n", arv.get_expressao());
    }

    private static Arvore[] get_file(String arquivo_nome) {
        Scanner sc;
        Validador v = new Validador();

        try {
                File arquivo = new File(arquivo_nome);

                // Conta quantas linhas tem
                sc = new Scanner(arquivo);
                int linhas = 0;
                while (sc.hasNextLine()) {
                    sc.nextLine();
                    linhas++;
                }
                sc.close();

                // Cria um array com a quantidade exatas de linhas
                Arvore[] arvs = new Arvore[linhas];

                // Preenche o array com a árvore de cada linha
                sc = new Scanner(arquivo);
                int index = 0;
                while (sc.hasNextLine()) {
                    String linha = sc.nextLine();
                    if (v.validar(linha)) arvs[index++] = nova_arvore(linha, true);
                    else arvs[index++] = nova_arvore(linha, false);
                }
                sc.close();

                return arvs;
        }
        
        catch (FileNotFoundException e) {
            throw new RuntimeException("\nArquivo não encontrado: " + arquivo_nome);
        }
    }
}