Please use Java and Selenium webdriver to automate this scenario.
You can store the test data in an external file (properties file, txt file, Excel file, etc.)
Navigate to www.credible.com//?optimizely_disable=true

Click on the Personal Loans tab and enter a loan amount ($15k should be good)

Personal Step
- Verify Personal step header text
- Complete input fields as well as verify the “Annual income” tool-tip’s text (Use fake test data not your own)
Profile Step
- Verify Profile step header text
- Complete input fields as well as verify the three tool-tip’s text (Use fake test data here as well)
- For Email use the following format
yourEmail+Jan01+1125@domain.com (your email + current date + current time @ domain name)
- Click on the ‘Find My Rates’ button to submit
Feel free to add other validations as well.

------------------------------------------------
RUN INSTRUCTIONS
------------------------------------------------
Use this for running from maven, other parameters like URL etc also can be added later

-P SmokeTest clean test

To run Locally - run MainTest from IDE