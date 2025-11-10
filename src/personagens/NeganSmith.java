package personagens;

import java.util.Random;

public class NeganSmith extends Vilao {
    private Random rand = new Random();
    public NeganSmith() { super("Negan Smith", 80, 17, 7); }

    @Override public void atacar(Personagem heroi) {
        int dano = calcularDano(heroi) + rand.nextInt(6);
        if (heroi.defendendo) {
            dano -= heroi.defesa;
            if (dano < 0) dano = 0;
            heroi.defendendo = false;
        }
        heroi.vida -= dano;
        System.out.println("🏏 " + nome + " atacou com Lucille causando " + dano + " de dano!");
    }
    @Override public void defender() { defendendo = true; System.out.println("🧥 " + nome + " se escondeu atrás de capangas!"); }
    @Override public void curar() { vida = Math.min(vidaMaxima, vida + 6); System.out.println("🩸 " + nome + " descansou (+6 HP)!"); }
    @Override public void habilidadeEspecial(Personagem inimigo) {
        System.out.println("🏏 Lucille Sedenta: aplica SANGRAMENTO!");
        inimigo.efeitos.add(Status.SANGRAMENTO);
    }
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
                default: atacar(heroi); break;
            }
        }
        energia = Math.max(0, energia - 2);
    }
}
