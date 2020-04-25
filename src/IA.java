/**
 * Classe représentant une IA et son alogrithme minimax. L'IA sera définie avec
 * une profondeur de recherche.
 * 
 * Son heuristique va être smple: essayer de faire le plus de différence entre
 * ses gains et les gains adverses. Donc même si un coup donne 5 points à
 * l'adversaire, mais qu'on en gagne 8 après, on jouera ce coup, sauf si ça fait
 * gagner l'adversaire. Stratégie offensive plutôt que défensive qui
 * consisterait à minimiser les gains adversaires au maximum mais qui nous
 * empêcherai d'en gagner car on prendrait moins de risques.
 * 
 * Elle va donc analyser chacune des branches possibles et comparer à chaque
 * fois les gains obtenus par chacun des joueurs. Elle jouera ensuite sur la
 * branche la plus rentable.
 * 
 * @author 21101243 - Davy Cavalli
 *
 */
public class IA
{
	String side;
	int score;
	String name;
	int profondeur;

	public IA(String side, int profondeur)
	{
		this.side = side;
		this.name = "IA-" + side;
		this.score = 0;
		this.profondeur = profondeur;
	}

	public void setScore(int score)
	{
		this.score = score;
	}

	public int getScore()
	{
		return this.score;
	}

	public void addScore(int value)
	{
		setScore(getScore() + value);
	}

	/**
	 * Représente le tour de jeu de l'IA
	 * 
	 * @param plateau
	 *            plateau de jeu actuel
	 */
	public void jouer(Plateau plateau)
	{

		// liste des coups à jouer : coup[0] = 1, coup[1] = 2 signifiera que
		// l'on devra jouer la cellule 1 puis la 2
		int[] coup = new int[profondeur];

		for (int i = 0; i < profondeur; i++)
			coup[i] = 0;

		int value = 0;

		// On teste les 6 cases de l'IA et on sauvegarde le coup qui nous
		// rapporte le plus
		for (int j = 0; j < 6; j++)
		{
			// tableau temporaire qui est une copie du plateau actuel
			Plateau plateau2 = plateau;
			// Valeur du gain en jouant la case j
			int tmp = plateau2.jouerCoup(j, side);
			// Si la valeur du gain est supérieure à la meilleure actuelle
			// on la sauvegarde et on indique que le 1er coup sera de jouer j
			if (tmp > value)
			{
				value = tmp;
				coup[0] = j;
			}
		}

	}
    
    	@Override
	public String toString()
	{
		return "IA : " + " , score = " + score + " , side : "
				+ side + " ]";
	}

}
