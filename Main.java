import java.util.Random;

public class Main {
    public static int bossHealth = 800;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static int[] heroesHealth = {290, 270, 250, 300};
    public static int[] heroesDamage = {25, 15, 10, 0};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Healer"};
    public static int roundNumber = 0;

    public static void main(String[] args) {
        showStatistics();
        while (!isGameOver()) {
            round();
        }
    }

    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void round() {
        roundNumber++;
        chooseBossDefence();
        bossAttacks();
        heroesAttack();
        medicHeal();
        showStatistics();
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefence = heroesAttackType[randomIndex];
    }

    public static void heroesAttack() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0 || heroesHealth[0]>0 || heroesHealth[1]>0 || heroesHealth[2]>0 || heroesHealth[3]>0) {
                int damage = heroesDamage[i];
                if (bossDefence == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2;
                    damage = damage * coeff;
                    System.out.println("Critical damage: " + heroesAttackType[i] + " " + damage);
                }
                if (bossHealth - damage < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - damage;
                }
            }
        }
    }

    public static void bossAttacks() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void medicHeal() {
        for (int i = 0; i < heroesHealth.length; i++) {
            Random random = new Random();
            int randomIndex = random.nextInt(heroesHealth.length-1);
            if (heroesHealth[i] < 100 && heroesHealth[i] > 0 && heroesHealth[i] != heroesHealth[3]) {
                int healAmount = 30;
                heroesHealth[randomIndex] += healAmount;
                System.out.println("Medic healed " + heroesAttackType[randomIndex] + " for " + healAmount + " health points.");
                break; 
            }
        }
    }

    public static void showStatistics() {
        System.out.println("ROUND " + roundNumber + " ----------------");
        System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage
                + " defence: " + (bossDefence == null ? "None" : bossDefence));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] +
                    " health: " + heroesHealth[i] + " damage: " + heroesDamage[i]);
        }
    }
}
