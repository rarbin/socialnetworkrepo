package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.MessagePost;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class NewsFeed {

    private ArrayList<MessagePost> posts;

    public NewsFeed() {
        posts = new ArrayList<MessagePost>();
    }

    public boolean addPost(MessagePost post) {
        return posts.add(post);
    }

    public String show() {
        String str = "";

        for(MessagePost post : posts) {
            str += posts.indexOf(post) + ": " + post.display() + "\n";
        }

        if (str.isEmpty()){
            return "No Posts";
        }
        else {
            return str;
        }
    }

    public MessagePost deleteMessagePost(int indexToDelete) {
        if (isValidIndex(indexToDelete)) {
            return posts.remove(indexToDelete);
        }
        return null;
    }

    public boolean updateMessagePost(int indexToUpdate, String author, String message) {
        //find the object by the index number
        MessagePost foundMessage = findMessagePost(indexToUpdate);

        //if the object exists, use the details passed in the parameters to
        //update the found object in the ArrayList.
        if (foundMessage != null) {
            foundMessage.setAuthor(author);
            foundMessage.setMessage(message);
            return true;
        }

        //if the object was not found, return false, indicating that the update was not successful
        return false;
    }

    public MessagePost findMessagePost(int index) {
        if (isValidIndex(index)) {
            return posts.get(index);
        }
        return null;
    }

    public int numberOfPosts() {
        return posts.size();
    }


    /**
     * The load method uses the XStream component to read all the models.MessagePost objects from the posts.xml
     * file stored on the hard disk.  The read objects are loaded into the posts ArrayList
     *
     * @throws Exception  An exception is thrown if an error occurred during the load e.g. a missing file.
     */
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[] { MessagePost.class };

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("posts.xml"));
        posts = (ArrayList<MessagePost>) is.readObject();
        is.close();
    }

    /**
     * The save method uses the XStream component to write all the objects in the posts ArrayList
     * to the posts.xml file stored on the hard disk.
     *
     * @throws Exception  An exception is thrown if an error occurred during the save e.g. drive is full.
     */
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("posts.xml"));
        out.writeObject(posts);
        out.close();
    }

    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < posts.size());
    }


}
