public class TriangleClassifier {

  int angleA;
  int angleB;
  int angleC;
  int sumAngles;

  // Create getClassification method here, make sure it returns a String type.
  public String getClassification(int a, int b, int c) {
    angleA = a;
    angleB = b;
    angleC = c;
    sumAngles = angleA + angleB + angleC;

    // invalid triangles
    if (angleA == 0 || angleB == 0 || angleC == 0 || sumAngles != 180) {
      return "INVALID";

      // equilateral triangles
    } else if (angleA == 60 && angleB == 60 && angleC == 60) {
      return "equilateral";

      // isosceles triangles
    } else if (angleA == angleB && angleC != angleA || angleA == angleC && angleB != angleA
        || angleB == angleC && angleC != angleA) {

      if (angleA < 90 && angleB < 90 && angleC < 90) {
        return "acute isosceles";

      } else if (angleA > 90 || angleB > 90 || angleC > 90) {
        return "obtuse isosceles";

      } else if (angleA == 90 || angleB == 90 || angleC == 90) {
        return "right isosceles";
      }

      // scalene triangles
    } else if (angleA != angleB && angleA != angleC && angleB != angleC) {

      if (angleA < 90 && angleB < 90 && angleC < 90) {
        return "acute scalene";

      } else if (angleA > 90 || angleB > 90 || angleC > 90) {
        return "obtuse scalene";

      } else if (angleA == 90 || angleB == 90 || angleC == 90) {
        return "right scalene";
      }
    }

    return "";
  }
}