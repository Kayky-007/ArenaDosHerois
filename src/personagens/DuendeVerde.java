package personagens;

import java.util.Random;

public class DuendeVerde extends Vilao {
    private Random rand = new Random();

    public DuendeVerde() {
        super("Duende Verde", 70, 16, 6, 10);
    }

    @Override
    public void atacar(Personagem heroi) {
        int dano = calcularDano(heroi) + rand.nextInt(7);
        heroi.vida -= dano;
        System.out.println("🎃 " + nome + " lançou bomba de abóbora causando " + dano + " de dano!");
    }

    @Override
    public void defender() {
        defendendo = true;
        System.out.println("🛶 " + nome + " manobrou o planador para evitar o ataque!");
    }

    @Override
    public void curar() {
        int cura = 7;
        vida = Math.min(vidaMaxima, vida + cura);
        System.out.println("🧪 " + nome + " usou soro e recuperou " + cura + " de vida!");
    }

    @Override
    public void habilidadeEspecial(Personagem inimigo) {
        System.out.println("💥 " + nome + " faz explosão e aplica ENVENENAMENTO!");
        inimigo.efeitos.add(Status.ENVENENAMENTO);
    }
}
