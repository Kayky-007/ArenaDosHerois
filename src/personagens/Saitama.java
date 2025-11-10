package personagens;

import java.util.Random;

public class Saitama extends Heroi {
    private Random rand = new Random();

    public Saitama() {
        super("Saitama", 100, 22, 8); // vida média-alta, força forte, defesa boa
    }

    @Override
    public void atacar(Personagem inimigo) {
        int dano = calcularDano(inimigo) + rand.nextInt(6); // 0 a 5 extra
        inimigo.vida -= dano;
        System.out.println("👊 " + nome + " desferiu um soco causando " + dano + " de dano!");
    }

    @Override
    public void defender() {
        defendendo = true;
        System.out.println("😐 " + nome + " se prepara para reduzir o próximo dano.");
    }

    @Override
    public void curar() {
        int cura = 8 + rand.nextInt(6); // 8-13 HP
        vida = Math.min(vidaMaxima, vida + cura);
        System.out.println("🧘 " + nome + " se recuperou e ganhou " + cura + " de vida!");
    }

    @Override
    public void habilidadeEspecial(Personagem inimigo) {
        if (energia >= 5) {
            int dano = 30 + rand.nextInt(11); // 30-40 de dano
            inimigo.vida -= dano;
            energia -= 5;
            System.out.println("💥 " + nome + " usou ataque especial causando " + dano + " de dano!");
        } else {
            System.out.println("⚡ Energia insuficiente! Atacando normalmente...");
            atacar(inimigo);
        }
    }

    @Override
    public void agir(Personagem inimigo) {
        // herói controlado pelo jogador
    }
}
