using OpenQA.Selenium;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Macro
{
    public class GangnamZero : CafeList
    {
        public GangnamZero(string date, string time, string theme, string url)
        {
            this.goalDate = date;
            this.goalTime = time;
            this.goalTheme = theme;
            this.url = url;
        }

        public override void Run()
        {
            base.Run();

            try
            {
                //날짜먼저
                var dateBtn = driver.FindElements(By.CssSelector("#calendar > div > div > div > div > div.datepicker--cells.datepicker--cells-days > div")).Where(x => x.Text == goalDate).First();
                dateBtn.Click();

                //테마
                List<IWebElement> webElement = driver.FindElements(By.CssSelector("#themeChoice .hover2")).Where(x => x.Text.Contains(goalTheme)).ToList();

                //엘레멘트 있을때까지 리로드
                while (webElement.Count==0)
                {
                    Thread.Sleep(1000);

                    dateBtn.Click();
                    dateBtn.Click();
                    webElement = driver.FindElements(By.CssSelector("#themeChoice .hover2")).Where(x => x.Text.Contains(goalTheme)).ToList();
                }

                //있으면 클릭
                webElement.ElementAt(0).Click();

                //시간
                List<IWebElement> times = driver.FindElements(By.CssSelector("#themeTimeWrap .hover2")).Where(x=>x.GetAttribute("value") == goalTime+":00").ToList();
                

                //계속
                driver.FindElement(By.Id("nextBtn")).Click();

                //다음페이지
                //이름
                driver.FindElement(By.CssSelector("#reservationForm input[name='name']")).SendKeys("김영주");

                //핸드폰
                driver.FindElement(By.CssSelector("#reservationForm input[name='phone']")).SendKeys("01074552960");


                //인원
                driver.FindElement(By.CssSelector("#reservationForm select[name='people']")).Click();
                driver.FindElements(By.CssSelector("#reservationForm select[name='people'] option"))[2].Click();

                //driver.ExecuteScript("document.getElementsByName(\"people\")[0].selectedIndex = 2;", null);

                //개인정보
                driver.FindElement(By.CssSelector(".step2-policy span")).Click();

                //driver.ExecuteScript("document.getElementsByName(\"policy\")[0].click();", null);

                //예약하기

                driver.FindElement(By.Id("reservationBtn")).Click();

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
