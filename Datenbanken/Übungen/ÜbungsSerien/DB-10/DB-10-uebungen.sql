CREATE TABLE Hersteller (
	h_pkey INTEGER PRIMARY KEY,
	name VARCHAR(10) NOT NULL,
	adresse VARCHAR(14)
);

CREATE TABLE Futter (
	f_pkey INTEGER PRIMARY KEY,
	name VARCHAR(10) NOT NULL
);

CREATE TABLE verkauft (
	f_pkey INTEGER REFERENCES Futter NOT NULL,
	h_pkey INTEGER REFERENCES Hersteller NOT NULL,
	preis DECIMAL(4,2),
	PRIMARY KEY (f_pkey,h_pkey)	
);

CREATE TABLE Person (
	p_pkey INTEGER PRIMARY KEY,
	name VARCHAR(10) NOT NULL
);

CREATE TABLE Haustiere (
	ha_pkey INTEGER PRIMARY KEY,
	name VARCHAR(10) NOT NULL,
	gewicht DECIMAL(8,3), --kg
	art VARCHAR(10) NOT NULL,
	p_pkey INTEGER REFERENCES Person
);

CREATE TABLE mag (
	ha_pkey INTEGER REFERENCES Haustiere NOT NULL,
	f_pkey INTEGER REFERENCES Futter NOT NULL,
	grad DECIMAL(3,1),
	PRIMARY KEY (ha_pkey,f_pkey)
);

