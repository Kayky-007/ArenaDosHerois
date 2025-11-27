package personagens;

import java.util.Random;

public class CaveiraVermelha extends Vilao {
    private Random rand = new Random();

    public CaveiraVermelha() {
        super("Caveira Vermelha", 75, 18, 7, 10);
    }

    @Override
    public void atacar(Personagem heroi) {
        int dano = calcularDano(heroi) + rand.nextInt(6);
        heroi.vida -= dano;
        System.out.println("☠️ " + nome + " atacou com tecnologia letal causando " + dano + " de dano!");
    }

    @Override
    public void defender() {
        defendendo = true;
        System.out.println("🧥 " + nome + " se protege com tecnologia Hydra!");
    }

    @Override
    public void curar() {
        int cura = 7;
        vida = Math.min(vidaMaxima, vida + cura);
        System.out.println("🧬 " + nome + " usou soro experimental (+7 HP)!");
    }

    @Override
    public void habilidadeEspecial(Personagem inimigo) {
        System.out.println("💣 " + nome + " dispara munição letal e causa sangramento!");
        inimigo.efeitos.add(Status.SANGRAMENTO);
    }
}
