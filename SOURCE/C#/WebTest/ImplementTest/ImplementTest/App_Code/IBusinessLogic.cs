using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;


public interface IBusinessLogic
{
    void Initialize();
}

public abstract class ABusinessLogic
{
    public abstract void abstractTest();
    public string Initialize()
    {
        Random r = new Random();
        return r.Next().ToString();
    }
}