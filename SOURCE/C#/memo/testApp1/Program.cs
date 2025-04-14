using System;
using System.IO;
using System.Net;
using System.Text;


namespace testApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            string url = "https://www.0srookpay.co.kr/newsrp/InicisApi/test";
            //string url = "https://www.keyescape.co.kr/web/rev.zizum_info.php";
            //string postData = "{\"zizum_num\":\"18\",\"rev_days\":\"2023-11-05\"}";
            string postData = "";
            //string postData = "{zizum_num:18,rev_days:2023-11-05}";

            byte[] data = Encoding.UTF8.GetBytes(postData);

            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
            request.Method = "POST";
            //request.ContentType = "application/json";
            request.ContentType = "text/html; charset=UTF-8";
            request.ContentLength = data.Length;

            using (Stream stream = request.GetRequestStream())
            {
                stream.Write(data, 0, data.Length);
            }

            using (HttpWebResponse response = (HttpWebResponse)request.GetResponse())
            using (Stream responseStream = response.GetResponseStream())
            using (StreamReader reader = new StreamReader(responseStream))
            {
                string jsonResponse = reader.ReadToEnd();
                Console.WriteLine(jsonResponse);
            }
        }
    }
}
