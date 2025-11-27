package personagens;

import java.util.Random;

public abstract class Vilao extends Personagem {
    protected Random rand = new Random();

    public Vilao(String nome, int vida, int forca, int defesa, int energia) {
        super(nome, vida, forca, defesa, energia);
    }

    // IA básica - subclasses podem sobrescrever
    @Override
    public void agir(Personagem heroi) {
        aplicarEfeitos();
        if (!estaVivo()) return;

        // se atordoado, 30% de perder a vez (simples)
        if (efeitos.contains(Status.STUN) && rand.nextInt(100) < 50) {
            System.out.println("😵 " + nome + " está atordoado e perde a vez!");
            efeitos.remove(Status.STUN);
            return;
        }

        if (vida < (vidaMaxima * 30 / 100) && energia >= 2) {
            curar();
        } else {
            int a = rand.nextInt(100);
            if (a < 60) atacar(heroi);
            else if (a < 80) defender();
            else habilidadeEspecial(heroi);
        }

        energia = Math.max(0, energia - 2);
    }
}
