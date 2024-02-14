using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;


public class BusinessLogic : IBusinessLogic
{
    public void Initialize()
    {
        Random r = new Random();
        r.Next();
    }
    BusinessLogic2 bl = new BusinessLogic2();
    
}

public class BusinessLogic2 : ABusinessLogic
{
    public BusinessLogic2()
    {

    }
    public override void abstractTest()
    {
        Random r = new Random();
        r.Next();
    }

    
}
