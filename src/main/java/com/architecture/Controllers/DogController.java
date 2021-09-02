package com.architecture.Controllers;


import java.util.List;

import com.architecture.entities.Dog;
import com.architecture.services.IDogService;
import com.architecture.templateHttp.HttpNotFound;
import com.architecture.templateHttp.HttpOk;
import com.architecture.templateHttp.TemplateHttp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.architecture.templateHttp.ObjectHttp;
@RestController
@RequestMapping("/api/cat")
public class DogController {
    private static final String template = "Hello, %s!";

    @Autowired
    private IDogService service;

    @GetMapping("")
	public ResponseEntity<ObjectHttp> getDogs() throws Exception {
        TemplateHttp httpResponse = null;
        Object obj = null;
        try{
            List<Dog> dog = service.getAllDogs();
            httpResponse = new HttpOk();
            obj = dog;
        }catch(Exception ex){
            httpResponse = new HttpNotFound();
            obj = ex;
        }
        return httpResponse.TemplateResponse(obj);
	}
    
    @GetMapping("/create")
	public ResponseEntity<ObjectHttp> create(@RequestParam(value = "name", defaultValue = "World") String name) throws Exception {

        TemplateHttp httpResponse = null;
        Object obj = null;
        try{
            Dog dog = service.addDog( String.format(template, name), String.format(template, name), 1.2,1.2);
            httpResponse = new HttpOk();
            obj = dog;
        }catch(Exception ex){
            httpResponse = new HttpNotFound();
            obj = ex;
        }
        return httpResponse.TemplateResponse(obj);

	}

    @PostMapping( value = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ObjectHttp> createDog(@RequestBody Dog dogHttp) throws Exception {
 
        TemplateHttp httpResponse = null;
        Object obj = null;
        try{
            Dog dog = service.addDog( dogHttp.getTitle(), dogHttp.getDescription(), dogHttp.getPrice(),dogHttp.getSize() );
            httpResponse = new HttpOk();
            obj = dog;
        }catch(Exception ex){
            httpResponse = new HttpNotFound();
            obj = ex;
        }
        return httpResponse.TemplateResponse(obj);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<ObjectHttp> getSingleDog(@PathVariable("id") long id) throws Exception {

        TemplateHttp httpResponse = null;
        Object obj = null;
        try{
            Dog dog = service.getDog(id);            
            httpResponse = new HttpOk();
            obj = dog;
        }catch(Exception ex){
            httpResponse = new HttpNotFound();
            obj = ex;
        }
        return httpResponse.TemplateResponse(obj);

	}
    @PutMapping({"/{id}"})
    public ResponseEntity<ObjectHttp> updateDog(@PathVariable("id") long id, @RequestBody Dog dogHttp ) throws Exception {

        TemplateHttp httpResponse = null;
        Object obj = null;
        try{
            Dog dog = service.updateDog(id,dogHttp.getTitle(), dogHttp.getDescription(), dogHttp.getPrice(), dogHttp.getSize());
            httpResponse = new HttpOk();
            obj = dog;
        }catch(Exception ex){
            httpResponse = new HttpNotFound();
            obj = ex;
        }
        return httpResponse.TemplateResponse(obj);

	}
    @DeleteMapping({"/delete/{todoId}"})
    public ResponseEntity<ObjectHttp> deleteDog(@PathVariable("id") long id ) throws Exception {

        TemplateHttp httpResponse = null;
        Object obj = null;
        try{
            boolean result = service.removeDog(id);
            httpResponse = new HttpOk();
            obj = result;
        }catch(Exception ex){
            httpResponse = new HttpNotFound();
            obj = ex;
        }
        return httpResponse.TemplateResponse(obj);
	}
}
