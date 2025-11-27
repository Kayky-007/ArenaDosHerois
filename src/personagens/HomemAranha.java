package personagens;

import java.util.Random;

public class HomemAranha extends Heroi {
    private Random rand = new Random();

    public HomemAranha() {
        super("Homem-Aranha", 80, 17, 8, 10);
    }

    @Override
    public void atacar(Personagem inimigo) {
        int dano = calcularDano(inimigo) + rand.nextInt(7);
        inimigo.vida -= dano;
        System.out.println("🕸️ " + nome + " lançou teias e causou " + dano + " de dano!");
    }

    @Override
    public void defender() {
        defendendo = true;
        System.out.println("🤸 " + nome + " desviou agilmente do próximo ataque!");
    }

    @Override
    public void curar() {
        if (energia >= 2) {
            int cura = 7;
            vida = Math.min(vidaMaxima, vida + cura);
            energia -= 2;
            System.out.println("🕷️ " + nome + " se recuperou (+7 HP)!");
        } else {
            System.out.println("⚡ Energia insuficiente para curar!");
        }
    }

    @Override
    public void habilidadeEspecial(Personagem inimigo) {
        if (energia >= 5) {
            int dano = calcularDano(inimigo) + 12;
            inimigo.vida -= dano;
            energia -= 5;
            System.out.println("🕸️ " + nome + " usou combo especial causando " + dano + " de dano!");
        } else {
            System.out.println("⚡ Energia insuficiente! Atacando normalmente...");
            atacar(inimigo);
        }
    }
}
