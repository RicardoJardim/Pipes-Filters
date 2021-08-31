package com.architecture.pipesandfilters;


import java.util.List;

import com.architecture.entities.Cat;
import com.architecture.services.CatService;

import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cat")
public class CatController {
    private static final String template = "Hello, %s!";
    private static final CatService service = new CatService();


    @GetMapping("")
	public List<Cat> getAllCats() throws Exception {

        try{
            List<Cat> cat = service.getAllCats();
            return cat;
        }catch(Exception ex){
            throw ex;
        }

	}
    
    @GetMapping("/create")
	public Cat create(@RequestParam(value = "name", defaultValue = "World") String name) throws Exception {

        try{
            Cat cat = service.addCat( String.format(template, name), String.format(template, name), 1.2);
            return cat;
        }catch(Exception ex){
            throw ex;
        }

	}

    

    /* @PostMapping( value = "/create", consumes = "application/json", produces = "application/json")
    public Cat createPerson(@RequestBody Cat person) {
        return personService.saveUpdatePerson(person);
    } */
}
