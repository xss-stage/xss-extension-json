<img src = "https://raw.githubusercontent.com/xss-stage/.github/e5a1bfbfb1882be45ba42c58b27218830015004a/Frame%206.svg" width = "450" height = "auto"/>   

#  
   
[***Press Star***](https://github.com/xss-stage/xss-core/stargazers)   
   
[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2Fxss-stage&count_bg=%23FF4848&title_bg=%232D2D2D&icon=&icon_color=%23E7E7E7&title=hits&edge_flat=false)](https://hits.seeyoufarm.com) [![made with love](https://camo.githubusercontent.com/c6c5b56fc051557203c6dffa4242b41b09ff22f6303da15e47162a5c1691e8a5/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4d616465253230776974682d4c6f76652d2d2545322539442541342d726564)](https://camo.githubusercontent.com/c6c5b56fc051557203c6dffa4242b41b09ff22f6303da15e47162a5c1691e8a5/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4d616465253230776974682d4c6f76652d2d2545322539442541342d726564)  
![needed jdk version](https://img.shields.io/badge/JDK-8-blue)   
![api-version](https://img.shields.io/badge/xss--extension--json-1.2.1-F29494)   

이 라이브러리는 xss-core에 json을 처리할 수 있는 XssFilter구현체를 등록시키며, Json을 객체로 바꾸는 상황에서 사용할 수 있습니다.    

주의할점은, 이 라이브러리를 이용해서 LocalDate, LocalDateTime과 같은 필드를 포함하는 객체를 파싱하려고 하는 경우, (Xss필터링을 하려는 객체에 LocalDate, LocalDateTime이 있는 경우, (@JsonSerialize(using = LocalDateSerializer.class)해당하는 field에 @JsonDeserialize(using = LocalDateDeserializer.class)를 명시해야합니다.)Json의 key에 Xss 필터링 대상 문자가 있다면, key또한 점 입니다.     
이 라이브러리를 주입받으면, 다음과 같이 `@Xss`의 value로 `json`을 지정할 수 있습니다.   

``` Java
    @Xss("json")
```

자세한 사용법은 [여기](https://github.com/xss-stage)를 참조하세요.
