package personagens;

import java.util.Random;

public class Coringa extends Vilao {
    private Random rand = new Random();

    public Coringa() {
        super("Coringa", 65, 15, 6, 10);
    }

    @Override
    public void atacar(Personagem heroi) {
        int dano = calcularDano(heroi) + rand.nextInt(8);
        heroi.vida -= dano;
        System.out.println("💣 " + nome + " usou truque explosivo causando " + dano + " de dano!");
    }

    @Override
    public void defender() {
        defendendo = true;
        System.out.println("🎭 " + nome + " riu e confundiu o inimigo!");
    }

    @Override
    public void curar() {
        int cura = 5;
        vida = Math.min(vidaMaxima, vida + cura);
        System.out.println("🃏 " + nome + " se recuperou (+5 HP)!");
    }

    @Override
    public void habilidadeEspecial(Personagem inimigo) {
        System.out.println("🎲 " + nome + " causa CAOS: aplica ENVENENAMENTO e BUFF_FORCA em si.");
        efeitos.add(Status.BUFF_FORCA);
        inimigo.efeitos.add(Status.ENVENENAMENTO);
    }
}
