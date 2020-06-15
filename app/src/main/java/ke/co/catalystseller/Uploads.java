package ke.co.catalystseller;

public class Uploads {
    private String mImageUrl;
    private String mName;
    private String uName;
    private String uEmail;
    private String uContact;

    public Uploads(){
        //empty constructor important
    }

    public Uploads(String name, String ImageUrl, String user, String email, String contact){
        if (name.trim().equals("")){
            name = "No Name";
        }
        mImageUrl = ImageUrl;
        mName = name;
        uName = user;
        uEmail = email;
        uContact = contact;
    }
    public String getName(){
        return mName;
    }

    public void setName(String name){
        mName = name;
    }

    public String getImageUrl(){
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl){
        mImageUrl= imageUrl;
    }

    public String getUser(){
        return uName;
    }

    public void setUser(String user){
        uName = user;
    }

    public String getEmail(){
        return uEmail;
    }

    public void setEmail(String email){
        uEmail = email;
    }

    public String getContact(){
        return uContact;
    }

    public void setContact(String contact){
        uContact = contact;
    }
}
