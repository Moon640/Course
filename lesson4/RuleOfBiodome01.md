# RuleOfBiodome01 - SOLID 원칙 분석 보고서

- 작성자: [이름 입력]
- 분석 대상: `RuleOfBodome01_before.java`
- 주제: 객체 지향 프로그래밍의 SOLID 원칙과 도서관 시스템 코드 분석

> **확인 사항**
>
> 현재 제공된 대화 및 파일 보관함에서는 과제의 분석 대상인 `RuleOfBodome01_before.java` 원본 파일을 확인할 수 없었다.
> 따라서 본 보고서는 **SOLID 원칙 설명 부분은 제출 가능한 형태로 완성**하였고,
> 코드 분석 부분은 도서관 시스템에서 자주 나타나는 SOLID 위반 형태를 **예시 분석**으로 정리하였다.
> 실제 제출 전에는 반드시 교수자가 제공한 `RuleOfBodome01_before.java`의 실제 코드와 대조하여
> 아래 예시 코드 부분을 원본 코드로 교체해야 한다.

---

## 목차

1. SOLID 원칙의 등장과 필요성
2. SOLID 원칙이 객체 지향 프로그래밍에 필요한 이유
3. SOLID 다섯 가지 원칙
   - SRP: 단일 책임 원칙
   - OCP: 개방-폐쇄 원칙
   - LSP: 리스코프 치환 원칙
   - ISP: 인터페이스 분리 원칙
   - DIP: 의존 역전 원칙
4. 도서관 시스템에서 SOLID 원칙에 어긋날 수 있는 부분
5. 분석 결과 요약
6. 결론
7. 참고 자료

---

# 1. SOLID 원칙의 등장과 필요성

객체 지향 프로그래밍은 프로그램을 여러 객체로 나누고, 각 객체가 역할과 책임을 가지도록 설계하는 방식이다.
하지만 단순히 클래스를 만들고 상속이나 인터페이스를 사용하는 것만으로 좋은 객체 지향 설계가 완성되는 것은 아니다.

프로그램의 규모가 커질수록 하나의 클래스가 지나치게 많은 기능을 담당하거나,
특정 클래스끼리 강하게 의존하는 문제가 발생할 수 있다.
이런 구조에서는 작은 기능 하나를 수정했을 뿐인데 다른 코드까지 함께 수정해야 하거나,
기존 기능을 변경하는 과정에서 예상하지 못한 오류가 발생할 가능성이 높아진다.

SOLID는 이러한 문제를 줄이고 객체 간의 책임과 의존 관계를 적절하게 설계하기 위한 다섯 가지 원칙이다.
SOLID라는 이름은 다음 다섯 원칙의 첫 글자를 조합한 것이다.

- **S**: Single Responsibility Principle
- **O**: Open/Closed Principle
- **L**: Liskov Substitution Principle
- **I**: Interface Segregation Principle
- **D**: Dependency Inversion Principle

SOLID 원칙을 적용하면 클래스가 담당하는 책임을 명확하게 나눌 수 있고,
기능을 추가하거나 변경할 때 기존 코드에 미치는 영향을 줄일 수 있다.
또한 객체 사이의 강한 결합을 줄여 유지보수성과 확장성, 재사용성, 테스트 편의성을 높일 수 있다.

---

# 2. SOLID 원칙이 객체 지향 프로그래밍에 필요한 이유

## 2.1 유지보수가 쉬워진다

프로그램은 개발이 끝난 뒤에도 요구사항 변경, 오류 수정, 기능 추가가 계속 발생한다.
각 클래스가 명확한 책임을 가지고 있다면 특정 기능을 수정해야 할 때 수정 범위를 쉽게 찾을 수 있다.

반대로 하나의 클래스가 데이터 저장, 검색, 출력, 대여 처리 등 여러 기능을 모두 담당하면
하나의 기능을 변경하면서 다른 기능까지 영향을 받을 수 있다.

## 2.2 기능 확장이 쉬워진다

새로운 도서 종류, 새로운 회원 유형, 새로운 저장 방식 등이 추가될 때
기존 코드를 계속 수정하는 구조보다 새로운 구현체를 추가하는 구조가 더 안전하다.

SOLID 원칙은 기존 기능을 최대한 유지하면서 새로운 기능을 추가할 수 있도록 설계 방향을 제시한다.

## 2.3 객체 사이의 결합도를 낮출 수 있다

한 클래스가 특정 구현 클래스에 직접 의존하면 해당 구현이 변경될 때 함께 수정되어야 한다.
인터페이스나 추상 클래스와 같은 추상화에 의존하도록 설계하면 실제 구현이 바뀌더라도
상위 로직의 변경을 최소화할 수 있다.

## 2.4 테스트가 쉬워진다

각 클래스의 책임이 분리되어 있으면 기능별 단위 테스트를 작성하기 쉽다.
또한 구체적인 데이터베이스나 파일 시스템 대신 인터페이스에 의존하면
테스트용 객체를 주입하여 독립적으로 테스트할 수 있다.

## 2.5 객체 지향의 장점을 효과적으로 활용할 수 있다

객체 지향 프로그래밍의 핵심은 단순히 클래스를 많이 만드는 것이 아니라
객체가 자신의 책임을 담당하고 객체끼리 적절하게 협력하도록 만드는 것이다.
SOLID 원칙은 캡슐화, 추상화, 다형성 등의 객체 지향 특징을 실제 설계에 적용하기 위한 기준으로 활용할 수 있다.

---

# 3. SOLID 다섯 가지 원칙

## 3.1 SRP - 단일 책임 원칙

### 개념

**Single Responsibility Principle**은 하나의 클래스가 하나의 책임만 가져야 한다는 원칙이다.

조금 더 정확하게 표현하면 **클래스가 변경되어야 하는 이유는 하나여야 한다**는 의미이다.

예를 들어 `Library` 클래스 하나가 다음 기능을 모두 담당한다고 가정할 수 있다.

- 도서 등록
- 도서 검색
- 도서 대여
- 도서 반납
- 파일 저장
- 화면 출력

이 경우 도서 관리 정책이 변경되어도 `Library`를 수정해야 하고,
저장 방식이 변경되어도 `Library`를 수정해야 하며,
출력 형식이 변경되어도 같은 클래스를 수정해야 한다.

따라서 하나의 클래스에 서로 다른 책임이 지나치게 많이 포함되어 있다고 볼 수 있다.

### 개선 방향

책임에 따라 클래스를 분리할 수 있다.

```java
class LibraryService {
    // 도서 대여 및 반납과 같은 업무 처리
}

class BookRepository {
    // 도서 데이터 저장 및 조회
}

class LibraryPrinter {
    // 도서 목록 출력
}
```

이렇게 하면 각 클래스의 변경 이유가 명확해지고 한 기능의 변경이 다른 기능에 미치는 영향을 줄일 수 있다.

---

## 3.2 OCP - 개방-폐쇄 원칙

### 개념

**Open/Closed Principle**은 소프트웨어 요소가

- **확장에는 열려 있어야 하고(Open)**
- **수정에는 닫혀 있어야 한다(Closed)**

는 원칙이다.

즉 새로운 기능이 추가될 때 기존 코드를 반복적으로 고치는 것보다,
새로운 클래스를 추가하여 기능을 확장할 수 있도록 설계해야 한다.

### 위반 예시

```java
public void printBookType(Book book) {
    if (book.getType().equals("NORMAL")) {
        System.out.println("일반 도서");
    } else if (book.getType().equals("EBOOK")) {
        System.out.println("전자책");
    } else if (book.getType().equals("AUDIO")) {
        System.out.println("오디오북");
    }
}
```

새로운 도서 유형이 추가될 때마다 기존 `if-else` 코드를 수정해야 한다.

### 개선 방향

```java
interface Book {
    void printType();
}

class NormalBook implements Book {
    public void printType() {
        System.out.println("일반 도서");
    }
}

class EBook implements Book {
    public void printType() {
        System.out.println("전자책");
    }
}
```

새로운 도서 유형이 추가되어도 기존 로직을 수정하지 않고 새로운 클래스를 추가하여 확장할 수 있다.

---

## 3.3 LSP - 리스코프 치환 원칙

### 개념

**Liskov Substitution Principle**은 부모 타입의 객체를 자식 타입의 객체로 변경하더라도
프로그램의 정상적인 동작이 깨지지 않아야 한다는 원칙이다.

즉 상속 관계에서 자식 클래스는 부모 클래스가 약속한 동작을 지켜야 한다.

### 위반 예시

```java
class Book {
    public void borrow() {
        System.out.println("도서를 대여합니다.");
    }
}

class ReferenceBook extends Book {
    @Override
    public void borrow() {
        throw new UnsupportedOperationException("참고 도서는 대여할 수 없습니다.");
    }
}
```

`ReferenceBook`은 `Book`의 하위 타입이지만 부모의 `borrow()` 동작을 정상적으로 수행하지 못한다.

```java
Book book = new ReferenceBook();
book.borrow();
```

부모 타입 `Book`을 사용할 수 있다고 생각한 코드에서 예외가 발생하므로
부모 객체를 자식 객체로 안전하게 치환할 수 없다.

### 개선 방향

대여 가능한 도서와 대여 불가능한 자료의 역할 자체를 분리하는 것이 적절하다.

```java
interface LibraryItem {
    String getTitle();
}

interface Borrowable {
    void borrow();
}
```

대여 가능한 자료만 `Borrowable`을 구현하도록 만들면 상속 관계의 의미가 명확해진다.

---

## 3.4 ISP - 인터페이스 분리 원칙

### 개념

**Interface Segregation Principle**은 클라이언트가 자신이 사용하지 않는 메서드에
의존하도록 강요받아서는 안 된다는 원칙이다.

하나의 거대한 인터페이스에 모든 기능을 넣기보다
역할별로 작은 인터페이스를 만드는 것이 좋다.

### 위반 예시

```java
interface LibraryItem {
    void borrow();
    void returnItem();
    void download();
    void playAudio();
}
```

일반 종이책은 `download()`나 `playAudio()`가 필요하지 않을 수 있다.
하지만 위 인터페이스를 구현하려면 사용하지 않는 기능까지 강제로 구현해야 한다.

### 개선 방향

```java
interface Borrowable {
    void borrow();
    void returnItem();
}

interface Downloadable {
    void download();
}

interface Playable {
    void playAudio();
}
```

각 객체가 실제로 필요한 인터페이스만 구현하도록 분리할 수 있다.

---

## 3.5 DIP - 의존 역전 원칙

### 개념

**Dependency Inversion Principle**은 상위 수준의 모듈이 하위 수준의 구체적인 구현에
직접 의존하지 않고, 양쪽 모두 추상화에 의존해야 한다는 원칙이다.

또한 추상화가 세부 구현에 의존하는 것이 아니라
세부 구현이 추상화에 의존하도록 설계해야 한다.

### 위반 예시

```java
class LibraryService {
    private FileBookRepository repository = new FileBookRepository();

    public void save(Book book) {
        repository.save(book);
    }
}
```

`LibraryService`가 `FileBookRepository`라는 구체 클래스에 직접 의존하고 있다.

만약 파일 저장 방식에서 데이터베이스 저장 방식으로 변경하면
`LibraryService`의 코드도 직접 수정해야 한다.

### 개선 방향

```java
interface BookRepository {
    void save(Book book);
}

class FileBookRepository implements BookRepository {
    public void save(Book book) {
        // 파일 저장
    }
}

class LibraryService {
    private final BookRepository repository;

    public LibraryService(BookRepository repository) {
        this.repository = repository;
    }
}
```

`LibraryService`는 구체 클래스가 아니라 `BookRepository` 인터페이스에 의존한다.
따라서 파일, 데이터베이스, 메모리 저장 방식 등 다양한 구현으로 쉽게 교체할 수 있다.

---

# 4. 도서관 시스템에서 SOLID 원칙에 어긋날 수 있는 부분

> 아래 코드는 `RuleOfBodome01_before.java` 원본을 확보하지 못한 상태에서 작성한 **분석 예시**이다.
> 실제 과제 제출 시에는 반드시 원본 파일의 해당 코드를 그대로 인용하여 교체해야 한다.

---

## 4.1 위반 사례 1 - 하나의 클래스가 여러 책임을 수행하는 경우

### 예시 코드

```java
class Library {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public Book searchBook(String title) {
        // 도서 검색
        return null;
    }

    public void lendBook(Book book) {
        // 대여 처리
    }

    public void saveToFile() {
        // 파일 저장
    }

    public void printBooks() {
        // 화면 출력
    }
}
```

### 위반 원칙

**SRP - 단일 책임 원칙**

### 위반 이유

`Library` 클래스가 도서 관리뿐 아니라 검색, 대여 처리, 데이터 저장, 화면 출력까지
서로 다른 여러 책임을 동시에 가지고 있다.

도서 관리 정책이 변경될 때도 이 클래스를 수정해야 하고,
파일 저장 방식이나 출력 형식이 변경될 때도 같은 클래스를 수정해야 한다.

즉 하나의 클래스가 변경되어야 하는 이유가 여러 개이기 때문에 SRP에 어긋난다.

### 개선 방향

다음과 같이 역할을 분리할 수 있다.

- `LibraryService`: 도서 대여 및 반납
- `BookRepository`: 도서 저장 및 조회
- `BookSearchService`: 검색
- `LibraryPrinter`: 출력

---

## 4.2 위반 사례 2 - 새로운 종류가 추가될 때 기존 조건문을 수정해야 하는 경우

### 예시 코드

```java
public int getLoanPeriod(Book book) {
    if (book.getType().equals("NORMAL")) {
        return 14;
    } else if (book.getType().equals("MAGAZINE")) {
        return 7;
    } else if (book.getType().equals("EBOOK")) {
        return 30;
    }

    return 0;
}
```

### 위반 원칙

**OCP - 개방-폐쇄 원칙**

### 위반 이유

새로운 도서 유형이 생길 때마다 기존 `getLoanPeriod()` 메서드 내부에
`if-else` 조건을 추가해야 한다.

예를 들어 `AUDIO_BOOK`이 추가되면 기존 메서드 자체를 수정해야 한다.
따라서 기능 확장을 위해 이미 작성된 코드를 계속 수정해야 하므로 OCP에 어긋난다.

### 개선 방향

각 도서 객체가 자신의 대여 기간을 반환하도록 다형성을 사용할 수 있다.

```java
interface BorrowableBook {
    int getLoanPeriod();
}

class NormalBook implements BorrowableBook {
    public int getLoanPeriod() {
        return 14;
    }
}

class EBook implements BorrowableBook {
    public int getLoanPeriod() {
        return 30;
    }
}
```

새로운 도서가 추가되어도 기존 코드를 변경할 필요가 줄어든다.

---

## 4.3 위반 사례 3 - 하위 클래스가 부모 클래스의 기능을 정상적으로 수행하지 못하는 경우

### 예시 코드

```java
class Book {
    public void borrow() {
        System.out.println("대여되었습니다.");
    }
}

class ReferenceBook extends Book {
    @Override
    public void borrow() {
        throw new UnsupportedOperationException();
    }
}
```

### 위반 원칙

**LSP - 리스코프 치환 원칙**

### 위반 이유

`ReferenceBook`은 `Book`을 상속받았으므로 일반적으로 `Book`이 사용되는 위치에서
대체할 수 있어야 한다.

그러나 `ReferenceBook` 객체에서 `borrow()`를 호출하면 예외가 발생한다.

```java
Book book = new ReferenceBook();
book.borrow();
```

따라서 부모 타입이 보장하던 동작을 자식 타입이 지키지 못하고 있으며,
부모 객체를 자식 객체로 안전하게 치환할 수 없으므로 LSP 위반으로 볼 수 있다.

### 개선 방향

모든 자료가 대여 가능한 것이 아니라면 `borrow()`를 부모 클래스의 공통 기능으로 두지 않고
`Borrowable` 인터페이스를 별도로 만드는 것이 적절하다.

---

## 4.4 위반 사례 4 - 필요하지 않은 기능까지 구현하게 하는 인터페이스

### 예시 코드

```java
interface LibraryItem {
    void borrow();
    void returnItem();
    void download();
    void playAudio();
}
```

```java
class PaperBook implements LibraryItem {
    public void borrow() {
        // 대여
    }

    public void returnItem() {
        // 반납
    }

    public void download() {
        throw new UnsupportedOperationException();
    }

    public void playAudio() {
        throw new UnsupportedOperationException();
    }
}
```

### 위반 원칙

**ISP - 인터페이스 분리 원칙**

### 위반 이유

`PaperBook`은 종이책이기 때문에 다운로드 기능이나 오디오 재생 기능이 필요하지 않다.
하지만 `LibraryItem` 인터페이스가 너무 많은 기능을 포함하고 있어
사용하지 않는 메서드까지 강제로 구현해야 한다.

이처럼 클라이언트가 필요하지 않은 기능에 의존하게 만드는 구조는 ISP에 어긋난다.

### 개선 방향

```java
interface Borrowable {
    void borrow();
    void returnItem();
}

interface Downloadable {
    void download();
}

interface Playable {
    void playAudio();
}
```

객체가 필요한 기능만 선택적으로 구현하도록 인터페이스를 역할별로 분리하는 것이 좋다.

---

## 4.5 위반 사례 5 - 상위 서비스가 구체적인 저장 구현에 직접 의존하는 경우

### 예시 코드

```java
class LibraryService {
    private FileBookRepository repository;

    public LibraryService() {
        repository = new FileBookRepository();
    }
}
```

### 위반 원칙

**DIP - 의존 역전 원칙**

### 위반 이유

`LibraryService`는 도서관의 주요 업무를 처리하는 상위 수준의 클래스이다.
그런데 내부에서 `FileBookRepository`라는 구체적인 하위 구현을 직접 생성하고 있다.

저장 방식을 파일에서 데이터베이스로 변경하려면
`LibraryService`의 내부 코드까지 변경해야 한다.

즉 상위 모듈이 하위 수준의 세부 구현에 강하게 결합되어 있으므로 DIP에 어긋난다.

### 개선 방향

```java
interface BookRepository {
    void save(Book book);
}

class LibraryService {
    private final BookRepository repository;

    public LibraryService(BookRepository repository) {
        this.repository = repository;
    }
}
```

상위 서비스가 추상화된 `BookRepository`에만 의존하도록 하면
구체적인 저장 방식은 외부에서 자유롭게 교체할 수 있다.

---

# 5. 분석 결과 요약

| 번호 | 문제 상황 | 위반 원칙 | 핵심 이유 |
|---|---|---|---|
| 1 | 하나의 클래스가 관리·검색·저장·출력까지 담당 | SRP | 클래스의 변경 이유가 여러 개임 |
| 2 | 새로운 도서 종류마다 기존 `if-else` 수정 | OCP | 확장할 때 기존 코드 수정이 필요함 |
| 3 | 하위 클래스가 부모의 대여 기능을 수행하지 못함 | LSP | 부모 객체를 자식 객체로 안전하게 치환할 수 없음 |
| 4 | 모든 자료가 불필요한 메서드까지 구현 | ISP | 사용하지 않는 인터페이스 기능에 강제로 의존함 |
| 5 | 서비스가 구체적인 저장 클래스에 직접 의존 | DIP | 상위 모듈이 하위 구현에 강하게 결합됨 |

과제에서는 **3개 이상**을 요구하고 있으므로,
실제 `RuleOfBodome01_before.java`에서 명백하게 확인되는 위반 사항을 최소 3개 선택하여
코드와 함께 설명하면 된다.

---

# 6. 결론

SOLID는 객체 지향 프로그램을 무조건 복잡하게 나누기 위한 규칙이 아니라,
프로그램이 변경되고 확장될 때 발생하는 문제를 줄이기 위한 설계 원칙이다.

SRP는 객체의 책임을 명확하게 만들고,
OCP는 기존 코드의 변경을 최소화하면서 새로운 기능을 확장할 수 있도록 한다.
LSP는 올바른 상속과 다형성을 유지하도록 하며,
ISP는 필요하지 않은 기능에 객체가 의존하는 것을 방지한다.
DIP는 구체적인 구현보다 추상화에 의존하게 하여 객체 사이의 결합도를 낮춘다.

도서관 시스템과 같이 도서 종류, 회원 정책, 대여 방식, 데이터 저장 방식 등이
계속 변경될 가능성이 있는 프로그램에서는 SOLID 원칙이 특히 중요하다.

SOLID 원칙을 적용하면 단순히 현재 동작하는 프로그램을 만드는 것을 넘어,
새로운 요구사항이 생겨도 기존 기능에 미치는 영향을 줄이고
유지보수하기 쉬운 객체 지향 프로그램을 설계할 수 있다.

---

# 7. 참고 자료

1. Robert C. Martin, **Principles of Object Oriented Design**  
   https://butunclebob.com/ArticleS.UncleBob.PrinciplesOfOod

2. Barbara Liskov, Jeannette M. Wing, **A Behavioral Notion of Subtyping**, ACM TOPLAS, 1994  
   https://www.cs.cmu.edu/~wing/publications/LiskovWing94.pdf

---

## 제출 전 체크리스트

- [ ] 파일명이 `RuleOfBiodome01.md`인지 확인
- [ ] 작성자명 입력
- [ ] `RuleOfBodome01_before.java` 실제 코드 확보
- [ ] 4장의 예시 코드를 실제 제공 코드로 교체
- [ ] 실제 코드에서 SOLID 위반 사례 3개 이상 확인
- [ ] 각 사례마다 어떤 SOLID 원칙인지 명시
- [ ] 왜 해당 원칙을 위반하는지 설명
- [ ] 마크다운 코드 블록 문법 확인
