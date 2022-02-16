package OtherUtils;

import com.github.javafaker.Faker;

import javax.crypto.spec.PSource;
import java.util.Random;

public class StringUtils {

   public static final Faker faker = new Faker();

   public static String generateRandomString() {
      return (faker.name().nameWithMiddle());
   }

   public static String generateLastName() {
      return (faker.name().lastName());
   }

   public static String generateNickName() {
      return (faker.name().username());
   }

   public static void main(String[] args) {
      System.out.println(generateRandomString());
   }

}