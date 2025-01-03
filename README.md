# 최종 코딩테스트

## ✏️ 구현 기능 목록

### ✔️ 정상 입력 처리

#### 입력

- 기능을 입력받는다.
    - 기능에 대한 유효성을 검증한다.
        - 기능은 비어있을 수 없다.
        - 기능은 1, 2, 3, 4, Q 형식이어야 한다.
- 닉네임을 입력받는다.
    - 닉네임에 대한 유효성을 검증한다.
        - 닉네임은 비어있을 수 없다.
        - 닉네임은 문자열이어야 한다.
        - 닉네임은 등록된 닉네임 내 존재해야 한다.
- 등교 시간을 입력받는다.
    - 등교 시간에 대한 유효성을 검증한다.
        - 등교 시간은 비어있을 수 없다.
        - 등교 시간은 HH:mm 형식이어야 한다.
- 수정할 날짜(일)를 입력받는다.
    - 수정할 일에 대한 유효성을 검증한다.
        - 수정한 일은 비어있을 수 없다.
        - 수정할 일은 숫자여야 한다.
        - 수정한 일은 일수여야 한다.
- 변경할 시간을 입력받는다.
    - 변경할 시간에 대한 유효성을 검증한다.
        - 변경할 시간은 비어있을 수 없다.
        - 변경할 시간은 HH:mm 형식이어야 한다.

#### 출력

- 오늘 날짜를 출력한다.
- 기능 선택 안내 메시지를 출력한다.
- 1 선택 시
    - 닉네임 입력 안내 메시지를 출력한다.
    - 등교 시간 입력 안내 메시지를 출력한다.
    - 출석 처리 완료된 메시지를 출력한다.
- 2 선택 시
    - 수정할 크루 닉네임 입력 안내 메시지를 출력한다.
    - 변경 일자 입력 안내 메시지를 출력한다.
    - 변경 시간 입력 안내 메시지를 출력한다.
    - 수정 완료 안내 메시지를 출력한다.
- 3 선택 시
    - 닉네임 입력 안내 메시지를 출력한다.
    - 닉네임에 해당하는 출석 기록을 출력한다.
    - 출석, 지각, 결석을 총합을 출력한다.
    - 면담 대상자인지 여부를 출력한다.
- 4 선택 시
    - 제적 위험자 조회 결과를 출력한다.

#### 기능

- csv 파일을 파싱한다.
- 시간은 24시간 형식만 사용한다.
- 교육 시간은 월요일 13:00-18:00, 화요일-금요일은 10:00~18:00이다.
    - 해당 요일의 시작 시각으로부터 5분 초과는 지각으로 간주한다.
    - 해당 요일의 시작 시각으로부터 30분 초과는 결석으로 간주한다.
    - 등교하지 않아 출석 기록이 없는 날은 결석으로 간주한다.
- 누적 지각 및 결석 횟수에 따라 경고 또는 면담을 시행한다. 또한 결석 횟수가 5회를 초과할 때 제적을 시행한다.
    - 지각 3회는 결석 1회로 간주한다.
        - 경고 대상자: 결석 2회 이상
        - 면담 대상자: 결석 3회 이상
        - 제적 대상자: 결석 5회 초과
- 캠퍼스 운영 시간은 매일 08:00~23:00이다.
- 주말 및 공휴일에는 출석을 받지 않는다.
- 프로그램은 사용자가 종료할 때까지 종료되지 않으며, 해당 기능을 수행한 후 초기 화면으로 돌아간다.
- 사용자가 잘못된 값을 입력할 경우 "\[ERROR]"로 시작하는 메시지와 함께 IllegalArgumentException을 발생시킨 후 애플리케이션은 종료되어야 한다.
- 이미 출석한 경우, 다시 출석할 수 없으며 수정 기능을 이용하도록 안내한다.
- 닉네임을 입력하면 전날까지의 크루 출석 기록을 확인할 수 있다.
- 전날까지의 크루 출석 기록을 바탕으로 제적 위험자를 파악한다.
- [제적 위험자는 제적 대상자, 면담 대상자, 경고 대상자순으로 출력하며, 대상 항목별 정렬 순서는 지각을 결석으로 간주하여 내림차순한다. 출석 상태가 같으면 닉네임으로 오름차순 정렬한다.

### ⚠️ 예외

IllegalArgumentException을 발생시키고, "\[ERROR]"로 시작하는 오류 메시지를 출력한다.

- 기능 선택 항목, 날짜 또는 시간을 잘못된 형식으로 입력한 경우: \[ERROR] 잘못된 형식을 입력하였습니다.
- 등록되지 않은 닉네임을 입력한 경우: \[ERROR] 등록되지 않은 닉네임입니다.
- 주말 또는 공휴일에 출석을 확인하거나 수정하는 경우: \[ERROR] 12월 14일 토요일은 등교일이 아닙니다.
- 미래 날짜로 출석을 수정하는 경우: \[ERROR] 아직 수정할 수 없습니다.
- 등교 시간이 캠퍼스 운영 시간이 아닌 경우: \[ERROR] 캠퍼스 운영 시간에만 출석이 가능합니다.
- 이미 출석을 하였는데 다시 출석 확인을 하는 경우: \[ERROR] 이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요.

<br>

## 💻 커밋 컨벤션

> [**AngularJS 커밋 컨벤션**](https://gist.github.com/stephenparish/9941e89d80e2bc58a153) 참고

| Type     | Description      |
|----------|------------------|
| feat     | 새로운 기능 추가        |
| fix      | 버그 수정            |
| docs     | 문서 변경            |
| style    | 코드 포맷 변경         |
| refactor | 코드 리팩토링          |
| test     | 테스트 추가 및 수정      |
| chore    | 빌드 작업 및 도구 관련 변경 |

<br>

## ✅ 체크 리스트

### 과제 진행 요구 사항

- [x] 우아한테크코스 출석 비공개 저장소를 생성한다.
- [ ] 우아한테크코스 계정을 collaborator로 초대한다.
- [x] 진행한 과제는 저장소의 main 브랜치에 커밋한다.
- [x] 기능을 구현하기 전에 README.md에 구현할 기능 목록을 정리해 추가한다.
- [x] Git의 커밋은 README.md에 정리된 기능 목록 단위로 나눈다. (AngularJS Git Commit Message Conventions 참고)

### 기능 요구 사항

- [x] 시간은 24시간 형식만 사용한다.
- [x] 교육 시간은 월요일 13:00~18:00, 화요일~금요일은 10:00~18:00이다.
    - [x] 해당 요일의 시작 시각으로부터 5분 초과는 지각으로 간주한다.
    - [x] 해당 요일의 시작 시각으로부터 30분 초과는 결석으로 간주한다.
    - [x] 등교하지 않아 출석 기록이 없는 날은 결석으로 간주한다.
- [x] 누적 지각 및 결석 횟수에 따라 경고 또는 면담을 시행한다. 또한 결석 횟수가 5회를 초과할 때 제적을 시행한다.
    - [x] 지각 3회는 결석 1회로 간주한다.
        - [x] 경고 대상자: 결석 2회 이상
        - [x] 면담 대상자: 결석 3회 이상
        - [x] 제적 대상자: 결석 5회 초과
- [x] 캠퍼스 운영 시간은 매일 08:00~23:00이다.
- [ ] 주말 및 공휴일에는 출석을 받지 않는다.
- [ ] 프로그램은 사용자가 종료할 때까지 종료되지 않으며, 해당 기능을 수행한 후 초기 화면으로 돌아간다.
- [ ] 사용자가 잘못된 값을 입력할 경우 "\[ERROR]"로 시작하는 메시지와 함께 IllegalArgumentException을 발생시킨 후 애플리케이션은 종료되어야 한다.
- [ ] 이미 출석한 경우, 다시 출석할 수 없으며 수정 기능을 이용하도록 안내한다.
- [ ] 닉네임을 입력하면 전날까지의 크루 출석 기록을 확인할 수 있다.
- [ ] 전날까지의 크루 출석 기록을 바탕으로 제적 위험자를 파악한다.
- [ ] 제적 위험자는 제적 대상자, 면담 대상자, 경고 대상자순으로 출력하며, 대상 항목별 정렬 순서는 지각을 결석으로 간주하여 내림차순한다. 출석 상태가 같으면 닉네임으로 오름차순 정렬한다.

### 입출력 요구 사항

#### 입력

- [x] 기능
- [x] 닉네임
- [x] 등교 시간
- [x] 수정할 날짜(일)
- [x] 변경할 시간

#### 출력

- [x] 오늘 날짜
- [x] 기능 선택 안내 메시지
- [x] 1 선택 시
    - [x] 닉네임 입력 안내 메시지
    - [x] 등교 시간 입력 안내 메시지
    - [x] 출석 처리 완료된 메시지
- [x] 2 선택 시
    - [x] 수정할 크루 닉네임 입력 안내 메시지
    - [x] 변경 일자 입력 안내 메시지
    - [x] 변경 시간 입력 안내 메시지
    - [x] 수정 완료 안내 메시지
- [x] 3 선택 시
    - [x] 닉네임 입력 안내 메시지
    - [x] 닉네임에 해당하는 출석 기록
    - [x] 출석, 지각, 결석을 총합
    - [x] 면담 대상자인지 여부
- [x] 4 선택 시
    - [x] 제적 위험자 조회 결과

### 실행 결과 예시

```
오늘은 12월 13일 금요일입니다. 기능을 선택해 주세요.
1. 출석 확인
2. 출석 수정
3. 크루별 출석 기록 확인
4. 제적 위험자 확인
Q. 종료
1

닉네임을 입력해 주세요.
이든
등교 시간을 입력해 주세요.
09:59

12월 13일 금요일 09:59 (출석)

오늘은 12월 13일 금요일입니다. 기능을 선택해 주세요.
1. 출석 확인
2. 출석 수정
3. 크루별 출석 기록 확인
4. 제적 위험자 확인
Q. 종료
2

출석을 수정하려는 크루의 닉네임을 입력해 주세요.
빙티
수정하려는 날짜(일)를 입력해 주세요.
3
언제로 변경하겠습니까?
09:58

12월 03일 화요일 10:07 (지각) -> 09:58 (출석) 수정 완료!

오늘은 12월 13일 금요일입니다. 기능을 선택해 주세요.
1. 출석 확인
2. 출석 수정
3. 크루별 출석 기록 확인
4. 제적 위험자 확인
Q. 종료
3

닉네임을 입력해 주세요.
빙티

이번 달 빙티의 출석 기록입니다.

12월 02일 월요일 13:00 (출석)
12월 03일 화요일 09:58 (출석)
12월 04일 수요일 10:02 (출석)
12월 05일 목요일 10:06 (지각)
12월 06일 금요일 10:01 (출석)
12월 09일 월요일 --:-- (결석)
12월 10일 화요일 10:08 (지각)
12월 11일 수요일 --:-- (결석)
12월 12일 목요일 --:-- (결석)

출석: 4회
지각: 2회
결석: 3회

면담 대상자입니다.

오늘은 12월 13일 금요일입니다. 기능을 선택해 주세요.
1. 출석 확인
2. 출석 수정
3. 크루별 출석 기록 확인
4. 제적 위험자 확인
Q. 종료
4

제적 위험자 조회 결과
- 빙티: 결석 3회, 지각 2회 (면담)
- 이든: 결석 2회, 지각 4회 (면담)
- 쿠키: 결석 2회, 지각 2회 (경고)
- 빙봉: 결석 1회, 지각 5회 (경고)

오늘은 12월 13일 금요일입니다. 기능을 선택해 주세요.
1. 출석 확인
2. 출석 수정
3. 크루별 출석 기록 확인
4. 제적 위험자 확인
Q. 종료
Q
```

### 프로그래밍 요구 사항

- [x] JDK 21에서 실행 가능해야 한다.
- [x] 프로그램의 시작점은 Application의 main()이다.
- [x] build.gradle 파일은 변경할 수 없으며, 제공된 라이브러리만 사용해야 한다.
- [x] 프로그램 종료 시 System.exit()를 호출하지 않는다.
- [x] 별도의 지시가 없는 한 파일, 패키지 등의 이름을 바꾸거나 이동하지 않는다.
- [x] 자바 코드 컨벤션을 준수하여 프로그래밍한다. 기본적으로 Google Java Style Guide를 따른다.
- [ ] Indent(인덴트, 들여쓰기) depth는 최대 2까지만 허용한다. (메서드를 분리하는 것을 추천)
- [x] 3항 연산자를 사용하지 않는다.
- [ ] 메서드는 한 가지 일만 하도록 최대한 작게 만든다.
- [ ] JUnit 5와 AssertJ를 사용하여 테스트 코드를 작성한다.
- [ ] 메서드 길이는 15라인을 초과하지 않는다.
- [x] else 예약어 및 switch-case 문 사용을 금지한다.
- [x] Enum을 사용하여 구현한다.
- [ ] UI 로직((System.out, System.in, Scanner)을 제외한 구현 기능에 대해 단위 테스트를 작성한다.
- [x] camp.nextstep.edu.missionutils에서 제공하는 DateTimes 및 Console API를 사용하여 구현해야 한다.
    - [x] DateTimes.now()를 사용하여 현재 날짜와 시간을 가져온다.
    - [x] Console.readLine()을 사용하여 사용자 입력을 처리한다.