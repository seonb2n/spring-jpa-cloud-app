# toodletoodle_spring_server
spring boot 활용해서 일정 관리 앱의 서버를 만든 프로젝트입니다.
MySql 기반으로 작동합니다.

## 특징
나름대로 공부한 DDD 를 적용해보고자 했습니다.
<br>
 -> Domain, Application, Infrastructure, Interface 4가지 레이어를 분리해서 작동합니다.
 <br>
 -> Entity 를 외부로 노출 시키지 않고 내부적으로도 Entity 를 직접 건드리는 일은 최소화했습니다.
 
테스트를 넣어놨습니다.
<br>
 -> Json 형식으로 데이터를 주고 받았습니다.
 
읽기 쉽게 코드를 작성하려고 했습니다.
<br>
 -> 하나의 메서드는 하나의 기능만 수행하고, Interface, Application, IntfraStructure 를 따라 데이터가 흐르는 구조를 볼 수 있습니다.

<br>
API 문서 주소
<br>
https://seonbin-kim.gitbook.io/toodle-server_spring/
