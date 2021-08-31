public class HttpNotFound extends AbstractTemplateHttp
{
    @Override
    protected void SetSuccess()
    {
        Success = false;
    }

    @Override
    protected void SetMessage(Object aMsg)
    {
        Data = aMsg;
    }

}