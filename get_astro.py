import requests
import re
from bs4 import BeautifulSoup
html = requests.get("https://astro.click108.com.tw/daily_10.php?iAstro=10")
sp = BeautifulSoup(html.text, features = "html.parser")
for item in sp.find("div", "TODAY_CONTENT").select("p"):
    print(item.text)