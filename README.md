# Gitignore
Dans le cas où votre gitignore n'est pas pris en compte veuillez suivre la procédure suivante.
- Suppression de l'index :
```
 git rm -r --cached .
```
- réimportation de tout l'index (le .gitignore est maintenant pris en compte) :
```
git add .
```
- commit des changements :
```
git commit -m ".gitignore est maintenant fonctionnel"
```