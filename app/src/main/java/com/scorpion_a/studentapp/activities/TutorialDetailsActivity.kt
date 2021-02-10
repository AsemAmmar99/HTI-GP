package com.scorpion_a.studentapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.scorpion_a.studentapp.R
import kotlinx.android.synthetic.main.activity_profile_page.*
import kotlinx.android.synthetic.main.activity_profile_page.header
import kotlinx.android.synthetic.main.activity_tutorial_details.*

class TutorialDetailsActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial_details)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title= intent.getStringExtra("pagetitle")
        var txt= intent.getStringExtra("pagetitle")

        if (txt.equals("Login Screen")) {
            tvTutorialDetails.text =
                "- The Login page is the main and the first page that will appear to you in this Application, you can change your Language form English to Arabic and From Arabic to English by turning the switch on or off and you can go to Help Page (User Assistant Page) to help you to use the Application.\n\n" +
                        "- You can Login into your Profile by using the ID and the Password as the same ID and the Password of the WebSite, you can save the ID and the Password by pressing the Remeber me button and if you logged out and login again you will see them in the specific text fields.\n\n" +
                        "- If you forget your password, you can change it by pressing (Forgot Your Password?) button. It will appear to you an alert to send an email to (info@hti.edu.eg). Send your id, and the password will be sent to you within 24 work hours after sending your email."
        }else if(txt.equals("Home Screen")){
            tvTutorialDetails.text =
                "- Home page is the main page after logging successfully, The Department will appear in the top of the page, and in the bottom you can see the BottomNavigationBar which you can use to navigate between your main pages that you can use  (Requests, Notification, More page).\n\n" +
                        "- The Home page contains two sections first section is event section that can notify the new event that you can follow the updates, you have a more button that can guide you to the Events Page to see more, and you can access the section by swipe it right and left and press on the event to view more.\n\n" +
                        "- The second section is news section that can notify the newest news that you can follow to know the updates, you can access it by the same way of the event section."
            }else if (txt.equals("Events and News")){
            tvTutorialDetails.text =
                "- Events and News pages holds the newest news and the newest events you can access them by swipe it up and down.\n\n" +
                        "- if you select a news or event then you will move a new page that hold the image of the news or event, description of the news, title of the news and the date of the news in Time/Day/Month/Year Format."
        }else if (txt.equals("Requests Screen")){
            tvTutorialDetails.text =
            "- The Requests Page, you can find it in the TabBar and you can select it by pressing of the name or the image in TabBar, this page contains the Requests depend on the Department and the statues of you (Student, graduated Student).\n\n" +
                    "- You can select a request by pressing on his name, then the page will appear from the bottom of the page you can select how many copies you need, and hold the notes of this request, after selecting the number of copies an alert will appear for you asking for the reset of the CIB Bank and Are you paied or not? and your answer will depend on your statues.\n\n" +
                    "- If you payed the money and have the CIB Reset you will transfare Automatically to confirmation requests page and you can review your information and edit it and upload your reset photo and press send request you will take a request number and changed automatically to my requests page and show the request number , request discription, date and time and request Statues (Accepted - pending - Rejected) , you will be notified anyway."
        }else if (txt.equals("Notifications Screen")){
            tvTutorialDetails.text =
                "- Notification Page, you can find it in BottomNavigationBar and you can access it by pressing on the image in the BottomNavigationBar.\n\n" +
                        "- You will see the title of notification , description of notification and time of the notification, you can access it by pressing on the notification you need and you can see the more details of the notification.\n\n"
        }else if (txt.equals("Profile Page")){
            tvTutorialDetails.text =
                "- The profile page, you can access it by more button in BottomNavigationBar and you can see your profile info like (Image, Arabic and English name, phone , email , gpa , year , Total Units) and you can edit your password from it.\n\n" +
                        "- If you want to change your password you will press the edit password button and type your old password and the new password and retype the new password and make sure the password is strong by mix upper and lower characters with numbers.\n\n" +
                        "- You can change your info by press edit button and you can change Your image , Arabic name , English name, Mobile and Your email if Graduated Student."
        }else if (txt.equals("My Requests Screen")){
            tvTutorialDetails.text =
                "- My request page, you can access it by more button in the BottomNavigationBar, you will see the requests status, request number, request description, Count, and requesting time."
        }else if (txt.equals("Test Yourself Screen")){
            tvTutorialDetails.text =
                "- Test yourSelf page, you can access is by pressing the more button in BottomNavigationBar, in this page you will se your Courses which you registerd in the current semester and can you test yourself by presseing on the subject name, we are collected some Questions in true and false format to test you knowledge in the Course.\n\n" +
                        "In this test, you can press the buttons true and false and show the right answer, it is not a quiz with result or an exam it just for you to make you better and you can back any time."
        }else if (txt.equals("FAQs and Support Screen")){
            tvTutorialDetails.text =
            "- FAQs and Support page you can access it by pressing more button in BottomNavigationBar, you will see the pages divided into section like (Registration - About HTI) and you can press on any button you need and will auto navigate to the more FAQ pages and see the questions and answers.\n\n" +
                    "- Support button, you can use it to send a Question to the HTI Support, In this case your email and ID and Your message will be sent to the HTI Support."
        }
    }
}