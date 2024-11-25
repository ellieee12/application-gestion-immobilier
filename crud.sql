use saes3;
set autocommit=0;
start transaction;

drop table if exists louer;
drop table if exists Document_Location;
DROP TABLE IF EXISTS location; 
DROP TABLE IF EXISTS bien;
DROP TABLE IF EXISTS locataire;
DROP TABLE IF EXISTS icc;
DROP TABLE IF EXISTS immeuble;

create table Immeuble (
    id_immeuble varchar(20) not null,
    adresse VARCHAR(50) not null,
    code_postale CHAR(5) not null,
    ville VARCHAR(50) not null,
    periode_construction VARCHAR(50),
    type_immeuble char(1) not null,
    constraint pk_immeuble primary key(id_immeuble),
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
    constraint pk_locataire primary key(id_locataire)
);

create table icc (
    annee date not null,
    trimestre smallint not null,
    indice int not null,
    constraint pk_icc primary key(annee, trimestre)
);

CREATE TABLE Location(
    id_bien VARCHAR(20) not null,
    date_debut DATE not null,
    nb_mois INT,
    provision_charges_ttc decimal(15,2),
    loyer_ttc decimal(15,2),
    caution_ttc decimal(15,2),
    etat_lieux VARCHAR(50),
    date_derniere_reg DATE,
    montant_reel_paye decimal(15,2),
    annee date NOT NULL,
    trimestre smallint NOT NULL,
    constraint pk_location PRIMARY KEY(id_bien, date_debut),
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
    CONSTRAINT pk_document_location PRIMARY KEY(id_document),
    CONSTRAINT fk_document_location FOREIGN KEY(id_bien, date_debut) REFERENCES Location(id_bien, date_debut)
);

CREATE TABLE Louer(
   id_bien VARCHAR(20),
   date_debut DATE,
   id_locataire VARCHAR(20),
   constraint pk_louer PRIMARY KEY(id_bien, date_debut),
   constraint fk_louer_location FOREIGN KEY(id_bien, date_debut)REFERENCES Location(id_bien, date_debut),
   constraint fk_louer_locataire FOREIGN KEY(id_locataire) REFERENCES Locataire(id_locataire)
);



-- Insert sample data into Immeuble
insert into Immeuble (id_immeuble, adresse, code_postale, ville, periode_construction, type_immeuble)
values 
(1,'123 Rue de Paris', '75001', 'Paris', '1990-2000', 'B'),
(2,'45 Avenue Victor Hugo', '69002', 'Lyon', '1980-1990', 'M'),
(3,'78 Rue de Lille', '59000', 'Lille', '2000-2010', 'B'),
(4,'10 Boulevard Gambetta', '06000', 'Nice', '1970-1980', 'M');

-- Insert sample data into Bien
insert into bien (id_bien, nb_pieces, numero_etage, surface_habitable, date_acquisition, type_bien, id_immeuble)
values 
(1, 3, 1, 75.50, '2020-01-15', 'G', 1),
(2, 2, 3, 45.00, '2021-05-10', 'L', 1),
(3, 4, 2, 80.75, '2019-11-20', 'G', 2),
(4, 1, 0, 30.25, '2023-03-05', 'L', 3),
(5, 5, 4, 120.50, '2018-07-30', 'G', 4);

-- Insert sample data into Locataire
insert into locataire (id_locataire,nom, prenom, telephone, mail, date_naissance)
values 
(1,'Dupont', 'Marie', '0123456789', 'marie.dupont@example.com', '1992-03-12'),
(2,'Leroy', 'Pierre', '0123456790', 'pierre.leroy@example.com', '1985-07-21'),
(3,'Martin', 'Lucie', '0123456791', 'lucie.martin@example.com', '1990-05-17'),
(4,'Bernard', 'Julien', '0123456792', 'julien.bernard@example.com', '1993-09-23'),
(5,'Rousseau', 'Sophie', '0123456793', 'sophie.rousseau@example.com', '1988-12-11');

-- Insert sample data into ICC
INSERT INTO icc (annee, trimestre, indice)
VALUES
('2023-01-01', 1, 125),
('2023-04-01', 2, 130),
('2023-07-01', 3, 135),
('2023-10-01', 4, 140),
('2024-01-01', 1, 145);

-- Insert sample data into Location
INSERT INTO Location (
    id_bien, date_debut, nb_mois, provision_charges_ttc, loyer_ttc, 
    caution_ttc, etat_lieux, date_derniere_reg, montant_reel_paye, annee, trimestre
)
VALUES
(1, '2023-01-01', 12, 150.00, 750.00, 1500.00, 'Etat_001', '2023-12-01', 9000.00, '2023-01-01', 1),
(2, '2023-04-01', 6, 100.00, 500.00, 1000.00, 'Etat_002', '2023-10-01', 3000.00, '2023-04-01', 2),
(3, '2023-07-01', 9, 200.00, 1000.00, 2000.00, 'Etat_003', '2023-10-01', 7000.00, '2023-07-01', 3),
(4, '2023-10-01', 24, 250.00, 1200.00, 2400.00, 'Etat_004', '2024-10-01', 12000.00, '2023-10-01', 4),
(5, '2024-01-01', 18, 300.00, 1500.00, 3000.00, 'Etat_005', '2024-06-01', 18000.00, '2024-01-01', 1);


INSERT INTO Louer (id_bien, date_debut, id_locataire)
VALUES
(1, '2023-01-01', 1),
(2, '2023-04-01', 2),
(3, '2023-07-01', 3),
(4, '2023-10-01', 4),
(5, '2024-01-01', 5);
commit;