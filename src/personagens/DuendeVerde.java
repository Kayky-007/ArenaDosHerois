package personagens;

import java.util.Random;

public class DuendeVerde extends Vilao {
    private Random rand = new Random();
    public DuendeVerde() { super("Duende Verde", 70, 16, 6); }

    @Override public void atacar(Personagem heroi) {
        int dano = calcularDano(heroi) + rand.nextInt(7);
        heroi.vida -= dano;
        System.out.println("🎃 " + nome + " lançou bomba de abóbora causando " + dano + " de dano!");
    }
    @Override public void defender() { defendendo = true; System.out.println("🛶 " + nome + " manobrou o planador!"); }
    @Override public void curar() { vida = Math.min(vidaMaxima, vida + 7); System.out.println("🧪 " + nome + " tomou soro (+7 HP)!"); }
    @Override public void habilidadeEspecial(Personagem inimigo) { System.out.println("💥 " + nome + " faz explosão e aplica CONFUSÃO(simulada)"); inimigo.efeitos.add(Status.ENVENENAMENTO); }
    @Override
    public void agir(Personagem heroi) {
        aplicarEfeitos();
        int a = rand.nextInt(3);
        switch (a) {
            case 0: atacar(heroi); break;
            case 1: defender(); break;
            case 2: habilidadeEspecial(heroi); break;
            default: defender(); break;
        }
        energia = Math.max(0, energia - 2);
    }
}
