package com.example.demo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class Restaurant {

    @Id
    @GeneratedValue
    @Setter
    private Long id;
    private String name;
    private String address;

    @Transient
    private List<MenuItem> menuItems;

    public String getInformation() {
        return name + " in " + address;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        //this.menuItems = menuItems
        //위와 같이 작성할 경우 this.menuItems는 menuItems를 참조만 하기 때문에
        //menuItems가 수정되면 같이 수정된다.
        this.menuItems = new ArrayList<>(menuItems);
    }

    public void updateInformation(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
