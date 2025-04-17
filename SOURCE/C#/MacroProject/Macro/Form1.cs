using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Support.UI;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;
using WebDriverManager;
using WebDriverManager.DriverConfigs.Impl;

namespace Macro
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string goalDate = dateTb.Text;
            string goalTime = HourTb.Text+":"+MinuteTb.Text;
            string goalTheme = themeName.Text;
            string branch = branchTb.Text;
            CafeList cafe = null;
            switch (cafeName.SelectedItem.ToString())
            {
                case "소우주":
                    cafe = new Souju(goalDate, goalTime, goalTheme, "http://sowoojoo-escape.com/layout/res/home.php?rev_days=2022-"+goalDate+"&s_theme_num=&go=rev.make");
                    break;
                case "키이스케이프":
                    cafe = new KeyEsc(goalDate, goalTime, goalTheme, "https://keyescape.co.kr/web/home.php?go=rev.make", branch);
                    break;
                case "홍대제로월드":
                    cafe = new GangnamZero(goalDate, goalTime, goalTheme, "https://www.zerohongdae.com/reservation");
                    break;
                case "황금열쇠":
                    cafe = new GoldenKey(goalDate, goalTime, goalTheme, "http://황금열쇠.com/layout/res/home.php?rev_days=2023-"+goalDate+"&s_zizum=6&go=rev.make", branch);
                    break;
            }
            cafe.Run();

        }

        private void cafeName_SelectedIndexChanged(object sender, EventArgs e)
        {
            if((sender as ComboBox).SelectedIndex == 1)
            {
                branchPanel.Visible =true;
            }
            else
            {
                branchPanel.Visible =false;
            }
        }

    }
}
