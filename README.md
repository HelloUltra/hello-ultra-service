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

# 대화형

```java
//시작점 예약어
@Command(value="검색", function="search")
public void search(com.example.dto.MessageRequest messageRequest){
    return "검색어를 입력해주세요."
}

//검색하는 메서드
@Command(parent="search", function="search1")
public void search1(com.example.dto.MessageRequest messageRequest){
    return ""
}

//다음
@Command(parent={"search1","search3"}, value="다음", function="search2", increable="+page")
public void search2(com.example.dto.MessageRequest messageRequest){
    return ""
}

//이전
@Command(parent={"search1","search2"}, value="이전", increable="-page")
public void search3(com.example.dto.MessageRequest messageRequest){
    return ""
}

//상세보기
@Command(group={"search1","search2","search3"}, function="detail")
public void detail(com.example.dto.MessageRequest messageRequest){
    return ""
}

```

## Command 정보 구조

```java

//Command당 하나(ex : search1)
class Conversation {
    Map<String, String> afters;
    //#다음, search2
    //#이전, search3
    
    String query;
    //detail
    
    String findMethod(String command){
        return afters.contains(command)?afters.get(command):query;
    }
}

/** 

parent를 갖지 않은 command

@key : command
@value : function key
*/
private Map<String, String> commandMap = new HashMap<>();
// ex) "#검색" , "search"

/** 

모든 commander의 정보

@key : function key
@value : Commander class
*/
private Map<String, Commander<?>> commanderMap = new HashMap<>();
// ex)  "search" , Commander
//      "search1" , Commander
//      "search2" , Commander
//      "search3" , Commander
//      "detail" , Commander

/** 

각 command의 추후 작업에 대한 conversation을 저장

@key : function key
@value : Conversation class
*/
Map<String, Conversation> conversationMap = new HashMap<>();
//search, Conversation
//search1, Conversation

//search2, Conversation
//search3, Conversation
//detail, Conversation

```

## AOP
- Command Annotation 의 전 작업으로 ConversationInfo를 생성

```java
ConversationInfo conversationInfo = 
    CustomUtil.stringToObject(redisFunction.getLastValue(message.getUser_key()));
```

- Command Annotation 의  후 작업으로 Redis 저장작업 수행

```java
conversationInfo.setFunction(commandAnnotation.function());
//기타 조건에 의한 값 변경
commandAnnotation.increase().stream(key -> conversationInfo.plus(key)); (?이런식??)

redisFunction.push(message.getUser_key(), CustomUtil.objectToString(conversationInfo));
```

- `1. #검색`

```java
--> redis 조회 : null

if(conversationInfo == null && !startMap.containsKey(message.getContent()) ||
        conversationInfo != null && !conversationMap.containsKey(redis.getFunction())){
    return MessageResponse.FAILED;
}

Commander command = commanderMap.get(
        redis==null?
            startMap.get(message.getContent()):
            conversationMap.get(redis.getFunction()).findMethod(message.getContent())
);
--> startMap.get("#검색") -> "search" -> commanderMap.get("search") -> command

try {
    return new MessageResponse(command.execute(message), null, null);
} catch (InvocationTargetException | IllegalAccessException e) {
    return MessageResponse.FAILED;
}

```

- `2. 테스트`

```java
--> redis 조회 : {function:"search", param:null}

if(conversationInfo == null && !startMap.containsKey(message.getContent()) ||
        conversationInfo != null && !conversationMap.containsKey(redis.getFunction())){
    return MessageResponse.FAILED;
}

Commander command = commanderMap.get(
        redis==null?
            startMap.get(message.getContent()):
            conversationMap.get(redis.getFunction()).findMethod(message.getContent())
);
--> conversationMap.get("search") -> Conversation -> Conversation.findMethod("테스트") -> "search1" -> commanderMap.get("search1");

try {
    return new MessageResponse(command.execute(message), null, null);
} catch (InvocationTargetException | IllegalAccessException e) {
    return MessageResponse.FAILED;
}

```

[설계 문서](https://docs.google.com/presentation/d/1TROQwWStmRAw63icuWptnPtNBEvTloKij0txuWIv22w/edit?usp=sharing)
