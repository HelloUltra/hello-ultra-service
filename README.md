# Hello Ultra

## 기능

`검색 {keyword}`

```
ex)
[6]question6(ultra.com/6)
[5]question5(ultra.com/5)
[4]question4(ultra.com/4)
```

`상세보기 {question_idx}`

```
ex)
title : 스프링 시큐리티
내용 : 시큐리티의 원리가 궁금합니다~
```

`답변검색 {question_idx}`

```
ex)
게시물 : ultra.com/{question_idx}
[3] 제 생각에는 버전을 ...
[7] 안녕하세요. 저는 JDK가..
```

`답변 상세보기 {answer_idx}`

```
ex)
게시물 : ultra.com/{question_idx}
게시자 : kingbbode님
내용 :
~~~~
제 생각에는 버전을 업데이트 하시는게 좋을 것 같습니다~~~
~~~~
```

`답변 {question_idx} {내용}`

```
ex)
게시물 : ultra.com/{question_idx}
답변이 등록되었습니다.
```

## 회원

`고유키 조회 기능` : 익명사용자의 고유키 -> 회원가입시 작성 필요

<mysql>
idx, id, pass, nickname

nosql(cache)

'redis'
/{api}
{api user id} , {question: [1,3,5,6], answer : [2,6,8], hash : '{hash}' }, -> expire(7일)

/{api}/hash
{hash}, {api user id}

batch : 익명 사용자의 데이터를 가입된 사용자 데이터로 전환.

