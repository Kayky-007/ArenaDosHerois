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
    protected boolean defendendo = false;

    // cooldowns simples (turnos do último uso)
    protected int turnoUltimaCura = -10;
    protected int turnoUltimaEspecial = -10;

    protected List<Status> efeitos = new ArrayList<>();
    protected Random rand = new Random();

    public Personagem(String nome, int vida, int forca, int defesa, int energia) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.forca = forca;
        this.defesa = defesa;
        this.energiaMaxima = energia;
        this.energia = energia;
    }

    // métodos obrigatórios
    public abstract void atacar(Personagem inimigo);
    public abstract void defender();
    public abstract void curar(); // usa energia
    public abstract void habilidadeEspecial(Personagem inimigo);
    public abstract void agir(Personagem inimigo); // IA para vilões, vazio para herói

    // ações utilitárias
    public void descansar() {
        int ganho = 3 + rand.nextInt(3); // 3-5
        energia = Math.min(energiaMaxima, energia + ganho);
        defendendo = false;
        System.out.println("💤 " + nome + " descansou e recuperou " + ganho + " de energia!");
    }

    public void aplicarEfeitos() {
        List<Status> expirados = new ArrayList<>();
        for (Status s : efeitos) {
            switch (s) {
                case SANGRAMENTO:
                    vida -= 3;
                    System.out.println("🩸 " + nome + " está sangrando (-3 HP).");
                    break;
                case ENVENENAMENTO:
                    vida -= 2;
                    System.out.println("☠️ " + nome + " sofre veneno (-2 HP).");
                    break;
                case BUFF_FORCA:
                    // aplicado no calcularDano
                    break;
                case DEBUFF_DEFESA:
                    // aplicado no calcularDano
                    break;
                case STUN:
                    // STUN é checado na arena/IA — aqui apenas informativo
                    System.out.println("😵 " + nome + " está atordoado e pode perder a vez.");
                    break;
            }
        }
        efeitos.removeAll(expirados);
    }

    protected int calcularDano(Personagem inimigo) {
        // chance de esquiva
        boolean esquiva = rand.nextInt(100) < 10; // 10% chance
        if (esquiva) {
            System.out.println("💨 " + inimigo.nome + " esquivou do ataque!");
            return 0;
        }

        boolean critico = rand.nextInt(100) < 12; // 12% crítico
        int danoBase = Math.max(1, forca - (inimigo.defesa / 2) + rand.nextInt(6)); // variação leve

        if (efeitos.contains(Status.BUFF_FORCA)) {
            danoBase = (int) (danoBase * 1.2);
        }
        if (inimigo.efeitos.contains(Status.DEBUFF_DEFESA)) {
            danoBase = (int) (danoBase * 1.2);
        }

        if (inimigo.defendendo) {
            danoBase /= 2;
            System.out.println("🛡️ " + inimigo.nome + " defendeu parte do dano!");
            inimigo.defendendo = false;
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
        System.out.println("========================================");
        System.out.printf("%s%n", nome);
        System.out.printf("Vida:    %d / %d  [%s]%n", Math.max(0, vida), vidaMaxima, gerarBarra(vida, vidaMaxima));
        System.out.printf("Energia: %d / %d  [%s]%n", Math.max(0, energia), energiaMaxima, gerarBarra(energia, energiaMaxima));
        System.out.println("Defesa:  " + (defendendo ? "Ativa 🛡️" : "Inativa"));
        if (!efeitos.isEmpty()) {
            System.out.print("Efeitos: ");
            for (Status s : efeitos) System.out.print(s + " ");
            System.out.println();
        }
        System.out.println("========================================");
    }

    private String gerarBarra(int atual, int max) {
        int total = 12;
        int preenchido = 0;
        if (max > 0) preenchido = (int) Math.round((atual / (double) max) * total);
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < preenchido; i++) b.append("█");
        for (int i = preenchido; i < total; i++) b.append("-");
        return b.toString();
    }

    // Eventos globais da arena
    public static void eventoAleatorio(Personagem p1, Personagem p2) {
        Random r = new Random();
        int chance = r.nextInt(100); // 0-99

        if (chance < 8) { // 8%
            System.out.println("\n⚡ Um raio atinge a arena! Ambos perdem 5 HP!");
            p1.vida -= 5; p2.vida -= 5;
        } else if (chance < 18) { // +10%
            System.out.println("\n🔥 O chão pega fogo! Ambos perdem 3 HP!");
            p1.vida -= 3; p2.vida -= 3;
        } else if (chance < 30) { // +12%
            System.out.println("\n🌪️ Poeira cega os lutadores: chance de esquiva reduzida por 1 turno (simulado).");
            // efeito de precisão pode ser adicionado; aqui é só informativo
        } else if (chance < 45) { // +15%
            System.out.println("\n💎 Uma aura beneficia " + p1.nome + ": +6 energia!");
            p1.energia = Math.min(p1.energiaMaxima, p1.energia + 6);
        } else if (chance < 60) { // +15%
            System.out.println("\n💀 Uma maldição ataca " + p2.nome + ": -4 força temporária (DEBUFF_DEFESA).");
            p2.forca = Math.max(1, p2.forca - 4);
            p2.efeitos.add(Status.DEBUFF_DEFESA);
        } else if (chance < 75) { // +15%
            System.out.println("\n🌈 Luz curativa: ambos curam 4 HP!");
            p1.vida = Math.min(p1.vidaMaxima, p1.vida + 4);
            p2.vida = Math.min(p2.vidaMaxima, p2.vida + 4);
        } else if (chance < 90) { // +15%
            System.out.println("\n🌀 Um vento estranho passa... nada relevante aconteceu.");
        } else { // 10% raro
            System.out.println("\n✨ Sorte divina! " + p1.nome + " recebe um pequeno buff de força temporário!");
            p1.efeitos.add(Status.BUFF_FORCA);
        }
    }

    // getters básicos
    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public int getEnergia() { return energia; }
}
