package ru.paracells.rockethics.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.paracells.rockethics.model.Cat;
import ru.paracells.rockethics.repository.CatsMapper;

import java.util.List;

@Service
public class CatsService {

    CatsMapper catsMapper;

    public CatsService(CatsMapper catsMapper) {
        this.catsMapper = catsMapper;
    }

    public List<Cat> findAll() {
        return catsMapper.findAll();
    }

    public ResponseEntity<?> findById(Long id) {
        Cat cat = catsMapper.findById(id);
        if (cat == null)
            return new ResponseEntity<>("Не существует кота с данным ID", HttpStatus.BAD_REQUEST);
        cat.setId(id);
        return ResponseEntity.ok(cat);
    }

    public ResponseEntity<String> save(Cat cat) {
        if (cat.getAge() == null || cat.getName() == null)
            return new ResponseEntity<>("Данные введены неправильно", HttpStatus.BAD_REQUEST);
        catsMapper.save(cat);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    public ResponseEntity<String> deleteById(Long id) {
        Boolean result = catsMapper.deleteById(id);
        if (result)
            return ResponseEntity.ok("Котейку забрали");
        return new ResponseEntity<>("Котейки с таким ID не существует, может потеряли?", HttpStatus.BAD_REQUEST);

    }
}
