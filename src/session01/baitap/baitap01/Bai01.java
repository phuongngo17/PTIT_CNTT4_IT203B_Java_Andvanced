package session01.baitap.baitap01;

import java.util.Scanner;

public class Bai01 {
   public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       try{
           System.out.println("Nhập năm sinh của bạn: ");
           String input = sc.nextLine();
           int birth = Integer.parseInt(input);
           int current = 2026;
           int age = current - birth;

           System.out.println("Tuổi của bạn: " + age);
       }catch (NumberFormatException e){
           System.out.println("Lỗi: Bạn phải nhập năm sinh là số");
       } finally {
           sc.close();
           System.out.println("Thực hiện dọn dẹp tài nguyên trong finally");
       }
   }
}
