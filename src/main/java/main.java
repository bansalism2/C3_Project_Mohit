

import java.time.LocalTime;

public class main {
    public static void main(String[] args){
        Restaurant rest = new Restaurant("abc","def",LocalTime.parse("10:00:00"), LocalTime.parse("22:00:00"));
        System.out.println(rest.isRestaurantOpen());

    }
}
//    public static void main(String[]args){
//        Restaurant rest = new Restaurant("abc","def",LocalTime.parse("10:00:00"), LocalTime.parse("22:00:00"));
//        System.out.println(rest.getName());
//    }