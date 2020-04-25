import java.util.Random;
import java.util.Scanner;

/**
 * Classe principale qui permet de jouer au jeu Awalé
 */
public class Awale {

    private static Scanner sc = new Scanner(System.in); // Pour la lecture à partir de l'entré standard

    // Afficher les informations des joueurs en cours
    private static void afficherJoueurs(Joueur j1, Joueur j2){
        System.out.println(j1.toString());
        System.out.println(j2.toString());
        System.out.println();
    }

    // Determiner le gagnat de la partie
    private static void resultatFinal(Joueur j1, Joueur j2){
        if (j1.getScore() > j2.getScore())
            System.out.println("IA l'emporte");
        else if (j1.getScore() < j2.getScore())
            System.out.println("Joueur l'emporte");
        else
            System.out.println("Egalité");
    }


    // Tour machine
    private static void tourIA(Joueur j1, Joueur j2,Plateau jeu){
        int NbTotalGraines = jeu.NbGraines();
        Random random = new Random();
        int cellule = random.nextInt(6); // Entre 0 et 5 (pointeur java commence par 0)
        while (cellule > 5 || cellule < 0) { // Vérifier que le nombre choisi est valide
            cellule = random.nextInt(6);
        }

        int value = jeu.jouerCoup(cellule, "J1");

        while (value < 0) {
            if (value == -3) {
                j1.addScore(jeu.NbGraines(0));
                j2.addScore(jeu.NbGraines(1));
                NbTotalGraines = 0;
                break;
            }
            cellule = random.nextInt(6);
            while (cellule > 5 || cellule < 0) {
                cellule = random.nextInt(6);
            }
            value = jeu.jouerCoup(cellule, "J1");
        }
        System.out.println("IA a choisi " + (cellule+1));
        j1.addScore(value);
    }

    // Tour joueur
    private static void tourJoueur(Joueur j1, Joueur j2,Plateau jeu){
        int NbTotalGraines = jeu.NbGraines();
        System.out.print("Joueur: choisissez la cellule à répartir !\t");
        int cellule = Integer.parseInt(sc.nextLine());
        while (cellule > 6 || cellule < 1) {
            System.out.print("Joueur: Choisissez un nombre entre 1 et 6 !\t");
            cellule = Integer.parseInt(sc.nextLine());
        }
        int value = jeu.jouerCoup(cellule-1, "J2");

        while (value < 0) {
            if (value == -1)
                System.out.print("Joueur: choisissez une case non vide !\t");
            if (value == -2)
                System.out.print("Joueur : vous devez nourrir le plateau adverse !\t");
            if (value == -3) {
                j1.addScore(jeu.NbGraines(0));
                j2.addScore(jeu.NbGraines(1));
                NbTotalGraines = 0;
                break;
            }
            cellule = Integer.parseInt(sc.nextLine());
            while (cellule > 6 || cellule < 1) {
                System.out.print("Joueur: Choisissez un nombre entre 1 et 6 !\t");
                cellule = Integer.parseInt(sc.nextLine());
            }
            value = jeu.jouerCoup(cellule-1, "J2");
        }
        j2.addScore(value);
    }

    // Afficher les informations du jeu en cours
    private static void informations(Joueur j1, Joueur j2, Plateau jeu){
        // Afficher plateau
        jeu.afficher();

        // Afficher nombre de graines sur le plateau
        int NbTotalGraines = jeu.NbGraines();
        System.out.println("Valeur du plateau : " + NbTotalGraines + "\n");

        // Afficher les joueurs
        afficherJoueurs(j1,j2);
    }


    public static void main(String[] args) {

        Joueur j1 = new Joueur("IA", "J1");
        System.out.print("Entrez le nom du joueur \t");
        Joueur j2 = new Joueur(sc.nextLine(), "J2");

        System.out.println("Initialisation du plateau ...\n");
        Plateau jeu = new Plateau();
        jeu.afficher();

        int NbTotalGraines = jeu.NbGraines();
        System.out.println("Valeur du plateau : " + NbTotalGraines + "\n");

        afficherJoueurs(j1,j2);

        // Boucle qui permet de jouer au tour par tour
        while (NbTotalGraines > 6 && j1.getScore() < 25 && j2.getScore() < 25) {

            // ------------ Tour IA --------------- //
            tourIA(j1,j2,jeu);
            System.out.println();
            informations(j1,j2,jeu);
            NbTotalGraines = jeu.NbGraines(); // Mettre à jour
            // S'il reste plus assez de graines ou que le j1 vient de faire un coup gagnant on sort de la boucle
            if (NbTotalGraines <= 6 || j1.getScore() >= 25)
                break;

            // ------------ Tour Joueur --------------- //
            tourJoueur(j1,j2,jeu);
            System.out.println();
            informations(j1,j2,jeu);
            NbTotalGraines = jeu.NbGraines(); // Mettre à jour
        }
        resultatFinal(j1,j2);
    }
}