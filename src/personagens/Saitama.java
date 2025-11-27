package personagens;

import java.util.Random;

public class Saitama extends Heroi {
    private Random rand = new Random();

    public Saitama() {
        super("Saitama", 100, 20, 8, 10);
    }

    @Override
    public void atacar(Personagem inimigo) {
        int dano = calcularDano(inimigo) + rand.nextInt(6);
        inimigo.vida -= dano;
        System.out.println("👊 " + nome + " desferiu um soco causando " + dano + " de dano!");
    }

    @Override
    public void defender() {
        defendendo = true;
        System.out.println("😐 " + nome + " se prepara para reduzir o próximo dano.");
    }

    @Override
    public void curar() {
        if (energia >= 2) {
            int cura = 8 + rand.nextInt(6);
            vida = Math.min(vidaMaxima, vida + cura);
            energia -= 2;
            System.out.println("🧘 " + nome + " curou " + cura + " de vida!");
        } else {
            System.out.println("⚡ Energia insuficiente para curar!");
        }
    }

    @Override
    public void habilidadeEspecial(Personagem inimigo) {
        if (energia >= 5) {
            int dano = 30 + rand.nextInt(11);
            inimigo.vida -= dano;
            energia -= 5;
            System.out.println("💥 " + nome + " usou ataque especial causando " + dano + " de dano!");
        } else {
            System.out.println("⚡ Energia insuficiente! Atacando normalmente...");
            atacar(inimigo);
        }
    }
}
