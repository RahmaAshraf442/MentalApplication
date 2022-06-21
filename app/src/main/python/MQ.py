import pandas as pd
from os.path import dirname, join

filename = join(dirname(__file__), "Motivational_Quotes.csv")
df = pd.read_csv(filename, encoding = 'unicode_escape', low_memory=False)
df2 =df.sample()
list =df2.values.tolist()
Quote = '"'+list[0][2]+"."+'"'
Author = list[0][1]

def quotes():
    return (Quote)

def author():
    return (Author)





