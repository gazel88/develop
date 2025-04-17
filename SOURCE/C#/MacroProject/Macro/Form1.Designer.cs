namespace Macro
{
    partial class Form1
    {
        /// <summary>
        /// 필수 디자이너 변수입니다.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 사용 중인 모든 리소스를 정리합니다.
        /// </summary>
        /// <param name="disposing">관리되는 리소스를 삭제해야 하면 true이고, 그렇지 않으면 false입니다.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form 디자이너에서 생성한 코드

        /// <summary>
        /// 디자이너 지원에 필요한 메서드입니다. 
        /// 이 메서드의 내용을 코드 편집기로 수정하지 마세요.
        /// </summary>
        private void InitializeComponent()
        {
            this.themeName = new System.Windows.Forms.TextBox();
            this.dateTb = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.HourTb = new System.Windows.Forms.TextBox();
            this.MinuteTb = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.button1 = new System.Windows.Forms.Button();
            this.cafeName = new System.Windows.Forms.ComboBox();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.branchTb = new System.Windows.Forms.TextBox();
            this.branchPanel = new System.Windows.Forms.Panel();
            this.branchPanel.SuspendLayout();
            this.SuspendLayout();
            // 
            // themeName
            // 
            this.themeName.Location = new System.Drawing.Point(202, 144);
            this.themeName.Name = "themeName";
            this.themeName.Size = new System.Drawing.Size(100, 21);
            this.themeName.TabIndex = 7;
            // 
            // dateTb
            // 
            this.dateTb.Location = new System.Drawing.Point(202, 194);
            this.dateTb.Name = "dateTb";
            this.dateTb.Size = new System.Drawing.Size(100, 21);
            this.dateTb.TabIndex = 1;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("굴림", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.label1.Location = new System.Drawing.Point(119, 194);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(47, 19);
            this.label1.TabIndex = 2;
            this.label1.Text = "날짜";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("굴림", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.label2.Location = new System.Drawing.Point(412, 194);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(47, 19);
            this.label2.TabIndex = 3;
            this.label2.Text = "시간";
            // 
            // HourTb
            // 
            this.HourTb.Location = new System.Drawing.Point(488, 192);
            this.HourTb.Name = "HourTb";
            this.HourTb.Size = new System.Drawing.Size(80, 21);
            this.HourTb.TabIndex = 4;
            // 
            // MinuteTb
            // 
            this.MinuteTb.Location = new System.Drawing.Point(589, 192);
            this.MinuteTb.Name = "MinuteTb";
            this.MinuteTb.Size = new System.Drawing.Size(86, 21);
            this.MinuteTb.TabIndex = 5;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(574, 196);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(9, 12);
            this.label3.TabIndex = 6;
            this.label3.Text = ":";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("굴림", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.label4.Location = new System.Drawing.Point(100, 146);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(66, 19);
            this.label4.TabIndex = 8;
            this.label4.Text = "테마명";
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(589, 324);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(117, 52);
            this.button1.TabIndex = 0;
            this.button1.Text = "시작";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // cafeName
            // 
            this.cafeName.FormattingEnabled = true;
            this.cafeName.Items.AddRange(new object[] {
            "소우주",
            "키이스케이프",
            "강남제로월드",
            "황금열쇠"});
            this.cafeName.Location = new System.Drawing.Point(202, 96);
            this.cafeName.Name = "cafeName";
            this.cafeName.Size = new System.Drawing.Size(121, 20);
            this.cafeName.TabIndex = 9;
            this.cafeName.SelectedIndexChanged += new System.EventHandler(this.cafeName_SelectedIndexChanged);
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Font = new System.Drawing.Font("굴림", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.label5.Location = new System.Drawing.Point(119, 96);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(47, 19);
            this.label5.TabIndex = 10;
            this.label5.Text = "카페";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Font = new System.Drawing.Font("굴림", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.label6.Location = new System.Drawing.Point(7, 34);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(66, 19);
            this.label6.TabIndex = 11;
            this.label6.Text = "지점명";
            // 
            // branchTb
            // 
            this.branchTb.Font = new System.Drawing.Font("굴림", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.branchTb.Location = new System.Drawing.Point(79, 32);
            this.branchTb.Name = "branchTb";
            this.branchTb.Size = new System.Drawing.Size(100, 21);
            this.branchTb.TabIndex = 12;
            // 
            // branchPanel
            // 
            this.branchPanel.Controls.Add(this.branchTb);
            this.branchPanel.Controls.Add(this.label6);
            this.branchPanel.Location = new System.Drawing.Point(416, 67);
            this.branchPanel.Name = "branchPanel";
            this.branchPanel.Size = new System.Drawing.Size(200, 98);
            this.branchPanel.TabIndex = 13;
            this.branchPanel.Visible = false;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.branchPanel);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.cafeName);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.themeName);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.MinuteTb);
            this.Controls.Add(this.HourTb);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.dateTb);
            this.Controls.Add(this.button1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.branchPanel.ResumeLayout(false);
            this.branchPanel.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox dateTb;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox HourTb;
        private System.Windows.Forms.TextBox MinuteTb;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.TextBox themeName;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.ComboBox cafeName;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.TextBox branchTb;
        private System.Windows.Forms.Panel branchPanel;
    }
}

