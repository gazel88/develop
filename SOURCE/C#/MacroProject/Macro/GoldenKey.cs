using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Macro
{
    public class GoldenKey : CafeList
    {
        public string branch { get; set; }
        public GoldenKey(string date, string time, string theme, string url, string branch)
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
                //driver.FindElements(By.CssSelector("form[name=register] select")).First().Click();
                //driver.FindElements(By.CssSelector("form[name=register] select option")).Where(x => x.Text.Contains(branch)).First().Click();

                ////일자
                //driver.ExecuteScript(String.Format("$('#rev_days').val('2023-{0}');fun_rev_change();", goalDate));


                //시간 -wait
                WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromMilliseconds(2000));
                wait.Until(x => x.FindElement(By.CssSelector(".theme_box")));
                
                //테마
                var div = driver.FindElements(By.CssSelector(".theme_box")).Where(x => x.FindElements(By.TagName("h3")).FirstOrDefault().Text.Contains(goalTheme)).First();

                var timetag = div.FindElements(By.CssSelector(".time")).Where(x => x.Text.Contains(goalTime)).First();

                if (timetag == null)
                {
                    throw new Exception("no time table enabled");
                }

                timetag.Click();

                //다음페이지
                //이름
                driver.FindElement(By.Name("register")).FindElement(By.Name("name")).SendKeys("김영주");

                //핸드폰
                driver.FindElement(By.Name("register")).FindElement(By.Name("mobile2")).SendKeys("7455");
                driver.FindElement(By.Name("register")).FindElement(By.Name("mobile3")).SendKeys("2960");

                //체크박스
                driver.FindElement(By.Name("register")).FindElement(By.Name("ck_agree")).Click();

                //예약하기
                driver.FindElements(By.TagName("form")).First().FindElement(By.ClassName("write_ok")).Click();

                //결제하기
                driver.FindElement(By.ClassName("btn_C_Area")).FindElement(By.TagName("button")).Click();
            }
            catch (Exception ex)
            {

            }
            finally
            {
                driver.Quit();
            }
        }
    }
}
