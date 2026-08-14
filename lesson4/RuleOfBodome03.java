import java.util.ArrayList;
import java.util.List;

/**
 * SOLID 원칙을 반영한 전자기기 컨트롤러 시스템.
 *
 * 프로젝트 및 메인 클래스 이름: RuleOfBodome03
 */
public class RuleOfBodome03 {

    public static void main(String[] args) {
        // 1. 일반 기기와 스마트 기기, 컨트롤러를 하나씩 생성한다.
        GeneralDevice doorOpener = new GeneralDevice("도어 오프너");

        SmartDevice smartMirror = new SmartDevice(
                "자동 거울",
                "기분을 인식해 옷을 추천하는 기능"
        );

        DeviceController controller = new DeviceController();

        System.out.println();

        // 2. 컨트롤러와 기기를 연결한다.
        controller.connectDevice(doorOpener);
        controller.connectDevice(smartMirror);

        System.out.println();

        // 3. 컨트롤러를 이용해 스마트 전자기기 전원을 켠다.
        controller.turnOnDevice(smartMirror);

        // 4. 컨트롤러를 이용해 스마트 고급 기능을 활성화한다.
        controller.activateSmartFeature(smartMirror);

        System.out.println();

        // 5. 일반 전자기기의 전원을 켠다.
        controller.turnOnDevice(doorOpener);

        System.out.println();

        // 6. 컨트롤러를 이용해 모든 전자기기 전원을 끈다.
        controller.turnOffAllDevices();

        System.out.println();
        System.out.println("===== 예외 처리 시뮬레이션 =====");

        // 예외 1: 꺼져 있는 스마트 기기의 고급 기능 활성화
        controller.activateSmartFeature(smartMirror);

        // 예외 2: 스마트 기기의 고급 기능이 입력되지 않은 경우
        SmartDevice invalidSmartDevice = new SmartDevice("스마트 조명", "");
        controller.connectDevice(invalidSmartDevice);
        controller.turnOnDevice(invalidSmartDevice);
        controller.activateSmartFeature(invalidSmartDevice);

        System.out.println();
        System.out.println("===== 연결된 기기 정보 =====");
        controller.printAllDeviceInfo();
    }
}


/**
 * 모든 전자기기가 가져야 하는 공통 전원 기능을 정의한다.
 *
 * ISP:
 * 모든 기기는 전원 기능만 공통으로 가지며,
 * 스마트 기능은 별도 인터페이스로 분리한다.
 */
interface PowerControllable {
    void turnOn();
    void turnOff();
    boolean isPowerOn();
}


/**
 * 스마트 기기만 사용하는 고급 기능 인터페이스.
 *
 * ISP:
 * 일반 기기가 사용하지 않는 고급 기능에 의존하지 않도록 분리했다.
 */
interface SmartFeatureControllable {
    void activateAdvancedFeature();
    String getAdvancedFeature();
}


/**
 * 전자기기의 공통 속성과 기능을 가진 추상 클래스.
 *
 * SRP:
 * 전자기기의 공통 정보와 전원 상태만 관리한다.
 */
abstract class ElectronicDevice implements PowerControllable {
    private static final String BRAND_NAME = "DOMETech";

    private final String productName;
    private boolean powerOn;

    protected ElectronicDevice(String productName) {
        this.productName = productName;
        this.powerOn = false;
    }

    public String getProductName() {
        return productName;
    }

    public String getBrandName() {
        return BRAND_NAME;
    }

    @Override
    public void turnOn() {
        if (powerOn) {
            System.out.printf("%s 전원은 이미 켜져 있습니다.%n", productName);
            return;
        }

        powerOn = true;
        System.out.printf("%s 전원을 켰습니다.%n", productName);
    }

    @Override
    public void turnOff() {
        if (!powerOn) {
            System.out.printf("%s 전원은 이미 꺼져 있습니다.%n", productName);
            return;
        }

        powerOn = false;
        System.out.printf("%s 전원을 껐습니다.%n", productName);
    }

    @Override
    public boolean isPowerOn() {
        return powerOn;
    }

    public void printInfo() {
        System.out.printf(
                "제품명: %s, 브랜드명: %s, 전원 상태: %s%n",
                productName,
                BRAND_NAME,
                powerOn ? "ON" : "OFF"
        );
    }
}


/**
 * 단일 기능을 담당하는 일반 기기.
 */
class GeneralDevice extends ElectronicDevice {

    public GeneralDevice(String productName) {
        super(productName);

        System.out.printf(
                "일반 기기가 생성되었습니다 : %s, %s%n",
                getProductName(),
                getBrandName()
        );
    }

    @Override
    public void printInfo() {
        System.out.printf(
                "[일반 기기] 제품명: %s, 브랜드명: %s, 전원 상태: %s%n",
                getProductName(),
                getBrandName(),
                isPowerOn() ? "ON" : "OFF"
        );
    }
}


/**
 * 고급 기능이 탑재된 스마트 기기.
 *
 * LSP:
 * ElectronicDevice의 전원 기능을 그대로 정상 수행하면서
 * 스마트 기기만의 기능을 추가한다.
 */
class SmartDevice extends ElectronicDevice
        implements SmartFeatureControllable {

    private final String advancedFeature;

    public SmartDevice(String productName, String advancedFeature) {
        super(productName);
        this.advancedFeature = advancedFeature;

        System.out.printf(
                "스마트 기기가 생성되었습니다 : %s, %s, %s%n",
                getProductName(),
                getBrandName(),
                hasAdvancedFeature()
                        ? advancedFeature
                        : "고급 기능 정보 없음"
        );
    }

    @Override
    public String getAdvancedFeature() {
        return advancedFeature;
    }

    private boolean hasAdvancedFeature() {
        return advancedFeature != null
                && !advancedFeature.trim().isEmpty();
    }

    @Override
    public void activateAdvancedFeature() {
        if (!isPowerOn()) {
            System.out.printf(
                    "%s의 고급 기능을 활성화할 수 없습니다: 전원이 꺼져 있습니다.%n",
                    getProductName()
            );
            return;
        }

        if (!hasAdvancedFeature()) {
            System.out.printf(
                    "%s의 고급 기능을 활성화할 수 없습니다: 고급 기능이 입력되지 않았습니다.%n",
                    getProductName()
            );
            return;
        }

        System.out.printf(
                "%s 고급 기능을 활성화 시켰습니다.%n",
                getProductName()
        );

        System.out.printf(
                "실행 기능 : %s%n",
                advancedFeature
        );
    }

    @Override
    public void printInfo() {
        System.out.printf(
                "[스마트 기기] 제품명: %s, 브랜드명: %s, 전원 상태: %s, 고급 기능: %s%n",
                getProductName(),
                getBrandName(),
                isPowerOn() ? "ON" : "OFF",
                hasAdvancedFeature()
                        ? advancedFeature
                        : "입력되지 않음"
        );
    }
}


/**
 * 전자기기를 조작하고 관리하는 컨트롤러.
 *
 * DIP:
 * 컨트롤러는 특정 기기 클래스가 아니라
 * ElectronicDevice라는 추상 타입과 SmartFeatureControllable 인터페이스를 이용한다.
 *
 * OCP:
 * 새로운 전자기기 클래스가 ElectronicDevice를 상속하면
 * 컨트롤러의 기존 코드를 수정하지 않고 연결할 수 있다.
 */
class DeviceController {
    private final List<ElectronicDevice> devices;

    public DeviceController() {
        devices = new ArrayList<>();
    }

    /**
     * 컨트롤러에 전자기기를 연결한다.
     */
    public void connectDevice(ElectronicDevice device) {
        if (device == null) {
            System.out.println(
                    "기기를 등록할 수 없습니다: 기기 정보가 없습니다."
            );
            return;
        }

        if (devices.contains(device)) {
            System.out.printf(
                    "%s은(는) 이미 컨트롤러에 등록되어 있습니다.%n",
                    device.getProductName()
            );
            return;
        }

        devices.add(device);

        System.out.printf(
                "컨트롤러에 기기가 등록되었습니다 : %s%n",
                device.getProductName()
        );
    }

    /**
     * 연결된 기기의 전원을 켠다.
     */
    public void turnOnDevice(ElectronicDevice device) {
        if (!isConnected(device)) {
            printNotConnectedMessage(device);
            return;
        }

        device.turnOn();
    }

    /**
     * 연결된 기기의 전원을 끈다.
     */
    public void turnOffDevice(ElectronicDevice device) {
        if (!isConnected(device)) {
            printNotConnectedMessage(device);
            return;
        }

        device.turnOff();
    }

    /**
     * 스마트 기기의 고급 기능을 활성화한다.
     */
    public void activateSmartFeature(SmartDevice smartDevice) {
        if (!isConnected(smartDevice)) {
            printNotConnectedMessage(smartDevice);
            return;
        }

        smartDevice.activateAdvancedFeature();
    }

    /**
     * 컨트롤러에 연결된 모든 기기의 전원을 한 번에 끈다.
     */
    public void turnOffAllDevices() {
        if (devices.isEmpty()) {
            System.out.println(
                    "종료할 기기가 없습니다: 컨트롤러에 연결된 기기가 없습니다."
            );
            return;
        }

        System.out.print("모든 기기 전원을 종료합니다 : ");

        for (int index = devices.size() - 1; index >= 0; index--) {
            System.out.print(devices.get(index).getProductName());

            if (index > 0) {
                System.out.print(", ");
            }
        }

        System.out.println();

        for (ElectronicDevice device : devices) {
            if (device.isPowerOn()) {
                device.turnOff();
            }
        }
    }

    /**
     * 모든 전자기기의 정보를 출력한다.
     */
    public void printAllDeviceInfo() {
        if (devices.isEmpty()) {
            System.out.println("등록된 기기가 없습니다.");
            return;
        }

        for (ElectronicDevice device : devices) {
            device.printInfo();
        }
    }

    private boolean isConnected(ElectronicDevice device) {
        return device != null && devices.contains(device);
    }

    private void printNotConnectedMessage(ElectronicDevice device) {
        if (device == null) {
            System.out.println(
                    "작업할 수 없습니다: 기기 정보가 없습니다."
            );
            return;
        }

        System.out.printf(
                "%s은(는) 컨트롤러에 연결되지 않은 기기입니다.%n",
                device.getProductName()
        );
    }
}
