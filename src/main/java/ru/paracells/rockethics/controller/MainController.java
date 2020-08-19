package ru.paracells.rockethics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.paracells.rockethics.model.Cat;
import ru.paracells.rockethics.service.CatsService;

import java.util.List;

@RestController
public class MainController {

    private CatsService catsService;


    @Autowired
    public MainController(CatsService catsService) {
        this.catsService = catsService;
    }

    // получем данные о всех котах в базе
    @GetMapping("/all")
    public ResponseEntity<List<Cat>> findAll() {
        List<Cat> all = catsService.findAll();
        return ResponseEntity.ok(all);
    }

    // можно найти кота по его id с проверкой на несуществующий id
    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return catsService.findById(id);
    }

    // добавляем кота, с проверкой на данные
    @PostMapping("/add")
    public ResponseEntity<String> save(@RequestBody Cat cat) {
        return catsService.save(cat);

    }

    @DeleteMapping("/del")
    public ResponseEntity<String> deleteById(@RequestParam("id") Long id) {
        return catsService.deleteById(id);
    }


}
