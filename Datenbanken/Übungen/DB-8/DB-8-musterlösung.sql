CREATE TABLE futter (
	hersteller VARCHAR(100),
	name VARCHAR(100),
	PRIMARY KEY (hersteller, name)
);
-- Zuerst das Allgemeine

CREATE TABLE lieblingsplatz (
	lid INTEGER PRIMARY KEY
);
-- dito

CREATE TABLE kamin (
	lid INTEGER PRIMARY KEY,
	material VARCHAR(100) NOT NULL,
	FOREIGN KEY (lid) REFERENCES lieblingsplatz (lid) ON DELETE CASCADE
);
-- Dann das Spezielle
-- Damit lieblingsplatz referenziert werden kann, muss die Tabelle existieren

CREATE TABLE karton (
	lid INTEGER PRIMARY KEY,
	FOREIGN KEY (lid) REFERENCES lieblingsplatz (lid) ON DELETE CASCADE
);
-- dito

CREATE TABLE laptop (
	lid INTEGER PRIMARY KEY,
	hersteller VARCHAR(100) NOT NULL,
	kennzeichnung VARCHAR(100),
	FOREIGN KEY (lid) REFERENCES lieblingsplatz (lid) ON DELETE CASCADE,
	CONSTRAINT "weder kamin noch karton"
	CHECK (
		NOT EXISTS ( SELECT *
			FROM karton
			WHERE karton.lid = laptop.lid
		)
		AND
		NOT EXISTS ( SELECT *
			FROM kamin
			WHERE kamin.lid = laptop.lid
		)
	)
);
-- Nun existiert alles, jetzt kann Constraint formuliert werden.

ALTER TABLE kamin ADD
	CONSTRAINT "weder laptop noch karton"
	CHECK (
		NOT EXISTS ( SELECT *
			FROM karton
			WHERE karton.lid = kamin.lid
		)
		AND
		NOT EXISTS ( SELECT *
			FROM laptop
			WHERE laptop.lid = kamin.lid
		)
);
-- die anderen Tabellen werden mit Constraint ergaenzt.

ALTER TABLE karton ADD
	CONSTRAINT "weder laptop noch kamin"
	CHECK (
		NOT EXISTS ( SELECT *
			FROM laptop
			WHERE laptop.lid = karton.lid
		)
		AND
		NOT EXISTS ( SELECT *
			FROM kamin
			WHERE kamin.lid = karton.lid
		)
	)
;
-- dito

ALTER TABLE lieblingsplatz ADD
	CONSTRAINT "nur Kamin, Karton oder Laptop"
	CHECK (
		lieblingsplatz.lid IN (
			(SELECT lid FROM kamin)
			UNION
			(SELECT lid FROM karton)
			UNION
			(SELECT lid FROM laptop)
		)
	)
;
-- nun kann auch der Constraint in lieblingsplatz gemacht werden.

CREATE TABLE haustier (
	hid INTEGER PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	tag INTEGER NOT NULL,
	monat INTEGER NOT NULL,
	jahr INTEGER NOT NULL,
	CHECK ( (tag BETWEEN 1 AND 31) AND  ( monat BETWEEN 1 AND 12))
);
-- Wieder zuerst das Allgemeine

CREATE TABLE hund (
	hid INTEGER PRIMARY KEY,
	rasse VARCHAR(100) NOT NULL,
	FOREIGN KEY (hid) REFERENCES haustier (hid) ON DELETE CASCADE
);
-- Dann das Spezielle. So klappts auch mit den Referenzen

CREATE TABLE katze (
	hid INTEGER PRIMARY KEY,
	fellfarbe VARCHAR(100) NOT NULL,
	dominanzgrad NUMERIC(2,2),
	--alternative: INTEGER mit CHECK ((dominanzgrad between 0 and 100) OR (dominanzgrad IS NULL))
	FOREIGN KEY (hid) REFERENCES haustier (hid) ON DELETE CASCADE,
	CONSTRAINT keinhund
	CHECK (
		NOT EXISTS ( SELECT *
			FROM hund
			WHERE katze.hid = hund.hid
		)
	)
);
-- dito und nun koennen auch Constraints formuliert werden

ALTER TABLE hund ADD
	CONSTRAINT keinekatze
	CHECK (
		NOT EXISTS ( SELECT *
			FROM katze
			WHERE katze.hid = hund.hid
		)
	)
;
-- bzw. die Tabellen mit Constraints ergaenzt werden

CREATE TABLE person (
	pid INTEGER PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	wohnort VARCHAR(100) NOT NULL
);
-- und wieder: Allgemeines vor Spezielllem

CREATE TABLE halter (
	pid INTEGER PRIMARY KEY,
	hid INTEGER UNIQUE NOT NULL REFERENCES haustier (hid) ON DELETE CASCADE,
	typ VARCHAR(100) NOT NULL,
	FOREIGN KEY (pid) REFERENCES person (pid) ON DELETE CASCADE
);
-- wegen der Referenzen :-)

ALTER TABLE haustier ADD
	CONSTRAINT "genau ein halter"
	CHECK (
		EXISTS ( SELECT  count(*)
			FROM halter
			GROUP BY halter.hid
			WHERE halter.hid = haustier.hid
				AND count (*) = 1
		)
	)
;
-- Und noch so ein Constraint, der jetzt gemacht werden kann

CREATE TABLE aufpasser (
	pid INTEGER PRIMARY KEY,
	stundenlohn NUMERIC(10,2),
	FOREIGN KEY (pid) REFERENCES person (pid) ON DELETE CASCADE
);
-- Da Allgemeines schon da ist, kann aufpasser darauf referenzieren

CREATE TABLE mag (
	hid INTEGER,
	hersteller VARCHAR (100),
	name VARCHAR(100),
	PRIMARY KEY (hid, hersteller, name),
	FOREIGN KEY hid REFERENCES haustier (hid) ON DELETE CASCADE,
	FOREIGN KEY (hersteller,name) REFERENCES futter (hersteller,name) ON DELETE CASCADE
);
-- Die im Kraehenfuss fehlende Tabelle kommt auch noch

ALTER TABLE haustier ADD
	CONSTRAINT "mindestens ein futter"	
	CHECK (
		EXISTS ( SELECT * FROM mag
			WHERE mag.hid = haustier.hid )
);
-- so dass nun auch noch der diesbezuegliche Constraint formuliert werden kann

CREATE TABLE hatbei (
	hid INTEGER REFERENCES haustier (hid) ON DELETE CASCADE,
	pid INTEGER REFERENCES person (pid) ON DELETE CASCADE,
	lid INTEGER NOT NULL REFERENCES lieblingsplatz (lid) ON DELETE CASCADE,
	PRIMARY KEY (hid, pid)
);
-- Da nun alle anderen Tabellen da sind, kann die Beziehung hatbei erstellt werden

ALTER TABLE haustier ADD
	CONSTRAINT "mindestens ein hatbei"	
	CHECK (
		EXISTS ( SELECT * FROM hatbei
			WHERE hatbei.hid = haustier.hid )
);
-- Schliesslich noch der letzte Constraint :-D

-- Make sure all constraints are evaluated only at the end of the transaction
SET CONSTRAINTS ALL DEFERRED;
-- ==================== THE LINE ABOVE IS IMPORTANT! =========================


