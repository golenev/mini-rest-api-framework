package utils;

import com.github.javafaker.Faker;

public class StringUtils {

   public static final Faker faker = new Faker();

   public static String generateRandomString() {
      return (faker.name().nameWithMiddle());
   }

   public static String insertTitleText() {
      return (faker.name().lastName());
   }

   public static String insertTitleText(String title) {
      return title;
   }

   public static String generateNickName() {
      return (faker.name().username());
   }

   public static String insertBodyText(String body) {
      return body;
   }

   public static String insertBodyText() {
      return generateNickName();
   }

   public static void main(String[] args) {
      System.out.println(insertBodyText());
   }

}
