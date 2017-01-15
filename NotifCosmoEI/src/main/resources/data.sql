INSERT INTO role
VALUES
  (2, 'ADMIN'),
  (1, 'USER' )
ON CONFLICT (id) DO NOTHING;

INSERT INTO work
VALUES
  (1, 'Médecin',                      5.0),
  (2, 'Pharmacien',                   3.0),
  (3, 'Association de consommateurs', 2.5),
  (4, 'Laboratoire',                  4.0),
  (5, 'Utilisateur simple',           1.0)
ON CONFLICT (id) DO NOTHING;

INSERT INTO region
VALUES
  (1, 'Auvergne-Rhône-Alpes'),
  (2, 'Bourgogne-Franche-Comté'),
  (3, 'Bretagne'),
  (4, 'Centre-Val de Loire'),
  (5, 'Corse'),
  (6, 'Grand Est'),
  (7, 'Hauts-de-France'),
  (8, 'Ile-de-France'),
  (9, 'Normandie'),
  (10, 'Nouvelle-Aquitaine'),
  (11, 'Occitanie'),
  (12, 'Pays de la Loire'),
  (13, 'Provence-Alpes-Côte d''Azur'),
  (14, 'Guadeloupe'),
  (15, 'Martinique'),
  (16, 'Guyane'),
  (17, 'La Réunion'),
  (18, 'Mayotte')
ON CONFLICT (id) DO NOTHING;
