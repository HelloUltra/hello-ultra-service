<<<<<<< HEAD
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
=======
INSERT INTO QUESTION (idx,title, url, content) VALUES (1,'question1', 'www.zum.com', '줌 내용');
INSERT INTO QUESTION (idx,title, url, content) VALUES (2,'question2', 'www.daum.com', '다음 내용');
INSERT INTO QUESTION (idx,title, url, content) VALUES (3,'question3', 'www.naver.com', '네이버 내용');
INSERT INTO QUESTION (idx,title, url, content) VALUES (4,'question4', 'www.nate.com', '네이트 내용');
INSERT INTO QUESTION (idx,title, url, content) VALUES (5,'question5', 'www.bing.com', '빙 내용');
INSERT INTO QUESTION (idx,title, url, content) VALUES (6,'question6', 'www.google.com', '구글 내용');

INSERT INTO TAG (idx, name) VALUES (1,'테스트');

INSERT INTO QUESTION_TAG (question_idx, tag_idx) VALUES (1,1);
INSERT INTO QUESTION_TAG (question_idx, tag_idx) VALUES (2,1);
INSERT INTO QUESTION_TAG (question_idx, tag_idx) VALUES (3,1);
INSERT INTO QUESTION_TAG (question_idx, tag_idx) VALUES (4,1);
INSERT INTO QUESTION_TAG (question_idx, tag_idx) VALUES (5,1);
INSERT INTO QUESTION_TAG (question_idx, tag_idx) VALUES (6,1);

INSERT INTO USER (IDX,NICK_NAME,USER_KEY) VALUES(1,'단데','123ABC');
>>>>>>> c2bf575efad53ca62fa812a86c34f803b81f72f7
