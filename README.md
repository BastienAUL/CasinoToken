# CasinoToken

CasinoToken est un plugin Minecraft pour **serveurs Spigot/Paper** qui permet aux joueurs d’acheter et vendre des jetons pour un casino. L’argent des achats est automatiquement transféré au propriétaire du casino défini par l’admin.

---

## 📦 Fonctionnalités

- Acheter des jetons via une interface GUI (`/buytoken`)  
- Vendre ses jetons et récupérer de l’argent (`/selltoken`)  
- Définir le propriétaire du casino (`/owner <pseudo>`)  
- Vérifier qui est le propriétaire (`/casinoowner`)  
- Compatible avec **Vault** et tout plugin d’économie (EssentialsX, etc.)  

---

## ⚡ Commandes

| Commande        | Description |
|-----------------|-------------|
| `/owner <pseudo>` | Définit le propriétaire du casino |
| `/casinoowner`  | Affiche le propriétaire actuel |
| `/buytoken`     | Ouvre l’interface d’achat de jetons |
| `/selltoken`    | Vend tous les jetons présents dans l’inventaire |

---

## 💰 Fonctionnement de l’économie

- Les achats retirent l’argent du joueur et le transfèrent au propriétaire du casino.  
- Les ventes retirent l’argent du propriétaire et le créditent au joueur.  
- Assurez-vous que le propriétaire possède assez d’argent pour racheter les jetons.

---

## 🛠 Installation

1. Téléchargez le fichier `CasinoToken-1.0-SNAPSHOT.jar`.  
2. Placez-le dans le dossier `plugins/` de votre serveur Spigot/Paper.  
3. Assurez-vous que **Vault** et un plugin d’économie sont installés.  
4. Redémarrez le serveur.  
5. Définissez un propriétaire avec `/owner <pseudo>` avant de vendre ou acheter des jetons.

---

## ⚙️ Configuration

Le plugin utilise un fichier `config.yml` minimal :

```yaml
casino-owner: "None"
