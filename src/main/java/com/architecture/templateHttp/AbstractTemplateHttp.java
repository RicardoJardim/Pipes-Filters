public abstract class AbstractTemplateHttp
    {
        public boolean Success { get; set; }
        public Object Data { get; set; }
        public Object TemplateResponse(Object data )
        {
            SetSuccess();
            SetMessage(data);
            return ReturnObjectMsg();
            
        }
        
        protected abstract void SetSuccess();
        protected abstract void SetMessage(Object data );


        private Object ReturnObjectMsg()
        {
            return new ObjectHttp(Success, Data);
        }


    }