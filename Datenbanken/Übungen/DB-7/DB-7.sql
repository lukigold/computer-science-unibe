--a
SELECT DISTINCT Filialleiter FROM filiale WHERE fid IN 
	(SELECT fid FROM artikel, lieferant WHERE name = 'Druckwerk Trallala');
	
--b
SELECT * FROM kunde WHERE kid IN
	(SELECT kid FROM kauft Where aid NOT IN
		(SELECT aid FROM artikel, lieferant WHERE land = 'Schweiz' AND artikel.lid = lieferant.lid));

--c
 SElECT * FROM kunde WHERE kid IN
	(SELECT kid FROM kauft, artikel WHERE kauft.aid = artikel.aid AND typid IN 
		(SELECT typid FROM artikeltyp WHERE typid IN 
			(SELECT typid FROM bietetan, lieferant WHERE lieferant.lid = bietetan.lid AND land = 'Schweiz')));

--d
SELECT bezeichnung, COUNT(*) FROM artikeltyp, artikel WHERE artikeltyp.typid = artikel.typid AND aid IN
	(SELECT aid FROM kauft) GROUP BY (Bezeichnung);
	
--e
SELECT kid, SUM(preis) FROM (SELECT preis, aid FROM artikel, artikeltyp WHERE artikel.typid = artikeltyp.typid)
		AS temp, kauft WHERE kauft.aid = temp.aid GROUP BY(kid, preis) ORDER BY (kid);