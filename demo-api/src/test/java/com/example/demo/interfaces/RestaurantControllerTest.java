package com.example.demo.interfaces;

import com.example.demo.application.RestaurantService;
import com.example.demo.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantService restaurantService;

    //Controller에 원하는 객체를 주입할 수 있음.
//    @SpyBean(RestaurantRepositoryImpl.class)
//    private RestaurantRepository restaurantRepository;
//
//    @SpyBean(MenuItemRepositoryImpl.class)
//    private MenuItemRepository menuItemRepository;
//
//    @SpyBean(RestaurantService.class)
//    private RestaurantService restaurantService;

    @Test
    public void list() throws Exception{

        ArrayList<Restaurant> restaurants = new ArrayList<>();

        restaurants.add(new Restaurant(1004L, "Bob zip", "Seoul"));

        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Bob zip\"")
                ));
    }

    @Test
    public void detail() throws Exception {

        Restaurant restaurant1 = new Restaurant(1004L, "Bob zip", "Seoul");
        Restaurant restaurant2 = new Restaurant(2020L, "Cyber food", "Seoul");
        restaurant2.addMenuItem(new MenuItem("kimchi"));

        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant1);
        given(restaurantService.getRestaurant(2020L)).willReturn(restaurant2);

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Bob zip\"")
                ))
        ;

        mvc.perform(get("/restaurants/2020"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":2020")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Cyber food\"")
                ))
                .andExpect(content().string(
                        containsString("kimchi")
                ));
    }

    @Test
    public void menu() throws Exception {
        ArrayList<Menu> menuList = new ArrayList<>();
        menuList.add(new Menu("chicken", 1000));

        given(restaurantService.getMenus()).willReturn(menuList);

        //Mockmvc : 테스트에 필요한 기능을 가진 객체이며 스프링 MVC 동작을 재현할 수 있다.
        mvc.perform(get("/menu"))
                //andExpect : 응답을 검증하는 역할
                //응답상태 검증
                .andExpect(status().isOk())
                //응답내용 검증
                .andExpect(content().string(
                        containsString("chicken")
                ))
                //요청,응답 메세지 확인
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void create() throws Exception {
        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"BeRyong\", \"address\":\"Seoul\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants/1234"))
                .andExpect(content().string("{}"))
                .andDo(MockMvcResultHandlers.print());

        //무엇이 와도 실행 될수 있게 any() 메서드를 넘김.
        verify(restaurantService).addRestaurant(any());
    }
}