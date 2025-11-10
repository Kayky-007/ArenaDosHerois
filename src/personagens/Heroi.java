package personagens;

public abstract class Heroi extends Personagem {
    public Heroi(String nome, int vida, int forca, int defesa) {
        super(nome, vida, forca, defesa);
    }
    // heróis podem usar a implementação padrão de descansar, exibirStatus, aplicarEfeitos
}
