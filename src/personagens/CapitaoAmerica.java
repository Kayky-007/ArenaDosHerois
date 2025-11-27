package personagens;

import java.util.Random;

public class CapitaoAmerica extends Heroi {
    private Random rand = new Random();

    public CapitaoAmerica() {
        super("Capitão América", 85, 18, 10, 10);
    }

    @Override
    public void atacar(Personagem inimigo) {
        int dano = calcularDano(inimigo) + rand.nextInt(6);
        inimigo.vida -= dano;
        System.out.println("🛡️ " + nome + " arremessou o escudo causando " + dano + " de dano!");
    }

    @Override
    public void defender() {
        defendendo = true;
        System.out.println("🦅 " + nome + " bloqueou com o escudo!");
    }

    @Override
    public void curar() {
        if (energia >= 2) {
            int cura = 10;
            vida = Math.min(vidaMaxima, vida + cura);
            energia -= 2;
            System.out.println("💪 " + nome + " recuperou " + cura + " de vida!");
        } else {
            System.out.println("⚡ Energia insuficiente para curar!");
        }
    }

    @Override
    public void habilidadeEspecial(Personagem inimigo) {
        if (energia >= 5) {
            System.out.println("⭐ " + nome + " inspira e golpeia com força extra!");
            int dano = calcularDano(inimigo) + 12;
            inimigo.vida -= dano;
            energia -= 5;
        } else {
            System.out.println("⚡ Energia insuficiente! Atacando normalmente...");
            atacar(inimigo);
        }
    }
}
