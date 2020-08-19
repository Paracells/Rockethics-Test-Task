package ru.paracells.rockethics.serviceTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.paracells.rockethics.model.Cat;
import ru.paracells.rockethics.repository.CatsMapper;
import ru.paracells.rockethics.service.CatsService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ServiceTests {

    @Autowired
    CatsService catsService;

    // забавно, но без конкретного указания name, mybatis не мокается
    @MockBean(name = "catsMapper")
    CatsMapper catsMapper;


    @Test
    @DisplayName("запишем нового кота - true")
    public void whenSaveCat__True__shouldReturn_1() {
        Cat cat = new Cat("Testik", 2);
        when(catsMapper.save(cat)).thenReturn(1);
        ResponseEntity<String> result = catsService.save(cat);
        verify(catsMapper, times(1)).save(cat);

        assertEquals(new ResponseEntity<String>(HttpStatus.CREATED), result);
    }

    @Test
    @DisplayName("забыли добавить данные о котейке - false")
    public void whenSaveCat__False__shouldReturn() {
        Cat cat = new Cat();
        ResponseEntity<String> result = catsService.save(cat);
        assertEquals(new ResponseEntity<String>("Данные введены неправильно", HttpStatus.BAD_REQUEST), result);
    }

    @Test
    @DisplayName("ищем кота с определенным номером - true")
    public void findCatById_ShouldReturnCat() {
        Cat cat = new Cat();
        when(catsMapper.findById(1L)).thenReturn(cat);
        Object body = catsService.findById(1L).getBody();
        assertEquals(body, cat);
    }

    @Test
    @DisplayName("ищем кота с определенным номером - false")
    public void findCatById_ShouldReturnFalse() {
        HttpStatus statusCode = catsService.findById(100L).getStatusCode();
        assertEquals(HttpStatus.BAD_REQUEST, statusCode);
    }

    @Test
    @DisplayName("получить всех котеек из базы - true")
    public void findAllCatsFromDB__shouldReturn__CountInteger() {

        List<Cat> cats = new ArrayList<>();
        Cat cat = new Cat("Jorik", 2);
        Cat cat1 = new Cat("Bar", 1);
        cats.add(cat);
        cats.add(cat1);
        when(catsMapper.findAll()).thenReturn(cats);
        List<Cat> result = catsService.findAll();
        verify(catsMapper, times(1)).findAll();
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("забрали котейку, убираем из бд - true")
    public void deleteCatById__shouldReturn__True() {
        when(catsMapper.deleteById(1L)).thenReturn(true);
        ResponseEntity<String> result = catsService.deleteById(1L);
        assertEquals("Котейку забрали", result.getBody());
    }

    @Test
    @DisplayName("такого котейки нет, убираем из бд - false")
    public void deleteCatById__shouldReturn__False() {
        when(catsMapper.deleteById(1L)).thenReturn(false);
        ResponseEntity<String> result = catsService.deleteById(1L);
        assertEquals("Котейки с таким ID не существует, может потеряли?", result.getBody());
    }

}

