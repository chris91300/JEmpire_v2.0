# JEMPIRE V2.0

## Comment lancer le jeu
cloner le dossier en local
```bash
git clone https://github.com/chris91300/JEmpire_v2.0.git
```

il faut ensuite compiler le jeu
```bash
javac -d build/ $(find src -name "*.java")
```
ou
```bash
 javac -d build/ --source-path src src/Main.java
```

puis lancer le jeu avec
```bash
 java -cp build/ Main
```


## But du jeu

L'objectif est de développer votre village en construisant des batiments et en augmentant votre popuation

## Organisation d'un tour (journée)
A chaque tour (jour), le jeu vous affiche vos ressources et votre population
Vous devez assigner une tache au villageois. tant que vous n'avez pas d'autre type de personnage (soldats, artisans, éclaireur, chef) le jeu ne vous propose pas d'assigner des taches aux autres types de personnage

A la fin d'une journée, le jeu récupère les ressources produites ou trouvées.
Il est possible que vos villageois rencontrent d'autres personnes dans les forets. Dans ce cas, si vous avez assez de place, ils seront intégrés à votre village.
Ils est également possible que des villageois, en fin de journée, demande à rejoindre vos troupes.

## Forêts et mines
Lorsque vos villageois partent en forêt, ils peuvent trouver des mines ou d'autres forêts.

## info technique
Nous avons fait le choix de ne pas instancier un villageois ou autre personnage à chaque fois qu'on en créé un. Par exemple, la classe Villageois va gérer le nombre de villageois. Ceci pour éviter d'avoir à afficher une liste de 100 villageois pour savoir 'qui assigne-t-on à quoi?'.
Le jeu gère seul les personnes disponibles ou non.
