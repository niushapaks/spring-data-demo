INSERT INTO student(id, name) VALUES (1, 'Ajax');
INSERT INTO student(id, name) VALUES (2, 'Achille');
INSERT INTO student(id, name) VALUES (3, 'Tyro');
INSERT INTO student(id, name) VALUES (4, 'Patrocle');
INSERT INTO student(id, name) VALUES (5, 'Agamemnon');
INSERT INTO student(id, name) VALUES (6, 'Tirésias');
INSERT INTO student(id, name) VALUES (7, 'Tityos');
INSERT INTO student(id, name) VALUES (8, 'Anticlée');
INSERT INTO student(id, name) VALUES (9, 'Sisyphe');
INSERT INTO student(id, name) VALUES (10, 'Tantale');

INSERT INTO course(id, name) VALUES (1, 'Wood-Horse construction');
INSERT INTO course(id, name) VALUES (2, 'Aegean Sea navigation');

INSERT INTO registration(id, course_id, student_id) VALUES (1, 1, 1);
INSERT INTO registration(id, course_id, student_id) VALUES (2, 1, 2);
INSERT INTO registration(id, course_id, student_id) VALUES (3, 1, 4);
INSERT INTO registration(id, course_id, student_id) VALUES (4, 2, 8);
INSERT INTO registration(id, course_id, student_id) VALUES (5, 2, 10);
INSERT INTO registration(id, course_id, student_id) VALUES (6, 2, 7);
INSERT INTO registration(id, course_id, student_id) VALUES (7, 2, 5);
INSERT INTO registration(id, course_id, student_id) VALUES (8, 2, 6);