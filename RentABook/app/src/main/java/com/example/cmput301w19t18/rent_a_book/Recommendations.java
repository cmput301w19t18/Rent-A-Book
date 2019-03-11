package com.example.cmput301w19t18.rent_a_book;

import android.support.v7.app.AppCompatActivity;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

/**
 * The type Recommendations.
 */
public class Recommendations extends AppCompatActivity {

    /**
     * The User id.
     */
// get current user's id from firebase
    public int userID;
    /**
     * The User i ds.
     */
// get user id's from firebase
    public int[] userIDs;
    /**
     * The Book i ds.
     */
// get book id's from firebase
    public int[] bookIDs;
    /**
     * The User ratings.
     */
// get user ratings from firebase
    public int[] userRatings;

    /**
     * The Preferred genres.
     */
// TODO change to int
    // get user genre preferences from firebase database
    public String[] preferredGenres;


    // column number should correspond to book id and row number should
    // correspond to user id; matrix should be filled by user ratings

    // 2d array to hold each book and its user given ratings
    // data from firebase
    //         |book 1 | 2 | 3 |
    //  -------|-------------
    //  user A |
    //  user B |

    /**
     * The Ratings.
     */
// maybe should be an int instead?
    public int[][] ratings = new int[userIDs.length][bookIDs.length];

    /**
     * The Recommended.
     */
// list of books to recommend to user
    public Book[] recommended;

    /**
     * The Similarity score.
     */
// list of similarity scores b/w items
    public double[] similarityScore;

    /**
     * Instantiates a new Recommendations.
     */
    public Recommendations() {
        super();
        // Read from database to get user genre preferences

        // Read from database to get user ratings

        // TODO fix the for loop to add the appropriate user rating
        // TODO would a map be better here?
        // Construct 2d matrix (users, books)
        for (int i = 0; i < ratings.length; i++) {
            for (int j = 0; j < ratings[i].length; j++) {
                ratings[i][j] = j;
            }
        }
    }


    //This is the realtime database set up -> getting reference to posts
    //FirebaseDatabase database = FirebaseDatabase.getInstance();

    // Reading to database
    //DatabaseReference ref = database.getReference("message");



    // TODO make this all one function and check if new product

    // Product cold start: a new product is added
    private Book[] productColdStart() {
        // if the number of ratings a book has is > 5 use user genre preferences
        return recommended;
    }

    // If user is new and has stated no ratings yet; pick by genre
    private Book[] visitorColdStart() {
        return recommended;
    }


    // collaborative filtering method -> no new user and no new books added
    private Book[] collaborativeFiltering() {
        // calculating item-item cosine similarity
        // TODO fix this; should be ratings lol
        // user similarities = cosine similarity of ratings
        double[] userSimilarities = cosSimilarity(userIDs, bookIDs);
        // item similarities = cosine similarity of transpose of ratings
        double[] itemSimilarities = cosSimilarity(bookIDs, userIDs);

        // sort list by ascending order and only add items to recommended if similarity is <.7
        // cutoff at max 20 items (subject to change)
        return recommended;
    }

    // TODO reference https://stackoverflow.com/questions/520241/how-do-i-calculate-the-cosine-similarity-of-two-vectors
    private double[] cosSimilarity(int[] vectA, int[] vectB)  {
        // get similarity between books and ratings using cos angle
        // closer it is to 1 the better
        double dotProduct = 0.0;
        double normOfA = 0.0;
        double normOfB = 0.0;
        double[] similarity = new double[] {};

        if (vectA.length == vectB.length) {
            for (int i = 0; i < vectA.length; i++) {
                // calculate dot product
                //dotProduct += vectA[i] * vectB[i];

                // calculate norms of each vector
                //normOfA += Math.pow(vectA[i], 2);
                //normOfB += Math.pow(vectB[i], 2);

                // calculate dot product
                dotProduct = vectA[i] * vectB[i];

                // calculate norms of each vector
                normOfA = Math.pow(vectA[i], 2);
                normOfB = Math.pow(vectB[i], 2);
                // TODO fix error here
                //similarity.add(dotProduct/(Math.sqrt(normOfA) * Math.sqrt(normOfB)));
            }

        }

        return similarity;
    }



}
