using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using WebDriverManager;
using WebDriverManager.DriverConfigs.Impl;

public abstract class CafeList
{
    public string goalDate;
    public string goalTime;
    public string goalTheme;
    public string url;
    public static ChromeDriver driver;

    public virtual void Run()
    {
        new DriverManager().SetUpDriver(new ChromeConfig());
        driver = new ChromeDriver();

        try
        {
            //예약페이지로 이동
            driver.Navigate().GoToUrl(url);
            Console.Read();
        }
        catch (Exception ex)
        {
            throw new Exception("page open error");
        }
    }
}
