import smtplib, ssl
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText

to = "collegebren@knights.ucf.edu"
subject = "HTML test"
body = """\
<html>
    <head></head>
    <title>Test email</title>
    <body>
        <p>You have successfully received an email sent by Python!</p>
        <p>I tried this with Java, but it:</p>
        <ul>
            <li>Sucked</li>
            <li>Was outdated</li>
            <li>Didn't wanna work</li>
        </ul>
    </body>
</html>
"""

msg = MIMEMultipart('alternative')
msg['Subject'] = subject
msg['From'] = "dev.mymeet@gmail.com"
msg['To'] = to

msg.attach(MIMEText(body, 'html'))
context = ssl.create_default_context()
with smtplib.SMTP_SSL("smtp.gmail.com", 465, context=context) as server:
    server.login("dev.mymeet@gmail.com", "buYbx*M@")    # Bad Brendan, bad security practice
    server.sendmail("dev.mymeet@gmail.com", to, msg.as_string())
    server.quit()

exit()