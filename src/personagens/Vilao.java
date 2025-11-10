package personagens;

public abstract class Vilao extends Personagem {
    public Vilao(String nome, int vida, int forca, int defesa) {
        super(nome, vida, forca, defesa);
    }
    // cada vilão implementa agir(Personagem) com IA própria
}
