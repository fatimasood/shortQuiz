package com.example.quiz;

public class question {
        private String ques;
        private String opt1;
        private String opt2;
        private int ansNr;

        public question(String s, String an_operating_system, String a_web_browser, String web_server, int i) {

        }

        public question(String ques, String opt1, String opt2, int ansNr) {
                this.ques = ques;
                this.opt1 = opt1;
                this.opt2 = opt2;
                this.ansNr = ansNr;
        }

        public String getQues() {

                return ques;
        }

        public void setQues(String question) {
                this.ques = ques;
        }

        public String getOption1() {
                return opt1;
        }

        public void setOption1(String option1) {
                this.opt1 = opt1;
        }

        public String getOption2() {
                return opt2;
        }

        public void setOption2(String option2) {
                this.opt2 = opt2;
        }

        public int getansNr() {
                return ansNr;
        }

        public void setAnswerNr(int answerNr) {
                this.ansNr = ansNr;
        }
}
