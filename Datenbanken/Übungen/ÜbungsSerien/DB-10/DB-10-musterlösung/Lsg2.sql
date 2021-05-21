DROP VIEW lieblingsfutter;

CREATE VIEW lieblingsfutter AS (
	WITH
	-- mean of grade for every pairing of species and feed
	beliebt AS (
		SELECT art, f_pkey, AVG(grad) as mean
		FROM mag NATURAL JOIN haustiere
		GROUP BY art, f_pkey 
	),
	-- the best feed per species
	beste AS (
		SELECT art, f_pkey, name
		FROM beliebt b NATURAL JOIN futter
		WHERE mean >= ALL (
			SELECT mean
			FROM beliebt c
			WHERE b.art = c.art
			)
	),
	-- the cheapest manufacturer per feed
	billigste AS (
		SELECT f_pkey, h_pkey, preis
		FROM verkauft NATURAL JOIN futter AS tmp
		WHERE preis <= ALL(
			SELECT preis
			FROM verkauft
			WHERE verkauft.f_pkey = tmp.f_pkey
			)
	)

SELECT DISTINCT 
	art AS tierart, 
	beste.name AS futter, 
	hersteller.name AS hersteller, 
	preis
FROM beste LEFT OUTER JOIN 
	billigste USING (f_pkey) LEFT OUTER JOIN 
	hersteller USING(h_pkey)
)
