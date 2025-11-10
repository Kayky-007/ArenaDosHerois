package personagens;

public class RickGrimes extends Heroi {
    public RickGrimes() { super("Rick Grimes", 70, 15, 7); }

    @Override
    public void atacar(Personagem inimigo) {
        int dano = calcularDano(inimigo);
        inimigo.vida -= dano;
        System.out.println("🔫 " + nome + " atirou e causou " + dano + " de dano!");
    }

    @Override public void defender() { defendendo = true; System.out.println("🧱 " + nome + " se protegeu atrás de barricada!"); }
    @Override public void curar() { vida = Math.min(vidaMaxima, vida + 6); System.out.println("💊 " + nome + " usou kit médico (+6 HP)!"); }

    @Override
    public void habilidadeEspecial(Personagem inimigo) {
        System.out.println("🛡️ Liderança: " + nome + " recupera energia e força por um turno!");
        energia = Math.min(energiaMaxima, energia + 6);
        efeitos.add(Status.BUFF_FORCA);
    }

    @Override public void agir(Personagem inimigo) { /* IA opcional */ }
}
