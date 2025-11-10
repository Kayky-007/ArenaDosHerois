package personagens;

import java.util.Random;

public class CapitaoAmerica extends Heroi {
    private Random rand = new Random();

    public CapitaoAmerica() { super("Capitão América", 85, 18, 10); }

    @Override
    public void atacar(Personagem inimigo) {
        int dano = calcularDano(inimigo) + rand.nextInt(6);
        inimigo.vida -= dano;
        System.out.println("🛡️ " + nome + " arremessou o escudo causando " + dano + " de dano!");
    }

    @Override public void defender() { defendendo = true; System.out.println("🛡️ " + nome + " bloqueou com o escudo!"); }
    @Override public void curar() { vida = Math.min(vidaMaxima, vida + 10); System.out.println("💪 " + nome + " recuperou 10 de vida!"); }

    @Override
    public void habilidadeEspecial(Personagem inimigo) {
        System.out.println("⭐ " + nome + " inspira e dá um golpe especial!");
        int dano = calcularDano(inimigo) + 10;
        inimigo.vida -= dano;
    }

    @Override
    public void agir(Personagem inimigo) {
        // IA simples para vilões/heróis controlados por AI, aqui não usado
    }
}
