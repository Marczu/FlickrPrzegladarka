package com.marcinmejner.flickrprzegladarka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PhotoDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);

        activateToolbar(true);

        Intent intent = getIntent();

        Photo photo = (Photo) intent.getSerializableExtra(PHOTO_TRANSFER);
        if(photo != null){
            TextView photoTitle = findViewById(R.id.photo_title_tv);
            photoTitle.setText("Tytuł: " + photo.getTitle());
            TextView photoTags = findViewById(R.id.photo_tags_tv);
            photoTags.setText("TAGS : "+ photo.getTags());
            TextView photoAuthor = findViewById(R.id.photo_author_tv);
            photoAuthor.setText("Autor: " + photo.getAuthor());
            ImageView photoImage = findViewById(R.id.photo_image_iv);

            Picasso.with(this).load(photo.getLink())
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(photoImage);
        }
    }

}
