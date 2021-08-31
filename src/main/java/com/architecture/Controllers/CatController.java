package com.architecture.Controllers;


import java.util.List;

import com.architecture.entities.Cat;
import com.architecture.services.ICatService;
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
public class CatController {
    private static final String template = "Hello, %s!";

    @Autowired
    private ICatService service;

    @GetMapping("")
	public ResponseEntity<ObjectHttp> getAllCats() throws Exception {
        TemplateHttp httpResponse = null;
        Object obj = null;
        try{
            List<Cat> cat = service.getAllCats();
            httpResponse = new HttpOk();
            obj = cat;
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
            Cat cat = service.addCat( String.format(template, name), String.format(template, name), 1.2);
            httpResponse = new HttpOk();
            obj = cat;
        }catch(Exception ex){
            httpResponse = new HttpNotFound();
            obj = ex;
        }
        return httpResponse.TemplateResponse(obj);

	}

    @PostMapping( value = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ObjectHttp> createCat(@RequestBody Cat catHttp) throws Exception {
 
        TemplateHttp httpResponse = null;
        Object obj = null;
        try{
            Cat cat = service.addCat( catHttp.getTitle(), catHttp.getDescription(), catHttp.getPrice() );
            httpResponse = new HttpOk();
            obj = cat;
        }catch(Exception ex){
            httpResponse = new HttpNotFound();
            obj = ex;
        }
        return httpResponse.TemplateResponse(obj);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<ObjectHttp> getSingleCat(@PathVariable("id") long id) throws Exception {

        TemplateHttp httpResponse = null;
        Object obj = null;
        try{
            Cat cat = service.getCat(id);            
            httpResponse = new HttpOk();
            obj = cat;
        }catch(Exception ex){
            httpResponse = new HttpNotFound();
            obj = ex;
        }
        return httpResponse.TemplateResponse(obj);

	}
    @PutMapping({"/{id}"})
    public ResponseEntity<ObjectHttp> updateCat(@PathVariable("id") long id, @RequestBody Cat catHttp ) throws Exception {

        TemplateHttp httpResponse = null;
        Object obj = null;
        try{
            Cat cat = service.updateCat(id,catHttp.getTitle(), catHttp.getDescription(), catHttp.getPrice());
            httpResponse = new HttpOk();
            obj = cat;
        }catch(Exception ex){
            httpResponse = new HttpNotFound();
            obj = ex;
        }
        return httpResponse.TemplateResponse(obj);

	}
    @DeleteMapping({"/delete/{todoId}"})
    public ResponseEntity<ObjectHttp> deleteCat(@PathVariable("id") long id ) throws Exception {

        TemplateHttp httpResponse = null;
        Object obj = null;
        try{
            boolean result = service.removeCat(id);
            httpResponse = new HttpOk();
            obj = result;
        }catch(Exception ex){
            httpResponse = new HttpNotFound();
            obj = ex;
        }
        return httpResponse.TemplateResponse(obj);
	}
}
