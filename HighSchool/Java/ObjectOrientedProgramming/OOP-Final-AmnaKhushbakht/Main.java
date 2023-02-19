class Main {
  public static void main(String[] args) {
    Course math = new Course("Math", 30, "Spongebob", 1);
    Course biology = new Course("Biology", 30, "Patrick", 2);
    Course english = new Course("English", 30, "Squidward", 3);
    Course social = new Course("Social", 30, "Mr. Krabs", 4);

    Schedule Amna = new Schedule(math, biology, english, social);
    System.out.println(Amna.getSchedule());
  }
}

class Course {
  String courseName, teacherName;
  int courseNumber, periodNumber;

  // constructor
  public Course(String name, int number, String teacher, int period) {
    courseName = name;
    courseNumber = number;
    teacherName = teacher;
    periodNumber = period;
  }

  // accessor
  public String getCourseName() {
    return courseName;
  }

  // accessor
  public int getCourseNumber() {
    return courseNumber;
  }

  // accessor
  public String getTeacherName() {
    return teacherName;
  }

  // accessor
  public int getPeriodNumber() {
    return periodNumber;
  }

  // mutator
  public void setCourseName(String name) {
    courseName = name;
  }

  // mutator
  public void setCourseNumber(int number) {
    courseNumber = number;
  }

  // mutator
  public void setTeacherName(String teacher) {
    teacherName = teacher;
  }

  // mutator
  public void setPeriodNumber(int period) {
    periodNumber = period;
  }

  // toString
  public String toString() {
    return courseName + " " + courseNumber + " with " + teacherName + " in Period " + periodNumber;
  }
}

class Schedule {

  Course firstPeriod;
  Course secondPeriod;
  Course thirdPeriod;
  Course fourthPeriod;

  public Schedule(Course first, Course second, Course third, Course fourth) {
    firstPeriod = first;
    secondPeriod = second;
    thirdPeriod = third;
    fourthPeriod = fourth;
  }

  public String getSchedule() {
    return firstPeriod.toString() + "\n" + secondPeriod.toString() + "\n" + thirdPeriod.toString() + "\n"
        + fourthPeriod.toString();
  }
}