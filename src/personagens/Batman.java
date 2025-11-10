package personagens;

public class Batman extends Heroi {
    public Batman() { super("Batman", 75, 16, 9); }

    @Override
    public void atacar(Personagem inimigo) {
        int dano = calcularDano(inimigo);
        inimigo.vida -= dano;
        System.out.println("🦇 " + nome + " atacou com gadgets causando " + dano + " de dano!");
    }

    @Override public void defender() { defendendo = true; System.out.println("🛡️ " + nome + " ativou gadget defensivo!"); }
    @Override public void curar() { vida = Math.min(vidaMaxima, vida + 8); System.out.println("🩹 " + nome + " aplicou primeiros socorros (+8 HP)!"); }

    @Override
    public void habilidadeEspecial(Personagem inimigo) {
        System.out.println("🧠 Plano de Contingência: " + nome + " anula o próximo ataque!");
        // Representamos anulando: aplica buff que reduz a chance de crítico inimigo (simples)
        inimigo.efeitos.add(Status.DEBUFF_DEFESA);
    }

    @Override public void agir(Personagem inimigo) { /* IA opcional */ }
}
