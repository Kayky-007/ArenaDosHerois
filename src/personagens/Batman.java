package personagens;

import java.util.Random;

public class Batman extends Heroi {
    private Random rand = new Random();

    public Batman() {
        super("Batman", 75, 22, 10, 10);
    }

    @Override
    public void atacar(Personagem inimigo) {
        int dano = calcularDano(inimigo) + rand.nextInt(5);
        inimigo.vida -= dano;
        System.out.println("🦇 " + nome + " atacou com gadgets causando " + dano + " de dano!");
    }

    @Override
    public void defender() {
        defendendo = true;
        System.out.println("🛡️ " + nome + " ativou gadget defensivo!");
    }

    @Override
    public void curar() {
        if (energia >= 2) {
            int cura = 8 + rand.nextInt(4);
            vida = Math.min(vidaMaxima, vida + cura);
            energia -= 2;
            System.out.println("🩹 " + nome + " aplicou primeiros socorros e recuperou " + cura + " de vida!");
        } else {
            System.out.println("⚡ Energia insuficiente para curar!");
        }
    }

    @Override
    public void habilidadeEspecial(Personagem inimigo) {
        if (energia >= 5) {
            System.out.println("🧠 " + nome + " usa Plano de Contingência (aplica DEBUFF_DEFESA no inimigo).");
            inimigo.efeitos.add(Status.DEBUFF_DEFESA);
            energia -= 5;
        } else {
            System.out.println("⚡ Energia insuficiente! Atacando normalmente...");
            atacar(inimigo);
        }
    }
}
