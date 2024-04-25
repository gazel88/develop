using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Support.UI;
using System;
using System.Web.Mvc;

namespace sel.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {

            return View();
        }

        public ActionResult Work()
        {
            // Chrome WebDriver를 초기화합니다.
            IWebDriver driver = new ChromeDriver();

            // 웹사이트에 접속합니다.
            driver.Navigate().GoToUrl("https://gw.srook.net/login");

            try
            {
                // ID 입력란을 찾고 값을 입력합니다.
                IWebElement idInput = driver.FindElement(By.Id("username"));
                idInput.SendKeys("kyj8");

                // 비밀번호 입력란을 찾고 값을 입력합니다.
                IWebElement pwInput = driver.FindElement(By.Id("password"));
                pwInput.SendKeys("kyj@0@11201");

                // 로그인 버튼을 찾고 클릭합니다.
                IWebElement loginButton = driver.FindElement(By.Id("login_submit"));
                loginButton.Click();

                // 로그인이 완료될 때까지 대기합니다.
                WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(1000));
                wait.Until(d => d.Url.Contains("home"));

                // 일정 버튼이 나타날 때까지 대기합니다.
                wait.Until(d => d.FindElement(By.Id("workIn")).Displayed);

                // 일정 버튼을 찾고 클릭합니다.
                IWebElement scheduleButton = driver.FindElement(By.Id("workIn"));
                scheduleButton.Click();

                Console.WriteLine("일정 페이지로 이동했습니다.");
            }
            finally
            {
                // WebDriver를 종료합니다.
                driver.Quit();
            }

            return View("index");
        }
    }
}
