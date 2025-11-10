package personagens;

import java.util.Random;

public class Coringa extends Vilao {
    private Random rand = new Random();
    public Coringa() { super("Coringa", 65, 15, 6); }

    @Override public void atacar(Personagem heroi) {
        int dano = calcularDano(heroi) + rand.nextInt(8);
        heroi.vida -= dano;
        System.out.println("💣 " + nome + " usou truque explosivo causando " + dano + " de dano!");
    }
    @Override public void defender() { defendendo = true; System.out.println("🎭 " + nome + " confundiu o inimigo!"); }
    @Override public void curar() { vida = Math.min(vidaMaxima, vida + 5); System.out.println("🃏 " + nome + " se recuperou (+5 HP)!"); }
    @Override public void habilidadeEspecial(Personagem inimigo) {
        System.out.println("🎲 " + nome + " causa CAOS: dobra seus danos e aplica ENVENENAMENTO!");
        efeitos.add(Status.BUFF_FORCA);
        inimigo.efeitos.add(Status.ENVENENAMENTO);
    }
    @Override
    public void agir(Personagem heroi) {
        aplicarEfeitos();
        int a = rand.nextInt(3);
        switch (a) {
            case 0: atacar(heroi); break;
            case 1: defender(); break;
            case 2: habilidadeEspecial(heroi); break;
            default: atacar(heroi); break;
        }
        energia = Math.max(0, energia - 2);
    }
}
