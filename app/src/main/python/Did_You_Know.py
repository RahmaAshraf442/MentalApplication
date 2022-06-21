import pandas as pd
from os.path import dirname, join

filename = join(dirname(__file__), "Did_You_Know.csv")
df = pd.read_csv(filename,encoding = 'unicode_escape', low_memory=False)

df2 =df.sample()
list=df2.values.tolist()

info = list[0][1]

def information():
    return (info)