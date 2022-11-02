package pattern;

public class MainClass {
    private static void print(Object object) {
        if (object instanceof Postion position) {
            System.out.println("object is a position, x = " + position.x()
                    + ", y = " + position.y());
        }
    }

    private static void print1(Object object) {
        if (object instanceof Postion(int x, int y)) {
            System.out.println("object is a position, x = " + x + ", y = " + y);
        }
        // else ...
    }


    private static void print3(Object object) {
        switch (object) {
            case Postion(int x, int y)-> System.out.println("object is a position, x = " + x + ", y = " + y);
                default -> System.out.println("default");
                // other cases ...
        }
    }

    public static void main(String a[]){
        Postion p = new Postion(10, 20);
        print1(p);

    }
}
