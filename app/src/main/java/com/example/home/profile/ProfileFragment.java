package com.example.home.profile;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.home.R;
import com.example.home.models.User;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.io.File;

public class ProfileFragment extends Fragment {

    private ImageView ProfilePicImageView;
    private TextView ProfileUsernameTextView;
    private TextView ProfileEmailTextView;
    private ImageButton AddProfileImageButton;
    private ParseUser user;

    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 42;
    private File photoFile;
    public String photoFileName = "photo.jpg";
    private final String TAG = "ProfileFragment";

    public ProfileFragment(ParseUser user) {
        // Required empty public constructor
        this.user = user;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ProfilePicImageView = view.findViewById(R.id.ProfilePicImageView);
        ProfileUsernameTextView = view.findViewById(R.id.ProfileUsernameTextView);
        ProfileEmailTextView = view.findViewById(R.id.ProfileEmailTextView);
        AddProfileImageButton = view.findViewById(R.id.ProfileAddProfilePicImageView);

        ProfileUsernameTextView.setText(user.getUsername());
        ProfileEmailTextView.setText(user.getString("email"));

        ParseFile image = user.getParseFile("profileImage");
        if (image != null) {
            Glide.with(this)
                    .load(image.getUrl())
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .circleCrop()
                    .into(ProfilePicImageView);
        }

        AddProfileImageButton.setOnClickListener(new View.OnClickListener() {
            Context context = getContext();

            @Override
            public void onClick(View v) {
                launchCamera();
            }
        });
    }

    private void launchCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // create a file reference to access for future access
        photoFile = getPhotoFileUri(photoFileName);

        Uri fileProvider = FileProvider.getUriForFile(getContext(), "com.codepath.fileprovider", photoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

        if (intent.resolveActivity(getContext().getPackageManager()) != null) {
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }

    public File getPhotoFileUri(String fileName) {
        File mediaStorageDir = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG);

        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
            Log.d(TAG, "failed to create directory");
        }
        File file = new File(mediaStorageDir.getPath() + File.separator + fileName);
        return file;
    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // by this point we have the camera photo on disk
                Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                // what should width be here?
                Bitmap resizedBitmap = BitmapScaler.scaleToFitWidth(takenImage, 100);
                ProfilePicImageView.setImageBitmap(resizedBitmap);
                user.put("profileImage", new ParseFile(photoFile));
                try {
                    user.save();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Glide.with(this)
                        .load(user.getParseFile("profileImage").getUrl())
                        .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .circleCrop()
                        .into(ProfilePicImageView);
            } else {
                Toast.makeText(getContext(), "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}