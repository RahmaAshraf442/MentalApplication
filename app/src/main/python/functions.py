import pandas as pd
import numpy as np
import numpy
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import linear_kernel
from os.path import dirname, join

pd.set_option('display.max_columns', None)
pd.set_option('display.width', 1000)
pd.set_option('display.max_colwidth', 20)
filename = join(dirname(__file__), "Books.csv")
df = pd.read_csv(filename, encoding = 'unicode_escape', low_memory=False)

def Random_Recommendations():
    random_df= df.sample(n=100)
    random_list = random_df.values.tolist()

    #list_array =np.asarray( random_list)

    return  random_list ;

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
    best_df = q_Books.head(100)
    random_df_to_numpy = best_df.to_numpy()
    return random_df_to_numpy;

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

def Search(text):
    result = df[df.apply(lambda row: row.astype(str).str.contains(text, case=False).any(), axis=1)]
    result_list = result.values.tolist()
    return result_list;

def mai2n ():

 Matrix = ([1,"Comprehensive Dermatologic Drug Therapy","Stephen E. Wolverton","1416024719","4.17","6","1099","3/1/2007","Saunders","Safely and effectively prescribe today's full spectrum of topical, intralesional, and systemic drugs for dermatologic disorders! Dr. Steven E. Wolverton and a team of leading international experts explain what drugs to use, when to use them"],
 [2,"Comprehensive Dermatologic Drug Therapy","Stephen E. Wolverton","1416024719","4.17","6","1099","3/1/2007","Saunders","Safely and effectively prescribe today's full spectrum of topical, intralesional, and systemic drugs for dermatologic disorders! Dr. Steven E. Wolverton and a team of leading international experts explain what drugs to use, when to use them"],
 [3,"Comprehensive Dermatologic Drug Therapy","Stephen E. Wolverton","1416024719","4.17","6","1099","3/1/2007","Saunders","Safely and effectively prescribe today's full spectrum of topical, intralesional, and systemic drugs for dermatologic disorders! Dr. Steven E. Wolverton and a team of leading international experts explain what drugs to use, when to use them"]
           )

 #list(np.string_(Matrix))
 arr1 = numpy.asarray(Matrix)
   # valid
 return (Matrix)

#print( mai2n())
#print( get_recommendations('The Boy Who Was Raised as a Dog: And Other Stories from a Child Psychiatrist's Notebook'))
