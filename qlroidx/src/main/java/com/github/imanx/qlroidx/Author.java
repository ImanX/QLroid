package com.github.imanx.qlroidx;

import com.github.imanx.qlroidx.annotation.SerializedField;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ImanX.
 * QLroid | Copyrights 2018 ZarinPal Crop.
 */

public class Author {

    private String     firstName;
    private List<Post> posts;

    @SerializedField("posts")
    private class Post {
        private String title;
        private String votes;
    }
}
