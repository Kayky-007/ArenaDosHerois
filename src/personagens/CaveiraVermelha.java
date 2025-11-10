package personagens;

import java.util.Random;

public class CaveiraVermelha extends Vilao {
    private Random rand = new Random();
    public CaveiraVermelha() { super("Caveira Vermelha", 75, 18, 7); }

    @Override public void atacar(Personagem heroi) {
        int dano = calcularDano(heroi) + rand.nextInt(5);
        heroi.vida -= dano;
        System.out.println("☠️ " + nome + " atacou com tecnologia letal causando " + dano + " de dano!");
    }
    @Override public void defender() { defendendo = true; System.out.println("🧥 " + nome + " ativou defesa tecnológica!"); }
    @Override public void curar() { vida = Math.min(vidaMaxima, vida + 7); System.out.println("🧬 " + nome + " usou soro experimental (+7 HP)!"); }
    @Override public void habilidadeEspecial(Personagem inimigo) { System.out.println("💣 " + nome + " dispara arma especial!"); inimigo.efeitos.add(Status.SANGRAMENTO); }
    @Override
    public void agir(Personagem heroi) {
        aplicarEfeitos();
        if (vida < 25) { curar(); }
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
