namespace WebApplication1.wwwroot
{
    public class Person
    {
        public string Name { get; set; }

        public void person(string name)
        {
            this.Name = name;
        }

        public string sayhello(string hello)
        {
            return this.Name + ":" + hello;
        }
    }

    public class Crew : Person
    {
        public string Name { get; set; }

        
    }
}
