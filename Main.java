import java.util.LinkedHashMap;
import java.util.Scanner;
public class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.println(calc(scan.nextLine()));
  }

  public static String calc(String input) {
    int a = 0;
    int b = 0;
    String[] arr = input.split(" ");
    boolean flag = false;
    LinkedHashMap<String, Integer> rome = new LinkedHashMap<String, Integer>();
    rome.put("I", 1);
    rome.put("II", 2);
    rome.put("III", 3);
    rome.put("IV", 4);
    rome.put("V", 5);
    rome.put("VI", 6);
    rome.put("VII", 7);
    rome.put("VIII", 8);
    rome.put("IX", 9);
    rome.put("X", 10);
    rome.put("L", 50);
    if (arr.length != 3) {
      throw new ArithmeticException("Error");
    }
    if (!arr[1].equals("-") && !arr[1].equals("+") && !arr[1].equals("*") && !arr[1].equals("/")) {
      throw new ArithmeticException("Error");
    }
    boolean op_1 = false;
    boolean op_2 = false;
    try {
      Integer.parseInt(arr[0]);
    } catch (NumberFormatException e) {
      op_1 = true;
    } finally {
      try {
        Integer.parseInt(arr[2]);
      } catch (NumberFormatException er) {
        op_2 = true;
      } finally {
        if ((op_1 == true && op_2 == false) || (op_1 == false && op_2 == true)) {
          throw new ArithmeticException("Error");
        }
        if (op_1 == false) {
          a = Integer.parseInt(arr[0]);
          b = Integer.parseInt(arr[2]);
          if (a < 1 || b > 10 || b < 1 || a > 10) {
            throw new ArithmeticException("Error");
          }
        } else {
          for (String key : rome.keySet()) {
            if (key == "L") {
              throw new ArithmeticException("Error");
            }
            if (key.equals(arr[0])) {
              a = rome.get(key);
              flag = true;
              break;
            }
          }
          for (String key : rome.keySet()) {
            if (key.equals("L")) {
              throw new ArithmeticException("Error");
            }
            if (key.equals(arr[2])) {
              b = rome.get(key);
              break;
            }
          }
        }
        int result = 0;
        if (arr[1].equals("-")) {
          result = a - b;
          if (flag == true && result < 1) {
            throw new ArithmeticException("Error");
          }
        } else if (arr[1].equals("+")) {
          result = a + b;
        } else if (arr[1].equals("/")) {
          result = a / b;
          if (result == 0 && flag == true) {
            throw new ArithmeticException("Error");
          }
        } else if (arr[1].equals("*")) {
          result = a * b;
        }
        if (flag == false) {
          return Integer.toString(result);
        }
        int reminder = result % 10;
        switch (result / 10) {
          case 0:
            arr[0] = "";
            break;
          case 1:
            arr[0] = "X";
            break;
          case 2:
            arr[0] = "XX";
            break;
          case 3:
            arr[0] = "XXX";
            break;
          case 4:
            arr[0] = "XL";
            break;
          case 5:
            arr[0] = "L";
            break;
          case 6:
            arr[0] = "LX";
            break;
          case 7:
            arr[0] = "LXX";
            break;
          case 8:
            arr[0] = "LXXX";
            break;
          case 9:
            arr[0] = "XC";
            break;
          default:
            return "C";
        }
        for (String key : rome.keySet()) {
          if (rome.get(key) == reminder) {
            return arr[0] + key;
          }
        }
        return arr[0];
      }
    }
  }
}