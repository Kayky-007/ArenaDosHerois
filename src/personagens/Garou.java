package personagens;

import java.util.Random;

public class Garou extends Vilao {
    private Random rand = new Random();

    public Garou() {
        super("Garou", 90, 0, 8, 10);
    }

    @Override
    public void atacar(Personagem heroi) {
        int dano = calcularDano(heroi) + rand.nextInt(6);
        heroi.vida -= dano;
        System.out.println("💥 " + nome + " aplicou artes marciais causando " + dano + " de dano!");
    }

    @Override
    public void defender() {
        defendendo = true;
        System.out.println("🌀 " + nome + " desviou com reflexos sobre-humanos!");
    }

    @Override
    public void curar() {
        int cura = 10;
        vida = Math.min(vidaMaxima, vida + cura);
        System.out.println("🔥 " + nome + " recuperou " + cura + " de vida!");
    }

    @Override
    public void habilidadeEspecial(Personagem inimigo) {
        System.out.println("🔁 " + nome + " adapta-se e ganha BUFF_FORCA!");
        efeitos.add(Status.BUFF_FORCA);
    }
}
