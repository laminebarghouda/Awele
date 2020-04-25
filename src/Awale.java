import java.util.Random;
import java.util.Scanner;

/**
 * Classe principale qui permet de jouer au jeu Awalé
 * 
 * @author 21101243 - Davy Cavalli
 *
 */
public class Awale
{
	public static void main(String[] args)
	{

		Scanner sc = new Scanner(System.in);
		String nomJoueur;

		System.out.println("Entrez le nom du joueur");
		nomJoueur = sc.nextLine();
		Joueur j1 = new Joueur("IA","J1");
		Joueur j2 = new Joueur(nomJoueur, "J2");


		System.out.println("\nInitialisation du plateau ...");
		Plateau jeu = new Plateau();
		System.out.println();
		jeu.MonString();

		int NbTotalGraines = jeu.NbGraines();
		System.out.println("\nValeur du plateau : " + NbTotalGraines + "\n");

		System.out.println(j1.toString());
		System.out.println(j2.toString());


		Random random = new Random();
		// Boucle qui permet de jouer au tour par tour
		while (NbTotalGraines > 6 && j1.getScore() < 25 && j2.getScore() < 25)
		{
			// ////////////////////////////////////////
			// ------------ Tour IA --------------- //
			// ////////////////////////////////////////
			System.out.println("\nIA : choisissez la cellule à répartir !\n");
			int cellule = random.nextInt(6);
			System.out.println("IA a choisi " + cellule);
			while (cellule > 5 || cellule < 0)
			{
				System.out.println("IA : Choisissez un nombre entre 0 et 5 ! \n");
				cellule = random.nextInt(6);
				System.out.println("IA a choisi " + cellule);
			}

			int value = jeu.jouerCoup(cellule, "J1");

			while (value < 0)
			{
				if (value == -1)
					System.out.println("AI : choisissez une case non vide !\n");
				if (value == -2)
					System.out.println("AI : vous devez nourrir le plateau adverse !");
				if(value == -3){
					j1.addScore(jeu.NbGraines(0));
					j2.addScore(jeu.NbGraines(1));
					NbTotalGraines = 0;
					break;
				}
				cellule = Integer.parseInt(sc.nextLine());
				while (cellule > 5 || cellule < 0)
				{
					System.out.println("AI : Choisissez un nombre entre 0 et 5 ! \n");
					cellule = random.nextInt(6);
				}
				value = jeu.jouerCoup(cellule, "J1");
			}

			j1.addScore(value);
			// ////////////////////////////////////////

			// ------------ Affiche plateau --------------- //
			jeu.MonString();

			// ------------ Vérifie nombre de graines sur le plateau
			// --------------- //
			NbTotalGraines = jeu.NbGraines();
			System.out.println("\nValeur du plateau : " + NbTotalGraines + "\n");

			// ------------ Affiche joueurs --------------- //
			System.out.println(j1.toString());
			System.out.println(j2.toString());
			
			// ------------ Affiche coups possibles ------- //
			System.out.println(jeu.coupPossiblesToString(jeu.coupPossibles()));

			// Si il reste plus assez de graines ou que le j1 vient de faire un
			// coup gagnant on sort de la boucle
			if (NbTotalGraines <= 6 || j1.getScore() >= 25)
				break;
			
			
			// ////////////////////////////////////////
			// ------------ Tour J2 --------------- //
			// ////////////////////////////////////////
			System.out.println("Joueur: choisissez la cellule à répartir !\n");
			cellule = Integer.parseInt(sc.nextLine());
			while (cellule > 5 || cellule < 0)
			{
				System.out.println("Joueur: Choisissez un nombre entre 0 et 5 !\n");
				cellule = Integer.parseInt(sc.nextLine());
			}
			value = jeu.jouerCoup(cellule, "J2");

			while (value < 0)
			{
				if (value == -1)
					System.out.println("Joueur: choisissez une case non vide !\n");
				if (value == -2)
					System.out
							.println("Joueur : vous devez nourrir le plateau adverse !");
				if(value == -3){
					j1.addScore(jeu.NbGraines(0));
					j2.addScore(jeu.NbGraines(1));
					NbTotalGraines = 0;
					break;
				}
				cellule = Integer.parseInt(sc.nextLine());
				while (cellule > 5 || cellule < 0)
				{
					System.out.println("Joueur: Choisissez un nombre entre 0 et 5 !\n");
					cellule = Integer.parseInt(sc.nextLine());
				}
				value = jeu.jouerCoup(cellule, "J2");
			}

			j2.addScore(value);
			// ////////////////////////////////////////

			// ------------ Affiche plateau --------------- //
			jeu.MonString();

			// ------------ Vérifie nombre de graines sur le plateau
			// --------------- //
			NbTotalGraines = jeu.NbGraines();
			System.out
					.println("\nValeur du plateau : " + NbTotalGraines + "\n");

			// ------------ Affiche joueurs --------------- //
			System.out.println(j1.toString());
			System.out.println(j2.toString());
		}

		if (j1.getScore() > j2.getScore())
			System.out.println("IA l'emporte");
		else if (j1.getScore() < j2.getScore())
			System.out.println("Joueur l'emporte");
		else
			System.out.println("Egalité");
	}
}
