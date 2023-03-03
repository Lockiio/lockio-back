INSERT INTO block (name, coordinate, status, privacy, url)
VALUES ('Lockio Paul Sabatier', POINT(21.67890, 91.54789), 'AVAILABLE', 'PUBLIC', 'http://localhost:8001/api/rasp/1/');

INSERT INTO lockio (id, block_id, local_id, size, status)
VALUES
    (1, 1, 1, 'SMALL', 'OCCUPIED'),
    (2, 1, 2, 'SMALL', 'AVAILABLE'),
    (3, 1, 3, 'MEDIUM', 'AVAILABLE'),
    (4, 1, 4, 'MEDIUM', 'AVAILABLE'),
    (5, 1, 5, 'MEDIUM', 'OCCUPIED'),
    (6, 1, 6, 'MEDIUM', 'OCCUPIED'),
    (7, 1, 7, 'LARGE', 'DISABLED'),
    (8, 1, 8, 'LARGE', 'DISABLED');

INSERT INTO block (name, coordinate, status, privacy, url)
VALUES ('Lockio Ramonville', POINT(31.67890, 81.54789), 'AVAILABLE', 'PUBLIC', "http://localhost:8002/api/rasp/1/");

INSERT INTO lockio (id, block_id, local_id, size, status)
VALUES
    (9, 2, 1, 'SMALL', 'OCCUPIED'),
    (10, 2, 2, 'SMALL', 'AVAILABLE');

INSERT INTO lockio_code (lockio_id, block_id)
SELECT l.id, b.id
FROM lockio l
         INNER JOIN block b ON l.block_id = b.id
WHERE b.name = 'Lockio Paul Sabatier';

INSERT INTO lockio_code (lockio_id, block_id)
SELECT l.id, b.id
FROM lockio l
         INNER JOIN block b ON l.block_id = b.id
WHERE b.name = 'Lockio Ramonville';