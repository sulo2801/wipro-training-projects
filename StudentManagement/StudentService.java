import java.util.*; public class StudentService{
private List<Student> list=new ArrayList<>(); Scanner sc=new Scanner(System.in);
public void addStudent(){System.out.print("ID: ");int id=sc.nextInt();System.out.print("Name: ");String n=sc.next();
System.out.print("Age: ");int a=sc.nextInt();System.out.print("Course: ");String c=sc.next();list.add(new Student(id,n,a,c));}
public void view(){for(Student s:list)System.out.println(s);} }