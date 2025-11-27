package personagens;

import java.util.Random;

public class NeganSmith extends Vilao {
    private Random rand = new Random();

    public NeganSmith() {
        super("Negan Smith", 80, 17, 7, 10);
    }

    @Override
    public void atacar(Personagem heroi) {
        int dano = calcularDano(heroi) + rand.nextInt(6);
        if (heroi.defendendo) {
            dano -= heroi.defesa;
            if (dano < 0) dano = 0;
            heroi.defendendo = false;
        }
        heroi.vida -= dano;
        System.out.println("🏏 " + nome + " atacou com Lucille causando " + dano + " de dano!");
    }

    @Override
    public void defender() {
        defendendo = true;
        System.out.println("🧥 " + nome + " se escondeu atrás dos capangas!");
    }

    @Override
    public void curar() {
        int cura = 6;
        vida = Math.min(vidaMaxima, vida + cura);
        System.out.println("🩸 " + nome + " descansou e recuperou " + cura + " de vida!");
    }

    @Override
    public void habilidadeEspecial(Personagem inimigo) {
        System.out.println("🏏 Lucille Sedenta: aplica SANGRAMENTO!");
        inimigo.efeitos.add(Status.SANGRAMENTO);
    }
}
