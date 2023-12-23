CREATE DATABASE gestion_commande;

CREATE TABLE utilisateur (
    id INT(10) PRIMARY KEY AUTO_INCREMENT,
    nom_utilisateur VARCHAR(100) NOT NULL,
    mot_de_passe VARCHAR(100) NOT NULL
);

CREATE TABLE client (
    client_id INT(10) PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL
);
CREATE TABLE produit (
    product_id INT(10) PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(100) NOT NULL,
    label VARCHAR(255) NOT NULL,
    quantite_in_stock INT(10) NOT NULL
);
CREATE TABLE commande (
    commande_id VARCHAR(255) PRIMARY KEY,
    client_id INT(10),
    date_de_commande DATE,
    status VARCHAR(50),
    FOREIGN KEY (client_id) REFERENCES client(client_id)
);


CREATE TABLE commande_produit (
    commande_id VARCHAR(255),
    product_id INT(10),
    quantite INT(10),
    PRIMARY KEY (commande_id, product_id),
    FOREIGN KEY (commande_id) REFERENCES commande(commande_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES produit(product_id)
);

INSERT INTO client (client_id, first_name, last_name, address)
VALUES (1, 'John', 'Doe', '123 Rue de la République');

INSERT INTO client (client_id, first_name, last_name, address)
VALUES (2, 'Jane', 'Smith', '456 Avenue des Fleurs');

INSERT INTO produit (product_id, product_name, label, quantite_in_stock)
VALUES (1, 'ProduitA', 'LabelA', 100);

INSERT INTO produit (product_id, product_name, label, quantite_in_stock)
VALUES (2, 'ProduitB', 'LabelB', 50);

INSERT INTO produit (product_id, product_name, label, quantite_in_stock)
VALUES (3, 'ProduitC', 'LabelC', 75);

INSERT INTO commande (commande_id, client_id, date_de_commande, status)
VALUES ('CMD1', 1, '2023-01-01', 'En cours');

INSERT INTO commande (commande_id, client_id, date_de_commande, status)
VALUES ('CMD2', 2, '2023-02-15', 'Terminée');

INSERT INTO commande_produit (commande_id, product_id, quantite)
VALUES ('CMD1', 1, 5);

INSERT INTO commande_produit (commande_id, product_id, quantite)
VALUES ('CMD1', 2, 3);

INSERT INTO commande_produit (commande_id, product_id, quantite)
VALUES ('CMD2', 3, 10);

ALTER TABLE commande
ADD COLUMN commande_description VARCHAR(255);


SELECT product_id, quantite FROM commande_produit WHERE commande_id ="CMD1";

ALTER TABLE produit ADD COLUMN prix_unitaire DECIMAL(10, 2);

ALTER TABLE commande_produit ADD COLUMN prix_total DECIMAL(10, 2);
UPDATE commande_produit
SET prix_total = quantite * (SELECT prix_unitaire FROM produit WHERE produit.product_id = commande_produit.product_id);


--requete qui me calcul le prix total de la commande

SELECT c.commande_id, c.client_id, c.commande_description, cl.first_name AS client_first_name, cl.last_name AS client_last_name, cl.address AS client_address, c.date_de_commande, c.status, SUM(cp.quantite * p.prix_unitaire) AS prix_final FROM commande c JOIN client cl ON c.client_id = cl.client_id JOIN commande_produit cp ON c.commande_id = cp.commande_id JOIN produit p ON cp.product_id = p.product_id GROUP BY c.commande_id, c.client_id, c.commande_description, cl.first_name, cl.last_name, cl.address, c.date_de_commande, c.status;


--requete pour selectionner les champs du produit commander
SELECT p.product_id, p.product_name, p.label, p.quantite_in_stock,p.prix_unitaire, cp.quantite
FROM commande_produit cp
JOIN produit p ON cp.product_id = p.product_id
WHERE cp.commande_id = '140e2';




--requete pour gettCommandeById avec les meme champs de la table pour get all 
SELECT c.commande_id, c.client_id, c.commande_description, cl.first_name AS client_first_name, cl.last_name AS client_last_name, cl.address AS client_address, c.date_de_commande, c.status, SUM(cp.quantite * p.prix_unitaire) AS prix_final FROM commande c JOIN client cl ON c.client_id = cl.client_id JOIN commande_produit cp ON c.commande_id = cp.commande_id JOIN produit p ON cp.product_id = p.product_id WHERE c.commande_id = '140e2' GROUP BY c.commande_id, c.client_id, c.commande_description, cl.first_name, cl.last_name, cl.address, c.date_de_commande, c.status;