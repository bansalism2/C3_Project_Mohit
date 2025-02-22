import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {
    Restaurant restaurant;
    @BeforeEach
    public void beforeEachTest(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
    }


    //REFACTOR ALL THE REPEATED LINES OF CODE



    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE


    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
//        restaurant = new Restaurant("abc","def",LocalTime.parse("10:30:00"),LocalTime.parse("22:30:00"));
        Restaurant spiedRest = Mockito.spy(restaurant);
        LocalTime lct = LocalTime.parse("15:00:00");
        Mockito.when(spiedRest.getCurrentTime()).thenReturn(lct);

       boolean check = spiedRest.isRestaurantOpen();
       assertTrue(check);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
//        restaurant = new Restaurant("abc","def",LocalTime.parse("10:30:00"),LocalTime.parse("22:30:00"));
        Restaurant spiedRest = Mockito.spy(restaurant);
        LocalTime lct = LocalTime.parse("09:00:00");
        Mockito.when(spiedRest.getCurrentTime()).thenReturn(lct);

        boolean check = spiedRest.isRestaurantOpen();
        assertFalse(check);

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>Order Total<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void on_selecting_item_list_the_order_amount_should_match(){
//        restaurant = new Restaurant("rst1","hyd", LocalTime.of(9,0,0),LocalTime.of(9,0,0));
        restaurant.addToMenu("item1",5);
        restaurant.addToMenu("item2",15);
        List<String> lst1 = Arrays.asList("item1","item2");

        assertEquals(restaurant.getOrderTotal(lst1),20);
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}