package personagens;

import java.util.*;

public abstract class Personagem {
    protected String nome;
    protected int vida;
    protected int vidaMaxima;
    protected int forca;
    protected int defesa;
    protected int energia;
    protected int energiaMaxima;
    protected boolean defendendo;

    protected int turnoUltimaCura = -10;
    protected int turnoUltimaHabilidade = -10;

    protected List<Status> efeitos = new ArrayList<>();
    protected Random rand = new Random();

    public Personagem(String nome, int vida, int forca, int defesa) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.forca = forca;
        this.defesa = defesa;
        this.energiaMaxima = 10;
        this.energia = energiaMaxima;
        this.defendendo = false;
    }

    // métodos que subclasses **devem** implementar
    public abstract void atacar(Personagem inimigo);
    public abstract void defender();
    public abstract void curar();
    public abstract void habilidadeEspecial(Personagem inimigo);
    public abstract void agir(Personagem inimigo); // IA / comportamento do vilão

    // descansar - recupera energia
    public void descansar() {
        int ganho = 3 + rand.nextInt(3); // 3-5
        energia = Math.min(energiaMaxima, energia + ganho);
        defendendo = false;
        System.out.println("💤 " + nome + " descansou e recuperou " + ganho + " de energia!");
    }

    // aplica efeitos contínuos como sangramento / veneno
    public void aplicarEfeitos() {
        List<Status> removidos = new ArrayList<>();
        for (Status s : efeitos) {
            switch (s) {
                case SANGRAMENTO:
                    vida -= 3;
                    System.out.println("🩸 " + nome + " sangra (-3 HP)!");
                    break;
                case ENVENENAMENTO:
                    vida -= 2;
                    System.out.println("☠️ " + nome + " sofre veneno (-2 HP)!");
                    break;
                case BUFF_FORCA:
                    // efeito aplicado durante cálculo de dano
                    break;
                case DEBUFF_DEFESA:
                    // efeito aplicado durante cálculo de dano
                    break;
            }
        }
        efeitos.removeAll(removidos);
    }

    // calcular dano padrão (critico, esquiva, defesa, buffs)
    protected int calcularDano(Personagem inimigo) {
        boolean esquiva = rand.nextInt(100) < 10; // 10% esquiva
        if (esquiva) {
            System.out.println("💨 " + inimigo.nome + " esquivou!");
            return 0;
        }

        boolean critico = rand.nextInt(100) < 15; // 15% crítico
        int danoBase = Math.max(1, forca - (inimigo.defesa / 2) + rand.nextInt(6));
        if (efeitos.contains(Status.BUFF_FORCA)) {
            danoBase = (int) (danoBase * 1.2);
        }
        if (inimigo.efeitos.contains(Status.DEBUFF_DEFESA)) {
            danoBase = (int) (danoBase * 1.2);
        }

        if (inimigo.defendendo) {
            danoBase /= 2;
            System.out.println("🛡️ " + inimigo.nome + " defendeu parte do dano!");
            inimigo.defendendo = false; // defesa consumida
        }

        if (critico) {
            System.out.println("💥 ATAQUE CRÍTICO de " + nome + "!");
            danoBase *= 2;
        }

        return danoBase;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void exibirStatus() {
        System.out.printf("%s ❤️ %d/%d  ⚡ %d/%d%n", nome, vida, vidaMaxima, energia, energiaMaxima);
        if (!efeitos.isEmpty()) {
            System.out.print("   Efeitos: ");
            for (Status s : efeitos) System.out.print(s + " ");
            System.out.println();
        }
    }

    // eventos globais da arena (chamado por ArenaDosHerois)
    public static void eventoAleatorio(Personagem p1, Personagem p2) {
        Random r = new Random();
        int chance = r.nextInt(100);

        if (chance < 10) {
            System.out.println("⚡ Um raio atinge a arena! Ambos perdem 5 HP!");
            p1.vida -= 5; p2.vida -= 5;
        } else if (chance < 20) {
            System.out.println("🔥 O chão prende fogo! Ambos perdem 3 HP este turno!");
            p1.vida -= 3; p2.vida -= 3;
        } else if (chance < 30) {
            System.out.println("🌪️ Poeira cega os lutadores: precisão diminuída (efeito simulado).");
            // você pode adicionar lógica real de precisão se quiser
        } else if (chance < 45) {
            System.out.println("💎 Uma aura beneficia " + p1.nome + ": +6 energia!");
            p1.energia = Math.min(p1.energiaMaxima, p1.energia + 6);
        } else if (chance < 60) {
            System.out.println("💀 Uma maldição ataca " + p2.nome + ": -4 força temporária!");
            p2.forca = Math.max(1, p2.forca - 4);
            p2.efeitos.add(Status.DEBUFF_DEFESA);
        } else if (chance < 75) {
            System.out.println("🌈 Luz curativa: ambos curam 4 HP!");
            p1.vida = Math.min(p1.vidaMaxima, p1.vida + 4);
            p2.vida = Math.min(p2.vidaMaxima, p2.vida + 4);
        } else {
            System.out.println("🌀 Um vento estranho passa... nada relevante aconteceu.");
        }
    }

    // getters úteis
    public String getNome() { return nome; }
    public int getVida() { return vida; }
}
