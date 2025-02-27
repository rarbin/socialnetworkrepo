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

    public boolean updateMessagePost(int indexToUpdate, MessagePost updateDetails) {
        //find the object by the index number
        MessagePost foundMessage = findMessagePost(indexToUpdate);

        //if the object exists, use the details passed in the updateDetails parameter to
        //update the found object in the ArrayList.
        if (foundMessage != null) {
            foundMessage.setAuthor(updateDetails.getAuthor());
            foundMessage.setMessage(updateDetails.getMessage());
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

    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < posts.size());
    }


}
