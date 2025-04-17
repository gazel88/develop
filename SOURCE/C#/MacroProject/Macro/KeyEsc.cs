using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

public class KeyEsc : CafeList
{
    public string branch { get; set; }
    public KeyEsc(string date, string time, string theme, string url, string branch)
    {
        this.goalDate = date;
        this.goalTime = time;
        this.goalTheme = theme;
        this.url = url;
        this.branch = branch;
    }

    public override void Run()
    {
        base.Run();

        try
        {
            //지점
            driver.FindElements(By.CssSelector(".revList1 a")).Where(x => x.Text.Contains(branch)).First().Click();

            //일자
            driver.FindElements(By.CssSelector(".calendar_day a")).Where(x => x.Text.Contains(goalDate)).First().Click();

            //테마
            driver.FindElements(By.CssSelector(".revList2 a")).Where(x => x.Text.Contains(goalTheme)).First().Click();

            //시간 -wait
            WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromMilliseconds(2000));
            wait.Until(x => x.FindElement(By.CssSelector(".rev4 li")));

            var timetag = driver.FindElements(By.CssSelector(".rev4 a")).Where(x => x.Text == goalTime).First();

            if(timetag == null)
            {
                throw new Exception("no time table enabled");
            }

            timetag.Click();

            //예약하기
            driver.FindElements(By.TagName("form")).First().FindElement(By.ClassName("b")).Click();

            //다음페이지
            //이름
            driver.FindElement(By.Name("register")).FindElement(By.Name("name")).SendKeys("김영주");

            //핸드폰
            driver.FindElement(By.Name("register")).FindElement(By.Name("mobile2")).SendKeys("7455");
            driver.FindElement(By.Name("register")).FindElement(By.Name("mobile3")).SendKeys("2960");

            //스팸방지
            string spmaCode = driver.FindElement(By.Name("register")).FindElement(By.ClassName("spam_code")).Text.Trim();
            driver.FindElement(By.Name("register")).FindElement(By.Name("str_spam")).SendKeys(spmaCode);

            //체크박스
            driver.FindElement(By.Name("register")).FindElement(By.Name("ck_agree")).Click();

            //예약하기
            driver.FindElements(By.TagName("form")).First().FindElement(By.ClassName("b")).Click();

            //결제하기
            driver.FindElement(By.ClassName("btn_C_Area")).FindElement(By.TagName("a")).Click();
        }
        catch(Exception ex)
        {

        }
        finally
        {
            driver.Quit();
        }

    }

}
