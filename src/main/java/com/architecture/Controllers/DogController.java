package com.architecture.Controllers;


import java.util.List;
import java.util.concurrent.ExecutionException;
import com.architecture.Entities.Dog;
import com.architecture.Entities.IDog;
import com.architecture.Services.IDogService;
import com.architecture.CrossCutting.HttpDecorators.HttpDecorator;
import com.architecture.CrossCutting.HttpDecorators.HttpOkDecorator;
import com.architecture.CrossCutting.HttpDecorators.HttpErrorDecorator;
import com.architecture.CrossCutting.HttpDecorators.Objects.AbstractHttpObject;
import com.architecture.CrossCutting.HttpDecorators.Objects.HttpObjectError;
import com.architecture.CrossCutting.HttpDecorators.Objects.HttpObjectOk;
import com.architecture.CrossCutting.PipesFilters.CustomExceptions;
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
@RequestMapping("/api/dog")
public class DogController {

    @Autowired
    private IDogService service;

    @GetMapping("")
	public ResponseEntity<AbstractHttpObject> getDogs() throws Exception {


        HttpDecorator httpResponse;
        try{
            List<Dog> dog = service.getAllDogs();
            httpResponse = new HttpOkDecorator(new HttpObjectOk(dog));
           
        }catch(Exception ex){
            httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, ex.getMessage()),HttpStatus.BAD_REQUEST);
        }

        return httpResponse.ReturnObjectMsg();
	}
    
    @GetMapping("/create")
	public ResponseEntity<AbstractHttpObject> create(@RequestParam(value = "title") String title, @RequestParam(value = "desc") String desc,@RequestParam(value = "price") double price,@RequestParam(value = "size") double size ) throws Exception {

        HttpDecorator httpResponse;

        try{
       
            IDog dog = service.addDog( title, desc,price,size);
            httpResponse = new HttpOkDecorator(new HttpObjectOk(dog));
           
        }catch(ExecutionException ex){
            Throwable cause = ex.getCause();
            if (cause instanceof CustomExceptions) {
                CustomExceptions cause2 = (CustomExceptions) cause;
                httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, cause2.getMyStrings()),HttpStatus.BAD_REQUEST);  
            }else{
                httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, cause.getMessage()),HttpStatus.BAD_REQUEST);  
            }
        }catch(CustomExceptions ex){
            httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, ex.getMyStrings()),HttpStatus.BAD_REQUEST);
        }catch(Exception ex){
            httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, ex.getMessage()),HttpStatus.BAD_REQUEST);
        }
        
        return httpResponse.ReturnObjectMsg();

	}

    @PostMapping( value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AbstractHttpObject> createDog(@RequestBody Dog dogHttp) throws Exception {

        HttpDecorator httpResponse;

        try{

            IDog dog = service.addDog( dogHttp );
            httpResponse = new HttpOkDecorator(new HttpObjectOk(dog));

        }catch(ExecutionException ex){
            Throwable cause = ex.getCause();
            if (cause instanceof CustomExceptions) {
                CustomExceptions cause2 = (CustomExceptions) cause;
                httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, cause2.getMyStrings()),HttpStatus.BAD_REQUEST);  
            }else{
                httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, cause.getMessage()),HttpStatus.BAD_REQUEST);  
            }
        }catch(CustomExceptions ex){
            httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, ex.getMyStrings()),HttpStatus.BAD_REQUEST);
        }catch(Exception ex){
            httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, ex.getMessage()),HttpStatus.BAD_REQUEST);
        }

        return httpResponse.ReturnObjectMsg();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<AbstractHttpObject> getSingleDog(@PathVariable("id") long id) throws Exception {
        
        HttpDecorator httpResponse;
        try{
            Dog dog = service.getDog(id);            
            httpResponse = new HttpOkDecorator(new HttpObjectOk(dog));
           
        }catch(Exception ex){
            httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, ex.getMessage()),HttpStatus.NOT_FOUND);
        }

        return httpResponse.ReturnObjectMsg();

	}
    @PutMapping({"/{id}"})
    public ResponseEntity<AbstractHttpObject> updateDog(@PathVariable("id") long id, @RequestBody Dog dogHttp ) throws Exception {
        
        HttpDecorator httpResponse;
        try{
            IDog dog = service.updateDog(id, dogHttp);
            httpResponse = new HttpOkDecorator(new HttpObjectOk(dog));
        }catch(ExecutionException ex){
            Throwable cause = ex.getCause();
            if (cause instanceof CustomExceptions) {
                CustomExceptions cause2 = (CustomExceptions) cause;
                httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, cause2.getMyStrings()),HttpStatus.BAD_REQUEST);  
            }else{
                httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, cause.getMessage()),HttpStatus.BAD_REQUEST);  
            }
        }catch(CustomExceptions ex){
            httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, ex.getMyStrings()),HttpStatus.BAD_REQUEST);
        }catch(Exception ex){
            httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, ex.getMessage()),HttpStatus.BAD_REQUEST);
        }
        return httpResponse.ReturnObjectMsg();

	}
    @DeleteMapping({"/delete/{todoId}"})
    public ResponseEntity<AbstractHttpObject> deleteDog(@PathVariable("id") long id ) throws Exception {

        HttpDecorator httpResponse;
        try{
            boolean result = service.removeDog(id);
            httpResponse = new HttpOkDecorator(new HttpObjectOk(result));
           
        }catch(Exception ex){
            httpResponse = new HttpErrorDecorator(new HttpObjectError(400.0, ex.getMessage()),HttpStatus.NOT_FOUND);
        }

        return httpResponse.ReturnObjectMsg();
	}

    
}
