package com.architecture.templateHttp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class TemplateHttp {

        HttpStatus Status;
        boolean Success;
        Object Data;

        public ResponseEntity<ObjectHttp> TemplateResponse(Object data )
        {
            SetSuccess();
            SetMessage(data);
            return ReturnObjectMsg();
            
        }
        
        protected abstract void SetSuccess();
        protected abstract void SetMessage(Object data );


        private ResponseEntity<ObjectHttp> ReturnObjectMsg()
        {
            ObjectHttp object = new ObjectHttp(Success, Data);
            return new ResponseEntity<>(object, Status);
        }


    }