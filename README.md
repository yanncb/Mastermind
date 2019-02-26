# Bienvenue Sur  Mastermind / Recherche Plus ou Moins !!


## Présentation du projet Mastermind.

Plus d'informations [sur les consignes ici](https://openclassrooms.com/fr/projects/140/assignment)

Le jeu se decline en deux jeux :
	
	- la recherche d'une combinaison à l'aide d'indications +/-,
	
	- le célèbre Mastermind. 
	

Chaque jeu pourra être joué selon 3 modes :

	- Mode challenger : le joueur doit trouver la combinaison secrète de l'ordinateur,
	
	- Mode défenseur : l'ordinateur doit trouver la combinaison secrète du joueur,
	
	- Mode duel : l'ordinateur et le joueur doivent jouer tour à tour, le premier à trouver la combinaison secrète de l'autre a gagné.


## Réalisation

1. Environnement technique

  - IDE :  [IntelliJ 2018.3](https://www.jetbrains.com/idea/).
 
  - Git
  
  - Langage de programmation : [Java 8/JDK 1.8.0_181](https://openclassrooms.com/fr/courses/26832-apprenez-a-programmer-en-java)
  


2. Livrable

	1. `mastermind.jar`

		À la fin de la programmation, j'ai généré le fichier `mastermind.jar` qui représente l'exécutable de mon programme.
		C'est un archive qui contient :
  
		- les fichiers `*.class` issus de la compilation des fichiers sources `*.java`,
		
		- les bibliothèques `*.jar` que j'ai utilisées pour construire le programme (bibliothèques `log4j`).


	2. Exécution du fichier `mastermind.jar`  

		Le jeu peut être exécuté en mode console uniquement (cmd "windows").

		Nous devons tout d'abord nous placer dans le dossier ou se situe le `mastermind.jar` avec le cmd de windows exemple : cd C:\Users\Mastermind.

		- Exécution sans argument

			```
			java -jar mastermind.jar
			```
   
		- Exécution avec arguments

		- Pour exécuter le programme en mode "DEV", taper la commande :

			```
			java -jar mastermind.jar DEV
			```
			
		- Pour exécuter le programme en mode "PROD", taper la commande :

			```
			java -jar mastermind.jar PROD
			```