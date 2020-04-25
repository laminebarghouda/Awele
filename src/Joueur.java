/**
 * Classe représentant un joueur
 */
public class Joueur
{

	private String nom;
	private int score;
	private String side;

	public Joueur(String nom, String side) {
		this.nom = nom;
		this.score = 0;
		this.side = side;
	}

	public String getNom()
	{
		return this.nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public int getScore()
	{
		return this.score;
	}

	public void setScore(int score)
	{
		this.score = score;
	}

	/**
	 * Ajoute une valeur au score actuel du joueur
	 * 
	 * @param score
	 *            valeur à ajouter au joueur
	 */
	public void addScore(int score)
	{
		setScore(this.score + score);
	}

	/**
	 * Affichage du joueur (nom, score et son side)
	 */
	@Override
	public String toString() {
		return "Joueur [nom : " + nom + " , score = " + score + " ]";
	}
}
