package personagens;

import java.util.Random;

public class Garou extends Vilao {
    private Random rand = new Random();
    public Garou() { super("Garou", 90, 22, 8); }

    @Override public void atacar(Personagem heroi) {
        int dano = calcularDano(heroi) + rand.nextInt(6);
        heroi.vida -= dano;
        System.out.println("💥 " + nome + " aplicou artes marciais causando " + dano + " de dano!");
    }
    @Override public void defender() { defendendo = true; System.out.println("🌀 " + nome + " desviou com reflexos!"); }
    @Override public void curar() { vida = Math.min(vidaMaxima, vida + 10); System.out.println("🔥 " + nome + " recuperou 10 de vida!"); }
    @Override public void habilidadeEspecial(Personagem inimigo) { System.out.println("🔁 " + nome + " adapta-se e ganha BUFF_FORCA!"); efeitos.add(Status.BUFF_FORCA); }
    @Override
    public void agir(Personagem heroi) {
        aplicarEfeitos();
        if (vida < 25 && energia > 1) { curar(); }
        else {
            int a = rand.nextInt(3);
            switch (a) {
                case 0: atacar(heroi); break;
                case 1: defender(); break;
                case 2: habilidadeEspecial(heroi); break;
                default: defender(); break;
            }
        }
        energia = Math.max(0, energia - 2);
    }
}
