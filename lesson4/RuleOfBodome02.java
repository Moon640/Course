import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SOLID 원칙을 반영하여 설계한 도서관 시스템.
 *
 * 프로젝트 및 메인 클래스 이름: RuleOfBodome02
 */
public class RuleOfBodome02 {

    public static void main(String[] args) {
        Library library = new Library();

        Manager manager = new Manager("M001", "세이코");
        Member member1 = new Member("U001", "메리");
        Member member2 = new Member("U002", "만옥");

        // 1. 관리자 1명, 이용자 2명을 도서관에 등록한다.
        library.registerManager(manager);
        library.registerMember(member1);
        library.registerMember(member2);

        System.out.println();

        // 2. 관리자가 책 5권을 등록한다.
        Book book1 = new Book("자바의 구름", "제임스밥", "ISBN-001");
        Book book2 = new Book("파이썬 마스터", "한송희", "ISBN-002");
        Book book3 = new Book("에너지 플로우", "키네틱스", "ISBN-003");
        Book book4 = new Book("화성에서의 기억", "한송희", "ISBN-004");
        Book book5 = new Book("야채의 비밀", "송은정", "ISBN-005");

        manager.addBook(library, book1);
        manager.addBook(library, book2);
        manager.addBook(library, book3);
        manager.addBook(library, book4);
        manager.addBook(library, book5);

        System.out.println();

        // 3. 이용자 1명이 책을 1권 대출한다.
        member1.borrowBook(library, book1);

        System.out.println();

        // 4. 관리자가 책을 2권 추가한다.
        Book book6 = new Book("자료구조의 언덕", "황수", "ISBN-006");
        Book book7 = new Book("그곳에 가면", "한송희", "ISBN-007");

        manager.addBook(library, book6);
        manager.addBook(library, book7);

        System.out.println();

        // 5. 이미 대출한 책을 다른 이용자가 대출 시도한다.
        member2.borrowBook(library, book1);

        System.out.println();

        // 6. 이용자 1명이 빌린 책을 반납한다.
        member1.returnBook(library, book1);

        System.out.println();

        // 7. 관리자가 책을 대출한다.
        manager.borrowBook(library, book4);

        System.out.println();

        // 8. 저자명으로 책을 검색한다.
        library.searchBooksByAuthor("한송희");

        /*
         * 아래 코드는 과제에서 요구한 예외 상황이 실제로 처리되는지
         * 확인하기 위한 추가 시뮬레이션이다.
         */
        System.out.println();
        System.out.println("===== 예외 처리 시뮬레이션 =====");

        // 예외 1: 등록되지 않은 책을 반납하는 경우
        Book unregisteredBook =
                new Book("등록되지 않은 책", "알 수 없는 저자", "ISBN-999");
        member1.returnBook(library, unregisteredBook);

        // 예외 2: 검색 결과 해당하는 책이 없는 경우
        library.searchBooksByAuthor("없는저자");

        // 예외 3: 대출 중인 책을 삭제하는 경우
        manager.removeBook(library, book4);

        System.out.println();
        System.out.println("===== 전체 책 정보 =====");
        library.printAllBooks();

        System.out.println();
        System.out.println("===== 전체 회원 정보 =====");
        library.printAllUsers();
    }
}


/**
 * 책 한 권의 정보와 상태만 담당한다.
 * SRP: 책 데이터 및 책 상태에 대한 책임만 가진다.
 */
class Book {
    private final String title;
    private final String author;
    private final String isbn;
    private boolean available;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return available;
    }

    /**
     * 과제 요구사항의 '책의 대출 가능 여부 변경 기능'.
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void printInfo() {
        System.out.printf(
                "제목: %s, 저자: %s, ISBN: %s, 상태: %s%n",
                title,
                author,
                isbn,
                available ? "대출 가능" : "대출 불가"
        );
    }
}


/**
 * 대출/반납 기능을 제공하는 추상화.
 * DIP: User는 구체적인 Library 구현보다 이 인터페이스에 의존한다.
 */
interface LoanService {
    boolean borrowBook(User user, Book book);
    boolean returnBook(User user, Book book);
}


/**
 * 책 등록/삭제 기능을 제공하는 별도의 관리자용 인터페이스.
 * ISP: 일반 회원은 책 관리 기능에 의존하지 않는다.
 */
interface BookManagementService {
    boolean addBook(Manager manager, Book book);
    boolean removeBook(Manager manager, Book book);
}


/**
 * 일반 회원과 관리자의 공통 정보를 정의한다.
 * LSP: Member와 Manager는 모두 User로서 동일하게 대출/반납할 수 있다.
 */
abstract class User {
    private final String userId;
    private final String name;
    private final List<Book> borrowedBooks;

    protected User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return Collections.unmodifiableList(borrowedBooks);
    }

    public void borrowBook(LoanService loanService, Book book) {
        loanService.borrowBook(this, book);
    }

    public void returnBook(LoanService loanService, Book book) {
        loanService.returnBook(this, book);
    }

    void addBorrowedBook(Book book) {
        borrowedBooks.add(book);
    }

    void removeBorrowedBook(Book book) {
        borrowedBooks.remove(book);
    }

    boolean hasBorrowed(Book book) {
        return borrowedBooks.contains(book);
    }

    public abstract String getRoleName();

    public void printInfo() {
        System.out.printf(
                "%s ID: %s, 이름: %s, 대출 권수: %d%n",
                getRoleName(),
                userId,
                name,
                borrowedBooks.size()
        );

        if (!borrowedBooks.isEmpty()) {
            for (Book book : borrowedBooks) {
                System.out.println("  - " + book.getTitle());
            }
        }
    }
}


/**
 * 일반 회원.
 * 회원에게 필요한 대출/반납 역할만 User로부터 제공받는다.
 */
class Member extends User {

    public Member(String userId, String name) {
        super(userId, name);
    }

    @Override
    public String getRoleName() {
        return "이용자";
    }
}


/**
 * 관리자.
 * User의 대출/반납 기능에 더해 책 등록/삭제 기능을 가진다.
 */
class Manager extends User {

    public Manager(String userId, String name) {
        super(userId, name);
    }

    @Override
    public String getRoleName() {
        return "관리자";
    }

    public void addBook(BookManagementService managementService, Book book) {
        managementService.addBook(this, book);
    }

    public void removeBook(BookManagementService managementService, Book book) {
        managementService.removeBook(this, book);
    }
}


/**
 * 도서관의 책과 회원 목록을 관리하고,
 * 대출/반납 및 검색 기능을 제공한다.
 */
class Library implements LoanService, BookManagementService {
    private final List<Book> books;
    private final List<User> users;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    /**
     * 새로운 일반 회원을 등록한다.
     */
    public void registerMember(Member member) {
        registerUser(member);
    }

    /**
     * 새로운 관리자를 등록한다.
     */
    public void registerManager(Manager manager) {
        registerUser(manager);
    }

    /**
     * 실제 사용자 등록의 공통 로직을 담당한다.
     */
    private void registerUser(User user) {
        if (user == null) {
            System.out.println("사용자를 등록할 수 없습니다: 사용자 정보가 없습니다.");
            return;
        }

        if (findUserById(user.getUserId()) != null) {
            System.out.printf(
                    "사용자를 등록할 수 없습니다: ID '%s'는 이미 등록되어 있습니다.%n",
                    user.getUserId()
            );
            return;
        }

        users.add(user);
        System.out.printf(
                "새로운 %s '%s'를 등록합니다.%n",
                user.getRoleName(),
                user.getName()
        );
    }

    @Override
    public boolean addBook(Manager manager, Book book) {
        if (book == null) {
            System.out.println("책을 추가할 수 없습니다: 책 정보가 없습니다.");
            return false;
        }

        if (findBookByIsbn(book.getIsbn()) != null) {
            System.out.printf(
                    "책을 추가할 수 없습니다: ISBN '%s'는 이미 등록되어 있습니다.%n",
                    book.getIsbn()
            );
            return false;
        }

        books.add(book);
        System.out.printf(
                "관리자 '%s'가 책을 추가합니다: '%s', '%s'%n",
                manager.getName(),
                book.getTitle(),
                book.getAuthor()
        );
        return true;
    }

    @Override
    public boolean removeBook(Manager manager, Book book) {
        if (!books.contains(book)) {
            System.out.printf(
                    "책을 삭제할 수 없습니다: '%s'은(는) 도서관에 등록되지 않은 책입니다.%n",
                    book.getTitle()
            );
            return false;
        }

        if (!book.isAvailable()) {
            System.out.printf(
                    "책을 삭제할 수 없습니다: '%s'은(는) 현재 대출 중입니다.%n",
                    book.getTitle()
            );
            return false;
        }

        books.remove(book);
        System.out.printf(
                "관리자 '%s'가 책을 삭제합니다: '%s'%n",
                manager.getName(),
                book.getTitle()
        );
        return true;
    }

    @Override
    public boolean borrowBook(User user, Book book) {
        if (!books.contains(book)) {
            System.out.printf(
                    "'%s'은(는) 도서관에 등록되지 않은 책입니다.%n",
                    book.getTitle()
            );
            return false;
        }

        if (!book.isAvailable()) {
            System.out.printf("'%s'은 대출 중입니다.%n", book.getTitle());
            return false;
        }

        book.setAvailable(false);
        user.addBorrowedBook(book);

        System.out.printf(
                "%s '%s'가 '%s' 대출합니다.%n",
                user.getRoleName(),
                user.getName(),
                book.getTitle()
        );
        return true;
    }

    @Override
    public boolean returnBook(User user, Book book) {
        if (!books.contains(book)) {
            System.out.printf(
                    "반납할 수 없습니다: '%s'은(는) 도서관에 등록되지 않은 책입니다.%n",
                    book.getTitle()
            );
            return false;
        }

        if (!user.hasBorrowed(book)) {
            System.out.printf(
                    "반납할 수 없습니다: %s '%s'의 대출 목록에 '%s'이(가) 없습니다.%n",
                    user.getRoleName(),
                    user.getName(),
                    book.getTitle()
            );
            return false;
        }

        user.removeBorrowedBook(book);
        book.setAvailable(true);

        System.out.printf(
                "%s '%s'가 '%s' 반납합니다.%n",
                user.getRoleName(),
                user.getName(),
                book.getTitle()
        );
        return true;
    }

    /**
     * 저자명으로 책을 검색하고 대출 상태를 출력한다.
     */
    public List<Book> searchBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();

        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                result.add(book);
            }
        }

        if (result.isEmpty()) {
            System.out.printf(
                    "검색 결과: 저자 '%s'의 책을 찾을 수 없습니다.%n",
                    author
            );
            return result;
        }

        System.out.printf("저자 '%s'의 책 목록:%n%n", author);

        for (Book book : result) {
            System.out.printf(
                    "- %s, %s%n",
                    book.getTitle(),
                    book.isAvailable() ? "대출 가능" : "대출 불가"
            );
        }

        return result;
    }

    public void printAllBooks() {
        if (books.isEmpty()) {
            System.out.println("등록된 책이 없습니다.");
            return;
        }

        for (Book book : books) {
            book.printInfo();
        }
    }

    public void printAllUsers() {
        if (users.isEmpty()) {
            System.out.println("등록된 회원이 없습니다.");
            return;
        }

        for (User user : users) {
            user.printInfo();
        }
    }

    private Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    private User findUserById(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
}
