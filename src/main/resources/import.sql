INSERT INTO QUESTION (idx,title, url) VALUES (1,'question1', 'www.zum.com');
INSERT INTO QUESTION (idx,title, url) VALUES (2,'question2', 'www.daum.com');
INSERT INTO QUESTION (idx,title, url) VALUES (3,'question3', 'www.naver.com');
INSERT INTO QUESTION (idx,title, url) VALUES (4,'question4', 'www.nate.com');
INSERT INTO QUESTION (idx,title, url) VALUES (5,'question5', 'www.bing.com');
INSERT INTO QUESTION (idx,title, url) VALUES (6,'question6', 'www.google.com');

INSERT INTO TAG (idx, name) VALUES (1,'테스트');

INSERT INTO QUESTION_TAG (question_idx, tag_idx) VALUES (1,1);
INSERT INTO QUESTION_TAG (question_idx, tag_idx) VALUES (2,1);
INSERT INTO QUESTION_TAG (question_idx, tag_idx) VALUES (3,1);
INSERT INTO QUESTION_TAG (question_idx, tag_idx) VALUES (4,1);
INSERT INTO QUESTION_TAG (question_idx, tag_idx) VALUES (5,1);
INSERT INTO QUESTION_TAG (question_idx, tag_idx) VALUES (6,1);

INSERT INTO USER (IDX,NICK_NAME,USER_KEY) VALUES(1,'단데','123ABC');
