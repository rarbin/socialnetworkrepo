package main;

import controllers.NewsFeed;
import models.MessagePost;
import utils.ScannerInput;

public class Driver {

    private NewsFeed newsFeed = new NewsFeed();

    public static void main(String[] args) {
        new Driver();
    }

    public Driver() {
        runMenu();
    }

    private int mainMenu(){
        return ScannerInput.readNextInt("""
               Social Network Menu
                  ---------------------
                  1) Add a Message Post
                  2) List all Posts
                  3) Update a Message Post
                  4) Delete a Message Post
                  ---------------------
                  5) Save Posts
                  6) Load Posts
                  ---------------------
                  0) Exit
               ==>>  """);
    }

    private void runMenu(){
        int option = mainMenu();

        while (option != 0){

            switch (option){
                case 1 -> addMessagePost();
                case 2 -> showPosts();
                case 3 -> updateMessagePost();
                case 4 -> deleteMessagePost();
                case 5 -> savePosts();
                case 6 -> loadPosts();
                default -> System.out.println("Invalid option entered: " + option);
            }

            //pause the program so that the user can read what we just printed to the terminal window
            ScannerInput.readNextLine("\nPress enter key to continue...");

            //display the main menu again
            option = mainMenu();
        }

        //the user chose option 0, so exit the program
        System.out.println("Exiting...bye");
        System.exit(0);
    }

    //gather the message post data from the user and add the new message post object to the arraylist
    private void addMessagePost(){

        String authorName = ScannerInput.readNextLine("Enter the Author Name:  ");
        String message = ScannerInput.readNextLine("Enter the Message:  ");

        boolean isAdded = newsFeed.addPost(new MessagePost(authorName, message));
        if (isAdded){
            System.out.println("Post Added Successfully");
        }
        else{
            System.out.println("No Post Added");
        }
    }

    //print the posts in newsfeed i.e. array list.
    private void showPosts(){
        System.out.println("List of Messages are:");
        System.out.println(newsFeed.show());
    }

    //ask the user to enter the index of the object to update, and assuming it's valid,
    //gather the new data from the user and update the selected object.
    private void updateMessagePost(){
        showPosts();
        if (newsFeed.numberOfPosts() > 0){
            //only ask the user to choose the object to update if objects exist
            int indexToUpdate = ScannerInput.readNextInt("Enter the index of the message to update ==> ");
            if (newsFeed.isValidIndex(indexToUpdate)){
                String author = ScannerInput.readNextLine("Enter the Author Name:  ");
                String message = ScannerInput.readNextLine("Enter the Message:  ");

                //pass the index of the product and the new product details to Store for updating and check for success.
                if (newsFeed.updateMessagePost(indexToUpdate, author, message)){
                    System.out.println("Update Successful");
                }
                else{
                    System.out.println("Update NOT Successful");
                }
            }
            else{
                System.out.println("There are no messages for this index number");
            }
        }
    }

    private void deleteMessagePost(){
        showPosts();
        if (newsFeed.numberOfPosts() > 0){
            //only ask the user to choose the message post to delete if posts exist
            int indexToDelete = ScannerInput.readNextInt("Enter the index of the messgae post to delete ==> ");
            //pass the index of the message post to controllers.NewsFeed for deleting and check for success.
            MessagePost messagePostToDelete = newsFeed.deleteMessagePost(indexToDelete);
            if (messagePostToDelete != null){
                System.out.println("Delete Successful! Deleted message post: " + messagePostToDelete.display());
            }
            else{
                System.out.println("Delete NOT Successful");
            }
        }
    }

    //save all the posts in the newsFeed to a file on the hard disk
    private void savePosts() {
        try {
            newsFeed.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }

    //load all the posts into the newsFeed from a file on the hard disk
    private void loadPosts() {
        try {
            newsFeed.load();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
    }

}
