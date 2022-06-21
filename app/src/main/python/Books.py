from tokenize import String
import pandas as pd
import numpy as np
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import linear_kernel

pd.set_option('display.max_columns', None)
pd.set_option('display.width', 1000)
pd.set_option('display.max_colwidth', 20)

df = pd.read_csv('Books.csv', encoding = 'unicode_escape', low_memory=False)

def Get_Arrays(text, df):
    match text:
        case "ID":
            Numpy_ID = np.array(df['Id'].values.tolist())
            ID = np.array_str(Numpy_ID)
            return ID;
        case "Name":    
            Numpy_Name = np.array(df['Name'].values.tolist())
            Name = np.array_str(Numpy_Name)
            return Name;
        case "Authors":     
            Numpy_Authors = np.array(df['Authors'].values.tolist())
            Authors = np.array_str(Numpy_Authors)
            return Authors;
        case "ISBN":  
            Numpy_ISBN = np.array(df['ISBN'].values.tolist())
            ISBN = np.array_str(Numpy_ISBN)
            return ISBN;
        case "Rating":    
            Numpy_Rating = np.array(df['Rating'].values.tolist())
            Rating = np.array_str(Numpy_Rating) 
            return Rating;
        case "RatingDistTotal":     
            Numpy_RatingDistTotal = np.array(df['RatingDistTotal'].values.tolist())
            RatingDistTotal = np.array_str(Numpy_RatingDistTotal)
            return RatingDistTotal;
        case "PagesNumber":      
            Numpy_PagesNumber = np.array(df['PagesNumber'].values.tolist())
            PagesNumber = np.array_str(Numpy_PagesNumber)
            return PagesNumber;
        case "PublishDate":     
            Numpy_PublishDate = np.array(df['PublishDate'].values.tolist())
            PublishDate = np.array_str(Numpy_PublishDate)
            return PublishDate;
        case "Publisher":     
            Numpy_Publisher = np.array(df['Publisher'].values.tolist())
            Publisher = np.array_str(Numpy_Publisher)
            return Publisher;
        case "Description":     
            Numpy_Description = np.array(df['Description'].values.tolist())
            Description = np.array_str(Numpy_Description)
            return Description;
    
def Final_Random():
    random_df = df.sample(n=100)
    id = Get_Arrays("ID", random_df)
    name = Get_Arrays("Name", random_df)
    authors = Get_Arrays("Authors", random_df)
    ISBN = Get_Arrays("ISBN", random_df)
    Rating = Get_Arrays("Rating", random_df)
    vots_num = Get_Arrays("RatingDistTotal", random_df)
    pages_num = Get_Arrays("PagesNumber", random_df)
    publish_date = Get_Arrays("PublishDate", random_df)
    publisher = Get_Arrays("Publisher", random_df)
    description = Get_Arrays("Description", random_df)
    return id, name, authors, ISBN, Rating, vots_num, pages_num, publish_date, publisher, description;

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

def Best_Recommendations():
    q_Books = Best_Dataframe()
    best_df = q_Books.head(100)
    best_list = best_df.values.tolist()
    return best_list;

q_Books = Best_Dataframe()
tfidf = TfidfVectorizer(stop_words='english')
q_Books['Description'] = q_Books['Description'].fillna('')
tfidf_matrix = tfidf.fit_transform(q_Books['Description'])
cosine_sim = linear_kernel(tfidf_matrix, tfidf_matrix)
indices = pd.Series(q_Books.index, index=q_Books['Name']).drop_duplicates()

def Content_Recommendations(title, cosine_sim=cosine_sim):
    idx = indices[title]
    sim_scores = list(enumerate(cosine_sim[idx]))
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
    sim_scores = sim_scores[1:11]
    movie_indices = [i[0] for i in sim_scores]
    recommended_books = q_Books.iloc[movie_indices]
    recommended_list = recommended_books.values.tolist()
    return recommended_list;

def Search(text):
    result = df[df.apply(lambda row: row.astype(str).str.contains(text, case=False).any(), axis=1)]
    result_list = result.values.tolist()
    return result_list;

 print(Final_Random())