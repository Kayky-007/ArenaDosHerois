import personagens.*;
import java.util.*;

public class ArenaDosHerois {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("⚔️  Bem-vindo à Arena dos Heróis ⚔️");
        System.out.println("Escolha seu herói:");
        System.out.println("1 - Saitama");
        System.out.println("2 - Capitão América");
        System.out.println("3 - Batman");
        System.out.println("4 - Rick Grimes");
        System.out.println("5 - Homem-Aranha");
        System.out.print("Opção: ");

        

        int escolha = scanner.nextInt();
        Heroi heroi;

        switch (escolha) {
            case 1:
                heroi = new Saitama();
                break;
            case 2:
                heroi = new CapitaoAmerica();
                break;
            case 3:
                heroi = new Batman();
                break;
            case 4:
                heroi = new RickGrimes();
                break;
            case 5:
                heroi = new HomemAranha();
                break;
            default:
                System.out.println("Opção inválida! Você será o Batman!");
                heroi = new Batman();
                break;
        }

        Vilao[] viloes = {
            new Garou(), new CaveiraVermelha(),
            new Coringa(), new NeganSmith(), new DuendeVerde()
        };

        Vilao vilao = viloes[rand.nextInt(viloes.length)];

        System.out.println("\n💀 Um vilão apareceu: " + vilao.getNome() + "!");
        System.out.println("Prepare-se para a batalha!\n");

        int turno = 1;

        while (heroi.estaVivo() && vilao.estaVivo()) {
            System.out.println("\n===== ⚔️ TURNO " + turno + " ⚔️ =====");
            heroi.exibirStatus();
            vilao.exibirStatus();

            // Evento aleatório com 30% de chance
            if (rand.nextInt(100) < 30) {
                System.out.println("\n⚡ Um evento inesperado ocorre!");
                Personagem.eventoAleatorio(heroi, vilao);
            }

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
                case 1:
                    heroi.atacar(vilao);
                    break;
                case 2:
                    heroi.defender();
                    break;
                case 3:
                    heroi.curar();
                    break;
                case 4:
                    heroi.habilidadeEspecial(vilao);
                    break;
                case 5:
                    heroi.descansar();
                    break;
                case 6:
                    System.out.println("Você fugiu da batalha!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida! Você perdeu tempo e descansou.");
                    heroi.descansar();
                    break;
            }

            // Vilão age depois do herói
            if (vilao.estaVivo()) {
                // usa agir(heroi) clássico (sem turno) — ajuste se suas subclasses usam outro método
                vilao.agir(heroi);
            } else {
                System.out.println("\n💥 " + vilao.getNome() + " foi derrotado!");
                break;
            }

            if (!heroi.estaVivo()) {
                System.out.println("\n☠️ " + heroi.getNome() + " foi derrotado!");
                break;
            }

            turno++;
        }

        System.out.println("\n🏁 Fim da batalha!");
        scanner.close();
    }
}
