using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TestApp1
{
    class EnumTest
    {
        public static void enumTest()
        {
            string stringValue = "kakao";

            // Enum.TryParse를 사용하여 문자열을 Enum으로 변환 (대소문자 무시)
            if (Enum.TryParse(stringValue, true, out oAuthType enumValue))
            {
                Console.WriteLine($"Enum value: {enumValue}");
            }
            else
            {
                Console.WriteLine("유효하지 않은 문자열 값입니다.");
            }

        }
    }

    public enum oAuthType
    {
        EMAIL = 0,
        KAKAO = 1,
        NAVER = 2,
        APPLE = 3,
        FACEBOOK = 4,
        GOOGLE = 5,
        KAKAOSYNC = 6
    }
}
