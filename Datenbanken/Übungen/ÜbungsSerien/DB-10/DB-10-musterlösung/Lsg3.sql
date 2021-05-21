-- Bedingungen:
-- Es braucht von allen Entitäten mehr als eins
-- Es braucht in mag für gleiche Tierart ein Beispiel mit gleichem Grad
-- Es braucht Futter, das von keinem Hersteller produziert wird und 
--	natürlich auch solches, das von einem oder mehreren Herstellern 
--	produziert wird
-- Es braucht in verkauft ein Futter, dass von mehreren Herstellern zum 
--	gleichen Preis verkauft wird sowie eines, dass zu verschiedenen 
--	Preisen verkauft wird. In beiden Fällen sollte dies auch 
--	Lieblingsfutter sein

INSERT INTO person (p_pkey, name)
VALUES
	(1, 'medea')
;

INSERT INTO Futter (f_pkey, name)
VALUES
	(1,'Ahoi'),
	(2,'Bum'),
	(3,'Chic'),
	(4,'Diat'),
	(5,'Stroh')
;


INSERT INTO haustiere (ha_pkey, name, gewicht, art, p_pkey)
VALUES
-- Katzen für allgemeines Verhalten
	(1, 'Flo', 4, 'Katze', 1),
	(2, 'Niku', 4.5, 'Katze', 1),
	(3, 'Maja', 3.5, 'Katze', 1),

-- Hunde für keine Präferenz	
	(4, 'Trick', 6, 'Hund', 1),
	(5, 'Track', 15, 'Hund', 1),
	(6, 'Truck', 7, 'Hund', 1),

-- Schwein für fehlenden Herrsteller
	(7, 'Babe', 25, 'Schwein', 1)
	;
	
INSERT INTO hersteller (h_pkey, name, adresse)
VALUES
	(1, 'Tier GmbH', 'Bern'),
	(2, 'Wuf AG', 'Bolligen'),
	(3, 'Miau IG', 'Biel')
;

INSERT INTO mag (ha_pkey, f_pkey, grad)
VALUES
-- Katzen bevorzugen futter 3
	(1, 1, 1),
	(1, 2, 1),
	(1, 3, 3),
	(1, 4, 2),
	(1, 5, NULL),
	
	(2, 1, 3),
	(2, 2, 2),
	(2, 3, 3),
	(2, 4, 1),
	(2, 5, 3),
	
	(3, 1, 2),
	(3, 2, 2),
	(3, 3, 2),
	(3, 4, NULL),
	(3, 5, NULL),

-- Hunde bevorzugen alles ausser 1
	(4, 1, 3),
	(4, 2, NULL),
	(4, 3, NULL),
	(4, 4, NULL),
	(4, 5, NULL),
	
	(5, 1, 3),
	(5, 2, 3),
	(5, 3, 3),
	(5, 4, 3),
	(5, 5, 3),
	
	(6, 1, 2),
	(6, 2, 3),
	(6, 3, 3),
	(6, 4, 3), 
	(6, 5, 3),

-- Schwein bevorzugt 1
	(7, 1, 3),
	(7, 2, 2),
	(7, 3, 2),
	(7, 4, 2),
	(7, 5, 2)
;
	
INSERT INTO verkauft (f_pkey, h_pkey, preis)
VALUES
-- Kein Anbieter für futter 1

-- Hunde sollten für futter 2 zwei billigste Anbieter haben
	(2, 1, 15.00),
	(2, 2, 15.00),
	(2, 3, 20.00),

-- Katzen sollten nur einen billigsten Anbieter haben
	(3, 1, 10.00),
	(3, 2, 15.00),
	(3, 3, 20.00),

-- Weiteres für Hunde
	(4, 1, 13.00),
	(4, 2, 15.00),
	(5, 3, 20.00)
;
