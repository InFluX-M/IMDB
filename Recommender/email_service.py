import smtplib, ssl
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart

port = 465  # For SSL
smtp_server = "smtp.gmail.com"
sender_email = "imdb.random@gmail.com"  # Enter your address
password = "xnkdawrqxaghnxqv"


def send_recommend_movies_to_user_mail(receiver_email, recommend_movies):
    message = MIMEMultipart("alternative")
    message["Subject"] = "Recommend Movies"
    message["From"] = sender_email
    message["To"] = "azami1382@gmail.com"

    body = ""
    for movie in recommend_movies:
        body += movie + "\t" + str(recommend_movies[movie][1]) + "\t" + str(round(recommend_movies[movie][0], 1)) + "\n"

    # Create the plain-text and HTML version of your message
    text = f"Hi,\nHow are you?\n{body}"

    # html = f"\
    # <html>\
    #   <body>\
    #     <p>\
    #         Hi,<br>\
    #         How are you?<br>\
    #         {body}\
    #         \
    #     </p>\
    #   </body>\
    # </html>\
    # "

    # Turn these into plain/html MIMEText objects
    part1 = MIMEText(text, "plain")
    # part2 = MIMEText(html, "html")

    # Add HTML/plain-text parts to MIMEMultipart message
    # The email client will try to render the last part first
    message.attach(part1)
    # message.attach(part2)

    # Create secure connection with server and send email
    context = ssl.create_default_context()
    with smtplib.SMTP_SSL("smtp.gmail.com", 465, context=context) as server:
        server.login(sender_email, password)
        server.sendmail(
            sender_email, receiver_email, message.as_string()
        )




