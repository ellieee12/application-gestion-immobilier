set autocommit=0;
start transaction;

drop table if exists bien;
drop table if exists immeuble;
drop table if exists locataire;

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
    id_locataire int auto_increment not null,
    nom VARCHAR(30) not null,
    prenom VARCHAR(30) not null,
    telephone CHAR(15) not null,
    mail VARCHAR(50) not null,
    date_naissance DATE not null,
    adresse VARCHAR(50) not null,
    code_postal CHAR(5) not null,
    ville VARCHAR(50) not null,
    constraint pk_locataire primary key(id_locataire)
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
insert into locataire (nom, prenom, telephone, mail, date_naissance, adresse, code_postal, ville)
values 
('Dupont', 'Marie', '0123456789', 'marie.dupont@example.com', '1992-03-12', '8 Rue de Rivoli', '75004', 'Paris'),
('Leroy', 'Pierre', '0123456790', 'pierre.leroy@example.com', '1985-07-21', '22 Boulevard Voltaire', '75011', 'Paris'),
('Martin', 'Lucie', '0123456791', 'lucie.martin@example.com', '1990-05-17', '56 Rue Saint-Jean', '69001', 'Lyon'),
('Bernard', 'Julien', '0123456792', 'julien.bernard@example.com', '1993-09-23', '34 Rue de Lille', '59000', 'Lille'),
('Rousseau', 'Sophie', '0123456793', 'sophie.rousseau@example.com', '1988-12-11', '19 Avenue des Fleurs', '06000', 'Nice');

commit;