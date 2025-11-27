package personagens;

import java.util.Random;

public class RickGrimes extends Heroi {
    private Random rand = new Random();

    public RickGrimes() {
        super("Rick Grimes", 70, 15, 7, 10);
    }

    @Override
    public void atacar(Personagem inimigo) {
        int dano = calcularDano(inimigo) + rand.nextInt(6);
        inimigo.vida -= dano;
        System.out.println("🔫 " + nome + " atirou e causou " + dano + " de dano!");
    }

    @Override
    public void defender() {
        defendendo = true;
        System.out.println("🧱 " + nome + " se protegeu atrás de barricada!");
    }

    @Override
    public void curar() {
        if (energia >= 2) {
            int cura = 6;
            vida = Math.min(vidaMaxima, vida + cura);
            energia -= 2;
            System.out.println("💊 " + nome + " usou kit médico (+6 HP)!");
        } else {
            System.out.println("⚡ Energia insuficiente para curar!");
        }
    }

    @Override
    public void habilidadeEspecial(Personagem inimigo) {
        if (energia >= 5) {
            System.out.println("🛡️ " + nome + " Liderança: recupera energia e ganha BUFF_FORCA!");
            energia = Math.min(energiaMaxima, energia + 6);
            efeitos.add(Status.BUFF_FORCA);
            energia -= 5;
        } else {
            System.out.println("⚡ Energia insuficiente! Atacando normalmente...");
            atacar(inimigo);
        }
    }
}
