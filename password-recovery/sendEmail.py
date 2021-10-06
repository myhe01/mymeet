#   File name:  sendEmail.py
#
#   Purpose:    Given command line arguments, send an email containing HTML to the specified
#               address.
#
#   Run:        python3.9 ./password-recovery/sendEmail.py arg0 arg1 arg2
#                   arg0: Password to dev.mymeet@gmail.com
#                   arg1: Target receiver
#                   arg2: HTML file
#

import smtplib, ssl
import sys
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText

if __name__ == "__main__":
    password = sys.argv[1]
    to = sys.argv[2]
    body = open(sys.argv[3], "r")

msg = MIMEMultipart('alternative')
msg['Subject'] = "Test Subject"         #TODO: subject
msg['From'] = "dev.mymeet@gmail.com"
msg['To'] = to
msg.attach(MIMEText(body.read(), 'html'))

context = ssl.create_default_context()
with smtplib.SMTP_SSL("smtp.gmail.com", 465, context=context) as server:
    server.login("dev.mymeet@gmail.com", password)
    server.sendmail("dev.mymeet@gmail.com", to, msg.as_string())
    server.quit()

exit()