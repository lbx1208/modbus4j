public class Testclass {
    public static void main(String[] args) {
        dog dog1 = new dog();
        dog dog2 = new dog(2,3);
        System.out.println(dog1.getAge());
        System.out.println(dog2.getAge());

    }
}
class dog{

    public dog(int age, int say) {
        this.age = age;
        this.say = say;
    }

    private int age;
    private int say;

    public dog() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSay() {
        return say;
    }

    public void setSay(int say) {
        this.say = say;
    }




}