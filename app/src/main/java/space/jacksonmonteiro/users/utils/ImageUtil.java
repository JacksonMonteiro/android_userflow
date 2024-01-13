package space.jacksonmonteiro.users.utils;
/*
Created By Jackson Monteiro on 13/01/2024
Copyright (c) 2024 GFX Consultoria
*/


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.widget.ImageView;

public class ImageUtil {
    public static final int REQUEST_IMAGE_PICK = 1;

    public static void pickImageFromGallery(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }

    public static void handleImagePickResult(Activity activity, int requestCode, int resultCode, Intent data, ImageView imageView) {
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK && data != null) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), data.getData());
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
