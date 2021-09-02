package com.architecture.Controllers;


import java.util.List;

import com.architecture.CrossCutting.TemplateHttp.HttpNotFound;
import com.architecture.CrossCutting.TemplateHttp.HttpOk;
import com.architecture.CrossCutting.TemplateHttp.ObjectHttp;
import com.architecture.CrossCutting.TemplateHttp.TemplateHttp;
import com.architecture.Entities.Dog;
import com.architecture.Services.IDogService;

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
@RestController
@RequestMapping("/api/dog")
public class DogController {

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
	public ResponseEntity<ObjectHttp> create(@RequestParam(value = "title") String title, @RequestParam(value = "desc") String desc,@RequestParam(value = "price") double price,@RequestParam(value = "price") double size ) throws Exception {

        TemplateHttp httpResponse = null;
        Object obj = null;
        try{
            Dog dog = service.addDog( title, desc, price,size);
            httpResponse = new HttpOk();
            obj = dog;
        }catch(Exception ex){
            httpResponse = new HttpNotFound();
            obj = ex;
        }
        return httpResponse.TemplateResponse(obj);

	}

    @PostMapping( value = "", consumes = "application/json", produces = "application/json")
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
