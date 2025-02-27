public class MessagePost {

    private String author = "";
    private String message = "";
    private int likes = 0;

    public MessagePost(String author, String message) {
        this.author = author;
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String display() {
        String str = "";

        str += (author + "\n");

        if(likes > 0) {
            str += ("  -  " + likes + " people like this.\n");
        }
        else {
            str += "0 likes.\n";
        }

        if (!message.isEmpty()){
            str += "\t" + message + "\n";
        }
        return str;
    }
}
