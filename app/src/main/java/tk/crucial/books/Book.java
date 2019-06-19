package tk.crucial.books;

import android.databinding.BindingAdapter;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Book  implements Parcelable {
    public String id;
    public String title;
    public String subTitle;
    public String authors;
    public String publisher;
    public String publishedDate;
    public String description;
    public String thumbNail;

    public Book(String id, String title, String subTitle, String[] authors, String publisher
            , String publishedDate
            ,String description
            ,String thumbNail) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.authors = TextUtils.join(", ",authors);
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.description = description;
        this.thumbNail = thumbNail;
    }


    protected Book(Parcel in) {
        id = in.readString();
        title = in.readString();
        subTitle = in.readString();
        authors = in.readString();
        publisher = in.readString();
        publishedDate = in.readString();
        description = in.readString();
        thumbNail = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(subTitle);
        dest.writeString(authors);
        dest.writeString(publisher);
        dest.writeString(publishedDate);
        dest.writeString(description);
        dest.writeString(thumbNail);
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view,String imageUrl){
        if(!imageUrl.isEmpty()){
            Picasso.get().load(imageUrl).into(view);
        }else {
            //view.setBackgroundResource(R.drawable.book_open);
        }
    }
}
