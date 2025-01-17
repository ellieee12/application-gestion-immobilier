USE saes3;
START TRANSACTION;

-- Immeuble
INSERT INTO Immeuble VALUES
('IM001', '10 Rue de Paris', '75001', 'Paris', '1950-1960', 'B'),
('IM002', '20 Avenue des Champs', '75008', 'Paris', '1970-1980', 'M'),
('IM003', '5 Boulevard Saint-Michel', '75005', 'Paris', NULL, 'B'),
('IM004', '15 Place de la Concorde', '75008', 'Paris', '2000-2010', 'M'),
('IM005', '25 Rue Lafayette', '75009', 'Paris', NULL, 'B');

-- Bien
INSERT INTO Bien VALUES
('B001', 3, 2, 75.50, '2015-06-01', 'G', 'IM001', 250.00),
('B002', 4, 3, 95.00, '2018-09-12', 'L', 'IM002', 300.00),
('B003', 2, 1, 55.25, '2020-03-15', 'G', 'IM003', 200.00),
('B004', 5, 5, 120.75, '2017-11-30', 'L', 'IM004', 400.00),
('B005', 1, 0, 35.50, '2021-05-20', 'G', 'IM005', 150.00);

-- Locataire
INSERT INTO Locataire VALUES
('LOC001', 'Dupont', 'Jean', '0601234567', 'jean.dupont@mail.com', '1985-01-15'),
('LOC002', 'Martin', 'Claire', '0612345678', 'claire.martin@mail.com', '1990-07-10'),
('LOC003', 'Bernard', 'Paul', '0623456789', NULL, '1980-03-25'),
('LOC004', 'Durand', 'Marie', '0634567890', 'marie.durand@mail.com', '1995-12-05'),
('LOC005', 'Petit', 'Luc', '0645678901', NULL, '2000-08-14');

-- Location
INSERT INTO Location VALUES
('B001', '2023-06-01', 12, "Non", 50.00, 1200.00, 2400.00, NULL, NULL),
('B002', '2023-07-01', 24, "Oui", 60.00, 1500.00, 3000.00, NULL, NULL),
('B003', '2023-08-01', 6, "Non", 40.00, 900.00, 1800.00, NULL, NULL),
('B004', '2023-09-01', 36, "Oui", 70.00, 1800.00, 3600.00, NULL, NULL),
('B005', '2023-10-01', 18, "Non", 30.00, 750.00, 1500.00, NULL, NULL);


-- Document_Location
INSERT INTO Document_Location(filepath, description, date_enregistrement, id_bien, id_locataire, date_debut) VALUES
('docs/contract_001.pdf', 'Lease Agreement', '2023-06-01', 'B001', 'LOC001', '2023-06-01'),
('docs/contract_002.pdf', 'Lease Agreement', '2023-07-01', 'B002', 'LOC002', '2023-07-01'),
('docs/contract_003.pdf', 'Lease Agreement', '2023-08-01', 'B003', 'LOC003', '2023-08-01'),
('docs/contract_004.pdf', 'Lease Agreement', '2023-09-01', 'B004', 'LOC004', '2023-09-01'),
('docs/contract_005.pdf', 'Lease Agreement', '2023-10-01', 'B005', 'LOC005', '2023-10-01');

-- Louer
INSERT INTO Louer VALUES
('B001', '2023-06-01', 'LOC001'),
('B002', '2023-07-01', 'LOC002'),
('B003', '2023-08-01', 'LOC003'),
('B004', '2023-09-01', 'LOC004'),
('B005', '2023-10-01', 'LOC005');

-- SignUp
INSERT INTO SignUp VALUES
('admin1', 'password123'),
('user1', 'userpass1'),
('manager', 'manager2023'),
('guest', 'guestlogin'),
('supervisor', 'supervisor1');

-- Facture
INSERT INTO Facture VALUES
('F001', '2022-01-13', '2022-02-02', 'DV001', 'Electricity', 120.00, 150.00, 50.00, 'B001'),
('F002', '2022-09-23', '2022-10-26', 'DV002', 'Water', 200.00, 250.00, 60.00, 'B002'),
('F003', '2024-09-26', '2024-11-23', 'DV003', 'Gas', 80.00, 100.00, 30.00, 'B003'),
('F004', '2020-05-23', '2020-06-23', 'DV004', 'Heating', 300.00, 350.00, 70.00, 'B004'),
('F005', '2022-07-02', '2022-08-13', 'DV005', 'Internet', 50.00, 70.00, 20.00, 'B005');

-- Compteur
INSERT INTO Compteur VALUES
(1,'Electricity', 100.00, 'B001'),
(2,'Water', 50.00, 'B001'),
(3,'Electricity', 100.00, 'B002'),
(4,'Water', 50.00, 'B002'),
(5,'Electricity', 100.00, 'B003'),
(6,'Water', 50.00, 'B003'),
(7,'Electricity', 100.00, 'B004'),
(8,'Water', 50.00, 'B004'),
(9,'Electricity', 100.00, 'B005'),
(10,'Water', 50.00, 'B005');

-- Releve
INSERT INTO Releve VALUES
(2023, 500, 1),
(2023, 600, 2);
COMMIT;
