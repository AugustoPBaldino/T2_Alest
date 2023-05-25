import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class App {
    private Scanner in;
    private LinkedListOfStreet lista;

    public App() {
        in = new Scanner(System.in);
        lista = new LinkedListOfStreet();
        LeituraArquivo.Leitura(lista);
    }

    public void start() {
        System.out.println("Bem-vindo ao Menu!");
        try (Scanner scanner = new Scanner(System.in)) {
            int opcao;
            do {
                showMenu();
                opcao = readIntInput(scanner, "Digite a opção desejada: ");
                switch (opcao) {
                    case 1:
                        mostSinalization();
                        break;
                    case 2:
                        monthMostSinalization();
                        break;
                    case 3:
                        navigationMode();
                        break;
                    case 0:
                        break;
                    default:
                        System.err.println("Opção inválida. Redigite.");
                }
            } while (opcao != 0);
        } catch (Exception e) {
            System.err.println("Ocorreu um erro: " + e.getMessage());
        }
    }

    private void showMenu() {
        System.out.println("===== Menu =====");
        System.out.println("1. Mostrar a via com maior número de sinalizações registradas");
        System.out.println("2. Mostrar o mês com maior número de implementações de sinalizações em uma rua/av/trav");
        System.out.println("3. Modo Navegação");
        System.out.println("0. Sair");
    }

    public void mostSinalization() {
        Rua_Av_Trav element = lista.moreSinalizations();
        System.out.println("Rua/Av/Trav com mais sinalizações registradas: " + element.getNomeEnd()
                + " -> " + element.getLista().size() + " sinalizações.");
    }

    public void monthMostSinalization() {
        System.out.println("Digite o nome da rua/av/trav que você deseja saber o mês em que foram mais implementadas sinalizações: ");
        String nome = in.nextLine().toUpperCase();
        Map<String, String> map = lista.monthMostSinalizations(nome);
        System.out.println("Mês em que foram implementadas mais sinalizações em " + nome + ": "
                + map.get("mes") + " -> " + map.get("quantidade"));
    }

    public void navigationMode() {
        int index = 0;

        System.out.println("Iniciando a navegação...");

        while (true) {
            Rua_Av_Trav element = getElementAtIndex(index);
            if (element == null) {
                break;
            }

            printNavigationInfo(element, index);
            System.out.println("Para avançar, digite 'next'. Para retroceder, digite 'prev'. Para sair do modo de navegação digite'exit'");
            String opcaoStr;
            do {
                opcaoStr = in.nextLine();
                if (opcaoStr.equals("next")) {
                    if (index >= lista.size() - 1) {
                        System.out.println("Você está no fim da lista.");
                    } else {
                        index++;
                        break;
                    }
                } else if (opcaoStr.equals("prev")) {
                    if (index <= 0) {
                        System.out.println("Você está no início da lista.");
                    } else {
                        index--;
                        break;
                    }
                } else if (opcaoStr.equals("exit")) {
                    break;
                } else {
                    System.out.println("Opção incorreta. Por favor, digite uma opção válida.");
                }
            } while (true);

            if (opcaoStr.equals("exit")) {
                break;
            }
        }

        System.out.println("Modo de navegação encerrado.");
    }

    private Rua_Av_Trav getElementAtIndex(int index) {
        try {
            return lista.get(index);
        } catch (InputMismatchException e) {
            System.out.println("Tipo incorreto. Por favor, redigite.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Índice inválido. Por favor, redigite.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Por favor, redigite.");
        }
        return null;
    }

    private void printNavigationInfo(Rua_Av_Trav element, int index) {
        int start = Math.max(0, index - 2);
        int end = Math.min(index + 2, lista.size() - 1);

        for (int i = start; i <= end; i++) {
            Rua_Av_Trav currentElement = lista.get(i);

            if (i == index) {
                int tam = currentElement.getLista().size();
                System.out.println("--> " + currentElement.getNomeEnd() + ": " + "\n " + "Total de sinalizações = "
                        + tam + "\n" + " Primeira sinalização = " + currentElement.getLista().getMenorData()
                        + "\n" + " Última sinalização = " + currentElement.getLista().getMaiorData() + " <--" + "\n");
            }
        }
    }

    private int readIntInput(Scanner scanner, String message) {
        boolean validInput = false;
        int value = 0;

        while (!validInput) {
            try {
                System.out.print(message);
                value = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.err.println("Tipo incorreto. Redigite.");
                scanner.nextLine(); 
            }
        }

        return value;
    }
}
