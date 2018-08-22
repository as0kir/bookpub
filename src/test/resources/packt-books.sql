--DELETE FROM book where author_id = 5;
--DELETE FROM author where id = 5;
INSERT INTO author (id, first_name, last_name) VALUES (5,'Shrikrishna', 'Holla');
INSERT INTO book (isbn, title, author_id, publisher_id) VALUES ('978-1-78398-478-7', 'Orchestrating Docker', 5, 1);