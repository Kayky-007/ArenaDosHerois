import personagens.*;

import java.io.PrintStream;
import java.util.*;

public class ArenaDosHerois {
    public static void main(String[] args) {

try {
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            new ProcessBuilder("cmd", "/c", "chcp", "65001", ">", "nul")
                .inheritIO().start().waitFor();
        }
        
        // Força o Java a imprimir tudo em UTF-8
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        
    } catch (Exception e) {
        
    }

        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("⚔️  Bem-vindo à Arena dos Heróis ⚔️");
        System.out.println("\nEscolha seu herói:");
        System.out.println("1 - Saitama");
        System.out.println("2 - Capitão América");
        System.out.println("3 - Batman");
        System.out.println("4 - Rick Grimes");
        System.out.println("5 - Homem-Aranha");
        System.out.print("Opção: ");

        int escolha = scanner.nextInt();

        Heroi heroi;
        switch (escolha) {
            case 1: heroi = new Saitama(); break;
            case 2: heroi = new CapitaoAmerica(); break;
            case 3: heroi = new Batman(); break;
            case 4: heroi = new RickGrimes(); break;
            case 5: heroi = new HomemAranha(); break;
            default:
                System.out.println("Opção inválida, você será Batman!");
                heroi = new Batman();
                break;
        }

        Vilao[] viloes = {
            new Garou(), new CaveiraVermelha(), new Coringa(),
            new NeganSmith(), new DuendeVerde()
        };
        Vilao vilao = viloes[rand.nextInt(viloes.length)];

        System.out.println("\nUm vilão apareceu: " + vilao.getNome() + "!");
        System.out.println("Prepare-se para a batalha!\n");

        int turno = 1;

        while (heroi.estaVivo() && vilao.estaVivo()) {
            System.out.println("\n===== ⚔️ TURNO " + turno + " ⚔️ =====");
            heroi.aplicarEfeitos();
            vilao.aplicarEfeitos();

            heroi.exibirStatus();
            vilao.exibirStatus();

            System.out.println("\nEscolha sua ação:");
            System.out.println("1 - Atacar");
            System.out.println("2 - Defender");
            System.out.println("3 - Curar");
            System.out.println("4 - Habilidade Especial");
            System.out.println("5 - Descansar");
            System.out.println("6 - Fugir");
            System.out.print("Opção: ");
            int acao = scanner.nextInt();

            switch (acao) {
                case 1 -> heroi.atacar(vilao);
                case 2 -> heroi.defender();
                case 3 -> heroi.curar();
                case 4 -> heroi.habilidadeEspecial(vilao);
                case 5 -> heroi.descansar();
                case 6 -> {
                    System.out.println("Você fugiu da batalha como um covarde!");
                    scanner.close();
                    return;
                }
                default -> {
                    System.out.println("Opção inválida! Você perdeu a vez.");
                    heroi.descansar();
                }
            }

            if (vilao.estaVivo()) {
                vilao.agir(heroi);
            }

            if (rand.nextInt(100) < 30) {
                Personagem.eventoAleatorio(heroi, vilao);
            }

            turno++;
        }

        if (!heroi.estaVivo()) {
            System.out.println("\n☠️  " + heroi.getNome() + " foi derrotado... Game Over!");
        } else {
            System.out.println("\n🎉 " + vilao.getNome() + " foi destruído! Você venceu!");
        }

        System.out.println("\n🏁 Fim da batalha! Obrigado por jogar!");
        scanner.close();
    }
}