DELIMITER $$

DROP PROCEDURE IF EXISTS getAllBiens$$
DROP PROCEDURE IF EXISTS getBiensByImmeuble$$
DROP PROCEDURE IF EXISTS insertGarage$$
DROP PROCEDURE IF EXISTS insertLogement$$
DROP PROCEDURE IF EXISTS deleteBien$$
DROP PROCEDURE IF EXISTS getBienById$$

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

    
DELIMITER ;