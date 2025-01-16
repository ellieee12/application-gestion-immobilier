use saes3;
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
DROP PROCEDURE IF EXISTS getAllFactures$$
DROP PROCEDURE IF EXISTS deleteFacture$$
DROP PROCEDURE IF EXISTS insertFacture$$
DROP PROCEDURE IF EXISTS getAllLocataires$$
DROP PROCEDURE IF EXISTS getLocataireById$$
DROP PROCEDURE IF EXISTS insertLocataire$$
DROP PROCEDURE IF EXISTS deleteLocation$$
DROP PROCEDURE IF EXISTS insertLocation$$
DROP PROCEDURE IF EXISTS getFactureByNumero$$
DROP PROCEDURE IF EXISTS insertLocation$$
DROP PROCEDURE IF EXISTS insertCompteur$$
DROP PROCEDURE IF EXISTS getColocationByIdBien$$
DROP PROCEDURE IF EXISTS getSommeLoyers12Mois$$
DROP PROCEDURE IF EXISTS getLoyersTermine$$
DROP PROCEDURE IF EXISTS getLoyersCommence$$
DROP PROCEDURE IF EXISTS getMontantTravaux$$

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
        entretien_parties_communes,
        date_acquisition
    FROM bien 
    WHERE id_immeuble = v_id_immeuble;
END$$

CREATE PROCEDURE insertGarage(
    IN v_id_bien varchar(20), 
    in v_date_acquisition date,
    in v_id_immeuble varchar(20),
    in v_entretien_parties_communes decimal(15,2))
BEGIN
    INSERT INTO bien (id_bien, date_acquisition,type_bien,id_immeuble,entretien_parties_communes)
    VALUES (v_id_bien, v_date_acquisition,'G',v_id_immeuble,v_entretien_parties_communes);
END$$

create procedure insertLogement(
    in v_id_bien varchar(20),
    in v_nb_pieces int,
    in v_numero_etage int,
    in v_surface_habitable decimal(5,2),
    in v_date_acquisition date,
    in v_id_immeuble varchar(20),
    in v_entretien_parties_communes decimal(15,2)
    )
BEGIN
    INSERT INTO bien (id_bien, nb_pieces, numero_etage, 
        surface_habitable, date_acquisition, type_bien, id_immeuble,entretien_parties_communes) 
    VALUES (v_id_bien, v_nb_pieces, v_numero_etage, v_surface_habitable, 
        v_date_acquisition, 'L', v_id_immeuble,v_entretien_parties_communes);
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
    select adresse, code_postal, ville, periode_construction, type_immeuble
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
    v_code_postal CHAR(5),
    v_ville VARCHAR(50),
    v_periode_construction VARCHAR(50),
    v_type_immeuble char(1))
BEGIN
    insert into immeuble (id_immeuble, adresse, code_postal,ville,periode_construction,type_immeuble)
    values (v_id_immeuble,v_adresse,v_code_postal,v_ville,v_periode_construction,v_type_immeuble);
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

CREATE PROCEDURE getReleveFromIdCompteur (v_id_compteur VARCHAR(50),v_annee int)

BEGIN 
    SELECT index_comp from releve where id_compteur = v_id_compteur and annee = v_annee;
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
    insert into releve (annee,index_comp,id_compteur)
    values (v_annee,v_index,v_id_compteur);
END$$
CREATE PROCEDURE getAllFactures()

BEGIN
    select * from facture;
END$$

CREATE PROCEDURE deleteFacture(v_numero_facture varchar(50))

BEGIN
    delete from facture where numero_facture=v_numero_facture;
END$$

CREATE PROCEDURE insertFacture (
    v_numero_facture VARCHAR(50),
    v_date_paiement DATE,
    v_date_emission DATE,
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

CREATE PROCEDURE getFactureByNumero(v_numero_facture varchar(50))
BEGIN
    select * from facture where numero_facture = v_numero_facture;
END$$

CREATE PROCEDURE getAllLocataires()
BEGIN
    select * from locataire;
END$$

CREATE PROCEDURE insertLocataire(
    v_id_locataire varchar(20),
    v_nom VARCHAR(30),
    v_prenom VARCHAR(30),
    v_telephone CHAR(15),
    v_mail VARCHAR(50),
    v_date_naissance DATE
)
BEGIN
    insert into locataire(id_locataire, nom, prenom, mail, telephone, date_naissance) 
    values (v_id_locataire,v_nom,v_prenom,v_mail,v_telephone,v_date_naissance);
END$$ 

CREATE PROCEDURE getLocataireById(v_id_locataire varchar(20))
BEGIN
    select * from locataire where id_locataire=v_id_locataire;
END$$

CREATE PROCEDURE deleteLocation(
    v_id_bien varchar(20),
    v_date_debut date
)
BEGIN
    delete from louer where id_bien = v_id_bien and date_debut = v_date_debut;
    delete from Document_Location where id_bien = v_id_bien and date_debut = v_date_debut;
    delete from location where id_bien = v_id_bien and date_debut = v_date_debut;
END$$

CREATE PROCEDURE insertLocation (
    v_id_bien VARCHAR(20),
    v_date_debut DATE,
    v_nb_mois INT,
    v_colocation VARCHAR(50),
    v_provision_charges_ttc decimal(15,2),
    v_loyer_ttc decimal(15,2),
    v_caution_ttc decimal(15,2),
    v_annee date,
    v_trimestre smallint(6)
)
BEGIN
    insert into location (id_bien,date_debut,nb_mois,colocation,provision_charges_ttc,
    loyer_ttc,caution_ttc,annee,trimestre)
    values (v_id_bien,v_date_debut,v_nb_mois,v_colocation,
    v_provision_charges_ttc,v_loyer_ttc,v_caution_ttc, v_annee, v_trimestre);
END$$

CREATE PROCEDURE insertCompteur (
    v_id_bien varchar(20),
    v_prix decimal(15,2),
    v_type_compteur varchar(20)
)
BEGIN
    insert into compteur (type_compteur,prix_abonnement,id_bien)
    values (v_type_compteur,v_prix,v_id_bien);
END$$

CREATE PROCEDURE getColocationByIdBien (
    v_id_bien varchar(20)
    )
BEGIN 
    Select * from location where id_bien = v_id_bien;
END$$

CREATE PROCEDURE getSommeLoyers12Mois(IN v_annee int)
BEGIN
    select sum(loyer_ttc) from location 
	where (date_fin is null or year(date_fin)>v_annee-1) 
	and year(date_debut) < v_annee - 1;
END$$

CREATE PROCEDURE getLoyersTermine(IN v_annee int)
BEGIN
	select loyer_ttc, year(date_debut), month(date_debut), month(date_fin) 
	from location  
	where year(date_fin) = v_annee -1;
END$$

CREATE PROCEDURE getLoyersCommence(IN v_annee int)
BEGIN
	select loyer_ttc, month(date_debut) 
	from location 
	where year(date_debut) = v_annee -1 
	and (date_fin is null or year(date_fin)>v_annee-1);
END$$

CREATE PROCEDURE getMontantTravaux(IN v_annee int)
BEGIN
	SELECT montant_reel_paye - (SELECT imputable_locataire 
                                FROM `facture` 
                                WHERE year(date_paiement)=v_annee)
    FROM `facture` 
    WHERE year(date_paiement)=v_annee;
END$$

DELIMITER ;