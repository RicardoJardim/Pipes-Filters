package com.architecture.Controllers;


import java.util.List;
import com.architecture.CrossCutting.HttpDecorators.HttpDecorator;
import com.architecture.CrossCutting.HttpDecorators.HttpOkDecorator;
import com.architecture.CrossCutting.HttpDecorators.HttpErrorDecorator;
import com.architecture.CrossCutting.HttpDecorators.Objects.AbstractHttpObject;
import com.architecture.CrossCutting.HttpDecorators.Objects.HttpObjectError;
import com.architecture.CrossCutting.HttpDecorators.Objects.HttpObjectOk;
import com.architecture.Entities.Cat;
import com.architecture.Services.ICatService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/cat")
public class CatController {

    @Autowired
    private ICatService service;

    @GetMapping("")
	public ResponseEntity<AbstractHttpObject> getAllCats() throws Exception {
        HttpDecorator httpResponse;
        try{
            List<Cat> cat = service.getAllCats();
            httpResponse = new HttpOkDecorator(new HttpObjectOk(cat));
           
        }catch(Exception ex){
            httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, ex.getMessage()),HttpStatus.BAD_REQUEST);
        }

        return httpResponse.ReturnObjectMsg();
	}
    
    @GetMapping("/create")
	public ResponseEntity<AbstractHttpObject> create(@RequestParam(value = "title") String title, @RequestParam(value = "desc") String desc,@RequestParam(value = "price") double price) throws Exception {

        HttpDecorator httpResponse;
        try{
            Cat cat = service.addCat( title,  desc, price);           
            httpResponse = new HttpOkDecorator(new HttpObjectOk(cat));
           
        }catch(Exception ex){
            httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, ex.getMessage()),HttpStatus.BAD_REQUEST);
        }

        return httpResponse.ReturnObjectMsg();

	}

    @PostMapping( value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AbstractHttpObject> createCat(@RequestBody Cat catHttp) throws Exception {
 
        HttpDecorator httpResponse;
        try{
            Cat cat = service.addCat(  catHttp.getTitle(), catHttp.getDescription(), catHttp.getPrice());           
            httpResponse = new HttpOkDecorator(new HttpObjectOk(cat));
           
        }catch(Exception ex){
            httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, ex.getMessage()),HttpStatus.BAD_REQUEST);
        }

        return httpResponse.ReturnObjectMsg();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<AbstractHttpObject> getSingleCat(@PathVariable("id") long id) throws Exception {


        HttpDecorator httpResponse;
        try{
            Cat cat = service.getCat(id); 
            httpResponse = new HttpOkDecorator(new HttpObjectOk(cat));
           
        }catch(Exception ex){
            httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, ex.getMessage()),HttpStatus.NOT_FOUND);
        }

        return httpResponse.ReturnObjectMsg();

	}
    @PutMapping({"/{id}"})
    public ResponseEntity<AbstractHttpObject> updateCat(@PathVariable("id") long id, @RequestBody Cat catHttp ) throws Exception {


        HttpDecorator httpResponse;
        try{
            Cat cat = service.updateCat(id,catHttp.getTitle(), catHttp.getDescription(), catHttp.getPrice());
            httpResponse = new HttpOkDecorator(new HttpObjectOk(cat));
           
        }catch(Exception ex){
            httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, ex.getMessage()),HttpStatus.NOT_FOUND);
        }

        return httpResponse.ReturnObjectMsg();

	}
    @DeleteMapping({"/delete/{todoId}"})
    public ResponseEntity<AbstractHttpObject> deleteCat(@PathVariable("id") long id ) throws Exception {

        HttpDecorator httpResponse;
        try{
            boolean result = service.removeCat(id);
            httpResponse = new HttpOkDecorator(new HttpObjectOk(result));
           
        }catch(Exception ex){
            httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, ex.getMessage()),HttpStatus.NOT_FOUND);
        }

        return httpResponse.ReturnObjectMsg();
	}
}
