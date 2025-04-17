using OpenQA.Selenium;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;

namespace Macro
{
    public class Souju : CafeList
    {
        public Souju(string date, string time, string theme, string url)
        {
            this.goalDate = date;
            this.goalTime = time;
            this.goalTheme = theme;
            this.url = url;
        }

        public override void Run()
        {
            
            base.Run();
            string theme_display_className = "theme_box";
            try
            {
                List<IWebElement> webElement = driver.FindElements(By.ClassName(theme_display_className)).ToList();

                //엘레멘트 있을때까지 리로드
                while (webElement.Count == 0)
                {
                    driver.Navigate().GoToUrl(url);
                    webElement = driver.FindElements(By.ClassName(theme_display_className)).ToList();

                    Thread.Sleep(300);
                }

                driver.FindElements(By.ClassName(theme_display_className)).Where(x => x.FindElement(By.ClassName("h3_theme")).Text.Contains(goalTheme)).FirstOrDefault()
                .FindElements(By.TagName("li")).Where(x => x.FindElement(By.ClassName("time")).Text == goalTime).FirstOrDefault().Click();

                //다음페이지
                //이름
                driver.FindElement(By.Name("register")).FindElement(By.Name("name")).SendKeys("김영주");

                //핸드폰
                driver.FindElement(By.Name("register")).FindElement(By.Name("mobile2")).SendKeys("7455");
                driver.FindElement(By.Name("register")).FindElement(By.Name("mobile3")).SendKeys("2960");

                //체크박스
                driver.FindElement(By.Name("register")).FindElement(By.Name("ck_agree")).Click();

                //예약하기
                driver.FindElement(By.Name("register")).FindElement(By.ClassName("write_ok")).Click();

                //결제하기
                driver.FindElement(By.ClassName("btn_C_Area")).FindElement(By.ClassName("write_ok")).Click();
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
