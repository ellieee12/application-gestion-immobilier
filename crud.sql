use saes3;
set autocommit=0;
start transaction;

DROP TABLE IF EXISTS facture;
DROP TABLE IF EXISTS releve;
DROP TABLE IF EXISTS compteur;
drop table if exists louer;
drop table if exists Document_Location;
DROP TABLE IF EXISTS location; 
DROP TABLE IF EXISTS bien;
DROP TABLE IF EXISTS locataire;
DROP TABLE IF EXISTS icc;
DROP TABLE IF EXISTS immeuble;
DROP TABLE IF EXISTS SignUp;


create table Immeuble (
    id_immeuble varchar(20) not null,
    adresse VARCHAR(50) not null,
    code_postale CHAR(5) not null,
    ville VARCHAR(50) not null,
    periode_construction VARCHAR(50),
    type_immeuble char(1) not null,
    primary key(id_immeuble),
    constraint ck_immeuble_type check(type_immeuble in ('B','M'))
);

create table bien(
    id_bien varchar(20) not null,
    nb_pieces int,
    numero_etage int,
    surface_habitable decimal(5,2),
    date_acquisition date,
    type_bien char(1),
    id_immeuble varchar(20) not null,
    entretien_partie_commune decimal(15,2),
    constraint pk_bien primary key(id_bien),
    constraint ck_bien_type check(type_bien in ('G','L')),
    constraint fk_bien_immeuble foreign key(id_immeuble) references immeuble(id_immeuble)
);

create table Locataire (
    id_locataire varchar(20) not null,
    nom VARCHAR(30) not null,
    prenom VARCHAR(30) not null,
    telephone CHAR(15) not null,
    mail VARCHAR(50) ,
    date_naissance DATE,
    primary key(id_locataire)
);

create table icc (
    annee date not null,
    trimestre smallint not null,
    indice int not null,
    primary key(annee, trimestre)
);

CREATE TABLE Location(
    id_bien VARCHAR(20) not null,
    date_debut DATE not null,
    nb_mois INT,
    colocation TINYINT(1),
    provision_charges_ttc decimal(15,2),
    loyer_ttc decimal(15,2),
    caution_ttc decimal(15,2),
    date_derniere_reg DATE,
    annee date NOT NULL,
    trimestre smallint NOT NULL,
    PRIMARY KEY(id_bien, date_debut),
    constraint fk_location_id_bien FOREIGN KEY(id_bien) REFERENCES Bien(id_bien),
    constraint fk_location_annee_trimestre FOREIGN KEY (annee, trimestre) REFERENCES ICC(annee, trimestre)
);

CREATE TABLE Document_Location(
    id_document INT not null auto_increment,
    filepath VARCHAR(200) not null,
    description VARCHAR(100),
    date_enregistrement DATE,
    id_bien VARCHAR(20) NOT NULL,
    id_locataire VARCHAR(20) NOT NULL,
    date_debut DATE NOT NULL,
    PRIMARY KEY(id_document),
    CONSTRAINT fk_document_location FOREIGN KEY(id_bien, date_debut) REFERENCES Location(id_bien, date_debut)
);

CREATE TABLE Louer(
   id_bien VARCHAR(20),
   date_debut DATE,
   id_locataire VARCHAR(20),
   PRIMARY KEY(id_bien, date_debut),
   constraint fk_louer_location FOREIGN KEY(id_bien, date_debut)REFERENCES Location(id_bien, date_debut),
   constraint fk_louer_locataire FOREIGN KEY(id_locataire) REFERENCES Locataire(id_locataire)
);

CREATE TABLE SignUp(
    username VARCHAR(50),
    mdp VARCHAR(50),
    PRIMARY KEY(username, mdp)
);

CREATE TABLE facture (
    numero_facture VARCHAR(50) not null,
    date_paiement DATE,
    date_emission DATE,
    numero_devis VARCHAR(50),
    designation VARCHAR(50),
    montant_reel_paye DECIMAL(15,2),
    montant DECIMAL(15,2),
    imputable_locataire DECIMAL(15,2),
    id_bien VARCHAR(20) NOT NULL,
    primary key(numero_facture),
    constraint fk_facture_id_bien foreign key(id_bien) references Bien(id_bien)
);

CREATE TABLE compteur (
    id_compteur INT not null auto_increment,
    type_compteur VARCHAR(50),
    prix_abonnement DECIMAL(15,2),
    id_bien VARCHAR(20) NOT NULL,
    primary key (id_compteur),
    constraint fk_compteur_id_bien foreign key(id_bien) references Bien(id_bien)
);

CREATE TABLE releve ( 
    annee INT not null,
    index_comp INT,
    id_compteur VARCHAR(50) NOT NULL,
    constraint pk_releve primary key(annee,id_compteur),
    constraint fk_releve_id_compteur foreign key (id_compteur) references compteur(id_compteur)
);


COMMIT;
