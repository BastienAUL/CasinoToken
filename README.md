# CasinoToken

ATTENTION , CECI N'EST QUE LE FICHIER SOURCE DU PROJET , POUR TELECHARGER LE PLUGIN , RENDEZ VOUS SUR [MODRINT](https://modrinth.com/plugin/casinotoken)

CasinoToken est un plugin Minecraft pour **serveurs Spigot/Paper** qui permet aux joueurs d’acheter et vendre des jetons pour un casino. L’argent des achats est automatiquement transféré au propriétaire du casino défini par l’admin.

---

## 📦 Fonctionnalités

- Acheter des jetons via une interface GUI (`/buytoken`)  
- Vendre ses jetons et récupérer de l’argent (`/selltoken`)  
- Définir le propriétaire du casino (`/setowner <pseudo>`)  
- Vérifier qui est le propriétaire (`/owner`)  
- Compatible avec **Vault** et tout plugin d’économie (EssentialsX, etc.)  

---

## ⚡ Commandes

| Commande        | Description |
|-----------------|-------------|
| `/setowner <pseudo>` | Définit le propriétaire du casino |
| `/owner`  | Affiche le propriétaire actuel |
| `/buytoken`     | Ouvre l’interface d’achat de jetons |
| `/selltoken`    | Vend tous les jetons présents dans l’inventaire |

---

## 💰 Fonctionnement de l’économie

- Les achats retirent l’argent du joueur et le transfèrent au propriétaire du casino.  
- Les ventes retirent l’argent du propriétaire et le créditent au joueur.  
- Assurez-vous que le propriétaire possède assez d’argent pour racheter les jetons.
