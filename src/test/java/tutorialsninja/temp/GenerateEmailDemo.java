package tutorialsninja.temp;

import java.util.Date;

public class GenerateEmailDemo {
    public static void main(String[] args) {
        Date date = new Date();
        String dateString = date.toString();
        //Loại bỏ khoảng trắng, : và + trong chuỗi
        String formattedDateString = dateString.replaceAll("[ :+]", "");
        String emailWithTimeStamp = formattedDateString + "@gmail.com";
    }
}
