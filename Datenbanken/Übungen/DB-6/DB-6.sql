--Lukas Ingold 20-123-998
--Florin Achermann 20-122-131
--Merlin Streilein 20-118-402
--AUFGABE 2
--Create table's
CREATE TABLE Angestellte (
    PersonalNR int,
    "Name" varchar(50),
    Vorname varchar(50),
	Telefon varchar(20),
	"akad Grad" varchar(50),
	Typ varchar(50));
CREATE TABLE Vorlesungen (
    VorlesungsNR int,
    Titel varchar(50),
    ECTS int,
	Semester varchar(10));
CREATE TABLE HaeltBetreut (
    VorlesungsNR int,
    PersonalNR int);
--Inserting info in tables
INSERT INTO Angestellte VALUES
(123, 'Zauder', 'Peer', '0123-1235', 'Prof. Dr.', 'Professor'),
(121, 'Prau', 'Hans', '0123-1125', 'Prof. Dr. hc.mult. Dr. ing. habil.', 'Professor'),
(171, 'Main', 'Willi', '0121-1123', 'Dipl.-Inf.', 'Assistent'),
(176, 'Meier', 'Hans', '0123-1124', 'Dipl.-Math.', 'Assistent'),
(178, 'Meier', 'Georg', '0123-83646', 'M.sc.', 'Assistent'),
(179, 'Meier', 'Karl', '0123-32546', 'M.sc.', 'SHK');

INSERT INTO Vorlesungen VALUES
(121, 'Programmieren', 4, 'hs10'),
(124, 'Datenbanken', 5, 'fs10'),
(128, 'Datenbanken', 4, 'fs11'),
(123, 'Programmieren', 4, 'hs11'),
(127, 'Programmieren', 4, 'hs12'),
(129, 'Datenbanken', 4, 'fs12'),
(135, 'Automatentheorie', 4, 'fs14');
INSERT INTO HaeltBetreut VALUES
(121, 121),
(124, 123),
(128, 121),
(123, 123),
(127, 123),
(129, 121),
(135, 121),
(121, 171),
(121, 176),
(127, 178),
(129, 176),
(135, 171);
--AUFGABE 3
--3A) Alle Assistenten, die jemals die Vorlesung Programmieren betreut haben.
SELECT * FROM Angestellte
WHERE Typ='Assistent' AND PersonalNr IN
(SELECT PersonalNr FROM haeltbetreut WHERE
VorlesungsNr=121 OR VorlesungsNr=123 OR VorlesungsNr=127);
--3B) Die Titel und ECTS aller Vorlesungen in Frühsemester ’11.
SELECT Titel, ECTS FROM Vorlesungen WHERE Semester = 'fs11';
--3C) Die ECTS und Titel derjenigen Vorlesungstypen, welche von Prof. Zauder gehalten wurden. (gleichartige Vorlesungen sollten nur einmal aufgeführt werden.)
SELECT DISTINCT ECTS, Titel FROM Vorlesungen WHERE VorlesungsNr IN
(SELECT Vorlesungsnr FROM HaeltBetreut WHERE PersonalNr IN (SELECT PersonalNr FROM Angestellte WHERE "Name" = 'Peer'));
