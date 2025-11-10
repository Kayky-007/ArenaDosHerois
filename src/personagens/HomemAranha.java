package personagens;

public class HomemAranha extends Heroi {
    public HomemAranha() { super("Homem-Aranha", 80, 17, 8); }

    @Override public void atacar(Personagem inimigo) {
        int dano = calcularDano(inimigo);
        inimigo.vida -= dano;
        System.out.println("🕸️ " + nome + " lançou teias e causou " + dano + " de dano!");
    }
    @Override public void defender() { defendendo = true; System.out.println("🤸 " + nome + " desviou agilmente!"); }
    @Override public void curar() { vida = Math.min(vidaMaxima, vida + 7); System.out.println("🕷️ " + nome + " se recuperou (+7 HP)!"); }
    @Override public void habilidadeEspecial(Personagem inimigo) {
        System.out.println("🕸️ Ataque de combo do " + nome + "!");
        int dano = calcularDano(inimigo) + 8;
        inimigo.vida -= dano;
    }
    @Override public void agir(Personagem inimigo) { /* IA opcional */ }
}
