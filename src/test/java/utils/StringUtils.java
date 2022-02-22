package utils;

import api.ContentType;
import api.StatusCode;
import com.github.javafaker.Faker;
import org.testng.Assert;

public class StringUtils {

   public static final Faker faker = new Faker();


   public static String insertTitleText() {
      return (faker.name().lastName());
   }

   public static String generateNickName() {
      return (faker.name().username());
   }

   public static String insertBodyText() {
      return generateNickName();
   }


}
