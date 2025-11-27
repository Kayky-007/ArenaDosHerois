package personagens;

public abstract class Heroi extends Personagem {

    public Heroi(String nome, int vida, int forca, int defesa, int energia) {
        super(nome, vida, forca, defesa, energia);
    }

    // herois controlados pelo jogador — agir() pode ficar vazio ou conter IA se quiser
    @Override
    public void agir(Personagem inimigo) {
        // controlado pelo jogador na Arena
    }
}
