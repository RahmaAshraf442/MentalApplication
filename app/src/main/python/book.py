import pandas as pd
import numpy as np

from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import linear_kernel
from os.path import dirname, join
filename = join(dirname(__file__), "Books.csv")
pd.set_option('display.max_columns', None)
pd.set_option('display.width', 1000)
pd.set_option('display.max_colwidth', 20)

df = pd.read_csv(filename, encoding = 'unicode_escape', low_memory=False)

def Random_Recommendations():
    random_df= df.sample(n=3)
    random_list = random_df.values.tolist()
    list_array =np.asarray( random_list)

    return list_array[0] ;

def Best_Dataframe():
    averageRating = df['Rating'].mean()
    minimum_votes =df['RatingDistTotal'].quantile(0.90)
    q_Books = df.copy().loc[df['RatingDistTotal'] >= minimum_votes]

    def weighted_rating(x, m= minimum_votes, c= averageRating):
        Votes_number = x['RatingDistTotal']
        movie_rating = x['Rating']
        # Calculation based on the IMDB formula
        return (Votes_number/(Votes_number+minimum_votes) * movie_rating) + (minimum_votes/(Votes_number+minimum_votes) * averageRating)

    q_Books['score'] = q_Books.apply(weighted_rating, axis=1)
    q_Books = q_Books.sort_values('score', ascending=False)
    q_Books = q_Books.reset_index(drop=True)
    return q_Books;

def Best_recommendations():
    q_Books = Best_Dataframe()
    best_df = q_Books.head(1)
    random_df_to_numpy = best_df.to_numpy()
    return random_df_to_numpy[0];

q_Books = Best_Dataframe()
tfidf = TfidfVectorizer(stop_words='english')
q_Books['Description'] = q_Books['Description'].fillna('')
tfidf_matrix = tfidf.fit_transform(q_Books['Description'])
cosine_sim = linear_kernel(tfidf_matrix, tfidf_matrix)
indices = pd.Series(q_Books.index, index=q_Books['Name']).drop_duplicates()

def get_recommendations(title, cosine_sim=cosine_sim):
    idx = indices[title]
    sim_scores = list(enumerate(cosine_sim[idx]))
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
    sim_scores = sim_scores[1:11]
    movie_indices = [i[0] for i in sim_scores]
    return q_Books.iloc[movie_indices]

def mai2n ():

 Matrix = (["eslam","tanta","android"],
 ["eslam1", "tanta1", "android1"],
 ["eslam2", "tanta2", "android2"]
           )
 arr1 = numpy.asarray(Matrix)
   # valid
 return (Matrix[1])

#print(Random_Recommendations())