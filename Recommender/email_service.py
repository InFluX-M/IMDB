import smtplib, ssl
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart
import IMDBapi

port = 465  # For SSL
smtp_server = "smtp.gmail.com"
sender_email = "imdb.random@gmail.com"  # Enter your address
password = "xnkdawrqxaghnxqv"


def send_recommend_movies_to_user_mail(receiver_email, recommend_movies):
    message = MIMEMultipart("alternative")
    message["Subject"] = "Recommend Movies"
    message["From"] = sender_email
    message["To"] = receiver_email

    body = dict()
    poster = dict()
    i = 0
    for movie in recommend_movies:
        body[i] = [movie, str(round(recommend_movies[movie][0], 1)), recommend_movies[movie][1]]
        poster[i] = IMDBapi.get_movie_poster(movie)
        print(poster[i])
        i = i + 1


    # Create the plain-text and HTML version of your message
    text2 = f"""
    <!DOCTYPE html>
<html xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office" lang="en">

<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]-->
<!--[if !mso]><!-->
<link href="https://fonts.googleapis.com/css?family=Permanent+Marker" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Monda" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Oxygen" rel="stylesheet" type="text/css">
<!--<![endif]-->
<style>
* {{
box-sizing: border-box;
}}

body {{
margin: 0;
padding: 0;
}}

a[x-apple-data-detectors] {{
color: inherit !important;
text-decoration: inherit !important;
}}

#MessageViewBody a {{
color: inherit;
text-decoration: none;
}}

p {{
line-height: inherit
}}

.desktop_hide,
.desktop_hide table {{
mso-hide: all;
display: none;
max-height: 0px;
overflow: hidden;
}}

@media (max-width:640px) {{

.desktop_hide table.icons-inner,
.social_block.desktop_hide .social-table {{
display: inline-block !important;
}}

.icons-inner {{
text-align: center;
}}

.icons-inner td {{
margin: 0 auto;
}}

.image_block img.big,
.row-content {{
width: 100% !important;
}}

.mobile_hide {{
display: none;
}}

.stack .column {{
width: 100%;
display: block;
}}

.mobile_hide {{
min-height: 0;
max-height: 0;
max-width: 0;
overflow: hidden;
font-size: 0px;
}}

.desktop_hide,
.desktop_hide table {{
display: table !important;
max-height: none !important;
}}

.row-9 .column-1 .block-5.button_block td.pad {{
padding: 5px !important;
}}

.row-9 .column-1 .block-5.button_block a span,
.row-9 .column-1 .block-5.button_block div,
.row-9 .column-1 .block-5.button_block div span {{
line-height: 2 !important;
}}
}}
</style>
</head>

<body style="background-color: #202020; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;">
<table class="nl-container" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #202020;">
<tbody>
<tr>
<td>
<table class="row row-1" align="center" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-image: url('https://d1oco4z2z1fhwp.cloudfront.net/templates/default/95/dark_geometric.png'); background-position: top center; background-repeat: repeat;">
<tbody>
<tr>
<td>
<table class="row-content stack" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-radius: 0; color: #333; width: 620px;" width="620">
<tbody>
<tr>
<td class="column column-1" width="33.333333333333336%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
<table class="image_block block-2" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="pad" style="width:100%;padding-right:0px;padding-left:0px;padding-top:30px;padding-bottom:30px;">
<div class="alignment" align="center" style="line-height:10px"><img src="https://d15k2d11r6t6rl.cloudfront.net/public/users/Integrators/BeeProAgency/922871_907241/logo2.png" style="display: block; height: auto; border: 0; width: 103px; max-width: 100%;" width="103"></div>
</td>
</tr>
</table>
</td>
<td class="column column-2" width="66.66666666666667%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
<table class="social_block block-2" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="pad" style="padding-bottom:15px;padding-left:10px;padding-right:10px;padding-top:40px;text-align:right;">
<div class="alignment" align="right">
<table class="social-table" width="141px" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; display: inline-block;">
<tr>
<td style="padding:0 0 0 15px;"><a href="https://www.facebook.com/" target="_blank"><img src="https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/t-circle-default-gray/facebook@2x.png" width="32" height="32" alt="Facebook" title="Facebook" style="display: block; height: auto; border: 0;"></a></td>
<td style="padding:0 0 0 15px;"><a href="https://twitter.com/" target="_blank"><img src="https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/t-circle-default-gray/twitter@2x.png" width="32" height="32" alt="Twitter" title="Twitter" style="display: block; height: auto; border: 0;"></a></td>
<td style="padding:0 0 0 15px;"><a href="https://plus.google.com/" target="_blank"><img src="https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/t-circle-default-gray/googleplus@2x.png" width="32" height="32" alt="Google+" title="Google+" style="display: block; height: auto; border: 0;"></a></td>
</tr>
</table>
</div>
</td>
</tr>
</table>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
<table class="row row-2" align="center" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #141414; background-image: url('https://d1oco4z2z1fhwp.cloudfront.net/templates/default/95/bg_cinema_browser.jpg'); background-position: top center; background-repeat: no-repeat;">
<tbody>
<tr>
<td>
<table class="row-content" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 620px;" width="620">
<tbody>
<tr>
<td class="column column-1" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 55px; padding-bottom: 45px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
<table class="image_block block-2" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="pad" style="width:100%;padding-right:0px;padding-left:0px;padding-top:25px;">
<div class="alignment" align="center" style="line-height:10px"><img class="big" src="https://d1oco4z2z1fhwp.cloudfront.net/templates/default/95/website-min__1_.png" style="display: block; height: auto; border: 0; width: 620px; max-width: 100%;" width="620" alt="Image" title="Image"></div>
</td>
</tr>
</table>
<table class="text_block block-3" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad">
<div style="font-family: sans-serif">
<div class style="font-size: 12px; font-family: 'Permanent Marker', Impact, Charcoal, sans-serif; mso-line-height-alt: 14.399999999999999px; color: #f9d836; line-height: 1.2;">
<p style="margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 16.8px;"><span style="font-size:50px;"><strong>Recommend Movies You May Like</strong></span></p>
</div>
</div>
</td>
</tr>
</table>
<table class="text_block block-4" width="100%" border="0" cellpadding="10" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad">
<div style="font-family: 'Trebuchet MS', sans-serif">
<div class style="font-size: 12px; font-family: 'Oxygen', 'Trebuchet MS', Helvetica, sans-serif; mso-line-height-alt: 18px; color: #FFFFFF; line-height: 1.5;">
<p style="margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 30px;"><span style="font-size:20px;"><span style="font-size:20px;">We've observed your interests and made this list of movie for you.<br></span></span></p>
</div>
</div>
</td>
</tr>
</table>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
<table class="row row-3" align="center" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tbody>
<tr>
<td>
<table class="row-content stack" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 620px;" width="620">
<tbody>
<tr>
<td class="column column-1" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 40px; padding-bottom: 25px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
<table class="text_block block-1" width="100%" border="0" cellpadding="10" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad">
<div style="font-family: 'Courier New', Courier, monospace">
<div class style="font-size: 12px; font-family: 'Monda', 'Courier New', Courier, monospace; mso-line-height-alt: 14.399999999999999px; color: #f9d836; line-height: 1.2;">
<p style="margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 16.8px;"><span style="font-size:30px;"><strong><span style="font-size:30px;">Top 5 Movies Based On Your Ratings<br></span></strong></span></p>
</div>
</div>
</td>
</tr>
</table>
<table class="text_block block-2" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad" style="padding-bottom:10px;padding-left:10px;padding-right:10px;">
<div style="font-family: sans-serif">
<div class style="font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #7E7E7E; line-height: 1.2; font-family: Oxygen, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;">
<p style="margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 16.8px;">movies you may like to watch:</p>
</div>
</div>
</td>
</tr>
</table>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
<table class="row row-4" align="center" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #202020; background-image: url('{poster[0]}'); background-position: top center; background-repeat: no-repeat;">
<tbody>
<tr>
<td>
<table class="row-content" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 620px;" width="620">
<tbody>
<tr>
<td class="column column-1" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 60px; padding-bottom: 50px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
<table class="text_block block-2" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad" style="padding-left:25px;padding-right:25px;padding-top:250px;">
<div style="font-family: 'Courier New', Courier, monospace">
<div class style="font-size: 12px; font-family: 'Monda', 'Courier New', Courier, monospace; mso-line-height-alt: 14.399999999999999px; color: #f9d836; line-height: 1.2;">
<p style="margin: 0; font-size: 14px; mso-line-height-alt: 16.8px;"><span style="font-size:26px;"><strong><span style="font-size:26px;">{body[0][2]}</span></strong></span></p>
</div>
</div>
</td>
</tr>
</table>
<table class="divider_block block-3" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="pad" style="padding-bottom:10px;padding-left:25px;padding-right:25px;padding-top:10px;">
<div class="alignment" align="center">
<table border="0" cellpadding="0" cellspacing="0" role="presentation" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="divider_inner" style="font-size: 1px; line-height: 1px; border-top: 1px solid #BBBBBB;"><span>&#8202;</span></td>
</tr>
</table>
</div>
</td>
</tr>
</table>
<table class="text_block block-4" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad" style="padding-bottom:50px;padding-left:25px;padding-right:25px;">
<div style="font-family: sans-serif">
<div class style="font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #FFFFFF; line-height: 1.2; font-family: Oxygen, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;">
<p style="margin: 0; font-size: 14px; mso-line-height-alt: 16.8px;"><strong>{body[0][0]} : {body[0][1]}</strong></p>
</div>
</div>
</td>
</tr>
</table>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
<table class="row row-5" align="center" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #121212; background-image: url('{poster[1]}'); background-position: top center; background-repeat: no-repeat;">
<tbody>
<tr>
<td>
<table class="row-content" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 620px;" width="620">
<tbody>
<tr>
<td class="column column-1" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 60px; padding-bottom: 25px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
<table class="text_block block-2" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad" style="padding-left:25px;padding-right:25px;padding-top:250px;">
<div style="font-family: 'Courier New', Courier, monospace">
<div class style="font-family: 'Monda', 'Courier New', Courier, monospace; font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #f9d836; line-height: 1.2;">
<p style="margin: 0; font-size: 12px; mso-line-height-alt: 14.399999999999999px;"><span style="font-size:26px;"><strong>{body[1][2]}</strong></span></p>
</div>
</div>
</td>
</tr>
</table>
<table class="divider_block block-3" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="pad" style="padding-bottom:10px;padding-left:25px;padding-right:25px;padding-top:10px;">
<div class="alignment" align="center">
<table border="0" cellpadding="0" cellspacing="0" role="presentation" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="divider_inner" style="font-size: 1px; line-height: 1px; border-top: 1px solid #BBBBBB;"><span>&#8202;</span></td>
</tr>
</table>
</div>
</td>
</tr>
</table>
<table class="text_block block-4" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad" style="padding-bottom:50px;padding-left:25px;padding-right:25px;">
<div style="font-family: sans-serif">
<div class style="font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #FFFFFF; line-height: 1.2; font-family: Oxygen, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;">
<p style="margin: 0; font-size: 14px; mso-line-height-alt: 16.8px;"><strong>{body[1][0]} : {body[1][1]}</strong></p>
</div>
</div>
</td>
</tr>
</table>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
<table class="row row-6" align="center" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #202020; background-image: url('{poster[2]}'); background-position: top center; background-repeat: no-repeat;">
<tbody>
<tr>
<td>
<table class="row-content" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 620px;" width="620">
<tbody>
<tr>
<td class="column column-1" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 60px; padding-bottom: 55px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
<table class="text_block block-2" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad" style="padding-left:25px;padding-right:25px;padding-top:250px;">
<div style="font-family: 'Courier New', Courier, monospace">
<div class style="font-family: 'Monda', 'Courier New', Courier, monospace; font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #f9d836; line-height: 1.2;">
<p style="margin: 0; font-size: 12px; mso-line-height-alt: 14.399999999999999px;"><span style="font-size:26px;"><strong>{body[2][2]}</strong></span></p>
</div>
</div>
</td>
</tr>
</table>
<table class="divider_block block-3" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="pad" style="padding-bottom:10px;padding-left:25px;padding-right:25px;padding-top:10px;">
<div class="alignment" align="center">
<table border="0" cellpadding="0" cellspacing="0" role="presentation" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="divider_inner" style="font-size: 1px; line-height: 1px; border-top: 1px solid #BBBBBB;"><span>&#8202;</span></td>
</tr>
</table>
</div>
</td>
</tr>
</table>
<table class="text_block block-4" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad" style="padding-bottom:50px;padding-left:25px;padding-right:25px;">
<div style="font-family: sans-serif">
<div class style="font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #FFFFFF; line-height: 1.2; font-family: Oxygen, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;">
<p style="margin: 0; font-size: 14px; mso-line-height-alt: 16.8px;"><strong>{body[2][0]} : {body[2][1]}</strong></p>
</div>
</div>
</td>
</tr>
</table>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
<table class="row row-7" align="center" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #121212; background-image: url('{poster[3]}'); background-position: top center; background-repeat: no-repeat;">
<tbody>
<tr>
<td>
<table class="row-content" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 620px;" width="620">
<tbody>
<tr>
<td class="column column-1" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 60px; padding-bottom: 25px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
<table class="text_block block-2" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad" style="padding-left:25px;padding-right:25px;padding-top:250px;">
<div style="font-family: 'Courier New', Courier, monospace">
<div class style="font-family: 'Monda', 'Courier New', Courier, monospace; font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #f9d836; line-height: 1.2;">
<p style="margin: 0; font-size: 12px; mso-line-height-alt: 14.399999999999999px;"><span style="font-size:26px;"><strong>{body[3][2]}</strong></span></p>
</div>
</div>
</td>
</tr>
</table>
<table class="divider_block block-3" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="pad" style="padding-bottom:10px;padding-left:25px;padding-right:25px;padding-top:10px;">
<div class="alignment" align="center">
<table border="0" cellpadding="0" cellspacing="0" role="presentation" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="divider_inner" style="font-size: 1px; line-height: 1px; border-top: 1px solid #BBBBBB;"><span>&#8202;</span></td>
</tr>
</table>
</div>
</td>
</tr>
</table>
<table class="text_block block-4" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad" style="padding-bottom:50px;padding-left:25px;padding-right:25px;">
<div style="font-family: sans-serif">
<div class style="font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #FFFFFF; line-height: 1.2; font-family: Oxygen, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;">
<p style="margin: 0; mso-line-height-alt: 14.399999999999999px;"><strong>{body[3][0]} : {body[3][1]}</strong></p>
<p style="margin: 0; font-size: 14px; mso-line-height-alt: 16.8px;"><strong>&nbsp;</strong></p>
</div>
</div>
</td>
</tr>
</table>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
<table class="row row-8" align="center" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #202020; background-image: url('{poster[4]}'); background-position: top center; background-repeat: no-repeat;">
<tbody>
<tr>
<td>
<table class="row-content" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 620px;" width="620">
<tbody>
<tr>
<td class="column column-1" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 60px; padding-bottom: 55px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
<table class="text_block block-2" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad" style="padding-left:25px;padding-right:25px;padding-top:250px;">
<div style="font-family: 'Courier New', Courier, monospace">
<div class style="font-family: 'Monda', 'Courier New', Courier, monospace; font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #f9d836; line-height: 1.2;">
<p style="margin: 0; font-size: 12px; mso-line-height-alt: 14.399999999999999px;"><span style="font-size:26px;"><strong>{body[4][2]}</strong></span></p>
</div>
</div>
</td>
</tr>
</table>
<table class="divider_block block-3" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="pad" style="padding-bottom:10px;padding-left:25px;padding-right:25px;padding-top:10px;">
<div class="alignment" align="center">
<table border="0" cellpadding="0" cellspacing="0" role="presentation" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="divider_inner" style="font-size: 1px; line-height: 1px; border-top: 1px solid #BBBBBB;"><span>&#8202;</span></td>
</tr>
</table>
</div>
</td>
</tr>
</table>
<table class="text_block block-4" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad" style="padding-bottom:50px;padding-left:25px;padding-right:25px;">
<div style="font-family: sans-serif">
<div class style="font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #FFFFFF; line-height: 1.2; font-family: Oxygen, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;">
<p style="margin: 0; font-size: 14px; mso-line-height-alt: 16.8px;"><strong>{body[4][0]} : {body[4][1]}</strong></p>
</div>
</div>
</td>
</tr>
</table>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
<table class="row row-9 mobile_hide" align="center" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #161616; background-image: url('https://d1oco4z2z1fhwp.cloudfront.net/templates/default/95/winticket_bg.jpg'); background-position: top center; background-repeat: repeat;">
<tbody>
<tr>
<td>
<table class="row-content stack" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 620px;" width="620">
<tbody>
<tr>
<td class="column column-1" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-left: 35px; padding-right: 35px; vertical-align: top; padding-top: 35px; padding-bottom: 35px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
<table class="text_block block-2" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad" style="padding-bottom:10px;padding-left:10px;padding-right:10px;padding-top:50px;">
<div style="font-family: sans-serif">
<div class style="font-family: 'Permanent Marker', Impact, Charcoal, sans-serif; font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #f9d836; line-height: 1.2;">
<p style="margin: 0; text-align: center; font-size: 12px; mso-line-height-alt: 14.399999999999999px;"><span style="font-size:30px;">How You Watched Any Of These Movies ?</span></p>
</div>
</div>
</td>
</tr>
</table>
<table class="text_block block-3" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad">
<div style="font-family: sans-serif">
<div class style="font-size: 12px; font-family: Oxygen, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 18px; color: #FFFFFF; line-height: 1.5;">
<p style="margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 25.5px;"><span style="font-size:17px;">Whenever you watched these movies, please come back to this email and rate them; to make this recommends better for you!<br></span></p>
<p style="margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 25.5px;"><span style="font-size:17px;"> All you need to do is to click on the link below:<br></span></p>
</div>
</div>
</td>
</tr>
</table>
<table class="button_block block-5" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="pad" style="padding-bottom:40px;padding-left:10px;padding-right:10px;padding-top:40px;text-align:center;">
<div class="alignment" align="center">
<!--[if mso]><v:roundrect xmlns:v="urn:schemas-microsoft-com:vml" xmlns:w="urn:schemas-microsoft-com:office:word" href="mailto:imdb.random@gmail.com?subject=Rate%20Movies%20Reply&body=" style="height:53px;width:324px;v-text-anchor:middle;" arcsize="67%" stroke="false" fillcolor="#d3b73a"><w:anchorlock/><v:textbox inset="0px,0px,0px,5px"><center style="color:#ffffff; font-family:'Courier New', Courier, monospace; font-size:24px"><![endif]--><a href="mailto:imdb.random@gmail.com?subject=Rate%20Movies%20Reply&body=" target="_blank" style="text-decoration:none;display:inline-block;color:#ffffff;background-color:#d3b73a;border-radius:35px;width:auto;border-top:0px solid transparent;font-weight:400;border-right:0px solid transparent;border-bottom:0px solid transparent;border-left:0px solid transparent;padding-top:0px;padding-bottom:5px;font-family:'Monda', 'Courier New', Courier, monospace;font-size:24px;text-align:center;mso-border-alt:none;word-break:keep-all;"><span style="padding-left:55px;padding-right:55px;font-size:24px;display:inline-block;letter-spacing:normal;"><span dir="ltr" style="word-break: break-word;"><span style="line-height: 48px;" dir="ltr" data-mce-style><strong>Rate Movies Now!</strong></span></span></span></a>
<!--[if mso]></center></v:textbox></v:roundrect><![endif]-->
</div>
</td>
</tr>
</table>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
<table class="row row-10" align="center" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-image: url('https://d1oco4z2z1fhwp.cloudfront.net/templates/default/95/dark_geometric.png'); background-repeat: repeat;">
<tbody>
<tr>
<td>
<table class="row-content stack" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 620px;" width="620">
<tbody>
<tr>
<td class="column column-1" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-left: 25px; padding-right: 25px; vertical-align: top; padding-top: 25px; padding-bottom: 25px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
<table class="text_block block-1" width="100%" border="0" cellpadding="10" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad">
<div style="font-family: sans-serif">
<div class style="font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #8C8C8C; line-height: 1.2; font-family: Oxygen, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;">
<p style="margin: 0; font-size: 12px; text-align: center; mso-line-height-alt: 14.399999999999999px;"><span style="background-color:transparent;font-size:12px;">Made By Us © All rights reserved :))</span></p>
</div>
</div>
</td>
</tr>
</table>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
<table class="row row-11" align="center" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tbody>
<tr>
<td>
<table class="row-content stack" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 620px;" width="620">
<tbody>
<tr>
<td class="column column-1" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 5px; padding-bottom: 5px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
<table class="icons_block block-1" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="pad" style="vertical-align: middle; color: #9d9d9d; font-family: inherit; font-size: 15px; padding-bottom: 5px; padding-top: 5px; text-align: center;">
<table width="100%" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="alignment" style="vertical-align: middle; text-align: center;">
<!--[if vml]><table align="left" cellpadding="0" cellspacing="0" role="presentation" style="display:inline-block;padding-left:0px;padding-right:0px;mso-table-lspace: 0pt;mso-table-rspace: 0pt;"><![endif]-->
<!--[if !vml]><!-->
<table class="icons-inner" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; display: inline-block; margin-right: -4px; padding-left: 0px; padding-right: 0px;" cellpadding="0" cellspacing="0" role="presentation">
<!--<![endif]-->
<tr>
<td style="vertical-align: middle; text-align: center; padding-top: 5px; padding-bottom: 5px; padding-left: 5px; padding-right: 6px;"><a href="https://www.designedwithbee.com/?utm_source=editor&utm_medium=bee_pro&utm_campaign=free_footer_link" target="_blank" style="text-decoration: none;"><img class="icon" alt="Designed with BEE" src="https://d15k2d11r6t6rl.cloudfront.net/public/users/Integrators/BeeProAgency/53601_510656/Signature/bee.png" height="32" width="34" align="center" style="display: block; height: auto; margin: 0 auto; border: 0;"></a></td>
<td style="font-family: Oxygen, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; font-size: 15px; color: #9d9d9d; vertical-align: middle; letter-spacing: undefined; text-align: center;"><a href="https://www.designedwithbee.com/?utm_source=editor&utm_medium=bee_pro&utm_campaign=free_footer_link" target="_blank" style="color: #9d9d9d; text-decoration: none;">Designed with BEE</a></td>
</tr>
</table>
</td>
</tr>
</table>
</td>
</tr>
</table>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table><!-- End -->
</body>

</html>"""


    text = f"""\
    <!DOCTYPE html>
<html xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office" lang="en">

<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]-->
<!--[if !mso]><!-->
<link href="https://fonts.googleapis.com/css?family=Permanent+Marker" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Monda" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Oxygen" rel="stylesheet" type="text/css">
<!--<![endif]-->
<style>
* {{
box-sizing: border-box;
}}

body {{
margin: 0;
padding: 0;
}}

a[x-apple-data-detectors] {{
color: inherit !important;
text-decoration: inherit !important;
}}

#MessageViewBody a {{
color: inherit;
text-decoration: none;
}}

p {{
line-height: inherit
}}

.desktop_hide,
.desktop_hide table {{
mso-hide: all;
display: none;
max-height: 0px;
overflow: hidden;
}}

@media (max-width:640px) {{

.desktop_hide table.icons-inner,
.social_block.desktop_hide .social-table {{
display: inline-block !important;
}}

.icons-inner {{
text-align: center;
}}

.icons-inner td {{
margin: 0 auto;
}}

.image_block img.big,
.row-content {{
width: 100% !important;
}}

.mobile_hide {{
display: none;
}}

.stack .column {{
width: 100%;
display: block;
}}

.mobile_hide {{
min-height: 0;
max-height: 0;
max-width: 0;
overflow: hidden;
font-size: 0px;
}}

.desktop_hide,
.desktop_hide table {{
display: table !important;
max-height: none !important;
}}

.row-9 .column-1 .block-5.button_block td.pad {{
padding: 5px !important;
}}

.row-9 .column-1 .block-5.button_block a span,
.row-9 .column-1 .block-5.button_block div,
.row-9 .column-1 .block-5.button_block div span {{
line-height: 2 !important;
}}
}}
</style>
</head>

<body style="background-color: #202020; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;">
<table class="nl-container" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #202020;">
<tbody>
<tr>
<td>
<table class="row row-1" align="center" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-image: url('https://d1oco4z2z1fhwp.cloudfront.net/templates/default/95/dark_geometric.png'); background-position: top center; background-repeat: repeat;">
<tbody>
<tr>
<td>
<table class="row-content stack" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #333; border-radius: 0; width: 620px;" width="620">
<tbody>
<tr>
<td class="column column-1" width="33.333333333333336%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
<table class="image_block block-2" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="pad" style="width:100%;padding-right:0px;padding-left:0px;padding-top:30px;padding-bottom:30px;">
<div class="alignment" align="center" style="line-height:10px"><img src="https://d15k2d11r6t6rl.cloudfront.net/public/users/Integrators/BeeProAgency/922871_907241/logo.png" style="display: block; height: auto; border: 0; width: 103px; max-width: 100%;" width="103"></div>
</td>
</tr>
</table>
</td>
<td class="column column-2" width="66.66666666666667%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
<table class="social_block block-2" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="pad" style="padding-bottom:15px;padding-left:10px;padding-right:10px;padding-top:40px;text-align:right;">
<div class="alignment" align="right">
<table class="social-table" width="141px" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; display: inline-block;">
<tr>
<td style="padding:0 0 0 15px;"><a href="https://www.facebook.com/" target="_blank"><img src="https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/t-circle-default-gray/facebook@2x.png" width="32" height="32" alt="Facebook" title="Facebook" style="display: block; height: auto; border: 0;"></a></td>
<td style="padding:0 0 0 15px;"><a href="https://twitter.com/" target="_blank"><img src="https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/t-circle-default-gray/twitter@2x.png" width="32" height="32" alt="Twitter" title="Twitter" style="display: block; height: auto; border: 0;"></a></td>
<td style="padding:0 0 0 15px;"><a href="https://plus.google.com/" target="_blank"><img src="https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/t-circle-default-gray/googleplus@2x.png" width="32" height="32" alt="Google+" title="Google+" style="display: block; height: auto; border: 0;"></a></td>
</tr>
</table>
</div>
</td>
</tr>
</table>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
<table class="row row-2" align="center" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #141414; background-image: url('https://d1oco4z2z1fhwp.cloudfront.net/templates/default/95/bg_cinema_browser.jpg'); background-position: top center; background-repeat: no-repeat;">
<tbody>
<tr>
<td>
<table class="row-content" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 620px;" width="620">
<tbody>
<tr>
<td class="column column-1" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 55px; padding-bottom: 45px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
<table class="image_block block-2" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="pad" style="width:100%;padding-right:0px;padding-left:0px;padding-top:25px;">
<div class="alignment" align="center" style="line-height:10px"><img class="big" src="https://d1oco4z2z1fhwp.cloudfront.net/templates/default/95/website-min__1_.png" style="display: block; height: auto; border: 0; width: 620px; max-width: 100%;" width="620" alt="Image" title="Image"></div>
</td>
</tr>
</table>
<table class="text_block block-3" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad">
<div style="font-family: sans-serif">
<div class style="font-size: 12px; font-family: 'Permanent Marker', Impact, Charcoal, sans-serif; mso-line-height-alt: 14.399999999999999px; color: #4AEDB0; line-height: 1.2;">
<p style="margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 16.8px;"><span style="font-size:50px;"><strong>Recommend Movies You May Like</strong></span></p>
</div>
</div>
</td>
</tr>
</table>
<table class="text_block block-4" width="100%" border="0" cellpadding="10" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad">
<div style="font-family: 'Trebuchet MS', sans-serif">
<div class style="font-size: 12px; font-family: 'Oxygen', 'Trebuchet MS', Helvetica, sans-serif; mso-line-height-alt: 18px; color: #FFFFFF; line-height: 1.5;">
<p style="margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 30px;"><span style="font-size:20px;"><span style="font-size:20px;">We've observed your interests and made this list of movie for you.<br></span></span></p>
</div>
</div>
</td>
</tr>
</table>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
<table class="row row-3" align="center" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tbody>
<tr>
<td>
<table class="row-content stack" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 620px;" width="620">
<tbody>
<tr>
<td class="column column-1" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 40px; padding-bottom: 25px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
<table class="text_block block-1" width="100%" border="0" cellpadding="10" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad">
<div style="font-family: 'Courier New', Courier, monospace">
<div class style="font-size: 12px; font-family: 'Monda', 'Courier New', Courier, monospace; mso-line-height-alt: 14.399999999999999px; color: #4AEDB0; line-height: 1.2;">
<p style="margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 16.8px;"><span style="font-size:30px;"><strong><span style="font-size:30px;">Top 5 Movies Based On Your Ratings<br></span></strong></span></p>
</div>
</div>
</td>
</tr>
</table>
<table class="text_block block-2" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad" style="padding-bottom:10px;padding-left:10px;padding-right:10px;">
<div style="font-family: sans-serif">
<div class style="font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #7E7E7E; line-height: 1.2; font-family: Oxygen, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;">
<p style="margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 16.8px;">movies you may like to watch:</p>
</div>
</div>
</td>
</tr>
</table>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
<table class="row row-4" align="center" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #202020; background-image: url('{poster[0]}'); background-position: top center; background-repeat: no-repeat;">
<tbody>
<tr>
<td>
<table class="row-content" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 620px;" width="620">
<tbody>
<tr>
<td class="column column-1" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 60px; padding-bottom: 50px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
<table class="text_block block-2" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad" style="padding-left:25px;padding-right:25px;padding-top:250px;">
<div style="font-family: 'Courier New', Courier, monospace">
<div class style="font-size: 12px; font-family: 'Monda', 'Courier New', Courier, monospace; mso-line-height-alt: 14.399999999999999px; color: #4AEDB0; line-height: 1.2;">
<p style="margin: 0; font-size: 14px; mso-line-height-alt: 16.8px;"><span style="font-size:26px;"><strong><span style="font-size:26px;">{body[0][2]}</span></strong></span></p>
</div>
</div>
</td>
</tr>
</table>
<table class="divider_block block-3" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="pad" style="padding-bottom:10px;padding-left:25px;padding-right:25px;padding-top:10px;">
<div class="alignment" align="center">
<table border="0" cellpadding="0" cellspacing="0" role="presentation" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="divider_inner" style="font-size: 1px; line-height: 1px; border-top: 1px solid #BBBBBB;"><span>&#8202;</span></td>
</tr>
</table>
</div>
</td>
</tr>
</table>
<table class="text_block block-4" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad" style="padding-bottom:50px;padding-left:25px;padding-right:25px;">
<div style="font-family: sans-serif">
<div class style="font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #FFFFFF; line-height: 1.2; font-family: Oxygen, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;">
<p style="margin: 0; font-size: 14px; mso-line-height-alt: 16.8px;"><strong>{body[0][0]} : {body[0][1]}</strong></p>
</div>
</div>
</td>
</tr>
</table>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
<table class="row row-5" align="center" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #121212; background-image: url('{poster[1]}'); background-position: top center; background-repeat: no-repeat;">
<tbody>
<tr>
<td>
<table class="row-content" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 620px;" width="620">
<tbody>
<tr>
<td class="column column-1" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 60px; padding-bottom: 25px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
<table class="text_block block-2" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad" style="padding-left:25px;padding-right:25px;padding-top:250px;">
<div style="font-family: 'Courier New', Courier, monospace">
<div class style="font-family: 'Monda', 'Courier New', Courier, monospace; font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #4AEDB0; line-height: 1.2;">
<p style="margin: 0; font-size: 12px; mso-line-height-alt: 14.399999999999999px;"><span style="font-size:26px;"><strong>{body[1][2]}</strong></span></p>
</div>
</div>
</td>
</tr>
</table>
<table class="divider_block block-3" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="pad" style="padding-bottom:10px;padding-left:25px;padding-right:25px;padding-top:10px;">
<div class="alignment" align="center">
<table border="0" cellpadding="0" cellspacing="0" role="presentation" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="divider_inner" style="font-size: 1px; line-height: 1px; border-top: 1px solid #BBBBBB;"><span>&#8202;</span></td>
</tr>
</table>
</div>
</td>
</tr>
</table>
<table class="text_block block-4" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad" style="padding-bottom:50px;padding-left:25px;padding-right:25px;">
<div style="font-family: sans-serif">
<div class style="font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #FFFFFF; line-height: 1.2; font-family: Oxygen, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;">
<p style="margin: 0; font-size: 14px; mso-line-height-alt: 16.8px;"><strong>{body[1][0]} : {body[1][1]}</strong></p>
</div>
</div>
</td>
</tr>
</table>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
<table class="row row-6" align="center" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #202020; background-image: url('{poster[2]}'); background-position: top center; background-repeat: no-repeat;">
<tbody>
<tr>
<td>
<table class="row-content" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 620px;" width="620">
<tbody>
<tr>
<td class="column column-1" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 60px; padding-bottom: 55px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
<table class="text_block block-2" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad" style="padding-left:25px;padding-right:25px;padding-top:250px;">
<div style="font-family: 'Courier New', Courier, monospace">
<div class style="font-family: 'Monda', 'Courier New', Courier, monospace; font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #4AEDB0; line-height: 1.2;">
<p style="margin: 0; font-size: 12px; mso-line-height-alt: 14.399999999999999px;"><span style="font-size:26px;"><strong>{body[2][2]}</strong></span></p>
</div>
</div>
</td>
</tr>
</table>
<table class="divider_block block-3" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="pad" style="padding-bottom:10px;padding-left:25px;padding-right:25px;padding-top:10px;">
<div class="alignment" align="center">
<table border="0" cellpadding="0" cellspacing="0" role="presentation" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="divider_inner" style="font-size: 1px; line-height: 1px; border-top: 1px solid #BBBBBB;"><span>&#8202;</span></td>
</tr>
</table>
</div>
</td>
</tr>
</table>
<table class="text_block block-4" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad" style="padding-bottom:50px;padding-left:25px;padding-right:25px;">
<div style="font-family: sans-serif">
<div class style="font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #FFFFFF; line-height: 1.2; font-family: Oxygen, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;">
<p style="margin: 0; font-size: 14px; mso-line-height-alt: 16.8px;"><strong>{body[2][0]} : {body[2][1]}</strong></p>
</div>
</div>
</td>
</tr>
</table>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
<table class="row row-7" align="center" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #121212; background-image: url('{poster[3]}'); background-position: top center; background-repeat: no-repeat;">
<tbody>
<tr>
<td>
<table class="row-content" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 620px;" width="620">
<tbody>
<tr>
<td class="column column-1" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 60px; padding-bottom: 25px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
<table class="text_block block-2" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad" style="padding-left:25px;padding-right:25px;padding-top:250px;">
<div style="font-family: 'Courier New', Courier, monospace">
<div class style="font-family: 'Monda', 'Courier New', Courier, monospace; font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #4AEDB0; line-height: 1.2;">
<p style="margin: 0; font-size: 12px; mso-line-height-alt: 14.399999999999999px;"><span style="font-size:26px;"><strong>{body[3][2]}</strong></span></p>
</div>
</div>
</td>
</tr>
</table>
<table class="divider_block block-3" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="pad" style="padding-bottom:10px;padding-left:25px;padding-right:25px;padding-top:10px;">
<div class="alignment" align="center">
<table border="0" cellpadding="0" cellspacing="0" role="presentation" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="divider_inner" style="font-size: 1px; line-height: 1px; border-top: 1px solid #BBBBBB;"><span>&#8202;</span></td>
</tr>
</table>
</div>
</td>
</tr>
</table>
<table class="text_block block-4" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad" style="padding-bottom:50px;padding-left:25px;padding-right:25px;">
<div style="font-family: sans-serif">
<div class style="font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #FFFFFF; line-height: 1.2; font-family: Oxygen, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;">
<p style="margin: 0; mso-line-height-alt: 14.399999999999999px;"><strong>{body[3][0]} : {body[3][1]}</strong></p>
<p style="margin: 0; font-size: 14px; mso-line-height-alt: 16.8px;"><strong>&nbsp;</strong></p>
</div>
</div>
</td>
</tr>
</table>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
<table class="row row-8" align="center" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #202020; background-image: url('{poster[4]}'); background-position: top center; background-repeat: no-repeat;">
<tbody>
<tr>
<td>
<table class="row-content" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 620px;" width="620">
<tbody>
<tr>
<td class="column column-1" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 60px; padding-bottom: 55px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
<table class="text_block block-2" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad" style="padding-left:25px;padding-right:25px;padding-top:250px;">
<div style="font-family: 'Courier New', Courier, monospace">
<div class style="font-family: 'Monda', 'Courier New', Courier, monospace; font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #4AEDB0; line-height: 1.2;">
<p style="margin: 0; font-size: 12px; mso-line-height-alt: 14.399999999999999px;"><span style="font-size:26px;"><strong>{body[4][2]}</strong></span></p>
</div>
</div>
</td>
</tr>
</table>
<table class="divider_block block-3" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="pad" style="padding-bottom:10px;padding-left:25px;padding-right:25px;padding-top:10px;">
<div class="alignment" align="center">
<table border="0" cellpadding="0" cellspacing="0" role="presentation" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="divider_inner" style="font-size: 1px; line-height: 1px; border-top: 1px solid #BBBBBB;"><span>&#8202;</span></td>
</tr>
</table>
</div>
</td>
</tr>
</table>
<table class="text_block block-4" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad" style="padding-bottom:50px;padding-left:25px;padding-right:25px;">
<div style="font-family: sans-serif">
<div class style="font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #FFFFFF; line-height: 1.2; font-family: Oxygen, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;">
<p style="margin: 0; font-size: 14px; mso-line-height-alt: 16.8px;"><strong>{body[4][0]} : {body[4][1]}</strong></p>
</div>
</div>
</td>
</tr>
</table>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
<table class="row row-9" align="center" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #161616; background-image: url('https://d1oco4z2z1fhwp.cloudfront.net/templates/default/95/winticket_bg.jpg'); background-position: top center; background-repeat: repeat;">
<tbody>
<tr>
<td>
<table class="row-content stack" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 620px;" width="620">
<tbody>
<tr>
<td class="column column-1" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-left: 35px; padding-right: 35px; vertical-align: top; padding-top: 35px; padding-bottom: 35px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
<table class="text_block block-2" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad" style="padding-bottom:10px;padding-left:10px;padding-right:10px;padding-top:50px;">
<div style="font-family: sans-serif">
<div class style="font-family: 'Permanent Marker', Impact, Charcoal, sans-serif; font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #4AEDB0; line-height: 1.2;">
<p style="margin: 0; text-align: center; font-size: 12px; mso-line-height-alt: 14.399999999999999px;"><span style="font-size:30px;">How You Watched Any Of These Movies ?</span></p>
</div>
</div>
</td>
</tr>
</table>
<table class="text_block block-3" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad">
<div style="font-family: sans-serif">
<div class style="font-size: 12px; font-family: Oxygen, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 18px; color: #FFFFFF; line-height: 1.5;">
<p style="margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 25.5px;"><span style="font-size:17px;">Whenever you watched these movies, please come back to this email and rate them; to make this recommends better for you!<br></span></p>
<p style="margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 25.5px;"><span style="font-size:17px;"> All you need to do is to click on the link below:<br></span></p>
</div>
</div>
</td>
</tr>
</table>
<table class="button_block block-5" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="pad" style="padding-bottom:40px;padding-left:10px;padding-right:10px;padding-top:40px;text-align:center;">
<div class="alignment" align="center">
<!--[if mso]><v:roundrect xmlns:v="urn:schemas-microsoft-com:vml" xmlns:w="urn:schemas-microsoft-com:office:word" style="height:149px;width:230px;v-text-anchor:middle;" arcsize="24%" stroke="false" fillcolor="#4AEDB0"><w:anchorlock/><v:textbox inset="0px,0px,0px,5px"><center style="color:#ffffff; font-family:'Courier New', Courier, monospace; font-size:24px"><![endif]-->
<div style="text-decoration:none;display:inline-block;color:#ffffff;background-color:#4AEDB0;border-radius:35px;width:auto;border-top:0px solid transparent;font-weight:400;border-right:0px solid transparent;border-bottom:0px solid transparent;border-left:0px solid transparent;padding-top:0px;padding-bottom:5px;font-family:'Monda', 'Courier New', Courier, monospace;font-size:24px;text-align:center;mso-border-alt:none;word-break:keep-all;"><span style="padding-left:55px;padding-right:55px;font-size:24px;display:inline-block;letter-spacing:normal;"><span dir="ltr" style="word-break: break-word;"><span style="line-height: 48px;" dir="ltr" data-mce-style><strong>Rate Movies Now!</strong></span></span></span></div>
<!--[if mso]></center></v:textbox></v:roundrect><![endif]-->
</div>
</td>
</tr>
</table>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
<table class="row row-10" align="center" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-image: url('https://d1oco4z2z1fhwp.cloudfront.net/templates/default/95/dark_geometric.png'); background-repeat: repeat;">
<tbody>
<tr>
<td>
<table class="row-content stack" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 620px;" width="620">
<tbody>
<tr>
<td class="column column-1" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-left: 25px; padding-right: 25px; vertical-align: top; padding-top: 25px; padding-bottom: 25px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
<table class="text_block block-1" width="100%" border="0" cellpadding="10" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;">
<tr>
<td class="pad">
<div style="font-family: sans-serif">
<div class style="font-size: 12px; mso-line-height-alt: 14.399999999999999px; color: #8C8C8C; line-height: 1.2; font-family: Oxygen, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;">
<p style="margin: 0; font-size: 12px; text-align: center; mso-line-height-alt: 14.399999999999999px;"><span style="background-color:transparent;font-size:12px;">Made By AsAlwaysZahra&InFluX © All rights reserved :))</span></p>
</div>
</div>
</td>
</tr>
</table>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
<table class="row row-11" align="center" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tbody>
<tr>
<td>
<table class="row-content stack" align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 620px;" width="620">
<tbody>
<tr>
<td class="column column-1" width="100%" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 5px; padding-bottom: 5px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;">
<table class="icons_block block-1" width="100%" border="0" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="pad" style="vertical-align: middle; color: #9d9d9d; font-family: inherit; font-size: 15px; padding-bottom: 5px; padding-top: 5px; text-align: center;">
<table width="100%" cellpadding="0" cellspacing="0" role="presentation" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt;">
<tr>
<td class="alignment" style="vertical-align: middle; text-align: center;">
<!--[if vml]><table align="left" cellpadding="0" cellspacing="0" role="presentation" style="display:inline-block;padding-left:0px;padding-right:0px;mso-table-lspace: 0pt;mso-table-rspace: 0pt;"><![endif]-->
<!--[if !vml]><!-->
<table class="icons-inner" style="mso-table-lspace: 0pt; mso-table-rspace: 0pt; display: inline-block; margin-right: -4px; padding-left: 0px; padding-right: 0px;" cellpadding="0" cellspacing="0" role="presentation">
<!--<![endif]-->
<tr>
<td style="vertical-align: middle; text-align: center; padding-top: 5px; padding-bottom: 5px; padding-left: 5px; padding-right: 6px;"><a href="https://www.designedwithbee.com/?utm_source=editor&utm_medium=bee_pro&utm_campaign=free_footer_link" target="_blank" style="text-decoration: none;"><img class="icon" alt="Designed with BEE" src="https://d15k2d11r6t6rl.cloudfront.net/public/users/Integrators/BeeProAgency/53601_510656/Signature/bee.png" height="32" width="34" align="center" style="display: block; height: auto; margin: 0 auto; border: 0;"></a></td>
<td style="font-family: Oxygen, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; font-size: 15px; color: #9d9d9d; vertical-align: middle; letter-spacing: undefined; text-align: center;"><a href="https://www.designedwithbee.com/?utm_source=editor&utm_medium=bee_pro&utm_campaign=free_footer_link" target="_blank" style="color: #9d9d9d; text-decoration: none;">Designed with BEE</a></td>
</tr>
</table>
</td>
</tr>
</table>
</td>
</tr>
</table>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
</td>
</tr>
</tbody>
</table><!-- End -->
</body>

</html>
    """

    # Turn these into plain/html MIMEText objects
    # part1 = MIMEText(text, "plain")
    part2 = MIMEText(text2, "html")
    # part3 = MIMEText(css, "html")

    # Add HTML/plain-text parts to MIMEMultipart message
    # The email client will try to render the last part first
    message.attach(part2)
    # message.attach(part2)

    # Create secure connection with server and send email
    context = ssl.create_default_context()
    with smtplib.SMTP_SSL("smtp.gmail.com", 465, context=context) as server:
        server.login(sender_email, password)
        server.sendmail(
            sender_email, receiver_email, message.as_string()
        )
