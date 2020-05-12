import java.time.LocalDate;
import java.util.Random;

public class DebuggingMethods {
    public static void main(String args[]){
        var person = new Person("Ann", "Smith", LocalDate.of(1971,1,2));
        person.SayHi();
        RandomChanges.Age(person);
        RandomChanges.Rename(person);
        person.SayHi();
    }
}

class Person{
    String Name;
    String LastName;
    Integer Age;
    LocalDate YearOfBirth;

    public Person(String name, String lastName, Integer age){
        setName(name);
        setLastName(lastName);
        setAge(age);
        setYearOfBirth(age);
    }

    public Person(String name, String lastName, LocalDate dateOfBirth) {
        setName(name);
        setLastName(lastName);
        setAge(dateOfBirth);
        setYearOfBirth(dateOfBirth);
    }

    public void setName(String name){
        Name = name;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public void setAge(LocalDate yearOfBirth) {
        LocalDate currentDate = LocalDate.now();
        Age = currentDate.getYear() - yearOfBirth.getYear();
    }

    public void setYearOfBirth(LocalDate yearOfBirth) {
        YearOfBirth = yearOfBirth;
    }
    public void setYearOfBirth(Integer age){
        LocalDate currentDate = LocalDate.now();
        YearOfBirth = LocalDate.ofEpochDay(currentDate.getYear() + age);
    }

    public void SayHi() {
        System.out.println(String.format("Hi, my name's %s!", Name));
    }
}

class RandomChanges{
    public static void Rename(Person person){
        Random random = new Random();
        int r = random.nextInt(5);
        switch (r){
            case 1: MakeChanges1(person); break;
            case 2: MakeChanges2(person); break;
            case 3: MakeChanges3(person); break;
            case 4: MakeChanges4(person); break;
            case 5: MakeChanges5(person); break;
            default: break;
        }
    }
    public static void Age(Person person){
        Random random = new Random();
        int r = random.nextInt(5);
        person.setAge(person.Age+r);
    }
    public static void MakeChanges1(Person person){
        person.setName("Mary");
    }
    public static void MakeChanges2(Person person){
        person.setName("Beth");
    }
    public static void MakeChanges3(Person person){
        person.setName("Liza");
    }
    public static void MakeChanges4(Person person){
        person.setName("Jane");
    }
    public static void MakeChanges5(Person person){
        person.setName("Jill");
    }
}
