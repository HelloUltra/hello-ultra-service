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


---

# 대화형 설계

```java
//시작점 예약어
@Command(group="search", value="#검색", function="search", query="search1", after=[])
public void search(com.example.dto.MessageRequest messageRequest){
    return "검색어를 입력해주세요."
}

//검색하는 메서드
@Command(group="search", value="", function="search1", query="detail:num", after=["search2","search3"])
public void search1(com.example.dto.MessageRequest messageRequest){
    return ""
}

//다음
@Command(group="search", value="#다음", function="search2", query="detail:num", after=["search2","search3"], increable="+page")
public void search2(com.example.dto.MessageRequest messageRequest){
    return ""
}

//이전
@Command(group="search", value="#이전", function="search3", query="detail:num", after=["search2","search3"], increable="-page")
public void search3(com.example.dto.MessageRequest messageRequest){
    return ""
}

//상세보기
@Command(group="search", value="", function="detail", after=["searchAnswer","registerAnswer"])
public void detail(com.example.dto.MessageRequest messageRequest){
    return ""
}

```

# 데이터 구조

```java

//Command당 하나(ex : search1)
class Conv {
    String function;
    //search1
    
    Map<String, String> afters;
    //#다음, search2
    //#이전, search3
    
    String query;
    //detail
    
    getFunction(){
        return function;
    }
    
    String findMethod(String command){
        return afters.contains(command)?afters.get(command):query;
    }
}

//Function 캐시
Map<String, Functional> functions;
//search, Object
//search1, Object
//search2, Object
//search3, Object
//detail, Object

Map<String, Conv> convs;
//search, conv
//search1, conv
//search2, conv
//search3, conv
//detail, conv

```

- `#검색`

```java
Conv conv =command1.get(command)

//레디스 조회
//redis = null

//실행
functions.get(conv.getFunction()).execute(MessageRequest, redis);

//레디스 저장
redis.initset(conv.getFunction());
//{function : "search", param : null }

```

- `테스트`

```java

//레디스 조회
//redis = {function : "search", param : null }
conv = convs.get(redis.getFunction());

//레디스 조회
//redis = null

//실행
functions.get(conv.getFunction()).execute(MessageRequest, redis);

//레디스 저장
redis.initset(conv.getFunction());
//{function : "search", param : null }

```