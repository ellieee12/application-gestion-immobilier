DELIMITER $$

DROP PROCEDURE IF EXISTS getAllBiens$$
DROP PROCEDURE IF EXISTS getBiensByImmeuble$$
DROP PROCEDURE IF EXISTS insertGarage$$
DROP PROCEDURE IF EXISTS insertLogement$$
DROP PROCEDURE IF EXISTS deleteBien$$
DROP PROCEDURE IF EXISTS getBienById$$
DROP PROCEDURE IF EXISTS getAllImmeubles$$
DROP PROCEDURE IF EXISTS getImmeubleById$$
DROP PROCEDURE IF EXISTS deleteImmeuble$$
DROP PROCEDURE IF EXISTS addImmeuble$$
DROP PROCEDURE IF EXISTS getImmeublesDisponibles$$
DROP PROCEDURE IF EXISTS getCompteByUsernameMdp$$
DROP PROCEDURE IF EXISTS insertCompte$$
DROP PROCEDURE IF EXISTS getMdpByUsername$$
DROP PROCEDURE IF EXISTS getCompteurByBienAndType$$
DROP PROCEDURE IF EXISTS getTypeImmeubleFromIdBien$$
DROP PROCEDURE IF EXISTS getReleveFromIdCompteur$$
DROP PROCEDURE IF EXISTS getEntretienFromIdBien$$
DROP PROCEDURE IF EXISTS getProvisionFromLocation$$
DROP PROCEDURE IF EXISTS setNouvelleProvision$$
DROP PROCEDURE IF EXISTS addReleve$$

CREATE PROCEDURE getAllBiens()
BEGIN
    SELECT * FROM bien;
END$$

CREATE PROCEDURE getBienById(IN v_id_bien varchar(20))
BEGIN
    SELECT * FROM bien WHERE id_bien = v_id_bien;
END$$

CREATE PROCEDURE getBiensByImmeuble(IN v_id_immeuble varchar(20))
BEGIN
    SELECT 
        id_bien, 
        type_bien, 
        numero_etage, 
        surface_habitable, 
        nb_pieces, 
        date_acquisition,
        entretien_partie_commune
    FROM bien 
    WHERE id_immeuble = v_id_immeuble;
END$$

CREATE PROCEDURE insertGarage(
    IN v_id_bien varchar(20), 
    in v_date_acquisition date,
    in v_id_immeuble varchar(20),
    in v_entretien_partie_commune decimal(15,2))
BEGIN
    INSERT INTO bien (id_bien, date_acquisition,type_bien,id_immeuble,entretien_partie_commune)
    VALUES (v_id_bien, v_date_acquisition,'G',v_id_immeuble,v_entretien_partie_commune);
END$$

create procedure insertLogement(
    in v_id_bien varchar(20),
    in v_nb_pieces int,
    in v_numero_etage int,
    in v_surface_habitable decimal(5,2),
    in v_date_acquisition date,
    in v_id_immeuble varchar(20),
    in v_entretien_partie_commune decimal(15,2)
    )
BEGIN
    INSERT INTO bien (id_bien, nb_pieces, numero_etage, 
        surface_habitable, date_acquisition, type_bien, id_immeuble,entretien_partie_commune) 
    VALUES (v_id_bien, v_nb_pieces, v_numero_etage, v_surface_habitable, 
        v_date_acquisition, 'L', v_id_immeuble,v_entretien_partie_commune);
END$$

CREATE PROCEDURE deleteBien (v_id_bien varchar(20))

BEGIN
    DELETE FROM louer where id_bien = v_id_bien;
    DELETE FROM location where id_bien = v_id_bien;
    DELETE FROM bien WHERE id_bien = v_id_bien;
END$$

CREATE PROCEDURE getAllImmeubles ()
BEGIN
    select * from immeuble;
END$$

CREATE PROCEDURE getImmeubleById (v_id_immeuble varchar(20))

BEGIN
    select adresse, code_postale, ville, periode_construction, type_immeuble
    from immeuble
    where id_immeuble=v_id_immeuble;
END$$

CREATE PROCEDURE deleteImmeuble(v_id_immeuble varchar(20))

BEGIN
    delete from louer where id_bien in (
        select id_bien 
        from location 
        where id_bien in (
            select id_bien 
            from bien
            where id_immeuble = v_id_immeuble
        )
    );
    delete from location where id_bien in (
        select id_bien 
        from bien
        where id_immeuble = v_id_immeuble
    );
    delete from bien where id_immeuble = v_id_immeuble;
    delete from immeuble where id_immeuble = v_id_immeuble;
END$$

CREATE PROCEDURE addImmeuble (
    v_id_immeuble varchar(20),
    v_adresse VARCHAR(50),
    v_code_postale CHAR(5),
    v_ville VARCHAR(50),
    v_periode_construction VARCHAR(50),
    v_type_immeuble char(1))
BEGIN
    insert into immeuble (id_immeuble, adresse, code_postale,ville,periode_construction,type_immeuble)
    values (v_id_immeuble,v_adresse,v_code_postale,v_ville,v_periode_construction,v_type_immeuble);
END$$
    
CREATE PROCEDURE getImmeublesDisponibles ()

BEGIN
    select * from immeuble where id_immeuble not in (
        select b.id_immeuble from bien b, immeuble i
        where b.id_immeuble=i.id_immeuble
        and i.type_immeuble='M'
    );
END$$

CREATE PROCEDURE getCompteByUsernameMdp (v_username VARCHAR(50))

BEGIN
    SELECT * from signup where username = v_username;
END$$

CREATE PROCEDURE insertCompte (in v_username VARCHAR(50), v_mdp VARCHAR(50))

BEGIN 
    INSERT INTO signup(username,mdp)
    VALUES (v_username, v_mdp);
END$$

CREATE PROCEDURE getMdpByUsername (v_username VARCHAR(50))

BEGIN 
    SELECT mdp from signup where username = v_username;
END$$

CREATE PROCEDURE getCompteurByBienAndType (v_id_bien VARCHAR(50),v_type_compteur VARCHAR(50))

BEGIN 
    SELECT id_compteur from compteur where id_bien = v_id_bien and type_compteur = v_type_compteur;
END$$

CREATE PROCEDURE getTypeImmeubleFromIdBien (v_id_bien VARCHAR(50))

BEGIN 
    SELECT type_immeuble from immeuble,bien where bien.id_bien = v_id_bien and bien.id_immeuble = immeuble.id_immeuble;
END$$

CREATE PROCEDURE getReleveFromIdCompteur (v_id_compteur VARCHAR(50))

BEGIN 
    SELECT annee,index_comp from releve where id_compteur = v_id_compteur;
END$$

CREATE PROCEDURE getEntretienFromIdBien(v_id_bien varchar(20))
BEGIN
    select entretien_parties_communes from bien where id_bien = v_id_bien;
END$$

CREATE PROCEDURE getProvisionFromLocation(v_id_bien varchar(20), v_date_debut date)
BEGIN
    select provision_charges_ttc from location where id_bien = v_id_bien and date_debut = v_date_debut;
END$$

CREATE PROCEDURE setNouvelleProvision(v_id_bien varchar(20), v_date_debut date, v_provision decimal(15,2))
BEGIN
    update location set provision_charges_ttc = v_provision where id_bien = v_id_bien and date_debut = v_date_debut;
END$$

CREATE PROCEDURE addReleve (v_annee int,v_index int,v_id_compteur varchar(50))
BEGIN
    insert into releve (id_immeuble, adresse, code_postale)
    values (v_id_immeuble,v_adresse,v_code_postale,v_ville,v_periode_construction,v_type_immeuble);
END$$

DELIMITER ;