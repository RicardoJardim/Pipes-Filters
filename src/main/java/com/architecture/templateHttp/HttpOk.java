public class HttpOk extends AbstractTemplateHttp
{
    @Override
    protected void SetSuccess()
    {
        Success = true;
    }

    @Override
    protected void SetMessage(Object aMsg)
    {
        Data = aMsg;
    }

}