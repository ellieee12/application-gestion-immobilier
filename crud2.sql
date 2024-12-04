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
DROP PROCEDURE IF EXISTS getAllFacture$$
DROP PROCEDURE IF EXISTS deleteFacture$$

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
        date_acquisition 
    FROM bien 
    WHERE id_immeuble = v_id_immeuble;
END$$

CREATE PROCEDURE insertGarage(IN v_id_bien varchar(20), in v_date_acquisition date,v_id_immeuble varchar(20))
BEGIN
    INSERT INTO bien (id_bien, date_acquisition,type_bien,id_immeuble)
    VALUES (v_id_bien, v_date_acquisition,'G',v_id_immeuble);
END$$

create procedure insertLogement(
    in v_id_bien varchar(20),
    in v_nb_pieces int,
    in v_numero_etage int,
    in v_surface_habitable decimal(5,2),
    in v_date_acquisition date,
    in v_id_immeuble varchar(20)
    )
BEGIN
    INSERT INTO bien (id_bien, nb_pieces, numero_etage, 
        surface_habitable, date_acquisition, type_bien, id_immeuble) 
    VALUES (v_id_bien, v_nb_pieces, v_numero_etage, v_surface_habitable, 
        v_date_acquisition, 'L', v_id_immeuble);
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

CREATE PROCEDURE getAllFacture()

BEGIN
    select * from facture;
END$$

CREATE PROCEDURE deleteFacture(v_numero_facture varchar(50))

BEGIN
    delete from facture where numero_facture=v_numero_facture;
END$$

CREATE PROCEDURE insertFacture (
    v_numero_facture VARCHAR(50),
    v_date_paiement DECIMAL(15,2),
    v_date_emission DECIMAL(15,2),
    v_numero_devis VARCHAR(50),
    v_designation VARCHAR(50),
    v_montant_reel_paye DECIMAL(15,2),
    v_montant DECIMAL(15,2),
    v_imputable_locataire DECIMAL(15,2),
    v_id_bien VARCHAR(20)
)
BEGIN
    insert into facture (numero_facture,date_paiement,date_emission,numero_devis,designation,montant_reel_paye,montant,imputable_locataire,id_bien)
    values (v_numero_facture,v_date_paiement,v_date_emission,v_numero_devis,v_designation,v_montant_reel_paye,v_montant,v_imputable_locataire,v_id_bien);
END$$
-- verifier from compteur if bien is a maison or what
-- insert compteur and releve

DELIMITER ;