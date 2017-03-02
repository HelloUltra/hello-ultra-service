INSERT INTO USER (user_idx,nick_name,user_key) VALUES(1,'단데','123ABC');
INSERT INTO QUESTION (question_idx,writer_user_idx,title, url) VALUES (1,1,'question', 'https://slipp.net/questions/tagged/spring-boot');
INSERT INTO TAG (tag_idx,tag_name) VALUES (1,'spring-boot');
INSERT INTO QUESTION_TAGS(tag_idx,question_idx) VALUES (1,1);

