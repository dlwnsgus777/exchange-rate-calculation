## 환율 계산기

---

### 프로젝트 정보

- Java 11 버전 
- Spring Boot 2.7.11
- H2 Database
- thymeleaf
- mockserver

### 기능 요구 사항

- [x] 송금국가는 미국(USD)으로 고정
  - [x] 송금액은 USD로 입력
  - [x] Submit 버튼 클릭시 수취금액이 화면에 표시된다.
- [x] 수취 국가는 한국, 일본, 필리핀 세 군데 중 하나
  - [x] select box로 선택한다.
  - [x] 수취국가 선택시 화면의 환율이 바뀌어야 한다.
    - [x] 1 USD 기준으로 각각의 단위에 대응하는 금액
- [x] 환율과 수취금액은 소숫점 2째자리까지, 3자리 이상되면 콤마를 찍는다.
  - 1234 -> 1,234.00 으로 나타낸다.

---

### Api 설정

```
# application.yml 

api:
  exchange_api_key: /* 발급받은 key 입력 */
```





