package com.example.demo.interfaces;

import com.example.demo.application.RestaurantService;
import com.example.demo.domain.MenuItemRepository;
import com.example.demo.domain.MenuItemRepositoryImpl;
import com.example.demo.domain.RestaurantRepository;
import com.example.demo.domain.RestaurantRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    //Controller에 원하는 객체를 주입할 수 있음.
    @SpyBean(RestaurantRepositoryImpl.class)
    private RestaurantRepository restaurantRepository;

    @SpyBean(MenuItemRepositoryImpl.class)
    private MenuItemRepository menuItemRepository;

    @SpyBean(RestaurantService.class)
    private RestaurantService restaurantService;

    @Test
    public void list() throws Exception{
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
}