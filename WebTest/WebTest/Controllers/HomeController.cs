using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Drawing;
using System.Drawing.Drawing2D;
using System.Threading.Tasks;
using WebTest.Models;
using StackExchange.Redis;
using Confluent.Kafka;
using System.Collections;
using System.Linq;

namespace WebTest.Controllers
{
    public class HomeController : Controller
    {
        private readonly ILogger<HomeController> _logger;

        public HomeController(ILogger<HomeController> logger)
        {
            _logger = logger;
        }

        public IActionResult Index()
        {
            //KafkaConsumeTest();

            DictionaryTest();
            SortedDicTest();


            return View();
        }

        public IActionResult Privacy()
        {
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }

        [HttpPost]
        public JsonResult FileTest()
        {
            dynamic data = null;

            data = JsonConvert.DeserializeObject(Request.Form["file"]);
            Image image = Image.FromFile(data);
            Bitmap b = new Bitmap(50, 50);
            Graphics g = Graphics.FromImage((Image)b);
            g.InterpolationMode = InterpolationMode.HighQualityBicubic;
            g.DrawImage(image, 0, 0, 50, 50);
            Image thumbnail = (Image)b;

            

            return Json(new { result = "ok" });
        } 

        [HttpPost]
        public JsonResult RediseTest([FromBody]testorgin key)
        {
            ConnectionMultiplexer redis = ConnectionMultiplexer.Connect(new ConfigurationOptions { EndPoints = { "127.0.0.1:6379" }, AbortOnConnectFail = true, ConnectTimeout = 8000, ConnectRetry = 5});
            if (redis.IsConnected)
            {
                IDatabase db = redis.GetDatabase();
                //testorgin obj = JsonConvert.DeserializeObject<testorgin>(key);



                db.StringSet(key.key, JsonConvert.SerializeObject(key.value));
            }


            return Json(new { result = "ok" });
        }

        public class testorgin
        {
            public string key { get; set; }
            public testModel value { get; set; }
        }
        public class testModel
        {
            public string jsontest {
                get { return jsontest; }
                set { jsontest = "hello"; } 
            }
            public string jsontest2 { get; set; }
        }

        public async Task<IActionResult> KafkaProduceTest()
        {

            var config = new ProducerConfig
            {
                BootstrapServers = "127.0.0.1:9092"
            };

            using(var producer = new ProducerBuilder<Null, string>(config).Build())
            {
                await producer.ProduceAsync("kafkatest", new Message<Null, string> { Value = "webformTest"}); //토픽, 키 밸류
            
            }

            return View();
        }

        public IActionResult KafkaConsumeTest()
        {
            var config = new ConsumerConfig
            {
                BootstrapServers = "127.0.0.1:9092",
                GroupId = "foo",                                                //required
                AutoOffsetReset = AutoOffsetReset.Earliest                      //offset
                
            };

            using (var consumer = new ConsumerBuilder<Ignore, string>(config).Build())
            {
                try
                {
                    consumer.Subscribe("kafkatest");
                    int i = 0;
                    while (i++<100)
                    {
                        var consumeResult = consumer.Consume(10000);
                        
                        Console.WriteLine("토픽="+consumeResult.Topic+", 메세지="+consumeResult.Message.Value+ ", offset = "+consumeResult.Offset);
                        if (i == 3)
                        {
                            //5초 지나기 전에 에러 throw
                            throw new Exception("test용");
                        }
                    }
                    consumer.Close();

                }
                catch (Exception ex)
                {
                    consumer.Close();

                }
            }
            return View();
        }

        public void hashsetTest()
        {
            HashSet<KeyValuePair<string, string>> hs = new HashSet<KeyValuePair<string, string>>();
            HashSet<KeyValuePair<string, string>> hs2 = new HashSet<KeyValuePair<string, string>>();

            hs.Add(new KeyValuePair<string, string>("개발팀", "김영주"));
            hs.Add(new KeyValuePair<string, string>("개발팀", "민기낭"));
            hs.Add(new KeyValuePair<string, string>("기획팀", "신희섭"));
            hs2.Add(new KeyValuePair<string, string>("개발팀", "김영주"));
            hs2.Add(new KeyValuePair<string, string>("기획팀", "이준호"));
            hs2.Add(new KeyValuePair<string, string>("운영팀", "이인주"));
            HashSet<KeyValuePair<string, string>> inter = new HashSet<KeyValuePair<string, string>>(hs);
            inter.IntersectWith(hs2);

            
            Console.WriteLine(hs2);
        }

        public void DictionaryTest()
        {
            Dictionary<object, object> dic = new Dictionary<object, object>();

            //불가
            //dic.Add("개발팀", "김영주");
            //dic.Add("개발팀", "강희욱");
            //dic.Add("개발팀", "민기낭");
            //dic.Add("개발팀", "홍길동");
            //dic.Add("운영팀", "고길동");
            //dic.Add("운영팀", "오인용");

            dic.Add("개발팀", new List<string> { "김영주", "고길동", "홍길동" });
            dic.Add("운영팀", new List<string> { "김영주", "오인용", "삼박자" });
            dic.Add(1, "test" );
            dic.Add(3, "test" );
            dic.Add(2, "test" );

            foreach(var item in dic)
            {
                Console.WriteLine($"{item.Key} : {item.Value}");
            }


            var aa = dic.Keys.ElementAt(0);

            var user = dic[1];
            Console.WriteLine(user);
        }

        public void hashTableTest()
        {
            Hashtable ht = new Hashtable();
            ht.Add("개발팀", "김영주");
            ht.Add(1, new List<string>{ "test" });

            string user = ht[1].ToString();
            Console.WriteLine(user);
        }

        public void SortedDicTest()
        {
            SortedDictionary<object, object> sd = new SortedDictionary<object, object>();

            //sd.Add("개발팀", "test");
            //sd.Add("운영팀", "test");
            sd.Add(1, "test");
            sd.Add(3, "test");
            sd.Add(2, "test");

            foreach (var item in sd)
            {
                Console.WriteLine($"{item.Key} : {item.Value}");
            }

        }
    }
}
