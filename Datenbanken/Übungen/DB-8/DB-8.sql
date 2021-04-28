
CREATE TABLE Haustier (
  hid serial,
  Name varchar(50) NOT NULL,
  GebTag int NOT NULL,
  GebMonat int NOT NULL,
  GebJahr int NOT NULL,
  pid int NOT NULL,
  FOREIGN KEY (pid),
  PRIMARY KEY (hid));

CREATE TABLE Hund (
  hid int PRIMARY KEY,
  Rasse varchar(50) NOT NULL,
  FOREIGN KEY (hid) REFERENCES Haustier(hid) ON DELETE CASCADE);

CREATE TABLE Katze (
  hid int PRIMARY KEY,
  Dominanz varchar(50) NOT NULL,
  Fellfarbe varchar(50) NOT NULL,
  CHECK( Dominanz <= 100),
  CHECK( Dominanz >= 0),
  FOREIGN KEY (hid) REFERENCES Haustier(hid) ON DELETE CASCADE);

CREATE TABLE Futter (
  Hersteller varchar(50) NOT NULL,
  Name varchar(50) NOT NULL,
  PRIMARY KEY (Hersteller, Name));

CREATE TABLE mag (
  hid int NOT NULL,
  Hersteller varchar(50) NOT NULL,
  Name varchar(50) NOT NULL,
  FOREIGN KEY (hid) REFERENCES Haustier(hid) ON DELETE CASCADE,
  FOREIGN KEY (Hersteller, Name) REFERENCES Futter(Hersteller, Name)
);

CREATE TABLE Person (
  pid serial,
  Name varchar(50) NOT NULL,
  Wohnort varchar(50) NOT NULL,
  PRIMARY KEY (pid));

CREATE TABLE Halter (
  pid int PRIMARY KEY,
  Typ varchar(50) NOT NULL,
  FOREIGN KEY (pid) REFERENCES Person(pid) ON DELETE CASCADE);

CREATE TABLE gehört(
  pid int NOT NULL,
  hid int NOT NULL,
  FOREIGN KEY (hid) REFERENCES Haustier(hid) ON DELETE CASCADE,
  FOREIGN KEY (pid) REFERENCES Person(pid)
);
CREATE TABLE Aufpasser (
  pid int PRIMARY KEY,
  Stundenlohn int,
  FOREIGN KEY (pid) REFERENCES Person(pid) ON DELETE CASCADE
);

CREATE TABLE Freiwilliger(
  pid int PRIMARY KEY,
  Stundenlohn NULL,
  FOREIGN KEY (pid) REFERENCES Person(pid) ON DELETE CASCADE
);

CREATE TABLE Lieblingsplatz (
  lid serial,
  PRIMARY KEY (lid));
  
CREATE TABLE "hat-bei"(
  hid int NOT NULL,
  pid int NOT NULL,
  lid int NOT NULL,
  FOREIGN KEY (hid) REFERENCES Haustier(hid) ON DELETE CASCADE,
  FOREIGN KEY (pid) REFERENCES Person(pid),
  FOREIGN KEY (lid) REFERENCES Lieblingsplatz(lid));

CREATE TABLE Kamin (
  lid int PRIMARY KEY,
  Material varchar(50) NOT NULL,
  FOREIGN KEY (lid) REFERENCES Lieblingsplatz(lid) ON DELETE CASCADE);

CREATE TABLE Laptop (
  lid int PRIMARY KEY,
  Hersteller varchar(50) NOT NULL,
  Kennzeichnung varchar(50),
  FOREIGN KEY (lid) REFERENCES Lieblingsplatz(lid) ON DELETE CASCADE);

CREATE TABLE Karton (
  lid int PRIMARY KEY,
  FOREIGN KEY (lid) REFERENCES Lieblingsplatz(lid) ON DELETE CASCADE);