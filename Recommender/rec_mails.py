import time
from itertools import chain
import email
import imaplib


def email_receiver():
    mail = imaplib.IMAP4_SSL('imap.gmail.com')
    mail.login("imdb.random@gmail.com", "xnkdawrqxaghnxqv")
    mail.list()
    mail.select('inbox')
    result, data = mail.uid('search', None, "ALL")
    i = len(data[0].split())

    response_ratings = dict()

    for x in range(i):
        latest_email_uid = data[0].split()[x]
        result, email_data = mail.uid('fetch', latest_email_uid, '(RFC822)')
        raw_email = email_data[0][1]
        raw_email_string = raw_email.decode('utf-8')
        email_message = email.message_from_string(raw_email_string)
        str_msg = str(email_message)

        frm = ""
        for ms in str_msg.split("\n"):
            if ms.find("From") != -1:
                frm = ms
        sender_msg = frm[frm.find("<")+1:frm.find(">")]

        body = ""
        for part in email_message.walk():
            if part.get_content_type() == "text/plain":
                body = part.get_payload(decode=True)

        body = str(body)
        body = body[2:-1]
        ratings = dict()
        for line in body.split("\\r\\n"):
            if line == "":
                break
            if not (line.split(" ")[1]).isnumeric():
                break
            ratings[line.split(" ")[0]] = float(line.split(" ")[1])

        response_ratings[sender_msg] = ratings

    mail.logout()
    return response_ratings
